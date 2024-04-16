package com.example.views.dialogs;

import com.example.controller.ReservaController;
import com.example.controller.UsuarioController;
import com.example.model.Reserva;
import com.example.model.UsuarioReserva;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.time.LocalDate;
import javax.swing.JComboBox;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.JSpinner;

public class EditDialog extends JDialog {
    private static final ReservaController reservaController = new ReservaController();
    private static final UsuarioController usuarioController = new UsuarioController();
    private final JTextField tfDia;
    private final JLabel labelId, labelCorreoReserva;
    private boolean save;
    private final JComboBox horarioComboBox;
    private final JSpinner spinnerNumeroMesa;
    private final JSpinner spinnerNumeroPersonas;

    public EditDialog(String id, String correoReserva, String numeroMesa, String dia, String horario, String numeroPersonas) {
        setTitle("Editar Reserva");
        setSize(400, 350);
        setModal(true);

        //Panel principal
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        //Crear labels y textFields
        labelId = new JLabel(id);
        labelId.setHorizontalAlignment(SwingConstants.CENTER);
        labelCorreoReserva = new JLabel(correoReserva);
        labelCorreoReserva.setHorizontalAlignment(SwingConstants.CENTER);
        tfDia = new JTextField(dia);
        tfDia.setHorizontalAlignment(SwingConstants.CENTER);

        //Panel del formulario
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5));

        formPanel.add(new JLabel("ID:"));
        formPanel.add(labelId);

        formPanel.add(new JLabel("Usuario Reserva:"));
        formPanel.add(labelCorreoReserva);

        formPanel.add(new JLabel("Numero Mesa:"));
        spinnerNumeroMesa = new JSpinner();
        spinnerNumeroMesa.setValue(Integer.valueOf(numeroMesa));
        formPanel.add(spinnerNumeroMesa);

        formPanel.add(new JLabel("Dia (AAAA-MM-DD):"));
        formPanel.add(tfDia);

        formPanel.add(new JLabel("Horario:"));
        horarioComboBox = new JComboBox();
        horarioComboBox.setModel(new DefaultComboBoxModel(Reserva.Horario.values()));
        if (horario.equals(Reserva.Horario.ALMUERZO.toString())) {
            horarioComboBox.setSelectedIndex(0);
        } else {
            horarioComboBox.setSelectedIndex(1);
        }
        BasicComboBoxRenderer basicComboBoxRenderer = new BasicComboBoxRenderer();
        basicComboBoxRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        horarioComboBox.setRenderer(basicComboBoxRenderer);
        formPanel.add(horarioComboBox);

        formPanel.add(new JLabel("Cantidad Personas:"));
        spinnerNumeroPersonas = new JSpinner();
        spinnerNumeroPersonas.setValue(Integer.valueOf(numeroPersonas));
        formPanel.add(spinnerNumeroPersonas);

        JButton saveButton = new JButton("Guardar");
        saveButton.addActionListener(e -> {
            // Guardar los valores y cerrar el diálogo
            int confirm = JOptionPane.showConfirmDialog(this, "Quieres confirmar los cambios sobre la reserva con ID: " + id + "?");

            if (confirm == JOptionPane.OK_OPTION) {
                UsuarioReserva usuario = usuarioController.getUsuarioByCorreo(correoReserva);

                Reserva reservaActualizada = Reserva.builder()
                        .id(Integer.valueOf(id))
                        .usuarioReserva(usuario)
                        .numeroMesa(Integer.valueOf(spinnerNumeroMesa.getValue().toString()))
                        .dia(LocalDate.parse(tfDia.getText()))
                        .horario(Reserva.Horario.valueOf(horarioComboBox.getSelectedItem().toString()))
                        .numeroPersonas(Integer.valueOf(spinnerNumeroPersonas.getValue().toString()))
                        .build();

                Reserva oldReserva = Reserva.builder()
                        .id(Integer.valueOf(id))
                        .usuarioReserva(usuario)
                        .numeroMesa(Integer.valueOf(numeroMesa))
                        .dia(LocalDate.parse(dia))
                        .horario(Reserva.Horario.valueOf(horario))
                        .numeroPersonas(Integer.valueOf(numeroPersonas))
                        .build();

                if (reservaActualizada.equals(oldReserva)) {
                    dispose();
                    return;
                }

                Boolean success = reservaController.editarReserva(reservaActualizada);

                if (!success) {
                    JOptionPane.showMessageDialog(this, "Mesa no disponible en ese horario");
                    return;
                }

                save = true;
            }

            dispose();
        });

        //Panel del boton de guardar
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(saveButton);

        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(panel);
    }

    public String getId() {
        return labelId.getText();
    }

    public String getCorreoReserva() {
        return labelCorreoReserva.getText();
    }

    public String getSpinnerNumeroMesa() {
        return spinnerNumeroMesa.getValue().toString();
    }

    public String getTfDia() {
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