package jarnil;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;



public class InputClient 
{

    final static String INET_ADDR = "224.0.0.117";
    final static int PORT = 8899;
   // static private HandleMessage handleMessage =new HandleMessage();
    
    
    public static void main(String[] args) throws UnknownHostException, InterruptedException 
    {
        InetAddress addr = InetAddress.getByName(INET_ADDR);
        String msg;
        HandleMessage handleMessage =new HandleMessage();
        String pola =  "H:mm:ss:SSS";
        handleMessage.start();
        try (DatagramSocket serverSocket = new DatagramSocket()) 
        {
            //int a =0;
            Scanner in = new Scanner(System.in);
            Random generator = new Random(); 
            while(true) 
            {
                //a++;
                
                int i = generator.nextInt(1000000) + 1;
                msg = in.nextLine();
                
                //Date waktu = new Date();
                //SimpleDateFormat formatter = new SimpleDateFormat(pola);
               
                Date waktu = new Date();
                long miliseconds = waktu.getTime();
                
                int lompatan = 2;
                InetAddress pengirim = InetAddress.getLocalHost();
                message dataPesan = new message(miliseconds,lompatan,i,/*pengirim.getHostAddress(),*/ msg, "192.168.173.3", pengirim.getHostAddress());
                
                handleMessage.isiBufferMessage(dataPesan);
                handleMessage.bufferId.add(i);
                //handleMessage.SendMessage(dataPesan, addr);
                handleMessage.SendMessage();
            }
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace();
        }
    }
    
}
