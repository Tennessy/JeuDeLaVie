import java.util.Scanner;


public class Programme2 {
	static public void main(String[] args) {
		TableCellule t = new TableCellule();
		Scanner sc = new Scanner(System.in);
		String saisi = null;
		
		t.addCel(new Cellule(0,0));
		t.addCel(new Cellule(1,0));
		t.addCel(new Cellule(1,1));
		t.addCel(new Cellule(5,3));
		t.addCel(new Cellule(11,10));
		t.addCel(new Cellule(4,3));
		t.addCel(new Cellule(-1,-1));
		
		t.afficherCel();
		sc.nextLine();
		
		do{
			t.nextStep();
			t.afficherCel();
			saisi = sc.nextLine();
		}while(saisi != "q");
	}
}