package ui;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Application {
	public static void main(String[] args){
		BufferedReader cons = new BufferedReader(new     
				InputStreamReader(System.in));
		
		boolean quit = false;
		
		ConnectionManager manager=new ConnectionManager();
				   
		while (!quit) {
			System.out.print("EchoClient> "); 
			String input;
			try {
				input = cons.readLine();
				String[] tokens = input.trim().split("\\s+");
				if(tokens.length>0){ // check wheter user input something
					
					String command=tokens[0].toLowerCase();
					if(command.equals("connect")){
						
						if(tokens.length==3){
							manager.connect(tokens[1], Integer.parseInt(tokens[2]));
						}else{
							// TODO 
							ConsoleManager.print("Syntax Error");
						}
						
					}if(command.equals("disconnect")){
						
						if(tokens.length==1){
							manager.disconnect();
						}else{
							// TODO 
							ConsoleManager.print("Syntax Error");
						}
						
					}else if(command.equals("send")){
						
						if(tokens.length==2){
							manager.send(tokens[1]);
						}else{
							// TODO 
							ConsoleManager.print("Syntax Error");
						}
						
					}else if(command.equals("loglevel")){
						
					}else if(command.equals("help")){
						
					}else if(command.equals("quit")){
						ConsoleManager.print("Application exit!");
						quit=true;
					}else{
						ConsoleManager.print("Unknow Commend");
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
	}
}
