document.addEventListener('DOMContentLoaded', loadScripts);

async function loadScripts() {
    const scripts = await getScriptsFromData();

    const list = document.getElementById('scripts-list');
    list.innerHTML = '';

    scripts.forEach(script => {
        const row = document.createElement('tr');
        row.classList.add('script-item');
        row.addEventListener('click', () => {
            goToScriptDetails(script);
        });
        if(script.content.length > 50){
            row.innerHTML = `
            <td>${script.title}</td>
            <td>${script.content.substring(0, 50)}...</td>
            <td>${script.createdAt.replace("T", " ")}</td>
            <td>${script.modifiedAt.replace("T", " ")}</td>
            `;
        } else {
            row.innerHTML = `
            <td>${script.title}</td>
            <td>${script.content}</td>
            <td>${script.createdAt.replace("T", " ")}</td>
            <td>${script.modifiedAt.replace("T", " ")}</td>
            `;
        }

        list.appendChild(row);
    });
}

async function getScriptsFromData() {
    const checkBefore = localStorage.getItem("jwtToken");
    const item = JSON.parse(checkBefore);
    const now = new Date();
    if (now.getTime() > item.expiry) {
        localStorage.removeItem("jwtToken");
    }
    const jwtToken = JSON.parse(localStorage.getItem("jwtToken"));
    const token = jwtToken.value;

    return fetch('/speech-scripts/users/me',{headers: {
            'Authorization': `${token}`}})
        .then(response => response.json())
        .then(scripts => scripts)
        .catch(error => console.error('Error loading scripts:', error));
}

function goToScriptDetails(script) {
    window.location.href = `scripts?title=${script.title}&content=${script.content}&modifiedAt=${script.modifiedAt}&createdAt=${script.createdAt}&id=${script.scriptId}`;
}

document.getElementById('create-script-btn').addEventListener('click', () => {
    window.location.href = '/script-write';
});
