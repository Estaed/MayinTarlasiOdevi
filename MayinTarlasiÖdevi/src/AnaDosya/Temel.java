package AnaDosya;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Temel extends JButton {
    int Satir;
    int Sutun;
    int Miktar;
    boolean Mayin;
    boolean bayrak;

    public Temel(int Satir, int Sutun){
        this.Satir = Satir;
        this.Sutun = Sutun;
        bayrak = false;
    }
    
    public void gecis(){
        bayrak = !bayrak;
        if(bayrak){
            this.setIcon(new ImageIcon("flag.png"));
        }
        else{
            this.setIcon(null);
        }
    }
    
    public int getMiktar() {
        return Miktar;
    }

    public void setMiktar(int Miktar) {
        this.Miktar = Miktar;
    }

    
    
    
    
    public int getSatir() {
        return Satir;
    }

    public void setSatir(int Satir) {
        this.Satir = Satir;
    }

    public int getSutun() {
        return Sutun;
    }

    public void setSutun(int Sutun) {
        this.Sutun = Sutun;
    }
   
}
