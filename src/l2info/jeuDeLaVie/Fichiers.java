package l2info.jeuDeLaVie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

public class Fichiers {
	private int x = 0;
	private int y = 0;

	private void ParamGame(StringTokenizer stk) {
		ArrayList<Integer> nb = new ArrayList<Integer>();
		while (stk.hasMoreTokens()) {
			try {
				nb.add(Integer.parseInt(stk.nextToken())); // Essaie de parser
															// le token
			} catch (Exception e) {
			}
		}
		x = nb.get(0);
		y = nb.get(1);
	}

	public ArrayList<Cellule> loadGame(String src) throws IOException {
		ArrayList<Cellule> jeu = new ArrayList<Cellule>();
		Vector<String> monVector = new Vector<String>();
		BufferedReader b = new BufferedReader(new FileReader(src));
		String a = b.readLine();
		while (a != null) {
			monVector.addElement(a);
			a = b.readLine();
		}
		String s = monVector.firstElement(); // Attribue à s la 1ere ligne du
												// fichier enregistré dans le
												// vector
		monVector.removeElementAt(0);// Suprime la 1ere ligne du fichier
										// enregistré dans le vector
		StringTokenizer stk = new StringTokenizer(s, " ");
		this.ParamGame(stk);
		for (int i = 0; i < monVector.size(); i++) {
			for (int j=0;i<monVector.size();i++){
				if(monVector.get(i).charAt(j)=='*'){
					jeu.add(new Cellule(x+i,y+j));
				}
			}
		}
		b.close();
		return jeu;
	}
	
	public  void GenerateHtml(Jeu j){
	}
}
