document.body.onload = () => {
    setTimeout(function() {
        var preloader = document.getElementById('page__preloader');
        if (!preloader.classList.contains('done')) {
            preloader.classList.add('done');
        }
    }, 3000);
}