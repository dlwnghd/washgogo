const changeInfo = document.getElementById('changeInfo');
const withdrawal = document.getElementById('withdrawal');

const $username = $("#USERNAME");
const $email = $("#EMAIL");
const $password = $("#PASSWORD");
const $phonenumber = $("#PHONENUMBER");
const $address = $("#ADDRESS");
const $addressdetail = $("#ADDRESSDETAIL");
const $payment = $("#PAYMENT");

const $inputInfo = $("#modifyInfo");
const $pw = $("#pw");
const $result = $("#result");

//  모달창에 있는 확인창이 눌리면
const changeuserBtn = document.querySelector(".change-user");
changeuserBtn.addEventListener('click', e => {
    console.log(changeInfo.innerText);
    let type = changeInfo.innerText;

    switch (type) {
        case '이름 수정':
            console.log(document.getElementById("result").value);
            $username.text(document.getElementById("result").value); // ⭐기능 구현되는 코드
            break;
        case '이메일 수정':
            console.log(document.getElementById("result").value);
            $email.text(document.getElementById("result").value); // ⭐기능 구현되는 코드
            break;
        case '비밀번호 수정':
            if (document.getElementById("Pw").value != document.getElementById("PwCheck").value) {
                alert("비밀번호가 일치하지 않습니다!");
                newPwCheck.innerHTML = "<input type=\"password\">";
                break;
            }
            // 비밀번호는 db에 전달만 하고 view에서는 보이지 않게 하도록 하자
            // console.log(document.getElementById("Pw").value);
            // $password.text(document.getElementById("Pw").value); // ⭐기능 구현되는 코드
            alert(type + "이 수정되었습니다!");
            break;
        case '휴대전화 번호 수정':
            $phonenumber.text(document.getElementById("result").value); // ⭐기능 구현되는 코드
            break;
        case '배송지 수정':
            $address.text(document.getElementById("result").value); // ⭐기능 구현되는 코드
            break;
        case '공동현관 출입방법 수정':
            $addressdetail.text(document.getElementById("result").value); // ⭐기능 구현되는 코드
            break;
        case '결제정보 수정':
            $payment.text(document.getElementById("result").value); // ⭐기능 구현되는 코드
            break;
    }
    close();
})

function close() {
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
// 취소 버튼으로 닫기
const nochangeBtn = document.querySelector(".no-change-user");
nochangeBtn.addEventListener("click", e => {
    close();
})

// 취소 버튼으로 닫기
const remainBtn = document.querySelector(".remain-user");
remainBtn.addEventListener("click", e => {
    close();
})

// 계정 탈퇴 버튼으로 닫기
const deleteBtn = document.querySelector(".delete-user");
deleteBtn.addEventListener("click", e => {
    close();
})

// 모달창 X버튼으로 닫기
const closeBtn = modal.querySelector(".close-area")
closeBtn.addEventListener("click", e => {
    close();
})

// 아무곳이나 눌러서 모달창 닫기
modal.addEventListener("click", e => {
    const evTarget = e.target
    if (evTarget.classList.contains("modal-overlay")) {
        close();
    }
})

// 이름 수정 모달 띄우기
const nameBtn = account.querySelector(".name")
nameBtn.addEventListener("click", e => {
    modal.style.display = "grid";
    // 수정할 데이터명
    changeInfo.innerHTML = '<h2 style="text-align: center;">이름 수정</h2>';
    modifyInfo.innerHTML = "이름<input id=\"result\" type=\"text\">";
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
    modifyInfo.innerHTML = "이메일<input id=\"result\" type=\"text\">";
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
    // 수정할 데이터명
    newPw.innerHTML = "새 비밀번호<input id=\"Pw\" type=\"password\">";
    newPwCheck.innerHTML = "새 비밀번호 확인<input id=\"PwCheck\" type=\"password\">";
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
    modifyInfo.innerHTML = "휴대전화<input id=\"result\" type=\"text\">";;
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
    modifyInfo.innerHTML = "배송지<input id=\"result\" type=\"text\">";;
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
    modifyInfo.innerHTML = "공동현관 출입방법<input id=\"result\" type=\"text\">";;
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
    modifyInfo.innerHTML = "결제정보<input id=\"result\" type=\"text\">";;
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