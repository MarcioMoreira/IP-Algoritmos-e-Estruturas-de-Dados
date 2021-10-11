/**
 * Esta classe testa as funcoes e metodos descritos na API das classes BoardState,
 * MasterMindGame e ComputerPlayer em alguns cenarios simples.
 * A API destas classes encontra-se no enunciado da terceiro fase do trabalho de
 * Introducao a Programacao (2019/20, FCUL).
 * @author Andreia Mordido
 * @date 24 Nov 2019
 */

public class MasterMindTest{
	private final static int MAX_GUESSES = 10;
	private final static int NR_ROUNDS = 4;
	private final static Piece[] validGuess = new Piece[] {Piece.Green, Piece.White,
			Piece.Green, Piece.White};
	private final static Piece[] validPuzzle = new Piece[] {Piece.Red, Piece.Green,
			Piece.Green, Piece.White};
	private final static Piece[] invalidGuess = new Piece[] {Piece.Red, Piece.Green,
			Piece.Green, Piece.White, Piece.White};


	/**
	 * Executes tests on classes BoardState, MasterMindGame e ComputerPlayer
	 * @param args
	 */
	 public static void main (String[] args){
		 System.out.println(">>>> Starting tests <<<<");
		 testBoardState();
		 testMasterMind();
		 testComputerPlayer();
		 System.out.println(">>>> Tests finished <<<<");
		 System.out.println("Do not forget: these are just a couple of simple tests.");
		 System.out.println("Test your code with additional tests!!!");
	 }

	 /**
	  * Tests functions and methods of class BoardState:
	  * - static isValid(Piece[] pieces)
	  * - puzzleLength()
	  * - maxGuesses()
	  * - guesses()
	  * - insertGuess(Piece[] guess)
	  * - colorPosMatch(int i)
	  * - onlyColorMatches(int i)
	  * - toString()
	  */
	 private static void testBoardState(){
		 BoardState board = new BoardState(validPuzzle, MAX_GUESSES);
		 System.out.println(">>> Testing class BoardState:");

		 System.out.println(">> Tests function static isValid(Piece[] pieces):");
		 System.out.println("> on a valid puzzle: ");
		 if (BoardState.isValid(validPuzzle))
			 System.out.println("Ok");
		 else
			 System.out.println("ERROR: Function isValid did not validate a valid puzzle");
		 System.out.println("> on an invalid puzzle: ");
		 if (BoardState.isValid(invalidGuess))
			 System.out.println("Ok");
		 else
			 System.out.println("ERROR: Function isValid validates an invalid puzzle");

		 System.out.println(">> Tests method puzzleLength():");
		 if (board.puzzleLength() == validPuzzle.length)
			 System.out.println("Ok");
		 else
			 System.out.println("ERROR: Method puzzleLength() returns " + board.puzzleLength()
			 + " and it was expected value " + validPuzzle.length);

		 System.out.println(">> Tests method maxGuesses():");
		 if (board.maxGuesses() == MAX_GUESSES)
			 System.out.println("Ok");
		 else
			 System.out.println("ERROR: Method maxGuesses() returns " + board.maxGuesses()
			 + " and it was expected value " + MAX_GUESSES);

		 System.out.println(">> Tests method guesses() at the beginning:");
		 if (board.guesses() == 0)
			 System.out.println("Ok");
		 else
			 System.out.println("ERROR: Method guesses() returns " + board.guesses()
			 + " and it was expected value 0.");

		 System.out.println(">> Inserting a new guess.");
		 board.insertGuess(validGuess);

		 System.out.println(">> Tests method guesses() after the first guess:");
		 if (board.guesses() == 1)
			 System.out.println("Ok");
		 else
			 System.out.println("ERROR: Method guesses() returns " + board.guesses()
			 + " and it was expected value 1.");

		 System.out.println(">> Tests method colorPosMatch(1) for the first guess:");
		 if (board.colorPosMatch(1) == 2)
			 System.out.println("Ok");
		 else
			 System.out.println("ERROR: Method colorPosMatch(1) returns " + board.colorPosMatch(1)
			 + " and it was expected value 2.");

		 System.out.println(">> Tests method onlyColorMatches(1) for the first guess:");
		 if (board.onlyColorMatches(1) == 1)
			 System.out.println("Ok");
		 else
			 System.out.println("ERROR: Method onlyColorMatches(1) returns " + board.onlyColorMatches(1)
			 + " and it was expected value 1.");

		 System.out.println(">> Inserting the right guess.");
		 board.insertGuess(validPuzzle);

		 System.out.println(">> Tests method guesses() after the first guess:");
		 if (board.guesses() == 2)
			 System.out.println("Ok");
		 else
			 System.out.println("ERROR: Method guesses() returns " + board.guesses()
			 + " and it was expected value 2.");

		 System.out.println(">> Tests method toString()");
		 System.out.println(board+"\n");
	 }

