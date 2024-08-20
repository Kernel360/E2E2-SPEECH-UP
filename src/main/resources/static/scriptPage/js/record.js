
let mediaRecorder;
let recordedChunks = [];
let recordingUrl;
let isRecording = false;

// 녹음 시작
function startRecording() {
    if (isRecording) return;

    navigator.mediaDevices.getUserMedia({ audio: true })
        .then(initMediaRecorder)
        .catch(error => console.error('녹음 시작 오류:', error));
}

// 미디어 레코더 초기화
function initMediaRecorder(stream) {
    mediaRecorder = new MediaRecorder(stream);
    mediaRecorder.ondataavailable = handleDataAvailable;
    mediaRecorder.onstop = handleStopRecording;
    mediaRecorder.start();
    setRecordingState(true);
}

// 녹음 데이터 처리
function handleDataAvailable(event) {
    if (event.data.size > 0) {
        recordedChunks.push(event.data);
    }
}

// 녹음 중지 처리
function handleStopRecording() {
    const timestamp = new Date().getTime();
    const file = new File(recordedChunks,timestamp+".wav",{ type: 'audio/wav' });
    recordedChunks = [];
    recordingUrl = URL.createObjectURL(file);
    setRecordingState(false);
}

// 녹음 중지
function stopRecording() {
    if (!isRecording) return;
    if (mediaRecorder) {
        mediaRecorder.stop();
    }
}

// 녹음 항목 추가


// 녹음 상태 설정
function setRecordingState(state) {
    isRecording = state;
    document.getElementById('record-btn').textContent = state ? '녹음 중지' : '녹음 추가';
}

/*// 페이지 로드 시 버튼 클릭 이벤트 설정
document.getElementById('record-btn').addEventListener('click', () => {
    if (isRecording) {
        stopRecording();
    } else {
        startRecording();
    }
});*/
