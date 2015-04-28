package jarnil;

import static jarnil.InputClient.PORT;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alif.sip
 */
public class HandleMessage extends Thread
{
    final static String INET_ADDR = "224.0.0.3";
    final static int PORT = 8888;
    
    public void SendMessage(String msg, InetAddress addr) throws InterruptedException
    {
        try (DatagramSocket serverSocket = new DatagramSocket()) 
        {
           // for(int a=0; a<3; a++)
            //{
                // Create a packet that will contain the data
                // (in the form of bytes) and send it.
                DatagramPacket msgPacket = new DatagramPacket(msg.getBytes(),msg.getBytes().length, addr, PORT);
                serverSocket.send(msgPacket);
     
                //Thread.sleep(500);
            //}
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace();
        }
    }
        
        

    
    public void run ()
    {
        // Get the address that we are going to connect to.
        
        
        // Create a buffer of bytes, which will be used to store
        // the incoming bytes containing the information from the server.
        // Since the message is small here, 256 bytes should be enough.
       
        InetAddress address = null;
        try 
        {
            address = InetAddress.getByName(INET_ADDR);
        } 
        catch (UnknownHostException ex) 
        {
            Logger.getLogger(HandleMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Create a new Multicast socket (that will allow other sockets/programs
        // to join it as well.
        try (MulticastSocket clientSocket = new MulticastSocket(PORT))
        {
            //Joint the Multicast group.
            clientSocket.joinGroup(address);
     
            while (true) 
            {
                // Receive the information and print it.
                byte[] buf = new byte[256];
                DatagramPacket msgPacket = new DatagramPacket(buf, buf.length);
                clientSocket.receive(msgPacket);
               
                String msg = new String(buf, 0, buf.length);
                msg = msg.trim();
                System.out.println("You have a message: " + msg);
            }
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace();
        }
    }
}
