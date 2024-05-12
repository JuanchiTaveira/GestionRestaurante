package com.example.views.dialogs.reservas;

import com.example.controller.EmpleadoController;
import com.example.controller.MesaController;
import com.example.controller.ReservaController;
import com.example.controller.ClienteController;
import com.example.model.Empleado;
import com.example.model.Reserva;
import com.example.model.Cliente;
import com.example.utils.Constants;
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
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Toolkit;
import java.awt.Component;
import javax.swing.ImageIcon;

public class EditDialog extends JDialog implements ChangeListener {
    private static final ReservaController reservaController = new ReservaController();
    private static final ClienteController clienteController = new ClienteController();
    private static final MesaController mesaController = new MesaController();
    private final JLabel labelId, labelCorreoReserva;
    private boolean save, deleted;
    private final JComboBox horarioComboBox;
    private final JSpinner spinnerNumeroMesa;
    private JSpinner spinnerNumeroPersonas;
    private final JDateChooser dateChooser;
    private SpinnerNumberModel model;
    private JButton btnEliminar;

    public EditDialog(String id, String correoReserva, String numeroMesa, String dia, String horario, String numeroPersonas, String empleado) {
    	setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/images/cubiertos.png"));
        setTitle("Editar Reserva");
        setSize(400, 350);
        setModal(true);
    	setBackground(Constants.COLOR_PRINCIPAL_AMARILLO);
    	getContentPane().setBackground(Constants.COLOR_PRINCIPAL_AMARILLO);

        //Panel principal
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Constants.COLOR_PRINCIPAL_AMARILLO);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        //Crear labels y textFields
        labelId = new JLabel(id);
        labelId.setFont(new Font(Constants.ROCKWELL_NOVA, labelId.getFont().getStyle(), labelId.getFont().getSize()));
        labelId.setHorizontalAlignment(SwingConstants.CENTER);

        labelCorreoReserva = new JLabel(correoReserva);
        labelCorreoReserva.setFont(new Font(Constants.ROCKWELL_NOVA, labelCorreoReserva.getFont().getStyle(), labelCorreoReserva.getFont().getSize()));
        labelCorreoReserva.setForeground(Constants.COLOR_NEGRO);
        labelCorreoReserva.setHorizontalAlignment(SwingConstants.CENTER);

        dateChooser = new JDateChooser();
        dateChooser.getCalendarButton().setIcon(new ImageIcon(EditDialog.class.getResource("/com/toedter/calendar/demo/images/DemoTableColor16.gif")));
        dateChooser.setFont(new Font(Constants.ROCKWELL_NOVA, dateChooser.getFont().getStyle(), dateChooser.getFont().getSize()));
        dateChooser.setBorder(new LineBorder(Constants.COLOR_NEGRO, 2));
        dateChooser.setDateFormatString("yyyy-MM-dd");
        dateChooser.setDate(Date.valueOf(dia));
        // Accediendo al JTextField y centrando el texto
        JTextField dateTextField = (JTextField) dateChooser.getDateEditor().getUiComponent();
        dateTextField.setHorizontalAlignment(SwingConstants.CENTER);

        //Panel del formulario
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        formPanel.setBackground(Constants.COLOR_PRINCIPAL_AMARILLO);

        JLabel label = new JLabel("ID:");
        label.setIcon(new ImageIcon("src/main/resources/images/id.png"));
        label.setFont(new Font(Constants.ROCKWELL_NOVA, label.getFont().getStyle(), label.getFont().getSize()));
        formPanel.add(label);
        labelId.setForeground(Constants.COLOR_NEGRO);
        labelId.setBackground(Constants.COLOR_NEGRO);
        formPanel.add(labelId);

        JLabel labelCorreo = new JLabel("Correo:");
        labelCorreo.setIcon(new ImageIcon("src/main/resources/images/correo.png"));
        labelCorreo.setAlignmentX(Component.RIGHT_ALIGNMENT);
        labelCorreo.setFont(new Font(Constants.ROCKWELL_NOVA, labelCorreo.getFont().getStyle(), labelCorreo.getFont().getSize()));
        formPanel.add(labelCorreo);
        formPanel.add(labelCorreoReserva);

        JLabel labelNumeroMesa = new JLabel("Numero Mesa:");
        labelNumeroMesa.setIcon(new ImageIcon("src/main/resources/images/comedor.png"));
        labelNumeroMesa.setFont(new Font(Constants.ROCKWELL_NOVA, labelNumeroMesa.getFont().getStyle(), labelNumeroMesa.getFont().getSize()));
        formPanel.add(labelNumeroMesa);
        spinnerNumeroMesa = new JSpinner();
        spinnerNumeroMesa.setFont(new Font(Constants.ROCKWELL_NOVA, spinnerNumeroMesa.getFont().getStyle(), spinnerNumeroMesa.getFont().getSize()));
        spinnerNumeroMesa.setBorder(new LineBorder(Constants.COLOR_NEGRO, 2));
        spinnerNumeroMesa.addChangeListener(this);
        spinnerNumeroMesa.setValue(Integer.valueOf(numeroMesa));
        formPanel.add(spinnerNumeroMesa);

        JLabel labelDia = new JLabel("Dia (AAAA-MM-DD):");
        labelDia.setIcon(new ImageIcon("src/main/resources/images/calendario.png"));
        labelDia.setFont(new Font(Constants.ROCKWELL_NOVA, labelDia.getFont().getStyle(), labelDia.getFont().getSize()));
        formPanel.add(labelDia);
        formPanel.add(dateChooser);

