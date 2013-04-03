package l2info.jeuDeLaVie;

public class CellulePotentielle extends Cellule {

	protected int nbVoisins;
	protected boolean existe;
	
	/**
	 * Créé une nouvelle CellulePotentielle
	 * @param x
	 * 		Abscisse de la CellulePotentielle
	 * @param y
	 * 		Ordonnée de la CellulePotentielle
	 * @param existe
	 * 		Une Cellule existe-t-elle à cette position.
	 * @param nbVoisin
	 * 		Nombre de voisin de cette CellulePotentielle.
	 */
	public CellulePotentielle(int x, int y, boolean existe, int nbVoisin) {
		super(x, y);
		this.nbVoisins = nbVoisin;
		this.existe = existe;
	}
	
	/**
	 * Retourne l'existance ou non d'une Cellule à cette position.
	 * @return
	 * 		True si une Cellule est présente à cette position, false sinon.
	 */
	public boolean exists(){
		return this.existe;
	}
	
	/**
	 * Definis si une Cellule existe à cette position.
	 * @param e
	 * 		True si une Cellule existe à cette position, false sinon.
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
	 * Incrémente le nombre de voisin d'une unité
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
