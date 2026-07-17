    function openModal(id) {
        document.getElementById(id).classList.remove('hidden');
    }
    function closeModal(id) {
        document.getElementById(id).classList.add('hidden');
    }
    function openCreateModal() {
        const form = document.getElementById('brandForm');
        form.reset();
        form.action = document.getElementById('createBrandUrl').value;
        document.getElementById('brandSubmitBtn').textContent = 'Guardar';
        document.querySelector('#modal-brand h2').textContent = 'Nueva Marca';
        openModal('modal-brand');
    }