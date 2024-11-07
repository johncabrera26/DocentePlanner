package com.metrodora.data;

import com.metrodora.dominio.Asignatura;
import com.metrodora.dominio.Profesor;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Stateless
public class ProfesorDao implements iProfesorDao {

    @PersistenceContext(unitName = "MetrodoraPU")
    EntityManager em;

    @Override
    public List<Profesor> findAllProfesores() {

        return em.createNamedQuery("Profesor.findAll").getResultList();
    }

    @Override
    public void insertarProfesor(Profesor profesor) {
        em.persist(profesor);
    }

    @Override
    public int obtenerUltimoIDInsertado() {
        Integer id = (Integer) em.createQuery("SELECT MAX(p.iDProfesor) FROM Profesor p").getSingleResult();
        return id != null ? id.intValue() : -1;
    }

    @Override
    @Transactional
    public void eliminarProfesorPorId(int profesorId) {
        Profesor profesor = em.find(Profesor.class, profesorId);
        if (profesor != null) {
            em.remove(profesor);
        }

    }

    @Override
    public String obtenerNombreProfesorPorId(int idProfesor) {
        Profesor profesor = em.find(Profesor.class, idProfesor);
        return (profesor != null) ? profesor.getNombre() : null;
    }

    @Override
    public List<Profesor> obtenerProfesoresPorAsignatura(int idAsignatura) {
        TypedQuery<Profesor> query = em.createQuery("SELECT hp.iDProfesor FROM Horarioprofesor hp WHERE hp.iDAsignatura.iDAsignatura = :idAsignatura", Profesor.class);
        query.setParameter("idAsignatura", idAsignatura);
        return query.getResultList();
    }

    @Override
    public List<Asignatura> obtenerAsignaturasPorProfesor(int idProfesor) {
        TypedQuery<Asignatura> query = em.createQuery(
                "SELECT hp.iDAsignatura FROM Horarioprofesor hp WHERE hp.iDProfesor.iDProfesor = :idProfesor", Asignatura.class
        );
        query.setParameter("idProfesor", idProfesor);
        return query.getResultList();
    }

    @Override
    public String obtenerNombreCompletoProfesorPorId(int idProfesor) {
        Profesor profesor = em.find(Profesor.class, idProfesor);
        if (profesor != null) {
            String nombreCompleto = profesor.getNombre() + " " + profesor.getApellido();
            return nombreCompleto;
        }
        return null;
    }

    @Override
    public Profesor findById(int id) {
        TypedQuery<Profesor> query = em.createNamedQuery("Profesor.findByIDProfesor", Profesor.class);
        query.setParameter("iDProfesor", id);
        return query.getSingleResult();
    }

    @Override
    public List<Asignatura> obtenerAsignaturasPorProfesorId(int profesorId) {
        TypedQuery<Asignatura> query = em.createQuery(
                "SELECT hp.asignatura FROM HorarioProfesor hp WHERE hp.profesor.id = :profesorId", Asignatura.class);
        query.setParameter("profesorId", profesorId);
        List<Asignatura> asignaturas = query.getResultList();

        return asignaturas;
    }
}
