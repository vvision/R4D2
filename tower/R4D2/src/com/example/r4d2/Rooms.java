package com.example.r4d2;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Rooms extends Activity {
	private String ip;
	private String port;
	private String delay;
	private static final int NB_SENSOR = 4;
	private static final String TAG_allValues = "allValues";
	private static final String TAG_TIMESTAMP = "timestamp";
	private static final String TAG_SENSOR_ID = "sensorId";
	private static final String TAG_VALUE = "value";
	private JSONArray jsonAllValues = null;
	private String resultJSON;
	private ArrayList<ArrayList<HashMap<String, String>>> listAllValues = new ArrayList<ArrayList<HashMap<String, String>>>();
	private Activity room;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.room = this;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_message);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		Intent intent = getIntent();
		ip = intent.getStringExtra(MainActivity.ADDRESS);
		port = intent.getStringExtra(MainActivity.PORT);
		delay = intent.getStringExtra(MainActivity.DELAY);

		TextView textView = (TextView) findViewById(R.id.textview_ip);
		textView.setText(ip + ":" + port);
		Button refreshButton = (Button) findViewById(R.id.button_refresh);
		refreshButton.setOnClickListener(new ListenerBoutonRefresh());

	}

	private String getURL(int id, String port, String ip) {
		StringBuffer sb = new StringBuffer("http://");
		sb.append(ip).append(":").append(port).append("/values?sensor=")
				.append(id);
		return sb.toString();
	}

	private String getDate(String timeStamp) {
		long dv = Long.valueOf(timeStamp);
		Date df = new java.util.Date(dv);
		String theDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss",
				Locale.FRANCE).format(df);

		return theDate;

	}

	private void setTimeStamp(int indexValue) {
		String text1, text2, text3, text4;
		try {
			text1 = listAllValues.get(0).get(indexValue).get(TAG_TIMESTAMP);
		} catch (Exception n) {
			text1 = "???";
		}
		TextView textViewSensorTimeStamp1 = (TextView) findViewById(R.id.textview_sensor1TimeStamp);
		textViewSensorTimeStamp1.setText(text1);
		try {
			text2 = listAllValues.get(1).get(indexValue).get(TAG_TIMESTAMP);
		} catch (Exception n) {
			text2 = "???";
		}
		TextView textViewSensorTimeStamp2 = (TextView) findViewById(R.id.textview_sensor2TimeStamp);
		textViewSensorTimeStamp2.setText(text2);
		try {
			text3 = listAllValues.get(2).get(indexValue).get(TAG_TIMESTAMP);
		} catch (Exception n) {
			text3 = "???";
		}
		TextView textViewSensorTimeStamp3 = (TextView) findViewById(R.id.textview_sensor3TimeStamp);
		textViewSensorTimeStamp3.setText(text3);
		try {
			text4 = listAllValues.get(3).get(indexValue).get(TAG_TIMESTAMP);
		} catch (Exception n) {
			text4 = "???";
		}
		TextView textViewSensorTimeStamp4 = (TextView) findViewById(R.id.textview_sensor4TimeStamp);
		textViewSensorTimeStamp4.setText(text4);
	}

	private void setValue(int indexValue) {
		String text1, text2, text3, text4;
		String prefixValue = "VALUE : ";
		TextView textViewSensorValue1 = (TextView) findViewById(R.id.textview_sensor1Value);
		try {
			text1 = listAllValues.get(0).get(indexValue).get(TAG_VALUE);
		} catch (Exception n) {
			text1 = "???";
		}
		textViewSensorValue1.setText(prefixValue + text1);
		TextView textViewSensorValue2 = (TextView) findViewById(R.id.textview_sensor2Value);

		try {
			text2 = listAllValues.get(1).get(indexValue).get(TAG_VALUE);
		} catch (Exception n) {
			text2 = "???";
		}
		textViewSensorValue2.setText(prefixValue + text2);
		TextView textViewSensorValue3 = (TextView) findViewById(R.id.textview_sensor3Value);
		try {
			text3 = listAllValues.get(2).get(indexValue).get(TAG_VALUE);
		} catch (Exception n) {
			text3 = "???";
		}
		textViewSensorValue3.setText(prefixValue + text3);
		TextView textViewSensorValue4 = (TextView) findViewById(R.id.textview_sensor4Value);
		try {
			text4 = listAllValues.get(3).get(indexValue).get(TAG_VALUE);
		} catch (Exception n) {
			text4 = "???";
		}
		textViewSensorValue4.setText(prefixValue + text4);
	}

	private void updateView(int indexValue) {

		setTimeStamp(indexValue);
		setValue(indexValue);

	}

	public JSONObject getJSON(String url) throws Exception {
		Parser parser = new Parser(this, url);
		resultJSON = parser.getJson();
		JSONObject json = null;
		// try parse the string to a JSON object
		try {
			json = new JSONObject(resultJSON);
		} catch (JSONException e) {
			Log.e("JSON Parser", "Error parsing data " + e.toString());
		}
		return json;
	}

	private class ListenerBoutonRefresh implements OnClickListener {

		@Override
		public void onClick(View v) {
			GetData getData = new GetData();
			getData.execute();
		}

	}

	private class GetData extends AsyncTask<String, Integer, String> {

		@Override
		protected void onPostExecute(String result) {
			if (result != null && result.equals("erreur")) {
				room.finish();
			}
			listAllValues.clear();
		}

		@Override
		protected String doInBackground(String... arg0) {
			for (int j = 1; j <= NB_SENSOR; j++) {

				try {

					String url = getURL(j, port, ip);
					JSONObject json = null;
					try {
						json = getJSON(url);
						if (json == null)
							throw new Exception();
					} catch (Exception e) {
						publishProgress(-1);
						return "erreur";
					}
					ArrayList<HashMap<String, String>> values = new ArrayList<HashMap<String, String>>();
					listAllValues.add(values);
					jsonAllValues = json.getJSONArray(TAG_allValues);
					// Pour chaque valeur de sensor selon son timestamp
					for (int i = 0; i < jsonAllValues.length(); i++) {
						JSONObject c = jsonAllValues.getJSONObject(i);
						String timestamp = getDate(c.getString(TAG_TIMESTAMP));
						String sensor = c.getString(TAG_SENSOR_ID);
						String value = c.getString(TAG_VALUE);
						HashMap<String, String> map = new HashMap<String, String>();
						map.put(TAG_TIMESTAMP, timestamp);
						map.put(TAG_SENSOR_ID, sensor);
						map.put(TAG_VALUE, value);
						values.add(map);
					}
				} catch (Exception e) {
					publishProgress(-1);
					return "erreur";
				}

			}
			int nbValues = jsonAllValues.length();
			long timeToSleep;
			try {
				timeToSleep = Long.parseLong(delay);
			} catch (Exception e) {
				timeToSleep = 1000;
				publishProgress(-2);
			}

			for (int indexValue = 0; indexValue < nbValues; indexValue++) {
				publishProgress(indexValue);
				SystemClock.sleep(timeToSleep);
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			if (values[0] == -1) {
				Toast.makeText(getApplicationContext(), "connexion problem",
						Toast.LENGTH_LONG).show();
				// 1. Instantiate an AlertDialog.Builder with its

			} else if (values[0] == -2) {
				Toast.makeText(getApplicationContext(),
						"PROBLEM DELAY SO SET TO 1000", Toast.LENGTH_SHORT)
						.show();
				// 1. Instantiate an AlertDialog.Builder with its

			} else {
				updateView(values[0]);
			}

		}
	}

	private class Parser {
		private InputStream is = null;
		private String json = "";

		public String getJson() {
			return json;
		}

		public Parser(Rooms rooms, String... urls) throws Exception {

			for (String url : urls) {

				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpGet httpGet = new HttpGet(url);
				HttpParams httpParameters = httpGet.getParams();
				// Set the timeout in milliseconds until a connection is
				// established.
				int timeoutConnection = 2000;
				HttpConnectionParams.setConnectionTimeout(httpParameters,
						timeoutConnection);
				// Set the default socket timeout (SO_TIMEOUT)
				// in milliseconds which is the timeout for waiting for data.
				int timeoutSocket = 2000;
				HttpConnectionParams
						.setSoTimeout(httpParameters, timeoutSocket);
				HttpResponse httpResponse = httpClient.execute(httpGet);
				HttpEntity httpEntity = httpResponse.getEntity();
				is = httpEntity.getContent();

				BufferedReader reader = new BufferedReader(
						new InputStreamReader(is, "iso-8859-1"), 8);
				StringBuilder sb = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
				is.close();
				json = sb.toString();

			}

		}
	}

}
