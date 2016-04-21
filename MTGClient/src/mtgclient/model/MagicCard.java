/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtgclient.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.json.JSONArray;
import org.json.JSONException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Fletcher Hart
 */
public class MagicCard {
    /**
     * @param args the command line arguments
     */
	
    private StringProperty name, manaCost, rulesText;
    private String[] types;
    private String colors;
    private boolean creature;
    private int strength, toughness;
    private String imageURL;
    
    public MagicCard()
    {
       this.name = new SimpleStringProperty(""); 
       this.manaCost = new SimpleStringProperty("");
       this.rulesText = new SimpleStringProperty("");
       this.setColors(null);
       this.setTypes(null);
       this.setCreature(false);
       this.setStrength(0);
       this.setToughness(0);
    }

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public String getManaCost() {
		return manaCost.get();
	}

	public void setManaCost(String manaCost) {
		this.manaCost.set(manaCost);
	}

	public String getRulesText() {
		return rulesText.get();
	}

	public void setRulesText(String rulesText) {
		this.rulesText.set(rulesText);
	}
	
	public String[] getTypes() {
		return types;
	}

	public void setTypes(JSONArray jsonArray) {
		
		if(jsonArray == null)
			return;
		
		List<String> list = new ArrayList<String>();
		for (int i=0; i<jsonArray.length(); i++) {
		    try {
				list.add( jsonArray.getString(i) );
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String[] typeArray = list.toArray(new String[list.size()]);
		this.types = typeArray;
	}

	public String getColors() {
		return colors;
	}

	public void setColors(String colors) {
		this.colors = colors;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getToughness() {
		return toughness;
	}

	public void setToughness(int toughness) {
		this.toughness = toughness;
	}

	public boolean isCreature() {
		for (String s : types)
			if (s.equals("creature")) 
				creature = true;
		
		return creature;
	}

	public void setCreature(boolean creature) {
		this.creature = creature;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(this.getName() + "\n");
		sb.append(this.getManaCost() + "\n");
		for(String s : types)
			sb.append(s + " ");
		sb.append("\n");
		sb.append(this.getRulesText() + "\n");
		if(this.isCreature())
			sb.append(this.getStrength() + "/" + this.getToughness());
		
		return sb.toString();
	}
        
}
