package l2info.jeuDeLaVie;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Classe s'occupant de la gestion du jeu ( Calcule de nouvel generation,
 * disposition, etc... ).
 * 
 * @author Ten
 * 
 */
public class Jeu {
	/**
	 * Contient la liste des cellules vivante du jeu.
	 */
	protected ArrayList<Cellule> listeCellule;
	/**
	 * Nom du jeu ( Initialement egal au nom du fichier ).
	 */
	private String name;
	/**
	 * Abscisse de la cellule la plus à gauche du jeu.
	 */
	private int minX;
	/**
	 * Ordonnee de la cellule la plus en haut du jeu.
	 */
	private int minY;
	/**
	 * Abscisse de la cellule la plus à droite du jeu.
	 */
	private int maxX;
	/**
	 * Ordonnee de la cellule la plus en bas du jeu.
	 */
	private int maxY;
	/**
	 * Numero de generation actuel du jeu.
	 */
	protected int nbGeneration;
	/**
	 * Type de monde du jeu.
	 */
	private int typeMonde;

	public static final int MONDE_NORMAL = 10;
	public static final int MONDE_CIRCULAIRE = 11;
	public static final int MONDE_FRONTIERES = 12;

	/**
	 * Constructeur par defaut, cree un nouveau Jeu vide.
	 */
	public Jeu() {
		listeCellule = new ArrayList<Cellule>();
		this.nbGeneration = 0;
		this.setName("");
		this.setMinX(999999);
		this.setMinY(999999);
		this.setMaxX(-999999);
		this.setMaxY(-999999);
		this.typeMonde = Jeu.MONDE_NORMAL;
	}

	/**
	 * Constructeur de copie. Cree un nouveau Jeu à partir d'un jeu existant.
	 * 
	 * @param jeu
	 *            à copier.
	 */
	public Jeu(Jeu jeu) {
		this.listeCellule = new ArrayList<Cellule>();
		for (Cellule c : jeu.listeCellule) {
			this.listeCellule.add(c);
		}
		this.nbGeneration = jeu.getNbGeneration();
		this.setName(jeu.getName());
		this.setMinX(jeu.getMinX());
		this.setMinY(jeu.getMinY());
		this.setMaxX(jeu.getMaxX());
		this.setMaxY(jeu.getMaxY());
		this.typeMonde = jeu.getTypeMonde();
	}

