import java.util.Random;


/*
 * Classe FuncoesMonius da 2a Fase do Projecto de IP da FCUL 
 * @author Grupo 045
 * @author Marcio Moreira - nr 41972
 * @author Maria Azevedo - nr 50299
 * @date Novembro 2017
 */
public class JogarLightmare {


	/**
	 * Joga o jogo Lightmare in Moniusland
	 * @param iniMonius a posicao inicial do Monius
	 * @param iniOutro a posicao inicial do Outro
	 * @param angulo angulo de movimento do sabre de luz
	 * @param linhas numero de linhas do caminho
	 * @param colunas numero de colunas do caminho
	 * @param maxVezes numero maximo de jogadas do jogo
	 * @requires iniMonius >= 1 && iniMonius <= linhas * colunas &&
	 * 			 iniOutro >= 1 && iniOutro < = linhas * colunas &&
	 *	         angulo em {-90, 90, -180, 180, 360} &&
	 * 	 		 linhas > 0 && colunas > 0 && maxVezes > 0
	 */
	public static void experimentaJogar (int posMonius, int posOutro, int angulo, 
			int linhas,int colunas, int maxVezes){

		Random rd = new Random(1);
		boolean emJogo = true;
		int contaJogadas = 0;
		int contaAttackMonius = 0;
		boolean foiAtingido = FuncoesMonius.foiAtingido(posMonius, posOutro,
				calculaAlcanceSabre(rd, colunas), angulo, colunas, linhas);

		System.out.println("######################################################"
				+ "######");
		System.out.print("O angulo de ataque do Monius serah sempre " + angulo + 
				" graus durante o jogo");

		System.out.println(printEstado(rd,posMonius, posOutro, linhas, colunas, angulo));

		do {

			calculaAlcanceSabre(rd, colunas);
			calculaAposSaltoOutro(rd, linhas, colunas, posOutro);
			atacaOuSalta(rd);


			emJogo = estahEmJogo(posOutro, posMonius, linhas, colunas, maxVezes, 
					foiAtingido, rd, angulo, contaJogadas);

			if (emJogo){

				if(atacaOuSalta(rd)){
					contaAttackMonius++;

				}
				else saltoDoMonius(rd, linhas, colunas, posMonius, posOutro);

				System.out.println(printEstado(rd, posMonius, posOutro, linhas, colunas,angulo));

				contaJogadas++;

			}

		}while (emJogo);


		System.out.println();
		printGameOver(contaJogadas, posOutro, posMonius, maxVezes, linhas, colunas, 
				contaAttackMonius, angulo, rd);


		//System.out.println("TESTE!!!!!!!" );
	}


	/**
	 * Imprime o estado do jogo
	 * @param rd aleatorio
	 * @param posMonius posicao do Monius
	 * @param posOutro posicao do Outro
	 * @param linhas numero de linhas
	 * @param colunas numero de colunas
	 * @param angulo angulo de ataque do Monius
	 * @return uma String que apresenta o tabuleiro de jogo
	 * @requires linhas > 0 && colunas > 0 , posMonius > 0 && posMonius < linhas * colunas
	 * 			 posOutro > 0 && posOutro < linhas*colunas
	 */
	public static String printEstado ( Random rd, int posMonius, int posOutro, 
			int linhas, int colunas, int angulo ){

		StringBuilder tabuleiro = new StringBuilder();
		StringBuilder estado = new StringBuilder();
		StringBuilder total = new StringBuilder();

		tabuleiro.append('\n');

		for ( int i = 1; i <= linhas; i++){
			for ( int j = 1; j <= colunas; j++){

				if( i == FuncoesMonius.linha(posOutro, colunas) && 
						j == FuncoesMonius.coluna(posOutro, colunas, linhas)  
						&& i == FuncoesMonius.linha(posMonius, colunas) && 
						j == FuncoesMonius.coluna(posMonius, colunas, linhas) ){
					tabuleiro.append('@');
				}

				else if (i == FuncoesMonius.linha(posOutro, colunas) && 
						j == FuncoesMonius.coluna(posOutro, colunas, linhas)){
					tabuleiro.append('O');
				}

				else if (i == FuncoesMonius.linha(posMonius, colunas) && 
						j == FuncoesMonius.coluna(posMonius, colunas, linhas)){
					tabuleiro.append('M');;
				}

				/*else if(FuncoesMonius.foiAtingido(posMonius, posOutro, 
						calculaAlcanceSabre(rd, colunas), angulo, colunas,
						linhas)){
					tabuleiro.append('*');
				}*/

				else tabuleiro.append("_");

			}
			tabuleiro.append("\n");
		}


		estado.append("\nAlcance de luz nesta jogada: " + 
				calculaAlcanceSabre(rd, colunas)).append("\nO Outro move-se: " + 
						calculaSaltoOutro(rd, colunas) + "   ").append("Nova posicao do Outro: " + 
								calculaAposSaltoOutro(rd, linhas, colunas, posOutro));

		if (!atacaOuSalta(rd)){
			estado.append("\nMonius salta: " + calculaValorMonius(rd, colunas)).append
			("      Monius saltou para a posicao: " + saltoDoMonius
					(rd, linhas, colunas, posMonius, posOutro));
		}
		else estado.append("\nMonius vai atacar!");


		total = tabuleiro.append(estado);

		return total.toString();
	}


