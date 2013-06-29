package l2info.test.jeuDeLaVie;

import static org.junit.Assert.*;

import l2info.jeuDeLaVie.CellulePotentielle;
import l2info.jeuDeLaVie.ListeCellulePotentielle;

import org.junit.Before;
import org.junit.Test;

public class TestListeCellulePotentielle {

	protected ListeCellulePotentielle lis;

	@Before
	public void test() {
		lis = new ListeCellulePotentielle();
	}

	//Test l'ajout d'un element à la liste.
	@Test
	public void testAjouterElement() {
		assertNull(lis.tete());
		lis.ajouterElement(new CellulePotentielle(0,0,true, 1));
		org.junit.Assert.assertEquals(new CellulePotentielle(0,0,true, 1),lis.tete());
		org.junit.Assert.assertEquals(null,lis.queue());
		assertNotNull(lis.tete()==null);
		lis.ajouterElement(new CellulePotentielle(0,7,true, 1));
		org.junit.Assert.assertEquals(new CellulePotentielle(0,0,true, 1),lis.tete());
		org.junit.Assert.assertEquals(new CellulePotentielle(0,7,true, 1),lis.queue().tete());
	}

	//Test l'ajout d'un element à la liste, à la suite de l'element courant.
	@Test
	public void testInsertElementAfter() {
		CellulePotentielle a,b,c;
		a=new CellulePotentielle(0,1,true, 1);
		b=new CellulePotentielle(1,2,false, 1);
		c=new CellulePotentielle(0,9,true, 1);
		assertNull(lis.tete());
		lis.ajouterElement(a);
		assertNull( lis.queue());
		lis.insertElementAfter(b);
		org.junit.Assert.assertEquals(b,lis.queue().tete());
		//assertFalse(lis.queue()==null);
		assertNotNull("Erreur001",lis.queue());
		lis.insertElementAfter(c);
		org.junit.Assert.assertEquals(c,lis.queue().tete());
		
		
	}
}
