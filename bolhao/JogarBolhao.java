


import java.util.Random;

/**
 * Watch out for the Bolhao! @author Grupo 150 Marcio Moreira nr 41972 - Guilherme Teixeira nr 49021
 * 
 */


public class JogarBolhao {



  /** Joga o jogo Watch out for the Bolhao!
   * @param posBom - A posicao inicial do jogador Bom
   * @param posMau - A posicao inicial do jogador Mau
   * @param impacto – Forca de impacto dos Bolhoes
   * @param dim - Dimensao do caminho
   * @param maxVezes - Numero maximo de jogadas do jogo
   * @requires posBom > 0 && posBom <= dim && posMau > 0 && posMau <= dim &&
   *           dim > 0 && impacto >= 0 && maxVezes > 0
   */
  public static void jogaJogoBolhao (int posBom, int posMau, int impacto,
      int dim, int maxVezes){

    //System.out.println("CHAMEI");

    boolean emJogo = true;
    int origemX = 0, origemY = 0;
    double angulo;
    int posicaoBom = posBom, posicaoMau = posMau;
    Random rd = new Random(1);
    int contaJogadas = 0, m = 0, k = 0;
    int contaMudaPosicaoMau = 0; 
    int auxiliaPosMau = 0;

    // impressao do estado inicial do jogo
    System.out.println(printEstado(dim, posicaoBom, posicaoMau, contaJogadas,
        origemX, origemY, m,  k));

    do{
      auxiliaPosMau = posMau;

      // variaveis auxiliares ao calculo do angulo
      // 1 < m <= 16
      m = 2 + rd.nextInt(16);
      // 1 <= k < m
      k = 1 + rd.nextInt(m-1);

      // calculos de proximo turno
      origemX = calculaOrigemX(rd,dim);
      origemY = calculaOrigemY(rd, posicaoBom, posicaoMau, origemX);

      angulo = calculaAngulo(rd,m,k);

      posicaoBom = FuncoesBolhao.efeitoBolhao(origemX, origemY, angulo, 
          impacto, posBom, dim);
      posicaoMau = FuncoesBolhao.efeitoBolhao(origemX, origemY, angulo, 
          impacto, posMau, dim);


      if ( posicaoMau != auxiliaPosMau){
        contaMudaPosicaoMau++;
      }

      contaJogadas++;

      emJogo = estahEmJogo(maxVezes, contaJogadas,posicaoBom,posicaoMau);

      if (emJogo){
        System.out.println(printEstado(dim, posicaoBom, posicaoMau, 
            contaJogadas, origemX, origemY, m,  k));
      }

    } while (emJogo);

    printEndGame(posicaoBom, posicaoMau, contaJogadas, maxVezes, 
        contaMudaPosicaoMau);
  }


  /**
   * Imprime mensagem de finalizacao de jogo
   * @param posBom - Posicao do Bom
   * @param posMau - Posicao do Mau
   * @param numJogadas - Contador do numero de jogadas
   * @param maxJogadas - Maximo de jogadas permitidas na ronda
   */
  public static void printEndGame (int posBom, int posMau, int numJogadas,
      int maxJogadas, int contaMudaPosicaoMau ){

    int contaMesmaPosicaoMau = numJogadas - contaMudaPosicaoMau;

    if ( posMau == -1 ){
      System.out.println("Parabens! Afogou o Mau ao fim de " + 
          (numJogadas) + " jogadas!" + "\n" + "O mau ficou na mesma posicao " 
          + contaMesmaPosicaoMau + " vezes e mudou de posicao " +
          contaMudaPosicaoMau + " vezes" );
    }
    else if (posBom == -1){
      System.out.println("Oooops! Afogou o Bom ao fim de " + (numJogadas) 
          + " jogadas!" + "\n" + "O mau ficou na mesma posicao " 
          + contaMesmaPosicaoMau + " vezes e mudou de posicao " + 
          contaMudaPosicaoMau + " vezes" );
    }

    else if(numJogadas >= maxJogadas){
      System.out.println("Temos pena! Better luck for next time!" + "\n" + 
          "O mau ficou na mesma posicao " 
          + contaMesmaPosicaoMau + " vezes e mudou de posicao " + 
          contaMudaPosicaoMau + " vezes" ); 
    }

  }

