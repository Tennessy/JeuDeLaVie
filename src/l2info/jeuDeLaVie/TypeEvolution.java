package l2info.jeuDeLaVie;

import java.util.Iterator;

/**
 * Classe permettant le calcule du type d'evolution d'un jeu.
 * 
 * @author Ten
 * 
 */
public class TypeEvolution {

	public static final int MORT = 1;
	public static final int STABLE = 2;
	public static final int VAISSEAU = 4;
	public static final int OSCILLATEUR = 3;
	public static final int INDETERMINE = 5;

	protected int type;
	protected int periode;
	protected int tailleQueue;
	protected String name;

	/**
	 * Constructeur par defaut. Definis le type d'evolution en Indetermine,
	 * ainsi que la periode et la taille de la Queue à 0.
	 */
	public TypeEvolution() {
		this.type = TypeEvolution.INDETERMINE;
		this.periode = 0;
		this.tailleQueue = 0;
		this.name = "";
	}

	/**
	 * Effectue la simulation d'un jeu afin d'en determiner le type d'evolution.
	 * Ne modifie pas le jeu passe en parametre.
	 * 
	 * @param jeu
	 *            Jeu sur lequel effectuer le calcule du type d'evolution.
	 * @param nbTours
	 *            Nombre d'evolution a effectuer au maximum.
	 */
	public void calculerTypeEvolution(Jeu jeu, int nbTours) {
		this.name = jeu.getName();
		Jeu jeuCopy = new Jeu(jeu);
		Jeu jeuX2 = new Jeu(jeu);
		Jeu jeuTemp = new Jeu(jeu);

		int i = 0;

		while (i < nbTours && this.type == TypeEvolution.INDETERMINE) {
			jeuTemp.calculer();
			jeuX2.calculer();
			jeuX2.calculer();

			if (jeuTemp.getListeCellule().isEmpty()) {
				this.type = TypeEvolution.MORT;
				this.periode = 1;
				this.tailleQueue = jeuTemp.getNbGeneration();
			} else if (jeuTemp.getListeCellule().equals(
					jeuCopy.getListeCellule())) {
				this.type = TypeEvolution.STABLE;
				this.periode = 1;
				this.tailleQueue = jeuTemp.getNbGeneration();

			} else if (jeuTemp.getListeCellule()
					.equals(jeuX2.getListeCellule())) {
				this.type = TypeEvolution.OSCILLATEUR;
				this.periode = jeuX2.getNbGeneration()
						- jeuTemp.getNbGeneration();
				this.tailleQueue = jeuTemp.getNbGeneration();
			} else if (isTranslation(jeuTemp, jeuX2)) {
				this.type = TypeEvolution.VAISSEAU;
				this.tailleQueue = jeuTemp.getNbGeneration();
				this.periode = jeuX2.getNbGeneration()
						- jeuTemp.getNbGeneration();
			}
			jeuCopy = new Jeu(jeuTemp);
			i++;
		}
	}

	/**
	 * 
	 * @return Le type d'evolution du jeu.
	 */
	public int getType() {
		return type;
	}

	/**
	 * 
	 * @return La periode du jeu.
	 */
	public int getPeriode() {
		return periode;
	}

	/**
	 * 
	 * @return La taille de la queue du jeu.
	 */
	public int getTailleQueue() {
		return tailleQueue;
	}

	/**
	 * Verifie si un jeu est une translation d'un autre jeu. ( Au niveau de la
	 * disposition des cellules ).
	 * 
	 * @param jeu1
	 *            Jeu a tester
	 * @param jeu2
	 *            Second jeu a tester
	 * @return True si un jeu est une translation d'un autre, false sinon.
	 */
	private boolean isTranslation(Jeu jeu1, Jeu jeu2) {
		Iterator<Cellule> it1 = jeu1.getListeCellule().iterator();
		Iterator<Cellule> it2 = jeu2.getListeCellule().iterator();
		boolean equal = true;
		if (jeu1.getListeCellule().size() == jeu2.getListeCellule().size()) {
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

	/**
	 * 
	 * @return Le nom du jeu.
	 */
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		String result = "Type d'evolution : ";
		switch (this.getType()) {
		case TypeEvolution.INDETERMINE:
			result += "Indetermine";
			break;
		case TypeEvolution.MORT:
			result += "Mort";
			break;
		case TypeEvolution.OSCILLATEUR:
			result += "Oscillateur";
			break;
		case TypeEvolution.STABLE:
			result += "Stable";
			break;
		case TypeEvolution.VAISSEAU:
			result += "Vaisseau";
		}
		result += "\nTaille de la queue : " + this.getTailleQueue()
				+ "\nPeriode : " + this.getPeriode();
		return result;

	}

}
