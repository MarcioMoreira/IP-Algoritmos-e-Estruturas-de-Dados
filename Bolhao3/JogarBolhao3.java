import java.util.Random;
import java.util.Scanner;

/**
 * Watch out for the Bolhao! @author Grupo 150 Marcio Moreira nr 41972 - Guilherme Teixeira nr 49021
 * 
 */

public class JogarBolhao3 {

  static final char MARCA_MAU = 'M';
  static final char CRATERA = ' ';
  static final int CRATERA_INT = -1;
  static final int MAU_MORTO = -1;
  static final int ALTURA_MINIMA = 2;


  /**
   * Joga o jogo Watch out for the Bolhao!
   * @param aviaoX Coordenada X da posicao inicial do aviao
   * @param aviaoY Coordenada Y da posicao inicial do aviao
   * @param iniMaus Posicoes iniciais dos Maus
   * @param altMax Altura maxima a que o aviao pode subir
   * @param dim Dimensao do caminho
   * @param maxVezes Numero maximo de jogadas
   * @param gerador O gerador de numeros aleatorios que deve ser usado para
   * a geracao de todos os aleatorios
   * @param leitor O objeto Scanner que deve ser usado para ler os dados que
   * o Utilizador vai introduzir
   * @requires aviaoX >= 1 && aviaoX <= dim &&
   * aviaoY >= ALTURA_MINIMA && aviaoY <= altMax &&
   * iniMaus != null && forall i, 1 <= iniMaus[i] <= dim &&
   * dim > 0 && dim <= 80 && maxVezes > 0 &&
   * gerador != null && leitor != null
   */
  public static void jogaJogoBolhao3 (int aviaoX, int aviaoY, int[] iniMaus,
      int altMax, int dim, int maxVezes, Random gerador, Scanner leitor){


    int [] caminho = new int [dim];
    int [] jogadores = iniMaus;
    int posAviaoX = aviaoX;
    int posAviaoY = aviaoY;

    int nJogadas = maxVezes;
    boolean emJogo = true;


    jogadores = normalizaPosJogadores(jogadores);

    // para debug
    //printjogadores (jogadores);


    caminho = defineCaminho(dim);
    System.out.println("Configuracao inicial do Caminho:");
    System.out.println(arrayToString(caminho, jogadores));


    do {

      System.out.println(posicaoAviaoToString(posAviaoX, posAviaoY));

      System.out.print("Movimento Horizontal? ");
      posAviaoX = calculaPosicaoAviaoX(posAviaoX, dim, leitor);

      System.out.print("Movimento Vertical? ");
      posAviaoY = calculaPosicaoAviaoY(posAviaoY, dim, leitor);



      fugaDosMaus(posAviaoX, posAviaoY, jogadores, dim, gerador);
      // para debug
      //printjogadores (jogadores);
      mataMausPosFuga(jogadores, caminho);
      System.out.println("Maus fogem " + posAviaoY + " posicoes antes do Bolhao cair:");
      // imprimir estado antes do impacto
      System.out.println(arrayToString(caminho, jogadores));
      // para debug
      //printjogadores (jogadores);


      int posicaoImpactoBolhao = posAviaoX;
      int forcaImpactoBolhao = posAviaoY/2;

      //imprimir estado apos impacto Bolhao
      System.out.println("Apos impacto do Bolhao com forca " + forcaImpactoBolhao +
          " na posicao " + posicaoImpactoBolhao);
      // estado update
      FuncoesBolhao3.aplicaBolhao3(posicaoImpactoBolhao, forcaImpactoBolhao, jogadores, dim);
      abreCratera(caminho, posicaoImpactoBolhao, dim);

      System.out.println(arrayToString(caminho, jogadores));

      nJogadas--;
      emJogo = checkEmJogo (jogadores, nJogadas);

    } while (emJogo);

    // mensagem de fim de jogo
    if (!aHMaus(jogadores))
      System.out.println("Parabens! Afogou os maus ao final de " + (maxVezes - nJogadas) 
          + " jogadas!");
    else
      System.out.println("Temos pena! Better luck next time.");

  }

