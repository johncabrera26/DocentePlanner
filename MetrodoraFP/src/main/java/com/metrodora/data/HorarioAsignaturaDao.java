package com.metrodora.data;

import com.metrodora.dominio.Ciclo;
import com.metrodora.dominio.Horarioasignatura;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class HorarioAsignaturaDao implements iHorarioAsignaturaDao {

    @PersistenceContext(unitName = "MetrodoraPU")
    EntityManager em;

    @Override
    public List<Horarioasignatura> findAllHorarioAsignaturas() {

        return em.createNamedQuery("Horarioasignatura.findAll").getResultList();

    }

    @Override
    public List<Horarioasignatura> findAllByCiclo(Ciclo ciclo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Horarioasignatura> findAll() {

        TypedQuery<Horarioasignatura> query = em.createQuery("SELECT h FROM Horarioasignatura h", Horarioasignatura.class);
        return query.getResultList();
    }

    @Override
    public void insertarHorarioAsignatura(Horarioasignatura horarioAsignatura) {

        em.persist(horarioAsignatura);

    }

    @Override
    public void eliminarHorariosPorCicloId(long cicloId) {

        Query query = em.createQuery("DELETE FROM Horarioasignatura h WHERE h.iDCiclo.iDCiclo = :cicloId");
        query.setParameter("cicloId", cicloId);
        query.executeUpdate();
    }

    @Override
    public List<Horarioasignatura> obtenerAsignaturasPorCiclo(int idCiclo) {
        TypedQuery<Horarioasignatura> query = em.createQuery("SELECT ha FROM Horarioasignatura ha WHERE ha.iDCiclo.iDCiclo = :idCiclo", Horarioasignatura.class);
        query.setParameter("idCiclo", idCiclo);
        return query.getResultList();
    }

}
