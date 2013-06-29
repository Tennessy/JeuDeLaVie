package l2info.jeuDeLaVie;

import java.util.ArrayList;
import java.util.Iterator;

public class Jeu {
	protected ArrayList<Cellule> listeCellule;
	private String name;
	private int tailleQueue;
	private int minX;
	private int minY;
	private int maxX;
	private int maxY;
	protected int nbGeneration;
	private int periode;
	private int typeMonde;
	
	public static final int MONDE_NORMAL = 10;
	public static final int MONDE_CIRCULAIRE = 11;
	public static final int MONDE_FRONTIERES = 12;


	/**
	 * Constructeur par defaut, créé un nouveau Jeu vide.
	 */
	public Jeu() {
		listeCellule = new ArrayList<Cellule>();
		this.nbGeneration = 0;
		this.setName("");
		this.setTailleQueue(0);
		this.setMinX(999999);
		this.setMinY(999999);
		this.setMaxX(-999999);
		this.setMaxY(-999999);
		this.setPeriode(0);
		this.typeMonde = Jeu.MONDE_NORMAL;
	}
	
	public Jeu(Jeu jeu){
		this.listeCellule = new ArrayList<Cellule>();
		for(Cellule c : jeu.listeCellule){
			this.listeCellule.add(c);
		}
		this.nbGeneration = jeu.getNbGeneration();
		this.setName(jeu.getName());
		this.setTailleQueue(jeu.getTailleQueue());
		this.setMinX(jeu.getMinX());
		this.setMinY(jeu.getMinY());
		this.setMaxX(jeu.getMaxX());
		this.setMaxY(jeu.getMaxY());
		this.setPeriode(jeu.getPeriode());
		this.typeMonde = jeu.getTypeMonde();
	}


	/**
	 * Créé un Jeu, à partir d'une ArrayList, avec un nom et les coordonnées
	 * minimal et maximal du Jeu.
	 * 
	 * @param name
	 *            Nom du Jeu.
	 * @param liste
	 *            ArrayList contenant la disposition des Cellules.
	 * @param minX
	 *            Abscisse à laquelle le terrain commence
	 * @param minY
	 *            Ordonnée à laquelle le terrain commence.
	 * @param maxX
	 *            Abscisse à laquelle le terrain se termine.
	 * @param maxY
	 *            Abscisse à laquelle le terrain se termine.
	 */
	public Jeu(String name, ArrayList<Cellule> liste, int minX, int minY,
			int maxX, int maxY) {
		this.setName(name);
		this.listeCellule = liste;
		this.setPeriode(0);
		this.setTailleQueue(0);
		this.setMinX(minX);
		this.setMinY(minY);
		this.setMaxX(maxX);
		this.setMaxY(maxY);
		this.nbGeneration = 0;
		this.typeMonde = Jeu.MONDE_NORMAL;
	}

	/**
	 * Ajoute une Cellule au Jeu.
	 * 
	 * @param c
	 *            Cellule à ajouter au Jeu.
	 */
	public void ajouterCellule(Cellule c) {
		listeCellule.add(c);
	}

	/**
	 * Définie la liste de Cellule du Jeu.
	 * 
	 * @param c
	 *            Une ArrayList contenant la disposition des Cellules.
	 */
	public void setListeCellule(ArrayList<Cellule> c) {
		this.listeCellule = c;
	}

	/**
	 * Retourne la liste contenant les Cellules du Jeu.
	 * 
	 * @return Une ArrayList de Cellule correspondant à la disposition actuelle
	 *         des Cellules.
	 */
	public ArrayList<Cellule> getListeCellule() {
		return this.listeCellule;
	}

