/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jarnil;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Fandazky23
 */
public class message implements Serializable{
    String pesan;
    String waktuKirim;
    ArrayList<String> daftarIP;
    message()
    {
        
    }
    message(String waktuKirim, String pesan)
    {
        this.pesan = pesan;
        this.waktuKirim = waktuKirim;
    }
    
    public String getPesan()
    {
        return "time: " + waktuKirim +"\n" +"pesan: "+ pesan;
    }
}
