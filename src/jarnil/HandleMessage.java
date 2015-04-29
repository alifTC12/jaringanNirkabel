package jarnil;

import static jarnil.InputClient.PORT;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author alif.sip
 */
public class HandleMessage extends Thread
{
    final static String INET_ADDR = "224.0.0.3";
    final static int PORT = 8888;
    
    
    public void SendMessage(message msg, InetAddress addr) throws InterruptedException
    {
        
        try (DatagramSocket serverSocket = new DatagramSocket()) 
        {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(outputStream);
            os.writeObject(msg);
            byte[] data = outputStream.toByteArray();
            DatagramPacket msgPacket = new DatagramPacket(data,data.length, addr, PORT);
            serverSocket.send(msgPacket);
     
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace();
        }
    }
        
        

    
    public void run ()
    {

        InetAddress address = null;
        try 
        {
            address = InetAddress.getByName(INET_ADDR);
        } 
        catch (UnknownHostException ex) 
        {
            Logger.getLogger(HandleMessage.class.getName()).log(Level.SEVERE, null, ex);
        }

        try (MulticastSocket clientSocket = new MulticastSocket(PORT))
        {
            clientSocket.joinGroup(address);
            byte[] buf = new byte[256];
            while (true) 
            {
                DatagramPacket msgPacket = new DatagramPacket(buf, buf.length);
                clientSocket.receive(msgPacket);
                
                byte[] data = msgPacket.getData();
                ByteArrayInputStream in = new ByteArrayInputStream(data);
                ObjectInputStream is = new ObjectInputStream(in);
                
                message pesan = (message) is.readObject();
                
                //String msg = new String(buf, 0, buf.length);
                //msg = msg.trim();
                System.out.println("You have a message: " + pesan.getPesan());
            }
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HandleMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
