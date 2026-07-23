    function editFunction(btn) {
        const id = btn.getAttribute('data-id');
        const name = btn.getAttribute('data-name');
        const form = document.getElementById('brandForm');
        document.getElementById('brandId').value = id;
        document.getElementById('brandName').value = name;
        form.action = document.getElementById('updateBrandUrl').value;
        document.getElementById('brandSubmitBtn').textContent = 'Actualizar';
        document.querySelector('#modal-brand h2').textContent = 'Editar Marca';
        openModal('modal-brand');
    }
