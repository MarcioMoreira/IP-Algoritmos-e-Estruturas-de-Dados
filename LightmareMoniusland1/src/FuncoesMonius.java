/**
 * Classe FuncoesMonius da 1a Fase do Projecto de IP da FCUL 
 * @author Grupo 045
 * @author Marcio Moreira - nr 41972
 * @author Maria Azevedo - nr 50299
 * @date Outubro 2017
 * 
 */

public class FuncoesMonius {

	/**
	 * Um Outro eh atingido pelo sabre de luz de um Monius?
	 * @param posMonius Posicao do Monius
	 * @param posOutro Posicao do Outro
	 * @param alcanceLuz Alcance da luz do sabre
	 * @param angulo Angulo de movimento do sabre de luz
	 * @param comprim Numero de blocos de comprimento do caminho (colunas)
	 * @param altura Numero de blocos de altura do caminho (linhas)
	 *
	 * @requires posMonius >= 1 && posMonius <= comprim * altura &&
	 * posOutro >= 1 && posOutro < = comprim * altura &&
	 * angulo em {-90, 90, -180, 180, 360} &&
	 * comprim > 0 && altura > 0 && alcanceLuz >= 0
	 *
	 * @return true se o Outro que se encontra na posicao posOutro eh atingido
	 * pelo movimento que o Monius, que estah na posicao posMonius,
	 * faz com o seu sabre de luz, de angulo graus, num caminho com
	 * comprim * altura posicoes
	 * false c.c.
	 * 
	 */
	public static boolean foiAtingido (int posMonius, int posOutro,
			int alcanceLuz, double angulo, int comprim, int altura){


		boolean ehAtingido = false;

		//EH REDUNDANTE CALCULAR/ESPECIFICAR CASOS LIMITE NAS EXTREMIDADES 
		//E CANTOS DA MATRIZ

		// se estah virado para OESTE

		double aproximacao = 0.0001;

		if (westIsTheBest(posMonius, comprim, altura)){

			// com angulo de 360 graus basta ver se estah ao alcance do 
			// sabre de luz

			if( angulo == 360){
				if( estahNoAlcance(posMonius, posOutro, comprim, altura, 
						alcanceLuz) && converteAproximacao(calculaDistancia(posMonius, 
								posOutro, comprim, altura), alcanceLuz, aproximacao)
						){
					ehAtingido = true;
				}
			}

			// se o angulo eh de 90 graus soh interessa avaliar as linhas e colunas 
			// menores que a posicao do Monius o Outro estah ao alcance se o tamanho 
			// do sabre de luz for >= que a distancia do Outro ao Monius

			else if( angulo == 90){
				if ( convertPosEmLinha(comprim, altura, posOutro) <= 
						convertPosEmLinha(comprim, altura, posMonius) &&
						convertPosEmColuna(comprim, altura, posOutro) <= 
						convertPosEmColuna(comprim, altura, posMonius) 
						&& estahNoAlcance(posMonius, posOutro, comprim, altura, 
								alcanceLuz) && converteAproximacao
						(calculaDistancia(posMonius, posOutro, comprim, altura), 
								alcanceLuz, aproximacao)){
					ehAtingido = true;
				}		
			}

			// se o angulo eh de -90 graus soh interessa avaliar as linhas maiores 
			// e colunas menores que a posicao do Monius o Outro estah ao alcance se 
			// o tamanho do sabre de luz for >= que a distancia do Outro ao Monius

			else if( angulo == -90){
				if ( convertPosEmLinha(comprim, altura, posOutro) >= 
						convertPosEmLinha(comprim, altura, posMonius) &&
						convertPosEmColuna(comprim, altura, posOutro) <= 
						convertPosEmColuna(comprim, altura, posMonius) 
						&& estahNoAlcance(posMonius, posOutro, comprim, altura, 
								alcanceLuz) 
						){
					ehAtingido = true;	
				}
			}

			// se o angulo eh de +180 || -180 o que interessa avaliar eh se a 
			// coluna eh <= ah posicao do Monius e se estah ao alcance so sabre 
			// de luz

			else if ( angulo == 180 || angulo == -180){
				if ( convertPosEmColuna(comprim, altura, posOutro) <= 
						convertPosEmColuna(comprim, altura, posMonius) &&
						estahNoAlcance(posMonius, posOutro, comprim, altura, 
								alcanceLuz)){
					ehAtingido = true;
				}
			}

		}

		// se estah virado para ESTE

		if (!westIsTheBest(posMonius, comprim, altura)){

			// se o angulo eh de +180 || -180 o que interessa avaliar eh se a 
			// coluna eh <= ah posicao do Monius e se estah ao alcance so 
			// sabre de luz

			if( angulo == 360){
				if( estahNoAlcance(posMonius, posOutro, comprim, altura, 
						alcanceLuz)&& converteAproximacao(calculaDistancia(posMonius, 
								posOutro, comprim, altura), alcanceLuz, aproximacao)){
					ehAtingido = true;
				}
			}

			// se o angulo eh de 90 graus soh interessa avaliar as linhas 
			// e colunas >= que a posicao do Monius o Outro estah ao alcance 
			// se o tamanho do sabre de luz for >= que a distancia do Outro 
			// ao Monius

			else if( angulo == 90){
				if ( convertPosEmLinha(comprim, altura, posOutro) >= 
						convertPosEmLinha(comprim, altura, posMonius) &&
						convertPosEmColuna(comprim, altura, posOutro) >= 
						convertPosEmColuna(comprim, altura, posMonius) 
						&& estahNoAlcance(posMonius, posOutro, comprim, altura, 
								alcanceLuz)){
					ehAtingido = true;
				}		
			}

			// se o angulo eh de -90 graus soh interessa avaliar as linhas 
			// sao <= e colunas >= que a posicao do Monius o Outro estah ao 
			// alcance se o tamanho do sabre de luz for >= que a distancia do 
			// Outro ao Monius

			else if( angulo == -90){
				if ( convertPosEmLinha(comprim, altura, posOutro) <= 
						convertPosEmLinha(comprim, altura, posMonius) &&
						convertPosEmColuna(comprim, altura, posOutro) >= 
						convertPosEmColuna(comprim, altura, posMonius) 
						&& estahNoAlcance(posMonius, posOutro, comprim, altura, 
								alcanceLuz)){
					ehAtingido = true;
				}		
			}

			// se o angulo eh de +180 || -180 o que interessa avaliar eh 
			// se a coluna eh >= ah posicao do Monius e se estah ao alcance 
			// so sabre de luz

			else if ( angulo == 180 || angulo == -180){
				if (  convertPosEmColuna(comprim, altura, posOutro) >= 
						convertPosEmColuna(comprim, altura, posMonius) &&
						estahNoAlcance(posMonius, posOutro, comprim, altura, 
								alcanceLuz)){
					ehAtingido = true;
				}
			}
		}

		return ehAtingido;
	}



