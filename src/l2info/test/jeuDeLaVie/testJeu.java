package l2info.test.jeuDeLaVie;

import java.util.ArrayList;
import java.util.Arrays;

import l2info.inputOutput.jeuDeLaVie.Output;
import l2info.jeuDeLaVie.Cellule;
import l2info.jeuDeLaVie.Jeu;

import org.junit.Before;
import org.junit.Test;

public class testJeu {

	protected Jeu jeu;

	@Before
	public void setUp() {
		this.jeu = new Jeu();
	}

	//Test l'ajout d'une Cellule au jeu.
	@Test
	public void testAjouterCellule() {
		this.jeu.ajouterCellule(new Cellule(0, 0));
		this.jeu.ajouterCellule(new Cellule(1, 1));
		org.junit.Assert.assertEquals(
				Arrays.asList(new Cellule(0, 0), new Cellule(1, 1)),
				jeu.getListeCellule());
	}

	//Test le calcule d'une nouvelle generation d'un jeu.
	@Test
	public void testCalculer() {
		ArrayList<Cellule> listeCel = new ArrayList<Cellule>();
		listeCel.add(new Cellule(1, 1));
		listeCel.add(new Cellule(3, 2));
		listeCel.add(new Cellule(2, 3));
		jeu.setListeCellule(listeCel);
		jeu.setTypeMonde(Jeu.MONDE_NORMAL);
		
		jeu.calculer();
		org.junit.Assert.assertEquals(Arrays.asList(new Cellule(2, 2)),
				jeu.getListeCellule());
	}

	

	//Test l'affichage de la disposition des Cellule du jeu.
	@Test
	public void testDisplay() {
		jeu = new Jeu("test", new ArrayList<Cellule>(), 0, 0, 4, 4);
		jeu.ajouterCellule(new Cellule(0, 0));
		jeu.ajouterCellule(new Cellule(1, 1));
		jeu.ajouterCellule(new Cellule(2, 2));
		jeu.ajouterCellule(new Cellule(3, 3));
		jeu.ajouterCellule(new Cellule(4, 4));
		String correct = "|*| | | | |\n| |*| | | |\n| | |*| | |\n| | | |*| |\n| | | | |*|";
		org.junit.Assert.assertEquals(correct, Output.display(jeu));
	}


}
