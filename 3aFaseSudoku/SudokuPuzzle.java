//import java.lang.Object;

/**
 *
 * @author Márcio Moreira 41972 - Pedro Mena 51024
 *
 */
public class SudokuPuzzle {

	private static final int DIMENSAO = 9;

	public int [][] grid;

	public boolean [][] filter;



	// TO COMPLETE!!!!!!
	public static boolean valid(int[][] grid, boolean[][] filter){

		int[][] matriz = new int[DIMENSAO][DIMENSAO];

		for(int i = 0; i < DIMENSAO-1; i++){

			for(int j = 0; j<DIMENSAO-1; j++){

				if( grid.length == DIMENSAO && grid[0].length == DIMENSAO && Sudoku2.quadriculaValida(grid)
						&& filter.length == DIMENSAO && filter[0].length == DIMENSAO){

					if( filter[i][j] != false){

						matriz[i][j] = grid[i+1][j+1];

					}
				}
			}
		}

		SudokuSolver solver = new SudokuSolver(matriz);

		return solver.howManySolutions(2) == 1;

	}

	public SudokuPuzzle(int[][] grid, boolean[][] filter){

		int[][] matriz = new int[DIMENSAO][DIMENSAO];

		for(int i = 0; i < DIMENSAO-1; i++){

			for(int j = 0; j<DIMENSAO-1; j++){

				if( filter[i][j] != false){

					matriz[i][j] = grid[i+1][j+1];
				}
			}
		}

		this.grid  = matriz;//matriz
		this.filter = filter;

		//SudokuSolver solver = new SudokuSolver(matriz);

		//this.solucao = solver.findSolutions(1);

	}

	public int cellValue(int l, int c){

		return this.grid[l-1][c-1];

	}

	public boolean revealed(int l, int c){

		return this.filter[l-1][c-1] = true;

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
				if( filter[i][j] ){
					sb.append(this.cellValue(i+1, j+1)+" ");
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
