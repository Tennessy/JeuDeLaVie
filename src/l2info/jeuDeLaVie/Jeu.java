package l2info.jeuDeLaVie;

import java.awt.Event;
import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;

public class Jeu {
	protected ArrayList<Cellule> listeCellule;
	private int type;
	private String name;
	private int tailleQueue;
	private int minX;
	private int minY;
	private int maxX;
	private int maxY;
	protected int nbGeneration;
	private int periode;


	/**
	 * Constructeur par defaut, créé un nouveau Jeu vide.
	 */
	public Jeu() {
		listeCellule = new ArrayList<Cellule>();
		setType(TypeEvolution.INDETERMINE);
		this.nbGeneration = 0;
		this.setName("");
		this.setTailleQueue(0);
		this.setMinX(999999);
		this.setMinY(999999);
		this.setMaxX(-999999);
		this.setMaxY(-999999);
		this.setPeriode(0);
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
		this.setType(TypeEvolution.INDETERMINE);
		this.setPeriode(0);
		this.setTailleQueue(0);
		this.setMinX(minX);
		this.setMinY(minY);
		this.setMaxX(maxX);
		this.setMaxY(maxY);
		this.nbGeneration = 0;
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
	 * Calcule l'évolution du Jeu sur un nombre de tours donné.
	 * 
	 * @param nbTours
	 *            Nombre de tours à effectuer.
	 * @param typeMonde
	 *            Type de monde ( Normal, Frontiere ou Circulaire ) du Jeu.
	 * @return Le type d'évolution asymptotique du Jeu.
	 */
	public int evaluer(int nbTours, int typeMonde, boolean afficher) {
		int nbGenerationTemoin = 0;
		ArrayList<Cellule> listeCelTemoin = new ArrayList<Cellule>();
		for (Cellule e : this.listeCellule) {
			listeCelTemoin.add(e);
		}

		for (int i = 0; i < nbTours; i++) {
			ArrayList<Cellule> temp = this.calculer(this.listeCellule, typeMonde);
			this.nbGeneration++;
			if (afficher) {
				System.out.print((char)Event.ESCAPE + "8");
				System.out.print((char)Event.ESCAPE + "[J");
				System.out.println(output.display());
				System.out.println("Genereation " + nbGeneration);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			if (this.listeCellule.equals(temp)) {
				this.setType(TypeEvolution.STABLE);
				this.setTailleQueue(this.nbGeneration);
				this.setPeriode(1);
				return TypeEvolution.STABLE;
			}

			this.listeCellule = temp;
			listeCelTemoin = this.calculer(listeCelTemoin, typeMonde);
			listeCelTemoin = this.calculer(listeCelTemoin, typeMonde);
			nbGenerationTemoin += 2;

			if (this.listeCellule.isEmpty()) {
				this.setType(TypeEvolution.MORT);
				this.setTailleQueue(this.nbGeneration);
				this.setPeriode(1);
				return TypeEvolution.MORT;
			} else if (this.listeCellule.equals(listeCelTemoin)) {
				this.setType(TypeEvolution.OSCILLATEUR);
				this.setTailleQueue(this.nbGeneration);
				this.setPeriode(nbGenerationTemoin - this.nbGeneration);
				return TypeEvolution.OSCILLATEUR;
			} else if (isTranslation(this.listeCellule, listeCelTemoin)) {
				this.setType(TypeEvolution.VAISSEAU);
				this.setTailleQueue(this.nbGeneration);
				this.setPeriode(nbGenerationTemoin - this.nbGeneration);
				return TypeEvolution.VAISSEAU;
			}
		}
		return TypeEvolution.INDETERMINE;
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
	public ArrayList<Cellule> calculer(ArrayList<Cellule> listCel, int typeMonde) {

		if (listCel.isEmpty()) {
			return new ArrayList<Cellule>();
		}

		ListeCellulePotentielle[] listesCellulePot = new ListeCellulePotentielle[9];
		for (int i = 0; i < 9; i++) {
			listesCellulePot[i] = new ListeCellulePotentielle();
		}

		Iterator<Cellule> itCels = listCel.iterator();
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

		ListeCellulePotentielle listeSomme = listesCellulePot[0];
		for (int i = 1; i < 9; i++) {
			ListeCellulePotentielle first = listeSomme;
			first.additionCelPot(listesCellulePot[i]);
		}

		if (typeMonde == TypeEvolution.MONDE_CIRCULAIRE) {
			ListeCellulePotentielle liste = listeSomme;
			ListeCellulePotentielle[] exterieur = new ListeCellulePotentielle[4];
			for (int i = 0; i < exterieur.length; i++) {
				exterieur[i] = new ListeCellulePotentielle();
			}
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

			for (int i = 0; i < exterieur.length; i++) {
				liste = listeSomme;
				if (exterieur[i].tete() != null)
					listeSomme.additionCelPot(exterieur[i]);
			}
		}

		ArrayList<Cellule> lCellule = new ArrayList<Cellule>();
		while (listeSomme != null) {
			if (!listeSomme.tete().exists()
					&& listeSomme.tete().getNbVoisin() == 3) {
				if (typeMonde == TypeEvolution.MONDE_FRONTIERES
						|| typeMonde == TypeEvolution.MONDE_CIRCULAIRE) {
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

		return lCellule;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

}