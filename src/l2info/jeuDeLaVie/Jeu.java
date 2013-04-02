package l2info.jeuDeLaVie;


import java.util.ArrayList;
import java.util.Iterator;

public class Jeu {
	protected ArrayList<Cellule> listeCellule;
	protected int type;
	protected String name;
	protected int tailleQueue;
	protected int minX, minY;
	protected int maxX, maxY;
	
	public static final int MORT = 1;
	public static final int STABLE = 2;
	public static final int VAISSEAU= 4;
	public static final int OSCILLATEUR = 3;
	public static final int INDETERMINE = 5;
	public static final int MONDE_NORMAL = 10;
	public static final int MONDE_CIRCULAIRE = 11;
	public static final int MONDE_FRONTIERES = 12;


	public Jeu(){
		listeCellule = new ArrayList<Cellule>();
		type = Jeu.INDETERMINE;
		this.name = "";
		this.tailleQueue = 0;
		this.minX = 999999;
		this.minY = 999999;
		this.maxX = -999999;
		this.maxY = -999999;
	}
	
	public Jeu(String name, ArrayList<Cellule> liste){
		this.name = name;
		this.listeCellule = liste;
		this.type = Jeu.INDETERMINE;
		this.tailleQueue = 0;
		this.minX = 999999;
		this.minY = 999999;
		this.maxX = -999999;
		this.maxY = -999999;
	}
	
