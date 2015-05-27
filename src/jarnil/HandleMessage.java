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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author alif.sip
 */
public class HandleMessage extends Thread
{
    final static String INET_ADDR = "228.5.6.9";
    final static int PORT = 5678;
    
    List<message> bufferMessage = new ArrayList<message>();
    
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
    
    public void ResendMessage(message msg)
    {
        try (DatagramSocket serverSocket = new DatagramSocket()) 
        {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(outputStream);
            os.writeObject(msg);
            byte[] data = outputStream.toByteArray();
            DatagramPacket msgPacket = new DatagramPacket(data,data.length, InetAddress.getByName(INET_ADDR), PORT);
            serverSocket.send(msgPacket);
     
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace();
        }
    }
     
    public boolean CekPenerima(String penerima, String alamatku)
    {
        if(penerima.equalsIgnoreCase(alamatku)) return true;
        else return false;
    }
    
    public String CekAlamatku() throws UnknownHostException
    {
        InetAddress alamatku = InetAddress.getLocalHost();
        return alamatku.getHostAddress();
    }
    
    public boolean CekIdPesan(message pesan){
        int flag = 0;
        for(int i=0;i<bufferMessage.size();i++)
        {
            if(bufferMessage.get(i).getId()==pesan.getId())
            {
                flag++;
            }
        }
        
        if(flag==0)
            return false;
        else 
            return true;
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
            //System.out.printf("haha");
            clientSocket.joinGroup(address);
            byte[] buf = new byte[256];
            while (true) 
            {
                DatagramPacket msgPacket = new DatagramPacket(buf, buf.length);
                clientSocket.receive(msgPacket);
                //System.out.printf("haha");
                byte[] data = msgPacket.getData();
                ByteArrayInputStream in = new ByteArrayInputStream(data);
                ObjectInputStream is = new ObjectInputStream(in);
                
                message pesan = (message) is.readObject();
                //pesan.updateLompatan();
                
                if (CekIdPesan(pesan) == false)
                {
                    System.out.println("You have a message: " + pesan.getSemua());
                    bufferMessage.add(pesan);
                    if (CekPenerima(pesan.getPenerima(), CekAlamatku()) == false)
                    {
                        SendMessage(pesan, address);
                        //pesan.lihatLompatan();
                    }
                }
                
                /*
                if(pesan.getLompatan() > 0 )
                {
                    if (CekPenerima(pesan.getPenerima(), CekAlamatku()) == false)
                    {
                        SendMessage(pesan, address);
                        //pesan.lihatLompatan();
                    }
                    
                }
                * */
                //String msg = new String(buf, 0, buf.length);
                //msg = msg.trim();
                //System.out.println("You have a message: " + pesan.getSemua());
            }
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HandleMessage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(HandleMessage.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
