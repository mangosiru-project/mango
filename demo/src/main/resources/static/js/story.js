let lastScrollTop = 0;
window.addEventListener('scroll', function() {
    var candle = document.getElementById('candle');
    var scrollTop = window.pageYOffset || document.documentElement.scrollTop;

    if (scrollTop > lastScrollTop) {
        // Scroll down
        candle.src = '/image/without3dcake.png';
    } else {
        // Scroll up
        candle.src = '/image/with3dcake.png';
    }
    lastScrollTop = scrollTop <= 0 ? 0 : scrollTop; // For Mobile or negative scrolling
});


document.addEventListener('DOMContentLoaded', () => {
    const splashText = document.querySelector('.splash-text');

    setTimeout(() => {
        splashText.style.opacity = '1';
        splashText.style.transform = 'translateY(0)';

        createSmoke(splashText);
    }, 500);

    function createSmoke(container) {
        const smoke = document.createElement('div');
        smoke.classList.add('smoke');
        container.appendChild(smoke);

        const x = container.clientWidth / 2;
        const y = container.clientHeight / 2;

        smoke.style.left = `${x}px`;
        smoke.style.top = `${y}px`;

        setTimeout(() => {
            smoke.remove();
        }, 1000);
    }
})