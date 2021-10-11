
public class Jogador {

	int numJogada;
	String nomeJogador;
	int numPontos;
	public int [] jogadasFeitas; 
	
	public Jogador(int numJogada, String nomeJogador, int numPontos){
		this.nomeJogador = nomeJogador;
		this.numJogada = numJogada;
		this.numPontos = numPontos;
	}
	
	public void playerStats(){
		System.out.println("--------------------------------------------------");
		System.out.println("|                                                |");
		System.out.println("|                 PLAYER STATS                   |");
		System.out.println("|                                                |");
		System.out.println("--------------------------------------------------");
		System.out.println("|                                                |");
		System.out.println("|   Player           Jogada             Pontos   |");
		System.out.println("|                                                |");
		System.out.println("|   " + nomeJogador + "              " + numJogada + "                  " + numPontos + "     |");
		System.out.println("|                                                |");
		System.out.println("--------------------------------------------------");
		
	}
	
	
}
