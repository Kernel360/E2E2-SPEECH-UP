function saveRecord() {
    const formData = new FormData();
    const urlParams = new URLSearchParams(window.location.search);

    const fileInput = document.getElementById('input-file');
    const myFile = fileInput.files[0];

    if (!myFile) {
        alert('파일을 선택해주세요.');
        return;
    }

    formData.append('myFile', myFile);
    formData.append('requestId', "reserved field"); // 실제 requestId로 대체 필요
    formData.append('apiType', "PRONUNCIATION");
    formData.append('languageCode', document.getElementById('language-select').value);
    formData.append('script', urlParams.get("content"));

    const button = document.querySelector(".saving-button");
    const report = document.getElementById('record-result');
    fetch('/api/upload', {
        method: 'POST',
        body: formData
    })
        .then(response => {
            button.disabled = true;
            button.textContent = "분석 완료";
            return response.json()
        }).then((response) => {

            report.innerHTML = response?.return_object?.score;
    })
        .catch(error => {
            console.error('API 호출 오류:', error.message);
        });
}