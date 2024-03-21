package com.example.views.dialogs;

import com.example.controller.ReservaController;
import com.example.controller.UsuarioController;
import com.example.model.Reserva;
import com.example.model.UsuarioReserva;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.time.LocalDate;

public class InsertDialog extends JDialog {
    private final ReservaController reservaController = new ReservaController();
    private final UsuarioController usuarioController = new UsuarioController();
    private final JTextField tfDia, tfCorreoReserva;
    private boolean save;
    private final JComboBox horarioComboBox;
    private final JSpinner spinnerNumeroMesa, spinnerNumeroPersonas;
    private Reserva nuevaReserva;

    public InsertDialog() {
        setTitle("Insertar Reserva");
        setSize(400, 350);
        setModal(true);

        //Panel principal
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        //Panel del formulario
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));

        formPanel.add(new JLabel("Correo usuario:"));
        tfCorreoReserva = new JTextField();
        tfCorreoReserva.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(tfCorreoReserva);

        formPanel.add(new JLabel("Numero Mesa:"));
        spinnerNumeroMesa = new JSpinner();
        formPanel.add(spinnerNumeroMesa);

        formPanel.add(new JLabel("Dia (AAAA-MM-DD):"));
        tfDia = new JTextField();
        tfDia.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(tfDia);

        formPanel.add(new JLabel("Horario:"));
        horarioComboBox = new JComboBox();
        horarioComboBox.setModel(new DefaultComboBoxModel(Reserva.Horario.values()));
        horarioComboBox.setSelectedIndex(0);
        BasicComboBoxRenderer basicComboBoxRenderer = new BasicComboBoxRenderer();
        basicComboBoxRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        horarioComboBox.setRenderer(basicComboBoxRenderer);
        formPanel.add(horarioComboBox);

        formPanel.add(new JLabel("Cantidad Personas:"));
        spinnerNumeroPersonas = new JSpinner();
        formPanel.add(spinnerNumeroPersonas);

        JButton saveButton = new JButton("Guardar");
        saveButton.addActionListener(e -> {

            UsuarioReserva usuarioReserva = usuarioController.getUsuarioByCorreo(tfCorreoReserva.getText());

            if (usuarioReserva == null) {
                CreateUserDialog dialog = new CreateUserDialog(tfCorreoReserva.getText());
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);

                if (dialog.isSave()) {
                    usuarioReserva = usuarioController.getUsuarioByCorreo(dialog.getTfCorreoReserva());
                    tfCorreoReserva.setText(usuarioReserva.getCorreo());
                }
                //TODO: agregar placeholder con el formato de la fecha esperado
            }

            Integer numeroMesa = (Integer) spinnerNumeroMesa.getValue();
            LocalDate dia = LocalDate.parse(tfDia.getText());
            Reserva.Horario horario = Reserva.Horario.valueOf(horarioComboBox.getSelectedItem().toString());
            Integer numeroPersonas = (Integer) spinnerNumeroPersonas.getValue();

            nuevaReserva = new Reserva(usuarioReserva, numeroMesa, dia, horario, numeroPersonas);

            Boolean success = reservaController.insertarReserva(nuevaReserva);

            if (!success) {
                JOptionPane.showMessageDialog(this, "Mesa no disponible en ese horario");
                return;
            }

            nuevaReserva = reservaController.getReserva(numeroMesa, dia, horario);

            save = true;

            dispose();
        });

        //Panel del boton de guardar
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(saveButton);

        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(panel);
    }

    public Reserva getNuevaReserva() {
        return nuevaReserva;
    }

    public boolean isSave() {
        return save;
    }
}