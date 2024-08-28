document.addEventListener('DOMContentLoaded', init);

let jwtToken;

function init(){
    jwtToken = getItemWithExpiry("jwtToken");
    adminGetUser();
}

function adminGetUser() {
    const jwtToken = getItemWithExpiry("jwtToken");  // jwtToken 가져오기

    fetch(`/api/admin/user/all`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `${jwtToken}`
        }
    }).then((res) => {
        if (res.status === 403) {
            alert("권한 없습니다.");
            window.location.href = "/";
        } else {
            return res.json();  // JSON 데이터를 반환합니다.
        }
    }).then(users => {
        if (users) {
            const tableBody = document.getElementById('userTableBody');
            users.forEach(user => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${user.userId}</td>
                    <td>${user.name}</td>
                    <td>${user.authorization.split("_")[1]}</td>
                    <td>${user.email}</td>
                    <td>${user.use ? "사용 중" : "정지됨"}</td>
                    <td>${new Date(user.lastAccessedAt).toLocaleString()}</td>
                    <td>
                        <div class="handler">
                            <button class="action-btn delete-btn mr-1" onclick="deleteUser(${user.userId})">삭제</button>
                            <button class="${user.use ? 'action-btn suspend-btn' : 'action-btn use-btn'}" 
                                onclick="${user.use ? `suspendUser(${user.userId})` : `restoreUser(${user.userId})`}">
                                ${user.use ? "정지" : "시작"}
                            </button>
                        </div>
                    </td>
                `;
                tableBody.appendChild(row);
            });
        }
    }).catch(() => window.location.href = "/login");
}

function deleteUser(userId){
    if(!confirm("정말 삭제하시겠습니까?")) return;
    fetch(`/api/admin/drop/${userId}`,{
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `${jwtToken}`
        }
    }).then((res)=>{
        if(res.status === 403){
            alert("권한 없습니다.");
            window.location.href = "/";
        }
        if(res.ok){
            window.location.reload();
        }
    })
}

function suspendUser(userId){
    if(!confirm("정말 정지시키겠습니까?")) return;
    fetch(`/api/admin/${userId}`,{
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `${jwtToken}`
        }
    }).then((res)=>{
        if(res.status === 403){
            alert("권한 없습니다.");
            window.location.href = "/";
        }
        if(res.ok){
            window.location.reload();
        }
    })
}

function restoreUser(userId){
    if(!confirm("정말 복구시키겠습니까?")) return;
    fetch(`/api/admin/${userId}`,{
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `${jwtToken}`
        }
    }).then((res)=>{
        if(res.status === 403){
            alert("권한 없습니다.");
            window.location.href = "/";
        }
        if(res.ok){
            window.location.reload();
        }
    })
}