	/**
	 * Calcula o valor do salto do Monius
	 * @param rd aleatorio
	 * @param colunas numero de colunas
	 * @return valor do salto
	 * @requires colunas > 0
	 */
	public static int calculaValorMonius ( Random rd ,int colunas){

		return 2*calculaAlcanceSabre(rd, colunas);
	}


	/**
	 * Calcula nova posicao do Monius apos o salto
	 * @param rd aleatório
	 * @param linhas numero de linhas
	 * @param colunas numero de colunas
	 * @param posMonius posicao do Monius
	 * @param posOutro posicao do Outro
	 * @return nova posicao apos o salto do Monius
	 * @requires linhas > o && colunas > 0 
	 */
	public static int saltoDoMonius (Random rd, int linhas, int colunas, 
			int posMonius, int posOutro){

		int novaPosMonius = 0;
		int valorDoSalto = calculaValorMonius(rd, colunas);

		//se o Monius decide saltar
		if( posOutro < posMonius){
			novaPosMonius = linhas * colunas + (posMonius - valorDoSalto);
		}
		else if ( posOutro > posMonius){
			novaPosMonius = (posMonius + valorDoSalto) - linhas * colunas ;
		}
		if (novaPosMonius < 0){
			novaPosMonius = (linhas * colunas) - (posMonius - novaPosMonius); 
		}
		else if (novaPosMonius > linhas * colunas){
			novaPosMonius = novaPosMonius - ((linhas * colunas) - posMonius) + 1;
		}

		return novaPosMonius;
	}


	/**
	 * Verifica se o Outro ainda estah em condicoes de jogar
	 * @param posOutro posicao do Outro
	 * @param posMonius posicao do Monius
	 * @param linhas numero de linhas 
	 * @param colunas numero de colunas
	 * @param maxJogadas numero maximo de jogadas
	 * @param foiAtingido vefica se foi atingido
	 * @param rd aleatorio
	 * @param angulo angulo do sabdre
	 * @param contaJogadas conta numero de jogadas
	 * @return True se estiver ainda em jogo, False caso contrario
	 * @requires linhas > 0 && colunas > 0 , posMonius > 0 && posMonius < linhas * colunas
	 * 			 posOutro > 0 && posOutro < linhas*colunas , maxJogadas > 0
	 */
	public static boolean estahEmJogo (int posOutro, int posMonius, int linhas, 
			int colunas, int maxJogadas, boolean foiAtingido, Random rd, 
			int angulo, int contaJogadas ){

		boolean emJogo = true;

		//condicoes que verificam em jogo
		if ( posOutro == linhas*colunas || (contaJogadas == maxJogadas) || 
				(FuncoesMonius.foiAtingido(posMonius, posOutro, 
						calculaAlcanceSabre(rd, colunas), angulo, colunas, linhas)) 
				|| (posOutro == posMonius))
			emJogo = false;

		return emJogo;
	}


