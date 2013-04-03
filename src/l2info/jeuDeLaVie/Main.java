package l2info.jeuDeLaVie;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		ArrayList<Cellule> listeCel = new ArrayList<Cellule>();
		listeCel.add(new Cellule(2, 0));
		listeCel.add(new Cellule(2, 3));
		listeCel.add(new Cellule(2, 4));
		Jeu jeu = new Jeu("Test", listeCel, 0, 0, 4, 4);
		jeu.evaluer(10, Jeu.MONDE_CIRCULAIRE);

		Jeu jeu2 = new Jeu("Test2", listeCel);
		jeu2.evaluer(10, Jeu.MONDE_NORMAL);
		ArrayList<Jeu> listeJeu = new ArrayList<Jeu>();
		listeJeu.add(jeu);
		listeJeu.add(jeu2);

		try {
			FileOutputStream file = new FileOutputStream(new File("test.html"));
			file.write(Jeu.toFullHTML(listeJeu).getBytes("UTF-8"));
			System.out.println("Fin ecriture HTML");
			file.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// System.out.println(jeu.evaluer(10));

		if (args.length > 0) {
			if (args[0] == "-name") {

			} else if (args[0] == "-h") {

			} else if (args.length == 3 && args[0] == "-s") {
				try {
					int duree = Integer.parseInt(args[1]);
				} catch (NumberFormatException e) {
					System.out.println("La durée doit être numerique");
				}
			} else if (args.length == 3 && args[0] == "-c") {
				try {
					int duree = Integer.parseInt(args[1]);
				} catch (NumberFormatException e) {
					System.out.println("La durée doit être numerique");
				}
			} else if (args.length == 3 && args[0] == "-w") {
				try {
					int duree = Integer.parseInt(args[1]);
				} catch (NumberFormatException e) {
					System.out.println("La durée doit être numerique");
				}
			} else {

			}
		} else {

		}

	}

}
