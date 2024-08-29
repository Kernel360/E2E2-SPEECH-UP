document.getElementById('script-form').addEventListener('submit', async function(event) {
    event.preventDefault(); // 폼 제출 기본 동작을 방지

    // 폼 데이터 가져오기
    const title = document.getElementById('script-title').value;
    const content = document.getElementById('script-content').value;
    const userId = document.getElementById('user-id').value;
    const jwtToken = getItemWithExpiry("jwtToken");
    // 대본 데이터 객체 생성
    const scriptData = {
        title: title,
        content: content,
        user :{
            user_id : userId
        }
    };

    try {
        const response = await fetch(
            '/speech-scripts', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization' : `${jwtToken}`
            },
            body: JSON.stringify(scriptData)
        });

        if (response.ok) {
            alert('대본이 성공적으로 저장되었습니다.');
            window.location.href = '/scripts-list';
        } else {
            alert('대본 저장 중 오류가 발생했습니다.');
        }
    } catch (error) {
        console.error('Error saving script:', error);
        alert('대본 저장 중 오류가 발생했습니다.');
    }
});
