package br.com.caelum.ingresso.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.ingresso.model.Sessao;

@Repository
public class SessaoDao {
	
    @PersistenceContext
    private EntityManager manager;

    public Sessao findOne(Integer id) {

        return manager.find(Sessao.class, id);
    }

    public void save(Sessao sessao) {
        manager.merge(sessao);
    }

    

}
