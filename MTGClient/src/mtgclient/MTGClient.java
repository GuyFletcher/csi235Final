/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtgclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.awt.Image;
import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import java.awt.event.*;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Fletcher Hart
 */
public class MTGClient {

    /**
     * @param args the command line arguments
     */
       
   static public JFrame frame;
    static public JLabel text;
    static public JPanel panel;
    static public JTextField field;
    static public JButton submit;
    
    
    public MTGClient()
    {
      prepareGUI();
    }
    
    
    
    
    
    
 
    
    
   private JFrame mainFrame;
   private JLabel headerLabel;
   private JTextField statusLabel;
   private JPanel controlPanel;
   static public String query;
   static public Semaphore semaphore = new Semaphore(0);

  /* public static void main(String[] args){
      SwingListenerDemo  swingListenerDemo = new SwingListenerDemo();  
      swingListenerDemo.showActionListenerDemo();
   }*/

   private void prepareGUI(){
      mainFrame = new JFrame("Java SWING Examples");
      mainFrame.setSize(400,400);
      mainFrame.setLayout(new GridLayout(3, 4));

      headerLabel = new JLabel("Please enter card name: ",JLabel.CENTER );
      statusLabel = new JTextField(10);        

      statusLabel.setSize(350,100);
      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
	        System.exit(0);
         }        
      });    
      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());

      mainFrame.add(statusLabel);
      mainFrame.add(headerLabel);
      mainFrame.add(controlPanel);
      
      mainFrame.pack();
      
      mainFrame.setVisible(true);  
   }

   private void showActionListenerDemo(){
      headerLabel.setText("Listener in action: ActionListener");      

      JPanel panel = new JPanel();      
      panel.setBackground(Color.magenta);            
		
      JButton okButton = new JButton("Submit");

      okButton.addActionListener(new CustomActionListener());        
      panel.add(okButton);
      controlPanel.add(panel);
      mainFrame.setVisible(true); 
      
      
      System.out.println("Echo Client");
    try {
        System.out.println("Waiting for connection.....");
        InetAddress localAddress = InetAddress.getLocalHost();
        try {
            Socket clientSocket = new Socket(localAddress, 6000);
            BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            System.out.println("Connected to server");
            
            while (true) {
             
                
                
                try{
                   semaphore.acquire(); 
                }
                catch(InterruptedException p )
                {
                        
                }
                
                out.println(query);
                //scanner = new Scanner(query);
                if ("quit".equalsIgnoreCase(query)) {
                break;
                }
                System.out.println(query);
                
                String response = "";
                String response2 = "";
                
                for(int i = 0; i < /*Integer.parseInt(inputLine)*/ 1;i++)
                {
                    response += br.readLine(); 
                    response2 = br.readLine();
                }
                
                Image image = null;
                try {
                URL url = new URL("https://image.deckbrew.com/mtg/multiverseid/116724.jpg");
                image = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
                JLabel thing = new JLabel(new ImageIcon(image));
                controlPanel.add(thing);
               
                
                
                StringBuilder buff = new StringBuilder();
                buff.append("<html><table>");
                buff.append(String.format("<tr><td align='left'>%s</td></tr>", response));
                buff.append(String.format("<tr><td align='left'>%s</td></tr>", response2));
                buff.append("</table></html>");
                
                System.out.println(buff.toString());
                
                JLabel thing2 = new JLabel(buff.toString());
                
                controlPanel.add(thing2);
                mainFrame.pack();
                controlPanel.validate();
                controlPanel.repaint();
                
                
                controlPanel.remove(thing);
                controlPanel.remove(thing2);
                
                
                
                
                
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
          }
        } 
        catch (IOException ex) {
            System.out.println(ex.getMessage());
          }
      
      
      
      
      
   }
   
   class CustomActionListener implements ActionListener{
      public void actionPerformed(ActionEvent e) {
          query = statusLabel.getText();
          System.out.println(query);
          semaphore.release();
      }
   }
    
    
    
    

  
    public static void main(String[] args)  {
        MTGClient  swingListenerDemo = new MTGClient();  
        swingListenerDemo.showActionListenerDemo();
    }
}
