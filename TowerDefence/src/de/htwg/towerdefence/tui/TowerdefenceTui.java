package de.htwg.towerdefence.tui;
import de.htwg.towerdefence.controller.IGameController;
import de.htwg.towerdefence.util.control.IObserver;
import de.htwg.towerdefence.util.enums.FieldType;

public class TowerdefenceTui implements IObserver{
	
	IGameController controller;
	StringBuilder output;
	
	public TowerdefenceTui (IGameController controller) {
		this.controller = controller;
		output = new StringBuilder();
	}
	
	public String TuiToString() {
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
		System.out.print(TuiToString());
	}
}
