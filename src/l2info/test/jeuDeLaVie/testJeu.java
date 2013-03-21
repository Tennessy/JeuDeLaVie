package l2info.test.jeuDeLaVie;

import java.util.ArrayList;
import java.util.Arrays;

import l2info.jeuDeLaVie.Cellule;
import l2info.jeuDeLaVie.Jeu;

import org.junit.Before;
import org.junit.Test;

public class testJeu {

	protected Jeu jeu;
	
	@Before
	public void setUp(){
		this.jeu = new Jeu();
	}
	
	@Test
	public void testAjouterCellule(){
		this.jeu.ajouterCellule(new Cellule(0,0));
		this.jeu.ajouterCellule(new Cellule(1,1));
		org.junit.Assert.assertEquals(jeu.getListeCellule(), Arrays.asList(new Cellule(0,0), new Cellule(1,1)));
	}
	
	@Test
	public void testCalculer() {
		this.jeu = new Jeu();
		jeu.ajouterCellule(new Cellule(1,1));
		jeu.ajouterCellule(new Cellule(3,2));
		jeu.ajouterCellule(new Cellule(2,3));
		jeu.ajouterCellule(new Cellule(3,3));
		jeu.calculer();
		org.junit.Assert.assertEquals(jeu.getListeCellule(), Arrays.asList(new Cellule(2,2)));
	}

}
