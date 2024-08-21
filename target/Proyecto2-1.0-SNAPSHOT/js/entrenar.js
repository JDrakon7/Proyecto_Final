/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

// Agregar entrenamiento
document.getElementById('training-form').addEventListener('submit', (event) => {
    event.preventDefault();
    const formData = new FormData(event.target);
    formData.append('id_usuario', '<%= session.getAttribute("userId") %>');
    
    fetch(('http://localhost:8080/Proyecto2/AgregarEntrenamiento'), {
        method: 'POST',
        body: new URLSearchParams(formData)
    })
    .then(response => response.text())
    .then(data => {
        alert(data);
         window.location.href = window.location.href;  // Alternativa para recargar la página
    })
    .catch(error => console.error('Error al agregar el entrenamiento:', error));
});


// Obtener categorías y cargarlas en el select
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


 document.getElementById('add-category-btn').addEventListener('click', () => {
        const categoryForm = document.getElementById('category-form');
        categoryForm.style.display = categoryForm.style.display === 'none' ? 'block' : 'none';
    });

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
        body: new URLSearchParams(formData)
    })
    .then(response => response.text())
    .then(data => {
        alert(data);
        document.getElementById('new-category').value = ''; // Limpiar el campo de texto
        document.getElementById('category-form').style.display = 'none'; // Ocultar el formulario de agregar categoría
        // Recargar las categorías después de agregar una nueva
        fetch('http://localhost:8080/Proyecto2/ObtenerCategorias')
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
            })
            .catch(error => console.error('Error al cargar las categorías:', error));
    })
    .catch(error => console.error('Error al agregar la categoría:', error));
});










