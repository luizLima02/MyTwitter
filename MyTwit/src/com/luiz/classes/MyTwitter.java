package com.luiz.classes;

import java.util.Vector;


import com.luiz.exceptions.PDException;
import com.luiz.exceptions.PIException;
import com.luiz.exceptions.SIException;
import com.luiz.exceptions.UJCException;
import com.luiz.exceptions.UNCException;
import com.luiz.interfaces.IRepositorioUsuario;
import com.luiz.interfaces.ITwitter;

public class MyTwitter implements ITwitter {
	
	private IRepositorioUsuario repositorio;
	
	public MyTwitter(IRepositorioUsuario repositorio){
		this.repositorio = repositorio;
	}
	

	@Override
	public void criarPerfil(Perfil usuario) throws UJCException {
		repositorio.cadastrar(usuario);
	}

	@Override
	public void cancelarPerfil(String usuario)  throws PIException, PDException {
		Perfil user = repositorio.buscar(usuario);
		if(user == null){
			throw new PIException();
		}else if(user.isAtivo() == false){
			throw new PDException();
		}
		try {
			repositorio.atualizar(user);
		} catch (UNCException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void tweetar(String usuario, String mensagem) throws PIException {
		Perfil user = repositorio.buscar(usuario);
		if(user != null){
			Tweet tweet = new Tweet();
			tweet.setUsuario(usuario);
			tweet.setMensagem(mensagem);
			user.addTweet(tweet);
		}else{
			throw new PIException();
		}
		
	}


	@Override
	public void seguir(String seguidor, String seguido) throws PIException, PDException, SIException {
		Perfil userseguidor = repositorio.buscar(seguidor);
		Perfil userseguido = repositorio.buscar(seguido);
		if(userseguidor == null || userseguido == null){
			throw new PIException();
		}else if(userseguidor.isAtivo() == false || userseguido.isAtivo() == false){
			throw new PDException();
		}else if(userseguidor == userseguido){
			throw new SIException();
		}else{
			userseguidor.addSeguido(userseguido);
			userseguido.addSeguidor(userseguidor);
		}
	}

	@Override
	public int numeroSeguidores(String Usuario) throws PIException, PDException {
		int i = 0;
		Perfil user = repositorio.buscar(Usuario);
		if(user == null){
			throw new PIException();
		}else if(user.isAtivo() == false){
			throw new PDException();
		}
		i = user.getSeguidores().size();
		return i;
	}

	@Override
	public Vector<Perfil> seguidores(String usuario) throws PIException, PDException {
		Perfil user = repositorio.buscar(usuario);
		if(user == null){
			throw new PIException();
		}else if(user.isAtivo() == false){
			throw new PDException();
		}else {
			return user.getSeguidores();
		}
	}

	@Override
	public Vector<Perfil> seguidos(String usuario) throws PIException, PDException {
		// TODO Auto-generated method stub
		Perfil user = repositorio.buscar(usuario);
		if(user == null){
			throw new PIException();
		}else if(user.isAtivo() == false){
			throw new PDException();
		}else {
			return user.getSeguido();
		}
     	}
	

}
