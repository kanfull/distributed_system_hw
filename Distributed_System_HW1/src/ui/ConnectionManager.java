package ui;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class ConnectionManager {
	Socket client;
	PrintWriter out;
	BufferedReader in;
	
	public void connect(String host,int port){
		try {
			client=new Socket(host,port);
		} catch (UnknownHostException e) {
			//TODO print unknown host
		} catch (IOException e) {
			//TODO print io exception
		}
	}
	public void send(String message){

	}
	public void disconnect(){
		try {
			client.close();
		} catch (IOException e) {
			//TODO print io exception 
		}
	}
}
