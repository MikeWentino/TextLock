package com.example.textlockapp;

import java.io.IOException;
import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
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
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
	
	public static ArrayList<String> messages_to_show = new ArrayList<String>();
	
	public static ArrayAdapter<String> Adpt;
	
	Encryptor encryptor;
	Decryptor decryptor;
	
	private static Context context;
	
	private void initList() {
	}
		

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getApplicationContext();
		setContentView(R.layout.activity_main);
		
		initList();
		
		Adpt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, messages_to_show);
		
		encryptor = new Encryptor(context);
		decryptor = new Decryptor(context);
		
		ListView DispMessages = (ListView) findViewById(R.id.displayed_messages);
		
		DispMessages.setAdapter(Adpt);

		DispMessages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		 
		       public void onItemClick(AdapterView<?> parentAdapter, View view, int position, long id) {
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
		
		menu.setHeaderTitle("Options for Selected Message");
		menu.add(1, 1, 1, "Details");
		menu.add(1, 2, 2, "Delete");
		menu.add(1, 3, 3, "Encrypt");
		menu.add(1, 4, 4, "Decrypt");
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		
		int itemID = item.getItemId();
		
		AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item.getMenuInfo();
		int index = menuInfo.position;
		
		if (itemID == 1) {
			Toast.makeText(this, "Details", Toast.LENGTH_SHORT).show();
		}
		
		if (itemID == 2) {
			deleteMessage(index);
			Toast.makeText(this, "Message Deleted", Toast.LENGTH_SHORT).show();
		}
		
		if (itemID == 3) {
			try {
				encryptMessage(index);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (itemID == 4) {
			decryptMessage(index);
		}
		
		return true;
	}

	public void deleteMessage(int index) {
		messages_to_show.remove(index);
			
		Adpt.notifyDataSetChanged();
	}
	
	public void encryptMessage(int index) throws IOException { 
		String text_message = messages_to_show.get(index);
		
		String[] parts = text_message.split(":     ");
		String part1 = parts[0];
		text_message = parts[1];
		
		messages_to_show.set(index, part1 + ":     " + encryptor.encrypt(text_message));
		
		Adpt.notifyDataSetChanged();
	}
	
	public void decryptMessage(int index) {
		String text_message = messages_to_show.get(index);
		
		String[] parts = text_message.split(":     ");
		String part1 = parts[0]; 
		text_message = parts[1]; 
		
		text_message = decryptor.decrypt(text_message);
		
		if (!(text_message == "")) {
			messages_to_show.set(index, part1 + ":     " + text_message);
			Adpt.notifyDataSetChanged();
		}
	}
	
	public void sendMessage(View view) throws IOException {
		EditText TextBox = (EditText)findViewById(R.id.text_box);
		EditText NumberBox = (EditText)findViewById(R.id.phone_number);
		
		String text_message = TextBox.getText().toString();
		String phoneNumber = NumberBox.getText().toString();
		
		if ((!text_message.trim().equals("")) && (!phoneNumber.trim().equals("")) ) {
			TextBox.setText("");
			
			Toast.makeText(this,  "Message Sent", Toast.LENGTH_SHORT).show();
			
			Encryptor encryptor = new Encryptor(context);
		
			text_message = encryptor.encrypt(text_message);
			
			messages_to_show.add("Sent:     " + text_message);
			
			SmsManager smsManager = SmsManager.getDefault();
			
			smsManager.sendTextMessage(phoneNumber, null, text_message, null, null);

			Adpt.notifyDataSetChanged();
			
		}
	}
}
