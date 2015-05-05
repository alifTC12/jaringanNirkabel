/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jarnil;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.ArrayList;

/**
 *
 * @author Fandazky23
 */
public class message implements Serializable{
    String pesan; 
    String pengirim;
    String penerima;
    int lompatan;
    message()
    {
        
    }
    message(String pengirim, String pesan, int lompatan, String penerima)
    {
        this.pesan = pesan;
        this.pengirim = pengirim;
        this.lompatan = lompatan;
        this.penerima = penerima;
    }

    public String getPesan() {
        return pesan;
    }

    public String getPengirim() {
        return pengirim;
    }

    public String getPenerima() {
        return penerima;
    }

    public int getLompatan() {
        return lompatan;
    }
    
    public int updateLompatan()
    {
        return this.lompatan = lompatan - 1;
    }
    
    public String getSemua()
    {
        return pengirim + " >> pesan: "+ pesan;
    }
    
    public void lihatLompatan()
    {
        System.out.println(this.lompatan);
    }
}
