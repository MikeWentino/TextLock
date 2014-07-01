import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Decryptor
{
  public String decrypt(String input)
    throws FileNotFoundException
  {
    File file = new File("Key3.Txt");
    String input2 = "";String output = "";String temp = "";String temp2 = "";String temp3 = "";
    Scanner readerKey = new Scanner(file);
    for (int i = 0; i < input.length(); i++) {
      if ((Character.toString(input.charAt(i)).equals("8")) || (Character.toString(input.charAt(i)).equals("9"))) {
        input2 = input2 + " ";
      } else {
        input2 = input2 + Character.toString(input.charAt(i));
      }
    }
    Scanner readerInput2 = new Scanner(input2);
    while (readerInput2.hasNext())
    {
      temp = readerInput2.next();
      
      int i = 0;
      readerKey = new Scanner(file);
      while (readerKey.hasNext())
      {
        i++;
        temp2 = readerKey.next();
        if (temp.equals("i"))
        {
          output = output + "@";
          break;
        }
        if (temp2.equals(temp))
        {
          for (int j = 0; j < 50 - i % 51; j++) {
            readerKey.next();
          }
          temp3 = readerKey.next();
          if (temp3.equals("/\\"))
          {
            output = output + " "; break;
          }
          output = output + temp3;
          break;
        }
      }
    }
    return output;
  }
}
