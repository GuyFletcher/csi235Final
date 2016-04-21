package mtgclient.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import mtgclient.model.MagicCard;

public class CardViewController {
	
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
	
	public void displayCard(MagicCard card)
	{
		cardNameLabel.setText(card.getName());
		manaCostLabel.setText(card.getManaCost());
		rulesTextLabel.setText(card.getRulesText());
		
		//TODO - filler info
		typeLabel.setText("Legendary Creature - Human Warrior");
		ptLabel.setText("4/4");
		
	}
}
