package com.metrodora.data;

import com.metrodora.dominio.Ciclo;
import com.metrodora.dominio.Horarioasignatura;
import java.util.List;

public interface iHorarioAsignaturaDao {

    public List<Horarioasignatura> findAllHorarioAsignaturas();

    public List<Horarioasignatura> findAllByCiclo(Ciclo ciclo);

    public List<Horarioasignatura> findAll();

    public void eliminarHorariosPorCicloId(long cicloId);

    public void insertarHorarioAsignatura(Horarioasignatura horarioAsignatura);

    public List<Horarioasignatura> obtenerAsignaturasPorCiclo(int idCiclo);

}
