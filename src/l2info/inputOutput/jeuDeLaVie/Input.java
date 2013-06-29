package l2info.inputOutput.jeuDeLaVie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

import l2info.jeuDeLaVie.Cellule;
import l2info.jeuDeLaVie.Jeu;

/**
 * Classe Contenant le necessaire au chargement des fichiers lif.
 * @author Ten
 *
 */
public class Input {
	
	/**
	 * Charge un Jeu à partir d'un fichier lif.
	 * @param src Chemin vers le fichier lif à charger.
	 * @return Renvois un Jeu avec les caracteristiques du fichier lif passé en parametre.
	 * @throws IOException
	 */
	public static Jeu loadGame(String src) throws IOException {
		Jeu jeu = new Jeu();
		jeu.setName(src);
		ArrayList<Cellule> listeCel = new ArrayList<Cellule>();
		jeu.setName(src);
		Vector<String> monVector = new Vector<String>();
		BufferedReader b = new BufferedReader(new FileReader(src));
		String a = b.readLine();
		while (a != null) {
			monVector.addElement(a);
			a = b.readLine();
		}
		String s = monVector.firstElement();
		monVector.removeElementAt(0);
		StringTokenizer stk = new StringTokenizer(s, " ");
		ParamGame(stk, jeu);
		for (int i = 0; i < monVector.size(); i++) {
			if (jeu.getMaxX() < jeu.getMaxX() + monVector.get(i).length() - 1)
				jeu.setMaxX(jeu.getMaxX() + (monVector.get(i).length() - 1));
			for (int j = 0; j < monVector.get(i).length(); j++) {
				if (monVector.get(i).charAt(j) == '*') {
					listeCel.add(new Cellule(jeu.getMinX() + j, jeu.getMinY() + i));
				}
			}
		}
		jeu.setMaxY(jeu.getMinY() + monVector.size() - 1);
		b.close();
		jeu.setListeCellule(listeCel);
		return jeu;
	}
	
	
	private static void ParamGame(StringTokenizer stk, Jeu jeu) {
		ArrayList<Integer> nb = new ArrayList<Integer>();
		while (stk.hasMoreTokens()) {
			try {
				nb.add(Integer.parseInt(stk.nextToken()));
			} catch (Exception e) {
			}
		}
		jeu.setMinX(nb.get(1));
		jeu.setMinY(nb.get(0));
	}
	
}
