package com.luiz.classes;

import java.io.IOException;

import com.luiz.gui.janelaInicial;


public class Main {
	
	public static BancoUsuarios bancod;
	public static MyTwitter twitter;

	public static void main(String[] args) throws IOException {
		bancod = new BancoUsuarios();
		twitter = new MyTwitter(bancod);
		
		@SuppressWarnings("unused")
		janelaInicial j = new janelaInicial(twitter); 
	}

}