	/**
	 * Calcule l'evolution sur un tour des Cellules du Jeu.
	 * 
	 * @param listCel
	 *            ArrayList contenant les Cellule du Jeu
	 * @param typeMonde
	 *            Type de monde dans lequel ce passe la simulation.
	 * @return Une ArrayList contenant la nouvelle disposition des Cellules.
	 */
	public void calculer() {

		if (this.listeCellule.isEmpty()) {
			this.listeCellule =  new ArrayList<Cellule>();
			return;
		}

		ListeCellulePotentielle[] listesCellulePot = new ListeCellulePotentielle[9];
		for (int i = 0; i < 9; i++) {
			listesCellulePot[i] = new ListeCellulePotentielle();
		}

		//Creation des listes contenant les coordonn�es des "cases" adjacantes � une cellule vivante.
		Iterator<Cellule> itCels = this.listeCellule.iterator();
		while (itCels.hasNext()) {
			Cellule cpTemp = itCels.next();
			setMinX(Math.min(getMinX(), cpTemp.getX()));
			setMinY(Math.min(getMinY(), cpTemp.getY()));
			setMaxX(Math.max(getMaxX(), cpTemp.getX()));
			setMaxY(Math.max(getMaxY(), cpTemp.getY()));

			listesCellulePot[0].ajouterElement(new CellulePotentielle(cpTemp
					.getX() - 1, cpTemp.getY() - 1, false, 1));
			listesCellulePot[1].ajouterElement(new CellulePotentielle(cpTemp.x,
					cpTemp.y - 1, false, 1));
			listesCellulePot[2].ajouterElement(new CellulePotentielle(
					cpTemp.x + 1, cpTemp.y - 1, false, 1));
			listesCellulePot[3].ajouterElement(new CellulePotentielle(
					cpTemp.x - 1, cpTemp.y, false, 1));
			listesCellulePot[4].ajouterElement(new CellulePotentielle(cpTemp.x,
					cpTemp.y, true, 1));
			listesCellulePot[5].ajouterElement(new CellulePotentielle(
					cpTemp.x + 1, cpTemp.y, false, 1));
			listesCellulePot[6].ajouterElement(new CellulePotentielle(
					cpTemp.x - 1, cpTemp.y + 1, false, 1));
			listesCellulePot[7].ajouterElement(new CellulePotentielle(cpTemp.x,
					cpTemp.y + 1, false, 1));
			listesCellulePot[8].ajouterElement(new CellulePotentielle(
					cpTemp.x + 1, cpTemp.y + 1, false, 1));

		}

		//Addition de toute les listes afin d'en obtenir qu'une contenant toutes les "cases" adjacantes.
		ListeCellulePotentielle listeSomme = listesCellulePot[0];
		for (int i = 1; i < 9; i++) {
			ListeCellulePotentielle first = listeSomme;
			first.additionCelPot(listesCellulePot[i]);
		}

		
		if (typeMonde == Jeu.MONDE_CIRCULAIRE) {
			ListeCellulePotentielle liste = listeSomme;
			ListeCellulePotentielle[] exterieur = new ListeCellulePotentielle[4];
			for (int i = 0; i < exterieur.length; i++) {
				exterieur[i] = new ListeCellulePotentielle();
			}
			
			//Dans le cas du monde circulaire, on recopie toute les cellules d'un bord sur le bord oppos�.
			while (liste != null) {
				if (liste.tete().getX() == getMinX() - 1
						&& liste.tete().getY() >= getMinY()
						&& liste.tete().getY() <= getMaxY()) {
					exterieur[0].ajouterElement(new CellulePotentielle(getMaxX(),
							liste.tete().getY(), false, liste.tete()
									.getNbVoisin()));
				} else if (liste.tete().getX() == getMaxX() + 1
						&& liste.tete().getY() >= getMinY()
						&& liste.tete().getY() <= getMaxY()) {
					exterieur[1].ajouterElement(new CellulePotentielle(getMinX(),
							liste.tete().getY(), false, liste.tete()
									.getNbVoisin()));
				} else if (liste.tete().getY() == getMinY() - 1
						&& liste.tete().getX() >= getMinX()
						&& liste.tete().getX() <= getMaxX()) {
					exterieur[2].ajouterElement(new CellulePotentielle(liste
							.tete().getX(), getMaxY(), false, liste.tete()
							.getNbVoisin()));
				} else if (liste.tete().getY() == getMaxY() + 1
						&& liste.tete().getX() >= getMinX()
						&& liste.tete().getX() <= getMaxX()) {
					exterieur[3].ajouterElement(new CellulePotentielle(liste
							.tete().getX(), getMinY(), false, liste.tete()
							.getNbVoisin()));
				}
				liste = liste.queue();
			}

			//On additionne les cellules calculer precedemment � celle que l'on possede deja.
			for (int i = 0; i < exterieur.length; i++) {
				liste = listeSomme;
				if (exterieur[i].tete() != null)
					listeSomme.additionCelPot(exterieur[i]);
			}
		}

		
		//On cree une liste final contenant les cellules possedant suffisement de voisin pour rester/devenir vivante.
		ArrayList<Cellule> lCellule = new ArrayList<Cellule>();
		while (listeSomme != null) {
			if (!listeSomme.tete().exists()
					&& listeSomme.tete().getNbVoisin() == 3) {
				//Dans le cas des mondes frontieres et circulaire, on verifie que les coordonnees des cellules ne depassent pas la taille du jeu.
				if (typeMonde == Jeu.MONDE_FRONTIERES
						|| typeMonde == Jeu.MONDE_CIRCULAIRE) {
					if (listeSomme.tete().getX() > getMinX()
							&& listeSomme.tete().getY() >= getMinY()
							&& listeSomme.tete().getX() <= getMaxX()
							&& listeSomme.tete().getY() <= getMaxY()) {
						lCellule.add(new Cellule(listeSomme.tete().getX(),
								listeSomme.tete().getY()));
					}
				} else {
					lCellule.add(new Cellule(listeSomme.tete().getX(),
							listeSomme.tete().getY()));
				}

			} else if (listeSomme.tete().exists()
					&& (listeSomme.tete().getNbVoisin() == 3 || listeSomme
							.tete().getNbVoisin() == 4)) {
				lCellule.add(new Cellule(listeSomme.tete().getX(), listeSomme
						.tete().getY()));
			}
			listeSomme = listeSomme.queue();
		}
		this.listeCellule = lCellule;
		this.nbGeneration++;
	}
	
