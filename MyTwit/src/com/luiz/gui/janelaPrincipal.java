package com.luiz.gui;


import java.awt.Color;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import com.luiz.classes.Main;
import com.luiz.classes.MyTwitter;
import com.luiz.classes.Perfil;
import com.luiz.classes.PessoaFisica;
import com.luiz.classes.PessoaJuridica;
import com.luiz.classes.Tweet;
import com.luiz.exceptions.PDException;
import com.luiz.exceptions.PIException;
import com.luiz.exceptions.SIException;




public class janelaPrincipal implements ActionListener {
	//variaveis
	JFrame framePrinc;
	Perfil usuario;
	JPanel container, timeline;
	JButton botaoTweetar,botaoPesquisar,botaoSair;
	JTextArea tweetarArea,timeArea;
	JTextField pesquisarArea;
	JLabel tweetarLabel;
	static MyTwitter twitter;
	

	//funcoes
	void perfilShow() throws PIException, PDException{
		if(this.usuario.isAtivo() == true){
		String mensagem = usuario.getUsuario() + "\nSeguidores: " + twitter.numeroSeguidores(this.usuario.getUsuario());
		if(this.usuario instanceof PessoaJuridica){
			mensagem = mensagem + "\n" + ((PessoaJuridica) usuario).getCnpj(); 
		}else if(this.usuario instanceof PessoaFisica){
			mensagem = mensagem + "\n" + ((PessoaFisica) usuario).getCPF(); 
		}
		String[] options = new String[3];
		int reposta;
		options[0] = "seguidos";
		options[1] = "seguidores";
		options[2] = "sair";
		reposta = JOptionPane.showOptionDialog( null, mensagem, "", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null);
		if(reposta == 0){
			String m = "";
			for(Perfil p:twitter.seguidos(usuario.getUsuario())){
				m = m + p.getUsuario() + "\n";
			}
			JOptionPane.showMessageDialog(null, m, "", JOptionPane.INFORMATION_MESSAGE);
		}
		if(reposta == 1){
			String m = "";
			for(Perfil p:twitter.seguidores(usuario.getUsuario())){
				m = m + p.getUsuario() + "\n";
			}
			JOptionPane.showMessageDialog(null, m, "", JOptionPane.INFORMATION_MESSAGE);
			
		}
	}else{
		JOptionPane.showMessageDialog(null, "Perfil Inativo\n Ative-o para usar o perfil", "User Desativado", JOptionPane.ERROR_MESSAGE);
	}
		
	}
	
