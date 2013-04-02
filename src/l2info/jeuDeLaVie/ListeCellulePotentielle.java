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
			ListeCellulePotentielle cTemp = this;
			while (cTemp.suivant != null) {
				cTemp = cTemp.suivant;
			}
			cTemp.suivant = new ListeCellulePotentielle(c);
		}
	}

	public CellulePotentielle tete(){
		return this.element;
	}

	public ListeCellulePotentielle queue(){
		return this.suivant;
	}

	public void insertElementAfter(CellulePotentielle c){
		if(this.suivant != null){
			ListeCellulePotentielle temp = this.suivant;
			this.suivant = new ListeCellulePotentielle(c);
			this.suivant.suivant = temp;
		}
		else{
			this.suivant = new ListeCellulePotentielle(c);
		}
	}
	
	public ListeCellulePotentielle AjouterTete(CellulePotentielle c){
		ListeCellulePotentielle temp = new ListeCellulePotentielle(c);
		temp.suivant = this;
		return temp;
	}

}
