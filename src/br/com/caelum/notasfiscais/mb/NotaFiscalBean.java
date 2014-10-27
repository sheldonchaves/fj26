package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.NotaFiscalDao;
import br.com.caelum.notasfiscais.dao.ProdutoDao;
import br.com.caelum.notasfiscais.modelo.Item;
import br.com.caelum.notasfiscais.modelo.NotaFiscal;
import br.com.caelum.notasfiscais.modelo.Produto;
import br.com.caelum.notasfiscais.util.ViewModel;

@ViewModel
public class NotaFiscalBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private NotaFiscal notaFiscal = new NotaFiscal();
	
	private List<NotaFiscal> notas;
	
	private boolean itemVazio;

	@Inject
	private NotaFiscalDao dao;

	private Item item = new Item();
	private Long idProduto;
	
	@Inject
	private ProdutoDao produtoDao;
	
	public void grava() {

		if (getNotaFiscal().getId() == null) {
			this.dao.adiciona(getNotaFiscal());
		}

		limpaForm();
		//updateListaNotas();
	}
	
	public void guardaItem() {
		Produto produto = produtoDao.buscaPorId(idProduto);
		item.setProduto(produto);
		
		item.setValorUnitario(produto.getPreco());
		notaFiscal.getItens().add(item);
		item.setNotaFiscal(notaFiscal);
		
		item = new Item();
	}
	
	public boolean isItemVazio(){
		this.itemVazio = this.notaFiscal.getItens().size()!=0;
		return itemVazio;
	}

	/*public void remove(NotaFiscal notaFiscal) {
		this.dao.remove(notaFiscal);
		updateListaNotas();
	}*/

	public void cancela() {
		/*limpaForm();
		updateListaNotas();*/
	}

	private void limpaForm() {
		this.notaFiscal = new NotaFiscal();
	}

	private void updateListaNotas() {
		this.notas = this.dao.listaTodos();
	}

	/*public List<NotaFiscal> getNotas() {
		if (this.notas == null) {
			updateListaNotas();
		}
		return this.notas;
	}*/

	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public void setItemVazio(boolean itemVazio) {
		this.itemVazio = itemVazio;
	}
}
