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

        row.innerHTML = `
            <td>스크립션 넘버 ${script.scriptId}</td>
            <td>${script.content.substring(0, 50)}...</td>
            <td>${script.createdAt}</td>
            <td>${script.modifiedAt}</td>
        `;

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
  window.location.href = `script?scriptId=${script.scriptId}&content=${script.content}&modifiedAt=${script.modifiedAt}&createdAt=${script.createdAt}`;
}

document.getElementById('create-script-btn').addEventListener('click', () => {
    window.location.href = '/script-write';
});
