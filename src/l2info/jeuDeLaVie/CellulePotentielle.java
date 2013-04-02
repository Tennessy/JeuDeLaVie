package l2info.jeuDeLaVie;

public class CellulePotentielle extends Cellule {

	protected int nbVoisins;
	protected boolean existe;
	
	public CellulePotentielle(int x, int y, boolean existe, int nbVoisin) {
		super(x, y);
		this.nbVoisins = nbVoisin;
		this.existe = existe;
	}
	
	public boolean exists(){
		return this.existe;
	}
	
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
	
	public void ajouterVoisin(){
		this.nbVoisins++;
	}
	
	public int getNbVoisin(){
		return this.nbVoisins;
	}
	
}
