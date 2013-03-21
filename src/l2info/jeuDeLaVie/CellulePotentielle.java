package l2info.jeuDeLaVie;

public class CellulePotentielle extends Cellule {

	protected int nbVoisins;
	
	public CellulePotentielle(int x, int y) {
		super(x, y);
		this.nbVoisins = 1;
	}
	
	@Override
	public boolean equals(Object o){
		if(this.x == ((CellulePotentielle)o).getX() && this.y == ((CellulePotentielle)o).getY()){
			return true;
		}
		else
			return false;
	}
	
	public void ajouterVoisin(){
		this.nbVoisins++;
	}
	
	public int getNbVoisin(){
		return this.nbVoisins;
	}
	
}
