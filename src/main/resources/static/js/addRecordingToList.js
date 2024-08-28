
function addRecordingToList(url, recordId) {
    const list = document.getElementById('recordings-list');
    const li = document.createElement('li');
    const audio = document.createElement('audio');

    audio.controls = true;
    audio.src = url;
    li.appendChild(audio);

    const analyzeButton = document.createElement('button');
    
    analyzeButton.setAttribute('id', 'analyze-button');
    analyzeButton.textContent = '분석하기';
    analyzeButton.addEventListener('click', () => saveRecord(recordId));

    li.appendChild(analyzeButton);
    list.appendChild(li);

}