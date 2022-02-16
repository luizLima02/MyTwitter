package com.luiz.classes;
import java.util.Vector;

import com.luiz.exceptions.UJCException;
import com.luiz.exceptions.UNCException;
import com.luiz.interfaces.IRepositorioUsuario;

public class BancoUsuarios implements IRepositorioUsuario {
	
	private Vector<Perfil> usuarios = new Vector<Perfil>();

	
	@Override
	public Perfil buscar(String usuario) {
			for(Perfil i : usuarios){
				if(i.getUsuario().equals(usuario)){
					return i;
				}
			}
		return null;
	}
	@Override
	public void cadastrar(Perfil usuario) throws UJCException
	{
		if(buscar(usuario.getUsuario()) == null) {
			usuario.setAtivo(true);
			usuarios.add(usuario);
			System.out.println("User cadastrado");
		}else{
			throw new UJCException(usuario);
		}
		
	}


	@Override
	public void atualizar(Perfil usuario) throws UNCException{
		if(buscar(usuario.getUsuario()) != null){
			Perfil remover = usuario;
			for(Perfil tdp:usuarios){ //rodando todos os perfis
				for(Perfil seg:tdp.getSeguido()) {//rodando os seguidos
					if(seg == remover) {tdp.getSeguido().remove(remover);break;} 
				}
				for(Perfil segd:tdp.getSeguidores()) {//rodando os seguidores
					if(segd == remover) {tdp.getSeguidores().remove(remover);break;}
				}
			}
			
			
			usuarios.remove(remover);
		}else{
			throw new UNCException(usuario);
		}
		
	}
	
	
	

}
