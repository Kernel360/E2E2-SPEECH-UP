let i = 1;

function addRecordingToList(url, file) {
    const list = document.getElementById('recordings-list');
    const li = document.createElement('li');
    li.id = 'record-' + i;

    // 오디오 플레이어 추가
    const audio = document.createElement('audio');
    audio.controls = true;
    audio.src = url;
    li.appendChild(audio);

    // "분석하기" 버튼 추가
    const analyzeButton = document.createElement('button');
    analyzeButton.textContent = '분석하기';
    analyzeButton.addEventListener('click', () => saveRecord(file, li.id));

    li.appendChild(analyzeButton);
    list.appendChild(li);

    i++;
}