  /**
   * 
   * @param dim - Dimensao do tabuleiro de jogo
   * @param posB - Posicao do Bom
   * @param posM -Posicao do Mau
   * @param contaJogadas - Contador de jogadas
   * @param origemX - Origem do lancamento do Bolhao no eixo dos Xs
   * @param origemY - Origem do lancamento do Bolhao no eixo dos Ys.
   * @param m - Denominador do angulo calculado
   * @param k - Numerador do angulo calculado
   * @return - String com o estado do jogo
   * @requires origemX >= 1 && origemY >= 1 && m > 0 && k != 0
   *           dim > 0 && posB >= 1 && posB >= dim && posM >= 1 && posM >= dim
   */
  public static String printEstado (int dim, int posB, int posM, 
      int contaJogadas, int origemX, int origemY, int m, int k){

    StringBuilder sbTabuleiro = new StringBuilder();
    StringBuilder sbContaTabuleiro = new StringBuilder();
    StringBuilder sbJogadores = new StringBuilder();
    StringBuilder sbTotal = new StringBuilder();
    StringBuilder sbOrigemAngulo = new StringBuilder();

    int listCompleta = dim/10;
    int listResto = dim %10;

    if (contaJogadas > 0)
      sbOrigemAngulo.append("Origem: " + "(" + origemX + "," + origemY +
          ")" + "     " + "Angulo: " + k + "PI / " + m );


    //   System.out.println("POSICOES: " + "B:"+posB +" M:" + posM);

    //desenha posicoes dos jogadores
    for (int p = 1; p<=dim; p++){
      if (p == posB)
        sbJogadores.append ("B");
      else if (p== posM)
        sbJogadores.append ("M");
      else
        sbJogadores.append(" ");
    }

    //System.out.println(posB + " " + posM);
    if (posB == posM && posB!=-1)
      sbJogadores.replace(posB-1, posB, "@"); //posicoes identicas

    //desenha tabuleiro de jogo
    for ( int i = 1; i <= listCompleta; i++ ){
      for (int j = 1; j <= 10; j++){
        sbTabuleiro.append((j%10));
        if (j<10)
          sbContaTabuleiro.append(" ");
      }      
      sbContaTabuleiro.append(i);
    }

    //desenha resto do tabuleiro de jogo
    for( int a = 1; a <= listResto; a++){
      sbTabuleiro.append(a);
    }

    //junta tudo numa so
    sbTotal = sbOrigemAngulo.append("\n").append(sbJogadores.append("\n").
        append(sbTabuleiro.append("\n").append(sbContaTabuleiro))); 

    return sbTotal.append("\n").toString();

  }


  /**
   * Calcula de forma aleatoria posicao do bolhao no eixo dos X
   * @param rd - O gerador de numeros aleatorios
   * @param dim - Dimensao do tabuleiro de jogo
   * @return - Posicao do bolhao quando cai
   * @requires dim > 0
   */
  public static int calculaOrigemX ( Random rd, int dim){

    return 1 + rd.nextInt(dim);

  }


  /**
   * Calcula de forma aleatoria posicao do bolhao no eixo dos Y
   * @param rd - O gerador de numeros aleatorios
   * @param posBom - Posicao do Bom
   * @param posMau - Posicao do Mau
   * @param origemX - posicao do bolhao no eixo do X
   * @return - Altura do lancamento do Bolhao
   * @requires - posBom >= 1 && posMau >= 1 && posBom <= dim && posM <= dim
   */
  public static int calculaOrigemY ( Random rd, int posBom, int posMau, 
      int origemX){

    //se a posicao dos dois for a mesma
    int origemY = 1;

    //se a posicao dos dois for diferente 
    if (posBom != posMau){

      //distancia entre bom e mau
      int dist = Math.abs(posBom - posMau);

      origemY = 1 + rd.nextInt(dist);
    }
    return origemY;   
  }


  /**
   * Calcula o angulo que a queda do bolao faz com o tabuleiro de jogo
   * @param rd - O gerador de numeros aleatorios
   * @return - Angulo em radianos
   * @requires - k != 0 && m >= 1
   */
  public static double calculaAngulo (Random rd, int k, int m){
    return (k*Math.PI)/m;    
  }


  /**
   * Verifica se esta em jogo de acordo com as regras ou nao
   * @param maxJ - Numero maximo de jogadas
   * @param nJog - Numero de jogadas
   * @param posB - Posicao do bom
   * @param posM - Posicao do mau
   * @return - Falso se as condicoes se verificarem e termina o jogo, True 
   *           em caso contrario
   * @requires - posB >= 1 && posB <= dim && posM >= 1 && posM <= dim
   */
  public static boolean estahEmJogo (int maxJog, int numJog, int posB, int posM){

    return posB == -1?false:            //se posicao do Bom eh -1
      posM == -1? false:                //se posicao do Mau eh -1
        numJog >= maxJog? false: true;  // se o numero de jogadas eh maior 
                                        //que o maximo de jogadas permitido

  }

}
