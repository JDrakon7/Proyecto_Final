/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


// Cargar entrenamientos
function cargarEntrenamientos() {
    fetch('http://localhost:8080/Proyecto2/EntrenamientoServlet?action=leerEntrenamientos')
        .then(response => response.json())
        .then(data => {
            const entrenamientosList = document.getElementById('entrenamientos-list');
            entrenamientosList.innerHTML = '';
            data.forEach(entrenamiento => {
                const entrenamientoDiv = document.createElement('div');
                entrenamientoDiv.innerHTML = `
                    <div>
                        <strong>Pregunta:</strong> ${entrenamiento.pregunta}
                        <strong>Respuesta:</strong> ${entrenamiento.respuesta}
                        <button onclick="editarEntrenamiento(${entrenamiento.idEntrenamiento})">Editar</button>
                        <button onclick="eliminarEntrenamiento(${entrenamiento.idEntrenamiento})">Eliminar</button>
                    </div>
                `;
                entrenamientosList.appendChild(entrenamientoDiv);
            });
        })
        .catch(error => console.error('Error al cargar los entrenamientos:', error));
}

// Enviar el formulario de agregar entrenamiento con AJAX
document.getElementById('submit-training-btn').addEventListener('click', () => {
    const pregunta = document.getElementById('pregunta').value;
    const respuesta = document.getElementById('respuesta').value;
    const categoria = document.getElementById('categoria').value;
    if (!pregunta || !respuesta || !categoria) {
        alert('Por favor, llene todos los campos.');
        return;
    }

    const formData = new FormData();
    formData.append('action', 'agregarEntrenamiento');
    formData.append('pregunta', pregunta);
    formData.append('respuesta', respuesta);
    formData.append('categoria', categoria);

    fetch('http://localhost:8080/Proyecto2/EntrenamientoServlet', {
        method: 'POST',
        body: formData
    })
    .then(response => response.json())
    .then(data => {
        if (data.message) {
            alert(data.message);
            document.getElementById('pregunta').value = '';
            document.getElementById('respuesta').value = '';
            document.getElementById('categoria').value = '';
            cargarEntrenamientos();
        } else if (data.error) {
            alert(data.error);
        }
    })
    .catch(error => console.error('Error al agregar el entrenamiento:', error));
});

// Enviar el formulario de agregar categoría con AJAX
document.getElementById('submit-category-btn').addEventListener('click', () => {
    const newCategory = document.getElementById('new-category').value;
    if (!newCategory) {
        alert('Por favor, ingrese el nombre de la nueva categoría.');
        return;
    }

    const formData = new FormData();
    formData.append('action', 'agregarCategoria');
    formData.append('nombre_categoria', newCategory);

    fetch('http://localhost:8080/Proyecto2/EntrenamientoServlet', {
        method: 'POST',
        body: formData
    })
    .then(response => response.json())
    .then(data => {
        if (data.message) {
            alert(data.message);
            document.getElementById('new-category').value = ''; // Limpiar el campo de texto
            document.getElementById('category-form').style.display = 'none'; // Ocultar el formulario de agregar categoría
            fetch('http://localhost:8080/Proyecto2/EntrenamientoServlet?action=obtenerCategorias')  // Recargar las categorías
                .then(response => response.json())
                .then(data => {
                    const categoriaSelect = document.getElementById('categoria');
                    categoriaSelect.innerHTML = '<option value="">Selecciona una categoría</option>';
                    data.forEach(categoria => {
                        const option = document.createElement('option');
                        option.value = categoria.idCategoria;
                        option.textContent = categoria.nombreCategoria;
                        categoriaSelect.appendChild(option);
                    });
                });
        } else if (data.error) {
            alert(data.error);
        }
    })
    .catch(error => console.error('Error al agregar la categoría:', error));
});





////////////////Plabras clave///////////////////

// Mostrar/Ocultar el formulario de agregar palabra clave
document.getElementById('add-keyword-btn').addEventListener('click', () => {
    const keywordForm = document.getElementById('keyword-form');
    keywordForm.style.display = keywordForm.style.display === 'none' ? 'block' : 'none';
});

// Enviar el formulario de agregar palabra clave con AJAX
document.getElementById('submit-keyword-btn').addEventListener('click', () => {
    const newKeyword = document.getElementById('new-keyword').value;
    if (!newKeyword) {
        alert('Por favor, ingrese la nueva palabra clave.');
        return;
    }

    const formData = new FormData();
    formData.append('palabra_clave', newKeyword);

    fetch('http://localhost:8080/Proyecto2/AgregarPalabraClave', {
        method: 'POST',
        body: formData
    })
    .then(response => response.text())
    .then(data => {
        alert('Palabra clave agregada exitosamente.');
        document.getElementById('new-keyword').value = ''; // Limpiar el campo de texto
        document.getElementById('keyword-form').style.display = 'none'; // Ocultar el formulario de agregar palabra clave
    })
    .catch(error => console.error('Error al agregar la palabra clave:', error));
});
