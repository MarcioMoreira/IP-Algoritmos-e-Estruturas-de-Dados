/**
 * Classe que faz algumas verificacoes de acordo com alguma regras
 * e imprime o resultado das mesmas caso nao se verifiquem
 * @author Marcio Moreira 41972 - Pedro Mena 51024
 * @date Outubro 2018
 *
 */
public class Sudoku1 {

	public static void main(String[] args) {

		int d = 9;

		imprimeResultados(123456789 , d);
		imprimeResultados(12345679  , d);
		imprimeResultados(1234567893, d);
		imprimeResultados(234567892 , d);
		imprimeResultados(123456785 , d);
		imprimeResultados(123447789 , d);
		

	}

	/**
	 * Funcao que dados dois numeros inteiros imprime 
	 * @param num numero inteiro composto por varios algarismos a avaliar
	 * @param d numero inteiro a avaliar
	 * @requires 0 < d < 10  && num != 0
	 */
	public static void imprimeResultados ( int num, int d){

		if(verificaCondicoes (num, d) == 0)
			System.out.println("O numero " + num + " passou todas as condicoes verificadas.");
		if(verificaCondicoes (num, d) == 1)
			System.out.println("O numero " + num + " nao verifica a condicao "+'"'+"positivo e tem " 
					+ d + " digitos"+'"'+".");	
		if(verificaCondicoes (num, d) == 2)
			System.out.println("O numero "+ num + " nao verifica a condicao " +'"'+ "maior digito " 
					+ d + " e menor digito " + 1 +'"'+ ".");
		if(verificaCondicoes (num, d) == 3)
			System.out.println("O numero "+ num + " nao verifica a condicao "+'"'+"soma digitos " 
					+ 45 + " e produto digitos " + 362880 + '"' + ".");	

	}



	/**
	 * Funcao que dados dois numeros inteiros avalia segundo as condicoes
	 * @param num numero inteiro composto por varios algarismos a avaliar
	 * @param d numero inteiro a avaliar
	 * @return a menor condicao caso alguma nao se cumpra 
	 * @requires 0 < d < 10 && num != 0
	 */
	public static int verificaCondicoes ( int num, int d){

		int condicao = 0; // todas as condicoes se verificam

			// se !(num > 0) && se num tem d digitos
			// se nao se verificar condicao == 1
		if (!(ehPositivo(num) && contaDigitos(num) == d)){
			condicao = 1;
			return condicao;
		}
			
			// o maior digito de num eh d e o menor eh 1
			// se nao se verificar condicao == 2
		if (!(dEhMaximo(num,d) && umEhMin(num))){ 
			condicao = 2;
			return condicao;
		}
		
		
		// soma dos digitos de num == somatorio de [1->d] && o seu produto == d!
		// se nao se verificar condicao == 3
		if (!(somaDigitos(num) == calcSumUmAD(d) && produtoDigitos(num) == dFactorial(d))){
			condicao = 3;
		}

		return condicao;
	}

	/**
	 * Calcula o somatorio de [1..d]
	 * @param d numero inteiro a avaliar
	 * @return somatorio 
	 * @requires 0 < d < 10
	 */
	public static int calcSumUmAD ( int d ){

		int calculo = 0;
		
		// corre de 1 a d e soma todos
		for(int i = 1; i <= d; i++){
			calculo +=  i;
		}
		return calculo;
	}


	/**
	 * Calcula somatorio dos algarismos que compoem um num
	 * @param num numero que eh avaliado
	 * @return somatorio de todos os algarismos
	 * @requires num != 0
	 */
	public static int somaDigitos ( int num ){

		int soma = 0, digito = 0;

		while (num > 0){
			// resto divisao
			digito = num % 10;
			// soma o resto divisao
			soma += digito;
			// exclui o resto divisao
			num = num / 10;
		}
		return soma;
	}


	/**
	 * Verifica se o menor algarismo que compoe o numero eh = 1
	 * @param num numero que eh avaliado
	 * @return True se o valor minimo == 1
	 * @requires num != 0
	 */
	public static boolean umEhMin ( int num ){

		int min = 1, digito = 0;
		boolean ehMin = false;

		while (num > 0){
			// resto divisao
			digito = num % 10;
			// exclui o resto divisao
			num = num / 10;

			// se digito == 1
			if ( min == digito)
				ehMin = true;
		}
		return ehMin ;	
	}


	/**
	 * Verifica se o maior algarismo que compoe o numero eh = d
	 * @param num numero que eh avaliado digito a digito
	 * @return True se e valor max == d
	 * @requires num != 0
	 */
	public static boolean dEhMaximo ( int num , int d){

		int max = 0, digito = 0;
		int numero = num;

		while ( numero > 0){
			// resto divisao
			digito = numero % 10;
			// exclui o resto divisao
			numero = numero / 10;

			// se maximo < que o resto divisao actualiza
			if(max < digito )
				max = digito;

		}	
		// se valor de max actualizado == d entao o valor max
		// dos digitos de num == d
		return max == d;
	}



	/**
	 * Conta o numero de digitos que compoem um numero
	 * @param num numero que eh avaliado digito a digito
	 * @return numero de digitos
	 * @requires num != 0
	 */
	public static int contaDigitos ( int num ){
		
		// contador
		int counter = 0;

		while (num > 0){
			// exclui o resto divisao
			num = num / 10;
			counter++;
		}
		return counter;
	}

	/**
	 * Calcula produto dos algarismos que compoem um num
	 * @param num numero que eh avaliado
	 * @return produto de todos os algarismos
	 * @requires num != 0
	 */
	public static int produtoDigitos ( int num ){

		int produto = 1, digito = 0;

		while (num > 1){
			// resto divisao
			digito = num % 10;
			// multiplica o acumulador do produto pelos digitos
			produto *= digito;
			// exclui o resto divisao
			num = num / 10;
		}
		return produto;
	}


	/**
	 * Calcula o factorial de [1..d]
	 * @param d numero que eh avaliado
	 * @return factorial de d
	 * @requires 0 < d < 10 && num != 0
	 */
	public static int dFactorial ( int d){

		int fact = 1;

		// corre de 2 a d enquanto acumula multiplica
		for ( int i = 2; i <= d; i++){
			fact *= i;
		}
		return fact ;
	}


	/**
	 * Verifica se um numero eh > 0
	 * @param num numero que eh avaliado
	 * @return True se num > 0
	 */
	public static boolean ehPositivo ( int num ){
		return num > 0;
	}


}
