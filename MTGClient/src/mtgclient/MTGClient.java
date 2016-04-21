/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtgclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import javafx.fxml.FXMLLoader;
import mtgclient.model.MagicCard;
import mtgclient.view.CardViewController;

/**
 *
 * @author Fletcher Hart
 */
public class MTGClient {

	
	public static MagicCard run(String searchKey)
	{
		try {
			
			System.out.println("Waiting for connection.....");
			InetAddress localAddress = InetAddress.getLocalHost();
			
			try{
				Socket clientSocket = new Socket(localAddress, 6000);
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				System.out.println("Connected to server");
				out.println(searchKey);
				
				try {
					ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
					MagicCard ret;
					ret = (MagicCard) in.readObject();
					return ret;
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
				
			}catch(IOException e){
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
    public static void main(String[] args)  {
        
    	System.out.println("Echo Client");
    try {
        System.out.println("Waiting for connection.....");
        InetAddress localAddress = InetAddress.getLocalHost();
        
        try {
            Socket clientSocket = new Socket(localAddress, 6000);
            BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            System.out.println("Connected to server");
            Scanner scanner = new Scanner(System.in);
            
            while (true) 
            {
                System.out.print("Enter card name: ");
                String inputLine = scanner.nextLine();
                if ("quit".equalsIgnoreCase(inputLine)) {
                break;
                }
                out.println(inputLine);
                                
                for(int i = 0; i < /*Integer.parseInt(inputLine)*/ 1;i++)
                {
                    String response = br.readLine();
                                
                    System.out.println("Response: " +response);
                }     
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
          }
        } 
        catch (IOException ex) {
            System.out.println(ex.getMessage());
          }
    }
}
