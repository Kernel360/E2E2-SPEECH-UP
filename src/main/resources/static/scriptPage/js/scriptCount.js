document.addEventListener('DOMContentLoaded', loadScriptsCount);

async function loadScriptsCount() {
    const scripts = await getScriptsFromData();
    const scriptsCount = document.getElementById("user-scripts-count");
    if(scriptsCount !== null){
        scriptsCount.innerHTML = scripts;
    }
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

    return fetch('/speech-scripts/users/counts/me',{headers: {
            'Authorization': `${token}`}})
        .then(response => response.json())
        .then(scripts => scripts)
        .catch(error => console.error('Error loading scripts:', error));
}
