import java.util.Random;
import java.util.Scanner;

/**
 * 
 * Classe define um programa que gera automaticamente ou pede ao utilizador uma 
 * grelha de Sudoku e pode efectuar varias operacoes na grelha.
 * 
 * @author Márcio Moreira 41972 - Pedro Mena 51024
 * 
 */
public class Sudoku2 {

	public static void main(String[] args){

		final int N_DIMENSAO = 9;

		Scanner input = new Scanner(System.in);

		int [][] m = new int [ N_DIMENSAO ] [ N_DIMENSAO ] ;
		int escolha;
		boolean valido = false;

		GQ(m);
		printMatriz(m);

		do{

			System.out.println("Escolha opcao:");
			System.out.println("        0 - Sair");
			System.out.println("        1 – Aplicar permutacao de dois números");
			System.out.println("        2 – Aplicar permutacao de duas linhas de uma mesma faixa horizontal");
			System.out.println("        3 – Aplicar permutacao de duas colunas de uma mesma faixa vertical");
			System.out.println("        4 – Aplicar permutacao de duas faixas horizontais");
			System.out.println("        5 – Aplicar permutacao de duas faixas verticais");
			System.out.println("        6 – Aplicar reflexao horizontal");
			System.out.println("        7 – Aplicar reflexao vertical");
			System.out.println("        8 – Indicar quadricula");
			System.out.print("> ");

			escolha = input.nextInt();

			int linha1 , linha2;

			switch(escolha){

			case 0: 			//termina programa

				valido = true;
				break;

			case 1: 			//Permutacao de dois numeros
				permutaDoisNumeros(m);
				printMatriz(m);
				break;

			case 2: 			//Permutacao de duas linhas
				System.out.print("Indique os dois alvos da permutacao > ");


				linha1 = input.nextInt();
				linha2 = input.nextInt();

				if( linha2-linha1 < 3 && linha2-linha1 > -3 && linha2 <= 9 && linha1 >= 1 && linha2 != linha1){
					permutaDuasLinhas(m,linha1-1,linha2-1);
					printMatriz(m);
				}
				else{
					System.out.println("Alvos incorretos para a transformacao escolhida.");
				}
				break;

			case 3: 		//Permutacao de duas colunas
				System.out.print("Indique os dois alvos da permutacao > ");


				linha1 = input.nextInt();
				linha2 = input.nextInt();

				if( linha2-linha1 < 3 && linha2-linha1 > -3 && linha2 <= 9 && linha1 >= 1 && linha2 != linha1){
					permutaDuasColunas(m,linha1-1,linha2-1);
					printMatriz(m);
				}
				else{
					System.out.println("Alvos incorretos para a transformacao escolhida.");
				}
				break;

			case 4: 		//Permutacao de duas faixas horizontais
				System.out.print("Indique os dois alvos da permutacao > ");


				linha1 = input.nextInt();
				linha2 = input.nextInt();

				if(linha2 <= 3 && linha1 >= 1 && linha2 != linha1){		
					permutaDuasFaixasHorizontais(m, linha1-1, linha2-1);
					printMatriz(m);
				}
				else{
					System.out.println("Alvos incorretos para a transformacao escolhida.");
				}
				break;

			case 5: 		//Permutacao de duas faixas verticais
				System.out.print("Indique os dois alvos da permutacao > ");


				linha1 = input.nextInt();
				linha2 = input.nextInt();

				if(linha2 <= 3 && linha1 >= 1 && linha2 != linha1){
					permutaDuasFaixasVerticais(m, linha1-1, linha2-1);
					printMatriz(m);
				}
				else{
					System.out.println("Alvos incorretos para a transformacao escolhida.");
				}
				break;

			case 6: 		//Reflexao horizontal

				refletirHorizontal(m);
				printMatriz(m);

				break;

			case 7: 		//Reflexao Vertical

				refletirVertical(m);
				printMatriz(m);

				break;

			case 8:		 //O utilizador indica a quadricula
				System.out.println("Insira os valores da quadricula > ");


				getValores(m, input);
				valido = isMatrizValida(m);
				//printMatriz(m);

				if( valido ){
					System.out.println("A quadricula correta.");
				}
				else{

					System.out.println("A quadricula indicada e incorreta.");
				}

				break;

			default:

				System.out.println("O valor introduzido não corresponde a nenhuma das opcoes.");
				break;

			}

		}
		while (escolha != 0);
	}


