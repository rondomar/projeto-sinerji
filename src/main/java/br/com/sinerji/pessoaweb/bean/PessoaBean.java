package br.com.sinerji.pessoaweb.bean;



import javax.annotation.PostConstruct;
import javax.inject.Named;

import br.com.sinerji.pessoaweb.dao.PessoaDAO;
import br.com.sinerji.pessoaweb.model.Endereco;
import br.com.sinerji.pessoaweb.model.Pessoa;

import javax.inject.Inject;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import java.io.Serializable;
import java.util.List;

@Named("pessoaBean")
@ViewScoped
public class PessoaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Pessoa pessoa = new Pessoa();
    private List<Pessoa> pessoas;
    private Endereco novoEndereco = new Endereco();

    @Inject
    private PessoaDAO pessoaDAO;

    @PostConstruct
    public void init() {
        pessoas = pessoaDAO.listarTodas();
    }

    public void salvar() {
        pessoaDAO.salvar(pessoa);
        pessoa = new Pessoa();
        pessoas = pessoaDAO.listarTodas();
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Pessoa salva com sucesso!", null));
    }

    public void editar(Pessoa pessoaSelecionada) {
        this.pessoa = pessoaSelecionada;
    }

    public void remover(Pessoa pessoaSelecionada) {
        pessoaDAO.remover(pessoaSelecionada);
        pessoas = pessoaDAO.listarTodas();
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Pessoa removida com sucesso!", null));
    }
    
    public void adicionarEndereco() {
        novoEndereco.setPessoa(pessoa); // vincula à pessoa
        pessoa.getEnderecos().add(novoEndereco);
        novoEndereco = new Endereco(); // limpa para novo cadastro
    }
    
    public void removerEndereco(Endereco endereco) {
        if (this.pessoa != null && this.pessoa.getEnderecos() != null) {
            this.pessoa.getEnderecos().remove(endereco);
        }
    }
    
    public void setPessoaDAO(PessoaDAO pessoaDAO) {
        this.pessoaDAO = pessoaDAO;
    }


    // Getters e Setters
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }
    
    // Adicione no final da classe PessoaBean
    public Endereco getNovoEndereco() {
        return novoEndereco;
    }

    public void setNovoEndereco(Endereco novoEndereco) {
        this.novoEndereco = novoEndereco;
    }

}
