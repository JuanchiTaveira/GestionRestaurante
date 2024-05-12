package com.example.views.dialogs.reservas;

import com.example.controller.EmpleadoController;
import com.example.controller.MesaController;
import com.example.controller.ReservaController;
import com.example.controller.ClienteController;
import com.example.model.Reserva;
import com.example.model.Cliente;
import com.example.views.dialogs.clientes.CreateClienteDialog;
import com.example.utils.Constants;
import com.toedter.calendar.JDateChooser;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.Date;
import java.time.LocalDate;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import java.awt.Cursor;
import javax.swing.ImageIcon;

public class InsertDialog extends JDialog implements ChangeListener {
    private static final ReservaController reservaController = new ReservaController();
    private static final ClienteController clienteController = new ClienteController();
    private static final MesaController mesaController = new MesaController();
    private JTextField tfCorreoReserva;
    private boolean save;
    private JComboBox horarioComboBox;
    private JSpinner spinnerNumeroMesa, spinnerNumeroPersonas;
    private Reserva nuevaReserva;
    private JDateChooser dateChooser;
    private SpinnerNumberModel model;

    public InsertDialog() {
    	setBackground(Constants.COLOR_PRINCIPAL_AMARILLO);
    	setFont(new Font("Dialog", Font.BOLD, 12));
    	setModalityType(ModalityType.APPLICATION_MODAL);
    	setOpacity(1.0f);
        initalize();
    }

    public InsertDialog(String numeroMesa, String dia, Reserva.Horario horario) {
        initalize();
        spinnerNumeroMesa.setValue(Integer.valueOf(numeroMesa));
        dateChooser.setDate(Date.valueOf(dia));
        horarioComboBox.setSelectedItem(horario);
    }

    private void initalize() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/images/cubiertos.png"));
        setTitle("Insertar Reserva");
        setSize(400, 350);
        setModal(true);

