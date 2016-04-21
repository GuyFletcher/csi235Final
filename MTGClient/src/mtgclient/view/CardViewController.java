package mtgclient.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import mtgclient.MainApp;
import mtgclient.model.MagicCard;

public class CardViewController {
	
	private MainApp mainApp;
	
	//Card Information
	@FXML
	private Label cardNameLabel;
	@FXML
	private Label manaCostLabel;
	@FXML
	private Label typeLabel;
	@FXML
	private Label rulesTextLabel;
	@FXML
	private Label ptLabel;
	
	//Card search area
	@FXML
	private TextField searchBox;
	
	@FXML
	public void search()
	{
		clearInfo();
		displayCard(MainApp.search(searchBox.getText()));
	}
	
	public void clearInfo()
	{
		cardNameLabel.setText("");
		manaCostLabel.setText("");
		rulesTextLabel.setText("");
		
		//TODO - filler info
		typeLabel.setText("");
		ptLabel.setText("");
		
	}
	
	public void displayCard(MagicCard card)
	{
		cardNameLabel.setText(card.getName());
		manaCostLabel.setText(card.getManaCost());
		rulesTextLabel.setText(card.getRulesText());
		
		//TODO - filler info
		typeLabel.setText("Legendary Creature - Human Warrior");
		ptLabel.setText("4/4");
		
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
}
