package Ejercicio13;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Titulo, borrar imagen si se carga una invalida
public class Ejercicio13 extends JFrame implements ActionListener {
   // TODO que no cambie color de form
   JTextField textRojo;
   JTextField textVerde;
   JTextField textAzul;

   JButton btnColor;

   JLabel lblPath;
   JTextField txfPath;
   JLabel lblImagen;

   public Ejercicio13() {
      super("Ejercicio Colores");
      setLayout(null);

      // TEXTO 1 (ROJO)
      textRojo = new JTextField("0");
      textRojo.setBounds(50, 50, 120, 40);
      textRojo.setToolTipText("Rojo (0-255)");
      add(textRojo);

      // TEXTO 2 (VERDE)
      textVerde = new JTextField("0");
      textVerde.setBounds(190, 50, 120, 40);
      textVerde.setToolTipText("Verde (0-255)");
      add(textVerde);

      // TEXTO 3 (AZUL)
      textAzul = new JTextField("0");
      textAzul.setBounds(330, 50, 120, 40);
      textAzul.setToolTipText("Azul (ENTER aplica color)");
      textAzul.addActionListener(this);
      add(textAzul);

      // BOTON
      btnColor = new JButton("Color");
      btnColor.setBounds(50, 100, 120, 40);
      btnColor.setToolTipText("Aplica color (CTRL cambia texto)");
      btnColor.addActionListener(this);
      add(btnColor);

      // RUTA IMAGEN
      lblPath = new JLabel("Ruta imagen:");
      lblPath.setBounds(50, 160, 120, 30);
      add(lblPath);

      txfPath = new JTextField();
      txfPath.setBounds(170, 160, 300, 30);
      txfPath.setToolTipText("Introduce ruta y pulsa ENTER");
      txfPath.addActionListener(this);
      add(txfPath);

      // LABEL IMAGEN
      lblImagen = new JLabel("Imagen");
      lblImagen.setBounds(50, 210, 400, 300);
      lblImagen.setBorder(BorderFactory.createLineBorder(Color.GRAY));
      lblImagen.setHorizontalAlignment(JLabel.CENTER);
      add(lblImagen);
   }

   @Override
   public void actionPerformed(ActionEvent e) {

      // ENTER en imagen
      if (e.getSource() == txfPath) {
         cargarImagen();
      }

      // ENTER en tezto 3
      else if (e.getSource() == textAzul) {

         Color color = colores();
         btnColor.setBackground(color);

      }

      // BOTON
      else if (e.getSource() == btnColor ) {

         Color color = colores();

         boolean control = (e.getModifiers() & ActionEvent.CTRL_MASK) == ActionEvent.CTRL_MASK;

         if (control) {
            btnColor.setForeground(color);

         } else {
            btnColor.setBackground(color);
         }
      }
   }

   // LEER COLOR
   private Color colores() {

      try {
         int r = Integer.parseInt(textRojo.getText());
         int g = Integer.parseInt(textVerde.getText());
         int b = Integer.parseInt(textAzul.getText());

         if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
            JOptionPane.showMessageDialog(this, "Valores entre 0 y 255");
            return null;
         }

         return new Color(r, g, b);

      } catch (NumberFormatException e) {// TODO excepcion no genérica
         JOptionPane.showMessageDialog(this, "Error en los números:o es negativo o no esta entre 0-255");
         return null;
      }
   }

   // CARGAR IMAGEN
   private void cargarImagen() {

      String ruta = txfPath.getText().trim();

      if (ruta.equals("")) {
         JOptionPane.showMessageDialog(this, "Ruta no valida");
      } else {
         ImageIcon icono = new ImageIcon(ruta);

         if (icono.getIconWidth() == -1) {
            JOptionPane.showMessageDialog(this, "No se puede cargar la imagen");
         } else {
            Image img = icono.getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH);
            lblImagen.setIcon(new ImageIcon(img));
            lblImagen.setText("");
         }
      }
   }

}