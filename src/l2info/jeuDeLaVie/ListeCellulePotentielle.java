package l2info.jeuDeLaVie;

public class ListeCellulePotentielle {

	protected CellulePotentielle element;
	protected ListeCellulePotentielle suivant;

	public ListeCellulePotentielle() {
		this.element = null;
		this.suivant = null;
	}

	public ListeCellulePotentielle(CellulePotentielle c) {
		this.element = c;
		this.suivant = null;
	}

	public void ajouterElement(CellulePotentielle c) {
		if (this.element == null) {
			this.element = c;
		} 
		else {
			if (this.suivant != null) {
				ListeCellulePotentielle cTemp = this.suivant;
				while (cTemp.suivant != null) {
					cTemp = cTemp.suivant;
				}
				cTemp.element = c;
			} 
			else
				this.suivant = new ListeCellulePotentielle(c);
		}
	}
	
	public CellulePotentielle tete(){
		return this.element;
	}
	
	public ListeCellulePotentielle queue(){
		return this.suivant;
	}
}
