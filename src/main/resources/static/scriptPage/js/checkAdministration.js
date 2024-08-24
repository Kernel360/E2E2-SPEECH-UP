document.addEventListener('DOMContentLoaded',checkAdministration )

function checkAdministration(){
    const jwtToken =  getItemWithExpiry("jwtToken");
    fetch(`/api/admin/me`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization' : `${jwtToken}`
        }
    }).then((res) => {
        if(res.status === 403){
            alert("권한 없습니다.");
            window.location.href="/"
        }
    }).catch(()=>window.location.href="/login");
}