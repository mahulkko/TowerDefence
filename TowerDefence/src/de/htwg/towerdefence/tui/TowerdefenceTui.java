package de.htwg.towerdefence.tui;
import org.apache.log4j.Logger;

import de.htwg.towerdefence.controller.IGameController;
import de.htwg.towerdefence.util.control.IObserver;
import de.htwg.towerdefence.util.enums.FieldType;

public class TowerdefenceTui implements IObserver{
	
	IGameController controller;
	StringBuilder output;
	
	/** Logger for log4j connection */
    private static Logger log = Logger.getLogger("TowerDefence.Tui.TowerdefenceTui");
	
	public TowerdefenceTui (IGameController controller) {
		log.info("Started the tui");
		this.controller = controller;
		output = new StringBuilder();
	}
	
	public String TuiToString() {
		log.info("Build the TuiString");
		output.setLength(0);
		for (int i = 0; i < controller.getSizeXOfPlayingField(); ++i) {
			output.append("###");	
		}	
		output.append("##\n");
		
		for(int i = 0; i < controller.getSizeYOfPlayingField(); ++i) {
			output.append("#");
			for(int j = 0; j < controller.getSizeXOfPlayingField(); ++j) {
				if(controller.getTypeOfPlayingField(i, j) == FieldType.TOWER) {
					output.append("-+-");
				} else if(controller.getTypeOfPlayingField(i, j) == FieldType.MOB) {
					output.append("~%~");
				} else {
					output.append("   ");
				}
			}
			output.append("#\n");
		}
		for(int i = 0; i < controller.getSizeXOfPlayingField(); ++i) {
			output.append("###");	
		}
		output.append("##\n\n");
		
		return output.toString();
	}

	@Override
	public void update() {
		log.info("Update the tui");
		System.out.print(TuiToString());
	}
}
