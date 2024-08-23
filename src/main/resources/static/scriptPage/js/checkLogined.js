document.addEventListener('DOMContentLoaded',checkLogined);

function checkLogined(){
    const jwtToken =  getItemWithExpiry("jwtToken");
    const writeBtn  = document.getElementById("board-buttons");
    if(jwtToken){
        writeBtn.innerHTML +=
            `
                    <a class="btn btn-primary mt-3" href="/boards/write">작성하기</a>
            `
    }
}