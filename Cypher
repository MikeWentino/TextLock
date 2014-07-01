import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class Cypher
  implements ActionListener
{
  JFrame frame;
  JPanel contentPane;
  JLabel answer;
  JButton encryptOne;
  JButton encryptTwo;
  JButton decryptOne;
  JButton decryptTwo;
  JTextArea one;
  JTextArea two;
  int a;
  int b;
  
  public Cypher()
  {
    this.frame = new JFrame("Cypher");
    this.frame.setDefaultCloseOperation(3);
    
    this.contentPane = new JPanel();
    this.contentPane.setLayout(null);
    
    this.one = new JTextArea(10, 22);
    this.one.setLineWrap(true);
    this.one.setTabSize(4);
    

    JScrollPane scrollingArea1 = new JScrollPane(this.one);
    this.contentPane.add(scrollingArea1);
    
    this.two = new JTextArea(10, 22);
    this.two.setLineWrap(true);
    this.two.setTabSize(4);
    

    JScrollPane scrollingArea2 = new JScrollPane(this.two);
    this.contentPane.add(scrollingArea2);
    
    this.encryptOne = new JButton("Encrypt");
    this.encryptOne.setActionCommand("ENCRYPTONE");
    this.encryptOne.addActionListener(this);
    this.contentPane.add(this.encryptOne);
    
    this.encryptTwo = new JButton("Encrypt");
    this.encryptTwo.setActionCommand("ENCRYPTTWO");
    this.encryptTwo.addActionListener(this);
    this.contentPane.add(this.encryptTwo);
    
    this.decryptOne = new JButton("Decrypt");
    this.decryptOne.setActionCommand("DECRYPTONE");
    this.decryptOne.addActionListener(this);
    this.contentPane.add(this.decryptOne);
    
    this.decryptTwo = new JButton("Decrypt");
    this.decryptTwo.setActionCommand("DECRYPTTWO");
    this.decryptTwo.addActionListener(this);
    this.contentPane.add(this.decryptTwo);
    
    Insets insets = this.contentPane.getInsets();
    
    Dimension size = scrollingArea1.getPreferredSize();
    scrollingArea1.setBounds(25 + insets.left, 25 + insets.top, 
      size.width, size.height);
    
    size = scrollingArea2.getPreferredSize();
    scrollingArea2.setBounds(300 + insets.left, 25 + insets.top, 
      size.width, size.height);
    
    size = this.encryptOne.getPreferredSize();
    this.encryptOne.setBounds(25 + insets.left, 200 + insets.top, 
      size.width + 38, size.height + 12);
    
    size = this.encryptTwo.getPreferredSize();
    this.encryptTwo.setBounds(300 + insets.left, 200 + insets.top, 
      size.width + 38, size.height + 12);
    
    size = this.decryptOne.getPreferredSize();
    this.decryptOne.setBounds(151 + insets.left, 200 + insets.top, 
      size.width + 38, size.height + 12);
    
    size = this.decryptTwo.getPreferredSize();
    this.decryptTwo.setBounds(427 + insets.left, 200 + insets.top, 
      size.width + 38, size.height + 12);
    
    Insets insets1 = this.frame.getInsets();
    this.frame.setSize(577 + insets1.left + insets1.right, 
      290 + insets1.top + insets1.bottom);
    this.frame.setResizable(false);
    this.frame.setContentPane(this.contentPane);
    

    this.frame.setVisible(true);
  }
  
  public void actionPerformed(ActionEvent event)
  {
    try
    {
      Decryptor decryptor = new Decryptor();
      Encryptor encryptor = new Encryptor();
      String eventName = event.getActionCommand();
      if (eventName.equals("ENCRYPTONE")) {
        this.one.setText(encryptor.encrypt(this.one.getText()));
      } else if (eventName.equals("DECRYPTONE")) {
        this.one.setText(decryptor.decrypt(this.one.getText()));
      } else if (eventName.equals("ENCRYPTTWO")) {
        this.two.setText(encryptor.encrypt(this.two.getText()));
      } else if (eventName.equals("DECRYPTTWO")) {
        this.two.setText(decryptor.decrypt(this.two.getText()));
      }
    }
    catch (IOException e)
    {
      System.out.println("error opening file" + e);
    }
  }
  
  private static void runGUI()
  {
    JFrame.setDefaultLookAndFeelDecorated(true);
    Cypher greeting = new Cypher();
  }
  
  public static void main(String[] args)
  {
    SwingUtilities.invokeLater(new Cypher.1());
  }
}
