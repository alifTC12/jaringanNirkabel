package jarnil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;


public class InputClient 
{

    final static String INET_ADDR = "228.5.6.9";
    final static int PORT = 5678;
    static private HandleMessage handleMessage =new HandleMessage();;
    
    
    public static void main(String[] args) throws UnknownHostException, InterruptedException 
    {
        InetAddress addr = InetAddress.getByName(INET_ADDR);
        String msg;
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
                
                int i = generator.nextInt(1000000000) + 1;
                msg = in.nextLine();
                
                //Date waktu = new Date();
                //SimpleDateFormat formatter = new SimpleDateFormat(pola);
               
                InetAddress pengirim = InetAddress.getLocalHost();
                message dataPesan = new message(i,pengirim.getHostAddress(), msg, "192.168.137.155");
                
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
//        catch (NullPointerException e)
//        {
//            
//        }
    }
    
}
