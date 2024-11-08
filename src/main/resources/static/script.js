const baseUrl = 'http://localhost:8080'; // URL base del back-end

// Funciones para Clientes
async function getClientes() {
    const response = await fetch(`${baseUrl}/clientes`);
    const clientes = await response.json();
    renderClientes(clientes);
}

async function addCliente(event) {
    event.preventDefault();
    const name = document.getElementById('cliente-name').value;
    const contact = document.getElementById('cliente-contact').value;

    await fetch(`${baseUrl}/clientes`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name, contact })
    });

    document.getElementById('cliente-form').reset();
    getClientes();
}

async function buscarClientePorId(event) {
    event.preventDefault();
    const id = document.getElementById('cliente-id').value;
    const response = await fetch(`${baseUrl}/clientes/${id}`);
    if (response.ok) {
        const cliente = await response.json();
        renderClientes([cliente]); // Mostrar solo el cliente encontrado
    } else {
        alert('Cliente no encontrado');
    }
}

async function updateCliente(event) {
    event.preventDefault();
    const id = document.getElementById('update-cliente-id').value;
    const name = document.getElementById('update-cliente-name').value;
    const contact = document.getElementById('update-cliente-contact').value;

    await fetch(`${baseUrl}/clientes/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name, contact })
    });

    document.getElementById('cliente-update-form').reset();
    getClientes();
}

function renderClientes(clientes) {
    const clientesList = document.getElementById('clientes-list');
    clientesList.innerHTML = '';
    clientes.forEach(cliente => {
        const clienteItem = document.createElement('div');
        clienteItem.className = 'item';
        clienteItem.innerHTML = ` <h1>Id: ${cliente.idClient}</h1><h1>Nombre:
        ${cliente.name}
        </h1>
             <h1>Contacto: ${cliente.contact}</h1>
            <button style="border-radius: 20px; color: red; font-size: 16px;"
            onclick="deleteCliente(${cliente.idClient})
            ">Eliminar</button>
        `;
        clientesList.appendChild(clienteItem);
    });
}

async function deleteCliente(idClient) {
    await fetch(`${baseUrl}/clientes/${idClient}`, { method: 'DELETE' });
    getClientes();
}

// Funciones para Productos
async function getProductos() {
    const response = await fetch(`${baseUrl}/productos`);
    const productos = await response.json();
    renderProductos(productos);
}

async function addProducto(event) {
    event.preventDefault();
    const name = document.getElementById('producto-name').value;
    const price = parseFloat(document.getElementById('producto-price').value);

    await fetch(`${baseUrl}/productos`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name, price })
    });

    document.getElementById('producto-form').reset();
    getProductos();
}

async function buscarProductoPorId(event) {
    event.preventDefault();
    const id = document.getElementById('producto-id').value;
    const response = await fetch(`${baseUrl}/productos/${id}`);
    if (response.ok) {
        const producto = await response.json();
        renderProductos([producto]); // Mostrar solo el producto encontrado
    } else {
        alert('Producto no encontrado');
    }
}

async function updateProducto(event) {
    event.preventDefault();
    const id = document.getElementById('update-producto-id').value;
    const name = document.getElementById('update-producto-name').value;
    const price = parseFloat(document.getElementById('update-producto-price').value);

    await fetch(`${baseUrl}/productos/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name, price })
    });

    document.getElementById('producto-update-form').reset();
    getProductos();
}

function renderProductos(productos) {
    const productosList = document.getElementById('productos-list');
    productosList.innerHTML = '';
    productos.forEach(producto => {
        const productoItem = document.createElement('div');
        productoItem.className = 'item';
        productoItem.innerHTML = `<h1>Id: ${producto.idProducto}</h1>
            <h1>Nombre: ${producto.name}</h1> <h1>Precio: $${producto.price
            .toFixed(2)}</h1>
            <button style="border-radius: 20px; color: red; font-size: 16px;"
            onclick="deleteProducto
            (${producto.idProducto})
            ">Eliminar</button>
        `;
        productosList.appendChild(productoItem);
    });
}

async function deleteProducto(id) {
    await fetch(`${baseUrl}/productos/${id}`, { method: 'DELETE' });
    getProductos();
}

// Inicializar y asociar eventos
document.getElementById('cliente-form').addEventListener('submit', addCliente);
document.getElementById('producto-form').addEventListener('submit', addProducto);
document.getElementById('buscar-cliente-form').addEventListener('submit', buscarClientePorId);
document.getElementById('buscar-producto-form').addEventListener('submit', buscarProductoPorId);
document.getElementById('cliente-update-form').addEventListener('submit', updateCliente);
document.getElementById('producto-update-form').addEventListener('submit', updateProducto);

getClientes();
getProductos();
