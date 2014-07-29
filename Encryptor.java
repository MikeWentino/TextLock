package com.example.textlockapp;
	import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

import java.io.IOException;
import java.util.ArrayList;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
//import android.telephony.SmsManager;
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

import android.content.res.AssetManager;

	public class Encryptor
	{
	  static Random generator = new Random();
	  static File file = new File("Key3.Txt");
	  static String[] LIST = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", " ", "'", "." };
	  private Context c;	  
	  private static String KeyText;
	  
	  public Encryptor(Context context)
	  {
		  c = context;
		  
		  byte[] fileBuffer;
		  try {
			  AssetManager am = c.getAssets();
			  InputStream fileStream = am.open("Key3.txt");
			  int fileLen = fileStream.available();
			  // Read the entire resource into a local byte buffer.
			  fileBuffer = new byte[fileLen];
			  fileStream.read(fileBuffer);
			  fileStream.close();
			  KeyText = new String(fileBuffer);
		  } catch (IOException e) {
		    // exception handling
		  }
		  
	  }
	  
	  private static String cypherCharacter(String a) throws FileNotFoundException
	  {
		  Scanner readerKey = new Scanner(KeyText);
		  int location = -1;
		  for (int i = 0; i < 65; i++) {
			  if (a.equals(LIST[i]))
			  {
				  location = i;
				  break;
			  }
		  }
		  if (location == -1) {
			  return "i";
		  }
		  int randNum = generator.nextInt(49);
		  for (int i = 0; i < location * 51 + randNum; i++) {
			  readerKey.next();
		  }
		  return readerKey.next();
	  }
	  
	  public String encrypt(String input) throws IOException
	  {
		  
	    String output = "";
	    for (int i = 0; i < input.length(); i++)
	    {
	      output = output + cypherCharacter(Character.toString(input.charAt(i)));
	      if (generator.nextInt(2) == 1) {
	        output = output + "8";
	      } else {
	        output = output + "9";
	      }
	    }
	    return output;
	  }
	}
