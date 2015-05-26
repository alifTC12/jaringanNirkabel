package jarnil;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Fandazky23
 */
public class message implements Serializable{
    int id_pesan;
    String pesan; 
    List<String> pengirim = new ArrayList<String>();
    String penerima;
    int lompatan;
    message()
    {
        
    }
    message(int id, String pengirim, String pesan, String penerima)
    {
        this.id_pesan = id;
        this.pesan = pesan;
        this.pengirim.add(pengirim);
        //this.lompatan = lompatan;
        this.penerima = penerima;
    }

    public int getId() {
        return id_pesan;
    }
    
    public String getPesan() {
        return pesan;
    }

    public List<String> getPengirim() {
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
    
    
    /*
    public void lihatLompatan()
    {
        System.out.println(this.lompatan);
    }
    * */
}
