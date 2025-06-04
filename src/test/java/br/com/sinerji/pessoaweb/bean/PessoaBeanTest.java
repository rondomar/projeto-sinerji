package br.com.sinerji.pessoaweb.bean;

import br.com.sinerji.pessoaweb.dao.PessoaDAO;
import br.com.sinerji.pessoaweb.model.Endereco;
import br.com.sinerji.pessoaweb.model.Pessoa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PessoaBeanTest {

    private PessoaBean bean;
    private PessoaDAO dao;

    @BeforeEach
    void setUp() {
        bean = new PessoaBean();
        dao = mock(PessoaDAO.class);
        bean.setPessoaDAO(dao);

        bean.init();
    }

    @Test
    void deveAdicionarEnderecoAPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setEnderecos(new ArrayList<>());
        bean.setPessoa(pessoa);

        bean.getNovoEndereco().setCidade("São Paulo");
        bean.adicionarEndereco();

        assertEquals(1, pessoa.getEnderecos().size());
        assertEquals("São Paulo", pessoa.getEnderecos().get(0).getCidade());
    }

    @Test
    void deveRemoverEndereco() {
        Endereco e = new Endereco();
        Pessoa pessoa = new Pessoa();
        pessoa.setEnderecos(new ArrayList<>(java.util.Arrays.asList(e)));


        bean.setPessoa(pessoa);
        bean.removerEndereco(e);

        assertTrue(pessoa.getEnderecos().isEmpty());
    }

    @Test
    void deveEditarPessoa() {
        Pessoa p = new Pessoa();
        bean.editar(p);
        assertEquals(p, bean.getPessoa());
    }
}
