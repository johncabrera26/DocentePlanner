package com.metrodora.service;

import com.metrodora.data.iProfesorDao;
import com.metrodora.dominio.Asignatura;
import com.metrodora.dominio.Profesor;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ProfesorService implements iProfesorService {

    @Inject
    private iProfesorDao profesorDao;

    @Override
    public List<Profesor> listarProfesores() {

        return profesorDao.findAllProfesores();
    }

    @Override
    public void insertarProfesor(Profesor profesor) {

        profesorDao.insertarProfesor(profesor);
    }

    @Override
    public int obtenerUltimoIDInsertado() {

        return profesorDao.obtenerUltimoIDInsertado();
    }

    @Override
    public void eliminarProfesorPorId(int profesorId) {

        profesorDao.eliminarProfesorPorId(profesorId);
    }

    @Override
    public String obtenerNombreProfesorPorId(int idProfesor) {
        return profesorDao.obtenerNombreProfesorPorId(idProfesor);

    }

    @Override
    public List<Profesor> obtenerProfesoresPorAsignatura(int idAsignatura) {
        return profesorDao.obtenerProfesoresPorAsignatura(idAsignatura);
    }

    @Override
    public List<Asignatura> obtenerAsignaturasPorProfesor(int idProfesor) {
        return profesorDao.obtenerAsignaturasPorProfesor(idProfesor);
    }

    @Override
    public String obtenerNombreCompletoProfesorPorId(int idProfesor) {
        return profesorDao.obtenerNombreCompletoProfesorPorId(idProfesor);
    }

    @Override
    public Profesor findById(int id) {
        return profesorDao.findById(id);
    }

    @Override
    public List<Asignatura> obtenerAsignaturasPorProfesorId(int profesorId) {
        return profesorDao.obtenerAsignaturasPorProfesor(profesorId);
    }

}
