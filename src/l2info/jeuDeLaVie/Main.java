package l2info.jeuDeLaVie;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Jeu jeu= new Jeu(args);
		/*ArrayList<Cellule> listeCel = new ArrayList<Cellule>();
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
*/
		/*try {
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
		}*/

		// System.out.println(jeu.evaluer(10));

	}
}
