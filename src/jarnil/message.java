package jarnil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Fandazky23
 */
public class message implements Serializable{
    private static long serialVersionUID = 2746996112706624547L;
    private int id_pesan;
    private String pesan; 
    private List<String> pengirim = new ArrayList<String>();
    private String penerima;
    private int maxlompatan;
    private long waktuPesan;
    
    message()
    {
        
    }
    message(long time, int lompatan,int id, String pengirim, String pesan, String penerima)
    {
        this.maxlompatan = lompatan;
        this.waktuPesan = time;
        this.id_pesan = id;
        //System.out.println(id_pesan);
        this.pesan = pesan;
        this.pengirim.add(pengirim);
        //this.maxlompatan = maxlompatan;
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

    public int getmaxLompatan() {
        return maxlompatan;
    }
    
    public int updateLompatan()
    {
        return this.maxlompatan = maxlompatan - 1;
    }
    
    public String getSemua()
    {
        return pengirim + " >> pesan: "+ pesan;
    }
    
    public long getwaktuPesan()
    {
        return waktuPesan;
    }
    /*
    public void lihatLompatan()
    {
        System.out.println(this.lompatan);
    }
    * */
}
