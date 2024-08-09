document.addEventListener('DOMContentLoaded', loadScripts);

async function loadScripts() {
    const scripts = await getScriptsFromData();

    const list = document.getElementById('scripts-list');
    list.innerHTML = ''; // 기존 리스트 초기화

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

    return fetch('/speech-scripts/users/me')
        .then(response => response.json())
        .then(scripts => scripts)
        .catch(error => console.error('Error loading scripts:', error));
}

function goToScriptDetails(script) {
  window.location.href = `script?title=${script.title}&content=${script.content}&modifiedAt=${script.modifiedAt}&createdAt=${script.createdAt}`;
}

document.getElementById('create-script-btn').addEventListener('click', () => {
    window.location.href = '/script-write';
});
