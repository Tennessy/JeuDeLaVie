import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.Timer;


public class Programme2 {
	static public void main(String[] args) {
		final TableCellule t = new TableCellule();
		final TableCellule t2 = new TableCellule();
		final boolean boucle = false;
		Scanner sc = new Scanner(System.in);
		String saisi = null;
//		Stable	
//		t2.addCel(new Cellule(0,0));
//		t2.addCel(new Cellule(1,0));
//		t2.addCel(new Cellule(1,1));
//		t2.addCel(new Cellule(5,3));
//		t2.addCel(new Cellule(5,4));
//		t2.addCel(new Cellule(4,3));
//		t2.addCel(new Cellule(6,3));
//		t2.addCel(new Cellule(-1,-1));
//		
//		t2.nextStep();
//		
//		t.addCel(new Cellule(0,0));
//		t.addCel(new Cellule(1,0));
//		t.addCel(new Cellule(1,1));
//		t.addCel(new Cellule(5,3));
//		t.addCel(new Cellule(5,4));
//		t.addCel(new Cellule(4,3));
//		t.addCel(new Cellule(6,3));
//		t.addCel(new Cellule(-1,-1));
		
//		Vaisseau
//		t.addCel(new Cellule(1,0));
//		t.addCel(new Cellule(2,1));
//		t.addCel(new Cellule(0,2));
//		t.addCel(new Cellule(1,2));
//		t.addCel(new Cellule(2,2));
		
//		Vaisseau
//		t.addCel(new Cellule(0,0));
//		t.addCel(new Cellule(3,0));
//		t.addCel(new Cellule(4,1));
//		t.addCel(new Cellule(0,2));
//		t.addCel(new Cellule(4,2));
//		t.addCel(new Cellule(1,3));
//		t.addCel(new Cellule(2,3));
//		t.addCel(new Cellule(3,3));
//		t.addCel(new Cellule(4,3));
		
		t.addCel(new Cellule(2,0));
		t.addCel(new Cellule(3,0));
		t.addCel(new Cellule(0,1));
		t.addCel(new Cellule(5,1));
		t.addCel(new Cellule(6,2));
		t.addCel(new Cellule(0,3));
		t.addCel(new Cellule(6,3));
		t.addCel(new Cellule(1,4));
		t.addCel(new Cellule(2,4));
		t.addCel(new Cellule(3,4));
		t.addCel(new Cellule(4,4));
		t.addCel(new Cellule(5,4));
		t.addCel(new Cellule(6,4));
		
		for(Cellule c : t.cellules){
			t2.addCel(c);
		}
		t2.nextStep();
		Timer time = new Timer(1000, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.print((char)Event.ESCAPE + "8");
				System.out.print((char)Event.ESCAPE + "[J");
				t.nextStep();
				t.afficherCel();
				System.out.println();
				System.out.println();
				t2.nextStep();
				t2.nextStep();
				t2.afficherCel();
				
				if(t.cellules.equals(t2.cellules)){
					System.out.println("\n\nBoucle!");
					Timer timer2 = (Timer)arg0.getSource();
					timer2.stop();
				}
				else if(checkTranslation(t, t2)){
					System.out.println("\nBoucle translation ! ");
				}
				
			}
			
		});
		time.start();
		
		try {
		     System.in.read();
		}
		catch (IOException e){}
		time.stop();
	}
	
	public static boolean checkTranslation(TableCellule t1, TableCellule t2){
		Iterator<Cellule> it1= t1.cellules.iterator();
		Iterator<Cellule> it2 = t2.cellules.iterator();
		boolean equal = true;
		if(t1.cellules.size() == t2.cellules.size()){
			int diffX = it1.next().getX() - it2.next().getX();
			int diffY = it1.next().getY() - it2.next().getY();
			
			while(it1.hasNext()){
				Cellule c1 = it1.next();
				Cellule c2 = it2.next();
				if(c2.getX()+diffX != c1.getX() || c2.getY()+diffY != c1.getY()){
					equal = false;
				}
			}
			return equal;
		}
		else{
			return false;
		}
		
		
	}
}