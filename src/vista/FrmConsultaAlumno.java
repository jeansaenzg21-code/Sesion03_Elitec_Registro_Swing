package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entidad.Alumno;
import model.AlumnoModel;
import util.ValidateUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;
import java.util.List;
import java.awt.event.KeyEvent;

public class FrmConsultaAlumno extends JFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtDNI;
	private JTextField txtCorreo;
	private JTextField txtdesde;
	private JTextField txthasta;
	private JTable table;
	private JButton btnFiltrar;
	private JButton btnCancelar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmConsultaAlumno frame = new FrmConsultaAlumno();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmConsultaAlumno() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 686, 448);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Consulta de Alumno");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(248, 31, 211, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombre.setBounds(37, 73, 57, 14);
		contentPane.add(lblNombre);
		
		JLabel lblDni = new JLabel("DNI:");
		lblDni.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDni.setBounds(248, 74, 57, 14);
		contentPane.add(lblDni);
		
		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCorreo.setBounds(430, 74, 57, 14);
		contentPane.add(lblCorreo);
		
		JLabel lblFechaNacimientodesde = new JLabel("Fecha Nacimiento (desde):");
		lblFechaNacimientodesde.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFechaNacimientodesde.setBounds(59, 126, 160, 14);
		contentPane.add(lblFechaNacimientodesde);
		
		JLabel lblhasta = new JLabel("(hasta):");
		lblhasta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblhasta.setBounds(354, 127, 57, 14);
		contentPane.add(lblhasta);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(90, 71, 148, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtDNI = new JTextField();
		txtDNI.addKeyListener(this);
		txtDNI.setColumns(10);
		txtDNI.setBounds(283, 71, 137, 20);
		contentPane.add(txtDNI);
		
		txtCorreo = new JTextField();
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(478, 71, 148, 20);
		contentPane.add(txtCorreo);
		
		txtdesde = new JTextField();
		txtdesde.setColumns(10);
		txtdesde.setBounds(213, 124, 131, 20);
		contentPane.add(txtdesde);
		
		txthasta = new JTextField();
		txthasta.setColumns(10);
		txthasta.setBounds(407, 124, 148, 20);
		contentPane.add(txthasta);
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(this);
		btnFiltrar.setBounds(231, 178, 89, 23);
		contentPane.add(btnFiltrar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(350, 178, 89, 23);
		contentPane.add(btnCancelar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 215, 650, 183);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Nombre", "DNI", "Correo", "Fecha Nacimiento"
			}
		));
		scrollPane.setViewportView(table);

	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancelar) {
			do_btnCancelar_actionPerformed(e);
		}
		if (e.getSource() == btnFiltrar) {
			do_btnFiltrar_actionPerformed(e);
		}
	}
	protected void do_btnFiltrar_actionPerformed(ActionEvent e) {
		//1 Recibimos todos los parametros del formulario
				String nombre = txtNombre.getText();
				String dni = txtDNI.getText();
				String correo = txtCorreo.getText();
				String desde = txtdesde.getText();
				String hasta = txthasta.getText();
				
				//imprimir los parametros recibidos
				System.out.println("Parametros recibidos: ");
				System.out.println("Nombre: " + nombre);
				System.out.println("DNI: " + dni);
				System.out.println("Correo: " + correo);
				System.out.println("Desde: " + desde);
				System.out.println("Hasta: " + hasta);
				
				
				//2 Validacion
				if (!desde.isEmpty()  && desde.matches(ValidateUtil.DATE_YYYY_MM_DD) == false) {
					JOptionPane.showMessageDialog(this,"La fecha de nacimiento(Desde) no es válida. Tiene que tener el formato YYYY-MM-DD");
					return;
				}
				if (!hasta.isEmpty()  && hasta.matches(ValidateUtil.DATE_YYYY_MM_DD) == false) {
					JOptionPane.showMessageDialog(this,"La fecha de nacimiento(Hasta) no es válida. Tiene que tener el formato YYYY-MM-DD");
					return;
				}
				
				//Fecha desde debe ser menor o igual a fecha hasta
				if (!desde.isEmpty() && !hasta.isEmpty()) {
					LocalDate fechaDesde = LocalDate.parse(desde);
					LocalDate fechaHasta = LocalDate.parse(hasta);
					if (fechaDesde.isAfter(fechaHasta)) {
						JOptionPane.showMessageDialog(this,"La fecha de nacimiento(Desde) no puede ser mayor a la fecha de nacimiento(Hasta)");
						return;
					}
				}
				
				LocalDate fechaDesde = desde.isEmpty()? LocalDate.parse("9999-01-01"): LocalDate.parse(desde);
				LocalDate fechaHasta = hasta.isEmpty()? LocalDate.parse("9999-01-01"): LocalDate.parse(hasta);
				
				//3 Crear la clase model
				AlumnoModel objAlumnoModel = new AlumnoModel();
				List<Alumno> lista = objAlumnoModel.listaAlumno(nombre, dni, correo, fechaDesde, fechaHasta);
				
				//4 recorremos la lista
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0); // Limpiar la tabla antes de agregar nuevos datos
				
				for (Alumno a : lista) {
					Object[] rowData = { a.getIdAlumno(), 
										 a.getNombre(), 
										 a.getDni(), 
										 a.getCorreo(), 
										 a.getFechaNacimiento() 
										 };
					model.addRow(rowData);
				}
				
			}
			protected void do_btnCancelar_actionPerformed(ActionEvent e) {
				txtNombre.setText("");
				txtDNI.setText("");
				txtCorreo.setText("");
				txtdesde.setText("");
				txthasta.setText("");
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0); // Limpiar la tabla
			}
			public void keyPressed(KeyEvent e) {

			}
			public void keyReleased(KeyEvent e) {
				if (e.getSource() == txtDNI) {
					do_txtDni_keyReleased(e);
				}
			}
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				System.out.println("Tecla presionada: keyPressed " + c);
				//Si es letra no ingresa
				if (Character.isLetter(c)) {
					e.consume(); // Ignorar la letra
				}
				//Si se ingresa más 8 dígitos digitos
				if (Character.isDigit(c) && txtDNI.getText().length() >= 8) {
					e.consume(); // Ignorar el dígito si ya hay 8
				}
			}
			protected void do_txtDni_keyReleased(KeyEvent e) {
			}
		}