	void config(){
		String[] options = new String[4];
		int reposta;
		options[0] = "ativar";
		options[1] = "desativar";
		options[2] = "deletar";
		options[3] = "sair";
		String mensagem;
		if(this.usuario.isAtivo()){
			mensagem = "Perfil Ativo";
		}else{
			mensagem = "Perfil Inativo";
		}
		reposta = JOptionPane.showOptionDialog( null, mensagem, "", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null);
		if(reposta == 0){
			this.usuario.setAtivo(true);
		}
		if(reposta == 1){
			this.usuario.setAtivo(false);
		}
		if(reposta == 2){
			try {
				Main.twitter.cancelarPerfil(usuario.getUsuario());
				JOptionPane.showMessageDialog(null, "Usuario Deletado", "User Deletado", JOptionPane.INFORMATION_MESSAGE);
				framePrinc.dispose();
				@SuppressWarnings("unused")
				janelaInicial j = new janelaInicial(twitter);
			} catch (PIException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (PDException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getMessage(), "Deletado User error", JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}
	
	void seguidos(){
		for(Perfil p: usuario.getSeguido()){
			if(p.isAtivo()){
				for(Tweet t:p.getTimeline()){
					timeArea.setText(timeArea.getText() + "\n-----------------------\n" +t.getUsuario() + " : " + t.getMensagem());
				}
				timeline.updateUI();
			}
		}
	}
	
	void essePerfil(){
	 
		String mensg = timeArea.getText();
		for(Tweet t: usuario.getTimeline()){
			mensg = mensg + "\n-----------------------\n" +t.getUsuario() + " : " + t.getMensagem();
		}
		timeArea.setText(mensg);
		timeline.updateUI();
	}
	
	void tweetar(){
		try {
			twitter.tweetar(usuario.getUsuario(), tweetarArea.getText());
		} catch (PIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tweetarArea.setText("");
		timeArea.setText("");
		seguidos();
		String mensg = timeArea.getText();
		for(Tweet t: usuario.getTimeline()){
			mensg = mensg + "\n-----------------------\n" +t.getUsuario() + " : " + t.getMensagem();
		}
		
		timeArea.setText(mensg);
		timeline.updateUI();
	}

	
	void pesquisar(){
	   if(this.usuario.isAtivo() == true){
		Perfil user = Main.bancod.buscar(pesquisarArea.getText());
		if(user != null) {
			boolean segue = false;
			String[] options = new String[3];
			int reposta;
			options[0] = "follow";
			options[1] = "unfollow";
			options[2] = "sair";
			reposta = JOptionPane.showOptionDialog( null, user.getUsuario(), "", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null);
			if(reposta == 0){
				for(Perfil u:this.usuario.getSeguido()){
					if(u == user){
						segue = true;
						JOptionPane.showMessageDialog(null, "Usuario já seguido", "", JOptionPane.ERROR_MESSAGE);
						break;
					}
				}
				if(segue == false) {
					try {
						twitter.seguir(this.usuario.getUsuario(), user.getUsuario());
					} catch (PIException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (PDException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SIException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			if(reposta == 1){
				for(Perfil u : this.usuario.getSeguido()){
					if(u == user){
						this.usuario.getSeguido().remove(u);
						break;
					}
				}
				
			}
			timeArea.setText("");
			seguidos();
			essePerfil();
			
		}else{
			JOptionPane.showMessageDialog(null, "Usuario nao encontrado", "", JOptionPane.ERROR_MESSAGE);
		}
	   }else{
		   JOptionPane.showMessageDialog(null, "Perfil Inativo\n Ative-o para usar o perfil", "User Desativado", JOptionPane.ERROR_MESSAGE);
	   }
	}
	
	//containers

	JPanel perfilPanel(){
	
		JLabel usuariolabel = new JLabel();
		usuariolabel.setText(usuario.getUsuario());
		usuariolabel.setBounds(5, 0, 255,15);
		usuariolabel.setForeground(Color.WHITE);
		usuariolabel.setFont(new Font("Arial",Font.BOLD,15));
		
		botaoSair = new JButton();
		botaoSair.setBounds(2, 150, 100,25);
		botaoSair.setText("Deslogar");
		botaoSair.addActionListener(this);
		
		JButton botaoconfig = new JButton();
		botaoconfig.setBounds(2, 125, 100,25);
		botaoconfig.setText("Configurar");
		botaoconfig.addActionListener(e->config());
		
		
		JButton perfilBotao = new JButton();
		perfilBotao.setBounds(2, 100, 100,25);
		perfilBotao.setText("Perfil");
		perfilBotao.addActionListener(e-> {
			try {
				perfilShow();
			} catch (PIException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (PDException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		JPanel perfilcontainer = new JPanel();
		perfilcontainer.setLayout(null);
		perfilcontainer.setBounds(2, 160, 255, 180);
		perfilcontainer.add(botaoSair);
		perfilcontainer.add(perfilBotao);
		perfilcontainer.add(usuariolabel);
		perfilcontainer.add(botaoconfig);
		perfilcontainer.setBackground(new Color(39,54,76));
		return perfilcontainer;
	}
	
	JPanel procurarPanel(){
		botaoPesquisar = new JButton();
		botaoPesquisar.setBounds(75, 75, 100, 25);
		botaoPesquisar.setText("Pesquisar");
		botaoPesquisar.setFocusable(false);
		botaoPesquisar.addActionListener(e->pesquisar());
		
		pesquisarArea = new JTextField();
		pesquisarArea.setFont(new Font("Arial",Font.CENTER_BASELINE,16));
		pesquisarArea.setBounds(5, 10, 246, 50);
		
	
		
		JPanel procurarPerfil = new JPanel();
		procurarPerfil.setLayout(null);
		procurarPerfil.setBounds(2, 5, 255, 110);
		procurarPerfil.add(botaoPesquisar);
		procurarPerfil.add(pesquisarArea);
		procurarPerfil.setBackground(new Color(39,54,76));
		return procurarPerfil;
	}
	
	JPanel tweetarPanel(){
		botaoTweetar = new JButton();
		botaoTweetar.setFocusable(false);
		botaoTweetar.addActionListener(this);
		botaoTweetar.setBounds(345, 125, 85, 25);
		botaoTweetar.setText("tweetar");
		
		tweetarArea = new JTextArea();
		tweetarArea.setBounds(20, 8, 380, 100);
		tweetarArea.setFont(new Font("Arial",Font.CENTER_BASELINE,16));
		tweetarArea.setLineWrap(true);
		tweetarArea.setWrapStyleWord(true);
		tweetarArea.setDocument(new DocumentoLimitado(140));
		
		
		JPanel containerTweetar = new JPanel();
		containerTweetar.setBounds(260, 5, 420, 150);
		containerTweetar.setLayout(null);
		containerTweetar.add(botaoTweetar);
		containerTweetar.add(tweetarArea);
		containerTweetar.setBackground(new Color(64,88,124));
		
		 return containerTweetar;
	}
	
	JPanel timeLinePanel(){

		timeArea = new JTextArea();
		timeArea.setFont(new Font("Arial",Font.CENTER_BASELINE,16));
		timeArea.setLineWrap(true);
		timeArea.setFocusable(false);
		timeArea.setEditable(false);
		timeArea.setWrapStyleWord(true);
		
		JScrollPane scroll = new JScrollPane(timeArea);
		scroll.setPreferredSize(new Dimension(400,530));
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		//scroll.setPreferredSize(new Dimension(400,700));
		
		timeline = new JPanel();
		timeline.setBounds(260, 160, 420, 530);
		timeline.setBackground(new Color(64,88,124));
		timeline.add(scroll);
	
		seguidos();
		essePerfil();
		
		return timeline;
	}
	
	
	//Janela
	public janelaPrincipal(Perfil user,MyTwitter twitter){
		this.usuario = user;
		janelaPrincipal.twitter = twitter;
		
		//inicializacao dos containers
		JPanel containerTweetar = tweetarPanel();
		JPanel containerTime = timeLinePanel();
		JPanel containerPesquisar = procurarPanel();
		JPanel containerPerfil = perfilPanel();
		
		
		//criacao da janela a adicao dos containers
		framePrinc = new JFrame();
		framePrinc.setTitle(usuario.getUsuario());
		framePrinc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framePrinc.setPreferredSize(new Dimension(940,740));
		framePrinc.setResizable(false);
		framePrinc.getContentPane().setBackground(new Color(39,54,76));
		framePrinc.setLayout(null);
		framePrinc.add(containerTweetar);
		framePrinc.add(containerTime);
		framePrinc.add(containerPesquisar);
		framePrinc.add(containerPerfil);
		framePrinc.pack();
		framePrinc.setLocationRelativeTo(null);
		framePrinc.setVisible(true);
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent a) {
		if(a.getSource() == botaoSair){
			framePrinc.dispose();
			@SuppressWarnings("unused")
			janelaInicial j = new janelaInicial(twitter);
		}else if(this.usuario.isAtivo() == true){
			if(a.getSource() == botaoTweetar && !tweetarArea.getText().equals("")){
				tweetar();
			}
		
		}else{
			JOptionPane.showMessageDialog(null, "Perfil Inativo\n Ative-o para usar o perfil", "User Desativado", JOptionPane.ERROR_MESSAGE);
			
		}
	}


}
