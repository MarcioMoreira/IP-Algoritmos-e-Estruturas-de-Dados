import java.util.Random;
import java.util.Scanner;

public class JogarLightmare3 {


	static final char MARCA_MONIUS = 'M';
	static final char MARCA_OUTRO = '&';
	static final char CAMINHO = '_';
	static final char MONIUSeOUTRO = '@';
	static final char ATINGEOUTRO = '*';
	static final int MONIUS_MORTO = -1;




	/**
	 * Joga o jogo Lightmare in Moniusland
	 * @param iniMonius - as posicoes iniciais dos Monius
	 * @param angulo - angulo de movimento do sabre de luz
	 * @param alcanceLuz - alcance da luz dos sabres dos Monius
	 * @param linhas - numero de linhas do caminho
	 * @param colunas - numero de colunas do caminho
	 * @param maxVezes - numero maximo de jogadas do jogo
	 * @param gerador O gerador de valores aleatorios
	 * @param leitor O objeto Scanner para ler dados do standard input
	 * @requires iniMonius != null && forall i, iniMonius[i] >= 1 &&
	 * iniMonius[i] <= linhas * colunas &&
	 * angulo em {-90, 90, -180, 180, 360} && alcanceLuz >= 1 &&
	 * linhas > 0 && colunas > 0 && maxVezes > 0 &&
	 * gerador != null && leitor != null
	 */
	public static void experimentaJogar3 (int[] iniMonius, int angulo, int alcanceLuz,
			int linhas, int colunas, int maxVezes, Random gerador, Scanner leitor){

		//String moniusOuCaminho = "M_";

		System.out.println("Como te chamas? ");
		String jogador = leitor.nextLine();

		System.out.println("\n#######################################################");
		System.out.println("O angulo de ataque dos Monius serah sempre " + angulo + " graus durante o jogo"
				+ "\nO alcance de luz dos sabres dos Monius serah sempre " + alcanceLuz + " durante o jogo\n");


		int posOutro = 1;
		int jogadas = 0;
		int [] posMonius = iniMonius;
		

		for ( int i = 0; i < iniMonius.length; i++){
			System.out.print(iniMonius[i] +": ");
			int linhaPos = FuncoesMonius3.linha(iniMonius[i], colunas);
			int colunaPos = FuncoesMonius3.coluna(iniMonius[i], colunas, linhaPos);
			System.out.print(" linha -> " + linhaPos + " ");
			System.out.println(" coluna -> " + colunaPos);

		}
		System.out.println();

		StringBuilder sb = new StringBuilder();

		for ( jogadas = 0; jogadas <= maxVezes && ahMaus(posMonius) > 0 && moche(posOutro, posMonius) ; jogadas++){

			for ( int i = 0; i < linhas; i++){
				for( int j = 0; j < colunas; j++){
					int linhaPos = FuncoesMonius3.linha(iniMonius[i], colunas);
					int colunaPos = FuncoesMonius3.coluna(iniMonius[i], colunas, linhaPos);
					if(FuncoesMonius3.linha(posOutro, colunas) == i && 
							j == FuncoesMonius3.coluna(posOutro, colunas, FuncoesMonius3.linha(posOutro, colunas))){
						sb.append(MARCA_OUTRO);
					}
					else if( ahMaus(iniMonius) > 0){

						if( i == linhaPos && j == colunaPos){
							sb.append(MARCA_MONIUS); 
						}
						else {
							sb.append(CAMINHO);
						}
					}
					else {
						sb.append(CAMINHO);
					}



				}
				sb.append("\n");
			}
			System.out.println(sb.toString());

		}



		System.out.println(criaTabuleiroJogo(iniMonius, linhas, colunas, posOutro));

		System.out.println("\n"+jogador + ", quantas posicoes vais saltar?");



	}

	/**
	 * Verifica se um Monius estah na mesma posicao do OUTRO
	 * @param posOutro poisicao do OUTRO
	 * @param posMonius vector que guarda todas as posicoes dos MONIUS actualizadas
	 * @return
	 */
	public static boolean moche (int posOutro, int []posMonius){

		boolean moche = false;

		for (int i = 0; i < posMonius.length; i++){
			if( posOutro == posMonius[i]){
				moche = true;
			}
		}
		return moche;
	}

