import java.awt.Event;
import java.util.ArrayList;
import java.util.Collections;


public class TableCellule {
	public ArrayList<Cellule> cellules;
	protected int nbCellule;
	protected int lowest;

	public TableCellule(){
		cellules = new ArrayList<Cellule>();
		nbCellule = 0;
		lowest = 99999999;
		System.out.print((char)Event.ESCAPE + "7");
	}

	public void addCel(Cellule c){
		this.cellules.add(c);
		this.nbCellule++;
		if(c.getX() < lowest){
			this.lowest = c.getX();
		}
	}

	public void afficherCel(){
		System.out.print((char)Event.ESCAPE + "8");
		System.out.print((char)Event.ESCAPE + "[J");
		
		if(!cellules.isEmpty()){
			Collections.sort(cellules);
			Cellule fCel = cellules.iterator().next();
			int lActuelle = fCel.getY();
			int firstC = this.lowest;
			int cActuelle = this.lowest;

			for(Cellule c : cellules){
				//	System.out.println("Cellule : x=" + c.getX() + " | y=" + c.getY());
			}
			//System.out.println("Ligne Actuelle = " + lActuelle);
			//System.out.println("Colonne min = " + this.lowest);
			for(Cellule c : cellules){
				if(c.getY() != lActuelle){
					cActuelle = firstC;
					for(;lActuelle<c.getY(); lActuelle++){
						System.out.println("-");
					}
				}

				for(int i=cActuelle; i<c.getX() ; i++){
					System.out.print('-');
					cActuelle++;
				}
				System.out.print('O');
				cActuelle++;
			}
		}
		else{
			System.out.println("Plus aucune cellule vivante");
		}
	}

	public void nextStep(){
		ArrayList<Cellule> newCel = new ArrayList<Cellule>();
		ArrayList<CellulePot> celPot = new ArrayList<CellulePot>();
		//System.out.println("NEXT STEP");
		for(Cellule c : this.cellules){
			for(int i=c.getY()-1; i<=c.getY()+1; i++){
				if((i-c.getY()) == -1){
					//System.out.println("----------------------");
				}
				//System.out.println("POUR Y = " + (i-c.getY()));

				if(i != c.getY()){
					for(int j=c.getX()-1; j<=c.getX()+1; j++){
						if(!celPot.contains(new CellulePot(j, i))){
							celPot.add(new CellulePot(j, i));
							//System.out.println("Ajout CelPot!"+ j + ";" + i + " par : " + c.getX() + ";" + c.getY());
						}
						else{
							celPot.get(celPot.indexOf(new CellulePot(j,i))).addVoisin();
							//System.out.println("Ajout voisin CelPot! " + j + ";" + i + " par : " + c.getX() + ";" + c.getY());
						}

					}
				}

				else{
					if(!celPot.contains(new CellulePot(c.getX()-1, i))){
						celPot.add(new CellulePot(c.getX()-1, i));
						//System.out.println("Ajout CelPot! " + (c.getX()-1) + ";" + i + " par : " + c.getX() + ";" + c.getY());
					}
					else{
						celPot.get(celPot.indexOf(new CellulePot(c.getX()-1,i))).addVoisin();
						//System.out.println("Ajout voisin CelPot! " + (c.getX()-1) + ";" + i + " par : " + c.getX() + ";" + c.getY());
					}




					if(!celPot.contains(new CellulePot(c.getX()+1, i))){
						celPot.add(new CellulePot(c.getX()+1, i));
						//System.out.println("Ajout CelPot! " + (c.getX()+1) + ";" + i + " par : " + c.getX() + ";" + c.getY());
					}
					else{
						celPot.get(celPot.indexOf(new CellulePot(c.getX()+1,i))).addVoisin();
						//System.out.println("Ajout voisin CelPot! " + (c.getX()+1) + ";" + i + " par : " + c.getX() + ";" + c.getY());
					}


				}
			}
		}
		//System.out.println("Liste celPot ----------------");
		for(CellulePot cp : celPot){
			//System.out.println("x = " + cp.getX() + "| y = " + cp.getY() + " | nbVoisin = " + cp.nbVoisins);
			if(cp.nbVoisins == 3){
				newCel.add(cp);
				if(cp.getX() < this.lowest){
					this.lowest = cp.getX();
				}
				//System.out.println("Ajout!");
			}

			else if(cellules.contains(new Cellule(cp.getX(), cp.getY())) && cp.nbVoisins == 2){
				newCel.add(cp);
				//System.out.println("Ajout vivante : " + cp.getX() + ";" + cp.getY());
				if(cp.getX() < this.lowest){
					this.lowest = cp.getX();
				}
			}
		}

		this.cellules = newCel;

	}

}
