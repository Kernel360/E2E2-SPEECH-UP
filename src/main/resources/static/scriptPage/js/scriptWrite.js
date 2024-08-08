document.getElementById('script-form').addEventListener('submit', async function(event) {
    event.preventDefault(); // 폼 제출 기본 동작을 방지

    // 폼 데이터 가져오기
    const title = document.getElementById('script-title').value;
    const content = document.getElementById('script-content').value;

    // 대본 데이터 객체 생성
    const scriptData = {
        title: title,
        content: content,
        createdAt: new Date().toISOString(), // 현재 시간
        modifiedAt: new Date().toISOString(),
        user :{
            user_id : 1,
            name : "ss",
            social_id : "fds",
            password : "121",
            token : "fds",
            address : "fdsfs",
            rank : "fds",
            authorization: "dfsaf"
        }// 현재 시간
    };

    try {
        const response = await fetch('/speech-scripts/users/1', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(scriptData)
        });

        if (response.ok) {
            alert('대본이 성공적으로 저장되었습니다.');
            // 대본 리스트 페이지로 리다이렉트
            window.location.href = '/script-list';
        } else {
            alert('대본 저장 중 오류가 발생했습니다.');
        }
    } catch (error) {
        console.error('Error saving script:', error);
        alert('대본 저장 중 오류가 발생했습니다.');
    }
});
