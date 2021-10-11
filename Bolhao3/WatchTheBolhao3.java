import java.util.Random;
import java.util.Scanner;

/**
 * Esta classe joga o jogo "Watch Out for the Bolhao" varias vezes
 * @author IP
 * @date Novembro de 2016
 */
public class WatchTheBolhao3 {

  /**
   * @param args
   */
  public static void main(String[] args) {
    // Objetos para leitura do standard input e para geracao de aleatorios
    Scanner leitor = new Scanner(System.in);
    Random gerador = new Random(1);

    // Numero de posicoes do caminho
    System.out.println("Qual a dimensao do caminho? (entre 1 e 80)");
    int dim = lerValorNoIntervalo(1, 80, "Inteiro entre 1 e 80!", leitor);

    // Alturas minima e maxima para o aviao 
    int alturaMin = 2; 
    int alturaMax = Math.max(alturaMin, dim / 4); 

    // Posicao inicial do aviao que lanca os Bolhoes
    System.out.println("Qual a posicao inicial do aviao?");
    int aviaoX = pedeCoordenada("X", 1, dim, leitor);
    int aviaoY = pedeCoordenada("Y", alturaMin, alturaMax, leitor);

    // Numero maximo de jogadas
    int maxVezes = 10;
    // Quantos Maus
    int nMaus = Math.max(1,dim / 6);
    // Posicoes iniciais dos Maus
    int [] posMaus = aleatoriosDiferentes(nMaus, dim, gerador);

    // Jogar o jogo!
    JogarBolhao3.jogaJogoBolhao3(aviaoX, aviaoY, posMaus, alturaMax, dim,
        maxVezes, gerador, leitor);
  }

  /**
   * Inteiro num dado intervalo correspondente a uma dada coordenada do aviao
   * @param c Qual a coordenada a pedir
   * @param inf Limite inferior para a coordenada
   * @param sup Limite superior para a coordenada
   * @param leitor Objeto Scanner a usar para a leitura
   * @return O inteiro no intervalo [inf,sup] dado pelo utilizador
   */
  private static int pedeCoordenada(String c, int inf, int sup,
      Scanner leitor) {

    System.out.println("Coordenada " + c + ":" + " (inteiro entre " + 
        inf + " e " + sup + ")");
    return lerValorNoIntervalo(inf,sup,
        "Inteiro entre " + inf + " e " + sup + "!", 
        leitor);
  }

  /**
   * Um vetor de valores aleatorios, todos diferentes, num dado intervalo
   * @param n - O numero de elementos que o vetor vai ter
   * @param sup - O limite superior do intervalo
   * @param g - O gerador de aleatorios
   * @requires n <= sup (para poderem ser todos diferentes)
   */
  static int[] aleatoriosDiferentes (int n, int sup, Random g){
    int[] result = new int [n];
    int i = 0;
    while (i < result.length) {
      int aleatorio = g.nextInt(sup) + 1;
      if (!contidoEmParte(aleatorio,result,i)){
        result[i] = aleatorio;
        i++;
      }
    }
    return result;
  }

  /**
   * Um valor estah contido em parte de um array?
   * @param val - O valor a procurar
   * @param v - O array onde procurar
   * @param parte - O indice ateh onde procurar (exclusive)
   * @return true se val estah nas primeiras parte posicoes de v
   * @requires v != null && parte < v.length
   */
  static boolean contidoEmParte (int val, int[] v, int parte){
    boolean encontrou = false;
    for (int i = 0 ; i < parte && !encontrou ; i++)
      if (v[i] == val)
        encontrou = true;
    return encontrou;
  }

  /**
   * Primeiro inteiro no canal de leitura que esta num dado intervalo
   * @param infLim   Limite inferior do intervalo
   * @param supLim  Limite superior do intervalo
   * @param errMess  Mensagem de  erro a apresentar no System.out
   * @param sc  Canal de leitura
   * @return um valor entre infLim e supLim
   * @requires sc != null && infLim <= supLim && errMess != null
   */
  static int lerValorNoIntervalo(int infLim, int supLim, 
      String errMess, Scanner sc) {
    int valor = 0;
    boolean erro;
    do {
      valor = lerInteiro ( errMess, sc );
      erro = valor < infLim || valor > supLim;
      if ( erro )
        System.out.println ( errMess );
    } while ( erro );

    return valor;
  }

  /**
   * Primeiro inteiro no canal de leitura
   * @param errMess - mensagem a escrever no System.out caso o valor 
   *                  acessivel no canal de leitura nao seja um inteiro
   * @param sc - canal de leitura
   * @return valor inteiro
   * @requires errMess != null && sc != null
   */
  static int lerInteiro ( String errMess, Scanner sc ) {
    int valor = 0;
    boolean erro = true;
    do {
      if ( sc.hasNextInt () ) {
        valor = sc.nextInt ();  // consome o inteiro
        erro = false;
      } else {
        sc.next ();      // consome o que lah esteja
        System.out.println ( errMess );
      }               
    } while ( erro );

    return valor;
  }

}