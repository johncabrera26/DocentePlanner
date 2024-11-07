
package com.metrodora.service;

import com.metrodora.dominio.Horarioprofesor;
import com.metrodora.dominio.Profesor;
import java.util.List;

public interface iHorarioProfesorService {

    public List<Horarioprofesor> listarHorarioProfesores();

    public void insertarHorarioProfesor(int idProfesor, int idAsignatura);

    public void eliminarHorariosPorProfesorId(int profesorId);

    public String obtenerNombreProfesorPorId(int idProfesor);

    public String obtenerNombreAsignaturaPorId(int idAsignatura);

    public List<Profesor> obtenerProfesoresPorAsignatura(int idAsignatura);

    public List<Horarioprofesor> obtenerHorariosPorProfesorYDia(int idProfesor, String dia);

    public String obtenerNombreCompletoProfesorPorId(int idProfesor);

    public Horarioprofesor findById(int idHorario);

    public void actualizarHorario(Horarioprofesor horarioprofesor);

    public void guardarHorario(Horarioprofesor horarioprofesor);

    public void eliminarHorarioPorId(int horarioId);

}
