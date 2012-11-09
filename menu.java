/*
* @author: António Pedras Nr:       ; Márcio Moreira Nr:41972
*/
import java.util.Scanner;
import java.util.Random;

public class menu{
/*
* @param    - String Lenght e CharAt para fazer verificacao caracter a caracter na linha as sequencias
* @requires	- 3 ou mais caracteres iguais contiguos
* @return   - c Esta função retorna a sequencia de caracteres contiguos a eliminar da linha introduzida
*/
	static void toDelete(String line){

		int i = 0;
		char c;
		int tam = line.length();

		c = line.charAt(i);

		while (i<tam) {
			int count = 1;
			i++;

			while (i < tam && (line.charAt(i)) == c) {
				count++;
				i++;

				if(count>=3);
				System.out.println("Sequencia a eliminar: "+line.charAt(i));	
			}
		}
	}
/*
* Retorna um print geral do menu que inicia o programa as vezes necessárias que forem requisitadas
*
* @param    - Nada
* @requires	- Nada
* @return   - Nada
*/
	static void writeMenu(){	//Menu

		System.out.println("Seleccione uma opÁ„o: ");
		System.out.println();
		System.out.println("1 ñ Introduzir linha ");
		System.out.println("2 ñ Gerar coluna ");
		System.out.println("3 ñ Sair do programa ");
	}
/*
* @param    - Scanner para introduzir a linha, String Lenght e CharAt para fazer verificacao caracter a 
*             caracter na linha as sequencias
* @requires	- 3 ou mais caracteres iguais contiguos
* @return   -  maior, c, Esta funcao retorna todas as operacoes em torno da primeira opcao. Introduzir 
*             linha de caracteres e verificar
*             a mesma e finalmente anuncia as sequencias de caracteres a eliminar (importa a funcao 
*             toDelete para o efectuar)
*/
	static void option1(Scanner input){		//opcao 1 do menu principal

		System.out.println("Insira uma linha:");

		String line;

		line = input.next();

		while(line.length() != 23){	// Enquanto o utilizador nao inserir 12 caracteres separados por virgulas o programa nao avanÁa

			System.out.println("Erro! A linha devera ter 12 caracteres separados por virgulas");

			System.out.println("Insira nova linha:");

			line=input.next();
		}	

		int exclam = 0, arroba = 0, cardinal = 0, cifra = 0, percent = 0, space = 0;

		if(line.length() == 23){ //quando o programa tiver os 12 caracteres
			
			for(int position = 1; position < (line.length()-1); position +=2){ //executa ciclo que indica qual o caracter que esta em cada posiÁao

				while(line.charAt(position) != ','){

					System.out.println();

					System.out.println("Os caracteres devem ser separados por virgulas");

					System.out.println("Insira nova linha:");

					line=input.next();
				}
				
			}

			for(int position = 0; position < line.length(); position +=2){ //executa ciclo que indica qual o caracter que esta em cada posiÁao

				while(line.charAt(position) != '!' || line.charAt(position) != '@' || line.charAt(position) != '#'|| line.charAt(position) != '$'|| line.charAt(position) != '%'|| line.charAt(position) != '_'){

					System.out.println();
					
					System.out.println("Caracter(s) inv·lido(s)");
					
					System.out.println("Insira nova linha:");

					line=input.nextLine();

				}
				
				switch (line.charAt(position)){

				case '!': exclam ++;
				break;
				case '@': arroba ++;
				break;
				case '#': cardinal ++;
				break;
				case '$': cifra ++;
				break;
				case '%': percent ++;
				break;
				case '_': space ++;
				break;
				
				}
			}

			System.out.println();

			int maior = Math.max(exclam , arroba);
			maior = Math.max(maior , cardinal);
			maior = Math.max(maior , cifra);
			maior = Math.max(maior , percent);

			if( space == 12)
				System.out.println("Nao foram inseridos simbolos");
			else if(maior == exclam)
				System.out.println("Caracter: ! ; Ocorrencia: " + maior);
			else if(maior == arroba)
				System.out.println("Caracter: @ ; Ocorrencia: " + maior);
			else if(maior == cardinal)
				System.out.println("Caracter: # ; Ocorrencia: " + maior);
			else if(maior == cifra)
				System.out.println("Caracter: $ ; Ocorrencia: " + maior);
			else if(maior == percent)
				System.out.println("Caracter: % ; Ocorrencia: " + maior);

		}
		
		toDelete(line);
		System.out.println();
	}
/*
* @param    - Scanner para introduzir os valores das Permutacoes, Random , StringBuilder, CharAt
* @requires	- Opcao seja 0, 1, ou 2 permutacoes e que a coluna seja gerada após escolher essa opcao no menu inicial
* @return   - Perm, Coluna, Esta funcao retorna 0, 1 ou 2 permutaçoes sobre a linha de 3 caracteres gerada 
*             Importa a funcao permutacao para retornar no Print as permutacoes.
*/
	static void option2(Scanner input){		//opcao 2 do menu principal

		String conjunto = "!@#$%";

		StringBuilder sb = new StringBuilder();

		Random gerador = new Random();

		for(int i = 1; i <= 3 ; i++){

			int n = gerador.nextInt(5);

			char symbol = conjunto.charAt(n);

			sb.append(symbol);

			if(i<=2)	//imprime virgulas entre cada simbolo
				sb.append(',');

		}

		String coluna = sb.toString();

		System.out.println("Coluna gerada:");
		System.out.println(coluna);
		System.out.println();

		int perm = 9;

		do{

			System.out.println("Realizar 0, 1 ou 2 permutacoes?");

			input.nextLine();

			if(input.hasNextInt()){ //caso o digito inserido seja um inteiro continua a executar
				perm = input.nextInt();

				if(perm == 0){
					System.out.println("Com 0 permutacoes: " + coluna);
				}

				else if(perm == 1){

					permutacao(coluna,perm);
				}

				else if (perm == 2){	//caso escolha opÁao 2

					permutacao(coluna,perm);
				}

				else if (perm != 2){	//caso escolha opÁao 3

					System.out.println();
					System.out.println("Introduza uma das opcoes validas!");

				}
			}

			else{

				System.out.println();
				System.out.println("Introduza uma das opcoes validas!");

			}


		}
		while (perm != 0 && perm != 1 && perm != 2);



	}
/*
* @param     - StringBuilder , String, ambas para construir a linha e desconstrui-la e poder manipular caracter a 
*              caracter
* @requires	 - Opcao seja 0, 1, ou 2 permutacoes e que a coluna seja gerada após escolher essa opcao no menu inicial
* @return    - perm, newCol1 newCol2, A funcao retorna 0, 1, ou 2 permutacoes conforme o utilizador pede e retorna a 
*              nova linha. 
*/
	static void permutacao(String coluna, int perm){	//faz as permutacoes da coluna

		StringBuilder sb = new StringBuilder();

		if(perm == 1){
			for(int i = 0; i <=3; i++){

				if(i==0)
					sb.append(coluna.charAt(4));

				if(i==1)
					sb.append(coluna.charAt(0));

				if(i==2)
					sb.append(coluna.charAt(2));


			}
			String newCol1 = sb.toString();
			System.out.println("Com 1 permutacao: " + newCol1);
		}
		else{
			for(int i = 0; i <=3; i++){

				if(i==0)
					sb.append(coluna.charAt(2));

				if(i==1)
					sb.append(coluna.charAt(4));

				if(i==2)
					sb.append(coluna.charAt(0));

			}
			String newCol2 = sb.toString();
			System.out.println("Com 2 permutacoes: " + newCol2);
		}
	}
/*
* O seguinte programa é uma adaptacao do jogo Columns onde em vez de se trabalhar em coluna, manipulamos uma linha
* de 12 caracteres especificos introduzidos pelo utilizador. O objectivo e apos gerar a linha o programa, anunciar 
* se existem conjuntos de 3 ou mais caracteres identicos colocados de forma contigua, caracteres esses que seriam
* anulados por um espaco em branco.
* Apos esta analise e dada a oportunidade ao utilizador de gerar atraves do programa de forma aleatoria, uma linha 
* de 3 caracteres que por sua vez tem a hipotese de permutar o posicionamento dos mesmos 0, 1 ou 2 vezes.
*/
	public static void main (String[]args){

		Scanner input = new Scanner(System.in);

		int option = 0;

		do{


			writeMenu();

			if(input.hasNextInt()){ //caso o digito inserido seja um inteiro continua a executar
				option = input.nextInt();
				if(option == 1){

					option1(input);
				}

				else if (option == 2){	//caso escolha opÁao 2

					option2(input);
				}

				else if (option != 3){	//caso escolha opÁao 3

					System.out.println("Introduza uma das opcoes validas!");

				}
			}

			else{//caso o digito inserido nao seja um inteiro emite uma mensagem de erro e pede ao utilizador que insira uma opÁao valida

				input.nextLine();
				System.out.println("Introduza uma das opcoes validas!");

			}
			System.out.println();
		}

		while (option != 3);	// Sair

	}
}