  /**
   * apenas para debug
   * @param jogadores o array dos jogadores maus
   */
  private static void printjogadores (int[] jogadores) {
    // print jogadores
    System.out.println("actual posicao dos jogadores");
    for (int i=0; i<jogadores.length; i++)
      System.out.print(jogadores[i]+"|");
    System.out.println();
  }

  /**
   * Normaliza todas as posicoes de jogadores no array
   * @param jogadores os maus
   * @return posicao dos jogadores normalizada
   * @requires jogadores > 0
   * @ensures posicao dos jogadores em qualquer array normalizada
   */
  private static int[] normalizaPosJogadores(int[] jogadores) {
    int [] auxArray = jogadores;
    for (int i = 0; i <  auxArray.length; i++)
      jogadores[i]--;
    return auxArray;
  }


  /**
   * Verifica se o jogo ainda nao terminou
   * @param jogadores os Maus
   * @param njogadas numero de jogadas
   * @return true se em jogo, false caso contrario
   * @requires jogadores > 0 && njogadas > 0
   * @ensures que o jogo nao acaba sem que as condicoes se verifiquem
   */
  private static boolean checkEmJogo (int [] jogadores, int njogadas) {
    return njogadas > 0 && aHMaus(jogadores);
  }


  /**
   * Imprime posicao do aviao em string
   * @param posX posicao do aviao no eixo horizontal
   * @param posY posicao do aviao no eixo vertical
   * @return uma string de texto
   * @ensures que mensagem de posicao de jogador eh impressa
   */
  private static String posicaoAviaoToString (int posX, int posY){
    return "Posicao actual do aviao: (" + posX + "," + posY +")";
  }


  /**
   * Calcula e valida a posicao do aviao dada pelo utilizador no eixo horizontal
   * @param posX posicao no eixo horizontal
   * @param dim dimensao do tamanho do caminho onde se joga
   * @param sc input do utilizador
   * @return valor validado ou mensagem de erro de acordo com as regras de jogo
   * @requires dim > 0 && posX > 0 && posY > 0 
   * @ensures que as guardas sejam respeitadas pelo utilizador
   */
  private static int calculaPosicaoAviaoX (int posX, int dim, Scanner sc) {

    int supLim = dim - (posX-1)-1; // menos 1 para normalizacao
    int infLim = supLim - (dim-1);
    String errMess = "Tem de ser inteiro entre " + infLim + " e " + supLim;
    System.out.println("(" + errMess + ")");
    int newPosX = WatchTheBolhao3.lerValorNoIntervalo(infLim, supLim, errMess, sc);

    return posX+newPosX;
  }


  /**
   * Calcula e valida a posicao do aviao dada pelo utilizador no eixo vertical
   * @param posY posicao no eixo vertical
   * @param dim dimensao do tamanho do caminho onde se joga
   * @param sc input do utilizador
   * @return valor validado ou mensagem de erro de acordo com as regras de jogo
   * @requires dim > 0 && posY > 2 
   * @ensures que as guardas sejam respeitadas pelo utilizador
   */
  private static int calculaPosicaoAviaoY (int posY, int dim, Scanner sc) {

    int limiteSuperior = dim/4;
    int infLim = ALTURA_MINIMA - posY; 
    int supLim = limiteSuperior - posY;
    String errMess = "Tem de ser inteiro entre " + infLim + " e " + supLim;
    System.out.println("(" + errMess + ")");
    int newPosY = WatchTheBolhao3.lerValorNoIntervalo(infLim, supLim, errMess, sc);

    return posY+newPosY;
  }


