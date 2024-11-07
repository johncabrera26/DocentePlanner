package com.metrodora.web;

import com.metrodora.dominio.Horariociclo;
import com.metrodora.service.iHorarioCicloService;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HorarioCicloServlet", urlPatterns = {"/HorarioCicloServlet"})
public class HorarioCicloServlet extends HttpServlet {

    @Inject

    iHorarioCicloService horariocicloService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Horariociclo> horariociclos = horariocicloService.listarHorarioCiclos();

        System.out.println("horariociclo" + horariociclos);

        request.setAttribute("horariociclo", horariociclos);

        request.getRequestDispatcher("listadohorariociclos.jsp").forward(request, response);

    }
}
