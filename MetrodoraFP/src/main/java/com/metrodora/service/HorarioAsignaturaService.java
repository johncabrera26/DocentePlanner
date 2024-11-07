package com.metrodora.service;

import com.metrodora.data.iHorarioAsignaturaDao;
import com.metrodora.dominio.Horarioasignatura;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class HorarioAsignaturaService implements iHorarioAsignaturaService {

    @Inject
    private iHorarioAsignaturaDao horarioAsignaturaDao;

    @Override
    public List<Horarioasignatura> listarHorarioAsignaturas() {

        return horarioAsignaturaDao.findAllHorarioAsignaturas();
    }

    @Override
    public List<Horarioasignatura> findAll() {

        return horarioAsignaturaDao.findAll();
    }

    @Override
    public void insertarHorarioAsignatura(Horarioasignatura horarioAsignatura) {

        horarioAsignaturaDao.insertarHorarioAsignatura(horarioAsignatura);
    }

    @Override
    public void eliminarHorariosPorCicloId(long cicloId) {

        horarioAsignaturaDao.eliminarHorariosPorCicloId(cicloId);
    }

    @Override
    public List<Horarioasignatura> obtenerAsignaturasPorCiclo(int idCiclo) {
        return horarioAsignaturaDao.obtenerAsignaturasPorCiclo(idCiclo);
    }

}
