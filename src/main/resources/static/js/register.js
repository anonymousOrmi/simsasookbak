const signInBtn = document.getElementById("signIn");
const signUpBtn = document.getElementById("signUp");
const fistForm = document.getElementById("form1");
const secondForm = document.getElementById("form2");
const container = document.querySelector(".container");
const signinButton = document.querySelector(".sign-in-button");
const signUpBtn2 = document.querySelector('.sign-up');
signInBtn.addEventListener("click", () => {
    container.classList.remove("right-panel-active");
});

signUpBtn.addEventListener("click", () => {
    container.classList.add("right-panel-active");
});

fistForm.addEventListener("submit", (e) => e.preventDefault());
secondForm.addEventListener("submit", (e) => e.preventDefault());

signInBtn.addEventListener("click",()=>{
    const emailValue=document.querySelector('.email').value;
    const passwordValue = document.querySelector('.password').value;
    fetch("/member/register",{method:'POST' ,
                                    headers:{"Content-Type":"application/json"},
                                    body:JSON.stringify({
                                        "email": emailValue,
                                        "password": passwordValue
                                    })})
.then(()=>{location.replace("/");})}
);

signUpBtn2.addEventListener()