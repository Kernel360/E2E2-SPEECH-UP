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
            // 사용자 정보가 성공적으로 로드되면 수정 폼의 submit 이벤트를 처리합니다.
            document.getElementById('board-form').addEventListener('submit', function(event) {
                event.preventDefault(); // 기본 폼 제출 방지

                const title = document.getElementById('card-title').value;
                const content = document.getElementById('card-text').value;

                // JSON 데이터 생성
                const requestBody = {
                    title: title,
                    content: content,
                    user: {
                        user_id: userData.userId, // /user/me 요청에서 받은 user_id 사용
                    }
                };

                // 서버에 수정 요청 보내기
                fetch(`/api/boards`, {
                    method: 'PATCH',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(requestBody)
                })
                    .then(response => response.json())
                    .then(data => {
                        if (data) {
                            console.log(data)
                            alert("게시글이 성공적으로 수정되었습니다.");
                            window.location.href = "/boards"; // 목록 페이지로 이동
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
