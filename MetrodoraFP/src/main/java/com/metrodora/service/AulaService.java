package com.metrodora.service;

import com.metrodora.data.iAulaDao;
import com.metrodora.dominio.Aula;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AulaService implements iAulaService {

    @Inject
    private iAulaDao aulaDao;

    @Override
    public List<Aula> listarAulas() {

        return aulaDao.findAllAulas();
    }

}
