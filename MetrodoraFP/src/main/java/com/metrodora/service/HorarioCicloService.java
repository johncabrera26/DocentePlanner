package com.metrodora.service;

import com.metrodora.data.iHorarioCicloDao;
import com.metrodora.dominio.Horariociclo;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class HorarioCicloService implements iHorarioCicloService {

    @Inject
    private iHorarioCicloDao horarioCicloDao;

    @Override
    public List<Horariociclo> listarHorarioCiclos() {

        return horarioCicloDao.findAllHorarioCiclo();

    }

}
