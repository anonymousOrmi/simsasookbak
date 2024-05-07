const signInBtn = document.getElementById("signIn");
const signUpBtn = document.getElementById("signUp");
const fistForm = document.getElementById("form1");
const secondForm = document.getElementById("form2");
const container = document.querySelector(".container");
const signinButton = document.querySelector(".sign-in-button");
// const signUpBtn2 = document.querySelector('.sign-up');
const signUpClick = document.querySelector('.sign-up-button');
signInBtn.addEventListener("click", () => {
    container.classList.remove("right-panel-active");
});

signUpBtn.addEventListener("click", () => {
    container.classList.add("right-panel-active");
});

fistForm.addEventListener("submit", (e) => e.preventDefault());
secondForm.addEventListener("submit", (e) => e.preventDefault());

signUpClick.addEventListener("click",()=>{
    const password = document.querySelector('#password').value;
    const password_check = document.querySelector('#password-check').value;
    const errorMsg=document.querySelector('.error-message');
    if(password === password_check){
        console.log('비밀번호 일치');
        signup(password);
    }else{
        errorMsg.innerText='비밀번호가 일치하지 않습니다.';
    }
});

function signup(password){
    role = document.getElementsByName('role');
    if(role[0].checked===true) {
        role = role[0].value;
    }else if(role[1].checked===true){
        role = role[1].value;
    }else{
        role = role[2].value;
    }
    var formData={
        name : document.getElementById('name').value,
        email : document.getElementById('email').value,
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
            fetch(`/t?msg=${role.value}`,{method:'GET'});
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
        console.log(`click ${event}`);
        const username= document.getElementById('username').value;
        const password = document.getElementById('pwd').value;
        fetch(`/login?username=${username}&password=${password}`,{
            method:'POST'
        }).then(()=>{
            window.location.href='/'
        });

    })
}
/*
 <input type="text" placeholder="이름" class="input" name="name"/>
            <div style="display: flex">
                <input type="email" placeholder="Email" class="input" name="email" id="email"/>
                <button type="button" style="margin-left: 20px">중복확인</button>
            </div>
            <input type="password" placeholder="Password" class="input" name="password" id="password"/>
            <input type="password" placeholder="Password 확인" class="input" id="password-check">
            <span class="error-message"></span>
            <fieldset>
                <label for="user-register">이용자</label>
                <input type="radio" id="user-register" name="role" value="이용자"/>
                <label for="accommodation-provider-register">숙박업자</label>
                <input type="radio"  id="accommodation-provider-register" name="role" value="숙박업자"/>
            </fieldset>
            <input type="tel" placeholder="Phone Number" name="phone" id="phone" pattern="[0-9]{11}" required class="input">
            <small>생일</small>
            <input type="date" class="input" name="birthDate" id="birthDate">
            <button class="btn sign-up-button" type="submit">Sign Up</button>


 */