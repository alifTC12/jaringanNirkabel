/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jarnil;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author alif.sip
 */
public class InputClient 
{

    /**
     * @param args the command line arguments
     */
    final static String INET_ADDR = "224.0.0.3";
    final static int PORT = 8888;
    static private HandleMessage handleMessage =new HandleMessage();;
    //handleMessage = 
    
    public static void main(String[] args) throws UnknownHostException, InterruptedException 
    {
        // Get the address that we are going to connect to.
        InetAddress addr = InetAddress.getByName(INET_ADDR);
        String msg;
        
        handleMessage.start();
        // Open a new DatagramSocket, which will be used to send the data.
        try (DatagramSocket serverSocket = new DatagramSocket()) 
        {
            Scanner in = new Scanner(System.in);
            while(true) 
            {
                msg = in.nextLine();
                handleMessage.SendMessage(msg, addr);
            }
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace();
        }
    }
    
}
