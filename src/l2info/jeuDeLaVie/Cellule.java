package l2info.jeuDeLaVie;

/**
 * Classe definissant les attributs des Cellules du jeu.
 * 
 * @author Ten
 * 
 */
public class Cellule implements Comparable<Cellule> {
	protected int x;
	protected int y;

	/**
	 * Constructeur de la classe Cellule
	 * 
	 * @param x
	 *            Abscisse de la cellule
	 * @param y
	 *            Ordonnée de la cellule
	 */
	public Cellule(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Methode Getter de l'attribut x
	 * 
	 * @return Entier correspondant à l'abscisse de la cellule
	 */
	public int getX() {
		return x;
	}

	/**
	 * Methode Setter de l' attribut x
	 * 
	 * @param x
	 *            Nouvelle valeur de l'abscisse de la cellule
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Methode Getter de l'attribut x
	 * 
	 * @return Entier correspondant à l'ordonnee de la cellule
	 */
	public int getY() {
		return y;
	}

	/**
	 * Methode Setter de l' attribut y
	 * 
	 * @param y
	 *            Nouvel valeur de l'ordonnée de la cellule
	 */
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int compareTo(Cellule o) {
		if (this.y != o.y) {
			return this.y - o.y;
		} else
			return this.x - o.x;
	}

	@Override
	public boolean equals(Object o) {
		if (this.x == ((Cellule) o).getX() && this.y == ((Cellule) o).getY()) {
			return true;
		} else
			return false;
	}
}