  /**
   * Para todos os Maus executa a sua fuga
   * @param posOrigemAviaoX posicao no eixo horizontal
   * @param posOrigemAviaoY posicao no eixo vertical
   * @param jogadores os Maus que estao em jogo
   * @param dim dimensao do caminho de jogo
   * @param gerador gerador de numeros aleatorios
   * @requires posOrigemAviaoX > 0 && posX > 0 && posOrigemAviaoX > 2 && 
   *           jogadores > 0 && dim > 0
   * @ensures que os maus fogem das suas posicoes
   */
  private static void fugaDosMaus (int posOrigemAviaoX, int posOrigemAviaoY, 
      int[] jogadores, int dim, Random gerador) {

    int limiteSuperior = dim/4;
    int distanciaAviao = posOrigemAviaoY-limiteSuperior;

    for (int i = 0 ; i < jogadores.length ; i++) {  // para cada Jogador
      if (jogadores[i] != MAU_MORTO) {  // soh interessam os que estao "vivos"

        jogadores[i] = novaPosFuga(posOrigemAviaoX, posOrigemAviaoY, jogadores[i],
            dim, gerador);
      }
    }

  }


  /**
   * Calculo da nova posicao do Mau pos fuga
   * @param posOrigemAviaoX posicao no eixo horizontal
   * @param posOrigemAviaoY posicao no eixo vertical
   * @param posJog posicao do jogador Mau
   * @param dim dimensao do caminho de jogo
   * @param rd gerador de numeros aleatorios
   * @return novas posicoes dos maus apos fuga
   * @requires posOrigemAviaoX > 0 && posOrigemAviaoX > 2 && posJog > 0 && dim > 0
   * @ensures que a nova posicao do mau eh detectada
   */
  private static int novaPosFuga (int posOrigemAviaoX, int posOrigemAviaoY, int posJog,
      int dim, Random rd){

    int nPosFuga=0;
    int maxFuga = posOrigemAviaoY;

    if (ehSentidoFugaDireita(rd))
      nPosFuga = posJog + maxFuga;
    else
      nPosFuga = posJog - maxFuga;
    // a nova posicao tem de estar compreendida nos limites do array
    return nPosFuga<0 ? 0: nPosFuga >dim-1 ? dim-1: nPosFuga;
  }


  /**
   * Mata os maus caso exista cratera apos uma fuga e caiam lah
   * @param jogadores os Maus
   * @param caminho caminho de jogo
   * @requires jogadores > 0 && dim > 0 && caminho > 0
   * @ensures que os maus sao mortos
   */
  private static void mataMausPosFuga (int [] jogadores,  int [] caminho) {
    for (int i=0; i<jogadores.length; i++){
      if (existeCratera(caminho, jogadores[i])) // passa o caminho e a posicao do jogador no caminho normalizada
        mataMau(jogadores, i); // i porque eh a posicao do jogador no array
    }}


  /**
   * Define o caminho onde eh jogado o jogo
   * @param dim dimensao do caminho
   * @return array preenchido de 0 a 9 eNe vezes com dim casas
   * @requires dim > 0
   * @ensures que a plataforma de jogo eh impressa
   */
  public static int [] defineCaminho (int dim){

    int [] camino = new int [dim]; 

    for ( int i = 0; i < dim; i++)
      camino[i] = i%10;         

    return camino;

  }


  /**
   * Abre uma cratera onde cai um Bolhao
   * @param caminho vector do caminho onde eh jogado o jogo
   * @param pos posicao onde cai Bolhao
   * @param dim dimensao do caminho
   * @return caminho novo com cratera aberta
   * @requires pos > 1 && dim > 0 && caminho > 0
   * @ensures que a plataforma de jogo sofre as alteracoes quando o bolhao cai
   */
  private static int[] abreCratera (int[] caminho, int pos, int dim) {

    int [] camino = caminho;
    camino[pos-1] = CRATERA_INT;
    return camino;
  }


