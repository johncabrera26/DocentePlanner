package com.metrodora.data;

import com.metrodora.dominio.Asignatura;
import com.metrodora.dominio.Horarioprofesor;
import com.metrodora.dominio.Profesor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Stateless
public class HorarioProfesorDao implements iHorarioProfesorDao {

    public HorarioProfesorDao() {
    }

    public HorarioProfesorDao(EntityManager entityManager) {
    }

    @PersistenceContext(unitName = "MetrodoraPU")
    EntityManager em;

    @Override
    public List<Horarioprofesor> findAllHorarioProfesores() {

        return em.createNamedQuery("Horarioprofesor.findAll").getResultList();

    }

    @Override
    public void insertarHorarioProfesor(int idProfesor, int idAsignatura) {
        Profesor profesor = em.find(Profesor.class, idProfesor);
        Asignatura asignatura = em.find(Asignatura.class, idAsignatura);

        if (profesor != null && asignatura != null) {
            Horarioprofesor horarioProfesor = new Horarioprofesor();
            horarioProfesor.setIDProfesor(profesor);
            horarioProfesor.setIDAsignatura(asignatura);
            em.persist(horarioProfesor);
        }

    }

    @Override
    @Transactional

    public void eliminarHorariosPorProfesorId(int profesorId) {
        Query query = em.createQuery("DELETE FROM Horarioprofesor h WHERE h.iDProfesor.iDProfesor = :profesorId")
                .setParameter("profesorId", profesorId);
        query.executeUpdate();
    }

    @Override
    public String obtenerNombreProfesorPorId(int idProfesor) {
        Profesor profesor = em.find(Profesor.class, idProfesor);
        return (profesor != null) ? profesor.getNombre() : null;
    }

    @Override
    public String obtenerNombreAsignaturaPorId(int idAsignatura) {
        Asignatura asignatura = em.find(Asignatura.class, idAsignatura);
        return (asignatura != null) ? asignatura.getNombre() : null;
    }

    @Override
    public List<Profesor> obtenerProfesoresPorAsignatura(int idAsignatura) {
        TypedQuery<Profesor> query = em.createQuery(
                "SELECT hp.iDProfesor FROM Horarioprofesor hp WHERE hp.iDAsignatura.iDAsignatura = :idAsignatura",
                Profesor.class
        );
        query.setParameter("idAsignatura", idAsignatura);
        return query.getResultList();
    }

    @Override
    public List<Horarioprofesor> obtenerHorariosPorProfesorYDia(int idProfesor, String dia) {
        String jpql = "SELECT hp FROM Horarioprofesor hp WHERE hp.iDProfesor.iDProfesor = :idProfesor AND hp.diaSemana = :dia";
        Query query = em.createQuery(jpql, Horarioprofesor.class);
        query.setParameter("idProfesor", idProfesor);
        query.setParameter("dia", dia);
        return query.getResultList();
    }

    public Map<Profesor, List<Asignatura>> obtenerProfesorAsignaturasMap() {
        Map<Profesor, List<Asignatura>> profesorAsignaturasMap = new HashMap<>();

        EntityManager entityManager = getEntityManager();

        try {
            // Consulta para obtener todos los profesores
            List<Profesor> profesores = entityManager.createQuery("SELECT p FROM Profesor p", Profesor.class).getResultList();

            // Iterar sobre cada profesor para obtener sus asignaturas
            for (Profesor profesor : profesores) {
                // Consulta para obtener todas las asignaturas asociadas a este profesor
                List<Asignatura> asignaturas = entityManager.createQuery(
                        "SELECT hp.iDAsignatura FROM Horarioprofesor hp WHERE hp.iDProfesor = :profesor", Asignatura.class)
                        .setParameter("profesor", profesor)
                        .getResultList();

                profesorAsignaturasMap.put(profesor, asignaturas);
            }
        } finally {
            entityManager.close();
        }

        return profesorAsignaturasMap;
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MetrodoraPU");
        return entityManagerFactory.createEntityManager();
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
    public Horarioprofesor findById(int idHorario) {

        return em.find(Horarioprofesor.class, idHorario);
    }

    @Override
    public void actualizarHorario(Horarioprofesor horarioprofesor) {
        em.merge(horarioprofesor);
    }

    @Override
    public void guardarHorario(Horarioprofesor horarioprofesor) {

        em.persist(horarioprofesor);
    }

    @Override
    public void eliminarHorarioPorId(int horarioId) {
        Horarioprofesor horario = em.find(Horarioprofesor.class, horarioId);
        if (horario != null) {
            em.remove(horario);
        }

    }
}
