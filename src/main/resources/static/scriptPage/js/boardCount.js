document.addEventListener('DOMContentLoaded', loadBoardsCount);

async function loadBoardsCount() {
    const boards = await getBoardsFromData();
    const boardsCount = document.getElementById("user-boards-count");
    if(boardsCount !== null){
        boardsCount.innerHTML = boards;
    }
}

async function getBoardsFromData() {
    const checkBefore = localStorage.getItem("jwtToken");
    const item = JSON.parse(checkBefore);
    const now = new Date();
    if (now.getTime() > item.expiry) {
        localStorage.removeItem("jwtToken");
    }
    const jwtToken = JSON.parse(localStorage.getItem("jwtToken"));
    const token = jwtToken.value;

    return fetch('/api/boards/users/counts/me',{headers: {
            'Authorization': `${token}`}})
        .then(response => response.json())
        .then(boards => boards)
        .catch(error => console.error('Error loading scripts:', error));
}
