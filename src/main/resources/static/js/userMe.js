document.addEventListener('DOMContentLoaded',starter);

function starter(){
    const jwtToken =  getItemWithExpiry("jwtToken");
    if(jwtToken != null){
        return userMe().then((res)=> res);
    }
    return  showLoggedOutNav();
}

function getItemWithExpiry(key) {
    const itemStr = localStorage.getItem(key);

    if (!itemStr) {
        return null;
    }

    const item = JSON.parse(itemStr);
    const now = new Date();

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
                'Authorization': `${jwtToken}`
            }
        });

        if (response.ok) {
            const userData = await response.json();
            if(userData.authorization === "ROLE_BAN_USER"){
                bannedUser();
            }
            showLoggedInNav(userData);
            myPage(userData);
        }
    } catch (error) {
        console.error('Error fetching user data:', error);
        showLoggedOutNav();
    }
}

function showLoggedInNav(userData) {
    const navButtons = document.getElementById('nav-buttons');
    const scriptForm = document.getElementById('script-form');
    if(scriptForm != null ){
        scriptForm.innerHTML += `
            <input type="text" id="user-id" name="user-id" value=${userData?.userId} hidden="hidden"/>
            <input type="text" id="user-name" name="user-name" value=${userData?.name} hidden="hidden"/>
        `
    }
    navButtons.innerHTML = `
        <a href="/" class="nav-button" >홈</a>
        <a href="/boards" class="nav-button">게시판</a>
        <a href="/scripts-list" class="nav-button">스피치 분석</a>
        <a href="/page/me" class="nav-button">마이페이지</a>
        <a onclick=logout() class="nav-button" id="logout-button">로그아웃</a>
    `;
}

function myPage(userData){
    const myPage = document.getElementById("profile");
    const level = document.getElementById("user-level");
    const authorization = document.getElementById("user-authorization");
    const lastAccessedAt= document.getElementById("user-last-accessed-at");
    if(myPage!== null){
        myPage.innerHTML = `
         <h2 id="user-name">${userData.name}</h2>
         <p id="user-email">${userData.email}</p>
        `
    }
    if(level !== null){
        level.innerHTML = `${userData.level}`
    }
    if(authorization !== null){
        authorization.innerHTML = `${userData.authorization}`
    }
    if(lastAccessedAt !== null){
        lastAccessedAt.innerHTML = `${userData.lastAccessedAt.replace("T", " ")}`
    }
}

function showLoggedOutNav() {
    const navButtons = document.getElementById('nav-buttons');
    navButtons.innerHTML = `
        <a href="/" class="nav-button" >홈</a>
        <a href="/login" class="nav-button">로그인</a>
    `;
}
function bannedUser(){
  window.location.href = "/banned-page";
}

function logout(){
    localStorage.removeItem("jwtToken");
    window.location.href = "/logout";
}
