document.addEventListener('DOMContentLoaded', loadReplyCount);

async function loadReplyCount() {
    const replies = await getRepliesFromData();
    const repliesCount = document.getElementById("user-replies-count");
    if(repliesCount !== null){
        repliesCount.innerHTML = replies;
    }
}

async function getRepliesFromData() {
    const checkBefore = localStorage.getItem("jwtToken");
    const item = JSON.parse(checkBefore);
    const now = new Date();
    if (now.getTime() > item.expiry) {
        localStorage.removeItem("jwtToken");
    }
    const jwtToken = JSON.parse(localStorage.getItem("jwtToken"));
    const token = jwtToken.value;

    return fetch('/replies/users/count/me',{headers: {
            'Authorization': `${token}`}})
        .then(response => response.json())
        .then(replies => replies)
        .catch(error => console.error('Error loading scripts:', error));
}
