/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import java.util.Scanner;

/**
 *
 * @author alif.sip
 */
public class InputClient 
{

    final static String INET_ADDR = "228.5.6.7";
    final static int PORT = 4321;
    static private HandleMessage handleMessage =new HandleMessage();;
    
    
    public static void main(String[] args) throws UnknownHostException, InterruptedException 
    {
        InetAddress addr = InetAddress.getByName(INET_ADDR);
        String msg;
        String pola =  "H:mm:ss:SSS";
        handleMessage.start();
        try (DatagramSocket serverSocket = new DatagramSocket()) 
        {
            
            Scanner in = new Scanner(System.in);
            while(true) 
            {
                msg = in.nextLine();
                
                //Date waktu = new Date();
                //SimpleDateFormat formatter = new SimpleDateFormat(pola);
                InetAddress pengirim = InetAddress.getLocalHost();
                message dataPesan = new message(pengirim.getHostAddress(), msg, 3, "192.168.0.5");
                
                handleMessage.SendMessage(dataPesan, addr);
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
