package br.com.sinerji.pessoaweb.dao;

import br.com.sinerji.pessoaweb.model.Endereco;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class EnderecoDAO {

    @PersistenceContext(unitName = "pessoaPU")
    private EntityManager em;

    public void salvar(Endereco endereco) {
        if (endereco.getId() == null) {
            em.persist(endereco);
        } else {
            em.merge(endereco);
        }
    }

    public void remover(Endereco endereco) {
        Endereco e = em.find(Endereco.class, endereco.getId());
        if (e != null) {
            em.remove(e);
        }
    }

    public Endereco buscarPorId(Long id) {
        return em.find(Endereco.class, id);
    }

    public List<Endereco> listarTodos() {
        return em.createQuery("SELECT e FROM Endereco e", Endereco.class).getResultList();
    }
}
