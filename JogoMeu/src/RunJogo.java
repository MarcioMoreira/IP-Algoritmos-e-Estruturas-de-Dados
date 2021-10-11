import java.util.Random;
import java.util.Scanner;

public class RunJogo {

	protected static int numJogadasMax = 10;
	protected static int numJogadas = 0;
	protected static int max = 100;
	protected static int min = 0;
	protected static int score = 1100;
	protected static int [] vectorEntradas;
	
	public static void main(String[] args) {
		
		vectorEntradas = new int[numJogadasMax];
		Random rd = new Random();
		int numAdivinha = rd.nextInt(100 - 1)+1;
		Scanner sc = new Scanner(System.in);
		int input = 0;

		System.out.println("------------------------------------------");
		System.out.println("\nRandom number has been launched\nTry to guess it\n");
		System.out.println("------------------------------------------");

		System.out.println("\nGAME MENU\n\n"
				+ "1 - Play");
		System.out.println("2 - Quit");
		System.out.println("\n------------------------------------------\n");
		System.out.print("Number : ");
		input = lerValorNoIntervalo(1, 2, "Must insert a valid number between 1 and 2\n"
				+ "Insert a number between 1 and 2", sc);

		switch (input) {

		case 1:
			do{
				
				System.out.println("\n------------------------------------------");
				System.out.println("\nInsert a number between 1 and 100");
				System.out.print("Number : ");
				input = lerValorNoIntervalo(min, max, "\nMust insert a valid number between 1 and 100\n"
						+ "Insert a number between 1 and 100\n", sc);

				System.out.println();
				preencheEntradas(vectorEntradas, numJogadas, input);

				checkPlays(input, numAdivinha);

				printVectorJogadas(vectorEntradas);
				numJogadas++;

				score -= 100;

				System.out.println("\nYOUR SCORE -> " + score);
				System.out.println("\nPLAY NUMBER -> "+ numJogadas +"\n");

			}while(numJogadas < numJogadasMax && !checkInput(input, numAdivinha) || score == 0);

			break;
		case 2:
			System.out.println("\n------------------------------------------");
			System.out.println("\nGOODBYE!! ");
			System.out.println("\n------------------------------------------");
			break;
		default:

		}
	}

	/**
	 * 
	 * @param input
	 * @param numAdivinha
	 */
	public static void checkPlays (int input, int numAdivinha) {
		
		if (inputBigger(input, numAdivinha)) {
			System.out.println("UPS!!! Try again inserting a smaller number");
		}
		else if (inputSmaller(input, numAdivinha)) {
			System.out.println("UPS!!! Try again inserting a bigger number");
		}
		else if (numJogadas == numJogadasMax) {
			System.out.println("UPS!!! Time up!");
		}
		else if(checkInput(input, numAdivinha)){
			System.out.println("CONGRATS! YOU GUESSED THE NUMBER\nAFTER " + (numJogadas + 1) + " PLAYS !!");
		}
	}


	/**
	 * 
	 * @param v
	 */
	public static void printVectorJogadas(int []v){
		System.out.print("\nENTRY -> ");
		System.out.print("[ ");
		for (int i = 0; i < v.length; i++) {
			System.out.print(v[i] + " ");
		}
		System.out.println("]");
	}

	/**
	 * 
	 * @param v
	 * @param jogada
	 * @param input
	 * @return
	 */
	public static int [] preencheEntradas ( int []v, int jogada,int input){
		v[jogada] = input;
		return v;
	}

	/**
	 * 
	 * @param input
	 * @param rdNumber
	 * @return
	 */
	public static boolean inputBigger (int input, int rdNumber){
		return (input > rdNumber);
	}

	/**
	 * 
	 * @param input
	 * @param rdNumber
	 * @return
	 */
	public static boolean inputSmaller (int input, int rdNumber){
		return (input < rdNumber);
	}

	/**
	 * 
	 * @param input
	 * @param rdNumber
	 * @return
	 */
	public static boolean checkInput (int input, int rdNumber){
		return (input == rdNumber);
	}

	/**
	 * Primeiro inteiro no canal de leitura que esta num dado intervalo
	 * @param min Limite inferior do intervalo
	 * @param max Limite superior do intervalo
	 * @param errMess  Mensagem de  erro a apresentar no System.out
	 * @param sc Canal de leitura
	 * @return um valor entre min e max
	 * @requires sc != null && min <= max && errMess != null
	 */
	public static int lerValorNoIntervalo(int min, int max, String errMess, Scanner sc) {
		int valor = 0;
		boolean erro;
		do {
			valor = lerInteiro ( errMess, sc );
			erro = valor < min || valor > max;
			if ( erro ){
				System.out.println ( errMess );
			}
		} while ( erro );

		return valor;
	}


	/**
	 * Primeiro inteiro no canal de leitura
	 * @param errMess - mensagem a escrever no System.out caso o valor 
	 *                  acessivel no canal de leitura nao seja um inteiro
	 * @param sc - canal de leitura
	 * @return valor inteiro
	 * @requires errMess != null && sc != null
	 */
	public static int lerInteiro ( String errMess, Scanner sc ) {
		int valor = 0;
		boolean erro = true;
		do {
			if ( sc.hasNextInt () ) {
				valor = sc.nextInt ();  // consome o inteiro
				erro = false;
			} else {
				sc.next ();      // consome o que lah esteja
				System.out.println ( errMess );
			}				
		} while ( erro );

		return valor;
	}

}
