package com.example.hp.waverrmerchant;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;

/**
 * Created by HP on 21-05-2015.
 */
public class AddAnnouncement extends Activity implements View.OnClickListener {

    EditText announcementText;
    Button announcementSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addannouncements);
        announcementText=(EditText)findViewById(R.id.AnnouncementText);
        announcementSend=(Button)findViewById(R.id.upload);
        announcementSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.upload:{
                if(announcementText.getText().toString().compareTo("")==0){
                    new AlertDialog.Builder(this)
                            .setTitle("Empty Fields!")
                            .setMessage("You cannot leave any field empty. Please fill in all the details")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                }
                            })

                            .show();
                }

                else {
                    String[] things = {
                            "http://waverr.in/app/merchantaddannouncement.php",
                            "announcement", announcementText.getText().toString()
                    };

                    new JSONObtainer() {
                        protected void onPostExecute(JSONArray array) {
                            Toast.makeText(getBaseContext(), "Data Added", Toast.LENGTH_SHORT).show();
                        }
                    }.execute(things);
                }
                break;

            }
        }
    }
}