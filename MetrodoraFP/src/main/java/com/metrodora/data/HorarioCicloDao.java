package com.metrodora.data;

import com.metrodora.dominio.Horariociclo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class HorarioCicloDao implements iHorarioCicloDao {

    @PersistenceContext(unitName = "MetrodoraPU")
    EntityManager em;

    @Override
    public List<Horariociclo> findAllHorarioCiclo() {

        return em.createNamedQuery("Horariociclo.findAll").getResultList();

    }

}
