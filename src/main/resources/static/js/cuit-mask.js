/**
 * CUIT Mask — formato XX-XXXXXXXX-X en vivo, envía 11 dígitos al backend.
 * Se activa automáticamente en cualquier input con class="cuit-mask".
 */
document.addEventListener('DOMContentLoaded', function () {
    document.querySelectorAll('.cuit-mask').forEach(function (input) {
        // Formatea en vivo mientras el usuario escribe
        input.addEventListener('input', function () {
            var digits = this.value.replace(/\D/g, '').slice(0, 11);
            var formatted = '';

            if (digits.length > 0) {
                formatted = digits.slice(0, 2);
                if (digits.length > 2) {
                    formatted += '-' + digits.slice(2, 10);
                }
                if (digits.length > 10) {
                    formatted += '-' + digits.slice(10, 11);
                }
            }

            if (this.value !== formatted) {
                this.value = formatted;
            }
        });

        // Dispara el formateo si ya tiene valor (ej: edición)
        if (input.value) {
            input.dispatchEvent(new Event('input'));
        }

        // Al enviar el formulario, saca los guiones
        var form = input.closest('form');
        if (form) {
            form.addEventListener('submit', function () {
                input.value = input.value.replace(/\D/g, '');
            });
        }
    });
});
