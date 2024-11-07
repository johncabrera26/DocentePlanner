package com.metrodora.service;

import com.metrodora.data.iHorarioProfesorDao;
import com.metrodora.dominio.Horarioprofesor;
import com.metrodora.dominio.Profesor;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class HorarioProfesorService implements iHorarioProfesorService {

    @Inject
    private iHorarioProfesorDao horarioprofesorDao;

    @Override
    public List<Horarioprofesor> listarHorarioProfesores() {

        return horarioprofesorDao.findAllHorarioProfesores();

    }

    @Override
    public void insertarHorarioProfesor(int idProfesor, int idAsignatura) {
        horarioprofesorDao.insertarHorarioProfesor(idProfesor, idAsignatura);
    }

    @Override
    public void eliminarHorariosPorProfesorId(int profesorId) {

        horarioprofesorDao.eliminarHorariosPorProfesorId(profesorId);

    }

    @Override
    public String obtenerNombreProfesorPorId(int idProfesor) {
        return horarioprofesorDao.obtenerNombreProfesorPorId(idProfesor);
    }

    @Override
    public String obtenerNombreAsignaturaPorId(int idAsignatura) {
        return horarioprofesorDao.obtenerNombreAsignaturaPorId(idAsignatura);
    }

    @Override
    public List<Profesor> obtenerProfesoresPorAsignatura(int idAsignatura) {
        return horarioprofesorDao.obtenerProfesoresPorAsignatura(idAsignatura);
    }

    @Override
    public List<Horarioprofesor> obtenerHorariosPorProfesorYDia(int idProfesor, String dia) {
        return horarioprofesorDao.obtenerHorariosPorProfesorYDia(idProfesor, dia);
    }

    @Override
    public String obtenerNombreCompletoProfesorPorId(int idProfesor) {
        return horarioprofesorDao.obtenerNombreCompletoProfesorPorId(idProfesor);
    }

    @Override
    public Horarioprofesor findById(int idHorario) {
        return horarioprofesorDao.findById(idHorario);
    }

    @Override
    public void actualizarHorario(Horarioprofesor horarioprofesor) {

        horarioprofesorDao.actualizarHorario(horarioprofesor);

    }

    @Override
    public void guardarHorario(Horarioprofesor horarioprofesor) {
        horarioprofesorDao.guardarHorario(horarioprofesor);
    }

    @Override
    public void eliminarHorarioPorId(int horarioId) {
        horarioprofesorDao.eliminarHorarioPorId(horarioId);
    }
}
