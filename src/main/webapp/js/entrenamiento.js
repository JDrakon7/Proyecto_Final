/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


// Código JavaScript para cargar las categorías desde la base de datos
window.addEventListener('DOMContentLoaded', (event) => {
    fetch('ObtenerCategorias')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
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

    fetch('AgregarCategoria', {
        method: 'POST',
        body: formData
    })
    .then(response => response.text())
    .then(data => {
        alert('Categoria agregada exitosamente.');
        document.getElementById('new-category').value = ''; // Limpiar el campo de texto
        document.getElementById('category-form').style.display = 'none'; // Ocultar el formulario de agregar categoría
        fetch('ObtenerCategorias')  // Recargar las categorías
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
    .catch(error => console.error('Error al agregar la categoria:', error));
});
