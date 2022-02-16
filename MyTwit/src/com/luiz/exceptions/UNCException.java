package com.luiz.exceptions;

import com.luiz.classes.Perfil;

public class UNCException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String usuario;
	
	public UNCException(Perfil usuario){
		super("Usuario não cadastrado");
		this.usuario = usuario.getUsuario();
	}
	
	
	public String getUsuario(){
		return usuario;
	}
	

}
