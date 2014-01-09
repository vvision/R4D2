package com.example.r4d2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	public final static String ADDRESS = "com.example.r4d2.ADDRESS";
	public final static String PORT = "com.example.r4d2.PORT";
	public final static String DELAY = "com.example.r4d2.DELAY";
	private final String SERVER_IP = "192.168.1.97";
	private final String SERVER_PORT = "8080";
	private final String DEFAULT_DELAY = "1000";
	private EditText editAddress;
	private EditText editPort;
	private EditText editDelay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		editAddress = (EditText) findViewById(R.id.edit_address);
		editAddress.setText(SERVER_IP);
		editPort = (EditText) findViewById(R.id.edit_port);
		editPort.setText(SERVER_PORT);
		editDelay = (EditText) findViewById(R.id.edit_delay);
		editDelay.setText(DEFAULT_DELAY);

	}

	/**
	 * To transfer IP.
	 * 
	 * @param view
	 */
	public void sendMessage(View view) {
		Intent intent = new Intent(this, Rooms.class);
		String address = editAddress.getText().toString();
		intent.putExtra(ADDRESS, address);
		String port = editPort.getText().toString();
		intent.putExtra(PORT, port);
		String delay = editDelay.getText().toString();
		intent.putExtra(DELAY, delay);
		startActivity(intent);

	}
}
