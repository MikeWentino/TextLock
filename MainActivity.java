package com.example.textlockapp;

import java.io.IOException;
import java.util.ArrayList;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;


public class MainActivity extends ActionBarActivity {
	
	ArrayList<String> messages_to_show = new ArrayList<String>();
	
	ArrayAdapter<String> Adpt;
	private static Context context;
	
	private void initList() {
	
		messages_to_show.add("0");
		messages_to_show.add("1");
		messages_to_show.add("2");
		messages_to_show.add("3");
		messages_to_show.add("4");
		messages_to_show.add("5");
		messages_to_show.add("6");
		messages_to_show.add("7");
		messages_to_show.add("8");
	}
		

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getApplicationContext();
		setContentView(R.layout.activity_main);
		
		initList();
		
		Adpt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, messages_to_show);
		
		ListView DispMessages = (ListView) findViewById(R.id.displayed_messages);
		
		DispMessages.setAdapter(Adpt);
	
		DispMessages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		 
		     public void onItemClick(AdapterView<?> parentAdapter, View view, int position, long id) {
		              
		         TextView clickedView = (TextView) view;
		 
		         Toast.makeText(MainActivity.this, "Item with id ["+id+"] - Position ["+position+"] - Planet ["+clickedView.getText()+"]", Toast.LENGTH_SHORT).show();
		 
		     }
		});
		
		registerForContextMenu(DispMessages);
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
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu,  v,  menuInfo);
		AdapterContextMenuInfo aInfo = (AdapterContextMenuInfo) menuInfo;
		
		String word = Adpt.getItem(aInfo.position);
		
		menu.setHeaderTitle("Options for " + word);
		menu.add(1, 1, 1, "Details");
		menu.add(1, 2, 2, "Delete");
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		int itemID = item.getItemId();
		
		AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item.getMenuInfo();
		int index = menuInfo.position;
		
		if (itemID == 2) {
			deleteMessage(index);
			Toast.makeText(this,  "Message Deleted", Toast.LENGTH_SHORT).show();
		}
		else {
			Toast.makeText(this,  "Item id ["+itemID+"]", Toast.LENGTH_SHORT).show();
		}
		return true;
	}

	public void deleteMessage(int index) {
		messages_to_show.remove(index);
		Adpt.notifyDataSetChanged();
	}
	
	public void sendMessage(View view) throws IOException {
		EditText TextBox = (EditText)findViewById(R.id.text_box);
		String text_message = TextBox.getText().toString();
		if (!text_message.trim().equals("")) {
			TextBox.setText("");
			
			Toast.makeText(this,  "Message Sent", Toast.LENGTH_SHORT).show();
			
			Encryptor encryptor = new Encryptor(context);
		
			messages_to_show.add(encryptor.encrypt(text_message));

			Adpt.notifyDataSetChanged();
		}
	}
}
