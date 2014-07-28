package com.example.textlockapp;

import java.util.ArrayList;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {
	
	ArrayList<String> messages_to_show = new ArrayList<String>();
	
	ArrayAdapter<String> Adpt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Adpt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, messages_to_show);
		
		ListView DispMessages = (ListView) findViewById(R.id.displayed_messages);
		
		DispMessages.setAdapter(Adpt);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void sendMessage(View view) {
		EditText TextBox = (EditText)findViewById(R.id.text_box);
		String text_message = TextBox.getText().toString();
		TextBox.setText("");
		
		messages_to_show.add(text_message);
		
		Adpt.notifyDataSetChanged();
	}
}
