package Ejercicio15;

import javax.swing.*;

import java.awt.Color;
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

    public Ejercicio15() {
        super("Supermenitragaperras");
        setLayout(null);
        // etiquetas
        label1 = new JLabel();
        label1.setBounds(50, 50, 120, 90);
        label1.setIcon(new ImageIcon("Ejercicio15\\gatito.png"));
        add(label1);

        label2 = new JLabel();
        label2.setBounds(190, 50, 120, 90);
        label2.setIcon(new ImageIcon("Ejercicio15\\pinguino (2).png"));
        add(label2);

        label3 = new JLabel();
        label3.setBounds(330, 50, 120, 90);
        label3.setIcon(new ImageIcon("Ejercicio15\\zorro.png"));
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

            } else {
                 JOptionPane.showMessageDialog(this, "PERDISTE!!");
                credito.setText("sin credito");
            }
            if (timer.isRunning()) {
                timer.stop();
                credito.setForeground(Color.BLACK); // color neutro al parar
            } else {
                timer.start();
            }

        }

    }

    public void numeroAleatorios() {
        int n1 = (int) (Math.random() * 6) + 1;
        int n2 = (int) (Math.random() * 6) + 1;
        int n3 = (int) (Math.random() * 6) + 1;
        label1.setText("" + n1);
        label2.setText("" + n2);
        label3.setText("" + n3);

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
