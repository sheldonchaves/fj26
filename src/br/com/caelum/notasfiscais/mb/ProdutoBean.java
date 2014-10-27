package br.com.caelum.notasfiscais.mb;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.ProdutoDao;
import br.com.caelum.notasfiscais.modelo.Produto;

@Named
@ViewScoped
public class ProdutoBean {
	private Produto produto = new Produto();
	private List<Produto> produtos;

	@Inject
	private ProdutoDao dao;

	private double total;

	/*private DataTable dataTable;*/
	
	public void grava() {
		
		if (produto.getId() == null) {
			this.dao.adiciona(produto);
		} else {
			this.dao.atualiza(produto);
		}

		limpaForm();
		updateListaProduto();
	}

	public void remove(Produto produto) {
		this.dao.remove(produto);
		updateListaProduto();
	}

	public void cancela() {
		limpaForm();
		updateListaProduto();
	}

	private void limpaForm() {
		this.produto = new Produto();
	}

	private void updateListaProduto() {
		this.produtos = this.dao.listaTodos();
	}

	public List<Produto> getProdutos() {
		if (this.produtos == null) {
			this.produtos = this.dao.listaTodos();
		}
		return this.produtos;
	}

	public Produto getProduto() {
		return this.produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public double getTotal() {
		total = 0;
		if (this.produtos != null) {
			for (Produto p : produtos) {
				total += p.getPreco();
			}
		}
		return total;
	}

	/*public DataTable getDataTable() {
		return dataTable;
	}
	public void setDataTable(DataTable dataTable) {
		this.dataTable = dataTable;
	}*/


}
