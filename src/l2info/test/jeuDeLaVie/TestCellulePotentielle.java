package l2info.test.jeuDeLaVie;

import static org.junit.Assert.*;
import l2info.jeuDeLaVie.CellulePotentielle;

import org.junit.Before;
import org.junit.Test;

public class TestCellulePotentielle {

	protected CellulePotentielle celP;

	@Before
	public void test() {
		celP = new CellulePotentielle(0, 0, true);
	}

	@Test
	public void testExists() {
		org.junit.Assert.assertEquals(true,celP.exists());
	}
	
	@Test
	public void testSetExist() {
		this.celP.setExist(false);
		org.junit.Assert.assertEquals(false,celP.exists());
		this.celP.setExist(true);
		org.junit.Assert.assertEquals(true,celP.exists());
	}
	
	@Test
	public void testAjouterVoisin() {
		celP.ajouterVoisin();
		org.junit.Assert.assertEquals(2,celP.getNbVoisin());
		celP.ajouterVoisin();
		celP.ajouterVoisin();
		org.junit.Assert.assertEquals(4,celP.getNbVoisin());
	}
	
	@Test
	public void testGetNbVoisin() {
		org.junit.Assert.assertEquals(1,celP.getNbVoisin());
		CellulePotentielle a=new CellulePotentielle(3,9,true);
		org.junit.Assert.assertEquals(1,a.getNbVoisin());
	}
	
	@Test
	public void testEquals() {
		CellulePotentielle a=new CellulePotentielle(3,9,true);
		CellulePotentielle b=new CellulePotentielle(0,0,true);
		//assertTrue((b.getX()==celP.getX())&&(b.getY()==celP.getY()));
		//assertFalse((b.getX()==a.getX())&&(a.getY()==b.getY()));
		org.junit.Assert.assertEquals(true,b.equals(celP));
		org.junit.Assert.assertEquals(false,a.equals(celP));
	}

}
