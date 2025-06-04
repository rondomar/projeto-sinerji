package br.com.sinerji.pessoaweb.dao;

import br.com.sinerji.pessoaweb.model.Pessoa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.persistence.EntityManager;

import static org.mockito.Mockito.*;

import java.lang.reflect.Field;

class PessoaDAOTest {

    private PessoaDAO pessoaDAO;
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
        pessoaDAO = new PessoaDAO();
        entityManager = Mockito.mock(EntityManager.class);

        try {
        	Field field = PessoaDAO.class.getDeclaredField("entityManager");
            field.setAccessible(true);
            field.set(pessoaDAO, entityManager);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void deveSalvarNovaPessoa() {
        Pessoa nova = new Pessoa();
        pessoaDAO.salvar(nova);
        verify(entityManager, times(1)).persist(nova);
    }

    @Test
    void deveAtualizarPessoaExistente() {
        Pessoa existente = new Pessoa();
        existente.setId(1L);
        pessoaDAO.salvar(existente);
        verify(entityManager, times(1)).merge(existente);
    }

    @Test
    void deveRemoverPessoa() {
        Pessoa p = new Pessoa();
        Pessoa pManaged = new Pessoa();

        when(entityManager.merge(p)).thenReturn(pManaged);
        pessoaDAO.remover(p);
        verify(entityManager).remove(pManaged);
    }
}
