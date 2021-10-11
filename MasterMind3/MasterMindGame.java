/**
 * Classe MasterMindGame da 3a Fase do Projeto de IP da FCUL
 *
 * @author Marcio Moreira - 41972 | Nuno Fontes - 46413
 * @date Dezembro 2019
 */
public class MasterMindGame {

	private int numberRounds;
	private int puzzleLength;
	private int maxGuesses;
	private BoardState board;
	private int[] points;
	private Piece[][] createBoard;
	private int currentPlayer;
	private int masterPlayer;

	/**
	 * Cria um jogo no estado inicial
	 * @param numberRounds - numero de partidas a jogar
	 * @param puzzleLength - tamanho do puzzle
	 * @param maxGuesses - numero maximo de tentativas
	 */
	public MasterMindGame(int numberRounds, int puzzleLength, int maxGuesses) {
		this.numberRounds = numberRounds;
		this.puzzleLength = puzzleLength;
		this.maxGuesses = maxGuesses;
		this.createBoard = new Piece[numberRounds][puzzleLength];

		for(int i = 0; i < numberRounds; i++)
			for(int j = 0; j < puzzleLength; j++)
				this.createBoard[i][j] = null;

		this.currentPlayer = 1;
		this.masterPlayer = 2;
		this.points = new int[2];
	}

	/**
	 * Funcao usada para retornar o tamanho do puzzle
	 * @return tamanho do puzzle
	 */
	public int puzzleLength() {
		return this.puzzleLength;
	}


	/**
	 * Funcao usada para retornar o numero maximo de tentativas
	 * @return numero maximo de tentativas
	 */
	public int maxGuesses(){
		return this.maxGuesses;
	}

	/**
	 * Funcao usada para retornar o numero de partidas a jogar
	 * @return numero de partidas a jogar
	 */
	public int numberRounds(){
		return this.numberRounds;
	}

	/**
	 * Funcao usada para retornar o numero de partidas jah jogadas
	 * @return numero de partidas jah jogadas
	 */
	public int roundsPlayed() {
		return this.board.guesses();
	}

	/**
	 * Funcao usada para verificar se pieces!=null, pieces[i]!=null
	 * @param pieces - objetos que representam os seis tipos de pecas
	 * @return true se for valido e false caso contrario
	 */
	public boolean isValid(Piece[] pieces){
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
	 * Funcao usada para permitir saber se existe uma partida em curso
	 * @return true se houver uma partida em curso e false caso contrario
	 */
	public boolean roundBeingPlayed(){
		//numero de partidas jah jogadas inferior ao numero de partidas a jogar
		return this.board.guesses() < this.numberRounds;
	}

	/**
	 * Funcao usada para permitir saber o numero de pontos do jogador i
	 * @param player - jogador a verificar
	 * @return numero de pontos do jogador
	 */
	public int points(int player){
		return points[player-1];
	}

	/**
	 * Funcao usada para retornar o jogador que adivinha o puzzle
	 * @return jogador que adivinha o puzzle
	 */
	public int guessingPlayer(){
		return currentPlayer;
	}

	/**
	 * Funcao usada para retornar o dono do puzzle
	 * @return dono do puzzle
	 */
	public int puzzleMaster(){
		return masterPlayer;
	}

	/**
	 * Funcao usada para indicar se o jogo ja terminou
	 * @return true se jah terminou e false caso contrario
	 */
	public boolean isOver(){
		boolean over = false;
		//enquanto o numero de partidas jah jogadas for inferior ao numero
		//de partidas a jogar ou over = false
		while(this.board.guesses() < numberRounds || !over) {
			//se o valor de colorPosMatch na jogada this.board.guesses() for
			//igual ao tamanho do puzzle, termina o jogo
			if(this.board.colorPosMatch(this.board.guesses()) == puzzleLength)
				over = true;
			//se nao, se o numero de partidas jah jogadas for igual ao
			//numero de partidas a jogar, termina o jogo e o dono do puzzle,
			//recebe um ponto, mais um ponto extra por ter terminado o numero
			//de partidas a jogar sem que o jogador que tenta adivinhar o puzzle
			//tenha adivinhado o mesmo
			else if(this.board.guesses() == numberRounds) {
				over = true;
				points[masterPlayer-1] += 2;
			}
		}
		return over;
	}

	/**
	 * Procedimento que cria um tabuleiro no estado inicial para jogar
	 * uma nova partida com o puzzle dado
	 * @param puzzle - puzzle dado, formado por objetos que representam os tipos de pecas
	 */
	public void startNewRound(Piece[] puzzle){
		this.board = new BoardState(puzzle, maxGuesses);
	}

	/**
	 * Funcao que devolve um vetor com duas posi�oes com o numero de cores na posicao certa
	 * e o numero de cores certas na posicao errada, respetivamente, e atualiza o estado do tabuleiro da
	 * partida em curso, atualiza a pontuacao do jogador que definiu o puzzle e, se apropriado (o puzzle
	 * foi descoberto ou as tentativas esgotadas), termina com a partida em curso
	 * @param guess - guess formado por objetos que representam os tipos de pecas
	 * @return vetor com duas posicoes, uma com o numero de cores na posicao certa e outra
	 *         com o numero de cores certas na posicao errada
	 */
	public int[] play(Piece[] guess){
		int[] result = new int[2];

		//atualiza o tabuleiro com um novo guess
		this.board.insertGuess(guess);
		//guarda na posicao zero do vetor o numero de cores corretas na posicao correta
		result[0] = this.board.colorPosMatch(this.board.guesses());
		//guarda na posicao um do vetor o numero de cores corretas na posicao errada
		result[1] = this.board.onlyColorMatches(this.board.guesses());

		//se terminou o jogo, o numero de partidas a jogar ficarah a zero
		if(isOver())
			this.numberRounds = 0;
		//caso contrario, o dono do puzzle recebe um ponto
		else
			points[masterPlayer-1] += 1;

		return result;
	}

	/**
	 * Representacao textual do jogo
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();



		return sb.toString();
	}
}
