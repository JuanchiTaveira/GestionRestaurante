package com.example.views;

import com.example.GestionRestaurante;
import com.example.controller.EmpleadoController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.border.MatteBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.UIManager;

public class LoginPanel extends JPanel implements ActionListener {

    private final GestionRestaurante gestionRestaurante;

    private static final EmpleadoController empleadoController = new EmpleadoController();

    private JTextField tfUsuario;
    private JPasswordField tfPassword;
    private JLabel labelPassword;
    private JLabel labelUsuario;
    private JButton btnIniciarSesion;
    private JLabel labelTitulo;
    private JLabel lblIconoLogo;
    private JLabel lblIconoUsuario;
    private JLabel lbIconoCandado;
    private GridBagConstraints gbc_1;
    private GridBagConstraints gbc_2;
    private GridBagConstraints gbc_3;
    private GridBagConstraints gbc_4;


    public LoginPanel(GestionRestaurante gestionRestaurante) {
    	setBackground(new Color(240, 197, 23));
        this.gestionRestaurante = gestionRestaurante;
        gestionRestaurante.setSize(600, 450);
        gestionRestaurante.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gestionRestaurante.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        gestionRestaurante.getContentPane().add(this, BorderLayout.CENTER);



        setLayout(new BorderLayout(0,0));

        labelTitulo = new JLabel("JAJ - Gestión de Restaurantes"); // titulo de la pantalla
        labelTitulo.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
        labelTitulo.setFocusCycleRoot(true);
        labelTitulo.setBackground(new Color(240, 197, 23));
        labelTitulo.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 22));
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelTitulo, BorderLayout.NORTH);

        GridBagLayout gbl_centerPanel = new GridBagLayout();
        gbl_centerPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
        gbl_centerPanel.rowHeights = new int[]{100, 19, 0, 42, 23};
        gbl_centerPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
        JPanel centerPanel = new JPanel(gbl_centerPanel); // contenedor central
        centerPanel.setBackground(new Color(240, 197, 23));

        GridBagConstraints gbc;
        Insets insets = new Insets(5, 5, 5, 5); // padding
        
        lblIconoLogo = new JLabel("");
        ImageIcon icono = new ImageIcon("src/main/resources/images/logo_transp3b.png");
        Image image = icono.getImage(); // Obtener la imagen del ImageIcon
        Image nuevaImagen = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH); // Escalar la imagen
        icono = new ImageIcon(nuevaImagen); // Crear un nuevo ImageIcon con la imagen escalada
        lblIconoLogo.setIcon(icono); // Establecer el ImageIcon en el JLabel
        GridBagConstraints gbc_lblIconoLogo = new GridBagConstraints();
        gbc_lblIconoLogo.gridwidth = 4;
        gbc_lblIconoLogo.insets = new Insets(0, 0, 5, 0);
        gbc_lblIconoLogo.gridx = 0;
        gbc_lblIconoLogo.gridy = 0;
        centerPanel.add(lblIconoLogo, gbc_lblIconoLogo);
        
        lblIconoUsuario = new JLabel("");
        lblIconoUsuario.setIcon(new ImageIcon("src/main/resources/images/Usuario.png"));
        GridBagConstraints gbc_lblIconoUsuario = new GridBagConstraints();
        gbc_lblIconoUsuario.insets = new Insets(0, 0, 5, 5);
        gbc_lblIconoUsuario.gridx = 0;
        gbc_lblIconoUsuario.gridy = 1;
        centerPanel.add(lblIconoUsuario, gbc_lblIconoUsuario);

        labelUsuario = new JLabel("Usuario");
        labelUsuario.setFont(new Font("Rockwell Nova", labelUsuario.getFont().getStyle(), 13));
        gbc = new GridBagConstraints();
        gbc.insets = insets;
        gbc.gridx = 1;
        gbc.gridy = 1;
        centerPanel.add(labelUsuario, gbc);

        tfUsuario = new JTextField(15);
        tfUsuario.setFont(new Font("Rockwell Nova", tfUsuario.getFont().getStyle(), tfUsuario.getFont().getSize()));
        tfUsuario.setSelectedTextColor(new Color(255, 255, 255));
        tfUsuario.setToolTipText("");
        tfUsuario.setCaretColor(new Color(0, 0, 0));
        tfUsuario.setBackground(new Color(255, 255, 255));
        gbc_1 = new GridBagConstraints();
        gbc_1.anchor = GridBagConstraints.WEST;
        gbc_1.insets = new Insets(5, 5, 5, 0);
        gbc_1.gridx = 3;
        gbc_1.gridy = 1;
        centerPanel.add(tfUsuario, gbc_1);
        
        lbIconoCandado = new JLabel("");
        lbIconoCandado.setIcon(new ImageIcon("src/main/resources/images/candado.png"));
        GridBagConstraints gbc_lbIconoCandado = new GridBagConstraints();
        gbc_lbIconoCandado.insets = new Insets(0, 0, 5, 5);
        gbc_lbIconoCandado.gridx = 0;
        gbc_lbIconoCandado.gridy = 2;
        centerPanel.add(lbIconoCandado, gbc_lbIconoCandado);
        
                labelPassword = new JLabel("Contraseña");
                labelPassword.setFont(new Font("Rockwell Nova", labelPassword.getFont().getStyle(), 13));
                gbc_4 = new GridBagConstraints();
                gbc_4.insets = insets;
                gbc_4.gridx = 1;
                gbc_4.gridy = 2;
                centerPanel.add(labelPassword, gbc_4);
        
                tfPassword = new JPasswordField(15);
                tfPassword.setFont(new Font("Rockwell Nova", tfPassword.getFont().getStyle(), tfPassword.getFont().getSize()));
                tfPassword.setToolTipText("");
                gbc_2 = new GridBagConstraints();
                gbc_2.anchor = GridBagConstraints.WEST;
                gbc_2.insets = new Insets(5, 5, 5, 0);
                gbc_2.gridx = 3;
                gbc_2.gridy = 2;
                tfPassword.putClientProperty("JComponent.roundRect",true);
                tfPassword.addActionListener(this);
                centerPanel.add(tfPassword, gbc_2);

        add(centerPanel, BorderLayout.CENTER); // agrego el contenedor central al contenedor principal
        
                btnIniciarSesion = new JButton("Iniciar sesión");
                btnIniciarSesion.setBorderPainted(false);
                btnIniciarSesion.setAlignmentX(Component.CENTER_ALIGNMENT);
                btnIniciarSesion.setForeground(new Color(240, 197, 23));
                btnIniciarSesion.setIconTextGap(5);
                btnIniciarSesion.setAlignmentY(Component.BOTTOM_ALIGNMENT);
                btnIniciarSesion.setBackground(new Color(0, 0, 0));
                btnIniciarSesion.setFont(new Font("Verdana", btnIniciarSesion.getFont().getStyle() | Font.BOLD, 15));
                gbc_3 = new GridBagConstraints();
                gbc_3.gridwidth = 3;
                gbc_3.fill = GridBagConstraints.HORIZONTAL;
                gbc_3.anchor = GridBagConstraints.SOUTH;
                gbc_3.insets = new Insets(5, 5, 5, 0);
                gbc_3.gridx = 1;
                gbc_3.gridy = 3;
                centerPanel.add(btnIniciarSesion, gbc_3);
                btnIniciarSesion.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnIniciarSesion)) {
            if (empleadoController.iniciarSesion(tfUsuario.getText(), tfPassword.getText())) {
                this.setVisible(false);
                new MainMenu(gestionRestaurante);
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos");
            }
        } else if (e.getSource().equals(tfPassword) && e.getModifiers() == 0) { // si clickeas enter tambien hace login
            btnIniciarSesion.doClick();
        }
    }
}
