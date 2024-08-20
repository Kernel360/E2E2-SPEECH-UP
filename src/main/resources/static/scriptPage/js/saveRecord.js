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
            console.log('Analysis status updated:', data);
        })
        .catch(error => {
            console.error('Error:', error);
        })
        .finally(() => {
            loadingBar.style.display = 'none';
            window.location.reload();
        });
}