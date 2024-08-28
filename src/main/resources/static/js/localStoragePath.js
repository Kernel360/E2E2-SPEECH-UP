let chosenDirectory = '';
let isDirectoryPickerActive = false;

function chooseDirectory() {
    if (!window.showDirectoryPicker) {
        return alert("이 브라우저는 디렉토리 선택을 지원하지 않습니다.");
    }
    if (isDirectoryPickerActive) {
        console.warn('디렉토리 선택창이 이미 열려 있습니다.');
        return;
    }

    isDirectoryPickerActive = true
    window.showDirectoryPicker()
        .then(directoryHandle => {
            console.log(directoryHandle)
            chosenDirectory = directoryHandle.name;
            document.getElementById('chosen-directory').textContent = `선택된 경로: ${chosenDirectory}`;
            isDirectoryPickerActive = false;
        })
        .catch(error => {
            console.error('디렉토리 선택 오류:', error);
            isDirectoryPickerActive = false;
        });
}
