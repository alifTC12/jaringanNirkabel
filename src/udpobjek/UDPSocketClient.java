/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package udpobjek;

/**
 *
 * @author Fandazky23
 */
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.Scanner;

public class UDPSocketClient {
    DatagramSocket Socket;

    public UDPSocketClient() {

    }

    public void createAndListenSocket() {
        String msg;
        try {

            Socket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName("localhost");
            byte[] incomingData = new byte[1024];
            Scanner input = new Scanner(System.in);
            while(true)
            {
                msg = input.nextLine();
                Student student = new Student(1, "test", msg);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ObjectOutputStream os = new ObjectOutputStream(outputStream);
                os.writeObject(student);
                byte[] data = outputStream.toByteArray();
                DatagramPacket sendPacket = new DatagramPacket(data, data.length, IPAddress, 9876);
                Socket.send(sendPacket);
                System.out.println("Message sent from client");
                Thread.sleep(2000);
            }
            
            //DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
            //Socket.receive(incomingPacket);
            //String response = new String(incomingPacket.getData());
            //System.out.println("Response from server:" + response);
            

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UDPSocketClient client = new UDPSocketClient();
        client.createAndListenSocket();
    }
}

