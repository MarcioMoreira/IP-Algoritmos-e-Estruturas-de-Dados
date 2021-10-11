import java.util.Random;


public class Teste {

	public static void main(String[] args) {

		int l = 4;
		int c = 4;
		int [] v = new int [c];
		Random rd = new Random(1);
		char outro = '&';
		int posOutro = 1;
		

		int []vetor = new int [c];
		int linhas = 10;
		int colunas= 10;


		int m = 5;
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < l; i++){
			for( int j= 0; j< c; j++){
				if ( i == 1 && j == 1){
					sb.append('M').charAt(m);
				}
				else sb.append('_');
			}
			sb.append("\n");
		}
		
		sb.toString();
		System.out.println(sb);
		
		
		
		

	}
	
	
	static String criaTabuleiro ( int doVectorMonius, int linhas, int colunas){

		StringBuilder tabuleiro = new StringBuilder();
		StringBuilder monius = new StringBuilder();
		
		for (int i = 0; i < linhas; i++){
			for ( int j = 0; j < colunas; j++){
				
			}
		}
		return tabuleiro.toString();
	}
	
	


	/**
	 * imprime vetor
	 * @param v
	 * @param linhas
	 */
	public static void imprimeVector (int v[], int linhas){
		for ( int i = 0; i < linhas; i++){
			for ( int j= 0; j< v.length; j++){
				System.out.print(v[i] + " ");
			}
			System.out.println();
		}
	}


	public static void printVector  ( int v[]){

		for( int i = 0; i <= v.length-1; i++){
			System.out.print(v[i] );
			if ( v[i] < v.length)
				System.out.print(" ");
		}

	}

}
