package com.example.views;

import com.example.GestionRestaurante;
import com.example.controller.EmpleadoController;
import com.example.views.utils.Constants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.MatteBorder;

public class LoginPanel extends JPanel implements ActionListener {

    private final GestionRestaurante gestionRestaurante;

    private static final EmpleadoController empleadoController = new EmpleadoController();

    private JTextField tfUsuario;
    private JPasswordField tfPassword;
    private JButton btnIniciarSesion;
    private JLabel labelTitulo, lblIconoLogo, lblIconoUsuario, lbIconoCandado, labelUsuario, labelPassword;
    private GridBagConstraints gbc_1, gbc_2, gbc_3, gbc_4;


    public LoginPanel(GestionRestaurante gestionRestaurante) {
    	setBackground(Constants.COLOR_PRINCIPAL_AMARILLO);
        this.gestionRestaurante = gestionRestaurante;
        gestionRestaurante.setSize(600, 450);
        gestionRestaurante.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gestionRestaurante.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        gestionRestaurante.getContentPane().add(this, BorderLayout.CENTER);



        setLayout(new BorderLayout(0,0));

        labelTitulo = new JLabel("JAJ - Gestión de Restaurantes"); // titulo de la pantalla
        labelTitulo.setBorder(new MatteBorder(0, 0, 2, 0, Constants.COLOR_NEGRO));
        labelTitulo.setFocusCycleRoot(true);
        labelTitulo.setBackground(Constants.COLOR_PRINCIPAL_AMARILLO);
        labelTitulo.setFont(new Font(Constants.YU_GOTHIC_UI_SEMIBOLD, Font.PLAIN, 22));
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        GridBagLayout gbl_centerPanel = new GridBagLayout();
        gbl_centerPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
        gbl_centerPanel.rowHeights = new int[]{100, 19, 0, 42, 23};
        gbl_centerPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
        JPanel centerPanel = new JPanel(gbl_centerPanel); // contenedor central
        centerPanel.setBackground(Constants.COLOR_PRINCIPAL_AMARILLO);

        GridBagConstraints gbc;
        Insets insets = new Insets(5, 5, 5, 5); // padding
        
        lblIconoLogo = new JLabel("");
        ImageIcon icono = new ImageIcon("src/main/resources/images/logo_transp3a.png");
        Image image = icono.getImage(); // Obtener la imagen del ImageIcon
        Image nuevaImagen = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH); // Escalar la imagen
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
        labelUsuario.setFont(new Font(Constants.ROCKWELL_NOVA, labelUsuario.getFont().getStyle(), 13));
        gbc = new GridBagConstraints();
        gbc.insets = insets;
        gbc.gridx = 1;
        gbc.gridy = 1;
        centerPanel.add(labelUsuario, gbc);

        tfUsuario = new JTextField(15);
        tfUsuario.setFont(new Font(Constants.ROCKWELL_NOVA, tfUsuario.getFont().getStyle(), tfUsuario.getFont().getSize()));
        tfUsuario.setSelectedTextColor(Constants.COLOR_BLANCO);
        tfUsuario.setToolTipText("");
        tfUsuario.setCaretColor(Constants.COLOR_NEGRO);
        tfUsuario.setBackground(Constants.COLOR_BLANCO);
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
                labelPassword.setFont(new Font(Constants.ROCKWELL_NOVA, labelPassword.getFont().getStyle(), 13));
                gbc_4 = new GridBagConstraints();
                gbc_4.insets = insets;
                gbc_4.gridx = 1;
                gbc_4.gridy = 2;
                centerPanel.add(labelPassword, gbc_4);
        
                tfPassword = new JPasswordField(15);
                tfPassword.setFont(new Font(Constants.ROCKWELL_NOVA, tfPassword.getFont().getStyle(), tfPassword.getFont().getSize()));
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
                btnIniciarSesion.setForeground(Constants.COLOR_BLANCO);
                btnIniciarSesion.setIconTextGap(5);
                btnIniciarSesion.setAlignmentY(Component.BOTTOM_ALIGNMENT);
                btnIniciarSesion.setBackground(Constants.COLOR_NEGRO);
                btnIniciarSesion.setFont(new Font(Constants.VERDANA, btnIniciarSesion.getFont().getStyle() | Font.BOLD, 15));
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
