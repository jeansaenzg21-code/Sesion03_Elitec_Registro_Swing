package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import entidad.Concurso;
import model.ConcursoModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmRegistraConcurso extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtInicio;
	private JTextField txtFin;
	private JButton btnNewButton;
	@SuppressWarnings("rawtypes")
	private JComboBox cmbEstado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRegistraConcurso frame = new FrmRegistraConcurso();
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public FrmRegistraConcurso() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 445, 361);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegistrarConcurso = new JLabel("Registrar Concurso");
		lblRegistrarConcurso.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrarConcurso.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRegistrarConcurso.setBounds(110, 44, 243, 14);
		contentPane.add(lblRegistrarConcurso);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(41, 87, 71, 14);
		contentPane.add(lblNombre);
		
		JLabel lblFechaInicio = new JLabel("Fecha Inicio:");
		lblFechaInicio.setBounds(41, 122, 71, 14);
		contentPane.add(lblFechaInicio);
		
		JLabel lblFechaFin = new JLabel("Fecha Fin:");
		lblFechaFin.setBounds(41, 157, 71, 14);
		contentPane.add(lblFechaFin);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(41, 193, 71, 14);
		contentPane.add(lblEstado);
		
		cmbEstado = new JComboBox();
		cmbEstado.setModel(new DefaultComboBoxModel(new String[] {"Activo", "Inactivo"}));
		cmbEstado.setBounds(110, 189, 125, 22);
		contentPane.add(cmbEstado);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(110, 84, 243, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtInicio = new JTextField();
		txtInicio.setColumns(10);
		txtInicio.setBounds(110, 119, 243, 20);
		contentPane.add(txtInicio);
		
		txtFin = new JTextField();
		txtFin.setColumns(10);
		txtFin.setBounds(110, 154, 243, 20);
		contentPane.add(txtFin);
		
		btnNewButton = new JButton("Registrar");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(266, 236, 89, 23);
		contentPane.add(btnNewButton);

	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNewButton) {
			do_btnNewButton_actionPerformed(e);
		}
	}
	protected void do_btnNewButton_actionPerformed(ActionEvent e) {
		//1 Recibir los datos del formulario en String
				String nombre = txtNombre.getText();
			    String inicio = txtInicio.getText();
			    String fin = txtFin.getText();
			    String estado = cmbEstado.getSelectedItem().toString();
				
				//2 Crear el objeto Alumno
			    Concurso obj = new Concurso();
				obj.setNombre(nombre);
			    obj.setFechaInicio(java.time.LocalDate.parse(inicio));
			    obj.setFechaFin(java.time.LocalDate.parse(fin));
			    obj.setEstado(estado);
				//3 Crear el objeto AlumnoModel
			    ConcursoModel model = new ConcursoModel();
			    int salida = model.insertaConcurso(obj);
				
				//4 Mostrar el resultado
				if (salida > 0) {
					JOptionPane.showMessageDialog(this, "Concurso registrado correctamente");
				} else {
					JOptionPane.showMessageDialog(this, "Error al registrar el concurso");
				}
				
			}
	
}
