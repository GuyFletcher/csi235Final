/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtg;

/**
 *
 * @author Fletcher Hart
 */
public class MTG {
    /**
     * @param args the command line arguments
     */
	
    private String name,manaCost, rulesText;
    private String[] types;
    private String[] colors;
    private boolean creature;
    private int strength, toughness;
    private String imageURL;
    
    public MTG()
    {
       this.setName(""); 
       this.setManaCost("");
       this.setRulesText("");
       this.setColors(null);
       this.setTypes(null);
       this.setCreature(false);
       this.setStrength(0);
       this.setToughness(0);
    }
    
    MTG(String name, String manaCost, String rules, String[] types, String flavor, String[] colors, int strength, int toughness)
    {
        this.setName(name);
    	this.setManaCost(manaCost);
    	this.setRulesText(rules);
        this.setTypes(types);
        this.setColors(colors);
        this.setCreature(false);
        
        for(String s : types)
        {
        	if(s.equals("creature"))
        		this.setCreature(true);
        		this.setStrength(strength);
        		this.setToughness(toughness);
        }
    }

    private static String removeLastChar(String str) {
        return str.substring(0,str.length()-1);
    }
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManaCost() {
		return manaCost;
	}

	public void setManaCost(String manaCost) {
		this.manaCost = manaCost;
	}

	public String getRulesText() {
		return rulesText;
	}

	public void setRulesText(String rulesText) {
		this.rulesText = rulesText;
	}
	
	public String[] getTypes() {
		return types;
	}

	public void setTypes(String[] types) {
		this.types = types;
	}

	public String[] getColors() {
		return colors;
	}

	public void setColors(String[] colors) {
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

        
}
