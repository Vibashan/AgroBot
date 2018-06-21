 import java.io.BufferedReader;
 import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
 
public class Server {
	
	static ServerSocket server = null;
	static Socket client = null;
	static BufferedReader in = null;
	static String line;
	static String sendline;
	static PrintWriter p;
	static BufferedWriter out=null;
	static boolean isConnected=true;
	static Scanner scanner;
	private static final int SERVER_PORT = 8006;
 
	public static void main(String[] args) {
		
	    try{
			server = new ServerSocket(SERVER_PORT); 
			client = server.accept();
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
p=new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
			
			System.out.println("hello");

                    
                          
		}catch (IOException e) {
			System.out.println("Error in opening Socket");
			System.exit(-1);
			isConnected=false;
		}
			scanner = new Scanner(System.in);
	
         if(isConnected)
	    {



for(int i=0;i<10;i++)	
            p.println(i+"");


             while(isConnected){


	        try{

			line = in.readLine();
			if(line!="") 
			System.out.println(line);
			
			
			
            
			if(line.equalsIgnoreCase("exit")){
				isConnected=false;
				server.close();
				client.close();
			}
	        } catch (IOException e) {
				System.out.println("Read failed");
				System.exit(-1);
	        }
          }

       }
else
System.out.println("Error");
System.out.println("Done");

      	
	}
}

