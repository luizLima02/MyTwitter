package com.luiz.classes;

public class PessoaJuridica extends Perfil {

	public PessoaJuridica(String usuario) {
		super(usuario);
	}
	private Long cnpj;
	
	public void setCnpj(Long cnpj) {this.cnpj = cnpj;}
	
	public Long getCnpj() {return cnpj;}
	

}
