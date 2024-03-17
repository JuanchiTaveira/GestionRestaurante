package com.example.views.dialogs;

import com.example.controller.ReservaController;
import com.example.controller.UsuarioController;
import com.example.model.Reserva;
import com.example.model.UsuarioReserva;

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

public class EditDialog extends JDialog {
    private JTextField tfNumeroMesa, tfDia, tfHorario, tfNumeroPersonas;
    private JLabel labelId, labelUsuarioReserva;
    private final ReservaController reservaController = new ReservaController();
    private final UsuarioController usuarioController = new UsuarioController();
    private boolean save;

    public EditDialog(String id, String usuarioReserva, String numeroMesa, String dia, String horario, String numeroPersonas) {
        setTitle("Editar Reserva");
        setSize(400, 350);
        setModal(true);

        labelId = new JLabel(id);
        labelId.setHorizontalAlignment(SwingConstants.CENTER);
        labelUsuarioReserva = new JLabel(usuarioReserva);
        labelUsuarioReserva.setHorizontalAlignment(SwingConstants.CENTER);
        tfNumeroMesa = new JTextField(numeroMesa);
        tfNumeroMesa.setHorizontalAlignment(SwingConstants.CENTER);
        tfDia = new JTextField(dia);
        tfDia.setHorizontalAlignment(SwingConstants.CENTER);
        tfHorario = new JTextField(horario);
        tfHorario.setHorizontalAlignment(SwingConstants.CENTER);
        tfNumeroPersonas = new JTextField(numeroPersonas);
        tfNumeroPersonas.setHorizontalAlignment(SwingConstants.CENTER);

        JButton saveButton = new JButton("Guardar");
        saveButton.addActionListener(e -> {
            // Guardar los valores y cerrar el diálogo
            int confirm = JOptionPane.showConfirmDialog(this, "Quieres confirmar los cambios sobre la reserva con ID: " + id + "?");

            //TODO: Crear PK compuesta en tabla Reservas con los campos (numero mesa, dia, horario) para que no se puedan guardar dos reservas de la misma mesa en el mismo dia y horario.
            //TODO: Desactivar la edicion de las celdas desde la tabla.

            if (confirm == 0) {
                UsuarioReserva usuario = usuarioController.getUsuarioByCorreo(usuarioReserva);

                Reserva reservaActualizada = Reserva.builder()
                        .id(Integer.valueOf(id))
                        .usuarioReserva(usuario)
                        .numeroMesa(Integer.valueOf(tfNumeroMesa.getText()))
                        .dia(LocalDate.parse(tfDia.getText()))
                        .horario(Reserva.Horario.valueOf(tfHorario.getText()))
                        .numeroPersonas(Integer.valueOf(tfNumeroPersonas.getText()))
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

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        formPanel.add(new JLabel("ID:"));
        formPanel.add(labelId);
        formPanel.add(new JLabel("Usuario Reserva:"));
        formPanel.add(labelUsuarioReserva);
        formPanel.add(new JLabel("Numero Mesa:"));
        formPanel.add(tfNumeroMesa);
        formPanel.add(new JLabel("Dia:"));
        formPanel.add(tfDia);
        formPanel.add(new JLabel("Horario:"));
        formPanel.add(tfHorario);
        formPanel.add(new JLabel("Cantidad Personas:"));
        formPanel.add(tfNumeroPersonas);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(saveButton);

        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(panel);
    }

    public String getId() {
        return labelId.getText();
    }

    public String getUsuarioReserva() {
        return labelUsuarioReserva.getText();
    }

    public String getTfNumeroMesa() {
        return tfNumeroMesa.getText();
    }

    public String getTfDia() {
        return tfDia.getText();
    }

    public String getTfHorario() {
        return tfHorario.getText();
    }

    public String getTfNumeroPersonas() {
        return tfNumeroPersonas.getText();
    }

    public boolean isSave() {
        return save;
    }
}