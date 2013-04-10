package l2info.jeuDeLaVie;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		
		switch (args.length) {
		case 0:
			System.out
					.println("Entrez en paramètres des arguments \n Pour plus d'aide tapez: java -jar JeuDeLaVie.jar -h");
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
		case 3:
			if (args[0].equals("-s")) {
				try {
					int duree = Integer.parseInt(args[1]);
					File f = new File(args[2]);
					if (f.exists() && f.isFile()) {
						// À completer
					}
				} catch (NumberFormatException e) {
					System.out.println("La durée doit être numerique \n");
					ErrorArgs();
				}
				// À completer
			} else {
				if (args[0].equals("-c")) {
					try {
						int max_duree = Integer.parseInt(args[1]);
						File f = new File(args[2]);
						if (f.exists() && f.isFile()) {
							// À completer
						}
					} catch (NumberFormatException e) {
						System.out.println("La durée doit être numerique \n");
						ErrorArgs();
					}
					// À completer
				} else {
					if (args[0].equals("-w")) {
						try {
							int max_duree = Integer.parseInt(args[1]);
							File f = new File(args[2]);
							if (f.isDirectory()) {
								for (File nom : f.listFiles()) {
									// À completer
								}
							} else {
								ErrorArgs();
							}
						} catch (NumberFormatException e) {
							System.out
									.println("La durée doit être numerique \n");
							ErrorArgs();
						}
						// À completer
					} else
						ErrorArgs();
				}
			}
		default:
			ErrorArgs();
			break;
		}

	}

	private static void ErrorArgs() {
		System.out.println("Erreur d'arguments, essayez de nouveau");
	}

	private static void ListeName() {
		System.out.println(" ***MEMBRES DU GROUPE*** \n");
		System.out.println("> KOLUBAKO Tennessy \n");
		System.out.println("> MAAROUFI Khaled \n");
		System.out.println("> PADONOU LOUEMBET Jimmy \n");
		System.out.println("> YOUGANG Adrielle \n");
	}

	private static void ListeOptions() {
		System.out.println(" ***LISTE DES OPTIONS*** ");
		System.out
				.println("> java -jar JeuDeLaVie.jar -name ==> Affiche les noms et prénoms des participants");
		System.out
				.println("> java -jar JeuDeLaVie.jar -h ==> Rappel de la liste des options du programme");
		System.out
				.println("> java -jar JeuDeLaVie.jar -s d fichier.lif ==> Execution d'une simulation du jeu de durée d affichant les configurations du jeu avec le numéro de génération");
		System.out
				.println("> java -jar JeuDeLaVie.jar -c max fichier.lif ==> Calcul du type d’évolution du jeu avec ses caractéristiques (taille de la queue, période et déplacement); max représente la durée maximale de simulation pour déduire les résultats du calcul");
		System.out
				.println("> java -jar JeuDeLaVie.jar -w max dossier ==> Calcul du type d’évolution de tous les jeux contenus dans le dossier passé en paramètre et affiche les résultats sous la forme d’un fichier html ");
	}

}