        JLabel labelHorario = new JLabel("Horario:");
        labelHorario.setIcon(new ImageIcon("src/main/resources/images/reloj.png"));
        labelHorario.setFont(new Font(Constants.ROCKWELL_NOVA, labelHorario.getFont().getStyle(), labelHorario.getFont().getSize()));
        formPanel.add(labelHorario);
        horarioComboBox = new JComboBox();
        horarioComboBox.setBorder(new LineBorder(Constants.COLOR_NEGRO, 2));
        horarioComboBox.setBackground(Constants.COLOR_BLANCO);
        horarioComboBox.setFont(new Font(Constants.ROCKWELL_NOVA, horarioComboBox.getFont().getStyle(), horarioComboBox.getFont().getSize()));
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

        JLabel labelCantidadPersonas = new JLabel("Cantidad Personas:");
        labelCantidadPersonas.setIcon(new ImageIcon("src/main/resources/images/grupo.png"));
        labelCantidadPersonas.setFont(new Font(Constants.ROCKWELL_NOVA, labelCantidadPersonas.getFont().getStyle(), labelCantidadPersonas.getFont().getSize()));
        formPanel.add(labelCantidadPersonas);
        // Crear el modelo del spinner
        model = new SpinnerNumberModel(Integer.parseInt(numeroPersonas), 1, mesaController.maxPersonasMesa(Integer.valueOf(numeroMesa)).intValue(), 1); // valor inicial, min, max, paso
        spinnerNumeroPersonas = new JSpinner(model);
        spinnerNumeroPersonas.setFont(new Font(Constants.ROCKWELL_NOVA, spinnerNumeroPersonas.getFont().getStyle(), spinnerNumeroPersonas.getFont().getSize()));
        spinnerNumeroPersonas.setBorder(new LineBorder(Constants.COLOR_NEGRO, 2));
        formPanel.add(spinnerNumeroPersonas);

        //Panel del boton de guardar y eliminar
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Constants.COLOR_PRINCIPAL_AMARILLO);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBorderPainted(false);
        btnEliminar.setBackground(Constants.COLOR_NEGRO);
        btnEliminar.setForeground(Constants.COLOR_PRINCIPAL_AMARILLO);
        btnEliminar.setFont(new Font(Constants.VERDANA, btnEliminar.getFont().getStyle() | Font.BOLD, 12));
        btnEliminar.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Quieres eliminar la reserva con ID: " + id + "?");

            if (confirm == JOptionPane.OK_OPTION) {
                reservaController.eliminarReserva(Integer.valueOf(id));
                save = true;
                deleted = true;
            }

            dispose();
        });
        buttonPanel.add(btnEliminar);

        JButton saveButton = new JButton("Guardar");
        saveButton.setBackground(Constants.COLOR_NEGRO);
        saveButton.setBorderPainted(false);
        saveButton.setForeground(Constants.COLOR_PRINCIPAL_AMARILLO);
        saveButton.setFont(new Font(Constants.VERDANA, saveButton.getFont().getStyle() | Font.BOLD, 12));
        saveButton.addActionListener(e -> {
            // Guardar los valores y cerrar el diálogo
            int confirm = JOptionPane.showConfirmDialog(this, "Quieres confirmar los cambios sobre la reserva con ID: " + id + "?");

            if (confirm == JOptionPane.OK_OPTION) {
                Cliente usuario = clienteController.getClienteByCorreo(correoReserva);
                Empleado empleadoReserva = new EmpleadoController().getEmpleadoByUsuario(empleado);

                Reserva reservaActualizada = Reserva.builder()
                        .id(Integer.valueOf(id))
                        .cliente(usuario)
                        .numeroMesa(Integer.valueOf(spinnerNumeroMesa.getValue().toString()))
                        .dia(LocalDate.parse(((JTextField) dateChooser.getDateEditor().getUiComponent()).getText()))
                        .horario(Reserva.Horario.valueOf(horarioComboBox.getSelectedItem().toString()))
                        .numeroPersonas(Integer.valueOf(spinnerNumeroPersonas.getValue().toString()))
                        .empleado(empleadoReserva)
                        .build();

                Reserva oldReserva = Reserva.builder()
                        .id(Integer.valueOf(id))
                        .cliente(usuario)
                        .numeroMesa(Integer.valueOf(numeroMesa))
                        .dia(LocalDate.parse(dia))
                        .horario(Reserva.Horario.valueOf(horario))
                        .numeroPersonas(Integer.valueOf(numeroPersonas))
                        .empleado(empleadoReserva)
                        .build();

                if (reservaActualizada.equals(oldReserva)) {
                    dispose();
                    return;
                }

                int resultCode = reservaController.persistReserva(reservaActualizada);

                if (resultCode == 0) {
                    save = true;
                } else if (resultCode == -1) {
                    JOptionPane.showMessageDialog(this, "Mesa no disponible en ese horario");
                    return;
                } else if (resultCode == -2) {
                    JOptionPane.showMessageDialog(this, "No es posible reservar una mesa para una fecha pasada.");
                    return;
                } else if (resultCode == -3) {
                    JOptionPane.showMessageDialog(this, "La mesa seleccionada no admite la cantidad de personas indicada.");
                    return;
                }
            }
            dispose();
        });
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

    public boolean isDeleted() {
        return deleted;
    }
}