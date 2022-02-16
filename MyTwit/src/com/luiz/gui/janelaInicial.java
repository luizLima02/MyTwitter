package com.luiz.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JPanel;

import com.luiz.classes.MyTwitter;

public class janelaInicial implements ActionListener {
	
	JFrame frameini;
    JButton logar;
    static MyTwitter twitter;
	JButton cadastar;
	
	void Logar(){
		frameini.dispose();
		@SuppressWarnings("unused")
		janelaLogin janelalogin = new janelaLogin(twitter);
	}
	
	void Cadastrar(){
		frameini.dispose();
		@SuppressWarnings("unused")
		janelaCadastro ja = new janelaCadastro(twitter);
	}
	
	JPanel Entrada(){
		
		logar = new JButton();
		logar.setBounds(160, 425, 100, 50);
		logar.setText("Logar");
		logar.setFocusable(false);
		logar.addActionListener(this);
		
		cadastar = new JButton();
		cadastar.setBounds(320, 425, 100, 50);
		cadastar.setText("Cadastrar");
		cadastar.setFocusable(false);
		cadastar.addActionListener(this);
		
		JPanel tentrada = new JPanel();
		tentrada.setBackground(new Color(64,88,124));
		tentrada.setBounds(105, 105, 580,580);
		tentrada.setLayout(null);
		tentrada.add(cadastar);
		tentrada.add(logar);
		
		return tentrada;
	}
	
	public janelaInicial(MyTwitter twitter){
		janelaInicial.twitter = twitter;
		JPanel entrada = Entrada();
		frameini = new JFrame();
		frameini.setTitle("My twitter");
		frameini.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameini.setPreferredSize(new Dimension(840,840));
		frameini.setResizable(false);
		frameini.getContentPane().setBackground(new Color(64,88,124));
		frameini.setLayout(null);
		frameini.add(entrada);
		frameini.pack();
		frameini.setLocationRelativeTo(null);
		frameini.setVisible(true);	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == logar){
			Logar();
		}
		else if(e.getSource() == cadastar){
			Cadastrar();
		}
		
	}

}
