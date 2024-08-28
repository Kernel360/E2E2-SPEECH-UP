document.addEventListener("DOMContentLoaded", function () {
    const academyFeature = document.getElementById("academy-feature");

    if (academyFeature) {
        academyFeature.addEventListener("click", function () {
            const url = `/map`;
            const name = 'popup-map'
            const options = 'top=10, left=10, width=800, height=600, status=no, menubar=no, toolbar=no, resizable=no, scrollbars=no, fullscreen=no';
            window.open(url, name, options);
        });
    }
});

document.addEventListener("DOMContentLoaded", function () {
    const academyFeature = document.getElementById("board");

    if (academyFeature) {
        academyFeature.addEventListener("click", function () {
            window.location.href = "/boards";
        });
    }
});

document.addEventListener("DOMContentLoaded", function () {
    const academyFeature = document.getElementById("analyze-record");

    if (academyFeature) {
        academyFeature.addEventListener("click", function () {
            window.location.href = "/scripts-list";
        });
    }
});