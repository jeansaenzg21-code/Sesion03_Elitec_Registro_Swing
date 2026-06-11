package vista;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import entidad.Pais;
import entidad.Proveedor;
import entidad.Tipo;
import model.PaisModel;
import model.ProveedorModel;
import model.TipoModel;
import util.ValidateUtil;


public class FrmRegistraProveedor extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtDNI;
	private JComboBox cmbTipo;
	private JComboBox cmbPais;
	private JButton btnRegistrar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRegistraProveedor frame = new FrmRegistraProveedor();
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
	public FrmRegistraProveedor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 419, 431);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegistroProveedor = new JLabel("Registro Proveedor");
		lblRegistroProveedor.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblRegistroProveedor.setBounds(116, 25, 182, 31);
		contentPane.add(lblRegistroProveedor);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombre.setBounds(52, 82, 89, 23);
		contentPane.add(lblNombre);
		
		JLabel lblDni = new JLabel("DNI:");
		lblDni.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDni.setBounds(52, 131, 89, 23);
		contentPane.add(lblDni);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTipo.setBounds(52, 185, 89, 23);
		contentPane.add(lblTipo);
		
		JLabel lblPais = new JLabel("País:");
		lblPais.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPais.setBounds(52, 240, 89, 23);
		contentPane.add(lblPais);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(125, 85, 218, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtDNI = new JTextField();
		txtDNI.setColumns(10);
		txtDNI.setBounds(125, 134, 218, 20);
		contentPane.add(txtDNI);
		
		cmbTipo = new JComboBox();
		cmbTipo.setBounds(125, 187, 218, 22);
		contentPane.add(cmbTipo);
		
		cmbPais = new JComboBox();
		cmbPais.setBounds(125, 241, 218, 22);
		contentPane.add(cmbPais);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(this);
		btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRegistrar.setBounds(125, 305, 158, 23);
		contentPane.add(btnRegistrar);
		cargaTipo();
		cargaPais();

	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegistrar) {
			do_btnRegistrar_actionPerformed(e);
		}
	}
	protected void do_btnRegistrar_actionPerformed(ActionEvent e) {

		String nombre = txtNombre.getText();
		String dni = txtDNI.getText();

		int indexTipo = cmbTipo.getSelectedIndex();
		int indexPais = cmbPais.getSelectedIndex();

		// Validaciones
		if (nombre.matches(ValidateUtil.TEXTO_40) == false) {
			JOptionPane.showMessageDialog(this,
					"El nombre no es válido");
			return;
		}

		if (dni.matches(ValidateUtil.DNI) == false) {
			JOptionPane.showMessageDialog(this,
					"El DNI no es válido");
			return;
		}

		if (indexTipo == 0) {
			JOptionPane.showMessageDialog(this,
					"Seleccione un tipo");
			return;
		}

		if (indexPais == 0) {
			JOptionPane.showMessageDialog(this,
					"Seleccione un país");
			return;
		}

		ProveedorModel model = new ProveedorModel();

		boolean existe = model.existeProveedorPorDNI(dni);

		if (existe) {
			JOptionPane.showMessageDialog(this,
					"El DNI ya existe");
			return;
		}

		// Tipo
		Tipo objTipo = new Tipo();

		objTipo.setIdTipo(
				Integer.parseInt(
						cmbTipo.getSelectedItem()
						.toString()
						.split(" - ")[0]
				));

		// País
		Pais objPais = new Pais();

		objPais.setIdPais(
				Integer.parseInt(
						cmbPais.getSelectedItem()
						.toString()
						.split(" - ")[0]
				));

		// Proveedor
		Proveedor proveedor = new Proveedor();

		proveedor.setNombre(nombre);
		proveedor.setDni(dni);
		proveedor.setFechaRegistro(LocalDateTime.now());
		proveedor.setFechaActualizacion(LocalDateTime.now());
		proveedor.setEstado(1);

		proveedor.setTipo(objTipo);
		proveedor.setPais(objPais);

		int resultado = model.insertaProveedor(proveedor);

		if (resultado > 0) {

			JOptionPane.showMessageDialog(this,
					"Proveedor registrado correctamente");

			txtNombre.setText("");
			txtDNI.setText("");

			cmbTipo.setSelectedIndex(0);
			cmbPais.setSelectedIndex(0);

		} else {

			JOptionPane.showMessageDialog(this,
					"Error al registrar proveedor");
		}
	}

	void cargaTipo() {

		TipoModel model = new TipoModel();
		List<Tipo> lista = model.ListaTodos();

		DefaultComboBoxModel<String> cboModel =
				new DefaultComboBoxModel<String>();

		cboModel.addElement("[ Seleccione ]");

		for (Tipo t : lista) {
			cboModel.addElement(
					t.getIdTipo() + " - " +
					t.getDescripcion());
		}

		cmbTipo.setModel(cboModel);
	}

	void cargaPais() {

		PaisModel model = new PaisModel();
		List<Pais> lista = model.ListaTodos();

		DefaultComboBoxModel<String> cboModel =
				new DefaultComboBoxModel<String>();

		cboModel.addElement("[ Seleccione ]");

		for (Pais p : lista) {
			cboModel.addElement(
					p.getIdPais() + " - " +
					p.getNombre());
		}

		cmbPais.setModel(cboModel);
	}

	}