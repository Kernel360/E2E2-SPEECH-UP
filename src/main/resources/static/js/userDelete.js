function confirmDelete() {
    const jwtToken =  getItemWithExpiry("jwtToken");
    if (confirm("회원을 탈퇴하시겠습니까?")) {
                fetch("/users/me",{
                    method:"DELETE",
                    headers: {
                        'Authorization': `${jwtToken}`
                    }
                }).then(() => {
                    localStorage.removeItem("jwtToken")
                    window.location.href = "/"
                }).catch((e)=>console.error(e))
                alert("탈퇴가 완료되었습니다.");
            }
}