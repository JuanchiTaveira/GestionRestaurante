package com.example.views.dialogs.reservas;

import com.example.controller.EmpleadoController;
import com.example.controller.MesaController;
import com.example.controller.ReservaController;
import com.example.controller.ClienteController;
import com.example.model.Empleado;
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
    private boolean save;
    private boolean deleted;
    private final JComboBox horarioComboBox;
    private final JSpinner spinnerNumeroMesa;
    private JSpinner spinnerNumeroPersonas;
    private final JDateChooser dateChooser;
    private SpinnerNumberModel model;
    private JButton btnEliminar;

    public EditDialog(String id, String correoReserva, String numeroMesa, String dia, String horario, String numeroPersonas, String empleado) {
    	setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/images/logo_pestana.png"));
        setTitle("Editar Reserva");
        setSize(400, 350);
        setModal(true);
    	setBackground(new Color(240, 197, 23));
    	getContentPane().setBackground(new Color(240, 197, 23));

        //Panel principal
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 197, 23));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        //Crear labels y textFields
        labelId = new JLabel(id);
        labelId.setFont(new Font("Rockwell Nova", labelId.getFont().getStyle(), labelId.getFont().getSize()));
        labelId.setHorizontalAlignment(SwingConstants.CENTER);

        labelCorreoReserva = new JLabel(correoReserva);
        labelCorreoReserva.setFont(new Font("Rockwell Nova", labelCorreoReserva.getFont().getStyle(), labelCorreoReserva.getFont().getSize()));
        labelCorreoReserva.setForeground(new Color(0, 0, 0));
        labelCorreoReserva.setHorizontalAlignment(SwingConstants.CENTER);

        dateChooser = new JDateChooser();
        dateChooser.getCalendarButton().setIcon(new ImageIcon(EditDialog.class.getResource("/com/toedter/calendar/demo/images/DemoTableColor16.gif")));
        dateChooser.setFont(new Font("Rockwell Nova", dateChooser.getFont().getStyle(), dateChooser.getFont().getSize()));
        dateChooser.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        dateChooser.setDateFormatString("yyyy-MM-dd");
        dateChooser.setDate(Date.valueOf(dia));
        // Accediendo al JTextField y centrando el texto
        JTextField dateTextField = (JTextField) dateChooser.getDateEditor().getUiComponent();
        dateTextField.setHorizontalAlignment(JTextField.CENTER);

        //Panel del formulario
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        formPanel.setBackground(new Color(240, 197, 23));

        JLabel label = new JLabel("ID:");
        label.setIcon(new ImageIcon("src/main/resources/images/id.png"));
        label.setFont(new Font("Rockwell Nova", label.getFont().getStyle(), label.getFont().getSize()));
        formPanel.add(label);
        labelId.setForeground(new Color(0, 0, 0));
        labelId.setBackground(new Color(0, 0, 0));
        formPanel.add(labelId);

        JLabel label_1 = new JLabel("Correo:");
        label_1.setIcon(new ImageIcon("src/main/resources/images/correo.png"));
        label_1.setAlignmentX(Component.RIGHT_ALIGNMENT);
        label_1.setFont(new Font("Rockwell Nova", label_1.getFont().getStyle(), label_1.getFont().getSize()));
        formPanel.add(label_1);
        formPanel.add(labelCorreoReserva);

        JLabel label_2 = new JLabel("Numero Mesa:");
        label_2.setIcon(new ImageIcon("src/main/resources/images/comedor.png"));
        label_2.setFont(new Font("Rockwell Nova", label_2.getFont().getStyle(), label_2.getFont().getSize()));
        formPanel.add(label_2);
        spinnerNumeroMesa = new JSpinner();
        spinnerNumeroMesa.setFont(new Font("Rockwell Nova", spinnerNumeroMesa.getFont().getStyle(), spinnerNumeroMesa.getFont().getSize()));
        spinnerNumeroMesa.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        spinnerNumeroMesa.addChangeListener(this);
        spinnerNumeroMesa.setValue(Integer.valueOf(numeroMesa));
        formPanel.add(spinnerNumeroMesa);

        JLabel label_3 = new JLabel("Dia (AAAA-MM-DD):");
        label_3.setIcon(new ImageIcon("src/main/resources/images/calendario.png"));
        label_3.setFont(new Font("Rockwell Nova", label_3.getFont().getStyle(), label_3.getFont().getSize()));
        formPanel.add(label_3);
        formPanel.add(dateChooser);

        JLabel label_4 = new JLabel("Horario:");
        label_4.setIcon(new ImageIcon("src/main/resources/images/reloj.png"));
        label_4.setFont(new Font("Rockwell Nova", label_4.getFont().getStyle(), label_4.getFont().getSize()));
        formPanel.add(label_4);
        horarioComboBox = new JComboBox();
        horarioComboBox.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        horarioComboBox.setBackground(new Color(255, 255, 255));
        horarioComboBox.setFont(new Font("Rockwell Nova", horarioComboBox.getFont().getStyle(), horarioComboBox.getFont().getSize()));
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

        JLabel label_5 = new JLabel("Cantidad Personas:");
        label_5.setIcon(new ImageIcon("src/main/resources/images/grupo.png"));
        label_5.setFont(new Font("Rockwell Nova", label_5.getFont().getStyle(), label_5.getFont().getSize()));
        formPanel.add(label_5);
        // Crear el modelo del spinner
        model = new SpinnerNumberModel(Integer.parseInt(numeroPersonas), 1, mesaController.maxPersonasMesa(Integer.valueOf(numeroMesa)).intValue(), 1); // valor inicial, min, max, paso
        spinnerNumeroPersonas = new JSpinner(model);
        spinnerNumeroPersonas.setFont(new Font("Rockwell Nova", spinnerNumeroPersonas.getFont().getStyle(), spinnerNumeroPersonas.getFont().getSize()));
        spinnerNumeroPersonas.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        formPanel.add(spinnerNumeroPersonas);

        //Panel del boton de guardar y eliminar
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(240, 197, 23));

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBorderPainted(false);
        btnEliminar.setBackground(new Color(0, 0, 0));
        btnEliminar.setForeground(new Color(240, 197, 23));
        btnEliminar.setFont(new Font("Verdana", btnEliminar.getFont().getStyle() | Font.BOLD, 12));
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
        saveButton.setBackground(new Color(0, 0, 0));
        saveButton.setBorderPainted(false);
        saveButton.setForeground(new Color(240, 197, 23));
        saveButton.setFont(new Font("Verdana", saveButton.getFont().getStyle() | Font.BOLD, 12));
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

                Boolean success = reservaController.editarReserva(reservaActualizada);

                if (!success) {
                    JOptionPane.showMessageDialog(this, "Mesa no disponible en ese horario");
                    return;
                }

                save = true;
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