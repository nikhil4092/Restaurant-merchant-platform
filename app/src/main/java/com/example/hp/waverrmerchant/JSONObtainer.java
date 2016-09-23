package com.example.hp.waverrmerchant;

import org.json.JSONArray;

import android.os.AsyncTask;

public class JSONObtainer extends AsyncTask<String[], Void, JSONArray>{

	@Override
	protected JSONArray doInBackground(String[]... url) {
		// TODO Auto-generated method stub
		return JSONfunctions.getJSONfromURL(url[0]);
	}

}
