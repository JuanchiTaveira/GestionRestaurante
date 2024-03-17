package com.example.views.dialogs;

import com.example.controller.ReservaController;
import com.example.controller.UsuarioController;
import com.example.model.Reserva;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class InsertDialog extends JDialog {
    private final ReservaController reservaController = new ReservaController();
    private final UsuarioController usuarioController = new UsuarioController();
    private final JTextField tfDia, tfUsuarioReserva;
    private boolean save;
    private final JComboBox horarioComboBox;
    private final JSpinner spinnerNumeroMesa, spinnerNumeroPersonas;

    public InsertDialog() {
        setTitle("Insertar Reserva");
        setSize(400, 350);
        setModal(true);

        //Panel principal
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        //Panel del formulario
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));

        formPanel.add(new JLabel("Usuario Reserva:"));
        tfUsuarioReserva = new JTextField();
        tfUsuarioReserva.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(tfUsuarioReserva);

        formPanel.add(new JLabel("Numero Mesa:"));
        spinnerNumeroMesa = new JSpinner();
        formPanel.add(spinnerNumeroMesa);

        formPanel.add(new JLabel("Dia:"));
        tfDia = new JTextField();
        tfDia.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(tfDia);

        formPanel.add(new JLabel("Horario:"));
        horarioComboBox = new JComboBox();
        horarioComboBox.setModel(new DefaultComboBoxModel(Reserva.Horario.values()));
        BasicComboBoxRenderer basicComboBoxRenderer = new BasicComboBoxRenderer();
        basicComboBoxRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        horarioComboBox.setRenderer(basicComboBoxRenderer);
        formPanel.add(horarioComboBox);

        formPanel.add(new JLabel("Cantidad Personas:"));
        spinnerNumeroPersonas = new JSpinner();
        formPanel.add(spinnerNumeroPersonas);

        JButton saveButton = new JButton("Guardar");
        saveButton.addActionListener(e -> {

            dispose();
        });

        //Panel del boton de guardar
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(saveButton);

        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(panel);
    }

    public String getUsuarioReserva() {
        return tfUsuarioReserva.getText();
    }

    public String getSpinnerNumeroMesa() {
        return spinnerNumeroMesa.getValue().toString();
    }

    public String getDia() {
        return tfDia.getText();
    }

    public String getHorario() {
        return horarioComboBox.getSelectedItem().toString();
    }

    public String getSpinnerNumeroPersonas() {
        return spinnerNumeroPersonas.getValue().toString();
    }

    public boolean isSave() {
        return save;
    }
}