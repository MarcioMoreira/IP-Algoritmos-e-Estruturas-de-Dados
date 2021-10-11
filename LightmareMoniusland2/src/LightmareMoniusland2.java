/**
 * Esta classe joga o jogo "Lightmare in Moniusland" varias vezes
 * @author IP
 * @date Novembro de 2017
 */
public class LightmareMoniusland2 {

    /**
     * O metodo experimentaJogar da classe JogarLightmare eh invocado
     * para varios valores dos seus parametros
     * @param args - Nao utilizado
     */
    public static void main(String[] args) {
	    
	    /* 
	     * O metodo experimentaJogar da classe JogarLightmare tem 
	     * os seguintes parametros:
	     * posMonius, posOutro, angulo, linhas, colunas, maxVezes
	     */

	    JogarLightmare.experimentaJogar (26, 28, 180, 6, 5, 5);

	    JogarLightmare.experimentaJogar (6 * 5, 10, 180, 6, 5, 5);

	    JogarLightmare.experimentaJogar (3 * 4, 3 * 4 - 1, 180, 3, 4, 5);

	    JogarLightmare.experimentaJogar (16, 15, 180, 3, 6, 5);

	    JogarLightmare.experimentaJogar (9, 5, 180, 3, 6, 5);

	    
	    //System.out.println("TESTE!!!!!!!!!!");
    }
}

