document.getElementById('upload-btn').addEventListener('click', function() {
    const fileInput = document.getElementById('file-input');
    const files = fileInput.files;

    if (files.length === 0) {
        alert('파일을 선택하세요.');
        return;
    }

    const file = files[0];
    const languageCode = document.getElementById('language-select').value;
    const urlParams = new URLSearchParams(window.location.search);
    const scriptId = urlParams.get('id');

    const formData = new FormData();
    formData.append('file', file);
    formData.append('languageCode', languageCode);
    formData.append('scriptId', scriptId);
    fetch('/speech-record', {
        method: 'POST',
        body: formData
    })
        .then(response => response.json())
        .then(data => {
            console.log('업로드 성공:', data);
            const url = URL.createObjectURL(file);
            addRecordingToList(url, scriptId);
        })
        .catch(error => {
            console.error('업로드 실패:', error);
        });
});