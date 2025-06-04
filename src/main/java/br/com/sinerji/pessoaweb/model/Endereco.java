package br.com.sinerji.pessoaweb.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "endereco")
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 2, nullable = false)
    private String estado;

    @Column(length = 100)
    private String cidade;

    @Column(length = 100)
    private String logradouro;

    private int numero;

    @Column(length = 8)
    private String cep;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
