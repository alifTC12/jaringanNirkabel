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

    final static String INET_ADDR = "224.0.0.3";
    final static int PORT = 8888;
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
                
                Date waktu = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat(pola);                
                message dataPesan = new message(formatter.format(waktu), msg);
                
                
                handleMessage.SendMessage(dataPesan, addr);
            }
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace();
        }
    }
    
}
