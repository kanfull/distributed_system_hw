import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
	public static void main(String[] args){
		BufferedReader cons = new BufferedReader(new     
				InputStreamReader(System.in));
		
		boolean quit = false;
				   
		while (!quit) {
			System.out.print("EchoClient> "); 
			String input;
			try {
				input = cons.readLine();
				System.out.println(input);
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
	}
}
