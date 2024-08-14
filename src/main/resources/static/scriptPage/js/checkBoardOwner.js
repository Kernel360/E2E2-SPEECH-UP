document.addEventListener('DOMContentLoaded',checkBoardOwner);

function checkBoardOwner(){
    const jwtToken =  getItemWithExpiry("jwtToken");
    const urlParams = window.location.pathname.split("/");
    fetch(`/api/boards/${urlParams[2]}`,{headers: {
            'Authorization': `${jwtToken}`}})
        .then(response => response.json())
        .then(board =>  showDeleteAndEdit(board))
        .catch(error => console.error('Error loading scripts:', error));
}

function showDeleteAndEdit(board){

    const boardButtons = document.getElementById('board-buttons');
    const urlParams = window.location.pathname.split("/");
    const boardId = urlParams[2];
    if(board?.owner === true){
        boardButtons.innerHTML += `
            <a onclick=navigateWithAuth('/boards/${boardId}/edit') class="btn btn-primary mt-3">수정하기</a>
            <a onclick=deleteBoard() class="btn btn-primary mt-3">삭제하기</a>
        `;
    }
}

function deleteBoard(){
    const urlParams = window.location.pathname.split("/");
    const boardId = urlParams[2];
    console.log(boardId + "삭제");
}