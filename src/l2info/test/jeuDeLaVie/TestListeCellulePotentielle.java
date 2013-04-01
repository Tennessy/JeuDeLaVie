package l2info.test.jeuDeLaVie;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import l2info.jeuDeLaVie.Cellule;
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

	@Test
	public void testAjouterElement() {
		assertNull(lis.tete());
		lis.ajouterElement(new CellulePotentielle(0,0,true));
		org.junit.Assert.assertEquals(new CellulePotentielle(0,0,true),lis.tete());
		org.junit.Assert.assertEquals(null,lis.queue());
		assertNotNull(lis.tete()==null);
		lis.ajouterElement(new CellulePotentielle(0,7,true));
		org.junit.Assert.assertEquals(new CellulePotentielle(0,0,true),lis.tete());
		org.junit.Assert.assertEquals(new CellulePotentielle(0,7,true),lis.queue().tete());
	}

	
	@Test
	public void testInsertElementAfter() {
		CellulePotentielle a,b,c;
		a=new CellulePotentielle(0,1,true);
		b=new CellulePotentielle(1,2,false);
		c=new CellulePotentielle(0,9,true);
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
