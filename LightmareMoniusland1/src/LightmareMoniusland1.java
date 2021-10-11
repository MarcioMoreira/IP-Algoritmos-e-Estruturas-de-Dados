/**
 * Esta classe permite testar as funcoes que os alunos implementaram
 * @author Isabel Nunes
 * @date Outubro 2017
 */
public class LightmareMoniusland1 {

	/**
	 * Neste metodo eh invocada uma funcao, para varios valores de interesse,
	 * que permite testar a funcao que os alunos implementaram
     * @param args Nao sao usados
	 */
	 public static void main(String[] args) {
			
		 		 
		 
	    /* 
	     * A funcao testaFuncao tem os seguintes parametros:
	     * posMonius, posOutro, alcanceLuz, angulo, comprim, altura
	     */
	        	
	    /*
         * Casos particulares: posicao do Monius eh num dos vertices
         * do caminho
	     */  
	     // Monius no vertice superior direito, angulo 180 graus, o Outro 
		 // na mesma coluna do Monius
	     testaFuncao (7, 8, 3, 180.0, 7, 8, true); // no alcance do sabre
	     testaFuncao (7, 21, 3, 180.0, 7, 8, true);
	     testaFuncao (7, 36, 3, 180.0, 7, 8, false); // fora de alcance
	     // Monius no vertice superior direito, angulo 180 graus, o Outro 
		 // numa coluna diferente do Monius
	     testaFuncao (7, 9, 3, 180.0, 7, 8, false);
	     // Monius no vertice superior direito, angulo +90 graus; Soh
	     // atinge os que estao na mesma coluna, a uma distancia conveniente
	     testaFuncao (7, 21, 3, 90.0, 7, 8, true);
	     // Monius no vertice superior direito, angulo -90 graus; nao
	     // atinge ninguem!
	     testaFuncao (7, 8, 3, -90.0, 7, 8, false);
	     /* Experimente com outros vertices...  */
	     
	        	
	    /* 
         * Casos particulares: posicao do Monius eh numa linha ou coluna
         * limitrofe do caminho
	     */	
	       
	     // Monius na coluna esquerda, angulo 180 graus
	     testaFuncao (15, 14, 3, 180.0, 7, 8, true); // virado para Este
	     testaFuncao (15, 12, 3, 180.0, 7, 8, true); 
	     testaFuncao (15, 27, 3, 180.0, 7, 8, true);
	     testaFuncao (15, 29, 3, 180.0, 7, 8, true);
	     testaFuncao (15, 19, 3, 180.0, 7, 8, false);
	     testaFuncao (14, 1, 3, 180.0, 7, 8, true); // virado para Oeste
	     testaFuncao (14, 15, 3, 180.0, 7, 8, true); 
	     testaFuncao (14, 13, 3, 180.0, 7, 8, false);
	     testaFuncao (14, 16, 3, 180.0, 7, 8, false);
	     /* Experimente com outras fronteiras...  */

	     
		/* 
	     * Outros casos
		 */			       
	     
	     testaFuncao (18, 5, 3, 180.0, 7, 8, true); 
	     testaFuncao (18, 20, 3, 180.0, 7, 8, true); 
	     testaFuncao (18, 30, 3, 180.0, 7, 8, false);
	     testaFuncao (18, 5, 3, -180.0, 7, 8, true); 
	     testaFuncao (18, 20, 3, -180.0, 7, 8, true); 
	     testaFuncao (18, 30, 3, -180.0, 7, 8, false);
	     testaFuncao (18, 5, 3, 90.0, 7, 8, false); 
	     testaFuncao (18, 20, 3, 90.0, 7, 8, true); 
	     testaFuncao (18, 30, 3, 90.0, 7, 8, false);
	     testaFuncao (18, 5, 3, -90.0, 7, 8, true); 
	     testaFuncao (18, 20, 3, -90.0, 7, 8, true); 
	     testaFuncao (18, 30, 3, -90.0, 7, 8, false);
	     testaFuncao (23, 22, 3, 180.0, 7, 8, false);
	     testaFuncao (23, 27, 3, 180.0, 7, 8, false);
	     testaFuncao (23, 22, 3, -180.0, 7, 8, false);
	     testaFuncao (23, 27, 3, -180.0, 7, 8, false);
	     testaFuncao (23, 22, 3, 90.0, 7, 8, false);
	     testaFuncao (23, 27, 3, 90.0, 7, 8, false);
	     testaFuncao (23, 22, 3, -90.0, 7, 8, false);
	     testaFuncao (23, 27, 3, -90.0, 7, 8, false);
	     testaFuncao (1, 11, 3, 180.0, 4, 4, true); 
	     testaFuncao (6, 15, 3, 180.0, 4, 4, true); 
	     testaFuncao (6, 13, 3, 180.0, 4, 4, false);
	     testaFuncao (6, 15, 3, -180.0, 4, 4, true); 
	     testaFuncao (6, 13, 3, -180.0, 4, 4, false);
	     testaFuncao (6, 15, 3, 90.0, 4, 4, false); 
	     testaFuncao (6, 13, 3, 90.0, 4, 4, false);
	     testaFuncao (6, 15, 3, -90.0, 4, 4, true); 
	     testaFuncao (6, 13, 3, -90.0, 4, 4, false);


	 } // fim do main

	/**
	 * Invoca a funcao foiAtingido dos alunos
	 * @param posMonius Posicao do Monius 
	 * @param posOutro Posicao do Outro 
	 * @param alcanceLuz Distancia que o sabre de luz alcanca
	 * @param angulo Angulo de movimento do sabre de luz
	 * @param comprim Numero de blocos de comprimento do caminho (colunas)
	 * @param altura Numero de blocos de altura do caminho (linhas)
	 * @param esperado Resultado esperado
	 * 
	 * @requires posMonius >= 1 && posMonius <= comprim x altura && 
	 *           posOutro >= 1 && posOutro < = comprim x altura && 
	 *           posMonius != posOutro &&
	 *           angulo in {-90, 90, -180, 180, 360} &&
	 *           comprim > 0 && altura > 0 && alcanceLuz >= 0
	 */
	 static void testaFuncao (int posMonius, int posOutro, int alcanceLuz,
                              double angulo, int comprim, int altura,
				              boolean esperado) {

	     System.out.println("-----------------------------------------------");
	     boolean result = FuncoesMonius.foiAtingido(posMonius, posOutro, 
	    		                                     alcanceLuz, angulo, 
	    		                                     comprim, altura);
	           	 
	     System.out.println("Na seguinte invocacao: ");
	     System.out.println("   foiAtingido(" + posMonius + "," + posOutro + "," +
	    		            alcanceLuz + "," + angulo + "," + comprim + "," + 
	    		            altura + ")");

	     System.out.print("A sua funcao dah: " + result);
	     System.out.println("      Resultado correto eh: " + esperado);
		 System.out.println("-----------------------------------------------");
	
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		// System.out.println("\n\nESTAH NA LINHA!!!! " + FuncoesMonius.convertPosEmLinha(6, 5, 23));
		// System.out.println("ESTAH NA COLUNA!!!! " + FuncoesMonius.convertPosEmColuna(6, 5, 23));
		 
		// System.out.println("Distancia do M ao O -> " + FuncoesMonius.calculaDistancia(12, 3, 6, 5));


	 }

}