	public static int TypeMonde(String s) {
		if (s.equals("normal"))
			return Jeu.MONDE_NORMAL;
		if (s.equals("circulaires"))
			return Jeu.MONDE_CIRCULAIRE;
		if (s.equals("frontieres"))
			return Jeu.MONDE_FRONTIERES;
		return 0;
	}
	
	public static String TypeMonde(int type) {
		switch(type){
		case Jeu.MONDE_NORMAL:
			return "normal";
		case Jeu.MONDE_CIRCULAIRE:
			return "circulaire";
		case Jeu.MONDE_FRONTIERES : 
			return "frontiere";
		default : 
			return "inconnu";
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaxX() {
		return maxX;
	}

	public int setMaxX(int maxX) {
		this.maxX = maxX;
		return maxX;
	}

	public int getMinX() {
		return minX;
	}

	public void setMinX(int minX) {
		this.minX = minX;
	}

	public int getMinY() {
		return minY;
	}

	public void setMinY(int minY) {
		this.minY = minY;
	}

	public int getMaxY() {
		return maxY;
	}

	public int setMaxY(int maxY) {
		this.maxY = maxY;
		return maxY;
	}

	public int getTailleQueue() {
		return tailleQueue;
	}

	public void setTailleQueue(int tailleQueue) {
		this.tailleQueue = tailleQueue;
	}

	public int getPeriode() {
		return periode;
	}

	public void setPeriode(int periode) {
		this.periode = periode;
	}
	
	public int getTypeMonde() {
		return this.typeMonde;
	}
	
	public void setTypeMonde(int typeMonde) {
		this.typeMonde = typeMonde;
	}
	
	public int getNbGeneration(){
		return this.nbGeneration;
	}
}