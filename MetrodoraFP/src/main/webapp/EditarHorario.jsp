<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Horario</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: white;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
        }
        input, select {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            box-sizing: border-box;
        }
        button {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h2>Editar Horario</h2>
    <form action="EditarHorarioServlet" method="post">
        <input type="hidden" name="idHorario" value="${horario.IDHorarioProfesor}" />
        <div class="form-group">
            <label for="diaSemana">Día de la Semana</label>
            <input type="text" id="diaSemana" name="diaSemana" value="${fn:toUpperCase(horario.diaSemana)}" oninput="this.value = this.value.toUpperCase()" required />
        </div>
        <div class="form-group">
            <label for="horaInicio">Hora de Inicio</label>
            <input type="time" id="horaInicio" name="horaInicio" value="${fn:substring(horario.horaInicio, 11, 16)}" required />
        </div>
        <div class="form-group">
            <label for="horaFin">Hora de Fin</label>
            <input type="time" id="horaFin" name="horaFin" value="${fn:substring(horario.horaFin, 11, 16)}" required />
        </div>
        <div class="form-group">
            <label for="asignaturaId">Asignatura</label>
            <select id="asignaturaId" name="asignaturaId" >
                <option value="" ${empty horario.IDAsignatura ? 'selected' : ''}></option>
                <c:forEach var="asignatura" items="${asignaturas}">
                    <option value="${asignatura.IDAsignatura}" ${horario.IDAsignatura != null && horario.IDAsignatura.IDAsignatura == asignatura.IDAsignatura ? 'selected' : ''}>
                        ${asignatura.nombre}
                    </option>
                </c:forEach>
            </select>
        </div>

        <button type="submit">Guardar</button>
    </form>
</body>
</html>
