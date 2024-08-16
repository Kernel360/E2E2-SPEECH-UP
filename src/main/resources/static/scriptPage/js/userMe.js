document.addEventListener('DOMContentLoaded',starter);

function starter(){
    const jwtToken =  getItemWithExpiry("jwtToken");
    if(jwtToken != null){
        return userMe().then((res)=> res.json()).catch((e) => console.error(e))
    }
    return  showLoggedOutNav();
}

function getItemWithExpiry(key) {
    const itemStr = localStorage.getItem(key);

    // 항목이 존재하지 않으면 null을 반환합니다.
    if (!itemStr) {
        return null;
    }

    const item = JSON.parse(itemStr);
    const now = new Date();

    // 항목이 만료된 경우, localStorage에서 항목을 제거하고 null을 반환합니다.
    if (now.getTime() > item.expiry) {
        localStorage.removeItem(key);
        return null;
    }

    return item.value;
}
async function userMe() {
   const jwtToken =  getItemWithExpiry("jwtToken");
    try {
        const response = await fetch("/users/me", {
            headers: {
                'Authorization': `${jwtToken}` // Authorization 헤더 추가
            }
        });

        if (response.ok) {
            const userData = await response.json();
            // 사용자 정보가 성공적으로 받아지면, 네비게이션 바에 로그인이 되어있음을 표시
            showLoggedInNav(userData);
        }
    } catch (error) {
        console.error('Error fetching user data:', error);
        showLoggedOutNav(); // 네트워크 오류 등으로 인한 실패 시 로그아웃 상태로 처리
    }
}

function showLoggedInNav(userData) {
    const navButtons = document.getElementById('nav-buttons');
    const scriptForm = document.getElementById('script-form');
    if(scriptForm != null ){
        scriptForm.innerHTML += `
            <input type="text" id="user-id" name="user-id" value=${userData?.userId} hidden="hidden"/>
        `
    }
    navButtons.innerHTML = `
        <a href="/" class="nav-button">마이페이지</a>
        <a onclick=logout('/logout') class="nav-button" id="logout-button">로그아웃</a>
        <a onclick=navigateWithAuth('/script-list') class="nav-button">스피치 분석</a>
        <a onclick=navigateWithAuth('/boards') class="nav-button">게시판</a>
       
    `;
}

function showLoggedOutNav() {
    const navButtons = document.getElementById('nav-buttons');
    navButtons.innerHTML = `
        <a href="/login" class="nav-button">로그인</a>
        <a onclick=navigateWithAuth('/boards') class="nav-button">게시판</a>
    `;
}
function logout(url){
    localStorage.removeItem("jwtToken");
    window.location.href = "/logout";
}

function navigateWithAuth(url) {
    const jwtToken =  getItemWithExpiry("jwtToken");
    const headers = new Headers({
        'Authorization': `${jwtToken}`
    });

    // 요청 생성 및 전송
    fetch(url, {
        method: 'GET',
        headers: headers
    })
        .then(response => {
            if (response.ok) {
                // 페이지를 새 창에서 열기
                window.location.href = url;
            } else {
                window.location.href = "/login";
                console.error('Authorization failed.');
            }
        })
        .catch(error => console.error('Error:', error));
}
