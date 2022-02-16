package com.luiz.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.luiz.classes.MyTwitter;
import com.luiz.classes.PessoaFisica;
import com.luiz.classes.PessoaJuridica;
import com.luiz.exceptions.UJCException;

public class janelaCadastro implements ActionListener {

	JFrame framecad;
	static JButton cadastrar;
	static JButton cancelar;
	static JTextField usuariofield;
	static JTextField cpfField;
	static JRadioButton pessoaFisica;
	static JRadioButton pessoaJuridica;
	static MyTwitter twitter;
	
	void CadastrarF(){
		if(usuariofield.getText().isBlank() == false) {
			PessoaFisica p = new PessoaFisica(usuariofield.getText());
			long cpf;
			try {
				cpf = Long.parseLong(cpfField.getText());
				p.setCPF(cpf);
				try {
					twitter.criarPerfil(p);
					p.setAtivo(true);
					JOptionPane.showMessageDialog(null, "Usuario Cadastrado", "", JOptionPane.INFORMATION_MESSAGE);
					framecad.dispose();
					@SuppressWarnings("unused")
					janelaInicial j = new janelaInicial(twitter);
				} catch (UJCException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage() + " at user "+ e1.getUsuario(), "UCJException", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				
			}catch(NumberFormatException nfe){
				JOptionPane.showMessageDialog(null, nfe.getMessage(), "NumberFormatException", JOptionPane.ERROR_MESSAGE);
				
			}
			
		}else{
			JOptionPane.showMessageDialog(null, "Preencha o campo Usuario", "", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	void CadastrarJ(){
		if(usuariofield.getText().isBlank() == false) {
			PessoaJuridica p = new PessoaJuridica(usuariofield.getText());
			long cnpj;
			try {
				cnpj = Long.parseLong(cpfField.getText());
				p.setCnpj(cnpj);
				try {
					twitter.criarPerfil(p);
					p.setAtivo(true);
					JOptionPane.showMessageDialog(null, "Usuario Cadastrado", "", JOptionPane.INFORMATION_MESSAGE);
					framecad.dispose();
					@SuppressWarnings("unused")
					janelaInicial j = new janelaInicial(twitter);
				 }catch (UJCException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage() + " at user "+ e1.getUsuario(), "UCJException", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				 }
			}catch(NumberFormatException nfe){
				JOptionPane.showMessageDialog(null, nfe.getMessage(), "NumberFormatException", JOptionPane.ERROR_MESSAGE);
			}
		}else{
			JOptionPane.showMessageDialog(null, "Preencha o campo Usuario", "", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	JPanel painelCadastro(){
		//botoes cadastrar e camcelar
		cadastrar = new JButton(); // realiza as funcoes de cadastro
		cadastrar.setBounds(60, 425, 100, 50);
		cadastrar.setText("Cadastrar");
		cadastrar.setFocusable(false);
		cadastrar.addActionListener(this);
		
		cancelar = new JButton();// volta para a tela inicial
		cancelar.setBounds(220, 425, 100, 50);
		cancelar.setText("cancelar");
		cancelar.setFocusable(false);
		cancelar.addActionListener(this);
		
		//cabecalho da pagina
		JLabel titulo = new JLabel();
		titulo.setText("Cadastro");
		titulo.setBounds(150, 0, 200, 30);
		titulo.setFont(new Font("Arial",Font.BOLD,20));
		
		//Campo usuario
		JLabel usuarioLabel = new JLabel();
		usuarioLabel.setText("Usuario:");
		usuarioLabel.setBounds(50, 80, 200, 30);
		usuariofield = new JTextField();
		usuariofield.setBounds(150, 80, 200, 30);
		
		// campo cpf / cpnj
		JLabel cpfLabel = new JLabel();
		cpfLabel.setText("CPF / CNPJ:");
		cpfLabel.setBounds(50, 160, 200, 30);
		cpfField = new JTextField();
		cpfField.setBounds(150, 160, 200, 30);
		
		//botoes pessoa fisica / juridica
		JLabel cpf = new JLabel();
		cpf.setText("Pessoa Fisica");
		cpf.setBounds(70, 245, 200, 30);
		cpf.setFont(new Font("Arial",Font.BOLD,10));
		pessoaFisica = new JRadioButton();
		pessoaFisica.setBounds(50, 250, 20, 20);
		
		JLabel cnpj = new JLabel();
		cnpj.setText("Pessoa Juridica");
		cnpj.setBounds(70, 285, 200, 30);
		cnpj.setFont(new Font("Arial",Font.BOLD,10));
		pessoaJuridica = new JRadioButton();
		pessoaJuridica.setBounds(50, 290, 20, 20);
		
		ButtonGroup grupo = new ButtonGroup();
		grupo.add(pessoaFisica);
		grupo.add(pessoaJuridica);
		
		JPanel tcadast = new JPanel();
		tcadast.setBounds(210, 120, 380,500);
		tcadast.setLayout(null);
		tcadast.add(cadastrar);
		tcadast.add(cancelar);
		tcadast.add(usuariofield);
		tcadast.add(usuarioLabel);
		tcadast.add(titulo);
		tcadast.add(pessoaFisica);
		tcadast.add(pessoaJuridica);
		tcadast.add(cpf);
		tcadast.add(cnpj);
		tcadast.add(cpfLabel);
		tcadast.add(cpfField);
		tcadast.setVisible(true);
		return tcadast;
	}
	
	
	janelaCadastro(MyTwitter twitter){
		janelaCadastro.twitter = twitter;
		// painel principal
		JPanel cadast = painelCadastro();
	
		// janela
		framecad = new JFrame();
		framecad.setTitle("My twitter");
		framecad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framecad.setPreferredSize(new Dimension(840,840));
		framecad.setResizable(false);
		framecad.getContentPane().setBackground(new Color(64,88,124));
		framecad.setLayout(null);
		framecad.add(cadast);
		framecad.pack();
		framecad.setLocationRelativeTo(null);
		framecad.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cancelar){
			framecad.dispose();
			@SuppressWarnings("unused")
			janelaInicial j = new janelaInicial(twitter);
		}
		
		if(pessoaFisica.isSelected()){
			if(e.getSource() == cadastrar){
				CadastrarF();
			}
		}else if(pessoaJuridica.isSelected()){
			if(e.getSource() == cadastrar){
				CadastrarJ();
			}
		}
		
	}
	
	
}
