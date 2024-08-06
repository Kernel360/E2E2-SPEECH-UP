
const list = document.getElementById('analyze-recordings-list');

function displayRecords(records) {
    list.innerHTML = ''; // 기존 내용을 초기화

    records.forEach(record => {
        console.log(record)
        const li = document.createElement('li');
        const url = URL.createObjectURL("file://"+record.audio_path);

        li.innerHTML = `
            <div class="recording-item">
                <audio controls src="${url}"></audio>
                <button onclick="analyzeRecording('${record.audio_path}')">분석하기</button>
            </div>
        `;
        list.appendChild(li);
    });
}

async function loadRecords(scriptId) {
     return await fetch(`/speech-record/${scriptId}`).then(r=>r.json())

}
const scriptId = new URLSearchParams(window.location.search).get('scriptId');

document.addEventListener('DOMContentLoaded', async () => {
    const records = await loadRecords(scriptId);
    displayRecords(records);
});