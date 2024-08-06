
function addRecordingToList(url, file) {
    const list = document.getElementById('recordings-list');
    const li = document.createElement('li');

    // Create a new button and set up the event listener
    const button = document.createElement('button');
    button.textContent = '저장하기';
    button.classList.add("saving-button");
    button.addEventListener('click', () => saveRecord(file));

    li.innerHTML = `
                <div class="recording-item">
                    <audio controls src="${url}"></audio>
                </div>
            `;
    li.appendChild(button);
    list.appendChild(li);

}