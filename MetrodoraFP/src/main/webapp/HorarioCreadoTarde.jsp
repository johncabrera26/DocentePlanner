<%-- 
    Document   : HorarioCreadoTarde
    Created on : 05-jun-2024, 9:26:47
    Author     : PC
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Horario Creado - Tarde</title>
        <style>
            body {
                background-color: white;
                font-family: Arial, sans-serif;
            }
            .schedule {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
            }
            .schedule th, .schedule td {
                border: 1px solid #ddd;
                padding: 8px;
                text-align: center;
            }
            .schedule th {
                background-color: #f2f2f2;
            }
            .empty {
                background-color: #f9f9f9;
            }
        </style>
    </head>
    <body>
        <h1>Horario Creado - Tarde</h1>
        <form method="get" action="CrearHorarioTardeServlet">
            <input type="hidden" name="idCiclo" value="${param.idCiclo}" />
            <button type="submit">Actualizar Horario</button>
        </form>
        <table class="schedule">
            <thead>
                <tr>
                    <th></th>
                    <c:forEach var="hora" items="${franjasHorarias}">
                        <th>${hora}</th>
                    </c:forEach>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="dia" items="${diasSemana}">
                    <tr>
                        <td>${dia}</td>
                        <c:forEach var="hora" items="${franjasHorarias}">
                            <c:set var="key" value="${dia} - ${hora}" />
                            <c:set var="value" value="${mapaAsignacion[key]}" />
                            <c:choose>
                                <c:when test="${not empty value}">
                                    <td><c:out value="${value}" escapeXml="false" /></td>
                                </c:when>
                                <c:otherwise>
                                    <td class="empty">Sin asignar</td>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <c:if test="${empty mapaAsignacion}">
            <p>No se encontraron asignaciones de horario.</p>
        </c:if>
    </body>
</html>
