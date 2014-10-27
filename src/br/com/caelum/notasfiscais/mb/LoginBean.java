package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.UsuarioDao;
import br.com.caelum.notasfiscais.modelo.Usuario;


@Named
@RequestScoped
public class LoginBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Usuario usuario = new Usuario();
	
	@Inject
	private UsuarioDao dao;

	@Inject
	private UsuarioLogadoBean usuarioLogado;
	
	@Inject
	private Event<Usuario> eventoLogin;
	
	public String efetuaLogin() {
		boolean loginValido = dao.existe(this.usuario);
		
		if (loginValido){
			usuarioLogado.logar(usuario);
			
			eventoLogin.fire(usuario);
			
			return "index?faces-redirect=true";
		} else {
			usuarioLogado.deslogar();
			this.setMessage(FacesMessage.SEVERITY_ERROR, "Error", "Erro de Login");
			this.usuario = new Usuario();
			return "login";
		}
	}
	
	public String efetuaLogout() {
		this.usuarioLogado.deslogar();
		this.usuario = new Usuario();
		return "login";
	}
	
	private void setMessage(Severity severity, String key, String msg){
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.setKeepMessages(true);
		FacesMessage facesMessage = new FacesMessage(severity, msg + "1", msg + "2");
		FacesContext.getCurrentInstance().addMessage(key, facesMessage);
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}
}