	/**
	 * Reflecte uma grelha de Sudoku verticalmente, ou seja, 
	 * o conteúdo da coluna i passa a ser o da coluna 10-i e vice-versa, 
	 * para i=1,..,4 
	 * @param m
	 */
	public static void refletirVertical(int[][] m){

		for ( int i = 0 ; i < m.length/2 ; i++){

			permutaDuasColunas( m, i, ( m.length-1 )-i );
		}	
	}


	/**
	 * Este método reflecte uma grelha de Sudoku horizontalmente, ou seja, 
	 * o conteúdo da linha i passa a ser o da linha 10-i e vice-versa, 
	 * para i=1,..,4 
	 * @param m
	 */
	public static void refletirHorizontal(int[][] m) {

		for ( int i = 0 ; i < m.length/2 ; i++){

			permutaDuasLinhas(m, i, ( m.length-1 )-i );
		}	
	}


	/**
	 * Este método troca uma faixa vertical (conjunto de 3 colunas) por 
	 * outra numa grelha de Sudoku.
	 * @param m - A matriz na qual as duas faixas se situam
	 * @param f1 - A primeira faixa a ser trocada
	 * @param f2 - A segunda faixa a ser trocada
	 */
	public static void permutaDuasFaixasVerticais(int[][] m, int f1, int f2) {

		final int N_COLUNAS = m.length/3;

		for( int i = 0 ; i < N_COLUNAS ; i++ ){

			permutaDuasColunas( m, f1 * N_COLUNAS + i , f2 * N_COLUNAS + i );
		}   
	}


	/**
	 * Este método troca uma faixa horizontal (conjunto de 3 linhas) por 
	 * outra numa grelha de Sudoku.
	 * @param m - A matriz na qual as duas faixas se situam
	 * @param f1 - A primeira faixa a ser trocada
	 * @param f2 - A segunda faixa a ser trocada
	 */
	public static void permutaDuasFaixasHorizontais(int[][] m, int f1, int f2) {

		final int N_LINHAS = m.length/3;

		for( int i = 0; i < N_LINHAS ; i++ ){

			permutaDuasLinhas( m, f1 * N_LINHAS + i , f2 * N_LINHAS + i );

		}
	}


	/**
	 * Este método troca uma coluna por outra numa grelha de Sudoku.
	 * @param m - A matriz na qual as duas colunas se situam
	 * @param c1 - A primeira coluna a ser trocada
	 * @param c2 - A segunda coluna a ser trocada
	 */
	public static void permutaDuasColunas(int[][] m,int c1, int c2) {

		int temp;

		for( int i = 0; i < m.length ; i++){

			temp = m[i][c1];

			m[i][c1] = m[i][c2];

			m[i][c2] = temp;

		}
	}


	/**
	 * Este método troca uma linha por outra numa grelha de Sudoku.
	 * @param m - A matriz na qual as duas linhas se situam
	 * @param l1 - A primeira linha a ser trocada
	 * @param l2 - A segunda linha a ser trocada
	 */
	public static void permutaDuasLinhas(int[][] m, int l1, int l2){

		int[] temp = m[l1];

		m[l1] = m[l2];

		m[l2] = temp;

	}


	/**
	 * Gera dois números entre 1 e 9 aleatoriamente e troca as 
	 * ocorrencias do primeiro número pelo do segundo e vice-versa numa grelha de Sudoku. 
	 * @param m - A matriz sobre a qual a permutação vai fazer efeito
	 */
	public static void permutaDoisNumeros(int[][] m) {

		Random r = new Random();

		int n1, n2;

		n1 = r.nextInt( m.length ) + 1;

		n2 = r.nextInt( m.length ) + 1;

		for( int i=0;i<m.length;i++){

			for( int j = 0; j < m[i].length; j++){

				if( m[i][j] == n1){
					m[i][j] = n2;
				}
				else if (m[i][j] == n2){

					m[i][j] = n1;
				}
			}
		}
	}


