package com.luiz.classes;

public class PessoaFisica extends Perfil {

	public PessoaFisica(String usuario) {
		super(usuario);
	}
	private Long cpf;
	
	public void setCPF(long cpf){ this.cpf = cpf;}
	
	public Long getCPF(){ return cpf;}

}
