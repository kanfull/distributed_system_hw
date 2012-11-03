package ui;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {
	public static void main(String[] args){
		BufferedReader cons = new BufferedReader(new     
				InputStreamReader(System.in));
		
		LogManager logManager=null;
		try {
			logManager = new LogManager();
		} catch (IOException e1) {
			System.exit(1);
		}
		
		ConnectionManager connectionManager=new ConnectionManager(logManager);

		boolean quit = false;		   
		while (!quit) {
			System.out.print("EchoClient> "); 
			String input;
			try {
				input = cons.readLine().trim();
				logManager.log(input);
				
				String[] tokens = input.split("\\s+");
				if(tokens.length>0){ // check wheter user input something
					
					String command=tokens[0].toLowerCase(); // getCommand
					if(command.equals("connect")){	// if it is connect command
						
						if(tokens.length==3){
							connectionManager.connect(tokens[1], Integer.parseInt(tokens[2]));
						}else{
							logManager.print("Syntax Error: connect <address> <port>");
						}
						
					}else if(command.equals("disconnect")){ // if it is disconnect command
						
						if(tokens.length==1){
							connectionManager.disconnect();
						}else{
							logManager.print("Syntax Error: disconnect");
						}
						
					}else if(command.equals("send")){// if it is send command
						
						if(tokens.length>=2){
							connectionManager.send(input.substring(5));
						}else{
							logManager.print("Syntax Error: send <message>");
						}
						
					}else if(command.equals("loglevel")){// if it is loglevel command
						
						if(tokens.length==2){
							logManager.setLevel(tokens[1]);
						}else{
							logManager.print("Syntax Error: logLevel <level>");
						}
						
					}else if(command.equals("help")){// if it is help command
						
						if(tokens.length==2){
							String cmd=tokens[1].toLowerCase();
							if(cmd.equals("connect")){
								logManager.print("Syntax: connect <address> <port> \n"+
										"Tries to establish a TCP- connection to " +
										"the echo server based on the given server address " +
										"and the port number of the echo service.");
							}else if(cmd.equals("disconnect")){
								logManager.print("Syntax: disconnect \n"+
										"Tries to disconnect from the connected server.");
							}else if(cmd.equals("send")){
								logManager.print("Syntax: send <message> \n"+
										"Sends a text message to the echo server " +
										"according to the communication protocol.");
							}else if(cmd.equals("loglevel")){
								logManager.print("Syntax: logLevel <level> \n"+
										"Sets the logger to the specified log level");
							}else if(cmd.equals("quit")){
								logManager.print("Syntax: quit \n"+
										"Tears down the active connection to the " +
										"server and exits the program execution.");
							}else{
								logManager.print("No command found");
							}
							
						}else{
							logManager.print("Syntax Error: help <command>");
						}
						
					}else if(command.equals("quit")){// if it is quit command
						
						if(tokens.length==1){
							logManager.print("Application exit!");
							quit=true;
						}else{
							logManager.print("Syntax Error: quit");
						}
						
					}else{// cannot find known command
						logManager.print("Unknown Command");
					}
				}
			} catch (IOException e) {
				logManager.print("Cannot read data from console");
			} 
		}
		
		
	}
}
