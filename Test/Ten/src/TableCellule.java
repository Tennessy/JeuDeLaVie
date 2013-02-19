import java.awt.Event;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.Vector;

public class TableCellule {
	public ArrayList<Cellule> cellules;
	protected int nbCellule;
	protected int lowest;

	private ArrayList<Integer> ParamGame(ArrayList<Integer> nb,
			StringTokenizer stk) throws IOException {
		while (stk.hasMoreTokens()) {
			try {
				nb.add(Integer.parseInt(stk.nextToken())); // Essaie de parser
															// le token
			} catch (Exception e) {
			}
		}
		return nb;
	}

	public void LoadFile(String file) throws IOException {
		Vector<String> monVector = new Vector<String>();
		BufferedReader b = new BufferedReader(new FileReader(file));
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
		ArrayList<Integer> nb = new ArrayList<Integer>();
		StringTokenizer stk = new StringTokenizer(s, " ");
		nb = this.ParamGame(nb, stk);
		int x = nb.get(0); // Récupère le 1er element de la liste
		int y = nb.get(1); // // Récupère le 2e element de la liste
		File f1=new File("double.lif");
		FileWriter f=new FileWriter(f1);
		BufferedWriter q = new BufferedWriter(f);
		for (int i = 0; i < monVector.size(); i++) {
			q.write(monVector.get(i) + "\n");
		}
		q.close();
		b.close();
		this.LoadFile("double.lif", x, y);
		f1.delete();
	}

	private void LoadFile(String file, int x, int y) throws IOException {
		BufferedReader b = new BufferedReader(new FileReader(file));
		String s = b.readLine();
		while (s != null) {
			if (s.charAt(0) != '#') {
				for (int i = 0; i <= s.length() - 1; i++) {
					if (s.charAt(i) == '*')
						this.addCel(new Cellule(x + i, y));

				}
				s = b.readLine();
				y++;
			}
		}
		b.close();

	}

	public TableCellule() {
		cellules = new ArrayList<Cellule>();
		nbCellule = 0;
		lowest = 99999999;
		System.out.print((char) Event.ESCAPE + "7");
	}

	public void addCel(Cellule c) {
		this.cellules.add(c);
		this.nbCellule++;
		if (c.getX() < lowest) {
			this.lowest = c.getX();
		}
	}

	public void afficherCel() {

		if (!cellules.isEmpty()) {
			Cellule fCel = cellules.iterator().next();
			int lActuelle = fCel.getY();
			int firstC = this.lowest;
			int cActuelle = this.lowest;

			for (Cellule c : cellules) {
				// System.out.println("Cellule : x=" + c.getX() + " | y=" +
				// c.getY());
			}
			// System.out.println("Ligne Actuelle = " + lActuelle);
			// System.out.println("Colonne min = " + this.lowest);
			for (Cellule c : cellules) {
				if (c.getY() != lActuelle) {
					cActuelle = firstC;
					for (; lActuelle < c.getY(); lActuelle++) {
						System.out.println("-");
					}
				}

				for (int i = cActuelle; i < c.getX(); i++) {
					System.out.print('-');
					cActuelle++;
				}
				System.out.print('O');
				cActuelle++;
			}
		} else {
			System.out.println("Plus aucune cellule vivante");
		}
	}

	public void nextStep() {
		ArrayList<Cellule> newCel = new ArrayList<Cellule>();
		ArrayList<CellulePot> celPot = new ArrayList<CellulePot>();
		// System.out.println("NEXT STEP");
		for (Cellule c : this.cellules) {
			for (int i = c.getY() - 1; i <= c.getY() + 1; i++) {
				if ((i - c.getY()) == -1) {
					// System.out.println("----------------------");
				}
				// System.out.println("POUR Y = " + (i-c.getY()));

				if (i != c.getY()) {
					for (int j = c.getX() - 1; j <= c.getX() + 1; j++) {
						if (!celPot.contains(new CellulePot(j, i))) {
							celPot.add(new CellulePot(j, i));
							// System.out.println("Ajout CelPot!"+ j + ";" + i +
							// " par : " + c.getX() + ";" + c.getY());
						} else {
							celPot.get(celPot.indexOf(new CellulePot(j, i)))
									.addVoisin();
							// System.out.println("Ajout voisin CelPot! " + j +
							// ";" + i + " par : " + c.getX() + ";" + c.getY());
						}

					}
				}

				else {
					if (!celPot.contains(new CellulePot(c.getX() - 1, i))) {
						celPot.add(new CellulePot(c.getX() - 1, i));
						// System.out.println("Ajout CelPot! " + (c.getX()-1) +
						// ";" + i + " par : " + c.getX() + ";" + c.getY());
					} else {
						celPot.get(
								celPot.indexOf(new CellulePot(c.getX() - 1, i)))
								.addVoisin();
						// System.out.println("Ajout voisin CelPot! " +
						// (c.getX()-1) + ";" + i + " par : " + c.getX() + ";" +
						// c.getY());
					}

					if (!celPot.contains(new CellulePot(c.getX() + 1, i))) {
						celPot.add(new CellulePot(c.getX() + 1, i));
						// System.out.println("Ajout CelPot! " + (c.getX()+1) +
						// ";" + i + " par : " + c.getX() + ";" + c.getY());
					} else {
						celPot.get(
								celPot.indexOf(new CellulePot(c.getX() + 1, i)))
								.addVoisin();
						// System.out.println("Ajout voisin CelPot! " +
						// (c.getX()+1) + ";" + i + " par : " + c.getX() + ";" +
						// c.getY());
					}

				}
			}
		}
		// System.out.println("Liste celPot ----------------");
		for (CellulePot cp : celPot) {
			// System.out.println("x = " + cp.getX() + "| y = " + cp.getY() +
			// " | nbVoisin = " + cp.nbVoisins);
			if (cp.nbVoisins == 3) {
				newCel.add(cp);
				if (cp.getX() < this.lowest) {
					this.lowest = cp.getX();
				}
				// System.out.println("Ajout!");
			}

			else if (cellules.contains(new Cellule(cp.getX(), cp.getY()))
					&& cp.nbVoisins == 2) {
				newCel.add(cp);
				// System.out.println("Ajout vivante : " + cp.getX() + ";" +
				// cp.getY());
				if (cp.getX() < this.lowest) {
					this.lowest = cp.getX();
				}
			}
		}

		this.cellules = newCel;
		Collections.sort(cellules);
	}

}
