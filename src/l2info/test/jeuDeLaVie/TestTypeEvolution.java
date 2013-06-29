package l2info.test.jeuDeLaVie;

import java.io.IOException;
import java.util.ArrayList;

import l2info.inputOutput.jeuDeLaVie.Output;
import l2info.jeuDeLaVie.Cellule;
import l2info.jeuDeLaVie.Jeu;
import l2info.jeuDeLaVie.TypeEvolution;

import org.junit.Before;
import org.junit.Test;

public class TestTypeEvolution {

	protected Jeu jeu;
	protected TypeEvolution typeEvo;

	@Before
	public void setUp() {
		this.jeu = new Jeu();
		typeEvo = new TypeEvolution();
	}
	
	//Test le type Oscillateur.
	@Test
	public void testEvaluerOscillateur() {
		jeu.setListeCellule(new ArrayList<Cellule>());
		jeu.ajouterCellule(new Cellule(2, 2));
		jeu.ajouterCellule(new Cellule(3, 2));
		jeu.ajouterCellule(new Cellule(4, 2));
		jeu.setTypeMonde(Jeu.MONDE_NORMAL);
		typeEvo.calculerTypeEvolution(jeu, 10);
		org.junit.Assert.assertEquals(TypeEvolution.OSCILLATEUR,
				typeEvo.getType());
	}

	//Test le type Stable.
	@Test
	public void testEvaluerStable() {
		jeu.setListeCellule(new ArrayList<Cellule>());
		jeu.ajouterCellule(new Cellule(0, 0));
		jeu.ajouterCellule(new Cellule(1, 0));
		jeu.ajouterCellule(new Cellule(0, 1));
		jeu.ajouterCellule(new Cellule(1, 1));
		jeu.setTypeMonde(Jeu.MONDE_NORMAL);
		typeEvo.calculerTypeEvolution(jeu, 10);
		org.junit.Assert.assertEquals(TypeEvolution.STABLE,
				typeEvo.getType());
	}

	//Test le type Mort.
	@Test
	public void testEvaluerMort() {
		jeu.setListeCellule(new ArrayList<Cellule>());
		jeu.ajouterCellule(new Cellule(0, 0));
		jeu.ajouterCellule(new Cellule(1, 0));
		jeu.setTypeMonde(Jeu.MONDE_NORMAL);
		typeEvo.calculerTypeEvolution(jeu, 10);
		org.junit.Assert.assertEquals(TypeEvolution.MORT,
				typeEvo.getType());
	}

	//Test le type Vaisseau.
	@Test
	public void testEvaluerVaisseau() {
		jeu = new Jeu();
		jeu.ajouterCellule(new Cellule(1, 0));
		jeu.ajouterCellule(new Cellule(2, 1));
		jeu.ajouterCellule(new Cellule(0, 2));
		jeu.ajouterCellule(new Cellule(1, 2));
		jeu.ajouterCellule(new Cellule(2, 2));
		jeu.setTypeMonde(Jeu.MONDE_NORMAL);
		typeEvo.calculerTypeEvolution(jeu, 10);
		org.junit.Assert.assertEquals(TypeEvolution.VAISSEAU,
				typeEvo.getType());
	}
	
	//Test la generation du code HTML affichant les caracteristiques d'evolution de plusieurs jeux.
	@Test
	public void testHtml() throws IOException {
		TypeEvolution typeEvo2 = new TypeEvolution();
		jeu = new Jeu("test", new ArrayList<Cellule>(), 0, 0, 4, 4);
		jeu.ajouterCellule(new Cellule(0, 0));
		jeu.ajouterCellule(new Cellule(1, 1));
		jeu.ajouterCellule(new Cellule(2, 2));
		jeu.ajouterCellule(new Cellule(3, 3));
		jeu.ajouterCellule(new Cellule(4, 4));
		jeu.setTypeMonde(Jeu.MONDE_NORMAL);
		typeEvo.calculerTypeEvolution(jeu, 10);
		
		Jeu jeu2 = new Jeu("test2", new ArrayList<Cellule>(), 0, 0, 6, 6);
		jeu2.ajouterCellule(new Cellule(2, 0));
		jeu2.ajouterCellule(new Cellule(2, 3));
		jeu2.ajouterCellule(new Cellule(2, 4));
		jeu2.setTypeMonde(Jeu.MONDE_NORMAL);
		typeEvo2.calculerTypeEvolution(jeu2, 10);
		
		ArrayList<TypeEvolution> typeEvos = new ArrayList<TypeEvolution>();
		typeEvos.add(typeEvo);
		typeEvos.add(typeEvo2);
		org.junit.Assert
		.assertEquals(
				"<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\"><html><head><title>Resultats Jeu de la vie </title></head><body> <table border=\"1\"> <tr><th>Nom</th><th>type</th><th>Taille queue</th><th>Periode</th></tr><tr><td>test</td><td>Mort</td><td>3</td><td>1</td></tr><td>test2</td><td>Mort</td><td>1</td><td>1</td></tr></table> </html></body>"
,				Output.toHtml(typeEvos).toString());
	}


	//Test l'exactitude de la generation en mode frontiere.
	@Test
	public void testEvaluerMondeFrontiere() {
		jeu.setListeCellule(new ArrayList<Cellule>());
		jeu.ajouterCellule(new Cellule(0, 0));
		jeu.ajouterCellule(new Cellule(0, 1));
		jeu.ajouterCellule(new Cellule(0, 2));
		jeu.setTypeMonde(Jeu.MONDE_FRONTIERES);
		typeEvo.calculerTypeEvolution(jeu, 10);
		
		org.junit.Assert.assertEquals(TypeEvolution.MORT,typeEvo.getType());
	}


}
