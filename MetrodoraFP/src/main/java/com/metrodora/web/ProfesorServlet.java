package com.metrodora.web;

import com.metrodora.dominio.Profesor;
import com.metrodora.service.iProfesorService;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProfesorServlet", urlPatterns = {"/ProfesorServlet"})
public class ProfesorServlet extends HttpServlet {

    @Inject

    iProfesorService profesorService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Profesor> profesores = profesorService.listarProfesores();

        System.out.println("profesor" + profesores);

        request.setAttribute("profesor", profesores);

        request.getRequestDispatcher("listadoprofesor.jsp").forward(request, response);

    }
}
