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
