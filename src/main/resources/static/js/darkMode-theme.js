/**
 * Gestión de temas y modo oscuro
 * Controla los temas de color (azul, verde, indigo, slate)
 * y el modo oscuro, persistiendo en localStorage.
 */
const ThemeManager = {
    init() {
        this.loadPreferences();
        this.setupDarkToggle();
    },

    setTheme(theme) {
        document.documentElement.className = document.documentElement.className
            .replace(/theme-\w+/g, '').trim();
        document.documentElement.classList.add(theme);
        localStorage.setItem('theme', theme);
        this.updateActiveTheme();
    },

    toggleDark() {
        document.documentElement.classList.toggle('dark');
        localStorage.setItem('darkMode', document.documentElement.classList.contains('dark'));
        this.updateDarkIcon();
    },

    updateActiveTheme() {
        const currentTheme = document.documentElement.className.match(/theme-\w+/)?.[0] || 'theme-azul';
        document.querySelectorAll('.theme-dot').forEach(btn => {
            btn.classList.toggle('border-white', btn.dataset.theme === currentTheme);
        });
    },

    updateDarkIcon() {
        const isDark = document.documentElement.classList.contains('dark');
        const moonIcon = document.getElementById('moonIcon');
        const sunIcon = document.getElementById('sunIcon');
        if (moonIcon && sunIcon) {
            moonIcon.classList.toggle('hidden', isDark);
            sunIcon.classList.toggle('hidden', !isDark);
        }
    },

    loadPreferences() {
        const savedTheme = localStorage.getItem('theme') || 'theme-azul';
        document.documentElement.classList.add(savedTheme);

        const savedDark = localStorage.getItem('darkMode');
        if (savedDark === 'true') {
            document.documentElement.classList.add('dark');
        }
    },

    setupDarkToggle() {
        const darkToggle = document.getElementById('darkToggle');
        if (darkToggle) {
            darkToggle.addEventListener('click', () => this.toggleDark());
        }
        this.updateDarkIcon();
        this.updateActiveTheme();
    }
};

// Inicializar cuando el DOM esté listo
document.addEventListener('DOMContentLoaded', () => ThemeManager.init());
