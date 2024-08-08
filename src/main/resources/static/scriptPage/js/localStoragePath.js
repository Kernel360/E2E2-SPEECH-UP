let chosenDirectory = '';
let isDirectoryPickerActive = false; // 디렉토리 선택 상태 추적 변수

// 로컬 저장소 선택
function chooseDirectory() {
    // 파일 시스템 접근을 지원하는 브라우저에서만 동작
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
// 디렉토리 선택 버튼 클릭 이벤트 핸들러
// document.getElementById('choose-directory-btn').addEventListener('click', chooseDirectory);
