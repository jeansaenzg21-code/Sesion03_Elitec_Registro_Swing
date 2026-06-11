package entidad;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Proveedor {

	private int idProveedor;
	private String nombre;
	private String dni;
	private LocalDateTime fechaRegistro;
	private LocalDateTime fechaActualizacion;
	private int estado;

	// Foreign Keys
	private Tipo tipo;
	private Pais pais;

}