document.addEventListener('DOMContentLoaded', loadScriptDetail);

function loadScriptDetail() {
    const urlParams = new URLSearchParams(window.location.search);
    const title = urlParams.get('title');
    const content = urlParams.get('content');
    const createdAt = urlParams.get('createdAt');
    const modifiedAt = urlParams.get('modifiedAt');

    const detailDiv = document.getElementById('script-detail');
    detailDiv.innerHTML = `
        <p><strong>작성일 : </strong> ${createdAt.replace("T", " ")} /<strong> 수정일 : </strong> ${modifiedAt.replace("T", " ")}</p>
        <p><strong>제목 : </strong> ${title}</p>
        <p id="script-content">${decodeURIComponent(content)}</p>
    `;
}

function goBack() {
    window.history.back();
}

document.querySelector('.help-icon').addEventListener('click', function() {
    document.getElementById('tooltip-modal').style.display = 'block';
});

document.querySelector('.close-btn').addEventListener('click', function() {
    document.getElementById('tooltip-modal').style.display = 'none';
});

window.addEventListener('click', function(event) {
    const modal = document.getElementById('tooltip-modal');
    if (event.target === modal) {
        modal.style.display = 'none';
    }
});

const fileInput = document.getElementById('file-input');
const uploadBtn = document.getElementById('upload-btn');

function showSection(sectionId) {
    const sections = document.querySelectorAll('.section');
    sections.forEach(section => {
        section.style.display = 'none';
    });
    document.getElementById(sectionId).style.display = 'block';
}
showSection('script-and-record-section');

fileInput.addEventListener('change', function() {
    const file = fileInput.files[0];
    if (file) {
        const fileType = file.type;
        if (fileType === 'audio/wav') {
            uploadBtn.disabled = false;
        } else {
            uploadBtn.disabled = true;
            alert('.wav 파일만 업로드 가능합니다.');
        }
    } else {
        uploadBtn.disabled = true;
    }
});