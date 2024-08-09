document.addEventListener('DOMContentLoaded',uerMe);

async function uerMe() {
    await fetch("/users/me").then(response => response.json());
}
