document.addEventListener('DOMContentLoaded',starter);

function starter(){
    const jwtToken =  getItemWithExpiry("jwtToken");
    if(jwtToken != null){
        return checkBanned();
    }
    window.location.href ="/";
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

function checkBanned(){
    const jwtToken =  getItemWithExpiry("jwtToken");

    fetch("/users/banned/me", {
        headers: {
            'Authorization': `${jwtToken}`
        }
    }).then((res) => {
        if(res.ok){
           return alert("관리자에게 문의하세요.")
        }
        window.location.href ="/";
    })
}


