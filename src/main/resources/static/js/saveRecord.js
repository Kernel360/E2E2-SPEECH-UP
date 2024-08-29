const jwtToken = getItemWithExpiry("jwtToken");

function saveRecord(recordId) {
    const formData = new FormData();
    formData.append('script', new URLSearchParams(window.location.search).get("content"));
    formData.append('recordId', recordId);
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
            isAnalyze(recordId);
        })
        .catch(error => {
            alert('분석 중 오류가 발생했습니다');
            console.log(error);
        })
}

function isAnalyze(recordId) {
    // 로딩바 표시
    const loadingBar = document.getElementById('loading-bar');
    fetch(`/speech-record/${recordId}/analyze`, {
        method: 'PATCH',
        headers: {
            'Authorization' : `${jwtToken}`
        },
    }).then(() => {
            alert('분석이 완료되었습니다.:');
        })
        .catch(() => {
            alert('분석중 오류가 발생했습니다. 잠시 후 다시 시도해주세요.');
        })
        .finally(() => {
            loadingBar.style.display = 'none';
            window.location.reload();
        });
}

function deleteRecord(recordId){
    const confirmed = confirm('삭제하시겠습니까?');
    if (confirmed) {
        fetch(`/speech-record/${recordId}`, {
            method: 'PATCH',
            headers: {
                'Authorization' : `${jwtToken}`
            },
            body: JSON.stringify(recordId)
        }).then(response => {
            if (!response.ok) {
                throw new Error('네트워크 응답이 올바르지 않습니다.');
            }
            alert('녹음이 삭제 되었습니다.');
        })
            .catch(error => {
                alert('삭제에 실패했습니다.');
                console.error('Error:', error);
            })
            .finally(() => {
                window.location.reload();
            });
    }
}