package com.example.views.dialogs.reservas;

import com.example.controller.EmpleadoController;
import com.example.controller.MesaController;
import com.example.controller.ReservaController;
import com.example.controller.ClienteController;
import com.example.model.Reserva;
import com.example.model.Cliente;
import com.example.views.dialogs.clientes.CreateClienteDialog;
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
        initalize();
    }

    public InsertDialog(String numeroMesa, String dia, Reserva.Horario horario) {
        initalize();
        spinnerNumeroMesa.setValue(Integer.valueOf(numeroMesa));
        dateChooser.setDate(Date.valueOf(dia));
        horarioComboBox.setSelectedItem(horario);
    }

    private void initalize() {
        setTitle("Insertar Reserva");
        setSize(400, 350);
        setModal(true);

        //Panel principal
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        //Panel del formulario
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));

        formPanel.add(new JLabel("Correo:"));
        tfCorreoReserva = new JTextField();
        tfCorreoReserva.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(tfCorreoReserva);

        formPanel.add(new JLabel("Numero Mesa:"));
        spinnerNumeroMesa = new JSpinner();
        spinnerNumeroMesa.addChangeListener(this);
        formPanel.add(spinnerNumeroMesa);

        formPanel.add(new JLabel("Dia (AAAA-MM-DD):"));
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("yyyy-MM-dd");
        // Accediendo al JTextField y centrando el texto
        JTextField dateTextField = (JTextField) dateChooser.getDateEditor().getUiComponent();
        dateTextField.setHorizontalAlignment(JTextField.CENTER);
        formPanel.add(dateChooser);

        formPanel.add(new JLabel("Horario:"));
        horarioComboBox = new JComboBox();
        horarioComboBox.setModel(new DefaultComboBoxModel(Reserva.Horario.values()));
        horarioComboBox.setSelectedIndex(0);
        BasicComboBoxRenderer basicComboBoxRenderer = new BasicComboBoxRenderer();
        basicComboBoxRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        horarioComboBox.setRenderer(basicComboBoxRenderer);
        formPanel.add(horarioComboBox);

        formPanel.add(new JLabel("Cantidad Personas:"));
        // Crear el modelo del spinner
        model = new SpinnerNumberModel(1, 1, 10, 1); // valor inicial, min, max, paso
        spinnerNumeroPersonas = new JSpinner(model);
        formPanel.add(spinnerNumeroPersonas);

        JButton saveButton = new JButton("Guardar");
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