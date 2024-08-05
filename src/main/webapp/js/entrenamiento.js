/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

// Obtener categorías
fetch('http://localhost:8080/Proyecto2/ObtenerCategorias')
    .then(response => response.json())
    .then(data => {
        const categoriaSelect = document.getElementById('categoria');
        data.forEach(categoria => {
            const option = document.createElement('option');
            option.value = categoria.idCategoria;
            option.textContent = categoria.nombreCategoria;
            categoriaSelect.appendChild(option);
        });
    })
    .catch(error => console.error('Error al cargar las categorías:', error));

// Agregar entrenamiento
document.getElementById('training-form').addEventListener('submit', (event) => {
    event.preventDefault();
    const formData = new FormData(event.target);
    formData.append('action', 'agregarEntrenamiento');

    fetch('http://localhost:8080/Proyecto2/AgregarEntrenamiento', {
        method: 'POST',
        body: new URLSearchParams(formData)
    })
    .then(response => response.text())
    .then(data => {
        alert(data);
    })
    .catch(error => console.error('Error al agregar el entrenamiento:', error));
});

document.getElementById('add-category-btn').addEventListener('click', () => {
    const categoryForm = document.getElementById('category-form');
    categoryForm.style.display = categoryForm.style.display === 'none' ? 'block' : 'none';
});

document.getElementById('submit-category-btn').addEventListener('click', () => {
    const newCategory = document.getElementById('new-category').value;
    if (!newCategory) {
        alert('Por favor, ingrese el nombre de la nueva categoría.');
        return;
    }

    const formData = new FormData();
    formData.append('nombre_categoria', newCategory);

    fetch('/EntrenamientoServlet?action=agregarCategoria', {
        method: 'POST',
        body: formData
    })
    .then(response => response.text())
    .then(data => {
        alert('Categoría agregada exitosamente.');
        document.getElementById('new-category').value = ''; // Limpiar el campo de texto
        document.getElementById('category-form').style.display = 'none'; // Ocultar el formulario de agregar categoría
        fetch('/EntrenamientoServlet?action=obtenerCategorias')  // Recargar las categorías
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
    })
    .catch(error => console.error('Error al agregar la categoría:', error));
});
