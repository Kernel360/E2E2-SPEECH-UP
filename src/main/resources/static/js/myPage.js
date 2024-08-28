document.addEventListener('DOMContentLoaded',myPage);

function myPage(){
    const jwtToken =  getItemWithExpiry("jwtToken");
    if(!jwtToken){
        window.location.href="/login";
    }
}
