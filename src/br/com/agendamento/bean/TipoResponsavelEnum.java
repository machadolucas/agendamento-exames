package br.com.agendamento.bean;

/**
 * @author lucasmachado
 * 
 */
public enum TipoResponsavelEnum {

	/**
	 * Usu�rio com todas as permiss�es
	 */
	ADMINISTRADOR("Administrador"), /**
	 * 
	 * Usu�rio com todas as permiss�es
	 */
	COORDENADOR("Coordenador"), /**
	 * 
	 * Usu�rio com permiss�es de visualiza��o e
	 * edi��o de status
	 */
	USUARIO("Usu�rio");

	String value;

	private TipoResponsavelEnum(String value) {
		this.value = value;
	}

	/**
	 * @return
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * @param value
	 * @return
	 */
	public static TipoResponsavelEnum getTypeByValue(String value) {
		if (value.equals(ADMINISTRADOR.getValue())) {
			return TipoResponsavelEnum.ADMINISTRADOR;
		} else if (value.equals(COORDENADOR.getValue())) {
			return TipoResponsavelEnum.COORDENADOR;
		}
		return TipoResponsavelEnum.USUARIO;
	}

}