	/**
	 * Faz o print de acordo com a analise do desempenho do jogo após este terminar
	 * @param contaJogadas conta o numero de jogadas	
	 * @param posOutro posicao do Outro
	 * @param posMonius posicao do Monius
	 * @param maxVezes numero maximo de jogadas permitidas
	 * @param linhas numero de linhas
	 * @param colunas numero de colunas
	 * @param contaAttackMonius numero de ataques do Monius
	 * @param angulo angulo do sabre
	 * @param rd aleatorio
	 * @requires linhas > 0 && colunas > 0 , posMonius > 0 && posMonius < linhas * colunas
	 * 			posOutro > 0 && posOutro < linhas*colunas , maxVezes > 0
	 */
	public static void printGameOver( int contaJogadas, int posOutro, int posMonius, 
			int maxVezes, int linhas, int colunas, int contaAttackMonius, int angulo, 
			Random rd){

		//o Outro chega ah ultima posicao do jogo
		if (posOutro == linhas * colunas){
			System.out.println("Parabéns ao Outro! Atravessou o caminho ao fim de " 
					+ contaJogadas + " jogadas!");
			System.out.println("O Monius atacou " + contaAttackMonius + " vezes");
		}

		//o Outro foi atingido pelo sabre do Monius
		else if (FuncoesMonius.foiAtingido(posMonius, posOutro, 
				calculaAlcanceSabre(rd, colunas), angulo, colunas, linhas)){
			System.out.println("Oooops! O Outro foi atingido ao fim de " + 
					contaJogadas + " jogadas!");
			System.out.println("O Monius atacou " + contaAttackMonius + " vezes");
		}

		//o numero maximo de jogadas foi efectuado e o Outro nao foi apanhado
		else if (contaJogadas == maxVezes){
			System.out.println("Parabéns ao Outro! Conseguiu escapar durante " + 
					contaJogadas + " jogadas!");
			System.out.println("O Monius atacou " + contaAttackMonius + " vezes");
		}	
	}


	/**
	 * Calcula aleatoriamente se o Monius ataca ou salta
	 * @param rd aleatorio
	 * @return TRUE || FALSE. Valor aleatorio
	 */
	public static boolean atacaOuSalta (Random rd){

		return rd.nextBoolean();

	}


	/**
	 * Calcula a nova posicao do Outro depois do salto
	 * @param rd aleatorio
	 * @param linhas numero de linhas no jogo
	 * @param colunas numero de colunas do jogo
	 * @param posOutro posicao do Outro
	 * @return a nova posicao do outro apos o salto
	 * @requires linhas > 0 && colunas > 0 && posOutro > 0 && posOutro < linhas*colunas
	 */
	public static int calculaAposSaltoOutro ( Random rd, int linhas, int colunas, 
			int posOutro){

		int novaPosOutro = posOutro + calculaSaltoOutro(rd, colunas);

		if( posOutro + calculaSaltoOutro(rd, colunas) < 0){
			novaPosOutro = calculaSaltoOutro(rd, colunas) + Math.abs(posOutro-1) + 1;
		}
		if ( posOutro + calculaSaltoOutro(rd, colunas) > (linhas * colunas)){
			novaPosOutro = (linhas * colunas) - calculaSaltoOutro(rd, colunas) + 1;
		}

		return novaPosOutro;
	}


	/**
	 * Calcula o valor do salto do Outro
	 * @param rd aleatório
	 * @param colunas numero de colunas
	 * @return um valor aleatorio entre um max e um minimo
	 * @requires min != 0 && max != 0
	 */
	public static int calculaSaltoOutro(Random rd, int colunas){

		int max = colunas;
		int min = (-colunas);

		return rd.nextInt(max-min + 1) + min;
	}


	/**
	 * Calcula valores aleatorios entre 1 e o numero de colunas
	 * @param rd aleatorio
	 * @param colunas numero de colunas do jogo
	 * @return um valor em determinado intervalo
	 * @requires colunas > 0
	 */
	public static int calculaAlcanceSabre (Random rd, int colunas){

		return rd.nextInt(colunas-1) + 1;

	}
}
