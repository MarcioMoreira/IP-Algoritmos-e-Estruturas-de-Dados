//Faça um programa que peça ao utilizador dois numeros inf e sup, 
//que depois imprima todos os impares ao quadrado entre inf e sup 
//(inf exclusive , sup inclusive)


import java.util.Scanner;

public class SupInf{
	
	public static void main (String[]args){

	int sup;
	int inf;
	Scanner input = new Scanner(System.in);
	System.out.println("Introduza um valor um superior: ");
	sup = input.nextInt();
	System.out.println("Introduza um valor um inferior: ");
	inf = input.nextInt();
	
		if (inf%2==0){
			inf = inf+1;
		}	
		else {
			inf = inf+2;
		}	
		for (int i= inf ; i<=sup ; i = i+2){
			int quad = i*i*i;
			System.out.println(quad);
		}
	}
}	
