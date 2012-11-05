import java.util.Scanner;
import java.util.Random;

public class Columns{

	static void writeMenu(){	//Menu

		System.out.println("Seleccione uma opcao: ");
		System.out.println("1 - Introduzir linha ");
		System.out.println("2 - Gerar coluna ");
		System.out.println("3 - Sair do programa ");
	}

	static void option1(Scanner input){

		System.out.println("Insira uma linha:");

		String line;

		line = input.next();

		while(line.length() != 12){	// Enquanto o utilizador nao inserir 12 caracteres o programa nao avanca

			System.out.println("A linha tem " + line.length() +" caractere(s). Deve ter 12.");

			System.out.println("Insira nova linha:");

			line=input.nextLine();
		}	

		int exclam = 0, arroba = 0, cardinal = 0, cifra = 0, percent = 0, space = 0;

		if(line.length() == 12){ //quando o programa tiver os 12 caracteres

			for(int position = 0; position < line.length(); position ++){ //executa ciclo que indica qual o caracter que esta em cada posicao

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
				default:
					System.out.println("Caracter(s) invalido(s)");
					System.out.println("Insira nova linha:");

					line=input.nextLine();	
				}
			}

			if(exclam >= 3)
				line = line.replace('!', '_');
			if(arroba >= 3)
				line = line.replace('@', '_');
			if(cardinal >= 3)
				line = line.replace('#', '_');
			if(cifra >= 3)
				line = line.replace('$', '_');
			if(percent >= 3)
				line = line.replace('%', '_');

			System.out.println(line);
		}

		else{

			for(int position = 0; position < line.length(); position ++){ //executa ciclo que indica qual o caracter que esta em cada posicao

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
				default:
					System.out.println("Caracter(s) invalido(s)");
					System.out.println("Insira nova linha:");

					line=input.nextLine();	
				}
			}


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

		System.out.println();

	}

	static void option2(Scanner input){
		
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

		System.out.println("A coluna gerada e:");
		System.out.println(coluna);
		System.out.println("Realizar 0, 1 ou 2 permutacoes?");

		int perm = input.nextInt();

		if(input.hasNextInt()){ //caso o digito inserido seja um inteiro continua a executar

			if(perm == 0){

				System.out.println(coluna);
			}

			else if (perm == 1 || perm == 2){	//caso escolha opcao 2

				permutacao(coluna,perm);
			}

			else
				System.out.println("Introduza uma das opcoes validas!");
			input.nextLine();
		}
		if 
			System.out.println("Introduza uma das opcoes validas!");
		input.nextLine();

		System.out.println();
		writeMenu ();

	}
	

	static void permutacao(String coluna, int perm){

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
		System.out.println(newCol1);
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
	System.out.println(newCol2);
	}
	}

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

				else if (option == 2){	//caso escolha opcao 2

					option2(input);
				}

				else if (option != 3){	//caso escolha opcao 3

					System.out.println("Introduza uma das opcoes validas!");

				}
			}

			else{//caso o digito inserido nao seja um inteiro emite uma mensagem de erro e pede ao utilizador que insira uma opcao valida

				input.nextLine();
				System.out.println("Introduza uma das opcoes validas!");

			}
		}

		while (option != 3);	//opcao Sair

	}
}
