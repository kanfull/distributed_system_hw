package ui;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class ConnectionManager {
	Socket client;
	LogManager logManager;
	InputStream in;
	OutputStream out;
	
	ConnectionManager(LogManager manager){
		this.logManager=manager;
	}
	
	public void connect(String host,int port){
		try {
			client=new Socket(host,port);
			in = client.getInputStream();
			out = client.getOutputStream();

			String result=new String(this.receive());
			result=result.substring(0, result.lastIndexOf("\n"));
			this.logManager.print(result);
		} catch (UnknownHostException e) {
			this.logManager.print("Do not know host");
		} catch (IOException e) {
			this.logManager.print("Error! Not connected!");
		}
	}
	public void send(String message){
		try {		
			this.send(message.getBytes());
			
			String result=new String(this.receive());
			result=result.substring(0, result.lastIndexOf("\n"));
			this.logManager.print(result);
			
		} catch (NullPointerException e) {
			this.logManager.print("Error! Not connected!");
		} catch (IOException e) {
			this.logManager.print("Error! Not connected!");
		}
	}
	public void disconnect(){
		try {
			in.close();
			out.close();
			client.close();
			this.logManager.print("Connection terminated: "+client.getInetAddress()+" / "+client.getPort());
		} catch (IOException e) {
			this.logManager.print("Error! Not connected!");
		}
	}
	
	private void send(byte[] data) throws IOException{
		out.write(data);
		out.write("\r\n".getBytes());
		out.flush();
	}
	private byte[] receive() throws IOException{
		byte[] result=new byte[128000];
		in.read(result);
		return result;
	}

}
