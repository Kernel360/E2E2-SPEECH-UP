document.addEventListener('DOMContentLoaded', function() {
    const targetNode = document.getElementById('script-form'); // 대상 요소의 부모 ID

    const config = { childList: true, subtree: true };

    const callback = function(mutationsList, observer) {
        for(const mutation of mutationsList) {
            if (mutation.type === 'childList') {
                const commentElement = document.getElementById('user-id'); // 특정 요소 ID
                if (commentElement) {
                    getComment(); // 요소가 발견되면 함수 실행
                    observer.disconnect(); // 필요 없으면 감시 중지
                }
            }
        }
    };

    // MutationObserver 생성 및 감시 시작
    const observer = new MutationObserver(callback);
    observer.observe(targetNode, config);
});



async function getComment(){
    const boardId = document.getElementById('board-id').value;
    const commentList = document.getElementById('comment-list');
    const userId = document.getElementById('user-id')?.value;
    await fetch(
        `/replies/${boardId}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            }
        }).then((data)=>data.json()).then((replies)=>{
        replies.forEach((reply)=>{
            let formattedTime = reply.modified_at.replace('T', ' ');
            commentList.innerHTML +=  `
            <div class="card mt-3" >
                <div class="card-body" id=reply-content-${reply.reply_id}>  
                    <h5 class="card-title">${reply.name}</h5>
                    <p class="card-text" id=reply-content-${reply.reply_id}>${reply.content}</p>
                    <p class="card-text">
                        <small class="text-muted">
                            Last updated <span class="relative-time" data-time="${reply.modified_at}">${formattedTime}</span>
                        </small>
                    </p>
                </div>
                  ${Number(userId) === reply.user_id ?
                `
                    <div class="btn-group" role="group" aria-label="Comment actions">
                        <button class="btn btn-primary btn-sm" onclick="editReply(${reply.reply_id})">수정하기</button>
                        <button class="btn btn-danger btn-sm" onclick="deleteReply(${reply.reply_id}, ${reply.user_id}, '${reply.name}', ${reply.board_id})">삭제하기</button>
                    </div>
                 `
                :""}
            <!-- 숨겨진 수정 폼 -->
                <div id="edit-form-${reply.reply_id}" style="display:none;">
                    <textarea id="edit-content-${reply.reply_id}" class="form-control mb-2">${reply.content}</textarea>
                    <button class="btn btn-success btn-sm" onclick="saveReply(${reply.reply_id}, ${reply.user_id}, '${reply.name}', ${reply.board_id})">저장</button>
                    <button class="btn btn-secondary btn-sm" onclick="cancelEdit(${reply.reply_id})">취소</button>
                </div>
            </div>
            
              
            `
        })
    })
}


async function addComment(){
    const content = document.getElementById('comment-text').value;
    const userId = document.getElementById('user-id')?.value;
    const name = document.getElementById('user-name')?.value;
    const boardId = document.getElementById('board-id')?.value;
    const body ={
        "user":{
            "user_id":userId,
            "name":name
        },
        "board":{
            "board_id":boardId
        },
        "content":
        content
    }
    const response =await fetch(
        '/replies', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(body)
        });
    if (response.ok) {
        // 대본 리스트 페이지로 리다이렉트
        window.location.reload();
    }else{
        alert("로그인 해주세요")
        window.location.href= "/login";
    }
}


async function saveReply(replyId, userId, name, boardId) {
    // 수정된 내용을 가져옴
    const editedContent = document.getElementById(`edit-content-${replyId}`).value;
    const body ={
        "reply_id":replyId,
        "user":{
            "user_id":userId,
            "name":name
        },
        "board":{
            "board_id":boardId
        },
        "content":
        editedContent
    }

    await fetch('/replies', {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(body)
    }).then(() => {
        document.getElementById(`reply-content-${replyId}`).textContent = editedContent;
        cancelEdit(replyId);
        }
    ).catch((e) => console.error(e))
}


function editReply(replyId) {
    // 댓글 내용을 숨기고 수정 폼을 보여줌
    document.getElementById(`reply-content-${replyId}`).style.display = 'none';
    document.getElementById(`edit-form-${replyId}`).style.display = 'block';
}

function cancelEdit(replyId) {
    // 댓글 내용을 다시 보여주고 수정 폼을 숨김
    document.getElementById(`reply-content-${replyId}`).style.display = 'block';
    document.getElementById(`edit-form-${replyId}`).style.display = 'none';
}


async function deleteReply(replyId, userId, name, boardId){
    if(!window.confirm("정말 삭제하시겠습니까?")) return;
    const content = document.getElementById(`reply-content-${replyId}`).value;
    const body ={
        "reply_id":replyId,
        "user":{
            "user_id":userId,
            "name":name
        },
        "board":{
            "board_id":boardId
        },
        "content":content,
        "id_user":false

    }

    await fetch('/replies', {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(body)
    }).then(() => {
           window.location.reload();
        }
    ).catch((e) => console.error(e))
}