package mtgserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

import mtgclient.model.MagicCard;

public class MTGServer 
{
	
	public static void main(String[] args) {
        Socket sSocket = null;

        
            
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
            ObjectOutputStream out = new ObjectOutputStream(sSocket.getOutputStream());
            
            String inputLine;
            
            while ((inputLine = br.readLine()) != null){
               System.out.println("Client request: " + inputLine);   
               MagicCard magic = parseData(fetchData(inputLine), inputLine);
               System.out.println(magic.toString());
               out.writeObject(magic);
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
	
	public static String fetchData(String card) throws IOException
    {        
        
        String[] cardData = card.split(" ");
        
        String cardID = "";
        
        for(int i = 0; i < cardData.length; i++)
        {
            //add   -   to end of all but last cardData strings for url
            cardID += cardData[i] + "-";
            
        }
        cardID = fixString(cardID);
        System.out.println(cardID);
        //cardURL =  removeLastChar(cardURL);  this is the old way we did it, trying out a new way
        
        String CardURl = "https://api.deckbrew.com/mtg/cards/" + cardID;
        URL url = new URL(CardURl);
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
	
	private static String fixString(String str) {
		str = str.toLowerCase();
		return str.substring(0,str.length()-1);
	}
	
	public static MagicCard parseData(String MTGJsonStr, String cardName) throws JSONException
    {
        JSONObject jCard = new JSONObject(MTGJsonStr);
        MagicCard card = new MagicCard();
        
        card.setName(jCard.getString("name"));
        card.setManaCost(jCard.getString("cost"));
        card.setRulesText(jCard.getString("text"));
        card.setTypes(jCard.getJSONArray("types"));
        
        if(card.isCreature())
        {
        	card.setStrength(Integer.parseInt(jCard.getString("power")));
        	card.setToughness(Integer.parseInt(jCard.getString("toughness")));
        }
        
        return card;
    }    
}
