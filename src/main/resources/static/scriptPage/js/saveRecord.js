// 파일을 서버로 전송하고 분석 결과를 처리하는 함수
function saveRecord(listItemId) {
    const formData = new FormData();
    formData.append('script', new URLSearchParams(window.location.search).get("content"));
    formData.append('recordId', listItemId);
    fetch('/api/upload', {
        method: 'POST',
        body: formData
    })
        .then(response => response.json())
        .then(response => {
            // 분석 결과를 화면에 표시
            const resultElement = document.createElement('p');
            resultElement.textContent = `분석 결과: ${response.return_object?.score}`;
            document.getElementById(listItemId).appendChild(resultElement);

            alert('분석이 완료되었습니다.');
        })
        .catch(error => {
            console.error('분석 중 오류가 발생했습니다:', error);
        });
}