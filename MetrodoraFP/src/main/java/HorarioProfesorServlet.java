/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.metrodora.web;

import com.metrodora.dominio.Horariociclo;
import com.metrodora.dominio.Horarioprofesor;
import com.metrodora.dominio.Profesor;
import com.metrodora.service.iHorarioProfesorService;
import com.metrodora.service.iProfesorService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author PC
 */
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "HorarioProfesorServlet", urlPatterns = {"/HorarioProfesorServlet"})
public class HorarioProfesorServlet extends HttpServlet {

    @Inject
    private iHorarioProfesorService horarioprofesorService;

    @Inject
    private iProfesorService profesorService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

     
        List<Horarioprofesor> horarios = horarioprofesorService.listarHorarioProfesores();
        
        
        
            System.out.println("horarios" + horarios);
        
       request.setAttribute("horarios", horarios);

        
        request.getRequestDispatcher("listadohorariociclos.jsp").forward(request, response);
}
}