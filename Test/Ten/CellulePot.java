
public class CellulePot extends Cellule{
	
	protected int nbVoisins;
	
	public CellulePot(int x, int y) {
		super(x, y);
		nbVoisins = 1;
	}
	
	public void addVoisin(){
		this.nbVoisins++;
	}
	
	@Override
	public boolean equals(Object o){
		if(this.x == ((CellulePot)o).getX() && this.y == ((CellulePot)o).getY()){
			return true;
		}
		else
			return false;
	}
	
}
