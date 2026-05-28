package util;

public class TeoriaExpresionesRegulares {
	
	public static void main(String[] args) {
		
		//Validar DNI
		System.out.println("8".matches("[0-9]"));
		System.out.println("81".matches("[0-9]"));
		System.out.println("a".matches("[0-9]"));
		System.out.println("@".matches("[0-9]"));
		
		
		System.out.println("8".matches("\\d"));
		System.out.println("81".matches("\\d"));
		System.out.println("a".matches("\\d"));
		System.out.println("@".matches("\\d"));
		
		//Tres digitos
		System.out.println("823".matches("[0-9][0-9][0-9]"));
		System.out.println("823".matches("\\d\\d\\d"));
		System.out.println("823".matches("[0-9]{3}"));
		System.out.println("823".matches("\\d{3}"));
		
		//Validar DNI
		System.out.println("82334567".matches("[0-9]{8}"));
		System.out.println("82334567".matches("\\d{8}"));
		
		//Validar RUC
		System.out.println("82334567123".matches("[0-9]{11}"));
		System.out.println("82334567123".matches("\\d{11}"));
		
		//Validar RUC
		System.out.println("ASS560".matches("[A-Z"));
		System.out.println("82334567123".matches("\\d{11}"));
	}

}
