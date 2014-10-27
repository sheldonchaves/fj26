package br.com.caelum.notasfiscais.mb;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.UsuarioDao;
import br.com.caelum.notasfiscais.modelo.Usuario;

@Named
@ViewScoped
public class UsuarioBean {
	private Usuario usuario = new Usuario();
	private List<Usuario> usuarios;

	@Inject
	private UsuarioDao dao;

	public void grava() {
		
		if (usuario.getId() == null) {
			this.dao.adiciona(usuario);
		} else {
			this.dao.atualiza(usuario);
		}

		limpaForm();
		updateListaUsuario();
	}

	public void remove(Usuario usuario) {
		this.dao.remove(usuario);
		updateListaUsuario();
	}

	public void cancela() {
		limpaForm();
		updateListaUsuario();
	}

	private void limpaForm() {
		this.usuario = new Usuario();
	}

	private void updateListaUsuario() {
		this.usuarios = this.dao.listaTodos();
	}

	public List<Usuario> getUsuarios() {
		if (this.usuarios == null) {
			updateListaUsuario();
		}
		return this.usuarios;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
