const typeFilter = document.getElementById('typeFilter');
const brandSelect = document.getElementById('brandId');
const modelSelect = document.getElementById('modelId');
const versionSelect = document.getElementById('versionId');
const vehicleTypeHidden = document.getElementById('vehicleType');
const initialBrandId = document.getElementById('initialBrandId');
const initialModelId = document.getElementById('initialModelId');
const initialVersionId = document.getElementById('initialVersionId');

if (!typeFilter || !brandSelect || !modelSelect || !versionSelect || !vehicleTypeHidden) {
    console.error('No se encontraron los elementos del formulario');
} else {

    function enableSelect(select) {
        select.disabled = false;
    }

    function disableSelect(select) {
        select.disabled = true;
    }

    function resetSelect(select) {
        select.innerHTML = '<option value="">Seleccionar...</option>';
        disableSelect(select);
    }

    function populateSelect(select, data, restoreValue) {
        select.innerHTML = '<option value="">Seleccionar...</option>';
        data.forEach(function (item) {
            var option = document.createElement('option');
            option.value = item.id;
            option.textContent = item.name;
            select.appendChild(option);
        });
        if (restoreValue && select.querySelector('option[value="' + restoreValue + '"]')) {
            select.value = restoreValue;
        }
        enableSelect(select);
    }

    // 1. Type filter → load brands
    typeFilter.addEventListener('change', function () {
        var type = this.value;

        resetSelect(brandSelect);
        resetSelect(modelSelect);
        resetSelect(versionSelect);

        if (!type) return;

        fetch('/api/vehicle/brands?type=' + type)
            .then(function (r) {
                if (!r.ok) throw new Error('Error HTTP: ' + r.status);
                return r.json();
            })
            .then(function (data) {
                populateSelect(brandSelect, data);
            })
            .catch(function (err) { console.error('Error cargando marcas:', err); });
    });

    // 2. Brand → load models
    brandSelect.addEventListener('change', function () {
        var brandId = this.value;
        var type = typeFilter.value;

        resetSelect(modelSelect);
        resetSelect(versionSelect);

        if (!brandId || !type) return;

        fetch('/api/vehicle/models?brandId=' + brandId + '&type=' + type)
            .then(function (r) {
                if (!r.ok) throw new Error('Error HTTP: ' + r.status);
                return r.json();
            })
            .then(function (data) {
                populateSelect(modelSelect, data);
            })
            .catch(function (err) { console.error('Error cargando modelos:', err); });
    });

    // 3. Model → load versions + set vehicleType
    modelSelect.addEventListener('change', function () {
        var modelId = this.value;

        resetSelect(versionSelect);

        if (!modelId) {
            vehicleTypeHidden.value = '';
            return;
        }

        vehicleTypeHidden.value = typeFilter.value;

        fetch('/api/vehicle/versions?modelId=' + modelId)
            .then(function (r) {
                if (!r.ok) throw new Error('Error HTTP: ' + r.status);
                return r.json();
            })
            .then(function (data) {
                if (data.length > 0) {
                    populateSelect(versionSelect, data);
                } else {
                    enableSelect(versionSelect);
                }
            })
            .catch(function (err) { console.error('Error cargando versiones:', err); });
    });

    // 4. Edit mode: restore cascade from initial values
    (function initEditMode() {
        var type = vehicleTypeHidden.value;
        var brandId = initialBrandId ? initialBrandId.value : '';
        var modelId = initialModelId ? initialModelId.value : '';
        var versionId = initialVersionId ? initialVersionId.value : '';

        if (!type) return;

        typeFilter.value = type;

        fetch('/api/vehicle/brands?type=' + type)
            .then(function (r) {
                if (!r.ok) throw new Error('Error HTTP: ' + r.status);
                return r.json();
            })
            .then(function (brands) {
                populateSelect(brandSelect, brands, brandId);

                if (!brandId || !modelId) return;

                return fetch('/api/vehicle/models?brandId=' + brandId + '&type=' + type)
                    .then(function (r) {
                        if (!r.ok) throw new Error('Error HTTP: ' + r.status);
                        return r.json();
                    })
                    .then(function (models) {
                        populateSelect(modelSelect, models, modelId);

                        if (!modelId || !versionId) return;

                        return fetch('/api/vehicle/versions?modelId=' + modelId)
                            .then(function (r) {
                                if (!r.ok) throw new Error('Error HTTP: ' + r.status);
                                return r.json();
                            })
                            .then(function (versions) {
                                populateSelect(versionSelect, versions, versionId);
                            });
                    });
            })
            .catch(function (err) { console.error('Error restaurando modo edición:', err); });
    })();

}

// ---------------------------------------------------------------------------
// Customer autocomplete (sin cambios)
// ---------------------------------------------------------------------------
var customerSearchInput = document.getElementById('customerSearch');
var customerResultsDiv = document.getElementById('customerResults');
var customerIdInput = document.getElementById('customerId');
var customerSearchUrl = document.getElementById('customerSearchUrl');

if (customerSearchInput && customerResultsDiv && customerIdInput && customerSearchUrl) {

    var debounceTimer = null;

    customerSearchInput.addEventListener('input', function () {
        clearTimeout(debounceTimer);
        var query = this.value.trim();

        if (query.length < 2) {
            customerResultsDiv.classList.add('hidden');
            customerResultsDiv.innerHTML = '';
            customerIdInput.value = '';
            return;
        }

        debounceTimer = setTimeout(function () {
            var url = customerSearchUrl.value + '?q=' + encodeURIComponent(query);

            fetch(url)
                .then(function (r) {
                    if (!r.ok) throw new Error('Error HTTP: ' + r.status);
                    return r.json();
                })
                .then(function (data) {
                    customerResultsDiv.innerHTML = '';

                    if (!data || data.length === 0) {
                        customerResultsDiv.classList.add('hidden');
                        return;
                    }

                    data.forEach(function (customer) {
                        var div = document.createElement('div');
                        div.className = 'px-3 py-2 hover:bg-surface-container-low cursor-pointer text-sm text-on-surface';
                        div.textContent = customer.label;
                        div.addEventListener('click', function () {
                            customerIdInput.value = customer.id;
                            customerSearchInput.value = customer.label;
                            customerResultsDiv.classList.add('hidden');
                        });
                        customerResultsDiv.appendChild(div);
                    });

                    customerResultsDiv.classList.remove('hidden');
                })
                .catch(function (err) { console.error('Error buscando clientes:', err); });
        }, 300);
    });

    document.addEventListener('click', function (e) {
        if (!customerSearchInput.contains(e.target) && !customerResultsDiv.contains(e.target)) {
            customerResultsDiv.classList.add('hidden');
        }
    });
}
