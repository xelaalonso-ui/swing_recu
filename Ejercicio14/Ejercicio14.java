package Ejercicio14;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

public class Ejercicio14 extends JFrame implements ActionListener, ItemListener {

    private JTextField txfA;
    private JTextField txfB;
    private JLabel lblOperador;
    private JLabel lblResultado;

    private JRadioButton rbtSuma;
    private JRadioButton rbtResta;
    private JRadioButton rbtMulti;
    private JRadioButton rbtDivision;
    private ButtonGroup grupo;

    private JButton btnOperar;
    private JLabel lblError;
    private JComboBox<String> cmbDecimales;

    private Timer timer;
    private int segundos = 0;

    private String ruta = "Ejercicio14\\calculo.txt";

    public Ejercicio14() {
        super("Calculadora");
        this.setLayout(null); // null layout

        // --- fila de operandos ---
        txfA = new JTextField(8);
        txfA.setToolTipText("Primer numero");
        txfA.addActionListener(this);
        txfA.setBounds(10, 10, 120, 30);
        this.add(txfA);

        lblOperador = new JLabel("+");
        lblOperador.setFont(new Font("Arial", Font.BOLD, 18));
        lblOperador.setBounds(140, 10, 30, 30);
        this.add(lblOperador);

        txfB = new JTextField(8);
        txfB.setToolTipText("Segundo numero");
        txfB.addActionListener(this);
        txfB.setBounds(180, 10, 120, 30);
        this.add(txfB);

        lblResultado = new JLabel("=");
        lblResultado.setFont(new Font("Arial", Font.BOLD, 14));
        lblResultado.setBounds(310, 10, 200, 30);
        this.add(lblResultado);

        // --- radiobuttons ---
        grupo = new ButtonGroup();

        rbtSuma = new JRadioButton("Suma (+)", true);
        rbtSuma.setBounds(10, 50, 120, 25);
        grupo.add(rbtSuma);
        rbtSuma.addItemListener(this);

        rbtResta = new JRadioButton("Resta (-)");
        rbtResta.setBounds(10, 80, 120, 25);

        grupo.add(rbtResta);
        rbtResta.addItemListener(this);
        rbtMulti = new JRadioButton("Multiplicacion (*)");

        rbtMulti.setBounds(10, 110, 180, 25);

        grupo.add(rbtMulti);
        rbtMulti.addItemListener(this);
        rbtDivision = new JRadioButton("Division (/)");
        rbtDivision.setBounds(10, 140, 150, 25);
        grupo.add(rbtDivision);
        rbtDivision.addItemListener(this);

        this.add(rbtSuma);
        this.add(rbtResta);
        this.add(rbtMulti);
        this.add(rbtDivision);

        // --- boton, decimales y error ---
        btnOperar = new JButton("Operacion");
        btnOperar.setToolTipText("Calcula el resultado");
        btnOperar.addActionListener(this);
        btnOperar.setBounds(10, 180, 120, 30);
        this.add(btnOperar);

        JLabel lblDec = new JLabel("Decimales:");
        lblDec.setBounds(140, 180, 80, 30);
        this.add(lblDec);

        String[] nums = { "0", "1", "2", "3", "4", "5" };
        cmbDecimales = new JComboBox<>(nums);
        cmbDecimales.setSelectedIndex(2);
        cmbDecimales.setToolTipText("Numero de decimales a mostrar");
        cmbDecimales.addActionListener(this);
        cmbDecimales.setBounds(225, 180, 60, 30);
        this.add(cmbDecimales);

        lblError = new JLabel("");
        lblError.setForeground(Color.RED);
        lblError.setBounds(10, 220, 300, 25);
        this.add(lblError);

        // --- timer titulo ---
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                segundos++;
                int min = segundos / 60;
                int seg = segundos % 60;
                setTitle(String.format("Calculadora | %02d:%02d", min, seg));
            }
        });
        timer.start();

        cargarConfig();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // si cambia el combo y ya hay resultado, recalcula
        if (e.getSource() == cmbDecimales) {
            if (!lblResultado.getText().equals("=")) {
                operar();
            }
            return;
        } // boton o enter en cualquier textfield
        operar();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {//ITEMEVENT=Se dispara cuando cambia el estado de selección de un componente como JRadioButton, JCheckBox o JComboBox.
        //en ested cado es para  que muestre +, -, * o /
        //IMPORTANTE = // solo se ejecuta cuando se SELECCIONA, no cuando se deselecciona  (si no se ejecutaria dos veces)
        if (e.getStateChange() == ItemEvent.SELECTED) {
            // cambiar simbolo
            if (rbtSuma.isSelected()) {
                lblOperador.setText("+");
            } else if (rbtResta.isSelected()) {
                lblOperador.setText("-");
            } else if (rbtMulti.isSelected()) {
                lblOperador.setText("*");
            } else if (rbtDivision.isSelected()) {
                lblOperador.setText("/");
            }

            // borrar resultado y error al cambiar operacion
            lblResultado.setText("=");
            guardarConfig();
            lblError.setText("");
        }
    }

    private void operar() {
        lblError.setText("");

        double a;
        double b;

        // parsear primer label
        try {
            a = Double.parseDouble(txfA.getText().trim());
        } catch (NumberFormatException ex) {
            lblError.setText("El primer valor no es un numero");
            lblResultado.setText("=");
            return;
        }

        // parsear segundo label
        try {
            b = Double.parseDouble(txfB.getText().trim());
        } catch (NumberFormatException ex) {
            lblError.setText("El segundo valor no es un numero");
            lblResultado.setText("=");
            return;
        }

        int dec = cmbDecimales.getSelectedIndex();
        double resultado = 0;

        if (rbtSuma.isSelected()) {
            resultado = a + b;

        } else if (rbtResta.isSelected()) {
            resultado = a - b;

        } else if (rbtMulti.isSelected()) {
            resultado = a * b;

        } else if (rbtDivision.isSelected()) {
            if (b == 0) {
                lblError.setText("No se puede dividir entre cero");
                lblResultado.setText("=");
                return;
            }
            resultado = a / b;
        }

        lblResultado.setText(String.format("= %." + dec + "f", resultado));
    }

    // guarda los datos en un archivo de texto en la carpeta del usuario
    public void guardarConfig() {
        String op = "suma";
        if (rbtResta.isSelected()) {
            op = "resta";
        } else if (rbtMulti.isSelected()) {
            op = "multi";
        } else if (rbtDivision.isSelected()) {
            op = "division";
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(ruta))) {
            pw.println(txfA.getText());
            pw.println(txfB.getText());
            pw.println(op);
            pw.println(cmbDecimales.getSelectedIndex());
            pw.println(lblResultado.getText());
        } catch (IOException ex) {
            System.err.println("Error guardando : " + ex.getMessage());
        }
    }

    // lee el archivo si existe y restaura el estado
    private void cargarConfig() {
        File f = new File(ruta);
        if (!f.exists()) {
            return;
        }

        try (Scanner sc = new Scanner(new FileReader(ruta))) {
            txfA.setText(sc.nextLine());
            txfB.setText(sc.nextLine());
            String op = sc.nextLine();
            cmbDecimales.setSelectedIndex(Integer.parseInt(sc.nextLine()));
            if (sc.hasNextLine()) {
                lblResultado.setText(sc.nextLine());
            }

            if (op.equals("resta")) {
                rbtResta.setSelected(true);
            } else if (op.equals("multi")) {
                rbtMulti.setSelected(true);
            } else if (op.equals("division")) {
                rbtDivision.setSelected(true);
            } else {
                rbtSuma.setSelected(true);
            }

            if (rbtSuma.isSelected()) {
                lblOperador.setText("+");
            } else if (rbtResta.isSelected()) {
                lblOperador.setText("-");
            } else if (rbtMulti.isSelected()) {
                lblOperador.setText("*");
            } else if (rbtDivision.isSelected()) {
                lblOperador.setText("/");
            }

        } catch (IOException ex) {
            System.err.println("Error leyendo : " + ex.getMessage());
        }
    }
}