	public Jeu(String name, ArrayList<Cellule> liste, int minX, int minY, int maxX, int maxY){
		this.name = name;
		this.listeCellule = liste;
		this.type = Jeu.INDETERMINE;
		this.tailleQueue = 0;
		this.minX = minX;
		this.minY = minY;
		this.maxX = maxX;
		this.maxY = maxY;
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

	public int evaluer(int nbTours, int typeMonde){
		ArrayList<Cellule> listeCelTemoin = new ArrayList<Cellule>();
		for(Cellule e : this.listeCellule){
			listeCelTemoin.add(e);
		}

		for(int i=0; i<nbTours; i++){
			ArrayList<Cellule> temp = this.calculer(this.listeCellule, typeMonde);

			if(this.listeCellule.equals(temp)){
				this.type = Jeu.STABLE;
				return Jeu.STABLE;
			}

			this.listeCellule = temp;
			listeCelTemoin = this.calculer(listeCelTemoin, typeMonde);
			listeCelTemoin = this.calculer(listeCelTemoin, typeMonde);

			if(this.listeCellule.isEmpty()){
				this.type = Jeu.MORT;
				return MORT;
			}
			else if(this.listeCellule.equals(listeCelTemoin)){
				this.type = Jeu.OSCILLATEUR;
				return Jeu.OSCILLATEUR;
			}
			else if(isTranslation(this.listeCellule, listeCelTemoin)){
				this.type = Jeu.VAISSEAU;
				return VAISSEAU;
			}
		}
		return INDETERMINE;
	}

	public boolean isTranslation(ArrayList<Cellule> l1, ArrayList<Cellule> l2) {
		Iterator<Cellule> it1 = l1.iterator();
		Iterator<Cellule> it2 = l2.iterator();
		boolean equal = true;
		if (l1.size() == l2.size()) {
			int diffX = it1.next().getX() - it2.next().getX();
			int diffY = it1.next().getY() - it2.next().getY();

			while (equal && it1.hasNext()) {
				Cellule c1 = it1.next();
				Cellule c2 = it2.next();
				if (c2.getX() + diffX != c1.getX()
						|| c2.getY() + diffY != c1.getY()) {
					equal = false;
				}
			}
			return equal;
		} else {
			return false;
		}

	}

	public ArrayList<Cellule> calculer(ArrayList<Cellule> listCel, int typeMonde){
		int minX = (this.minX<999999?this.minX:999999);
		int minY = (this.minY<999999?this.minY:999999);
		int maxX = (this.maxX>-999999?this.maxX:-999999);
		int maxY = (this.maxY>-999999?this.maxY:-999999);;
		
		if(listCel.isEmpty()){
			return new ArrayList<Cellule>();
		}

		ListeCellulePotentielle[] listesCellulePot = new ListeCellulePotentielle[9];
		for(int i = 0; i<9; i++){
			listesCellulePot[i] = new ListeCellulePotentielle();
		}



		Iterator<Cellule> itCels = listCel.iterator();
		while(itCels.hasNext()){
			Cellule cpTemp = itCels.next();
			minX = Math.min(minX, cpTemp.getX());
			minY = Math.min(minY, cpTemp.getY());
			maxX = Math.max(maxX, cpTemp.getX());
			maxY = Math.max(maxY, cpTemp.getY());
			
			
			listesCellulePot[0].ajouterElement(new CellulePotentielle(cpTemp.getX()-1, cpTemp.getY()-1, false, 1));
			listesCellulePot[1].ajouterElement(new CellulePotentielle(cpTemp.x, cpTemp.y-1, false, 1));
			listesCellulePot[2].ajouterElement(new CellulePotentielle(cpTemp.x+1, cpTemp.y-1, false, 1));
			listesCellulePot[3].ajouterElement(new CellulePotentielle(cpTemp.x-1, cpTemp.y, false, 1));
			listesCellulePot[4].ajouterElement(new CellulePotentielle(cpTemp.x, cpTemp.y, true, 1));
			listesCellulePot[5].ajouterElement(new CellulePotentielle(cpTemp.x+1, cpTemp.y, false, 1));
			listesCellulePot[6].ajouterElement(new CellulePotentielle(cpTemp.x-1, cpTemp.y+1, false, 1));
			listesCellulePot[7].ajouterElement(new CellulePotentielle(cpTemp.x, cpTemp.y+1, false, 1));
			listesCellulePot[8].ajouterElement(new CellulePotentielle(cpTemp.x+1, cpTemp.y+1, false, 1));

		}
	

	ListeCellulePotentielle listeSomme = listesCellulePot[0];
	for(int i=1; i<9; i++){
		ListeCellulePotentielle first = listeSomme;
//		while(current != null){
//			if(first.tete().getY() == current.tete().getY() && first.tete().getX() == current.tete().getX()){
//				first.tete().ajouterVoisin();
//				if(current.tete().exists())
//					first.tete().setExist(true);
//				current = current.queue();
//			}
//
//			else if(first.tete().getY() < current.tete().getY() || (first.tete().getY() == current.tete().getY() && first.tete().getX() < current.tete().getX())){
//				if(first.queue() == null || (first.queue().tete().y > current.tete().getY() || (first.queue().tete().getY() == current.tete().getY() && first.queue().tete().getX() > current.tete().getX()))){
//					first.insertElementAfter(current.tete());
//					first = first.queue();	
//					current = current.queue();
//				}
//				else{
//					if(first.queue() != null)
//						first = first.queue();
//				}
//			}
//		}
		this.additionCelPot(first, listesCellulePot[i]);
	}
	
	if(typeMonde == Jeu.MONDE_CIRCULAIRE){
		ListeCellulePotentielle liste = listeSomme;
		ListeCellulePotentielle[] exterieur = new ListeCellulePotentielle[4];
		for(int i=0; i<exterieur.length; i++){
			exterieur[i] = new ListeCellulePotentielle();
		}
		while(liste != null){
			if(liste.tete().getX() == minX-1 && liste.tete().getY()>=minY && liste.tete().getY() <= maxY){
				exterieur[0].ajouterElement(new CellulePotentielle(maxX, liste.tete().getY(), false, liste.tete().getNbVoisin()));
			}
			else if(liste.tete().getX() == maxX+1 && liste.tete().getY()>=minY && liste.tete().getY() <= maxY){
				exterieur[1].ajouterElement(new CellulePotentielle(minX, liste.tete().getY(), false, liste.tete().getNbVoisin()));
			}
			else if(liste.tete().getY() == minY-1 && liste.tete().getX()>= minX && liste.tete().getX() <= maxX){
				exterieur[2].ajouterElement(new CellulePotentielle(liste.tete().getX(), maxY, false, liste.tete().getNbVoisin()));
			}
			else if(liste.tete().getY() == maxY+1 && liste.tete().getX()>= minX && liste.tete().getX() <= maxX){
				exterieur[3].ajouterElement(new CellulePotentielle(liste.tete().getX(), minY, false, liste.tete().getNbVoisin()));
			}
			liste = liste.queue();
		}
		
		for(int i=0; i<exterieur.length; i++){
			liste = listeSomme;
			if(exterieur[i].tete() != null)
				listeSomme = this.additionCelPot(listeSomme, exterieur[i]);			
		}
	}

	ArrayList<Cellule> lCellule = new ArrayList<Cellule>();
	while(listeSomme != null){
		if(!listeSomme.tete().exists() && listeSomme.tete().getNbVoisin() == 3){
			if(typeMonde == Jeu.MONDE_FRONTIERES || typeMonde == Jeu.MONDE_CIRCULAIRE){
				if(listeSomme.tete().getX() > minX && listeSomme.tete().getY() >= minY && listeSomme.tete().getX() <= maxX && listeSomme.tete().getY() <= maxY){
					lCellule.add(new Cellule(listeSomme.tete().getX(), listeSomme.tete().getY()));
				}
			}
			else{
				lCellule.add(new Cellule(listeSomme.tete().getX(), listeSomme.tete().getY()));
			}
			
		}
		else if(listeSomme.tete().exists() && (listeSomme.tete().getNbVoisin() == 3 || listeSomme.tete().getNbVoisin() == 4)){
			lCellule.add(new Cellule(listeSomme.tete().getX(), listeSomme.tete().getY()));
		}
		listeSomme = listeSomme.queue();
	}

	return lCellule;		
}
	
	private ListeCellulePotentielle additionCelPot(ListeCellulePotentielle lc1, ListeCellulePotentielle lc2){
		ListeCellulePotentielle temp = lc1;
		while(lc2 != null){
			if(lc1.tete().getY() == lc2.tete().getY() && lc1.tete().getX() == lc2.tete().getX()){
				lc1.tete().ajouterVoisin();
				if(lc2.tete().exists())
					lc1.tete().setExist(true);
				lc2 = lc2.queue();
			}

			else if(lc1.tete().getY() < lc2.tete().getY() || (lc1.tete().getY() == lc2.tete().getY() && lc1.tete().getX() < lc2.tete().getX())){
				if(lc1.queue() == null || (lc1.queue().tete().y > lc2.tete().getY() || (lc1.queue().tete().getY() == lc2.tete().getY() && lc1.queue().tete().getX() > lc2.tete().getX()))){
					lc1.insertElementAfter(lc2.tete());
					lc1 = lc1.queue();	
					lc2 = lc2.queue();
				}
				else{
					if(lc1.queue() != null)
						lc1 = lc1.queue();
				}
			}
			else{
				lc1 = lc1.AjouterTete(lc2.tete());
				temp = lc1;
				lc2 = lc2.queue();
			}
		}
		return temp;
	}

	public static String toFullHTML(ArrayList<Jeu> listeJeu){
		String html = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\"><html><head><title>Resultats Jeu de la vie</title></head><body> <table border=\"1\"> <tr><th>Nom</th><th>type</th><th>Taille queue</th></tr><tr>";
		for(Jeu jeu : listeJeu){
			html += "<td>" + jeu.name + "</td><td>";
			
			switch(jeu.type){
			case Jeu.INDETERMINE:
				html += "Indetermine";
				break;
			case Jeu.MORT:
				html += "Mort";
				break;
			case Jeu.OSCILLATEUR:
				html += "Oscillateur";
				break;
			case Jeu.STABLE:
				html += "Stable";
				break;
			case Jeu.VAISSEAU:
				html += "Vaisseau";
			}
			html += "</td><td>" + (jeu.tailleQueue>0?jeu.tailleQueue:"Inconnue") + "</td></tr>";
		}
		
		html += "</table> </body></html";
		return html;
	}

}