	/**
	 * Cree un Jeu, a  partir d'une ArrayList, avec un nom et les coordonnees
	 * minimal et maximal du Jeu.
	 * 
	 * @param name
	 *            Nom du Jeu.
	 * @param liste
	 *            ArrayList contenant la disposition des Cellules.
	 * @param minX
	 *            Abscisse a  laquelle le terrain commence
	 * @param minY
	 *            Ordonnee a  laquelle le terrain commence.
	 * @param maxX
	 *            Abscisse a laquelle le terrain se termine.
	 * @param maxY
	 *            ordonnee a  laquelle le terrain se termine.
	 */
	public Jeu(String name, ArrayList<Cellule> liste, int minX, int minY,
			int maxX, int maxY) {
		this.setName(name);
		this.listeCellule = liste;
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
	 *            Cellule a ajouter au Jeu.
	 */
	public void ajouterCellule(Cellule c) {
		listeCellule.add(c);
	}

	/**
	 * Definie la liste de Cellule du Jeu.
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
	 * @return Une ArrayList de Cellule correspondant Ã  la disposition actuelle
	 *         des Cellules.
	 */
	public ArrayList<Cellule> getListeCellule() {
		return this.listeCellule;
	}

	/**
	 * Calcule l'evolution sur un tour des Cellules du Jeu et modifie
	 * listeCellule en consequence.
	 */
	public void calculer() {

		if (this.listeCellule.isEmpty()) {
			this.listeCellule = new ArrayList<Cellule>();
			this.nbGeneration++;

			return;
		}

		ListeCellulePotentielle[] listesCellulePot = new ListeCellulePotentielle[9];
		for (int i = 0; i < 9; i++) {
			listesCellulePot[i] = new ListeCellulePotentielle();
		}

		// Creation des listes contenant les coordonnées des "cases" adjacantes
		// à une cellule vivante.
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

		// Addition de toute les listes afin d'en obtenir qu'une contenant
		// toutes les "cases" adjacantes.
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

			// Dans le cas du monde circulaire, on recopie toute les cellules
			// d'un bord sur le bord opposé.
			while (liste != null) {
				if (liste.tete().getX() == getMinX() - 1
						&& liste.tete().getY() >= getMinY()
						&& liste.tete().getY() <= getMaxY()) {
					exterieur[0].ajouterElement(new CellulePotentielle(
							getMaxX(), liste.tete().getY(), false, liste.tete()
									.getNbVoisin()));
				} else if (liste.tete().getX() == getMaxX() + 1
						&& liste.tete().getY() >= getMinY()
						&& liste.tete().getY() <= getMaxY()) {
					exterieur[1].ajouterElement(new CellulePotentielle(
							getMinX(), liste.tete().getY(), false, liste.tete()
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

			// On additionne les cellules calculer precedemment à celle que l'on
			// possede deja.
			for (int i = 0; i < exterieur.length; i++) {
				liste = listeSomme;
				if (exterieur[i].tete() != null)
					listeSomme.additionCelPot(exterieur[i]);
			}
		}

		// On cree une liste final contenant les cellules possedant suffisement
		// de voisin pour rester/devenir vivante.
		ArrayList<Cellule> lCellule = new ArrayList<Cellule>();
		while (listeSomme != null) {
			if (!listeSomme.tete().exists()
					&& listeSomme.tete().getNbVoisin() == 3) {
				// Dans le cas des mondes frontieres et circulaire, on verifie
				// que les coordonnees des cellules ne depassent pas la taille
				// du jeu.
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

	/**
	 * Convertie le nom du monde en Integer.
	 * 
	 * @param s
	 *            String contenant le type de monde.
	 * @return Un Integer correpondant au type de monde.
	 */
	public static int TypeMonde(String s) {
		if (s.equals("normal"))
			return Jeu.MONDE_NORMAL;
		if (s.equals("circulaires"))
			return Jeu.MONDE_CIRCULAIRE;
		if (s.equals("frontieres"))
			return Jeu.MONDE_FRONTIERES;
		return 0;
	}

	/**
	 * Convertie le type de monde en String.
	 * 
	 * @param type
	 *            le type de mon a convertir.
	 * @return Un string correspondant au nom du type de monde.
	 */
	public static String TypeMonde(int type) {
		switch (type) {
		case Jeu.MONDE_NORMAL:
			return "normal";
		case Jeu.MONDE_CIRCULAIRE:
			return "circulaire";
		case Jeu.MONDE_FRONTIERES:
			return "frontiere";
		default:
			return "inconnu";
		}
	}

	/**
	 * @return Renvois le nom du jeu.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Definie le nom du jeu.
	 * 
	 * @param name
	 *            Le nom du jeu.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return Renvois l'abscisse maximal du jeu.
	 */
	public int getMaxX() {
		return maxX;
	}

	/**
	 * Definie l'abscisse maximal du jeu.
	 * 
	 * @param maxX
	 *            L'abscisse maximal.
	 */
	public void setMaxX(int maxX) {
		this.maxX = maxX;
	}

	/**
	 * 
	 * @return L'abscisse minimal en jeu.
	 */
	public int getMinX() {
		return minX;
	}

	/**
	 * Definis l'abscisse minimale en jeu
	 * 
	 * @param minX
	 *            L'abscisse minimale.
	 */
	public void setMinX(int minX) {
		this.minX = minX;
	}

	/**
	 * 
	 * @return L'ordonnee minimale en jeu.
	 */
	public int getMinY() {
		return minY;
	}

	/**
	 * Definis l'ordonne minimale en jeu.
	 * 
	 * @param minY
	 *            L'ordonnee minimale
	 */
	public void setMinY(int minY) {
		this.minY = minY;
	}

	/**
	 * 
	 * @return L'ordonnee maximale en jeu.
	 */
	public int getMaxY() {
		return maxY;
	}

	/**
	 * Definis l'ordonnee maximale en jeu.
	 * 
	 * @param maxY
	 *            L'ordonnee maximale.
	 */
	public void setMaxY(int maxY) {
		this.maxY = maxY;
	}

	/**
	 * 
	 * @return Le type de monde du jeu.
	 */
	public int getTypeMonde() {
		return this.typeMonde;
	}

	/**
	 * Definie le type de monde dans lequel le jeu est simuler.
	 * 
	 * @param typeMonde
	 *            Le type de monde.
	 */
	public void setTypeMonde(int typeMonde) {
		this.typeMonde = typeMonde;
	}

	/**
	 * 
	 * @return Le numero de la generation actuelle du jeu.
	 */
	public int getNbGeneration() {
		return this.nbGeneration;
	}
}