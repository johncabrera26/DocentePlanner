package com.metrodora.data;

import com.metrodora.dominio.Aula;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AulaDao implements iAulaDao {

    @PersistenceContext(unitName = "MetrodoraPU")
    EntityManager em;

    @Override
    public List<Aula> findAllAulas() {

        return em.createNamedQuery("Aula.findAll").getResultList();

    }

}
