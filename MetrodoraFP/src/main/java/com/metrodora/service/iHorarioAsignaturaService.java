package com.metrodora.service;

import com.metrodora.dominio.Horarioasignatura;
import java.util.List;

public interface iHorarioAsignaturaService {

    public List<Horarioasignatura> listarHorarioAsignaturas();

    public List<Horarioasignatura> findAll();

    public void insertarHorarioAsignatura(Horarioasignatura horarioAsignatura);

    public void eliminarHorariosPorCicloId(long cicloId);

    public List<Horarioasignatura> obtenerAsignaturasPorCiclo(int idCiclo);
}
