/* Escreva um programa que pede ao utilizador um inteiro e o guarda numa variável n e que de seguida escreve no ecrã
* os quadrados dos primeiros n números pares.
* Exemplo: se o utilizador introduzir o valor 3, o programa escreverá no ecrã os valores 4, 16 e 36.
* Considere que o utilizador não se engana quando lhe são pedidos valores inteiros, ou seja, não precisa de validar o
* valor introduzido pelo utilizador.
*/

import java.util.Scanner;


public class Exerc{

	public static void main (String[]args){

		int num;

		Scanner input = new Scanner(System.in);
		System.out.println("Introduza um valor um superior: ");
		num = input.nextInt();
		
		for(int i = 1; i<=num; i++){
				System.out.println(i*i*4);
			}
			
	
	}	
}

