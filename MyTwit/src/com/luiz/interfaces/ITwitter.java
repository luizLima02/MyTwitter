package com.luiz.interfaces;

import java.util.Vector;

import com.luiz.classes.Perfil;
import com.luiz.exceptions.PDException;
import com.luiz.exceptions.PIException;
import com.luiz.exceptions.SIException;
import com.luiz.exceptions.UJCException;

public interface ITwitter {
	
	void criarPerfil(Perfil usuario) throws UJCException;
	void cancelarPerfil(String usuario) throws PIException, PDException;
	void tweetar(String usuario, String mensagem) throws PIException;
	void seguir(String seguidor, String seguido)  throws PIException, PDException, SIException;
	int numeroSeguidores(String Usuario) throws PIException, PDException;
	Vector<Perfil> seguidores(String usuario) throws PIException, PDException;
	Vector<Perfil> seguidos(String usuario) throws PIException, PDException;
	

}
