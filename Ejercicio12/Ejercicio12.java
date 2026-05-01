package Ejercicio12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Ejercicio12 extends JFrame
        implements ActionListener, KeyListener, MouseListener, MouseMotionListener {

    JButton btnAzul;
    JButton btnAmarillo;
    JButton btnAleatorio;
    JLabel lblTecla;

    // ── Constructor
    public Ejercicio12() {
        super("Ejercicio 11");
        // setTitle("Ejercicio 11");
        setLayout(null);

        // boton 1
        btnAzul = new JButton("boton1");
        btnAzul.setBounds(50, 50, 120, 40);
        
        btnAzul.addMouseMotionListener(this);
        addKeyListener(this);
        add(btnAzul);
        btnAzul.addKeyListener(this);

        // boton amarillo
        btnAmarillo = new JButton("boton2");
        btnAmarillo.setBounds(50, 110, 120, 40);
        btnAmarillo.setForeground(Color.YELLOW);
        btnAmarillo.addActionListener(this);
        btnAmarillo.addMouseMotionListener(this);
        add(btnAmarillo);
        btnAmarillo.addKeyListener(this);

        // boton aleatorio
        btnAleatorio = new JButton("Aleatorio");
        btnAleatorio.setBounds(50, 170, 120, 40);
        btnAleatorio.addActionListener(this);
        btnAleatorio.addMouseMotionListener(this);
        addKeyListener(this);
        add(btnAleatorio);
        btnAleatorio.addKeyListener(this);

        // Etiqueta tecla pulsada
        lblTecla = new JLabel("Tecla: -");
        lblTecla.setBounds(200, 50, 160, 30);
        add(lblTecla);

        // Listeners del formulario,preguntar si se pueden poner en cada btn
        // Listener para resturr toitulo
        getContentPane().addMouseMotionListener(this);
        addMouseListener(this);

    }

    // ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {
        // SHIFT se detecta aqui
        boolean shift = (e.getModifiers() & ActionEvent.SHIFT_MASK) == ActionEvent.SHIFT_MASK;

        if (e.getSource() == btnAzul || e.getSource() == btnAmarillo) {// TODO con OR pones el color del getSource
            // getContentPane().setBackground(Color.BLUE);
            // getContentPane().setBackground(Color.YELLOW);

            JButton boton = (JButton) e.getSource();
            getContentPane().setBackground(boton.getForeground());

        } else if (e.getSource() == btnAleatorio) {
            if (shift) {
                // aqui lo que hace es mover el boton en el formulkario
                int anchoFormulario = getContentPane().getWidth();
                int altoFormulario = getContentPane().getHeight();
                int x = (int) (Math.random() * (anchoFormulario - btnAleatorio.getWidth()));
                int y = (int) (Math.random() * (altoFormulario - btnAleatorio.getHeight()));
                btnAleatorio.setLocation(x, y);
            } else {
                // Sin SHIFT: mueve la ventana por la pantalla
                int x = (int) (Math.random() * 400);
                int y = (int) (Math.random() * 600);
                setLocation(x, y);
            }
        }

        // hace falta devolver el foco al formulario para seguir capturando teclado ??

    }

    // ── KeyListener
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        lblTecla.setText("Tecla: " + KeyEvent.getKeyText(e.getKeyCode()));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        lblTecla.setText("Tecla: -");
    }

    // ── MouseMotionListener
    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        // Si el evento viene de un boton, sumamos su posicion para obtener
        // coordenadas relativas al formulario
        if (e.getSource() instanceof JButton) {
            JButton btn = (JButton) e.getSource();
            x += btn.getX();
            y += btn.getY();
        }

        setTitle("(X:" + x + ", Y:" + y + ")");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
    }

    // ── MouseListener
    @Override
    public void mouseExited(MouseEvent e) {
        // Restablecer titulo solo al salir del formulario
        if (e.getSource() == this) {
            setTitle("Control de raton");
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
}