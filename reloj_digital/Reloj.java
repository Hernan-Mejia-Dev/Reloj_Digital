package reloj_digital;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

public class Reloj extends javax.swing.JFrame implements Runnable{
    String hora, min, seg, ampm;
    Calendar calendario;
    Thread h1;
    static VentanaCronometro c1 = new VentanaCronometro();
    public Reloj() {
        initComponents();
        h1 = new Thread(this);
        h1.start();
    }
                    
    private void initComponents() {
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Reloj");
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setSize(300, 210);
        
        panel = new javax.swing.JPanel();
        panel.setLayout(null);
        this.getContentPane().add(panel);

        LBL_reloj = new javax.swing.JLabel();
        LBL_reloj.setBounds(160, 65, 150, 50);
        LBL_reloj.setFont(new Font("Traditional Arabic", Font.PLAIN,20));
        panel.add(LBL_reloj);
        
        CronoBoton = new javax.swing.JButton("Cronometro");    
        CronoBoton.setBounds(20, 30, 120, 20);
        panel.add(CronoBoton);
        EventosBotonCrono();
        
        TempoBoton = new javax.swing.JButton("Temporizador"); 
        TempoBoton.setBounds(20, 80, 120, 20);
        panel.add(TempoBoton);
        EventosBotonTempo();
        
        AlarmBoton = new javax.swing.JButton("Alarma");
        AlarmBoton.setBounds(20, 130, 120, 20);
        panel.add(AlarmBoton);
    }                      
                  
    private javax.swing.JLabel LBL_reloj;
    private javax.swing.JPanel panel;
    private javax.swing.JButton CronoBoton;
    private javax.swing.JButton TempoBoton;
    private javax.swing.JButton AlarmBoton;

    @Override
    public void run() {
        
        Thread ct= Thread.currentThread();
        while(ct==h1){
            CalcularHora();
            LBL_reloj.setText(hora+":"+min+":"+seg+"  "+ampm);
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){}
        }
    }

    private void CalcularHora() {
        Calendar calendario = new GregorianCalendar();
        Date FechaHoraActual = new Date();
        
        calendario.setTime(FechaHoraActual);
        ampm = calendario.get(Calendar.AM_PM)==Calendar.AM?"AM":"PM";
        
        if(ampm.equals("PM")){
            int h = calendario.get(Calendar.HOUR_OF_DAY)-12;
            hora = h > 9 ? "" + h : "0" + h;//si es menor que 9 se agrega un 0 antes del numero para cumplir el formato de hora
            if(h==00){
                hora="12";
            }else{
                hora=h > 9 ? "" + h : "0" + h;//si es menor que 9 se agrega un 0 antes del numero para cumplir el formato de hora
            }
        }else{
            hora = calendario.get(Calendar.HOUR_OF_DAY) > 9 ? "" +calendario.get(Calendar.HOUR_OF_DAY) : "0" +calendario.get(Calendar.HOUR_OF_DAY);
        }
        min = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE) : "0" + calendario.get(Calendar.MINUTE);
        seg = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND) : "0" + calendario.get(Calendar.SECOND);
    }
    
    private void EventosBotonCrono(){
        MouseListener ClicksRaton = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                c1.setVisible(true);
                reloj_digital.Main.r1.setVisible(false);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                c1.setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }
        };
        CronoBoton.addMouseListener(ClicksRaton);
    }
    
    private void EventosBotonTempo(){
        MouseListener ClicksRaton = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                c1.setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                c1.setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }
        };
        TempoBoton.addMouseListener(ClicksRaton);
    }
}

