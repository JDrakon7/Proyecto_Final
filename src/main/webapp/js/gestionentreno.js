/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

// Cargar entrenamientos
function cargarEntrenamientos() {
    fetch('http://localhost:8080/Proyecto2/LeerEntrenamiento', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error al realizar la solicitud');
        }
        return response.json();
    })
    .then(data => {
        const entrenamientosList = document.getElementById('entrenamientos-list');
        entrenamientosList.innerHTML = '';
        data.forEach(entrenamiento => {
            const pregunta = decodeURIComponent(entrenamiento.pregunta);
            const respuesta = decodeURIComponent(entrenamiento.respuesta);
            const entrenamientoDiv = document.createElement('div');
            entrenamientoDiv.classList.add('item');
            entrenamientoDiv.innerHTML = `
                <div>
                    <strong>Pregunta:</strong> ${pregunta}<br>
                    <strong>Respuesta:</strong> ${respuesta}
                </div>
                <div class="buttons">
                    <button onclick="editarEntrenamiento(${entrenamiento.idEntrenamiento}, '${pregunta}', '${respuesta}')" class="button editar">Editar</button>
                    <button onclick="eliminarEntrenamiento(${entrenamiento.idEntrenamiento})" class="button eliminar">Eliminar</button>
                </div>
            `;
            entrenamientosList.appendChild(entrenamientoDiv);
        });
    })
    .catch(error => console.error('Error al cargar los entrenamientos:', error));
}

// Editar entrenamiento
function editarEntrenamiento(id, pregunta, respuesta) {
    document.getElementById('edit-id').value = id;
    document.getElementById('edit-pregunta').value = pregunta;
    document.getElementById('edit-respuesta').value = respuesta;
    document.getElementById('edit-form').style.display = 'block';
}

// Guardar entrenamiento editado
function guardarEntrenamiento() {
    const id = document.getElementById('edit-id').value;
    const pregunta = document.getElementById('edit-pregunta').value;
    const respuesta = document.getElementById('edit-respuesta').value;

    fetch('EntrenamientoServlet?action=editarEntrenamiento', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded ; charset=UTF-8'
        },
        body: `id_entrenamiento=${encodeURIComponent(id)}&pregunta=${encodeURIComponent(pregunta)}&respuesta=${encodeURIComponent(respuesta)}`
    })
    .then(response => response.text())
    .then(data => {
        alert(data);
        document.getElementById('edit-form').style.display = 'none';
        cargarEntrenamientos();
    })
    .catch(error => console.error('Error al guardar el entrenamiento:', error));
}

// Cancelar edición
function cancelarEdicion() {
    document.getElementById('edit-form').style.display = 'none';
}

// Eliminar entrenamiento
function eliminarEntrenamiento(id) {
    if (confirm('¿Estás seguro de que deseas eliminar este entrenamiento?')) {
        fetch('EntrenamientoServlet?action=eliminarEntrenamiento', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded ; charset=UTF-8'
            },
            body: `id_entrenamiento=${encodeURIComponent(id)}`
        })
        .then(response => response.text())
        .then(data => {
            alert(data);
            cargarEntrenamientos();
        })
        .catch(error => console.error('Error al eliminar el entrenamiento:', error));
    }
}

// Inicializar la carga de entrenamientos al cargar la página
window.addEventListener('DOMContentLoaded', (event) => {
    cargarEntrenamientos();
});
