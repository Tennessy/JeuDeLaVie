package l2info.test.jeuDeLaVie;

import l2info.jeuDeLaVie.CellulePotentielle;

import org.junit.Before;
import org.junit.Test;

public class TestCellulePotentielle {

	protected CellulePotentielle celP;

	@Before
	public void test() {
		celP = new CellulePotentielle(0, 0, true, 1);
	}

	//Test l'existance d'une Cellule.
	@Test
	public void testExists() {
		org.junit.Assert.assertEquals(true,celP.exists());
	}
	
	//Test la definition de l'existance d'une Cellule.
	@Test
	public void testSetExist() {
		this.celP.setExist(false);
		org.junit.Assert.assertEquals(false,celP.exists());
		this.celP.setExist(true);
		org.junit.Assert.assertEquals(true,celP.exists());
	}
	
	//Test l'ajout d'un voisin à une Cellule.
	@Test
	public void testAjouterVoisin() {
		celP.ajouterVoisin();
		org.junit.Assert.assertEquals(2,celP.getNbVoisin());
		celP.ajouterVoisin();
		celP.ajouterVoisin();
		org.junit.Assert.assertEquals(4,celP.getNbVoisin());
	}
	
	//Test l'obtention du nombre de voisin d'une cellule.
	@Test
	public void testGetNbVoisin() {
		org.junit.Assert.assertEquals(1,celP.getNbVoisin());
		CellulePotentielle a=new CellulePotentielle(3,9,true, 1);
		org.junit.Assert.assertEquals(1,a.getNbVoisin());
	}
	
	//Test l'egalité de deux CellulePotentielle.
	@Test
	public void testEquals() {
		CellulePotentielle a=new CellulePotentielle(3,9,true, 1);
		CellulePotentielle b=new CellulePotentielle(0,0,true, 1);
		org.junit.Assert.assertEquals(true,b.equals(celP));
		org.junit.Assert.assertEquals(false,a.equals(celP));
	}

}
