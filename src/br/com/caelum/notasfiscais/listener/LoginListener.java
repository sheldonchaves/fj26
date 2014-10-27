package br.com.caelum.notasfiscais.listener;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import br.com.caelum.notasfiscais.modelo.Usuario;
import br.com.caelum.notasfiscais.util.EmailComercial;

public class LoginListener {

	@Inject @EmailComercial
	private String email;
	
	public void onLogin (@Observes Usuario usuario){
		System.out.printf(">> Listener >> Usuario %s se logou no sistema no email: " + email,usuario.getLogin() );
		// enviar por email login
	}
	
}
