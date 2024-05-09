const accountCancellation = document.getElementById('cancel-register');

accountCancellation.addEventListener('click',()=>{
        const email = document.getElementById('useremail').value;
        if(confirm('정말 탈퇴하시겠습니까?')==true){
            fetch(`/memberinfo/${email}/delete`,{method:'DELETE'}).then(()=>{
                alert('탈퇴되었습니다.');
                location.replace('/');
                fetch('/logout',{method:'GET'}).then();
            })
        }
    }
)
