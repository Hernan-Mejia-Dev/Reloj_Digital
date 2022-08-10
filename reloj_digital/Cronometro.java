
package reloj_digital;

import javax.swing.JLabel;

public class Cronometro extends Thread{
    JLabel eti;
    
    public Cronometro(JLabel cronometro){
        this.eti=cronometro;
    }
    
    @Override
    public void run(){
        try{
            while(VentanaCronometro.iniciaHilo){
                Thread.sleep(1000);
                EjecutarHiloCronometro();
            }
        }catch(Exception e){
            System.out.println("Exception en el hilo: "+e.getMessage());
        }
    }

    private void EjecutarHiloCronometro(){
        
        VentanaCronometro.seg++;
        
        if(VentanaCronometro.seg>59){
            VentanaCronometro.seg=0;
            VentanaCronometro.min++;
        }
        if(VentanaCronometro.min>59){
            VentanaCronometro.min=0;
            VentanaCronometro.hora++;
        }
        
        String TextSeg = "", TextMin = "", TextHora = "";
        TextSeg+=VentanaCronometro.seg;
        TextMin+=VentanaCronometro.min;
        TextHora+=VentanaCronometro.hora;
        
        if(TextSeg.length()==1){
            TextSeg = "0"+TextSeg;
        }
        if(TextMin.length()==1){
            TextMin = "0"+TextMin;
        }
        if(TextHora.length()==1){
            TextHora = "0"+TextHora;
        }
        
        String Reloj = TextHora+":"+TextMin+":"+TextSeg;
        eti.setText(Reloj);
    }
}
