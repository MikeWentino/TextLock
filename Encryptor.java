package com.example.textlockapp;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

import android.content.Context;

import android.content.res.AssetManager;

public class Encryptor
{
	  static Random generator = new Random();
	  static File file = new File("Key3.Txt");
	  static String[] LIST = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", " ", "'", "." };
	  private Context c;	  
	  private static String KeyText;
	  private static String[][] Key;

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
		  
		  //creating Key
		  Scanner readerKey = new Scanner(KeyText);
		  Key = new String[65][50];
		  
		  for (int i = 0; i < 65; i++)
		  {
			  for(int j=0; j<50; j++)
			  {
				  Key[i][j] = readerKey.next();
			  }
			  readerKey.next();
		  }
		  readerKey.close();
		  
	  }

	  private static int keyLocation(String a)
	  {
		  int location = -1;
		  for (int i = 0; i < 65; i++) {
			  if (a.equals(LIST[i]))
			  {
				  location = i;
				  break;
			  }
		  }
		  return location;
	  }

	  public String encrypt(String input) throws IOException
	  {

		  String output = "";
		  for (int i = 0; i < input.length(); i++)
		  {
			  output = output + Key[keyLocation(Character.toString(input.charAt(i)))][generator.nextInt(49)];
			  if (generator.nextInt(2) == 1) {
				  output = output + "8";
			  } else {
				  output = output + "9";
			  }
		  }
		  return output;
	  }
}
