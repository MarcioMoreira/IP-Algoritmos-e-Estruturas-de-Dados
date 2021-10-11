import java.util.Random;

public class Teste {

	public static void main(String[] args) {

		int posMonius = 0;
		int posOutro = 2;
		char monius = 'M';
		char outro = 'O';

		Random rd = new Random();

		int max = 1;
		int min = 0;
		//random.nextInt(max - min + 1) + min
		int aleat = rd.nextInt(max-min +1)+min;

		//	System.out.println( stb (posMonius, posOutro, 8, 6) );
		//System.out.println(aleat);
		//System.out.println(Math.abs(-5+2)+1);



		System.out.println(printEstado(30, 12, 5, 6));

	}



	public static String stb ( int posMonius, int posOutro, int linhas, int colunas){

		StringBuilder stb = new StringBuilder();

		for ( int i = 1; i <= linhas; i++){
			for ( int j = 1; j <= colunas; j++){

				stb.append("_");	
			}
			stb.append("\n");
		}

		return stb.toString();
	}


	public static String printEstado ( int posMonius, int posOutro, int linhas, int colunas ){

		StringBuilder tabuleiro = new StringBuilder();


		for ( int i = 1; i <= linhas; i++){
			for ( int j = 1; j <= colunas; j++){

				if( i == linha(posOutro, colunas) && j == coluna(posOutro, colunas, linhas)  
						&& i == linha(posMonius, colunas) && j == coluna(posMonius, colunas, linhas) ){
					tabuleiro.append('@');
				}
				
				else if (i == linha(posOutro, colunas) && j == coluna(posOutro, colunas, linhas)){
					tabuleiro.append('O');
				}

				else if (i == linha(posMonius, colunas) && j == coluna(posMonius, colunas, linhas)){
					tabuleiro.append('M');;
				}

				else tabuleiro.append("_");
				
			}
			tabuleiro.append("\n");
		}

		return tabuleiro.toString();
	}

	public static int linha(int pos, int comprim) {

		int linhas = pos / comprim;
		if (pos % comprim > 0){
			linhas++;
		}
		return linhas;
	}


	public static int coluna(int pos, int comprim, int linha) {

		int resto = pos % comprim;
		int result;

		if (linha % 2 == 1){
			if (resto == 0){
				result = comprim;
			} else {
				result = resto;
			}
		} else {
			if (resto == 0){
				result = 1;
			} else {
				result = comprim + 1 - resto;
			}
		}
		return result;
	}


}
