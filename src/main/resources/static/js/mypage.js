const accountCancellation = document.getElementById('cancel-register');

accountCancellation.addEventListener('click', () => {
        const email = document.getElementById('useremail').value;
        if (confirm('정말 탈퇴하시겠습니까?') == true) {
            fetch(`/memberInfo/${email}/delete`, {method: 'DELETE'}).then(() => {
                alert('탈퇴되었습니다.');
                location.replace('/');
                fetch('/logout', {method: 'GET'}).then();
            })
        }
    }
)

function updateUser(userId) {
    var formData = {
        name: document.getElementById("name").value,
        phone: document.getElementById("phone").value
    };

    fetch(`/memberInfo/${userId}/change`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })
        .then(response => {
            if (!response.ok) {
                console.error('Error updating user:', response.statusText);
                return;
            }
            alert('유저 정보가 수정되었습니다.');
            location.replace("/myPage");
        })
        .catch(error => {
            console.error('Error updating user:', error);
        })
}