package com.example.hp.waverrmerchant;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Nikhil on 5/27/2015.
 */
public class ImageSelect extends ActionBarActivity {

    TextView tv;
    private RecyclerView mRecyclerView;
    private ImageAdapter mAdapter;
    private ArrayList<Deal> deals;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageselect);

        deals = new ArrayList<>();
        mRecyclerView = (RecyclerView)findViewById(R.id.imageRecycleView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new ImageAdapter(deals, R.layout.imageselect_card, this);
        mRecyclerView.setAdapter(mAdapter);

        getDeals();
    }






    private void getDeals() {
        String[] url = {
                "http://waverr.in/app/getimages.php"

        };




        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Getting Images..");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setIndeterminate(true);
        dialog.setCancelable(true);
        dialog.show();

        JSONObtainer obtainer = new JSONObtainer() {
            @Override
            protected void onPostExecute(JSONArray array) {

                String things[] = {
                        "imageURL"

                };

                try {

                    if(array!=null) {
                        for(int i=0; i<array.length(); i++) {
                            JSONObject object = array.getJSONObject(i);

                            //Gson gson = new Gson();
                            Deal newDeal = new Deal();
                            newDeal.setImageURL(object.getString(things[0]));
                            deals.add(newDeal);
                        }
                    }
                    //pullToRefreshView.onRefreshComplete();

                    dialog.dismiss();
                    mAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        };
        obtainer.execute(url);
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

}