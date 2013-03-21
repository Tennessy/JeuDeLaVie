package l2info.jeuDeLaVie;

public class Cellule implements Comparable<Cellule>{
	protected int x;
	protected int y;
	
	public Cellule(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int compareTo(Cellule o) {
		if(this.y != o.y){
			return this.y - o.y;
		}
		else
			return this.x - o.x;
	}
	
	@Override
	public boolean equals(Object o){
		if(this.x == ((Cellule)o).getX() && this.y == ((Cellule)o).getY()){
			return true;
		}
		else
			return false;
	}
}
