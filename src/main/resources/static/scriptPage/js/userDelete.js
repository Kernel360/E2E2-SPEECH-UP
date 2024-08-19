function confirmDelete() {
    const jwtToken =  getItemWithExpiry("jwtToken");
    if (confirm("정말 회원을 탈퇴하실 껀가요?")) {
                fetch("/users/me",{
                    method:"DELETE",
                    headers: {
                        'Authorization': `${jwtToken}` // Authorization 헤더 추가
                    }
                }).then(() => {
                    localStorage.removeItem("jwtToken")
                    window.location.href = "/"
                }).catch((e)=>console.error(e))
                alert("탈퇴가 완료되었습니다.");
            }
}