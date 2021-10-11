import java.util.Random;

public class Jogo {
	
	private int MAXJOGADAS = 10;
	
	
	public Jogo(int maxJogadas){
		this.MAXJOGADAS = maxJogadas;
	}
	
	public static int geraRdNum (){
		Random rd = new Random();
		
		int numero = rd.nextInt(100)+1; // zero a 100
		
		return numero;
	}
	
	public void printGameNum (){
		
		System.out.println("Numero Gerado: " + geraRdNum());
	}

}
