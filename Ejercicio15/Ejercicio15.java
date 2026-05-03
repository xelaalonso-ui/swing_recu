package Ejercicio15;

import javax.swing.*;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.*;

public class Ejercicio15 extends JFrame implements ActionListener {
    JLabel label1;
    JLabel label2;
    JLabel label3;
    JLabel credito;
    JLabel numeros;

    JButton btnJuego;
    double contador = 10;
    Timer timer;
    int segundos;
    boolean colores = false;
    ImageIcon[] imagenes = new ImageIcon[6];

    

    public Ejercicio15() {
        super("Supermenitragaperras");
        setLayout(null);
                      //imagenes
         imagenes[0]=new ImageIcon(getClass().getResource("/Ejercicio15/bebida-de-coco.png"));
         imagenes[1]=new ImageIcon(getClass().getResource("/Ejercicio15/loro.png"));
         imagenes[2]=new ImageIcon(getClass().getResource("/Ejercicio15/pelota-de-playa.png"));
         imagenes[3]=new ImageIcon(getClass().getResource("/Ejercicio15/tabla-de-surf.png"));
         imagenes[4]=new ImageIcon(getClass().getResource("/Ejercicio15/tortuga.png"));
         imagenes[5]=new ImageIcon(getClass().getResource("/Ejercicio15/verano.png"));
        // etiquetas
        label1 = new JLabel();
        label1.setBounds(50, 50, 120, 90);
         label1.setIcon(imagenes[0]);
        
        add(label1);

        label2 = new JLabel();
        label2.setBounds(190, 50, 120, 90);
        label2.setIcon(imagenes[1]);
        add(label2);

        label3 = new JLabel();
        label3.setBounds(330, 50, 120, 90);
        label3.setIcon(imagenes[2]);
        add(label3);

        btnJuego = new JButton("Jugar");
        btnJuego.setBounds(180, 200, 120, 40);
        btnJuego.addActionListener(this);
        add(btnJuego);

        credito = new JLabel();
        credito.setBounds(400, 300, 120, 40);
        credito.setText("Credito=10€");
        add(credito);
   

        timer = new Timer(500, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (colores) {
                    credito.setForeground(Color.PINK);
                } else {
                    credito.setForeground(Color.cyan);
                }
                colores = !colores;
            }

        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnJuego) {
            if (contador > 0) {
                contador--;
                numeroAleatorios();
                credito.setText("credito=" + contador + "€");
                timer.start();
            } else {
                 JOptionPane.showMessageDialog(this, "AS PERDIDO!!");
                credito.setText("sin credito");
                timer.stop();
            }
            // if (timer.isRunning()) {
            //     timer.stop();
            //     credito.setForeground(Color.BLACK); // color neutro al parar
            // } else {
            //     timer.start();
            // }

        }

    }

    public void numeroAleatorios() {

        int n1 = (int) (Math.random() * 6) + 1;
        int n2 = (int) (Math.random() * 6) + 1;
        int n3 = (int) (Math.random() * 6) + 1;
        label1.setIcon(imagenes[n1-1]);//siempre se va a poner menos uno poer que array empieza en 0
        label2.setIcon(imagenes[n2-1]);
        label3.setIcon(imagenes[n3-1]);

        if (n1 == n2 && n2 == n3) {
            contador += 5;
             JOptionPane.showMessageDialog(this, "Ganaste 5€");
            credito.setText("Credito=" + contador);
        } else if (n1 == n2 || n2 == n3 || n1 == n3) {
            contador += 1.5;
             JOptionPane.showMessageDialog(this, "Ganaste 1,5€");
            credito.setText("Credito=" + contador);
        }

    }

}
