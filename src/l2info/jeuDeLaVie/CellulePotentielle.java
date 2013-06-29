package l2info.jeuDeLaVie;

/**
 * Classe definissant les Cellules adjacante aux cellules vivante, ayant donc une chance de devenir vivante à la generation suivante.
 * @author Ten
 *
 */
public class CellulePotentielle extends Cellule {

	protected int nbVoisins;
	protected boolean existe;
	
	/**
	 * Cree une nouvelle CellulePotentielle
	 * @param x
	 * 		Abscisse de la CellulePotentielle
	 * @param y
	 * 		Ordonnee de la CellulePotentielle
	 * @param existe
	 * 		Une Cellule existe-t-elle Ã  cette position.
	 * @param nbVoisin
	 * 		Nombre de voisin de cette CellulePotentielle.
	 */
	public CellulePotentielle(int x, int y, boolean existe, int nbVoisin) {
		super(x, y);
		this.nbVoisins = nbVoisin;
		this.existe = existe;
	}
	
	/**
	 * Retourne l'existance ou non d'une Cellule a  cette position.
	 * @return
	 * 		True si une Cellule est presente a cette position, false sinon.
	 */
	public boolean exists(){
		return this.existe;
	}
	
	/**
	 * Definis si une Cellule existe a cette position.
	 * @param e
	 * 		True si une Cellule existe a cette position, false sinon.
	 */
	public void setExist(boolean e){
		this.existe = e;
	}

	
	@Override
	public boolean equals(Object o){
		if(this.x == ((CellulePotentielle)o).getX() && this.y == ((CellulePotentielle)o).getY()){
			return true;
		}
		else
			return false;
	}
	/**
	 * Incremente le nombre de voisin d'une unite
	 */
	public void ajouterVoisin(){
		this.nbVoisins++;
	}
	/**
	 * Retourne le nombre de voisin de l'objet courant
	 * @return
	 * 		un entier correspondant au nombre actuel de voisins de la cellulePotentielle
	 */
	public int getNbVoisin(){
		return this.nbVoisins;
	}
	
}