	/**
	 * Conta o numero de monius no array
	 * @param monius vector que eh recebido
	 * @return numero de monius
	 */
	public static int ahMaus (int [] monius){

		int counter = 0;

		for ( int i = 0; i< monius.length; i++){
			if(monius[i] > 0)
				counter++;
		}

		return counter ;
	}

	/**
	 * Verifica cada posicao do vector e converte o seu valor em linha de jogo
	 * @param vecMonius vector com as posicoes aleatorias dos Monius
	 * @param colunas numero de colunas do jogo
	 * @return
	 */
	public static int linhaPosMonius(int [] vecMonius,int colunas){

		int linhaMonius = 0;

		for ( int i = 0; i < vecMonius.length; i++){

			linhaMonius = FuncoesMonius3.linha(vecMonius[i], colunas);

		};
		return linhaMonius;
	}

	/**
	 * Verifica cada posicao do vector e converte o seu valor em coluna de jogo
	 * @param vecMonius vector com as posicoes aleatorias dos Monius
	 * @param colunas numero de colunas do jogo
	 * @return 
	 */
	public static int colunaPosMonius(int [] vecMonius,int colunas){

		int colunaMonius = 0;

		for ( int i = 0; i < vecMonius.length; i++){

			colunaMonius = FuncoesMonius3.coluna(vecMonius[i], colunas, linhaPosMonius(vecMonius,colunas));

		}
		return colunaMonius;
	}

	//VERIFICAR AS CONDICOES DE SALTO NOS LIMITES
	public static int calculaSaltoOutro(int posOutro, int colunas, int linhas, Scanner sc){


		int infLim = (-colunas);
		int supLim = colunas;
		if(posOutro - colunas <= 0){
			//VERIFICAR AS CONDICOES DE SALTO NOS LIMITES
		}
		// linhas*colunas
		String errMess = "Tem de ser inteiro entre " + infLim + " e " + supLim;
		System.out.println("(" + errMess + ")");
		int salto = FuncoesMonius3.lerValorNoIntervalo(infLim, supLim, errMess, sc);

		return salto;
	}

	public static String criaTabuleiroJogo(int []vecMonius, int linhas, int colunas, int posOutro ){

		StringBuilder tabuleiro = new StringBuilder();
		int linhaMonius = linhaPosMonius(vecMonius, colunas);
		int colunaMonius = colunaPosMonius(vecMonius, colunas);


		for (int i = 1; i <= linhas; i++){

			//linhaMonius = FuncoesMonius3.linha(vecMonius[i-1], colunas);
			//colunaMonius = FuncoesMonius3.coluna(vecMonius[i-1], colunas, linhaMonius);


			for ( int j = 1; j<= colunas; j++){		
				if(FuncoesMonius3.linha(posOutro, colunas) == i && 
						j == FuncoesMonius3.coluna(posOutro, colunas, FuncoesMonius3.linha(posOutro, colunas))){
					tabuleiro.append(MARCA_OUTRO);
				}
				//System.out.print("linha" + linhaMonius + " ");
				//System.out.print("coluna" + colunaMonius + " ");
				else if( ahMaus(vecMonius) > 0){

					if( i == linhaMonius && j == colunaMonius){
						tabuleiro.append(MARCA_MONIUS); 
					}
					else {
						tabuleiro.append(CAMINHO);
					}
				}
				else {
					tabuleiro.append(CAMINHO);
				}



			}
			tabuleiro.append("\n");
		}

		return tabuleiro.toString();

	}




	//CONTINUAR!!!!!!!!!! ESTAO APENAS MARCADAS ALGUMAS DAS PREMISSAS
	public static boolean checkEmJogo( int posOutro, int posMonius , int maxJogadas, int contadorMonius,
			int contaJogadas, int linhas, int colunas){

		boolean termina = false;

		if(contadorMonius == 0){
			termina = true;
		}
		if(posMonius == posOutro){
			termina = true;
		}

		if( posOutro == linhas * colunas ){
			termina = true;
		}

		return termina;
	}





}
