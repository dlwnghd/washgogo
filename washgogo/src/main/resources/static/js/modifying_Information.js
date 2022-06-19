const changeInfo = document.getElementById('changeInfo');
const withdrawal = document.getElementById('withdrawal');



// ⭐모달창에 있는 input에 넣었던 값을 받기위해 했던 발버둥
// const $username = $(".username");
// const $inputInfo = $(".inputInfo");
// // $('.username').text('konnichiha');

// // 모달창에 있는 확인창이 눌리면
// const changeuserBtn = document.querySelector(".change-user");
// console.log($inputInfo);
// changeuserBtn.addEventListener('click', e =>{
//     console.log(document.getElementById("result").innerText);
//     // console.log(document.getElementById("result").innerText);
//     // $username.text();
// })


















// 모달창 X버튼으로 닫기
const closeBtn = modal.querySelector(".close-area")
closeBtn.addEventListener("click", e => {
    modal.style.display = "none";
    modal_window.style.height = '250px';
    // 비밀번호 지우기❌
    newPw.style.display = "none";
    newPwCheck.style.display = "none";
    // 계정 지우기❌
    withdrawals.style.display = "none";
    choose2.style.display = "none";
    // 수정칸 지우기❌
    modifyInfo.style.display = 'none';
})

// 아무곳이나 눌러서 모달창 닫기
modal.addEventListener("click", e => {
    const evTarget = e.target
    if (evTarget.classList.contains("modal-overlay")) {
        modal.style.display = "none";
        modal_window.style.height = '250px';
        // 비밀번호 지우기❌
        newPw.style.display = "none";
        newPwCheck.style.display = "none";
        // 계정 지우기❌
        withdrawals.style.display = "none";
        choose2.style.display = "none";
        // 수정칸 지우기❌
        modifyInfo.style.display = 'none';
    }
})

// 이름 수정 모달 띄우기
const nameBtn = account.querySelector(".name")
nameBtn.addEventListener("click", e => {
    modal.style.display = "grid";
    // 수정할 데이터명
    changeInfo.innerHTML = '<h2 style="text-align: center;">이름 수정</h2>';
    modifyInfo.innerHTML = "이름<input type=\"text\">";
    // 수정공간 나타내기
    modifyInfo.style.display = 'block';
    choose1.style.display = 'block';
})
// 이메일 수정 모달 띄우기
const emailBtn = account.querySelector(".email")
emailBtn.addEventListener("click", e => {
    modal.style.display = "grid";
    // 수정할 데이터명
    changeInfo.innerHTML = '<h2 style="text-align: center;">이메일 수정</h2>';
    modifyInfo.innerHTML = "이메일<input type=\"text\">";
    // 수정공간 나타내기
    modifyInfo.style.display = 'block';
    choose1.style.display = 'block';
})
// 비밀번호 수정 모달 띄우기
const passwordBtn = account.querySelector(".password")
passwordBtn.addEventListener("click", e => {
    modal.style.display = "grid"
    changeInfo.innerHTML = '<h2 style="text-align: center;">비밀번호 수정</h2>';
    modal_window.style.height = '300px';
    // 비밀번호 나타내기
    newPw.style.display = "block";
    newPwCheck.style.display = "block";
    // 수정공간 나타내기
    choose1.style.display = 'block';
})
// 휴대전화번호 수정 모달 띄우기
const phoneNumberBtn = account.querySelector(".phoneNumber")
phoneNumberBtn.addEventListener("click", e => {
    modal.style.display = "grid";
    // 수정할 데이터명
    changeInfo.innerHTML = '<h2 style="text-align: center;">휴대전화 번호 수정</h2>';
    modifyInfo.innerHTML = "휴대전화<input type=\"text\">";;
    // 수정공간 나타내기
    modifyInfo.style.display = 'block';
    choose1.style.display = 'block';
})
// 배송지 수정 모달 띄우기
const addressBtn = account.querySelector(".address")
addressBtn.addEventListener("click", e => {
    modal.style.display = "grid";
    // 수정할 데이터명
    changeInfo.innerHTML = '<h2 style="text-align: center;">배송지 수정</h2>';
    modifyInfo.innerHTML = "배송지<input type=\"text\">";;
    // 수정공간 나타내기
    modifyInfo.style.display = 'block';
    choose1.style.display = 'block';
})
//  공동현관 출입방법 모달 띄우기
const addressDetailBtn = account.querySelector(".addressDetail")
addressDetailBtn.addEventListener("click", e => {
    modal.style.display = "grid";
    // 수정할 데이터명
    changeInfo.innerHTML = '<h2 style="text-align: center;">공동현관 출입방법 수정</h2>';
    modifyInfo.innerHTML = "공동현관 출입방법<input type=\"text\">";;
    // 수정공간 나타내기
    modifyInfo.style.display = 'block';
    choose1.style.display = 'block';
})
// 결제정보 수정 모달 띄우기
const paymentBtn = account.querySelector(".payment")
paymentBtn.addEventListener("click", e => {
    modal.style.display = "grid";
    // 수정할 데이터명
    changeInfo.innerHTML = '<h2 style="text-align: center;">결제정보 수정</h2>';
    modifyInfo.innerHTML = "결제정보<input type=\"text\">";;
    // 수정공간 나타내기
    modifyInfo.style.display = 'block';
    choose1.style.display = 'block';
})
// 계정탈퇴 모달 띄우기
const accountWithdrawalBtn = withdrawal.querySelector(".accountWithdrawal")
accountWithdrawalBtn.addEventListener("click", e => {
    modal.style.display = "grid";
    changeInfo.innerHTML = '<h2 style="text-align: center;">정말 WASHGOGO를<br>떠나실 건가요?</h2>';
    modal_window.style.height = '300px';
    // 계정 탈퇴 선택창 나타내기
    withdrawals.style.display = "block";
    choose2.style.display = "block";
    choose1.style.display = "none";
})