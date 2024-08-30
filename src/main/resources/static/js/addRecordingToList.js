
function addRecordingToList(url, recordId) {
    const list = document.getElementById('recordings-list');
    const li = document.createElement('li');
    const audio = document.createElement('audio');

    audio.controls = true;
    audio.src = url;
    li.appendChild(audio);

    const deleteButton = document.createElement('button');

    deleteButton.setAttribute('id', 'delete-button');
    deleteButton.setAttribute('style', 'background-color: darkred')
    deleteButton.textContent = '삭제하기';
    deleteButton.addEventListener('click', () => deleteRecord(recordId));
    console.log(recordId);
    const analyzeButton = document.createElement('button');
    
    analyzeButton.setAttribute('id', 'analyze-button');
    analyzeButton.textContent = '분석하기';
    analyzeButton.addEventListener('click', () => saveRecord(recordId));

    li.appendChild(analyzeButton);
    li.appendChild(deleteButton);
    list.appendChild(li);

}