document.getElementById('upload-btn').addEventListener('click', function() {
    const fileInput = document.getElementById('file-input');
    const files = fileInput.files;

    if (files.length === 0) {
        alert('파일을 선택하세요.');
        return;
    }

    const file = files[0];
    const url = URL.createObjectURL(file);
    addRecordingToList(url, file);
});



