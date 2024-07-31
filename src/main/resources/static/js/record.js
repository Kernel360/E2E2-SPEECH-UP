document.addEventListener("DOMContentLoaded", function() {
    const startRecordButton = document.getElementById("startRecordButton");
    const stopRecordButton = document.getElementById("stopRecordButton");
    const scriptForm = document.getElementById("scriptForm");
    const audioFileInput = document.getElementById("audioFile");

    let mediaRecorder;
    let audioChunks = [];

    startRecordButton.addEventListener("click", async () => {
        const stream = await navigator.mediaDevices.getUserMedia({ audio: true });
        mediaRecorder = new MediaRecorder(stream);

        mediaRecorder.ondataavailable = event => {
            audioChunks.push(event.data);
        };

        mediaRecorder.onstop = () => {
            const audioBlob = new Blob(audioChunks, { type: 'audio/wav' });
            const audioFile = new File([audioBlob], 'recording.wav', { type: 'audio/wav' });

            const dataTransfer = new DataTransfer();
            dataTransfer.items.add(audioFile);
            audioFileInput.files = dataTransfer.files;
        };

        mediaRecorder.start();
        startRecordButton.disabled = true;
        stopRecordButton.disabled = false;
    });

    stopRecordButton.addEventListener("click", () => {
        mediaRecorder.stop();
        startRecordButton.disabled = false;
        stopRecordButton.disabled = true;
        audioChunks = [];
    });
});