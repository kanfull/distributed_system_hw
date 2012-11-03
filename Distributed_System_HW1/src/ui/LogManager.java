package ui;

import java.io.IOException;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class LogManager {
	Logger myLogger;
	LogManager() throws IOException{
		this.myLogger = Logger.getLogger(Application.class);
		String logDir = "./logs/client.log";
		String pattern = "%d{ISO8601} %-5p [%t] %c: %m%n";
		PatternLayout pLayout = new PatternLayout(pattern);
		FileAppender fa = new FileAppender(pLayout, logDir, true );
		this.myLogger.addAppender(fa);
	}
	public void setLevel(String level){
		level=level.toUpperCase();
		if(level.equals("ALL")){
			this.myLogger.setLevel(Level.ALL);
			this.print("Current log status is "+this.getLevel());
		}else if(level.equals("DEBUG")){
			this.myLogger.setLevel(Level.DEBUG);
			this.print("Current log status is "+this.getLevel());
		}else if(level.equals("INFO")){
			this.myLogger.setLevel(Level.INFO);
			this.print("Current log status is "+this.getLevel());
		}else if(level.equals("WARN")){
			this.myLogger.setLevel(Level.WARN);
			this.print("Current log status is "+this.getLevel());
		}else if(level.equals("ERROR")){
			this.myLogger.setLevel(Level.ERROR);
			this.print("Current log status is "+this.getLevel());
		}else if(level.equals("FATAL")){
			this.myLogger.setLevel(Level.FATAL);
			this.print("Current log status is "+this.getLevel());
		}else if(level.equals("OFF")){
			this.myLogger.setLevel(Level.OFF);
			this.print("Current log status is "+this.getLevel());
		}else {
			this.print("We do not know log level "+level);
		}
	}
	public Level getLevel(){
		return this.myLogger.getLevel();
	}
	public void log(String text){
		this.myLogger.info("EchoClient> "+text);
	}
	public void print(String text){
		System.out.println("EchoClient> "+text);
		this.log(text);
	}
}