	 /**
	  * Tests the methods of class MasterMind:
	  * - puzzleLength()
	  * - maxGuesses()
	  * - numberRounds()
	  * - roundsPlayed()
	  * - isValid(Piece[] pieces)
	  * - roundBeingPlayed()
	  * - points(int player)
	  * - guessingPlayer()
	  * - puzzleMaster()
	  * - isOver()
	  * - startNewRound(Piece[] puzzle)
	  * - play(Piece[] guess)
	  * - toString()
	  */
	 public static void testMasterMind(){
		 MasterMindGame game = new MasterMindGame(NR_ROUNDS, validPuzzle.length, MAX_GUESSES);

		 System.out.println(">>> Testing class MasterMindGame:");

		 System.out.println(">> Tests method puzzleLength():");
		 if (game.puzzleLength() == validPuzzle.length)
			 System.out.println("Ok");
		 else
			 System.out.println("ERROR: Method puzzleLength() returns " + game.puzzleLength()
			 + " and it was expected value " + validPuzzle.length);

		 System.out.println(">> Tests method maxGuesses():");
		 if (game.maxGuesses() == MAX_GUESSES)
			 System.out.println("Ok");
		 else
			 System.out.println("ERROR: Method maxGuesses() returns " + game.maxGuesses()
			 + " and it was expected value " + MAX_GUESSES);

		 System.out.println(">> Tests method numberRounds():");
		 if (game.numberRounds() == NR_ROUNDS)
			 System.out.println("Ok");
		 else
			 System.out.println("ERROR: Method numberRounds() returns " + game.numberRounds()
			 + " and it was expected value " + NR_ROUNDS);

		 System.out.println(">> Tests method roundsPlayed() at the beginning:");
		 if (game.roundsPlayed() == 0)
			 System.out.println("Ok");
		 else
			 System.out.println("ERROR: Method roundsPlayed() returns " + game.roundsPlayed()
			 + " and it was expected value 0.");

		 System.out.println(">> Tests method isValid(Piece[] pieces):");
		 System.out.println("> on a valid guess: ");
		 if (game.isValid(validGuess))
			 System.out.println("Ok");
		 else
			 System.out.println("ERROR: Function isValid did not validate a valid guess");
		 System.out.println("> on an invalid guess: ");
		 if (BoardState.isValid(invalidGuess))
			 System.out.println("Ok");
		 else
			 System.out.println("ERROR: Function isValid validates an invalid guess");

		 System.out.println(">> Tests method roundBeingPlayed() at the beginning:");
		 if (game.roundBeingPlayed() == false)
			 System.out.println("Ok");
		 else
			 System.out.println("ERROR: Method roundBeingPlayed() returns " + game.roundBeingPlayed()
			 + " and it was expected false.");

		 System.out.println(">> Starting a new round.");
		 game.startNewRound(validPuzzle);
		 System.out.println(">> Making a guess.");
		 game.play(validGuess);
		 System.out.println(">> Tests method roundBeingPlayed() now:");
		 if (game.roundBeingPlayed() == true)
			 System.out.println("Ok");
		 else
			 System.out.println("ERROR: Method roundBeingPlayed() returns " + game.roundBeingPlayed()
			 + " and it was expected true.");

		 System.out.println(">> Tests method guessingPlayer():");
		 if (game.guessingPlayer() == 1)
			 System.out.println("Ok");
		 else
			 System.out.println("ERROR: Method guessingPlayer() returns " + game.guessingPlayer()
			 + " and it was expected player 1.");

		 System.out.println(">> Tests method puzzleMaster():");
		 if (game.puzzleMaster() == 2)
			 System.out.println("Ok");
		 else
			 System.out.println("ERROR: Method puzzleMaster() returns " + game.puzzleMaster()
			 + " and it was expected player 2.");

		 System.out.println(">> Tests method isOver():");
		 if (game.isOver() == false)
			 System.out.println("Ok");
		 else
			 System.out.println("ERROR: Method isOver() returns " + game.isOver()
			 + " and it was expected false.");

		 System.out.println(">> Tests method points(int player):");
		 System.out.println("> for player 1");
		 if (game.points(1) == 0)
			 System.out.println("Ok");
		 else
			 System.out.println("ERROR: Method points(1) returns " + game.points(1)
			 + " and it was expected value 0.");
		 System.out.println("> for player 2");
		 if (game.points(2) == 1)
			 System.out.println("Ok");
		 else
			 System.out.println("ERROR: Method points(2) returns " + game.points(2)
			 + " and it was expected value 1.");

		 System.out.println(">> Making the right guess.");
		 game.play(validPuzzle);
		 System.out.println(">> Tests method roundBeingPlayed() now:");
		 if (game.roundBeingPlayed() == false)
			 System.out.println("Ok");
		 else
			 System.out.println("ERROR: Method roundBeingPlayed() returns " + game.roundBeingPlayed()
			 + " and it was expected false.");

		 System.out.println(">> Tests method isOver():");
		 if (game.isOver() == false)
			 System.out.println("Ok");
		 else
			 System.out.println("ERROR: Method isOver() returns " + game.isOver()
			 + " and it was expected false.");

		 System.out.println(">> Tests method points(int player):");
		 System.out.println("> for player 1");
		 if (game.points(1) == 0)
			 System.out.println("Ok");
		 else
			 System.out.println("ERROR: Method points(1) returns " + game.points(1)
			 + " and it was expected value 0.");
		 System.out.println("> for player 2");
		 if (game.points(2) == 1)
			 System.out.println("Ok");
		 else
			 System.out.println("ERROR: Method points(2) returns " + game.points(2)
			 + " and it was expected value 1.");

		 System.out.println(">> Starting a new round and making a guess.");
		 game.startNewRound(validPuzzle);
		 game.play(validGuess);
		 System.out.println(">> Tests method toString()");
		 System.out.println(game+"\n");
	 }

