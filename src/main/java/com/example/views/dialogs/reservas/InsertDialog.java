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
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Dialog.ModalityType;
import java.awt.Font;
import java.awt.Window.Type;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import java.awt.Cursor;
import javax.swing.ImageIcon;
import javax.swing.border.MatteBorder;

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
    	setBackground(new Color(240, 197, 23));
    	setFont(new Font("Dialog", Font.BOLD, 12));
    	setModalityType(ModalityType.APPLICATION_MODAL);
    	setOpacity(1.5f);
        initalize();
    }

    public InsertDialog(String numeroMesa, String dia, Reserva.Horario horario) {
        initalize();
        spinnerNumeroMesa.setValue(Integer.valueOf(numeroMesa));
        dateChooser.setDate(Date.valueOf(dia));
        horarioComboBox.setSelectedItem(horario);
    }

    private void initalize() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/images/logo_pestana.png"));
        setTitle("Insertar Reserva");
        setSize(400, 350);
        setModal(true);

        //Panel principal
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 197, 23));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        //Panel del formulario
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        formPanel.setBackground(new Color(240, 197, 23));

        JLabel label_1 = new JLabel("Correo:");
        label_1.setIcon(new ImageIcon("src/main/resources/images/correo.png"));
        label_1.setFont(new Font("Rockwell Nova", Font.PLAIN, 12));
        formPanel.add(label_1);
        tfCorreoReserva = new JTextField();
        tfCorreoReserva.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        tfCorreoReserva.setFont(new Font("Rockwell Nova", tfCorreoReserva.getFont().getStyle(), 11));
        tfCorreoReserva.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        tfCorreoReserva.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(tfCorreoReserva);

        JLabel label_2 = new JLabel("Numero Mesa:");
        label_2.setIcon(new ImageIcon("src/main/resources/images/comedor.png"));
        label_2.setFont(new Font("Rockwell Nova", Font.PLAIN, 12));
        formPanel.add(label_2);
        spinnerNumeroMesa = new JSpinner();
        spinnerNumeroMesa.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        spinnerNumeroMesa.setFont(new Font("Rockwell Nova", spinnerNumeroMesa.getFont().getStyle(), 11));
        spinnerNumeroMesa.addChangeListener(this);
        formPanel.add(spinnerNumeroMesa);

        JLabel label = new JLabel("Dia (AAAA-MM-DD):");
        label.setIcon(new ImageIcon("src/main/resources/images/calendario.png"));
        label.setFont(new Font("Rockwell Nova", label.getFont().getStyle(), 11));
        label.setBackground(new Color(240, 197, 23));
        formPanel.add(label);
        dateChooser = new JDateChooser();
        dateChooser.getCalendarButton().setFont(new Font("Rockwell Nova", dateChooser.getCalendarButton().getFont().getStyle(), dateChooser.getCalendarButton().getFont().getSize()));
        dateChooser.setForeground(new Color(0, 0, 0));
        dateChooser.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        dateChooser.setFont(new Font("Rockwell Nova", dateChooser.getFont().getStyle(), 12));
        dateChooser.setDateFormatString("yyyy-MM-dd");
        // Accediendo al JTextField y centrando el texto
        JTextField dateTextField = (JTextField) dateChooser.getDateEditor().getUiComponent();
        dateTextField.setHorizontalAlignment(JTextField.CENTER);
        formPanel.add(dateChooser);

        JLabel label_3 = new JLabel("Horario:");
        label_3.setIcon(new ImageIcon("src/main/resources/images/reloj.png"));
        label_3.setFont(new Font("Rockwell Nova", Font.PLAIN, 12));
        formPanel.add(label_3);
        horarioComboBox = new JComboBox();
        horarioComboBox.setOpaque(false);
        horarioComboBox.setFont(new Font("Rockwell Nova", horarioComboBox.getFont().getStyle(), 11));
        horarioComboBox.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        horarioComboBox.setBackground(new Color(255, 255, 255));
        horarioComboBox.setModel(new DefaultComboBoxModel(Reserva.Horario.values()));
        horarioComboBox.setSelectedIndex(0);
        BasicComboBoxRenderer basicComboBoxRenderer = new BasicComboBoxRenderer();
        basicComboBoxRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        horarioComboBox.setRenderer(basicComboBoxRenderer);
        formPanel.add(horarioComboBox);

        JLabel label_4 = new JLabel("Cantidad Personas:");
        label_4.setIcon(new ImageIcon("src/main/resources/images/grupo.png"));
        label_4.setFont(new Font("Rockwell Nova", Font.PLAIN, 12));
        formPanel.add(label_4);
        // Crear el modelo del spinner
        model = new SpinnerNumberModel(1, 1, 10, 1); // valor inicial, min, max, paso
        spinnerNumeroPersonas = new JSpinner(model);
        spinnerNumeroPersonas.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        spinnerNumeroPersonas.setFont(new Font("Rockwell Nova", spinnerNumeroPersonas.getFont().getStyle(), 11));
        spinnerNumeroPersonas.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        spinnerNumeroPersonas.setBackground(new Color(240, 197, 23));
        formPanel.add(spinnerNumeroPersonas);

        JButton saveButton = new JButton("Guardar");
        saveButton.setBorderPainted(false);
        saveButton.setForeground(new Color(240, 197, 23));
        saveButton.setBorder(UIManager.getBorder("Button.border"));
        saveButton.setFont(new Font("Verdana", saveButton.getFont().getStyle() | Font.BOLD, 13));
        saveButton.setBackground(new Color(0, 0, 0));
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
        buttonPanel.setBackground(new Color(240, 197, 23));
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