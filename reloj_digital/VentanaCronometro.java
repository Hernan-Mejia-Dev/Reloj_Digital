
package reloj_digital;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class VentanaCronometro extends javax.swing.JFrame implements ActionListener{

    private JPanel panel;
    private JLabel label;
    private JButton BackMenu;
    private JButton iniciar;
    private JButton pausar;
    private JButton reiniciar;
    
    static int hora = 0, min = 0, seg = 0;
    static boolean iniciaHilo = false;
    boolean running = false;
    
    public VentanaCronometro(){
        this.setResizable(false);
        this.setTitle("Cronometro");
        this.setLocationRelativeTo(null);
        this.setSize(250, 150);
        iniciarComponentes();
        CerrarVentana();
    }

    private void iniciarComponentes(){
        panel = new JPanel();
        panel.setLayout(null);
        this.getContentPane().add(panel);
        
        label = new JLabel("00:00:00");
        label.setFont(new Font("Traditional Arabic", Font.PLAIN,30));
        label.setBounds(55, 11, 140, 45);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label);
        
        iniciar = new JButton("Iniciar");
        iniciar.setFont(new Font("Arial",Font.BOLD,15));
        iniciar.setBounds(20, 55, 90, 25);
        iniciar.addActionListener(this);
        panel.add(iniciar);
        
        pausar = new JButton("Pausar");
        pausar.setFont(new Font("Arial",Font.BOLD,15));
        pausar.setBounds(135, 55, 90, 25);
        pausar.addActionListener(this);
        panel.add(pausar);
        
        reiniciar = new JButton("Reset");
        reiniciar.setFont(new Font("Arial",Font.BOLD,15));
        reiniciar.setBounds(80, 90, 90, 25);
        reiniciar.addActionListener(this);
        panel.add(reiniciar);
        
        BackMenu = new JButton();
        BackMenu.setBounds(200, 10, 30, 30);
        ImageIcon imagen = new ImageIcon("src/Back.jpg");
        BackMenu.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(BackMenu.getWidth(),BackMenu.getHeight(), Image.SCALE_SMOOTH)));
        BackMenu.addActionListener(this);
        panel.add(BackMenu);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==iniciar){
            if(running==false){
                iniciaHilo=true;
                running=true;
                IniciarHiloCronometro();
            }
        }
        if(e.getSource()==pausar){
            running=false;
            iniciaHilo=false;
        }
        if(e.getSource()==reiniciar){
            running=false;
            iniciaHilo=false;
            label.setText("00:00:00");
            seg=0;
            min=0;
            hora=0;
        }
        if(e.getSource()==BackMenu){
            running=false;
            iniciaHilo=false;
            label.setText("00:00:00");
            seg=0;
            min=0;
            hora=0;
            reloj_digital.Reloj.c1.setVisible(false);
            reloj_digital.Main.r1.setVisible(true);
        }
    }
    
    private void IniciarHiloCronometro(){
        if(iniciaHilo==true){
            Cronometro miCronometro = new Cronometro(label);
            miCronometro.start();
        }
    }
    
    private void CerrarVentana(){
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                reloj_digital.Main.r1.setVisible(true);
                running=false;
                iniciaHilo=false;
                label.setText("00:00:00");
                seg=0;
                min=0;
                hora=0;
            }
        });
    }
}