package AnaDosya;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class OyunTahtasi extends JPanel implements MouseListener{
    Temel[][] OyunTahtasi = new Temel[8][10];
    
    public void MayinDose(){
        int miktar = 0;
        while(miktar < 10){
            //mayinlari random döse
            int Satir = (int)(Math.random() * OyunTahtasi.length);
            int Sutun = (int)(Math.random() * OyunTahtasi[0].length);
            
            while(OyunTahtasi[Satir][Sutun].Mayin){
                Satir = (int)(Math.random() * OyunTahtasi.length);
                Sutun = (int)(Math.random() * OyunTahtasi[0].length);
            }
            OyunTahtasi[Satir][Sutun].Mayin = true;
            miktar++;
        }
    }
    
    public void ortayacik(int satir, int sutun){
        
       
        if(satir<0 || satir>=OyunTahtasi.length || sutun<0 || 
                sutun>=OyunTahtasi[0].length || OyunTahtasi[satir][sutun].getText().length() > 0
                 || OyunTahtasi[satir][sutun].Mayin || OyunTahtasi[satir][sutun].isEnabled() == false){
            return;
        }
        else if(OyunTahtasi[satir][sutun].Miktar !=0){
                OyunTahtasi[satir][sutun].setText(OyunTahtasi[satir][sutun].Miktar + "");
                OyunTahtasi[satir][sutun].setEnabled(false);
        }
        else{
            
            OyunTahtasi[satir][sutun].setEnabled(false);
            
            ortayacik(satir - 1, sutun);//kuzey
            ortayacik(satir + 1, sutun);//guney
            ortayacik(satir, sutun - 1);//dogu
            ortayacik(satir, sutun + 1);//bati
        }
    }
    
    public void MiktarGuncelle(int Satir, int Sutun){
        
        if(!OyunTahtasi[Satir][Sutun].Mayin) return;//mayin yoksa   
        
        for(int satir = Satir-1;satir <= Satir + 1;satir++){
            for(int sutun = Sutun-1;sutun <= Sutun + 1;sutun++){
                try{
                OyunTahtasi[satir][sutun].Miktar++;
                }
                catch(Exception e){
                    
                }
            }
        }
    }
    
    
    public void kazanmasarti(){
         
    }
    
    
    
    public void MayinMiktari(){
         for(int satir = 0;satir < OyunTahtasi.length;satir++){
            for(int sutun = 0;sutun < OyunTahtasi[0].length;sutun++){
                MiktarGuncelle(satir, sutun);
            }
         }
    }
    
     public void MayinGoruntule(){
        for(int satir = 0;satir < OyunTahtasi.length;satir++){
            for(int sutun = 0;sutun < OyunTahtasi[0].length;sutun++){
                if(OyunTahtasi[satir][sutun].Mayin){
                   OyunTahtasi[satir][sutun].setIcon(new ImageIcon("mine.png"));
                }
                else{
                OyunTahtasi[satir][sutun].setText(OyunTahtasi[satir][sutun].getMiktar() + "");
                }
            }
        }
        
        repaint();
        
    }
    
    
    public OyunTahtasi(){
        JFrame ekran = new JFrame("Mayin Tarlasi");
        ekran.setSize(1024, 768);
        ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Gorsellik ekle
        ekran.setLayout(new GridLayout(8, 10));
        
        //OyunTahtasi ekle
        for(int satir = 0;satir < OyunTahtasi.length;satir++){
            for(int sutun = 0;sutun < OyunTahtasi[0].length;sutun++){
                Temel t = new Temel(satir, sutun);
                t.addMouseListener(this);
                ekran.add(t);
                OyunTahtasi[satir][sutun] = t;
            }
        }

        MayinDose();
        MayinMiktari();
        //MayinGoruntule();
        ekran.setVisible(true);
    }

    
    public void OyunBitti(){
        for(int satir = 0;satir < OyunTahtasi.length;satir++){
            for(int sutun = 0;sutun < OyunTahtasi[0].length;sutun++){
                if(OyunTahtasi[satir][sutun].Mayin){
                    OyunTahtasi[satir][sutun].setIcon(new ImageIcon("mine.png"));
                }
            }
        }
    }
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
        if(e.getButton() == 1){
        
            Temel t = (Temel)(e.getComponent());
            if(t.Mayin){
                //OyunBitti();
                MayinGoruntule();
                JOptionPane.showMessageDialog(null, "Kaybettiniz");
                System.exit(0);
            }
            else{
                 ortayacik(t.Satir, t.Sutun);
            }
        }
        else if(e.getButton() == 3){
        
            Temel t = (Temel)(e.getComponent());
            t.gecis();
            //t.setIcon(new ImageIcon("flag.png"));
        }
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
