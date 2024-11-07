package com.metrodora.data;

import com.metrodora.dominio.Asignatura;
import java.util.List;

public interface iAsignaturaDao {

    public List<Asignatura> findAllAsignaturas();

    public List<String> obtenerNombresAsignaturas(String[] idAsignaturas);

    public void insertarAsignatura(Asignatura asignatura);

    public List<Asignatura> obtenerAsignaturasPorCicloId(int cicloId);

    public void eliminarAsignaturaPorId(int asignaturaId);

    public void eliminarAsignaturasPorCicloId(int cicloId);

    public String obtenerNombreAsignaturaPorId(int idAsignatura);

    public Asignatura findById(int id);

}
