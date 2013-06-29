package l2info.jeuDeLaVie;

import java.awt.Event;
import java.io.*;
import java.util.*;

import l2info.inputOutput.jeuDeLaVie.Input;
import l2info.inputOutput.jeuDeLaVie.Output;

public class Main {

	public static void main(String[] args) throws IOException {
		
		switch (args.length) {
		case 0:
			System.out
					.println("Entrez en parametre des arguments \n Pour plus d'aide tapez: java -jar JeuDeLaVie.jar -h");
			break;
		case 1:
			if (args[0].equals("-name")) {
				ListeName();
			} else {
				if (args[0].equals("-h")) {
					ListeOptions();
				} else
					ErrorArgs();
			}
			break;
		case 4:
			if (args[0].equals("-s")) {
				try {
					int duree = Integer.parseInt(args[1]);
					int typeMonde = Jeu.TypeMonde(args[3]);
					
					simulation(duree, typeMonde, args[2]);

				} catch (NumberFormatException e) {
					System.out.println("La duree doit etre numerique \n");
					ErrorArgs();
				}
			} else {
				if (args[0].equals("-c")) {
					try {
						int max_duree = Integer.parseInt(args[1]);
						int typeMonde = Jeu.TypeMonde(args[3]);
						
						System.out.println(calculerEvolution(max_duree, typeMonde, args[2]).toString());
						
					} catch (NumberFormatException e) {
						System.out.println("La duree doit etre numerique \n");
						Main.ErrorArgs();
					}
				} else {
					if (args[0].equals("-w")) {
						try {
							int max_duree = Integer.parseInt(args[1]);
							File f = new File(args[2]);
							int typeMonde = Jeu.TypeMonde(args[3]);
							if (f.isDirectory()) {
								
								ArrayList<TypeEvolution> listeEvo = new ArrayList<TypeEvolution>();
								for (File fichier : f.listFiles()) {
									String nom = f.getName().concat("/" + fichier.getName());
										listeEvo.add(calculerEvolution(max_duree, typeMonde, nom));
									}
								System.out.println(Output.toHtml(listeEvo));
								
							} 
							else {
								Main.ErrorArgs();
							}
						} catch (NumberFormatException e) {
							System.out
									.println("La duree doit etre numerique \n");
							Main.ErrorArgs();
						}
					} else
						Main.ErrorArgs();
				}
			}
			break;
			
		default:
			Main.ErrorArgs();
			break;
		}

	}

	/**
	 * Affichage du message d'erreur
	 */
	public static void ErrorArgs() {
		System.out.print("\n Erreur d'arguments, essayez de nouveau");
	}

	/**
	 * Affichage des noms et prenoms des membres du groupes
	 */
	public static void ListeName() {
		System.out.println(" ***MEMBRES DU GROUPE*** \n");
		System.out.println("> KOLUBAKO Tennessy ");
		System.out.println("> MAAROUFI Khaled ");
		System.out.println("> PADONOU LOUEMBET Jimmy ");
		System.out.println("> YOUGANG Adrielle ");
	}

	/**
	 * Affichage des options du jeu
	 */
	public static void ListeOptions() {
		System.out.println(" ***LISTE DES OPTIONS*** ");
		System.out
				.println("> java -jar JeuDeLaVie.jar -name ==> Affiche les noms et prénoms des participants");
		System.out
				.println("> java -jar JeuDeLaVie.jar -h ==> Rappel de la liste des options du programme");
		System.out
				.println("> java -jar JeuDeLaVie.jar -s d fichier.lif normal ==> Execution d'une simulation du jeu en mode normal de durée d affichant le numéro de génération");
		System.out
				.println("> java -jar JeuDeLaVie.jar -s d fichier.lif circulaires ==> Execution d'une simulation du jeu en mode circulaire de durée d affichant le numéro de génération");
		System.out
				.println("> java -jar JeuDeLaVie.jar -s d fichier.lif frontieres ==> Execution d'une simulation du jeu en mode frontiere de durée d affichant le numéro de génération");
		System.out
				.println("> java -jar JeuDeLaVie.jar -c max fichier.lif normal ==> Calcul du type d’évolution du jeu en monde normal avec ses caractéristiques (taille de la queue, période et déplacement); max représente la durée maximale de simulation pour déduire les résultats du calcul");
		System.out
				.println("> java -jar JeuDeLaVie.jar -c max fichier.lif circulaires ==> Calcul du type d’évolution du jeu en monde circulaires avec ses caractéristiques (taille de la queue, période et déplacement); max représente la durée maximale de simulation pour déduire les résultats du calcul");
		System.out
				.println("> java -jar JeuDeLaVie.jar -c max fichier.lif fontieres ==> Calcul du type d’évolution du jeu en monde frontieres avec ses caractéristiques (taille de la queue, période et déplacement); max représente la durée maximale de simulation pour déduire les résultats du calcul");
		System.out
				.println("> java -jar JeuDeLaVie.jar -w max dossier normal ==> Calcul du type d’évolution de tous les jeux contenus dans le dossier passé en paramètre en monde normal et affiche les résultats sous la forme d’un fichier html ");
		System.out
				.println("> java -jar JeuDeLaVie.jar -w max dossier circulaires ==> Calcul du type d’évolution de tous les jeux contenus dans le dossier passé en paramètre en monde circulaire et affiche les résultats sous la forme d’un fichier html ");
		System.out
				.println("> java -jar JeuDeLaVie.jar -w max dossier frontieres ==> Calcul du type d’évolution de tous les jeux contenus dans le dossier passé en paramètre en monde frontiere et affiche les résultats sous la forme d’un fichier html ");
	}
	
	/**
	 * Simule et affiche les evolutions, generation par generation, d'un jeu.
	 * @param duree Nombre d'evolution a effectuer.
	 * @param typeMonde Type de monde dans lequel effectuer cette simulation.
	 * @param filename Chemin du fichier lif contenant les caracteristiques du jeu.
	 */
	public static void simulation(int duree, int typeMonde, String filename){
		if(typeMonde != 0){
			try{
				Jeu jeu = Input.loadGame(filename);
				jeu.setTypeMonde(typeMonde);
				
				for(int i=0; i<duree; i++){
					System.out.print((char)Event.ESCAPE + "8");
			  		System.out.print((char)Event.ESCAPE + "[J");
					System.out.println(Output.display(jeu));
					System.out.println("Generation N�" + jeu.getNbGeneration());
					
					jeu.calculer();
					Thread.sleep(1000);
				}
				
			}catch(Exception e){
				System.out.println("Erreur lors de la lecture du fichier");
			}
			
		}
	}
	
	/**
	 * Calcule et affiche les caracteristiques d'evolution d'un jeu.
	 * @param duree Nombre d'evolution maximale � effectuer.
	 * @param typeMonde Type de monde dans lequel effectuer la simulation.
	 * @param filename Chemin du fichier lif contenant les caracteristiques du jeu.
	 * @return Le type d'evolution du jeu. 
	 */
	public static TypeEvolution calculerEvolution(int duree, int typeMonde, String filename){
		TypeEvolution typeEvo = new TypeEvolution();
		if(typeMonde != 0){
			try{
				Jeu jeu = Input.loadGame(filename);
				jeu.setTypeMonde(typeMonde);
				typeEvo.calculerTypeEvolution(jeu, duree);
				System.out.println(typeEvo.toString());
			}catch(Exception e){
				System.out.println("Erreur lors de la lecture du fichier");
			}
		}
		return typeEvo;
	}

}