document.addEventListener('DOMContentLoaded', loadScriptDetail);

function loadScriptDetail() {
    const urlParams = new URLSearchParams(window.location.search);
    const scriptId = urlParams.get('scriptId');
    const content = urlParams.get('content');
    const createdAt = urlParams.get('createdAt');
    const modifiedAt = urlParams.get('modifiedAt');

    const detailDiv = document.getElementById('script-detail');
    detailDiv.innerHTML = `
        <p><strong>제목:</strong> 스크립션 넘버 ${scriptId}</p>
        <p id="script-content">${decodeURIComponent(content)}</p>
        <p><strong>작성일:</strong> ${createdAt}</p>
        <p><strong>수정일:</strong> ${modifiedAt}</p>
    `;
}

function goBack() {
    window.history.back();
}