        //Panel principal
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Constants.COLOR_PRINCIPAL_AMARILLO);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        //Panel del formulario
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        formPanel.setBackground(Constants.COLOR_PRINCIPAL_AMARILLO);

        JLabel labelCorreo = new JLabel("Correo:");
        labelCorreo.setIcon(new ImageIcon("src/main/resources/images/correo.png"));
        labelCorreo.setFont(new Font(Constants.ROCKWELL_NOVA, Font.PLAIN, 12));
        formPanel.add(labelCorreo);
        tfCorreoReserva = new JTextField();
        tfCorreoReserva.setFont(new Font(Constants.ROCKWELL_NOVA, tfCorreoReserva.getFont().getStyle(), 11));
        tfCorreoReserva.setBorder(new LineBorder(Constants.COLOR_NEGRO, 2));
        tfCorreoReserva.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(tfCorreoReserva);

        JLabel labelNumeroMesa = new JLabel("Numero Mesa:");
        labelNumeroMesa.setIcon(new ImageIcon("src/main/resources/images/comedor.png"));
        labelNumeroMesa.setFont(new Font(Constants.ROCKWELL_NOVA, Font.PLAIN, 12));
        formPanel.add(labelNumeroMesa);
        spinnerNumeroMesa = new JSpinner();
        spinnerNumeroMesa.setBorder(new LineBorder(Constants.COLOR_NEGRO, 2));
        spinnerNumeroMesa.setFont(new Font(Constants.ROCKWELL_NOVA, spinnerNumeroMesa.getFont().getStyle(), 11));
        spinnerNumeroMesa.addChangeListener(this);
        formPanel.add(spinnerNumeroMesa);

        JLabel label = new JLabel("Dia (AAAA-MM-DD):");
        label.setIcon(new ImageIcon("src/main/resources/images/calendario.png"));
        label.setFont(new Font(Constants.ROCKWELL_NOVA, label.getFont().getStyle(), 11));
        label.setBackground(Constants.COLOR_PRINCIPAL_AMARILLO);
        formPanel.add(label);
        dateChooser = new JDateChooser();
        dateChooser.getCalendarButton().setFont(new Font(Constants.ROCKWELL_NOVA, dateChooser.getCalendarButton().getFont().getStyle(), dateChooser.getCalendarButton().getFont().getSize()));
        dateChooser.setForeground(Constants.COLOR_NEGRO);
        dateChooser.setBorder(new LineBorder(Constants.COLOR_NEGRO, 2));
        dateChooser.setFont(new Font(Constants.ROCKWELL_NOVA, dateChooser.getFont().getStyle(), 12));
        dateChooser.setDateFormatString("yyyy-MM-dd");
        // Accediendo al JTextField y centrando el texto
        JTextField dateTextField = (JTextField) dateChooser.getDateEditor().getUiComponent();
        dateTextField.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(dateChooser);

        JLabel labelHorario = new JLabel("Horario:");
        labelHorario.setIcon(new ImageIcon("src/main/resources/images/reloj.png"));
        labelHorario.setFont(new Font(Constants.ROCKWELL_NOVA, Font.PLAIN, 12));
        formPanel.add(labelHorario);
        horarioComboBox = new JComboBox();
        horarioComboBox.setOpaque(false);
        horarioComboBox.setFont(new Font(Constants.ROCKWELL_NOVA, horarioComboBox.getFont().getStyle(), 11));
        horarioComboBox.setBorder(new LineBorder(Constants.COLOR_NEGRO, 2));
        horarioComboBox.setBackground(Constants.COLOR_BLANCO);
        horarioComboBox.setModel(new DefaultComboBoxModel(Reserva.Horario.values()));
        horarioComboBox.setSelectedIndex(0);
        BasicComboBoxRenderer basicComboBoxRenderer = new BasicComboBoxRenderer();
        basicComboBoxRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        horarioComboBox.setRenderer(basicComboBoxRenderer);
        formPanel.add(horarioComboBox);

        JLabel labelCantidadPersonas = new JLabel("Cantidad Personas:");
        labelCantidadPersonas.setIcon(new ImageIcon("src/main/resources/images/grupo.png"));
        labelCantidadPersonas.setFont(new Font(Constants.ROCKWELL_NOVA, Font.PLAIN, 12));
        formPanel.add(labelCantidadPersonas);
        // Crear el modelo del spinner
        model = new SpinnerNumberModel(1, 1, 10, 1); // valor inicial, min, max, paso
        spinnerNumeroPersonas = new JSpinner(model);
        spinnerNumeroPersonas.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        spinnerNumeroPersonas.setFont(new Font(Constants.ROCKWELL_NOVA, spinnerNumeroPersonas.getFont().getStyle(), 11));
        spinnerNumeroPersonas.setBorder(new LineBorder(Constants.COLOR_NEGRO, 2));
        spinnerNumeroPersonas.setBackground(Constants.COLOR_PRINCIPAL_AMARILLO);
        formPanel.add(spinnerNumeroPersonas);

        JButton saveButton = new JButton("Guardar");
        saveButton.setBorderPainted(false);
        saveButton.setForeground(Constants.COLOR_PRINCIPAL_AMARILLO);
        saveButton.setBorder(UIManager.getBorder("Button.border"));
        saveButton.setFont(new Font(Constants.VERDANA, saveButton.getFont().getStyle() | Font.BOLD, 13));
        saveButton.setBackground(Constants.COLOR_NEGRO);
        saveButton.addActionListener(e -> {

            Cliente cliente = clienteController.getClienteByCorreo(tfCorreoReserva.getText());

            if (cliente == null) {
                CreateClienteDialog dialog = new CreateClienteDialog(tfCorreoReserva.getText());
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);

                if (dialog.isSave()) {
                    cliente = clienteController.getClienteByCorreo(dialog.getTfCorreoReserva());
                    tfCorreoReserva.setText(cliente.getCorreo());
                }
            }

            Integer numeroMesa = (Integer) spinnerNumeroMesa.getValue();
            LocalDate dia = LocalDate.parse(((JTextField) dateChooser.getDateEditor().getUiComponent()).getText());
            Reserva.Horario horario = Reserva.Horario.valueOf(horarioComboBox.getSelectedItem().toString());
            Integer numeroPersonas = (Integer) spinnerNumeroPersonas.getValue();

            nuevaReserva = new Reserva(cliente, numeroMesa, dia, horario, numeroPersonas, EmpleadoController.authUser);

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
        buttonPanel.setBackground(Constants.COLOR_PRINCIPAL_AMARILLO);
        buttonPanel.add(saveButton);

        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(panel);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource().equals(spinnerNumeroMesa)) {
            int maxPersonas = mesaController.maxPersonasMesa(Integer.parseInt(spinnerNumeroMesa.getValue().toString()));

            if (Integer.parseInt(spinnerNumeroPersonas.getValue().toString()) > maxPersonas) {
                model.setValue(maxPersonas);
            }

            model.setMaximum(maxPersonas);
        }
    }

    public Reserva getNuevaReserva() {
        return nuevaReserva;
    }

    public boolean isSave() {
        return save;
    }
}