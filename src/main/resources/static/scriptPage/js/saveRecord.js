
// 오디오 분석
function saveRecord(blob) {
    // blob 을 FormData 에 직접 추가
    const urlParams = new URLSearchParams(window.location.search);
    const content =  document.getElementById('script-content').innerHTML;
    const directory = "/Users/leekangmin/Documents/E2E/E2E2-SPEECH-UP"; // 개인 아이디로 변경

    const formData = new FormData();
    const body = {script_id : urlParams.get('scriptId') };
    const jsonBody = JSON.stringify(body);
    formData.append('script', jsonBody);
    formData.append('audioPath', directory);
    formData.append('languageCode', document.getElementById('language-select').value);
    formData.append('file', blob, blob.name);
    const button = document.getElementsByClassName("saving-button");
    fetch('/speech-record', {
        method: 'POST',
        body: formData
    })
        .then(response => {
            if (response.ok) {
                button.item(0).setAttribute('disabled', 'true');
                button.item(0).textContent = "저장완료"
            }
        })
        .catch(error => {
            console.error('API 호출 오류:', error);
            alert('분석 실패');
        });
}