	/**
	 * Verifica se o Monius esta virado para Este ou Oeste
	 * @param posMonius Posicao dos Monius
	 * @param colunas Numero de colunas do Jogo
	 * @param linhas Numero de linhas da matriz de jogo
	 * 
	 * @return True se estiver virado para Oeste
	 * 
	 * @requires posMonius >= 1 && posMonius <= colunas * linhas 
	 * && linhas >= 1 && colunas >= 1 
	 * 
	 */
	public static boolean westIsTheBest (int posMonius, int colunas, int linhas){

		boolean west = false;

		// se a linha eh par estah virado para OESTE

		if ( convertPosEmLinha(colunas, linhas, posMonius) % 2 == 0)
			west = true;

		return west;	
	}

	/**
	 * Calcula a linha em que qualquer um dos jogadores se encontra
	 * @param comprim Comprimento da linhas da quadricula de jogo
	 * @param altura Dimensao das colunas da quadricula de jogo
	 * @param posicao Numero da casa onde se encontra determinado jogador
	 * 
	 * @return Linha onde se situa um jogador
	 * 
	 * @requires comprim >= 1 && altura >= 1 && posicao >= 1
	 * 
	 */
	public static int convertPosEmLinha ( int comprim, int altura, int posicao){


		int linhaPos = 0;

		//faz o mesmo que os ifs -> recuerdo para memoria futura. FUNCIONA!!!

		/*for ( int i = 1; i <= altura; i++){
			if( posicao <= comprim)
				linhaPos = 1;
			else if (posicao > i*(comprim) && posicao <= (i+1)*comprim)
				linhaPos = i+1;
		}*/

		if (posicao <= comprim){
			linhaPos = 1;
		}
		else if (posicao % comprim == 0){
			linhaPos = posicao / comprim;
		}
		else if ( posicao % comprim != 0){
			linhaPos = (posicao / comprim) + 1;
		}

		return linhaPos;
	}

	/**
	 * Calcula a coluna em que qualquer um dos jogadores se encontra
	 * @param comprim Comprimento da linhas da quadricula de jogo
	 * @param altura Dimensao das colunas da quadricula de jogo
	 * @param posicao Numero da casa onde se encontra determinado jogador
	 * 
	 * @return Coluna onde se situa um jogador
	 * 
	 * @requires comprim >= 1 && altura >= 1 && posicao >= 1
	 * 
	 */
	public static int convertPosEmColuna ( int comprim, int altura, int posicao){

		int colunaPos = 0;

		// linhas que nao sao pares
		if (convertPosEmLinha (comprim, altura, posicao) % 2 != 0){

			if ( posicao % comprim == 0)
				colunaPos = comprim;				
			else 
				colunaPos = posicao % comprim;
		}

		// linhas pares
		else if  (convertPosEmLinha (comprim, altura, posicao) % 2 == 0){


			if ( posicao % comprim == 0 )
				colunaPos = 1;		
			else 
				colunaPos = (comprim - (posicao % comprim) + 1 )  ;
		}

		return colunaPos;

	}

