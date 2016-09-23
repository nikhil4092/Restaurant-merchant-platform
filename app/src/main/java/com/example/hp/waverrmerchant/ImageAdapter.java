package com.example.hp.waverrmerchant;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

	private List<Deal> deals;
	private int rowLayout;
	private Context mContext;


	public ImageAdapter(List<Deal> deals, int rowLayout, Context context) {
		this.deals = deals;
		this.rowLayout = rowLayout;
		this.mContext = context;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
		View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
		return new ViewHolder(v);

	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int i) {
		final Deal deal = deals.get(i);


		Gson gson = new Gson();
		final String dealString = gson.toJson(deal);

        Picasso.with(mContext)
               .load(deal.getImageURL())
               .into(viewHolder.dealImage);
        viewHolder.dealImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "clicked", Toast.LENGTH_SHORT).show();
                Intent returnIntent = new Intent(mContext,AddDeal.class);
                returnIntent.putExtra("result",deal.getImageURL());

                mContext.startActivity(returnIntent);

            }
        });

	}

	@Override
	public int getItemCount() {
		return deals == null ? 0 : deals.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {

		public ImageView dealImage;



		public ViewHolder(View itemView) {
			super(itemView);

			dealImage = (ImageView)itemView.findViewById(R.id.Image);


		}
	}



}