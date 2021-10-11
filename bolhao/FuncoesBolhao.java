/**
 * Esta classe contem as funcoes pedidas aos alunos na Fase 1 do trabalho
 * @author Isabel Nunes
 * @date Outubro 2016
 */
public class FuncoesBolhao {

  /**
   * Posicao em que o jogador vai ficar apohs o impacto do Bolhao
   * @param origemX Coordenada X da origem do Bolhao
   * @param origemY Coordenada Y da origem do Bolhao
   * @param angulo Angulo em radianos que a trajetoria do Bolhao faz 
   *               com o caminho
   * @param impacto Numero de posicoes fortemente inundadas pelo impacto
   *                do Bolhao para a direita e para a esquerda
   * @param posJogador Posicao onde o jogador se encontra quando o 
   *                   Bolhao cai
   * @param dim Numero de posicoes do caminho
   *
   * @return Se o impacto do Bolhao eh fora dos limites do caminho ou se o 
   *         posJogador estah fora das zonas de impacto, o resultado eh 
   *         posJogador
   *         Se posJogador estah dentro da zona principal de impacto (zona 
   *         de afogamento), o resultado eh -1
   *         Se posJogador estah dentro da zona de impacto secundario, o 
   *         resultado depende de posJogador e de impacto (de acordo com o
   *         enunciado).
   * 
   * @requires origemX >= 1 && origemY >= 1 && posJogador >= 1 && 
   *           origemX <= dim && origemY <= dim && posJogador <= dim &&
   *           dim > 0 && angulo > 0 && angulo < Math.PI
   */
  public static int efeitoBolhao (int origemX, int origemY, double angulo,
      int impacto, int posJogador, int dim) {

    int result = posJogador;

    int posAlvo = posicaoImpacto(origemX, origemY, angulo);

    if (posAlvo >= 1 && posAlvo <= dim) {

      int distancia = Math.abs(posJogador - posAlvo);

      if (distancia <= impacto) { // jogador vai-se afogar

        result = -1;

      } else if (distancia <= 2 * impacto) { // jogador eh arrastado

        int arrast = arrastamento (impacto, distancia);

        if (posJogador < posAlvo) { // jogador ah direita do alvo

          result = posicaoEsquerda(arrast,posJogador);

        } else {                   // jogador ah esquerda do alvo

          result = posicaoDireita(arrast,posJogador,dim);

        }
      }
    }  

    return result; 
  }

  /**
   * Posicao em que o jogador vai ficar apohs ser arrastado para a direita
   * @param arrast O numero de posicoes que deve mover-se
   * @param posJogador A posicao em que se encontra antes do arrastamento
   * @param dim O numero de posicoes no caminho
   * @return Posicao em que o jogador fica apohs ser arrastado arrast
   *         posicoes ah direita; se ultrapassa o limite do caminho 
   *         (posicao dim) entao a nova posicao eh a que resulta do 
   *         ricochete na parede
   */
  static int posicaoDireita (int arrast, int posJogador, int dim) {
    int novaPos = posJogador + arrast;
    if (novaPos > dim) {
      novaPos = dim - (novaPos - dim);
    }
    return novaPos;
  }

  /**
   * Posicao em que o jogador vai ficar apohs ser arrastado para a esquerda
   * @param arrast O numero de posicoes que deve mover-se
   * @param posJogador A posicao em que se encontra antes do arrastamento
   * @return Posicao em que o jogador fica apohs ser arrastado arrast
   *         posicoes ah esquerda; se ultrapassa o limite do caminho 
   *         (posicao 1) entao a nova posicao eh a que resulta do 
   *         ricochete na parede
   */
  static int posicaoEsquerda(int arrast, int posJogador) {
    int novaPos = posJogador - arrast;
    if (novaPos < 1) {
      novaPos = 1 - (novaPos - 1) ;
    }
    return novaPos;
  }

  /**
   * Arrastamento que o jogador deve sofrer com o impacto do Bolhao se
   * estah a uma distancia da posicao de impacto que eh superior ao valor
   * do impacto do Bolhao.
   * @param impacto Numero de posicoes afetadas pelo impacto do Bolhao
   *                para a direita e para a esquerda
   * @param distancia Distancia (em valor absoluto) a que o jogador se 
   *                  encontrava da posicao de impacto antes do impacto do
   *                  Bolhao
   * @return O arrastamento que o jogador vai sofrer como consequencia do 
   *         impacto do Bolhao. 
   *         - Se distancia > 2 * impacto o arrastamento eh zero
   *         - Senao:
   *           - Se impacto == 1 entao arrastamento eh 1
   *           - Se impacto == 2:
   *              - se distancia == impacto + 1 entao arrastamento eh 2
   *              - se distancia == impacto + 2 entao arrastamento eh 1
   *           - Se impacto >= 3:
   *             - Seja I o intervalo ]impacto, 2 * impacto]; o arrastamento
   *               serah de:
   *               - impacto, se distancia estah no primeiro terco de I
   *               - impacto/2, se distancia estah no segundo terco de I
   *               - impacto/4, se distancia estah no ultimo terco de I
   * @requires impacto > 0 && distancia > impacto   
   */
  static int arrastamento(int impacto, int distancia) {

    long arrast = 0;

    if (distancia <= 2 * impacto) {

      if (impacto == 1) {

        arrast = 1;

      } else if (impacto == 2) {

        arrast = 3 - (distancia - impacto);

      } else if (impacto >= 3) {

        if (distancia <= impacto + Math.round (impacto / 3.0)) {
          arrast = impacto;
        } else if (distancia <= impacto + Math.round (2 * impacto / 3.0)) {
          arrast = Math.round(impacto / 2.0);
        } else {
          arrast = Math.round(impacto / 4.0);
        }
      }

    }
    return (int) arrast;
  }

  /**
   * Posicao do caminho onde o Bolhao vai cair
   * @param origemX Coordenada X da origem do Bolhao
   * @param origemY Coordenada Y da origem do Bolhao
   * @param angulo Angulo em radianos que a trajetoria do Bolhao faz 
   *               com o caminho
   * @requires origemX >= 1 && origemY >=1 && origemX <= dim && 
   *           origemY <= dim && angulo > 0 && angulo < Math.PI
   * @return Posicao do impacto de um Bolhao cuja origem eh o ponto 
   *         (origemX,origemY) e cuja trajetoria faz um angulo ang com o
   *         caminho
   */
  static int posicaoImpacto(int origemX, int origemY, double ang) {

    double declive = Math.tan (ang);
    return (int) Math.round (origemX - origemY / declive);
  }
}