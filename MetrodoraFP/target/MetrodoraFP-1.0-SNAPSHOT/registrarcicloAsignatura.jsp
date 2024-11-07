<!DOCTYPE html>
<html>
<head>
    <title>Formulario de Inserción</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Estilos adicionales */
        body {
            font-family: Arial, sans-serif;
        }

        .form-container {
            max-width: 500px;
            margin: 0 auto;
        }

        .form-title {
            text-align: center;
            margin-bottom: 30px;
        }

        .form-group label {
            font-weight: bold;
        }

        .form-group input[type="text"], 
        .form-group input[type="number"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border-radius: 5px;
            border: 1px solid #ced4da;
        }

        .form-group input[type="submit"] {
            width: 100%;
            padding: 10px;
            border-radius: 5px;
            border: none;
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
        }

        .form-group input[type="submit"]:hover {
            background-color: #0056b3;
        }

        .error-message {
            color: red;
            margin-top: 5px;
        }
    </style>
</head>
<body>
<div class="container form-container">
    <h1 class="form-title">Formulario de Inserción</h1>
    <form action="InsertarCiclosAsignaturas" method="post" onsubmit="return validateForm()">
        <div class="form-group">
            <label for="nombreCiclo">Nombre del Ciclo:</label>
            <input type="text" id="nombreCiclo" name="nombreCiclo">
            <span id="nombreCicloError" class="error-message"></span>
        </div>

        <div class="form-group">
            <label>Asignaturas:</label>
            <div id="asignaturasContainer">
                <div class="asignatura-group">
                    <input type="text" name="asignatura[]" placeholder="Nombre de la Asignatura" class="form-control">
                    <input type="number" name="horasSemanales[]" placeholder="Horas Semanales" class="form-control">
                    <button type="button" onclick="agregarCampo()" class="btn btn-primary btn-sm mt-2">Agregar Asignatura</button>
                </div>
            </div>
            <span id="asignaturasError" class="error-message"></span>
        </div>

        <div class="form-group">
            <input type="submit" value="Enviar" class="btn btn-primary">
        </div>
    </form>
</div>

<!-- Bootstrap JS (jQuery debe ser incluido antes) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    function validateForm() {
        var nombreCiclo = document.getElementById("nombreCiclo").value.trim();
        var asignaturasGroups = document.querySelectorAll(".asignatura-group");

        var nombreCicloError = document.getElementById("nombreCicloError");
        var asignaturasError = document.getElementById("asignaturasError");

        nombreCicloError.innerHTML = "";
        asignaturasError.innerHTML = "";

        if (nombreCiclo === "") {
            nombreCicloError.innerHTML = "Por favor, ingrese el nombre del ciclo";
            return false;
        }

        var alMenosUnaAsignaturaValida = false;

        for (var i = 0; i < asignaturasGroups.length; i++) {
            var asignaturaInput = asignaturasGroups[i].querySelector("input[name='asignatura[]']");
            var horasSemanalesInput = asignaturasGroups[i].querySelector("input[name='horasSemanales[]']");

            if (asignaturaInput.value.trim() !== "" && horasSemanalesInput.value.trim() !== "") {
                alMenosUnaAsignaturaValida = true;
            } else {
                asignaturasError.innerHTML = "Por favor, complete todos los campos de asignatura";
                return false;
            }
        }

        if (!alMenosUnaAsignaturaValida) {
            asignaturasError.innerHTML = "Por favor, ingrese al menos una asignatura válida";
            return false;
        }

        return true;
    }

    function agregarCampo() {
        var asignaturasContainer = document.getElementById("asignaturasContainer");

        var divFormGroup = document.createElement("div");
        divFormGroup.classList.add("asignatura-group", "form-group");

        var nuevaAsignaturaInput = document.createElement("input");
        nuevaAsignaturaInput.type = "text";
        nuevaAsignaturaInput.name = "asignatura[]";
        nuevaAsignaturaInput.placeholder = "Nombre de la Asignatura";
        nuevaAsignaturaInput.classList.add("form-control");

        var nuevaHorasSemanalesInput = document.createElement("input");
        nuevaHorasSemanalesInput.type = "number";
        nuevaHorasSemanalesInput.name = "horasSemanales[]";
        nuevaHorasSemanalesInput.placeholder = "Horas Semanales";
        nuevaHorasSemanalesInput.classList.add("form-control");

        var btnEliminar = document.createElement("button");
        btnEliminar.type = "button";
        btnEliminar.classList.add("btn", "btn-danger", "btn-sm", "ml-2");
        btnEliminar.textContent = "Eliminar";
        btnEliminar.onclick = function () {
            asignaturasContainer.removeChild(divFormGroup);
        };

        divFormGroup.appendChild(nuevaAsignaturaInput);
        divFormGroup.appendChild(nuevaHorasSemanalesInput);
        divFormGroup.appendChild(btnEliminar);

        asignaturasContainer.appendChild(divFormGroup);
    }
</script>

</body>
</html>
