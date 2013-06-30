package l2info.inputOutput.jeuDeLaVie;

import java.util.ArrayList;

import l2info.jeuDeLaVie.Cellule;
import l2info.jeuDeLaVie.Jeu;
import l2info.jeuDeLaVie.TypeEvolution;

/**
 * Classe contenant les fonctions d'affichage et de generation HTML.
 * 
 * @author Ten
 * 
 */
public class Output {

	/**
	 * Genere le code html affichant les caracteristiques des evolutions passés
	 * en parametre.
	 * 
	 * @param listeEvolution
	 *            ArrayList de TypeEvolution contenant les caracteristique
	 *            d'evolution des jeux.
	 * @return Retourne un StringBuffer contenant le code html
	 */
	public static StringBuffer toHtml(ArrayList<TypeEvolution> listeEvolution) {
		StringBuffer html = new StringBuffer();
		html.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\"><html><head><title>Resultats Jeu de la vie </title></head><body> <table border=\"1\"> <tr><th>Nom</th><th>type</th><th>Taille queue</th><th>Periode</th></tr><tr>");
		for (TypeEvolution evolution : listeEvolution) {
			html.append("<td>" + evolution.getName() + "</td><td>");

			switch (evolution.getType()) {
			case TypeEvolution.INDETERMINE:
				html.append("Indetermine");
				break;
			case TypeEvolution.MORT:
				html.append("Mort");
				break;
			case TypeEvolution.OSCILLATEUR:
				html.append("Oscillateur");
				break;
			case TypeEvolution.STABLE:
				html.append("Stable");
				break;
			case TypeEvolution.VAISSEAU:
				html.append("Vaisseau");
			}
			html.append("</td><td>"
					+ (evolution.getTailleQueue() > 0 ? evolution
							.getTailleQueue() : "Inconnue")
					+ "</td><td>"
					+ (evolution.getPeriode() > 0 ? evolution.getPeriode()
							: "Inconnue") + "</td></tr>");
		}

		html.append("</table> </body></html>");
		return html;
	}

	/**
	 * Genere un String permettant d'afficher la disposition des cellules du
	 * jeu.
	 * 
	 * @param jeu
	 *            Jeu à afficher
	 * @return Un String contenant la disposition des cellules.
	 */
	public static String display(Jeu jeu) {
		String tab = "";
		if (!jeu.getListeCellule().isEmpty()) {
			int xActuel = jeu.getMinX();
			int yActuel = jeu.getMinY();
			for (Cellule c : jeu.getListeCellule()) {

				while (yActuel < c.getY()) {
					tab += "| ";
					for (; xActuel < jeu.getMaxX(); xActuel++) {
						tab += "| ";
					}
					tab += "|\n";
					yActuel++;
					xActuel = jeu.getMinX();
				}

				while (xActuel < c.getX()) {
					tab += "| ";
					xActuel++;
				}

				tab += "|*";
				xActuel++;
				if (xActuel == jeu.getMaxX() + 1) {
					tab += "|";
					if (yActuel != jeu.getMaxY()) {
						tab += "\n";
						xActuel = jeu.getMinX();
						yActuel++;
					}
				}

			}

			if (xActuel < jeu.getMaxX() + 1) {
				while (xActuel < jeu.getMaxX() + 1) {
					tab += "| ";
					xActuel++;
				}
				tab += "|";
			}

		}
		return tab;
	}

}
