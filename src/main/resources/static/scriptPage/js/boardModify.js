document.addEventListener('DOMContentLoaded', function() {
    const jwtToken =  getItemWithExpiry("jwtToken");

    fetch('/users/me', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization' : `${jwtToken}`
        }
    })
        .then(response => response.json())
        .then(userData => {
            document.getElementById('board-form').addEventListener('submit', function(event) {
                event.preventDefault();

                const boardId = document.getElementById('board-id').name;
                const title = document.getElementById('card-title').value;
                const content = document.getElementById('card-text').value;

                const requestBody = {
                    board_id: boardId,
                    title: title,
                    content: content,
                    user: {
                        user_id: userData.userId,
                    }
                };

                fetch(`/api/boards`, {
                    method: 'PATCH',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization' : `${jwtToken}`
                    },
                    body: JSON.stringify(requestBody)
                })
                    .then(response => response.json())
                    .then(data => {
                        if (data) {
                            console.log(data)
                            alert("게시글이 성공적으로 수정되었습니다.");
                            window.location.href = "/boards";
                        } else {
                            alert("게시글 수정에 실패했습니다.");
                        }
                    })
                    .catch(error => console.error('Error:', error));
            });
        })
        .catch(error => {
            console.error('사용자 정보를 가져오는 데 실패했습니다.', error);
        });
});
