// 파일을 서버로 전송하고 분석 결과를 처리하는 함수
function saveRecord(listItemId) {
    const formData = new FormData();
    formData.append('script', new URLSearchParams(window.location.search).get("content"));
    formData.append('recordId', listItemId);
    const loadingBar = document.getElementById('loading-bar');
    loadingBar.style.height = document.body.scrollHeight + "px";
    loadingBar.style.display = 'block';

    fetch('/api/upload', {
        method: 'POST',
        body: formData
    })
        .then(() => {
            // 분석 결과를 화면에 표시
            isAnalyze(listItemId);
        })
        .catch(error => {
            console.error('분석 중 오류가 발생했습니다:', error);
        })
}

function isAnalyze(recordId) {
    // 로딩바 표시
    const loadingBar = document.getElementById('loading-bar');
    fetch(`/speech-record/${recordId}/analyze`, {
        method: 'PATCH'
    }).then(data => {
            // 서버에서 응답한 데이터로 작업을 수행
            console.log('Analysis status updated:', data);
        })
        .catch(error => {
            console.error('Error:', error);
        })
        .finally(() => {
            // 요청이 끝나면 로딩바를 숨김
            loadingBar.style.display = 'none';
            window.location.reload();
        });
}