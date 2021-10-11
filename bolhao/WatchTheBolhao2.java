/*
 * Esta classe joga o jogo "Watch Out for the Bolhao" varias vezes
 * @author IP
 * @date Novembro de 2016
 */
public class WatchTheBolhao2 {

  /**
   * @param args
   */
  public static void main(String[] args) {

    /* 
          O metodo imprimeResultados tem os seguintes parametros:
             posicao inicial do Bom, posicao inicial do Mau, 
             forca de impacto do Bolhao, dimensao do caminho,
             numero maximo de jogadas, 

          Vamos invocah-lo varias vezes
     */
    imprimeResultados(1, 40, 4, 40, 10);
    imprimeResultados(10, 20, 4, 40, 10);
    imprimeResultados(14, 9, 4, 40, 15);
    imprimeResultados(18, 16, 8, 20, 15);
    imprimeResultados(8, 42, 8, 50, 15);
    imprimeResultados(33, 31, 6, 66, 8);
    imprimeResultados(14, 35, 4, 40, 5);
    imprimeResultados(1, 4, 2, 6, 5);
    imprimeResultados(2, 7, 2, 7, 5);

  }

  /**
   * Imprime no standard output os resultados de jogar o jogo
   * @param iniBom Posicao do Bom
   * @param iniMau Posicao do Mau
   * @param impacto Forca de impacto do Bolhao (numero de posicoes que alaga)
   * @param dim Dimensao do caminho
   * @param maxVezes Numero maximo de iteracoes do jogo
   */
  static void imprimeResultados (int iniBom, int iniMau, int impacto, 
      int dim, int maxVezes){
    System.out.println();
    System.out.println("########################################################");
    System.out.println();
    System.out.println("Executar o metodo");
    System.out.println("JogarBolhao.jogaJogoBolhao(" + iniBom + ", " + iniMau
        + ", " + impacto + ", " + dim + ", " + maxVezes + ")");
    System.out.println("resulta em:");
    JogarBolhao.jogaJogoBolhao(iniBom, iniMau, impacto, dim, maxVezes);

  }

}