package l2info.jeuDeLaVie;

import java.util.ArrayList;
import java.util.Iterator;

public class Jeu {
	protected ArrayList<Cellule> listeCellule;

	public Jeu(){

	}

	public ArrayList<Cellule> calculer(){
		if(this.listeCellule.isEmpty()){
			return null;
		}

		ListeCellulePotentielle ListesCellulePot = new ListeCellulePotentielle();
		int nbSommet = this.listeCellule.size();

		Iterator<Cellule> itCels = this.listeCellule.iterator();
		for(int i = 0; i<8; i++){
			while(itCels.hasNext()){				
				Cellule cpTemp = itCels.next();
				switch(i){
				case 0:
					ListesCellulePot.ajouterElement(new CellulePotentielle(cpTemp.x-1, cpTemp.y-1));
					break;
				case 1:
					ListesCellulePot.ajouterElement(new CellulePotentielle(cpTemp.x, cpTemp.y-1));
					break;
				case 2:
					ListesCellulePot.ajouterElement(new CellulePotentielle(cpTemp.x+1, cpTemp.y-1));
					break;
				case 3:
					ListesCellulePot.ajouterElement(new CellulePotentielle(cpTemp.x-1, cpTemp.y));
					break;
				case 4:
					ListesCellulePot.ajouterElement(new CellulePotentielle(cpTemp.x+1, cpTemp.y));
					break;
				case 5:
					ListesCellulePot.ajouterElement(new CellulePotentielle(cpTemp.x-1, cpTemp.y+1));
					break;
				case 6:
					ListesCellulePot.ajouterElement(new CellulePotentielle(cpTemp.x, cpTemp.y+1));
					break;
				case 7:
					ListesCellulePot.ajouterElement(new CellulePotentielle(cpTemp.x+1, cpTemp.y+1));
					break;
				}
			}
		}
		return null;
		

	}


}
