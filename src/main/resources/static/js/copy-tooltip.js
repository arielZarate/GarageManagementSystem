   document.addEventListener('click', function(e) {
        var btn = e.target.closest('[data-copy]');
        if (!btn) return;

        var text = btn.getAttribute('data-copy');
        if (!text) return;

        navigator.clipboard.writeText(text).then(function() {
            var tooltip = btn.parentElement.querySelector('.scale-90');
            if (!tooltip) return;

            tooltip.classList.remove('scale-90', 'opacity-0');
            tooltip.classList.add('scale-100', 'opacity-100');
            setTimeout(function() {
                tooltip.classList.remove('scale-100', 'opacity-100');
                tooltip.classList.add('scale-90', 'opacity-0');
            }, 1200);
        }).catch(function() {
            var textarea = document.createElement('textarea');
            textarea.value = text;
            textarea.style.position = 'fixed';
            textarea.style.opacity = '0';
            document.body.appendChild(textarea);
            textarea.select();
            document.execCommand('copy');
            document.body.removeChild(textarea);
        });
    });