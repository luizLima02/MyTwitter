package com.luiz.exceptions;



import com.luiz.classes.Perfil;

public class UJCException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String usuario;
	
	public UJCException(Perfil usuario){
		super("Usuario J� cadastrado");
		this.usuario = usuario.getUsuario();
	}
	
	public String getUsuario(){
		return usuario;
	}

}
