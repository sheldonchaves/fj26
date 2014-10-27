package br.com.caelum.notasfiscais.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;

import br.com.caelum.notasfiscais.mb.UsuarioLogadoBean;

public class Autorizador implements PhaseListener {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioLogadoBean usuarioLogado;
	
	@Inject
	private FacesContext context;
	
	@Inject
	private NavigationHandler handler;
	
	@Override
	public void afterPhase(PhaseEvent event) {
		System.out.println("Inicio Autorizador.... > " + context.getViewRoot().getViewId());
		if("/login.xhtml".equals(context.getViewRoot().getViewId())) {
			System.out.println("Chamada para tela de login.");
			return;
		}
			
		//Verificacao do autorizador
		if(! usuarioLogado.isLogado()){
			System.out.println("O usuario nao esta logado.");
			handler.handleNavigation(context, null, "login?faces-redirect=true");
			//Renderiza a tela
			System.out.println("Prepara renderizacao da tela.");
			context.renderResponse();
		}else{
			System.out.println("O usuario esta logado.");
		}
		
		return;
		
	}

	@Override
	public void beforePhase(PhaseEvent event) {

	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}