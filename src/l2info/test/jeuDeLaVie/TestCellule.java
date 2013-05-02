package l2info.test.jeuDeLaVie;

import static org.junit.Assert.*;
import l2info.jeuDeLaVie.Cellule;
import org.junit.Test;

public class TestCellule {
	protected Cellule cel;

	@Test
	public void testGetX() {
		cel=new Cellule(-2,5);
		org.junit.Assert.assertEquals(-2,cel.getX());
	}

	@Test
	public void testSetX() {
		cel=new Cellule(-2,6);
		cel.setX(0);
		org.junit.Assert.assertEquals(0, cel.getX());
	}
	
	@Test
	public void testGetY() {
		cel=new Cellule(-2,5);
		org.junit.Assert.assertEquals(5,cel.getY());
	}
	
	@Test
	public void testSetY() {
		cel=new Cellule(-2,6);
		cel.setY(0);
		org.junit.Assert.assertEquals(0, cel.getY());
	}
	
	@Test
	public void testCompareTo() {
		cel=new Cellule(2,3);
		Cellule cel2=new Cellule(1,8);
		
		org.junit.Assert.assertEquals(cel.getY()-cel2.getY(),cel.compareTo(cel2));
	}
	
	@Test
	public void testEqualsObject() {
		cel=new Cellule(2,3);
		Cellule cel2=new Cellule(2,3);
		assertFalse("Differents",cel.equals(new Cellule(0,0)));
		assertTrue("Identiques",cel.equals(cel2));
		org.junit.Assert.assertEquals(cel2.compareTo(cel),cel.compareTo(cel2));
	}

}