  /**
   * Converte array em string
   * @param array vector de jogo/caminho
   * @param jogadores vector dos maus
   * @return String com tabuleiro de jogo/caminho
   * @requires array > 0 && jogadores > 0
   * @ensures que o tabuleiro de jogo eh impresso com as devidas premissas
   */
  private static String arrayToString (int []array, int [] jogadores) {

    StringBuilder sbJogadores = new StringBuilder();
    StringBuilder sbContaTabuleiro = new StringBuilder();
    StringBuilder sbTabuleiro = new StringBuilder();
    StringBuilder sbTotal = new StringBuilder();

    for (int i=0; i<array.length; i++){
      // calcula posicao jogadores
      if (!existeMauPos(i, jogadores))
        sbJogadores.append (" ");
      else
        sbJogadores.append (MARCA_MAU);

      // preenche caminho tabuleiro   
      if (array[i]==CRATERA_INT)
        sbTabuleiro.append (CRATERA);
      else
        // i+1 para normalizacao, print contagem inicia em 1
        sbTabuleiro.append((array[i]+1)%10);

      //i>0 porque na casa zero nao se imprime
      if ( i>0 && ((i+1) % 10) == 0)
        sbContaTabuleiro.append((i+1)/10);
      else sbContaTabuleiro.append(" ");
    }

    sbTotal.append(sbJogadores.toString()).append("\n").append(
        sbTabuleiro.toString()).append("\n").append(sbContaTabuleiro);

    return sbTotal.toString();
  }


  /**
   * Marca como eliminado uma posicao de um jogador mau
   * @param jogadores os Maus
   * @param posJog posicao do jogador
   * @return vector com posicao do Mau morto preenchida
   * @requires jogadores > 0 && posJog >= 1
   * @ensures que a posicao onde foi morto o mau fica marcada
   */
  private static int[] mataMau(int[] jogadores, int posJog) {
    int [] jog = jogadores;
    jog[posJog] = MAU_MORTO;
    return jog;
  }


  /**
   * Verifica se existem Maus no caminho
   * @param jogadores os Maus
   * @return true se existem maus
   * @requires jogadores > 0
   * @ensures que ha maus no array
   */
  private static boolean aHMaus(int [] jogadores){
    int contaMaus =0;

    for (int i=0; i<jogadores.length; i++)
      if (jogadores[i]>=0)
        contaMaus++;

    return contaMaus>0;
  }


  /**
   * Verifica se existe Mau no caminho
   * @param pos posicao do jogador
   * @param jogadores os Maus
   * @return true se existe, false caso contrario
   * @requires jogadores > 0 && pos >= 1
   * @ensures que existem maus no caminho
   */
  private static boolean existeMauPos (int pos, int jogadores[]) {

    boolean existe = false;

    for (int i=0; i<jogadores.length; i++)
      if (jogadores[i]==pos)
        existe = true;

    return existe;
  }


  /**
   * Verifica se existe cratera numa dada posicao
   * @param caminho o caminho de jogo 
   * @param pos a posicao a verificar
   * @return true se existe cratera, false caso contrario
   * @requires caminho > 0 && pos >= 1
   * @ensures que existe uma cratera no caminho
   */
  private static boolean existeCratera (int caminho[], int pos) {
    return pos!=MAU_MORTO && caminho[pos]==CRATERA_INT ? true: false;
  }


  /**
   * Gera fuga aleatoria para a direita ou esquerda 
   * @param gerador gerador aleatorio
   * @return fuga aleatoria 
   * @ensures que a fuga dos maus eh aleatoria
   */
  private static boolean ehSentidoFugaDireita ( Random gerador ){ 
    return gerador.nextBoolean();
  }


  /**
   * Verifica se existe pelo menos UMA cratera em todo o caminho
   * (Metodo nao utilizado, mas ficou no codigo para o caso de ser necessario)
   * @param cratera cratera criada pela queda de bolhao
   * @param caminho plano de jogo
   * @return true se existe, falso caso caso contrario
   * @requires caminho > 0
   * @ensures que existe pelo menos uma cratera
   */
  private static boolean existeCratera (int caminho[]){

    boolean existe = false;

    for( int i = 0; i <caminho.length; i++)
      if( caminho[i] == CRATERA_INT)
        existe = true;

    return existe;
  }



}
