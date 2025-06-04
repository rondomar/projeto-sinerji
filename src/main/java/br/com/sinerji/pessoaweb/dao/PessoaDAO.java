package br.com.sinerji.pessoaweb.dao;

import br.com.sinerji.pessoaweb.model.Pessoa;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class PessoaDAO {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Salva ou atualiza a pessoa no banco de dados.
     * 
     * @param pessoa a pessoa a ser salva ou atualizada
     */
    @Transactional
    public void salvar(Pessoa pessoa) {
        if (pessoa.getId() == null) {
            entityManager.persist(pessoa);
        } else {
            entityManager.merge(pessoa);
        }
    }

    /**
     * Remove a pessoa do banco de dados.
     * 
     * @param pessoa a pessoa a ser removida
     */
    @Transactional
    public void remover(Pessoa pessoa) {
        Pessoa pessoaGerenciada = entityManager.merge(pessoa);
        entityManager.remove(pessoaGerenciada);
    }

    /**
     * Lista todas as pessoas no banco de dados.
     * 
     * @return uma lista de todas as pessoas
     */
    public List<Pessoa> listarTodas() {
        return entityManager.createQuery("FROM Pessoa", Pessoa.class).getResultList();
    }

    /**
     * Busca uma pessoa pelo ID.
     * 
     * @param id o ID da pessoa
     * @return a pessoa encontrada, ou null se não existir
     */
    public Pessoa buscarPorId(Long id) {
        return entityManager.find(Pessoa.class, id);
    }
}
