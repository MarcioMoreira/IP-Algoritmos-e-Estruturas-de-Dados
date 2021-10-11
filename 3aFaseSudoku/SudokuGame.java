/**
 *
 * @author Márcio Moreira 41972 - Pedro Mena 51024
 *
 */
public class SudokuGame {


	private static final int DIMENSAO = 9;

	public SudokuPuzzle puzzle;

	public SudokuGame(SudokuPuzzle puzzle){

		this.puzzle = puzzle;

	}

	public boolean revealed(int l, int c){

		for(int i = 0; i < DIMENSAO; i++){
			for(int j = 0; j < DIMENSAO; j++){
				if(this.puzzle.filter[l][c] == true){
					return true;
				}
			}
		}
		return false;
	}

	public boolean filled(int l, int c){

		for(int i = 0; i < DIMENSAO; i++){
			for(int j = 0; j < DIMENSAO; j++){
				if(this.revealed(l, c) || this.puzzle.grid[l][c] != 0){
					return true;
				}
			}
		}
		return false;
	}

	public void fill(int l, int c, int value){

		for(int i = 0; i < DIMENSAO; i++){
			for(int j = 0; j < DIMENSAO; j++){
				if ( revealed(l, c)){
					this.puzzle.grid[l][c] = value;
				}
			}
		}

	}


	public void unfill(int l, int c){

		for(int i = 0; i < DIMENSAO; i++){
			for(int j = 0; j < DIMENSAO; j++){
				if ( !revealed(l, c)){
					this.puzzle.grid[l][c] = 0;
				}
			}
		}

	}

	public int value(int l, int c){

		return this.puzzle.cellValue(l,c);

	}



	//VERIFICAR ESTA TRETA!!
	public boolean isCorrectSoFar(){

		for ( int i = 0; i < DIMENSAO; i++){
			for( int j = 0; j < DIMENSAO; j++){


			}
		}

	/*	for(int i = 0; i < DIMENSAO; i++){
			for(int j = 0; j < DIMENSAO; j++){
				if(this.puzzle.filter[i][j] != true && this.puzzle.grid[i][j] != 0){
					if(this.puzzle.grid[i][j] != this.puzzle.solucao[i][j]){
						return false;
					}
				}
			}
		}*/

		return true;
	}

	public boolean isCorrect(){

		return Sudoku2.quadriculaValida(this.puzzle.grid);
	}


	public String toString(){

		StringBuilder sb = new StringBuilder();

		for( int i = 0; i < DIMENSAO; i++){

			if( i % 3 == 0){

				sb.append("-------------------------\n");
			}
			for(int j=0; j<DIMENSAO; j++){

				if( j % 3 == 0){
					sb.append("| ");
				}
				if( this.puzzle.filter[i][j] ){
					sb.append(this.puzzle.cellValue(i+1, j+1)+" ");
				}
				else{
					sb.append("  ");
				}
			}
			sb.append("|\n");
		}
		sb.append("-------------------------\n");

		return sb.toString();
	}






}
