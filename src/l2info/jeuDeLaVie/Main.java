package l2info.jeuDeLaVie;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
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
		case 4:
			if (args[0].equals("-s")) {
				try {
					int duree = Integer.parseInt(args[1]);
					int typeMonde = Main.TypeMonde(args[3]);
					if (Main.IsFichierLif(args[2]) && typeMonde != 0) {
						Jeu start = new Jeu();
						start.loadGame(args[2]);
						start.evaluer(duree, typeMonde, true);
					} else
						Main.ErrorArgs();
				} catch (NumberFormatException e) {
					System.out.println("La durée doit être numerique \n");
					ErrorArgs();
				}
			} else {
				if (args[0].equals("-c")) {
					try {
						int max_duree = Integer.parseInt(args[1]);
						int typeMonde = Main.TypeMonde(args[3]);
						if (Main.IsFichierLif(args[2]) && typeMonde != 0) {
							Jeu start = new Jeu();
							start.loadGame(args[2]);
							start.evaluer(max_duree, typeMonde, true);
							// À completer ou pas
							// //System.out.println("Periode queue et evolution");
						} else
							Main.ErrorArgs();
					} catch (NumberFormatException e) {
						System.out.println("La durée doit être numerique \n");
						Main.ErrorArgs();
					}
				} else {
					if (args[0].equals("-w")) {
						try {
							int max_duree = Integer.parseInt(args[1]);
							File f = new File(args[2]);
							int typeMonde = Main.TypeMonde(args[3]);
							if (f.isDirectory() && typeMonde != 0) {
								ArrayList<Jeu> listeJeu = new ArrayList<Jeu>();
								for (File fichier : f.listFiles()) {
									String nom = f.getName().concat(
											"/" + fichier.getName());
									if (Main.IsFichierLif(nom)) {
										Jeu start = new Jeu();
										start.loadGame(nom);
										start.evaluer(max_duree, typeMonde,
												false);
										listeJeu.add(start);
									}
								}
								System.out.println(Jeu.toFullHTML(listeJeu)
										.toString());
							} else {
								Main.ErrorArgs();
							}
						} catch (NumberFormatException e) {
							System.out
									.println("La durée doit être numerique \n");
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
	 * Affichage des noms et prénoms des membres du groupes
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
	 * Test de l'existence du fichier et de son type
	 * 
	 * @param fichier
	 *            le nom du fichier
	 * @return true si le fichier existe et est de type .lif
	 */
	public static boolean IsFichierLif(String fichier) {
		if (fichier.contains(".lif")) {
			File f = new File(fichier);
			if (f.exists() && f.isFile()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Determine le type de jeu voulu par l'utilisateur
	 * 
	 * @param s
	 *            String ayant pour valeur le 4e argument ajouté par
	 *            l'utilisateur
	 * @return Un entier different de 0 si le type de jeu specifié par
	 *         l'utilisateur correspond à celui inscrit dans les options de jeu
	 */
	public static int TypeMonde(String s) {
		if (s.equals("normal"))
			return Jeu.MONDE_NORMAL;
		if (s.equals("circulaires"))
			return Jeu.MONDE_CIRCULAIRE;
		if (s.equals("frontieres"))
			return Jeu.MONDE_FRONTIERES;
		return 0;
	}

}