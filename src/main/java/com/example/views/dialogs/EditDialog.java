package com.example.views.dialogs;

import com.example.controller.MesaController;
import com.example.controller.ReservaController;
import com.example.controller.ClienteController;
import com.example.model.Reserva;
import com.example.model.Cliente;
import com.toedter.calendar.JDateChooser;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.Date;
import java.time.LocalDate;
import javax.swing.JComboBox;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.JSpinner;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.SystemColor;

public class EditDialog extends JDialog implements ChangeListener {
    private static final ReservaController reservaController = new ReservaController();
    private static final ClienteController CLIENTE_CONTROLLER = new ClienteController();
    private static final MesaController mesaController = new MesaController();
    private final JLabel labelId, labelCorreoReserva;
    private boolean save;
    private final JComboBox horarioComboBox;
    private final JSpinner spinnerNumeroMesa;
    private JSpinner spinnerNumeroPersonas;
    private final JDateChooser dateChooser;
    private SpinnerNumberModel model;

    public EditDialog(String id, String correoReserva, String numeroMesa, String dia, String horario, String numeroPersonas) {
    	setBackground(new Color(240, 197, 23));
    	getContentPane().setBackground(new Color(240, 197, 23));
        setTitle("Editar Reserva");
        setSize(400, 350);
        setModal(true);

        //Panel principal
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 197, 23));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        //Crear labels y textFields
        labelId = new JLabel(id);
        labelId.setHorizontalAlignment(SwingConstants.CENTER);

        labelCorreoReserva = new JLabel(correoReserva);
        labelCorreoReserva.setHorizontalAlignment(SwingConstants.CENTER);

        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("yyyy-MM-dd");
        dateChooser.setDate(Date.valueOf(dia));
        // Accediendo al JTextField y centrando el texto
        JTextField dateTextField = (JTextField) dateChooser.getDateEditor().getUiComponent();
        dateTextField.setHorizontalAlignment(JTextField.CENTER);

        //Panel del formulario
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        formPanel.setBackground(new Color(240, 197, 23));

        JLabel label = new JLabel("ID:");
        label.setForeground(UIManager.getColor("Button.focus"));
        label.setBackground(new Color(0, 0, 0));
        formPanel.add(label);
        formPanel.add(labelId);

        formPanel.add(new JLabel("Usuario Reserva:"));
        formPanel.add(labelCorreoReserva);

        formPanel.add(new JLabel("Numero Mesa:"));
        spinnerNumeroMesa = new JSpinner();
        spinnerNumeroMesa.addChangeListener(this);
        spinnerNumeroMesa.setValue(Integer.valueOf(numeroMesa));
        formPanel.add(spinnerNumeroMesa);

        formPanel.add(new JLabel("Dia (AAAA-MM-DD):"));
        formPanel.add(dateChooser);

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
        // Crear el modelo del spinner
        model = new SpinnerNumberModel(Integer.parseInt(numeroPersonas), 1, mesaController.maxPersonasMesa(Integer.valueOf(numeroMesa)).intValue(), 1); // valor inicial, min, max, paso
        spinnerNumeroPersonas = new JSpinner(model);
        formPanel.add(spinnerNumeroPersonas);

        JButton saveButton = new JButton("Guardar");
        saveButton.addActionListener(e -> {
            // Guardar los valores y cerrar el diálogo
            int confirm = JOptionPane.showConfirmDialog(this, "Quieres confirmar los cambios sobre la reserva con ID: " + id + "?");

            if (confirm == JOptionPane.OK_OPTION) {
                Cliente usuario = CLIENTE_CONTROLLER.getUsuarioByCorreo(correoReserva);

                Reserva reservaActualizada = Reserva.builder()
                        .id(Integer.valueOf(id))
                        .cliente(usuario)
                        .numeroMesa(Integer.valueOf(spinnerNumeroMesa.getValue().toString()))
                        .dia(LocalDate.parse(((JTextField) dateChooser.getDateEditor().getUiComponent()).getText()))
                        .horario(Reserva.Horario.valueOf(horarioComboBox.getSelectedItem().toString()))
                        .numeroPersonas(Integer.valueOf(spinnerNumeroPersonas.getValue().toString()))
                        .build();

                Reserva oldReserva = Reserva.builder()
                        .id(Integer.valueOf(id))
                        .cliente(usuario)
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
        buttonPanel.setBackground(new Color(240, 197, 23));
        buttonPanel.add(saveButton);

        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(panel);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource().equals(spinnerNumeroMesa) && spinnerNumeroPersonas != null) {
            int maxPersonas = mesaController.maxPersonasMesa(Integer.parseInt(spinnerNumeroMesa.getValue().toString()));

            if (Integer.parseInt(spinnerNumeroPersonas.getValue().toString()) > maxPersonas) {
                model.setValue(maxPersonas);
            }

            model.setMaximum(maxPersonas);
        }
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
        return dateChooser.getDate().toString();
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