/*

let mediaRecorder;
let recordedChunks = [];
let recordingUrl;
let isRecording = false;

function startRecording() {
    if (isRecording) return;

    navigator.mediaDevices.getUserMedia({ audio: true })
        .then(initMediaRecorder)
        .catch(error => console.error('녹음 시작 오류:', error));
}

function initMediaRecorder(stream) {
    mediaRecorder = new MediaRecorder(stream);
    mediaRecorder.ondataavailable = handleDataAvailable;
    mediaRecorder.onstop = handleStopRecording;
    mediaRecorder.start();
    setRecordingState(true);
}

function handleDataAvailable(event) {
    if (event.data.size > 0) {
        recordedChunks.push(event.data);
    }
}

function handleStopRecording() {
    const timestamp = new Date().getTime();
    const file = new File(recordedChunks,timestamp+".wav",{ type: 'audio/wav' });
    recordedChunks = [];
    recordingUrl = URL.createObjectURL(file);
    setRecordingState(false);
}

function stopRecording() {
    if (!isRecording) return;
    if (mediaRecorder) {
        mediaRecorder.stop();
    }
}

function setRecordingState(state) {
    isRecording = state;
    document.getElementById('record-btn').textContent = state ? '녹음 중지' : '녹음 추가';
}

document.getElementById('record-btn').addEventListener('click', () => {
    if (isRecording) {
        stopRecording();
    } else {
        startRecording();
    }
}); >>>>>>>>>>>> 향후 직접 녹음 업로드 기능 구현 시 살리기
*/
