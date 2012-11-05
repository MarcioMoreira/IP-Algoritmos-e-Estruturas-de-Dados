public class Primo{
	public static boolean ePrimo( long nr ){
		if (nr<2)
			return false;
		for (long i = 2 ; i <= (nr/2) ; i++){
			if (nr%i == 0)
				return false;
		}
		return true;
	}
	public static void main(String [] args){
		long x = 25;
		if (ePrimo (x))
			System.out.println( x + " é primo.");
		else
			System.out.println( x + " não é primo.");
	}
}