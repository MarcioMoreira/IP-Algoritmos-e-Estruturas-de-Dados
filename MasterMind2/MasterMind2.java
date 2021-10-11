import java.util.Random;
import java.util.Scanner;

/**
 * Classe MasterMind2 da 2a Fase do Projeto de IP da FCUL
 *
 * @author Marcio Moreira - 41972 | Nuno Fontes - 46413
 * @date Novembro 2019
 */
public class MasterMind2 {

	public static void main(String[] args) {

		Random ger = new Random(7);

		int nrColors = 5;
		int len = 4;
		int nrAttempts = 10;
		int[] gerPuzzle = randomPuzzle(nrColors, len, ger);
		int[][] board = new int[nrAttempts][gerPuzzle.length];

		System.out.println("Let’s start a MasterMind game!");
		System.out.println("Insert values between 1 and 5 to represent colors:");
		System.out.println("1 – white 2 – yellow 3 – red 4 – green 5 – blue \n");

		printBoardAndClues(gerPuzzle, board, nrAttempts);

	}

	/**
	 * Funcao que recebe um vetor de inteiros colorSequence e numeros inteiros nrColors e
	 * len e verifica se colorSequence eh valido, ou seja, tem len elementos e todos
	 * estes elementos sao numeros entre 1 e nrColors
	 * @param colorSequence - vetor de inteiros colorSequence
	 * @param nrColors - numero de cores
	 * @param len - tamanho do vetor
	 * @requires { @code colorSequence[i] != null, nrColors >= 1 && nrColors <= 5, len == 4 }
	 * @ensures { @code itsValid (colorSequence, nrColors, lengthGuess) >= 0 }
	 * @return true se colorSequence for valido e false caso contrario
	 */
	public static boolean isValid(int[]colorSequence, int nrColors, int len) {
		boolean valid = colorSequence.length == len;

		if(valid)
			for(int i = 0; i < colorSequence.length; i++)
				if(!(colorSequence[i] > 0 && colorSequence[i] <= nrColors))
					valid = false;
		return valid;
	}

	/**
	 * Funcao que retorna um vetor de tamanho len com numeros inteiros aleatorios
	 * entre 1 e nrColors. Este procedimento deverah assegurar que o vetor retornado
	 * eh um puzzle valido
	 * @param nrColors - numero de cores
	 * @param len - tamanho do vetor
	 * @param rd - class random para gerar numeros inteiros aleatorios
	 * @requires { @code nrColors >= 1 && nrColors <= 5, len == 4 }
	 * @ensures { @code randomPuzzle (nrColors, len, rd) != null }
	 * @return vetor de tamanho len com numeros inteiros aleatorios entre 1 e nrColors
	 */
	public static int[] randomPuzzle(int nrColors, int len, java.util.Random rd) {
		int[] puzzle = new int[len];
		do {
			for(int i = 0; i < puzzle.length; i++)
				puzzle[i] = rd.nextInt(nrColors) + 1;
		}while(!isValid(puzzle, nrColors, len));
		return puzzle;
	}


	/**
	 * Funcao auxiliar para verificar se os digitos do valor introduzido se encontram
	 * entre 1 e nrColors
	 * @param value - valor introduzido pelo utilizador
	 * @param nrColors - numero de cores
	 * @requires { @code value != 0, nrColors >= 1 && nrColors <= 5 }
	 * @ensures { @code verifyDigits (value, nrColors) != null }
	 * @return true se os digitos estiverem entre 1 e nrColors e false caso contrario
	 */
	public static boolean verifyDigits(int value, int nrColors) {
		boolean checkDigits = true;
		int count = 0;

		//enquanto value > 0 e checkDigits != false
		while(value > 0 && checkDigits) {
			int resValue = value % 10;
			if(resValue <= 0 || resValue > nrColors)
				checkDigits = false;
			value /= 10;
			count++;
		}
		if(count != 4)
			checkDigits = false;
		return checkDigits;
	}

	/**
	 * Procedimento que le uma jogada representada como uma sequencia de digitos,
	 * atraves do canal dado e verifica se o valor lido eh valido, ou seja,
	 * se eh uma sequencia com len digitos de tamanho len, se for valido,
	 * transforma o valor introduzido num vetor do tipo [0,0,0,0]
	 * @param sc - Canal usado para o utilizador introduzir o valor
	 * @param nrColors - numero de cores
	 * @param len - tamanho do valor inteiro
	 * @requires { @code sc != null, nrColors >= 1 && nrColors <= 5, len == 4 }
	 * @ensures { @code readGuess (sc, nrColors, len) != null }
	 * @return vetor com os digitos introduzidos se forem validos
	 */
	public static int[] readGuess(Scanner sc, int nrColors, int len){
		int[] guess = new int[len];
		int valor = 0;

		boolean error;
		int value;
		String errorMsg = "The guess is invalid! Insert a new guess: ";
		do {
			value = sc.nextInt();
			error = !verifyDigits(value, nrColors);
			if (error)
				System.out.println(errorMsg);
		} while (error);
		valor = value;

		for(int i = guess.length - 1; i >= 0; i--) {
			guess[i] = valor % 10;
			valor /= 10;
		}
		return guess;
	}