	 /**
	  * Tests the methods of class ComputerPlayer:
	  * - firstGuess()
	  * - nextGuess(int colorPosMatch, int onlyColorMatch)
	  * - generatePuzzle()
	  */
	 public static void testComputerPlayer(){
		 ComputerPlayer player = new ComputerPlayer(validPuzzle.length, MAX_GUESSES, 5);
		 System.out.println(">>> Testing class ComputerPlayer:");
		 System.out.println(">>> these tests are only valid if the player plays randomly !");

		 System.out.println(">> Tests method generatePuzzle():");
		 Piece[] pc = player.generatePuzzle();
		 if (pc[0] == Piece.Orange && pc[1] == Piece.Green && pc[2] == Piece.Red && pc[3] == Piece.Red)
			 System.out.println("Ok");
		 else {
			 System.out.print("If the player is generating the puzzle randomly, it " +
					 "should have generated OGRR and it generates: " );
			 for (int i = 0; i < pc.length; i++)
				 System.out.print(pc[i]);
			 System.out.println();
		 }

		 System.out.println(">> Tests if firstGuess() returns a valid guess:");
		 Piece[] guess = player.firstGuess();
		 if (BoardState.isValid(guess))
			 System.out.println("Ok");
		 else
			 System.out.print("ERROR: Method firstGuess() does not return a valid guess." );

		 System.out.println(">> Tests if nextGuess(int colorPosMatch, int onlyColorMatch) returns a valid guess:");
		 guess = player.nextGuess(1,2);
		 if (BoardState.isValid(guess))
			 System.out.println("Ok");
		 else
			 System.out.print("ERROR: Method nextGuess(int colorPosMatch, int onlyColorMatch) does not return a valid guess." );
	 }
}
