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
    const jwtToken =  getItemWithExpiry("jwtToken");

    const boardId = urlParams[2];
    if(!window.confirm("정말 삭제하시겠습니까?")){
        return;
    }
    fetch('/users/me', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization' : `${jwtToken}`
        }
    })
        .then(response => response.json())
        .then(userData => {
            // 사용자 정보가 성공적으로 로드되면 수정 폼의 submit 이벤트를 처리합니다.


                const title = document.getElementById('board-title').innerHTML;
                const content = document.getElementById('board-text').innerHTML;

                // JSON 데이터 생성
                const requestBody = {
                    board_id: boardId,
                    title: title,
                    content: content,
                    user: {
                        user_id: userData.userId, // /user/me 요청에서 받은 user_id 사용
                    }
                };
                // 서버에 수정 요청 보내기
                fetch(`/api/boards`, {
                    method: 'DELETE',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(requestBody)
                })
                    .then(response => response.json())
                    .then(data => {
                        if (data) {
                            console.log(data)
                            window.location.href = "/boards"; // 목록 페이지로 이동
                        } else {
                            alert("게시글 삭제에 실패했습니다.");
                        }
                    })
                    .catch(error => console.error('Error:', error));
            })
        .catch(error => {
            console.error('사용자 정보를 가져오는 데 실패했습니다.', error);
        });
}