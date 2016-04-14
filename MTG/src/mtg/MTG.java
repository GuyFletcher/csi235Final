/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import org.json.JSONArray; 
import org.json.JSONException; 
import org.json.JSONObject;
import java.nio.charset.Charset;

/**
 *
 * @author Fletcher Hart
 */
public class MTG {
    /**
     * @param args the command line arguments
     */
    private String name, manaCost, flavor;
    private String[] colors;
    private int strength, toughness;
    
    MTG()
    {
        
    }
    
    MTG(String manaCost, String flavor, /*String[] colors,*/int strength, int toughness)
    {
        this.manaCost = manaCost;
        this.flavor=flavor;
        //this.colors=colors;
        this.strength=strength;
        this.toughness=toughness;
    }
    
    public String getMana()
    {
        return manaCost;
    }
    
    public String getFlavor()
    {
        return flavor;
    }
    
    public int getStrength()
    {
        return strength;
    }
    
    public int getToughness()
    {
        return toughness;
    }
    
    private static String removeLastChar(String str) {
        return str.substring(0,str.length()-1);
    }
    
    public static String fetchData(String card) throws IOException
    {        
        
        String[] cardData = card.split(" ");
        
        String cardURL = "";
        
        for(int i = 0; i < cardData.length; i++)
        {
            //add   -   to end of all but last cardData strings for url
            cardURL += cardData[i] + "-";
            
        }
       
       System.out.println(cardURL);
       cardURL =  removeLastChar(cardURL);
        
        String url_ = "https://api.deckbrew.com/mtg/cards/" + cardURL;//http://www.mtgjson.com/json/AllCards.json";
        URL url = new URL(url_);
        URLConnection conn = url.openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/5.0");
        InputStream is = conn.getInputStream();
                    
        BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
        sb.append((char) cp);
        }               
        
        
        return sb.toString();
    }
    
     public static MTG parseData(String MTGJsonStr, String cardName) throws JSONException
    {
        JSONObject jCard = new JSONObject(MTGJsonStr);
        
        //JSONObject cardObject = jCard.getJSONObject(cardName);
        
        String flavor, manaCost;
        int strength, toughness;
        //String color;
                
        
        flavor = jCard.getString("text");
        manaCost = jCard.getString("cost");
        strength = jCard.getInt("power");
        toughness = jCard.getInt("toughness");
                   
       /* flavor = cardObject.getString("text");
        manaCost = cardObject.getString("manaCost");
        strength = cardObject.getInt("power");
        toughness = cardObject.getInt("toughness");*/
        
            
        MTG magic = new MTG(manaCost, flavor, strength, toughness);           
        
        
        
        return magic;
    }
    
    public static void main(String[] args) {
        String response;
        Socket sSocket = null;
        int year, numMovies;
        
            
        try {
            ServerSocket serverSocket = new ServerSocket(6000);
            System.out.println("Waiting for connection.....");
            sSocket = serverSocket.accept();
            System.out.println("Connected to client");
        } 
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        try {        
            BufferedReader br = new BufferedReader(new InputStreamReader(sSocket.getInputStream()));
            //InputStream -- InputStreamReader -- BufferedReader
            PrintWriter out = new PrintWriter(sSocket.getOutputStream(), true);
            //OutputStream -- PrintWriter
            //Prints formatted representations of objects to a text-output stream.
            //true to enable automatic flushing
            
            String inputLine;
            
            while ((inputLine = br.readLine()) != null){
                System.out.println("Client request: " + inputLine);   
               
                
               MTG magic = parseData(fetchData(inputLine), inputLine);
               
                
               
                System.out.println(magic.getMana() + magic.getFlavor() + magic.getToughness() + magic.getStrength());
               
            }
            
        }
        catch(IOException ex)  {
            System.out.println(ex.getMessage());
        }
        catch(JSONException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
}
