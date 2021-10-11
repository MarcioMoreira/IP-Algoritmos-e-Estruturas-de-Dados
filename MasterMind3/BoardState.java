/**
 * Classe BoardState da 3a Fase do Projeto de IP da FCUL
 *
 * @author Marcio Moreira - 41972 | Nuno Fontes - 46413
 * @date Dezembro 2019
 */
public class BoardState {

	private Piece[][] board;
	private Piece[] puzzle;
	private int maxGuesses;
	private int numGuesses;
	private static final int COLUNA = 14;

	/**
	 * Verifica se pieces != null e pieces[i] != null para todo 0 <= i < pieces.length
	 * @param pieces - objetos que representam os seis tipos de pecas
	 * @return true se for valido e false caso contrario
	 */
	public static boolean isValid(Piece[] pieces) {
		boolean ehValido = true;

		if(pieces != null) {
			for (int i = 0; i < pieces.length ; i++)
				if (pieces[i] == null )
					ehValido = false;
		}else {
			ehValido = false;
		}
		return ehValido;
	}

	/**
	 * Inicializa o estado do tabuleiro para uma partida que vai ser
	 * jogada com o puzzle e o limite de tentativas fornecidos
	 * @param puzzle - puzzle formado por objetos que representam os tipos de pecas
	 * @param maxGuesses - numero maximo de tentativas
	 * @ensures {@code }
	 * @requires isValid(puzzle), puzzle.length > 1 e maxGuesses >= 1
	 */
	public BoardState(Piece[] puzzle, int maxGuesses){
		this.board = new Piece[maxGuesses][puzzle.length];
		this.maxGuesses = maxGuesses;
		for(int i = 0; i < maxGuesses; i++)
			for(int j = 0; j < puzzle.length; j++)
				this.board[i][j] = puzzle[j];
	}

	/**
	 * Funcao usada para retornar o tamanho do puzzle
	 * @return tamanho do puzzle
	 */
	public int puzzleLength(){
		return this.puzzle.length;
	}

	/**
	 * Funcao usada para retornar o numero maximo de tentativas
	 * @return numero maximo de tentativas
	 */
	public int maxGuesses(){
		return this.maxGuesses;
	}

	/**
	 * Funcao usada para retornar o numero de tentativas ja realizadas
	 * @return numero de tentativas ja realizadas
	 */
	public int guesses(){
		return this.numGuesses;
	}

	/**
	 * Procedimento que regista a jogada fornecida, assumindo que guesses()<maxGuesses(),
	 * guess.length==puzzleLength() e isValid(guess)
	 * @param guess - guess formado por objetos que representam os tipos de pecas
	 */
	public void insertGuess(Piece[] guess){
		for(int i = 0; i < this.maxGuesses; i++)
			for(int j = 0; j < guess.length; j++)
				this.board[i][j] = guess[j];
		//apos inserir um guess, incrementa o numero de tentativas realizadas
		this.numGuesses++;
	}


	/**
	 *  Funcao usada para retornar o numero de pecas da cor correta
	 *  na posicao correta na jogada i
	 * @param i - numero da jogada
	 * @return numero de pecas da cor correta na posicao correta na jogada i
	 */
	public int colorPosMatch(int i){
		int count = 0;
		for(int j = 0; j < this.puzzleLength(); j++)
			if(this.board[i][j] == this.puzzle[j])
				count++;
		return count;
	}


	/**
	 * Funca usada para retornar o numero de pecas da cor correta
	 * na posicao errada na jogada i
	 * @param i - numero da jogada
	 * @return o numero de pecas da cor correta na posicao errada na jogada i
	 */
	public int onlyColorMatches(int i){
		int count = 0;
		Piece[] tempPuzzle = new Piece[puzzleLength()];
		Piece[] tempGuess = new Piece[this.board.length];

		//Aqui vai verificar se o valor na posicao i do guess eh igual ao valor na posicao i do tempPuzzle,
		//se for, sabemos que essa peca tem a mesma cor e estah na posicao certa, entao alteramos o valor
		//nessa posicao em tempPuzzle para null
		for(int j = 0; j < this.puzzleLength(); j++) {
			tempGuess[j] = this.board[i][j];
			tempPuzzle[j] = puzzle[j];
			if(this.board[i][j] == puzzle[j])
				tempPuzzle[j] = null;
		}

		for(int j = 0; j < puzzleLength(); j++) {
			//Se a posicao j de tempGuess for diferente de null e a posicao j de tempPuzzle
			//tambem for diferente de null, e a posicao j em tempGuess for igual ah posicao j
			//de tempPuzzle, incrementamos o numero de pecas da cor correta na posicao errada
			if((tempGuess[j] != null && tempPuzzle[j] != null) && tempGuess[j] == tempPuzzle[j]) {
				count++;
				//tempGuess[j] = null, para nao voltar a comparar esta posicao
				tempGuess[j] = null;
			}
		}
		return count;
	}

	/**
	 * Representacao textual do estado do tabuleiro (sem revelar o puzzle)
	 */
	public String toString(){
		StringBuilder sb = new StringBuilder();

		for(int i = 0; i < this.maxGuesses; i++) {
			int match = colorPosMatch(i);
			int notMatch = onlyColorMatches(i);

			if(i % 2 == 0)
				sb.append("+-------------+");
			for(int j = 0; j < COLUNA; j++) {
				if(i % 7 == 0)
					sb.append("| ");
				sb.append(this.board[i][j] + " ");
				if(match > 0 || notMatch > 0) {
					while(match > 0)
						sb.append("o");
					match--;
					while(notMatch > 0)
						sb.append("*");
					notMatch--;
				}else {
					sb.append(" ");
				}
			}
			sb.append("|\n");
		}
		sb.append("+-------------+");
		return sb.toString();
	}
}
