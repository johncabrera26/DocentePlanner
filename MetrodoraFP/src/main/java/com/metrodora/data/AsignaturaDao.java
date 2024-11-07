package com.metrodora.data;

import com.metrodora.dominio.Asignatura;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Stateless
public class AsignaturaDao implements iAsignaturaDao {

    @PersistenceContext(unitName = "MetrodoraPU")
    EntityManager em;

    @Override
    public List<Asignatura> findAllAsignaturas() {

        return em.createNamedQuery("Asignatura.findAll").getResultList();
    }

    @Override
    public List<String> obtenerNombresAsignaturas(String[] idAsignaturas) {
        List<String> nombresAsignaturas = new ArrayList<>();
        for (String idAsignatura : idAsignaturas) {
            try {
                Integer id = Integer.parseInt(idAsignatura);
                Asignatura asignatura = em.find(Asignatura.class, id);
                if (asignatura != null) {
                    nombresAsignaturas.add(asignatura.getNombre());
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return nombresAsignaturas;
    }

    @Override
    public void insertarAsignatura(Asignatura asignatura) {
        em.persist(asignatura);
    }

    @Override
    public List<Asignatura> obtenerAsignaturasPorCicloId(int cicloId) {

        return em.createQuery("SELECT ha.iDAsignatura FROM Horarioasignatura ha WHERE ha.iDCiclo.iDCiclo = :cicloId", Asignatura.class)
                .setParameter("cicloId", cicloId)
                .getResultList();
    }

    @Override
    @Transactional
    public void eliminarAsignaturaPorId(int asignaturaId) {
        Asignatura asignatura = em.find(Asignatura.class, asignaturaId);
        if (asignatura != null) {
            em.remove(asignatura);
        }
    }

    @Override
    public void eliminarAsignaturasPorCicloId(int cicloId) {
        em.createQuery("DELETE FROM Asignatura a WHERE a IN (SELECT ha.iDAsignatura FROM Horarioasignatura ha WHERE ha.iDCiclo.iDCiclo = :cicloId)")
                .setParameter("cicloId", cicloId)
                .executeUpdate();
    }

    @Override
    public String obtenerNombreAsignaturaPorId(int idAsignatura) {
        Asignatura asignatura = em.find(Asignatura.class, idAsignatura);
        return (asignatura != null) ? asignatura.getNombre() : null;
    }

    @Override
    public Asignatura findById(int id) {
        return em.find(Asignatura.class, id);
    }

}
