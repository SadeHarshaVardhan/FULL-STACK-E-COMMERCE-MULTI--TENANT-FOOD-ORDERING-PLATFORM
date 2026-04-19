const insertForm = document.getElementById('insertForm');
const updateForm = document.getElementById('updateForm');
const tableBody = document.querySelector('#teaTable tbody');

// Insert tea
insertForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const formData = new FormData(insertForm);
    try {
        const response = await fetch('/alltea/insert', {
            method: 'POST',
            body: formData
        });
        const result = await response.text();
        alert(result);
        insertForm.reset();
        loadTeas();
    } catch (err) {
        alert('Error: ' + err.message);
    }
});

// Update tea
updateForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const formData = new FormData(updateForm);
    try {
        const response = await fetch(`/alltea/update/`, {
            method: 'PUT',
            body: formData
        });
        const result = await response.text();
        alert(result);
        updateForm.reset();
        updateForm.classList.add('hidden');
        loadTeas();
    } catch (err) {
        alert('Error: ' + err.message);
    }
});

// Cancel update
function cancelUpdate() {
    updateForm.reset();
    updateForm.classList.add('hidden');
}

// Load teas
async function loadTeas() {
    try {
        const response = await fetch('/alltea');
        const teas = await response.json();
        tableBody.innerHTML = '';
        teas.forEach(item => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${item.teaid}</td>
                <td>${item.teaname}</td>
                <td>${item.tealink ? `<img src="${item.tealink.replace("dl=0","raw=1")}" alt="${item.teaname}">` : ''}</td>
                <td>Rs${parseFloat(item.teaprice).toFixed(2)}</td>
                <td>${item.teadet}</td>
                <td class="actions">
                    <button onclick="editTea(${item.teaid}, '${item.teaname}', ${item.teaprice}, '${item.teadet}')">Update</button>
                    <button onclick="deleteTea(${item.teaid})">Delete</button>
                </td>
            `;
            tableBody.appendChild(tr);
        });
    } catch (err) {
        console.error(err);
    }
}

// Edit tea
function editTea(id, name, price, det) {
    updateForm.classList.remove('hidden');
    updateForm.querySelector('[name="id"]').value = id;
    updateForm.querySelector('[name="name"]').value = name;
    updateForm.querySelector('[name="price"]').value = price;
    updateForm.querySelector('[name="det"]').value = det;
    updateForm.querySelector('[name="file"]').value = ''; // Clear file input
}

// Delete tea
async function deleteTea(id) {
    if (!confirm('Are you sure you want to delete this tea?')) return;
    try {
        const response = await fetch(`/alltea/delete/${id}`, { method: 'DELETE' });
        const result = await response.text();
        alert(result);
        loadTeas();
    } catch (err) {
        alert('Error: ' + err.message);
    }
}

// Initial load
loadTeas();