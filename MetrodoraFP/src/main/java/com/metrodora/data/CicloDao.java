package com.metrodora.data;

import com.metrodora.dominio.Asignatura;
import com.metrodora.dominio.Ciclo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class CicloDao implements iCicloDao {

    @PersistenceContext(unitName = "MetrodoraPU")
    EntityManager em;

    @Override
    public List<Ciclo> findAllCiclos() {

        return em.createNamedQuery("Ciclo.findAll").getResultList();
    }

    @Override
    public String ciloByID(String idCiclo) {
        try {
            Integer id = Integer.parseInt(idCiclo);
            Query query = em.createNamedQuery("Ciclo.findByIDCiclo");
            query.setParameter("iDCiclo", id); // Debes usar el nombre correcto del parámetro en la consulta
            Ciclo ciclo = (Ciclo) query.getSingleResult();
            return ciclo.getNombre();
        } catch (NumberFormatException | NoResultException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void insertarCicloConAsignaturas(Ciclo ciclo, List<Asignatura> asignaturas) {
        try {
            // Iniciar transacción
            em.getTransaction().begin();

            // Insertar ciclo
            em.persist(ciclo);

            // Obtener el ID del ciclo recién insertado
            int idCiclo = ciclo.getIDCiclo();

            // Insertar asignaturas en la tabla horarioasignatura
            for (Asignatura asignatura : asignaturas) {
                em.createNativeQuery("INSERT INTO horarioasignatura (ID_Ciclo, ID_Asignatura) VALUES (?, ?)")
                        .setParameter(1, idCiclo)
                        .setParameter(2, asignatura.getIDAsignatura())
                        .executeUpdate();
            }

            // Confirmar transacción
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new RuntimeException("Error al insertar el ciclo con sus asignaturas", ex);
        }
    }

    @Override
    public void InsertarCiclo(Ciclo ciclo) {

        em.persist(ciclo);
    }

    @Override
    public void eliminarCicloPorId(int cicloId) {
        em.createQuery("DELETE FROM Asignatura a WHERE a IN (SELECT ha.iDAsignatura FROM Horarioasignatura ha WHERE ha.iDCiclo.iDCiclo = :cicloId)")
                .setParameter("cicloId", cicloId)
                .executeUpdate();

        Ciclo ciclo = em.find(Ciclo.class, cicloId);
        if (ciclo != null) {
            em.remove(ciclo);
        }

    }

    @Override
    public String obtenerNombreCicloPorId(int idCiclo) {
        try {
            return em.createQuery("SELECT c.nombre FROM Ciclo c WHERE c.id = :idCiclo", String.class)
                    .setParameter("idCiclo", idCiclo)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
