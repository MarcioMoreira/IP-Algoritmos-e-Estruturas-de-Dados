import java.util.Scanner;
import java.util.Random;
import java.lang.Math;
     
    public class Menu
    {
    	
    	static void writeMenu ()	//Menu
    	{	
    		System.out.println("Seleccione uma opcao: ");
            System.out.println("1 - Introduzir linha ");
            System.out.println("2 - Gerar coluna ");
            System.out.println("3 - Sair do programa ");
    	}
 
    	public static void main (String[]args)
    	{
    		Scanner input = new Scanner (System.in); //scanner para a opcao do menu
            
            int option = 0;
            
            String line;
            	
            	do
            	{
            		writeMenu();
                    	                    	                    	
                    if(input.hasNextInt()) //caso o digito inserido seja um inteiro continua a executar
                    {
                    	option = input.nextInt();
                    
                    		if(option==1)	//caso escolha opcao 1
                    		{
                    			System.out.println("Insira uma linha:");
                            
                    			Scanner input2 = new Scanner (System.in); //scanner para a introducao da linha
                            
                    			line = input2.nextLine();
                            
                    			while(line.length() != 12)	// Enquanto o utilizador nao inserir 12 caracteres o programa nao avanca
                    			{
                    				System.out.println("A linha tem " + line.length() +" caractere(s). Dever· ter 12.");
                            		
                    				System.out.println("Insira nova linha:");
                            		
                            		line=input2.nextLine();
                    			}	
                    			
                    			int exclam = 0, arroba = 0, asterisco = 0, cifra = 0, percent = 0, space = 0;
                    			
                    			if(line.length() == 12) //quando o programa tiver os 12 caracteres
                    			{             				
                    				
                    				for(int position = 0; position < line.length(); position ++) //executa ciclo que indica qual o caracter que esta em cada posicao
                    				{
                    					
                    					switch (line.charAt(position))
                    					{
                    						case '!': exclam ++;
                    							break;
                    						case '@': arroba ++;
                    							break;
                    						case '#': asterisco ++;
                    							break;
                    						case '$': cifra ++;
                    							break;
                    						case '%': percent ++;
                    							break;
                    						case '_': space ++;
                    							break;
                    						default:
                    							System.out.println("Caracter(s) invalido(s)");
                    							System.out.println("Insira nova linha:");
                    							
                    							line=input2.nextLine();	
                    					}
                    					
                    				}
                    				
                    				
            					 	if(exclam >= 3)
            					 		line = line.replace('!', '_');
            					 	if(arroba >= 3)
            					 		line = line.replace('@', '_');
            					 	if(asterisco >= 3)
            					 		line = line.replace('#', '_');
            					 	if(cifra >= 3)
            					 		line = line.replace('$', '_');
            					 	if(percent >= 3)
            					 		line = line.replace('%', '_');
                    				
            					 	System.out.println(line);
                    			}
                    			
            					else
            					{				
                    				for(int position = 0; position < line.length(); position ++) //executa ciclo que indica qual o caracter que esta em cada posicao
                    				{
                    					
                    					switch (line.charAt(position))
                    					{
                    						case '!': exclam ++;
                    							break;
                    						case '@': arroba ++;
                    							break;
                    						case '#': asterisco ++;
                    							break;
                    						case '$': cifra ++;
                    							break;
                    						case '%': percent ++;
                    							break;
                    						case '_': space ++;
                    							break;
                    						default:
                    							System.out.println("Caracter(s) invalido(s)");
                    							System.out.println("Insira nova linha:");
                    							
                    							line=input2.nextLine();	
                    					}
                    					
                    				}
                    				
                    				int maior = Math.max(exclam , arroba);
            					 	maior = Math.max(maior , asterisco);
            					 	maior = Math.max(maior , cifra);
            					 	maior = Math.max(maior , percent);
            					 	
            					 	if( space == 12)
            					 		System.out.println("Nao foram inseridos simbolos");
            					 	else if(maior == exclam)
            					 		System.out.println("Caracter: ! ; Ocorrencia: " + maior);
            					 	else if(maior == arroba)
            					 		System.out.println("Caracter: @ ; Ocorrencia: " + maior);
            					 	else if(maior == asterisco)
            					 		System.out.println("Caracter: # ; Ocorrencia: " + maior);
            					 	else if(maior == cifra)
            					 		System.out.println("Caracter: $ ; Ocorrencia: " + maior);
            					 	else if(maior == percent)
            					 		System.out.println("Caracter: % ; Ocorrencia: " + maior);
                    			
                    				
                    			}
                    			System.out.println();
        						
                    		}
                    			
                    		else if (option==2)	//caso escolha opcao 2
                    		{
                    			String conjunto = "!@#$%";
                    			
                    			StringBuilder sb = new StringBuilder();
                    			
                    			Random gerador = new Random();
                    			
                    			for(int i = 1; i <= 3 ; i++)
                    			{
                    				int n = gerador.nextInt(5);
                    				
                    				char symbol = conjunto.charAt(n);
                    				
                    				sb.append(symbol);
                    				
                    				if(i<=2)	//imprime virgulas entre cada simbolo
                    				sb.append(',');
                    				
                    			}
                    			
                    			String coluna = sb.toString();
                    			
                    			System.out.println("A coluna gerada e:");
                    			System.out.println(coluna);
                    			System.out.println();
                    		}
                    		
                    		else if (option!=3)	//caso escolha opÁao 3
                    		{
                    			System.out.println("Introduza uma das opcoes validas!");
                                
                    		}
                    }
                    
                    else//caso o digito inserido nao seja um inteiro emite uma mensagem de erro e pede ao utilizador que insira uma opcao valida
                    {
                    	option = 0;
                    	input.nextLine();
                    	System.out.println("Introduza uma das opcoes validas!");
                    }
                    	
                }
                                   
                while (option!=3);	//opcao Sair
                                                     
        }
}