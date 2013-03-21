package l2info.jeuDeLaVie;

import java.util.ArrayList;
import java.util.Iterator;

public class Jeu {
	protected ArrayList<Cellule> listeCellule;

	public Jeu(){
		listeCellule = new ArrayList<Cellule>();
	}
	
	public void ajouterCellule(Cellule c){
		listeCellule.add(c);
	}
	
	public void setListeCellule(ArrayList<Cellule> c){
		this.listeCellule = c;
	}

	public ArrayList<Cellule> getListeCellule(){
		return this.listeCellule;
	}
	
	public ArrayList<Cellule> calculer(){
		if(this.listeCellule.isEmpty()){
			return null;
		}

		ListeCellulePotentielle[] listesCellulePot = new ListeCellulePotentielle[9];
		for(int i = 0; i<9; i++){
			listesCellulePot[i] = new ListeCellulePotentielle();
		}

		
		for(int i = 0; i<9; i++){
			Iterator<Cellule> itCels = this.listeCellule.iterator();
			while(itCels.hasNext()){
				Cellule cpTemp = itCels.next();
				switch(i){
				case 0:
					listesCellulePot[0].ajouterElement(new CellulePotentielle(cpTemp.getX()-1, cpTemp.getY()-1));
					break;
				case 1:
					listesCellulePot[1].ajouterElement(new CellulePotentielle(cpTemp.x, cpTemp.y-1));
					break;
				case 2:
					listesCellulePot[2].ajouterElement(new CellulePotentielle(cpTemp.x+1, cpTemp.y-1));
					break;
				case 3:
					listesCellulePot[3].ajouterElement(new CellulePotentielle(cpTemp.x-1, cpTemp.y));
					break;
				case 4:
					listesCellulePot[4].ajouterElement(new CellulePotentielle(cpTemp.x, cpTemp.y));
					break;
				case 5:
					listesCellulePot[5].ajouterElement(new CellulePotentielle(cpTemp.x+1, cpTemp.y));
					break;
				case 6:
					listesCellulePot[6].ajouterElement(new CellulePotentielle(cpTemp.x-1, cpTemp.y+1));
					break;
				case 7:
					listesCellulePot[7].ajouterElement(new CellulePotentielle(cpTemp.x, cpTemp.y+1));
					break;
				case 8:
					listesCellulePot[8].ajouterElement(new CellulePotentielle(cpTemp.x+1, cpTemp.y+1));
					break;
				}
			}
		}
		
		ListeCellulePotentielle listeSomme = listesCellulePot[0];
		for(int i=1; i<9; i++){
			ListeCellulePotentielle first = listeSomme;
			ListeCellulePotentielle current = listesCellulePot[i];
			while(current != null){
				if(first.tete().getY() == current.tete().getY() && first.tete().getX() == current.tete().getX()){
					first.tete().ajouterVoisin();
					if(first.queue() != null)
						first = first.queue();
					current = current.queue();
				}
				
				else if(first.tete().getY() < current.tete().getY() || (first.tete().getY() == current.tete().getY() && first.tete().getX() < current.tete().getX())){
					if(first.queue() == null || (first.queue().tete().y > current.tete().getY() || (first.queue().tete().getY() == current.tete().getY() && first.queue().tete().getX() > current.tete().getX()))){
						first.insertElementAfter(current.tete());
						first = first.queue();	
						current = current.queue();
					}
					else{
						if(first.queue() != null)
							first = first.queue();
					}
				}
			}
		}
		
		ArrayList<Cellule> lCellule = new ArrayList<Cellule>();
		while(listeSomme != null){
			if(listeSomme.tete().getNbVoisin() == 4){
				lCellule.add(new Cellule(listeSomme.tete().getX(), listeSomme.tete().getY()));
			}
			listeSomme = listeSomme.queue();
		}
		this.listeCellule = lCellule;
		
		return lCellule;		
	}


}
