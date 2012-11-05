public class FactorialFunction{
	public static void factor(int num){
		int fact = 1;
		for (int i = num; i>1; i--){
			fact = fact*i;
		}
		System.out.println(num + " ! = " + fact);
	}
	public static void main(String [] args){
		for (int x=1; x<=10; x++)
			factor (x);
	}
}