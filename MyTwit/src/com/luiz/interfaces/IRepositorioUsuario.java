package com.luiz.interfaces;



import com.luiz.classes.Perfil;
import com.luiz.exceptions.UJCException;
import com.luiz.exceptions.UNCException;

public interface IRepositorioUsuario {
	
	
	void cadastrar(Perfil usuario) throws UJCException;
	
	Perfil buscar(String usuario);
	
	void atualizar(Perfil usuario) throws UNCException;

}
