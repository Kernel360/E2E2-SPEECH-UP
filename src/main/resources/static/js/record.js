let mediaRecorder;
let recordedChunks = [];
let recordingUrl;
let chosenDirectory = '';
let isRecording = false; // 녹음 상태를 나타내는 변수

// 녹음 시작
function startRecording() {
    if (isRecording) return; // 이미 녹음 중이면 함수를 종료

    navigator.mediaDevices.getUserMedia({ audio: true })
        .then(stream => {
            mediaRecorder = new MediaRecorder(stream);
            mediaRecorder.ondataavailable = event => {
                if (event.data.size > 0) {
                    recordedChunks.push(event.data);
                }
            };
            mediaRecorder.onstop = () => {
                const blob = new Blob(recordedChunks, { type: 'audio/wav' });
                recordedChunks = [];
                recordingUrl = URL.createObjectURL(blob);
                addRecordingToList(recordingUrl, blob);
                isRecording = false; // 녹음 종료 상태로 변경
                document.getElementById('record-btn').textContent = '녹음 추가';
            };
            mediaRecorder.start();
            isRecording = true; // 녹음 시작 상태로 변경
            document.getElementById('record-btn').textContent = '녹음 중지';
        })
        .catch(error => {
            console.error('녹음 시작 오류:', error);
        });
}

// 녹음 중지
function stopRecording() {
    if (!isRecording) return; // 녹음 중이 아닐 때는 함수를 종료

    if (mediaRecorder) {
        mediaRecorder.stop();
    }
}

// 녹음 항목 추가
function addRecordingToList(url, blob) {
    const list = document.getElementById('recordings-list');
    const li = document.createElement('li');
    li.innerHTML = `
        <div class="recording-item">
            <audio controls src="${url}"></audio>
            <button onclick="analyzeAudio(${JSON.stringify(blob)})">분석하기</button>
        </div>
    `;
    list.appendChild(li);
}

// 오디오 분석
function analyzeAudio(blob) {
    // blob 을 FormData 에 직접 추가
    console.log(typeof blob);
    const formData = new FormData();
    formData.append('script', document.getElementById('script-text').value);
    formData.append('audioPath', chosenDirectory); // 선택된 경로를 사용
    formData.append('languageCode', document.getElementById('language-select').value);
    formData.append('audio', blob, 'audio.wav');

    fetch('/speech-record', {
        method: 'POST',
        body: formData,
    })
        .then(response => response.json())
        .then(data => {
            alert(`분석 결과: ${data.result}`);
            document.getElementById('view-analysis-btn').classList.remove('hidden');

            // 분석 결과를 새로운 창에 표시
            const newWindow = window.open('', '_blank');
            newWindow.document.write(`<html><head><title>분석 결과</title></head><body><pre>${JSON.stringify(data, null, 2)}</pre></body></html>`);
        })
        .catch(error => {
            console.error('API 호출 오류:', error);
            alert('분석 실패');
        });
}

let isDirectoryPickerActive = false; // 디렉토리 선택 상태 추적 변수

// 로컬 저장소 선택
function chooseDirectory() {
    // 파일 시스템 접근을 지원하는 브라우저에서만 동작
    if (window.showDirectoryPicker) {
        if (isDirectoryPickerActive) {
            console.warn('디렉토리 선택창이 이미 열려 있습니다.');
            return; // 이미 디렉토리 선택창이 열려 있는 경우 함수 종료
        }

        isDirectoryPickerActive = true; // 디렉토리 선택창 열림 상태 설정

        window.showDirectoryPicker()
            .then(directoryHandle => {
                chosenDirectory = directoryHandle.name;
                document.getElementById('chosen-directory').textContent = `선택된 경로: ${chosenDirectory}`;
                isDirectoryPickerActive = false; // 디렉토리 선택 완료 후 상태 초기화
            })
            .catch(error => {
                console.error('디렉토리 선택 오류:', error);
                if (error.name === 'AbortError') {
                    console.log('디렉토리 선택이 취소되었습니다.');
                } else {
                    alert('디렉토리 선택에 실패했습니다. 오류: ' + error.message);
                }
                isDirectoryPickerActive = false; // 오류 발생 후 상태 초기화
            });
    } else {
        alert('이 브라우저는 디렉토리 선택을 지원하지 않습니다.');
    }
}

// 페이지 로드 시 버튼 클릭 이벤트 설정
document.getElementById('record-btn').addEventListener('click', () => {
    if (isRecording) {
        stopRecording();
    } else {
        startRecording();
    }
});

document.getElementById('choose-directory-btn').addEventListener('click', chooseDirectory);