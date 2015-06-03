package jarnil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 *
 * @author Fandazky23
 */
public class message implements Serializable{
    private static long serialVersionUID = 2746996112706624547L;
    private int id_pesan;
    private String pesan, pembuatPesan; 
    private List<String> pengirim = new ArrayList<String>();
    private String penerima;
    private int maxlompatan;
    private long waktuPesan;
    
    message(String pembuatPesan)
    {
        Date waktu = new Date();
        
        this.maxlompatan = 2;
        this.waktuPesan = waktu.getTime();;
        this.id_pesan = 84145;
        //System.out.println(id_pesan);
        this.pesan = "pesan sudah sampai tujuan";
        //this.pengirim.add(pengirim);
        //this.maxlompatan = maxlompatan;
        //this.penerima = penerima;
        this.penerima = pembuatPesan;
    }
    
    message(long time, int lompatan,int id, String pengirim, String pesan, String penerima, String pembuatPesan)
    {
        this.maxlompatan = lompatan;
        this.waktuPesan = time;
        this.id_pesan = id;
        //System.out.println(id_pesan);
        this.pesan = pesan;
        this.pengirim.add(pengirim);
        //this.maxlompatan = maxlompatan;
        this.penerima = penerima;
        this.pembuatPesan = pembuatPesan;
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
    
    public String getPembuatPesan() {
        return pembuatPesan;
    }
    
    public void addPengirimUlang(String pengirim){
        this.pengirim.add(pengirim);
    }
    /*
    public void lihatLompatan()
    {
        System.out.println(this.lompatan);
    }
    * */
}
