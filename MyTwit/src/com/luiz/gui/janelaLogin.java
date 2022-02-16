package com.luiz.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.luiz.classes.Main;
import com.luiz.classes.MyTwitter;


public class janelaLogin implements ActionListener {
	
	JFrame framelog;
	static JButton entrar;
	static JButton cancelar;
	static JTextField usuariofield;
	static MyTwitter twitter;
	
	void logar(){
		if(Main.bancod.buscar(usuariofield.getText()) == null){
			JOptionPane.showMessageDialog(null, "Usuario Invalido", "User Invalido", JOptionPane.ERROR_MESSAGE);
		}else{
			framelog.dispose();
			@SuppressWarnings("unused")
			janelaPrincipal jp = new janelaPrincipal(Main.bancod.buscar(usuariofield.getText()),twitter);
		}
		
	}
	
	JPanel Painel(){
		entrar = new JButton(); // realiza as funcoes de login
		entrar.setBounds(60, 325, 100, 50);
		entrar.setText("entrar");
		entrar.setFocusable(false);
		entrar.addActionListener(this);
		cancelar = new JButton();// volta para a tela inicial
		cancelar.setBounds(220, 325, 100, 50);
		cancelar.setText("cancelar");
		cancelar.setFocusable(false);
		cancelar.addActionListener(this);
		
		JLabel usuarioLabel = new JLabel();
		usuarioLabel.setText("Usuario:");
		usuarioLabel.setBounds(100, 160, 200, 30);
		usuariofield = new JTextField();
		usuariofield.setBounds(100, 200, 200, 30);
		
		
		JPanel tlog = new JPanel();
		tlog.setBounds(210, 120, 380,500);
		tlog.setLayout(null);
		tlog.add(entrar);
		tlog.add(cancelar);
		tlog.add(usuariofield);
		tlog.add(usuarioLabel);
		tlog.setVisible(true);
		
		return tlog;
	}
	
	janelaLogin(MyTwitter twitter){
		janelaLogin.twitter = twitter;
		JPanel panelLogin = Painel();
		//log.setBackground(new Color(64,88,124));
		framelog = new JFrame();
		framelog.setTitle("Login");
		framelog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framelog.setPreferredSize(new Dimension(840,840));
		framelog.setResizable(false);
		framelog.getContentPane().setBackground(new Color(64,88,124));
		framelog.setLayout(null);
		framelog.add(panelLogin);
		framelog.pack();
		framelog.setLocationRelativeTo(null);
		framelog.setVisible(true);
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cancelar){
			framelog.dispose();
			@SuppressWarnings("unused")
			janelaInicial j = new janelaInicial(twitter);
			
		}if(e.getSource() == entrar){
			logar();
		}
		
	}

}