	/**
	 * Calcula distancia entre dois pontos
	 * @param posMonius Posicao dos Monius
	 * @param posOutro Posicao dos Outros
	 * @param colunas Numero de colunas do Jogo
	 * @param linhas Numero de linhas da matriz de jogo
	 * 
	 * @return A distancia entre os dois jogadores
	 * 
	 * @requires posMonius >= 1 && posMonius <= colunas * linhas &&
	 * posOutro >= 1 && posOutro < = colunas * linhas
	 * 
	 */
	public static double calculaDistancia (int posMonius, int posOutro, int colunas,
			int linhas){

		//calculo euclidiano entre dois pontos 
		//(raiz quadrada da soma dos quadrados das diferencas de coordenadas)

		double x = Math.pow( (convertPosEmColuna(colunas, linhas, posMonius) - 
				convertPosEmColuna(colunas, linhas, posOutro)),2);

		double y = Math.pow( (convertPosEmLinha(colunas, linhas, posMonius) - 
				convertPosEmLinha(colunas, linhas, posOutro) ) , 2);

		return Math.sqrt( x + y );

	}


	/**
	 * Verifica se o Outro esta ao alcance do sabre do Monius
	 * @param posMonius Posicao dos Monius
	 * @param posOutro Posicao dos Outros
	 * @param colunas Numero de colunas do Jogo
	 * @param linhas Numero de linhas da matriz de jogo
	 * @param alcanceLuz Comprimento do sabre de luz com que o Monius ataca o Outro
	 * 
	 * @return True se estiver ao alcance
	 * 
	 * @requires posMonius >= 1 && posMonius <= colunas * linhas &&
	 * posOutro >= 1 && posOutro < = colunas * linhas && alcanceLuz > 0
	 * 
	 */
	public static boolean estahNoAlcance ( int posMonius, int posOutro, int colunas, 
			int linhas, int alcanceLuz){

		// se a distancia a que o Monius estah do Outro for <= ao alcance do 
		// sabre de luz entao ha probabilidade de ser atingido dependendo da 
		// verificacao do angulo retorna TRUE se estiver ao alcace

		return (calculaDistancia(posMonius, posOutro, colunas, linhas) <= alcanceLuz);
	}

	/**
	 * Verifica o se a diferenca eh menor a um dado valor
	 * @param distAaB Distancia do Monius ao Outro
	 * @param alcanceLuz Tamanho do sabre de Luz
	 * @param aproximacao Valor de aproximacao
	 * 
	 * @return True se a diferenca for <= ao valor aproximacao
	 * 
	 * @requires distAaB >= 0, alcanceLuz >= 0 , aproximacao > 0
	 */
	public static boolean converteAproximacao ( double distAaB, int alcanceLuz, double aproximacao){

		// se a diferenca absoluta entre a distacia do [Monius ; Outro] 
		// e o tamanho do sabre de luz for inferior
		// ao valor de aproximacao, entao eh igual.

		if ( distAaB <= alcanceLuz){

			return ( Math.abs(distAaB - alcanceLuz) <= aproximacao) ;
		}
		else 
			return ( Math.abs(alcanceLuz - distAaB ) <= aproximacao) ;
	}


	//----------------------------------------------------------------------------------------------------

	// NAO USADO, MAS GUARDADO PARA MEMORIA FUTURA

	/*---------------------------------------------------------------------------------------------------------------*/
	//FUNCAO NAO USADA AGORA, MAS PARA MEMORIA FUTURA PODERAH VIR A SER UTIL

	/**
	 * Verifica se o Monius e o Outro sao vizinhos ou nao
	 * @param posMonius Posicao dos Monius
	 * @param posOutro Posicao dos Outros
	 * @param colunas Numero de colunas do Jogo
	 * @param linhas Numero de linhas da matriz de jogo
	 * 
	 * @return True se forem vizinhos
	 * 
	 * @requires posMonius >= 1 && posMonius <= colunas * linhas &&
	 * posOutro >= 1 && posOutro < = colunas * linhas
	 * 
	 */
	public static boolean ehVizinho (int posMonius, int posOutro, int colunas, int linhas){

		// se a distancia entre um e outro for <= 1 entao são vizinhos, logo ha 
		// probabilidade de ser atingido.

		return calculaDistancia(posMonius, posOutro, colunas, linhas) <= 1 ;

	}

}