	/**
	 * Procedimento que regista a jogada guess na linha i de board
	 * @param board - tabuleiro onde vai ser registada, por linha, a jogada
	 * @param guess - jogada a registar
	 * @param i - linha em board onde vai ser registada a jogada
	 * @requires { @code board[i] != null, guess[i] > 0 && guess[i] < 6, i >= 1 && i <= 10 }
	 */
	public static void insertGuess(int[][] board, int[] guess, int i) {
		for(int col = 0; col < board[i].length; col++)
			board[i][col] = guess[col];
	}

	/**
	 * Funcao que determina o numero de pecas da cor correta na posicao correta
	 * para a jogada e puzzle dados
	 * @param puzzle - puzzle dado
	 * @param guess - jogada do utilizador
	 * @requires { @code puzzle[i] >= 1 && puzzle[i] <= 5, guess[i] > 0 && guess[i] < 6 }
	 * @ensures { @code colorPosMatch (puzzle[i], guess[i]) are equals }
	 * @return numero de pecas da cor correta na posicao correta
	 */
	public static int colorPosMatch(int[] puzzle, int[] guess) {
		int count = 0;

		for(int i = 0; i < puzzle.length; i++)
			if(puzzle[i] == guess[i])
				count++;
		return count;
	}

	/**
	 * Funcao que determina o numero de pecas da cor correta na posicao errada
	 * para a jogada e puzzle dados
	 * @param puzzle - puzzle dado
	 * @param guess - jogada do utilizador
	 * @requires { @code puzzle[i] >= 1 && puzzle[i] <= 5, guess[i] > 0 && guess[i] < 6 }
	 * @ensures { @code onlyColorMatches (puzzle[i], guess[j]) color are equals }
	 * @return numero de pecas da cor correta na posicao errada
	 */
	public static int onlyColorMatches(int[] puzzle, int[] guess) {
		int count = 0;
		int[] tempPuzzle = new int[puzzle.length];
		int[] tempGuess = new int[guess.length];

		//Aqui vai verificar se o valor na posicao i do tempGuess eh igual ao valor na posicao i do tempPuzzle,
		//se for, sabemos que essa peca tem a mesma cor e estah na posicao certa, entao alteramos o valor
		//nessa posicao em tempPuzzle para zero
		for(int i = 0; i < puzzle.length; i++) {
			tempGuess[i] = guess[i];
			tempPuzzle[i] = puzzle[i];
			if(guess[i] == puzzle[i])
				tempPuzzle[i] = 0;
		}

		for(int i = 0; i < guess.length; i++) {
			for(int j = 0; j < puzzle.length; j++) {
				//Se o produto dos valores na mesma posiçao for != 0, ou seja, o valor em tempPuzzle
				//ou de tempGuess nessa posiçao nao eh 0 e tempGuess na posicao i eh igual
				//a tempPuzzle na posicao j
				if(tempGuess[i]*tempPuzzle[i] != 0 && tempGuess[i] == tempPuzzle[j]) {
					count++;
					//tempGuess[i] = 0, para nao voltar a comparar esta posicao
					tempGuess[i] = 0;
				}
			}
		}
		return count;
	}

	/**
	 * Procedimento que assumindo que board eh uma matriz com puzzle.length
	 * colunas e i >= 0 && i < board.length, imprime as jogadas registadas
	 * no tabuleiro da linha i ateh ah linha 0 e respetivas pistas.
	 * Nas pistas eh usado o simbolo 'o' para representar a cor correta na
	 * posicao correta e '*' para a cor correta na posicao errada
	 * @param puzzle - puzzle dado
	 * @param board - tabuleiro
	 * @param i - linha i do tabuleiro
	 * @requires { @code puzzle[i] >= 1 && puzzle[i] <= 5, board[i][j] != null, i > 0 && i <= 10 }
	 */
	public static void printBoardAndClues(int[] puzzle, int[][] board, int i) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int len = 4;
		int nrColors = 5;
		int[] guess ;
		boolean end;
		board = new int[i][puzzle.length];
		sb.append("+-------------+\n");
		do {
			System.out.println("Insert a valid guess of length " + len + ":");
			guess = readGuess(sc, nrColors, len);
			i--;
			insertGuess(board, guess, i);
			int colorSamePos = colorPosMatch(puzzle, guess);
			int colorOtherPos = onlyColorMatches(puzzle, guess);
			end = i == 0 || colorPosMatch(puzzle, guess) == len;

			sb.append("| ");
			for(int j = 0; j < board[0].length; j++)
				sb.append(board[i][j]);
			sb.append(" | ");

			if(colorSamePos > 0) {
				while(colorSamePos > 0) {
					sb.append("o");
					colorSamePos--;
				}
			}
			if(colorOtherPos > 0) {
				while(colorOtherPos > 0) {
					sb.append("*");
					colorOtherPos--;
				}
			}
			if(colorSamePos == 0 && colorOtherPos == 0){
				int sum = colorPosMatch(puzzle, board[i]) + onlyColorMatches(puzzle, board[i]);
				while(sum < len) {
					sb.append(" ");
					sum++;
				}
			}
			sb.append(" |\n");

			sb.append("+-------------+\n");
			System.out.print(sb.toString());

			if(colorPosMatch(puzzle, guess) != len && i != 0) {
				System.out.println("Number of remaining guesses: " + i + "\n");
			}
		}while(!end);

		if(colorPosMatch(puzzle, guess) == len)
			System.out.println("Game over! Congratulations!");
		else if(i <= 0 && colorPosMatch(puzzle, guess) != len)
			System.out.println("Game over! You lost!");

	}

}
