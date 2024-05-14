
const signInBtn = document.getElementById("signIn");
const signUpBtn = document.getElementById("signUp");
const fistForm = document.getElementById("form1");
const secondForm = document.getElementById("form2");
const container = document.querySelector(".container");
const signinButton = document.querySelector(".sign-in-button");
// const signUpBtn2 = document.querySelector('.sign-up');
const signUpClick = document.querySelector('.sign-up-button');
let emailcheckResponse ;
let emailCheckValue;
signInBtn.addEventListener("click", () => {
    container.classList.remove("right-panel-active");
});

signUpBtn.addEventListener("click", () => {
    container.classList.add("right-panel-active");
});

// fistForm.addEventListener("submit", (e) => e.preventDefault());
// secondForm.addEventListener("submit", (e) => e.preventDefault());

signUpClick.addEventListener("click",(e)=>{
    const password = document.querySelector('#password').value;
    const password_check = document.querySelector('#password-check').value;
    const errorMsg=document.querySelector('.error-message');
    const inputEnable = document.getElementById('email-check-input').value;
    console.log(emailcheckResponse);
    console.log(typeof emailcheckResponse);

    console.log(inputEnable);
    console.log(typeof inputEnable);
    if(emailcheckResponse.toString()===inputEnable){
        console.log('true');
    }
    e.preventDefault();
    if(inputEnable !== "") {
        if (password === password_check && inputEnable===emailcheckResponse.toString()) {
            console.log('비밀번호 일치');
            signup(password,emailCheckValue);

        } else {
            errorMsg.innerText = '비밀번호가 일치하지 않거나 이메일 인증번호가 다릅니다.';
        }
    }else{
        alert('이메일이 인증되지 않았습니다.')
    }
});

function signup(password,email){
    let role = document.getElementsByName('role');
    if(role[0].checked===true) {
        role = role[0].value;
    }else if(role[1].checked===true){
        role = role[1].value;
    }
    var formData={
        name : document.getElementById('name').value,
        email : email,
        password : password,
        phone : document.getElementById('phone').value,
        birthDate : document.getElementById('birthDate').value,
        role:role
    }
    // role_user = document.getElementById('user-register');
    // role_provider = document.getElementById('accommodation-provider-register');
    // console.log(role_user);
    // console.log(role_provider);
    // console.log(role_user.value);
    // console.log(role_provider.value);

    console.log(role);
    console.log(role[0]);
    console.log(role[1]);
    console.log(role[0].value);
    console.log(role[1].value);

    fetch('/member/register',{method:'POST',
        headers:{ 'Content-Type':'application/json'},
        body:JSON.stringify(formData)
    }).then(response=>{
        if(response.ok){
            alert('회원가입이 완료되었습니다.');
            // window.location.href='/login';
        }else{
            throw new Error('회원가입 실패');

        }

    }).catch(error=>{
        console.log(error);
    });

}

if(signinButton) {
    signinButton.addEventListener('click', (event) => {
        event.preventDefault();
        const username= document.getElementById('username').value;
        const password = document.getElementById('pwd').value;
        fetch(`/member/check/${username}/${password}`,{
            method:'POST'
        }).then(async (response)=>{
            let flag = await response.json();
            console.log(flag);
            if(flag){
                fetch(`/login?username=${username}&password=${password}`,{method:'POST'}).then(()=>{window.location.href='/'});
            }else{
                alert('아이디나 비밀번호가 다릅니다.');
            }
        });
    })
}

const emailCheckBtn = document.getElementById('email-check');
emailCheckBtn.addEventListener('click', ()=>{
    const email = document.getElementById('email').value;
    if(email !== "") {
        fetch('/email/check/message', {
            method: 'POST',
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({
                "email": email
            })
        }).then(async (response) => {
            if(response.ok) {
                const inputEnable = document.getElementById('email-check-input');
                const inputLabelEnable = document.getElementById('email-check-input-label');
                // inputEnable.classList.remove('disable')
                // inputLabelEnable.classList.remove('disable');
                inputEnable.style.cssText="display:block";
                inputLabelEnable.style.cssText="display:block";
                emailCheckValue = email;
                emailcheckResponse = await response.json();
                alert("인증번호를 해당 이메일로 보냈습니다.")
            }else{
                alert('이미 가입되어있습니다.')
            }
        })
    }else{
        alert('이메일을 입력하지않았습니다.');

    }
})