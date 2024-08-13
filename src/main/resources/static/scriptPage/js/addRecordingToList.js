let i = 1;

function addRecordingToList(url, file) {
    const list = document.getElementById('recordings-list');
    const li = document.createElement('li');


    // Number(i)
    li.id = 'record-' + i;
    // Create a new button and set up the event listener
    li.innerHTML = `
                <div class="recording-item">
                    <audio controls src="${url}"></audio>
                   
                </div>
            `;
    const button = document.createElement('button');

    button.textContent = '분석하기';
    button.classList.add("saving-button");
    button.addEventListener('click', () => saveRecord(file, li.id))
    li.appendChild(button);
    list.appendChild(li);

    i++;

}