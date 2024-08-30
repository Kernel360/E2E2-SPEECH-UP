
function saveRecord(listItemId) {
    const formData = new FormData();
    const jwtToken =  getItemWithExpiry("jwtToken");
    formData.append('script', new URLSearchParams(window.location.search).get("content"));
    formData.append('recordId', listItemId);
    const loadingBar = document.getElementById('loading-bar');
    loadingBar.style.height = document.body.scrollHeight + "px";
    loadingBar.style.display = 'block';

    fetch('/api/upload', {
        method: 'POST',
        headers: {
            'Authorization' : `${jwtToken}`
        },
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
    const jwtToken =  getItemWithExpiry("jwtToken");
    const loadingBar = document.getElementById('loading-bar');
    fetch(`/speech-record/${recordId}/analyze`, {
        method: 'PATCH',
        headers: {
            'Authorization' : `${jwtToken}`
        },
    })
    .then(data => {
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

function deleteRecord(recordId) {
    const jwtToken = getItemWithExpiry("jwtToken");
    const request = {
        recordId: recordId
    };

    fetch(`/speech-record/${recordId}`, {
        method: 'PATCH',
        headers: {
            'Authorization': `${jwtToken}`
        },
        body: JSON.stringify(request)
    })
        .then(response => {
            if (!response.ok) {
                return response.json().then(data => {
                    throw new Error(`삭제 요청 실패: ${data.message || response.statusText}`);
                });
            } else {
                alert('삭제가 완료되었습니다.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('삭제 중 오류가 발생했습니다.');
        })
        .finally(() => {
            window.location.reload();
        });
}