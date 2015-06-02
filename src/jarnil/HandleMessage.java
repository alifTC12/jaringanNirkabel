package jarnil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HandleMessage extends Thread
{
    final static String INET_ADDR = "228.5.6.9";
    final static int PORT = 5670;
    
    List<message> bufferMessage = new ArrayList<message>();
    List<Integer> bufferId = new ArrayList<Integer>();
    Timer timer = new Timer();
    class SendMessage extends TimerTask{
        long wkt = System.currentTimeMillis();
        public void run() 
        {
            if(bufferMessage.size()>0){
                for(int i=0;i<bufferMessage.size();i++)
                {
                    Date waktu = new Date();
                    long waktusekarang = waktu.getTime();
                    
                    if(waktusekarang - bufferMessage.get(i).getwaktuPesan() > 30000)
                    {
                        bufferMessage.remove(i);
                        break;
                    }
                    System.out.println("isi buffer :" + bufferMessage.size());
                    System.out.println("waktu sekarang" + waktusekarang + "\nWaktu pesan :" + bufferMessage.get(i).getwaktuPesan());
                    try (DatagramSocket serverSocket = new DatagramSocket()) 
                    {
                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                        ObjectOutputStream os = new ObjectOutputStream(outputStream);
                        os.writeObject(bufferMessage.get(i));
                        byte[] data = outputStream.toByteArray();
                        DatagramPacket msgPacket = new DatagramPacket(data, data.length, InetAddress.getByName(INET_ADDR), PORT);
                        serverSocket.send(msgPacket);

                     } 
                     catch (IOException ex) 
                     {
                        ex.printStackTrace();
                     }
                }
            }
            else
            {
                System.out.println("buffer habis");
                cancel();
                //timer.cancel();
                //timer.purge();
            }

        }
    }
    
    public void SendMessage() throws InterruptedException
    {
        Timer timer = new Timer();
        timer.schedule(new SendMessage(), 0, 3000); 
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
    
    public void isiBufferMessage(message pesan)
    {
        this.bufferMessage.add(pesan);
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
                pesan.updateLompatan();

                if (CekIdPesan(pesan) == false)
                {
                    System.out.println("You have a message: " + pesan.getSemua());
                    System.out.println("jumlah lompatan : " + pesan.getmaxLompatan());
                    bufferMessage.add(pesan);
                    if (CekPenerima(pesan.getPenerima(), CekAlamatku()) == false/* && pesan.getmaxLompatan()>0*/)
                    {
                        SendMessage();
                    }
                }
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HandleMessage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(HandleMessage.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
