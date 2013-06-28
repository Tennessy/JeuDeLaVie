package l2info.jeuDeLaVie;

public class ListeCellulePotentielle {

	protected CellulePotentielle element;
	protected ListeCellulePotentielle suivant;

	/**
	 * Créé une nouvelle Liste de CellulePotentielle vide
	 */
	public ListeCellulePotentielle() {
		this.element = null;
		this.suivant = null;
	}

	/**
	 * Créé une liste de CellulePotentielle possedant un element.
	 * @param c
	 */
	public ListeCellulePotentielle(CellulePotentielle c) {
		this.element = c;
		this.suivant = null;
	}

	/**
	 * Ajoute un element en fin de liste.
	 * @param c
	 * 		CellulePotentielle à ajouter à la liste.
	 */
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

	/**
	 * Retourne le premier element de la liste
	 * @return
	 * 		Le premier element de la liste.
	 */
	public CellulePotentielle tete(){
		return this.element;
	}

	/**
	 * Retourne l'element qui fait suite à l'element courant.
	 * @return
	 * 		L'element suivant.
	 */
	public ListeCellulePotentielle queue(){
		return this.suivant;
	}

	/**
	 * Insert une nouvelle CellulePotentielle dans la suite, après l'element courant.
	 * @param c
	 */
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
	
	/**
	 * Ajoute une nouvelle CellulePotentielle qui precede l'element courant.
	 * @param c
	 * 		CellulePotentielle à ajouter.
	 * @return
	 * 		La nouvelle liste, avec comme première element la CellulePotentielle passée en parametre.
	 */
	public ListeCellulePotentielle AjouterTete(CellulePotentielle c){
		ListeCellulePotentielle temp = new ListeCellulePotentielle(c);
		temp.suivant = this;
		return temp;
	}
	
	public void additionCelPot(ListeCellulePotentielle lc2) {
		ListeCellulePotentielle lc1 = this;
		while (lc2 != null) {
			if (lc1.tete().getY() == lc2.tete().getY()
					&& lc1.tete().getX() == lc2.tete().getX()) {
				lc1.tete().ajouterVoisin();
				if (lc2.tete().exists())
					lc1.tete().setExist(true);
				lc2 = lc2.queue();
			}

			else if (lc1.tete().getY() < lc2.tete().getY()
					|| (lc1.tete().getY() == lc2.tete().getY() && lc1.tete()
							.getX() < lc2.tete().getX())) {
				if (lc1.queue() == null
						|| (lc1.queue().tete().y > lc2.tete().getY() || (lc1
								.queue().tete().getY() == lc2.tete().getY() && lc1
								.queue().tete().getX() > lc2.tete().getX()))) {
					lc1.insertElementAfter(lc2.tete());
					lc1 = lc1.queue();
					lc2 = lc2.queue();
				} else {
					if (lc1.queue() != null)
						lc1 = lc1.queue();
				}
			} else {
				lc1 = lc1.AjouterTete(lc2.tete());
				lc2 = lc2.queue();
			}
		}
	}

}
