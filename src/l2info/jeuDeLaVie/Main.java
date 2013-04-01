package l2info.jeuDeLaVie;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		ArrayList<Cellule> listeCel = new ArrayList<Cellule>();
		Jeu jeu = new Jeu();
		
		listeCel.add(new Cellule(0,0));
		listeCel.add(new Cellule(1,0));
		listeCel.add(new Cellule(0,1));
		listeCel.add(new Cellule(1,1));
		jeu.setListeCellule(listeCel);
		for(Cellule c : jeu.getListeCellule()){
			System.out.println("Cellule : " + c.getX() + ";" + c.getY());
		}
		
		jeu.setListeCellule(jeu.calculer(jeu.getListeCellule(), Jeu.MONDE_NORMAL));
		System.out.println("-----------");
		for(Cellule c : jeu.getListeCellule()){
			System.out.println("Cellule : " + c.getX() + ";" + c.getY());
		}

		
		//System.out.println(jeu.evaluer(10));
	
		if(args.length > 0){
			if(args[0] == "-name"){
				
			}
			else if(args[0] == "-h"){
				
			}
			else if(args.length == 3 && args[0] == "-s"){
				try{
					int duree = Integer.parseInt(args[1]);
				}catch(NumberFormatException e){
					System.out.println("La durée doit être numerique");
				}
			}
			else if(args.length == 3 && args[0] == "-c"){
				try{
					int duree = Integer.parseInt(args[1]);
				}catch(NumberFormatException e){
					System.out.println("La durée doit être numerique");
				}
			}
			else if(args.length == 3 && args[0] == "-w"){
				try{
					int duree = Integer.parseInt(args[1]);
				}catch(NumberFormatException e){
					System.out.println("La durée doit être numerique");
				}
			}
			else{
				
			}
		}
		else{
			
		}
		
	}

}
