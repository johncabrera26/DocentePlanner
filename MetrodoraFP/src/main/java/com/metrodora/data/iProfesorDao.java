package com.metrodora.data;

import com.metrodora.dominio.Asignatura;
import com.metrodora.dominio.Profesor;
import java.util.List;

public interface iProfesorDao {

    public List<Profesor> findAllProfesores();

    public void insertarProfesor(Profesor profesor);

    public int obtenerUltimoIDInsertado();

    public void eliminarProfesorPorId(int profesorId);

    public String obtenerNombreProfesorPorId(int idProfesor);

    public List<Profesor> obtenerProfesoresPorAsignatura(int idAsignatura);

    public List<Asignatura> obtenerAsignaturasPorProfesor(int idProfesor);

    public String obtenerNombreCompletoProfesorPorId(int idProfesor);

    public Profesor findById(int id);
    public List<Asignatura> obtenerAsignaturasPorProfesorId(int profesorId);
    

}
