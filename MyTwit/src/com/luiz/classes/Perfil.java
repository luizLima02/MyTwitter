package com.luiz.classes;
import java.util.Vector;

public abstract class Perfil {
	private String usuario;
	private Vector<Perfil> seguidos = new Vector<Perfil>();
	private Vector<Perfil> seguidores =  new Vector<Perfil>();
	private Vector<Tweet> timeline =  new Vector<Tweet>();
	private Boolean ativo;
	
	public Perfil(String usuario) {
		this.usuario = usuario;
	}
	public void addSeguido(Perfil usuario){ seguidos.add(usuario);}
	
	public void addSeguidor(Perfil usuario){seguidores.add(usuario);}
	
	public void addTweet(Tweet tweet){ timeline.add(tweet);}
	
	public void setUsuario(String usuario) {this.usuario = usuario;}
	
	public String getUsuario() { return usuario;}
	
	public Vector<Perfil> getSeguido(){ return seguidos;}
	
	public Vector<Perfil> getSeguidores(){ return seguidores;}
	
	public Vector<Tweet> getTimeline(){ return timeline;}
	
	public void setAtivo(Boolean valor){this.ativo = valor;}
	
	public Boolean isAtivo() {return ativo; }
}
