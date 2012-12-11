package br.com.agendamento.bean;

/**
 * @author lucasmachado
 * 
 */
public enum TipoResponsavelEnum {

	/**
	 * Usuário com todas as permissões
	 */
	ADMINISTRADOR("Administrador"), /**
	 * 
	 * Usuário com todas as permissões
	 */
	COORDENADOR("Coordenador"), /**
	 * 
	 * Usuário com permissões de visualização e
	 * edição de status
	 */
	USUARIO("Usuário");

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