	/**
	 * Coloca numa matriz 81 valores, separados em 9 linhas e 9 colunas, 
	 * os quais estão de acordo com as regras do Sudoku. Estes valores são 
	 * gerados automaticamente.
	 * @param GQ - A matriz sobre a qual vai ser efectuada a operação. 
	 * @requires Numero de linhas = Numero de colunas
	 */
	public static void GQ( int[][] GQ ){

		for ( int i=0; i < GQ.length; i++){

			for ( int j = 0 ; j < GQ.length; j++){

				GQ[i][j] = (i/3 + 3*(i % 3) + j) % 9 + 1;

			}
		}
	}


	/**
	 * Imprime a grelha de Sudoku actual
	 * @param m - A matriz que contém o Sudoku
	 */
	public static void printMatriz(int[][] m){

		System.out.println("A quadrícula corrente: ");

		for( int i = 0 ; i < m.length ; i++){

			if( i % 3 == 0 ){

				System.out.println("-------------------------");
			}
			for( int j = 0 ; j < m[i].length ; j++){

				if( j % 3 == 0){

					System.out.print("| ");
				}
				System.out.print( m[i][j] + " " );
			}
			System.out.println("|");
		}
		System.out.println("-------------------------");
	}


	/**
	 * Este método pede ao utilizador 81 valores para colocar numa grelha de Sudoku.
	 * @param m - A matriz que vai guardar o Sudoku
	 * @param input - um Scanner que vai ser utilizado para ler os valores do teclado.
	 */
	public static void getValores(int[][] m, Scanner input){

		for( int i = 0; i < m.length; i++){

			for( int j = 0; j < m.length; j++){

				m[i][j] = input.nextInt();

			}
		}
	}


	/**
	 * Este método testa se a grelha é válida, ou seja, está de acordo com as regras do Sudoku.
	 * @param m - A matriz a testar
	 * @return true se for válida, false caso contrário
	 */
	public static boolean isMatrizValida( int[][] m ) {

		boolean isValid = true;
		int i = 0;

		while ( isValid && i < m.length ){

			//Verifica a linha i, a coluna i e quadrado i
			if( isLinhaValida( m[i] ) == false || isColunaValida( m,i ) == false || 
					isQuadradoValido( m,i ) == false){

				return false;
			}

			i++;
		}
		return isValid;
	}


	/**
	 * Este método verifica se um quadrado está de acordo com as regras do Sudoku.
	 * @param m - A matriz na qual o quadrado a testar está inserido
	 * @param valor - Indica qual o quadrado a verificar
	 * @return true se for válido, false caso contrário
	 */
	public static boolean isQuadradoValido(int[][] m, int valor){

		int soma = 0;
		int produto = 1;

		for ( int i = 0 ; i < m.length/3 ; i++){

			for(int j = 0 ; j < m[i].length/3 ; j++){

				soma += m[i][j];
				produto *= m[i][j];

			}
		}

		return (soma == 45 && produto == 362880);
	}


	/**
	 * Este método verifica se uma coluna está de acordo com as regras do Sudoku.
	 * @param m - A matriz na qual a coluna a testar está inserida
	 * @param valor - Indica qual a coluna a verificar
	 * @return true se for válida, false caso contrário
	 */
	public static boolean isColunaValida(int[][] m, int valor) {

		int soma = 0;
		int produto = 1;

		for ( int i = 0; i < m.length; i++){

			soma += m[i][valor];
			produto *= m[i][valor];

		}	
		return (soma == 45 && produto == 362880);
	}


	/**
	 * Metodo verifica se a linha esta de acordo com as regras do Sudoku
	 * @param v - O vector que contém a linha a ser testada
	 * @return true se for válida, false caso contrário
	 */
	public static boolean isLinhaValida(int[] v){

		int soma = 0;
		int produto = 1;

		for ( int i = 0; i < v.length; i++){

			soma += v[i];
			produto *= v[i];

		}	
		return (soma==45 && produto==362880);
	}

}