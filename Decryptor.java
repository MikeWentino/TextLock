package com.example.textlockapp;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import android.content.Context;
import android.content.res.AssetManager;

public class Decryptor
{
	static String[] LIST = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", " ", "'", "." };
	private Context c;
	private String KeyText;
	private String[][] Key;
	  
	public Decryptor(Context context) 
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
	public String decrypt(String input)
	{
		String output = "", temp = "";
		
		for (int i = 0; i < input.length(); i++) {
			if ((Character.toString(input.charAt(i)).equals("8")) || (Character.toString(input.charAt(i)).equals("9"))) {
				for(int j=0; j<65; j++)
					  for(int k=0; k<50; k++)
						  if(temp.equals(Key[j][k]))
							  output = output + LIST[j];
				temp = "";
			} else {
				temp = temp + Character.toString(input.charAt(i));
			}
		}
		return output;
    }
}
