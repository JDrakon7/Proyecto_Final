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

// Editar entrenamiento
function editarEntrenamiento(id) {
    // Lógica para editar un entrenamiento
    // Aquí podrías abrir un formulario con los datos actuales y permitir que el usuario los edite
}

// Eliminar entrenamiento
function eliminarEntrenamiento(id) {
    if (confirm('¿Estás seguro de que deseas eliminar este entrenamiento?')) {
        fetch('http://localhost:8080/Proyecto2/EntrenamientoServlet?action=eliminarEntrenamiento', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: `id_entrenamiento=${id}`
        })
        .then(response => response.text())
        .then(data => {
            alert(data);
            cargarEntrenamientos();
        })
        .catch(error => console.error('Error al eliminar el entrenamiento:', error));
    }
}

// Código para cargar categorías y agregar nuevas
window.addEventListener('DOMContentLoaded', (event) => {
    fetch('http://localhost:8080/Proyecto2/EntrenamientoServlet?action=obtenerCategorias')
        .then(response => {
            if (!response.ok) {
                throw new Error('Error en la solicitud, inténtelo nuevamente');
            }
            return response.json();
        })
        .then(data => {
            const categoriaSelect = document.getElementById('categoria');
            data.forEach(categoria => {
                const option = document.createElement('option');
                option.value = categoria.idCategoria;
                option.textContent = categoria.nombreCategoria;
                categoriaSelect.appendChild(option);
            });
        })
        .catch(error => console.error('Error al cargar las categorias:', error));
});

// Mostrar/Ocultar el formulario de agregar categoría
document.getElementById('add-category-btn').addEventListener('click', () => {
    const categoryForm = document.getElementById('category-form');
    categoryForm.style.display = categoryForm.style.display === 'none' ? 'block' : 'none';
});

// Enviar el formulario de agregar categoría con AJAX
document.getElementById('submit-category-btn').addEventListener('click', () => {
    const newCategory = document.getElementById('new-category').value;
    if (!newCategory) {
        alert('Por favor, ingrese el nombre de la nueva categoria.');
        return;
    }

    const formData = new FormData();
    formData.append('nombre_categoria', newCategory);

    fetch('http://localhost:8080/Proyecto2/AgregarCategoria', {
        method: 'POST',
        body: formData
    })
    .then(response => response.text())
    .then(data => {
        alert('Categoria agregada exitosamente.');
        document.getElementById('new-category').value = ''; // Limpiar el campo de texto
        document.getElementById('category-form').style.display = 'none'; // Ocultar el formulario de agregar categoría
        fetch('http://localhost:8080/Proyecto2/EntrenamientoServlet?action=obtenerCategorias')  // Recargar las categorías
            .then(response => response.json())
            .then(data => {
                const categoriaSelect = document.getElementById('categoria');
                categoriaSelect.innerHTML = '<option value="">Selecciona una categoria</option>';
                data.forEach(categoria => {
                    const option = document.createElement('option');
                    option.value = categoria.idCategoria;
                    option.textContent = categoria.nombreCategoria;
                    categoriaSelect.appendChild(option);
                });
            });
    })
    .catch(error => console.error('Error al agregar la categoría:', error));
});
