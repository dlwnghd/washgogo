const changeInfo = document.getElementById('changeInfo');
const withdrawal = document.getElementById('withdrawal');

const $username = $("#USERNAME");
const $email = $("#EMAIL");
const $password = $("#PASSWORD");
const $phonenumber = $("#PHONENUMBER");
const $address = $("#ADDRESS");
const $entrancepw = $("#ENTRANCEPW")

const $inputInfo = $("#modifyInfo");
const $pw = $("#pw");
const $result = $("#result");

//  모달창에 있는 확인창이 눌리면
const changeuserBtn = document.querySelector(".change-user");
changeuserBtn.addEventListener('click', e => {
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
                newPw.innerHTML = "비밀번호 확인<div class=\"password-wrapper npw\"><input id=\"Pw\" type=\"password\"><button class=\"pw-show\"></button></div>";
                newPwCheck.innerHTML = "새 비밀번호 확인<div class=\"password-wrapper npw\"><input id=\"PwCheck\" type=\"password\"><button class=\"pw-show\"></button></div>";
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
            $entrancepw.text(document.getElementById("result").value); // ⭐기능 구현되는 코드
            break;
    }
    close();
})

function close() {
    modal.style.display = "none";
    modal_window.style.height = '300px';
    // 비밀번호 지우기❌
    newPw.style.display = "none";
    newPwCheck.style.display = "none";
    // 계정 지우기❌
    withdrawals.style.display = "none";
    choose2.style.display = "none";
    // 수정칸 지우기❌
    modifyInfo.style.display = 'none';
    document.getElementById('Pw').value = "";
    document.getElementById('PwCheck').value = "";
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
    changeInfo.innerHTML = '<h2>이름 수정</h2>';
    modifyInfo.innerHTML = "이름<input id=\"result\" type=\"text\" placeholder=\"이름을 입력해주세요\">";
    // 수정공간 나타내기
    modifyInfo.style.display = 'block';
    choose1.style.display = 'contents';
})
// 이메일 수정 모달 띄우기
const emailBtn = account.querySelector(".email")
emailBtn.addEventListener("click", e => {
    modal.style.display = "grid";
    // 수정할 데이터명
    changeInfo.innerHTML = '<h2>이메일 수정</h2>';
    modifyInfo.innerHTML = "이메일<input id=\"result\" type=\"text\" placeholder=\"이메일을 입력해주세요\">";
    // 수정공간 나타내기
    modifyInfo.style.display = 'block';
    choose1.style.display = 'contents';
})
// 비밀번호 수정 모달 띄우기
const passwordBtn = account.querySelector(".password")
passwordBtn.addEventListener("click", e => {
    modal.style.display = "grid"
    changeInfo.innerHTML = '<h2>비밀번호 수정</h2>';
    modal_window.style.height = '360px';
    // 비밀번호 나타내기
    newPw.style.display = "block";
    newPwCheck.style.display = "block";


    // 수정공간 나타내기
    choose1.style.display = 'contents';
})
// 휴대전화번호 수정 모달 띄우기
const phoneNumberBtn = account.querySelector(".phoneNumber")
phoneNumberBtn.addEventListener("click", e => {
    modal.style.display = "grid";
    // 수정할 데이터명
    changeInfo.innerHTML = '<h2>휴대전화 번호 수정</h2>';
    modifyInfo.innerHTML = "휴대전화<input id=\"result\" type=\"text\">";;
    // 수정공간 나타내기
    modifyInfo.style.display = 'block';
    choose1.style.display = 'contents';
})
// 배송지 수정 모달 띄우기
const addressBtn = account.querySelector(".address")
addressBtn.addEventListener("click", e => {
    location.href="/user/modifyAddress";
})

// 계정탈퇴 모달 띄우기
const accountWithdrawalBtn = withdrawal.querySelector(".accountWithdrawal")
accountWithdrawalBtn.addEventListener("click", e => {
    modal.style.display = "grid";
    changeInfo.innerHTML = '<h2>정말 WASHGOGO를<br>떠나실 건가요?</h2>';
    modal_window.style.height = '350px';
    // 계정 탈퇴 선택창 나타내기
    withdrawals.style.display = "block";
    choose2.style.display = "contents";
    choose1.style.display = "none";
})

/* 비밀번호 보이기, 감추기 */
$(".pw-show").on("click",function(){
    $("input").toggleClass("active");
    if($("input").hasClass("active")){
        $(".pw-show").prev("input").attr("type","text");
        $(".pw-show").toggleClass("pw-hide");
    }else{
        $(".pw-show").prev("input").attr("type","password");
        $(".pw-show").removeClass("pw-hide");
    }
});

// let replacePw = String(PASSWORD).length * '*';
// console.log(replacePw);
// let rplace = String(PASSWORD).replace('$****');

//수정
function modify() {
    let userName = $("#USERNAME").text();
    let userEmail = $("#EMAIL").text();
    let userPw = $("#PASSWORD").text();
    let userPhonenum = $("#PHONENUMBER").text();
    let userAddress = $("#ADDRESS").text();
    let userAddressDetail = $("#ADDRESSDETAIL").text();
    let userEntranceType = $("#ENTRANCETYPE").text();
    let userEntrancePw = $("#ENTRANCEPW").text();
    let userEntranceMessage = $("#ENTRANCEMESSAGE").text();
    var userVO = {
        userName : userName,
        userEmail : userEmail,
        userPw : userPw,
        userPhonenum : userPhonenum,
        userAddress : userAddress,
        userAddressDetail : userAddressDetail,
        userEntranceType : userEntranceType,
        userEntrancePw : userEntrancePw,
        userEntranceMessage : userEntranceMessage
    }
    console.log(userVO);
    $.ajax({
        url: '/user/informationModify',
        type: 'post',
        data: JSON.stringify(userVO),
        contentType: "application/json",
        success: function (result) {
            console.log(result);
            location.href = result;
        },
        error: function () {
            alert("에러입니다");
        }
    });
}

//수정 버튼 클릭 시
const modifyBtn = document.querySelector("#change-ok");
modifyBtn.addEventListener("click", e => {
    modify();
})

//계정 탈퇴
function remove() {
    $.ajax({
        url: '/user/informationRemove',
        type: 'post',
        success: function (result) {
            console.log(result);
            location.href = result;
        },
        error: function () {
            alert("에러입니다");
        }
    });
}

// 계정 탈퇴 클릭 시
const deleteBtn = document.querySelector(".delete-user");
deleteBtn.addEventListener("click", e => {
    remove();
})

/*----------------프로필 업로드------------------------*/
/* 모달창 이벤트 */
const $modalBg = $("#commonMessageBoxBg");
const $body = $("body");
const $modal = $("#commonMessageBox");
const $basicBtn = $("#commonMessageBox .btn-to-basic");
const $closeModal = $(".modalCloseBtn");
const $okBtn = $("#commonMessageBox .btnOk");
function showModal() {
    $modal.css("display", "block");
    modalOn();
}
$closeModal.on("click", function(){
    modalOff();
});
function modalOn(){
    $modalBg.css("display", "block");
    $body.css("overflow", "hidden");
}
function modalOff(){
    $modalBg.css("display", "none");
    $modal.css("display", "none");
    $body.css("overflow", "auto");
}
/* 카메라 아이콘 클릭 이벤트*/
const $cameraIcon = $(".modifyInfo .camera-icon");
$cameraIcon.on("click",function () {
    showModal();
});

/* 업로드 파일의 확장자 제한 */
let regex = new RegExp("(.*?)\.(jpg|png)$");
let maxSize = 5242880; // 5MB

function checkExtension(fileName, fileSize){
    if(!regex.test(fileName)){
        alert("(" + fileName + ")업로드 할 수 없는 파일의 형식입니다.");
        return false;
    }
    if(fileSize >= maxSize){
        alert("(" + fileName + ")파일 사이즈 초과");
        return false;
    }
    return true;
}
/* 사진 업로드 */
const $fileInput = $("#profileInput");
const $Image = $(".modifyInfo .user-profile-picture div.image");
$okBtn.on("click", function () {
    modalOff();
    $fileInput.click();
});
$fileInput.on("change", function () {
    let formData = new FormData();
    let input = $("input[name='file']");
    let file = input[0].files[0];
    console.log("파일태그 체인지 이벤트 파일 가져오기 : " + file.name, file.size);
    if(file){
        if(checkExtension(file.name, file.size)){
            formData.append("file", file);
        }

        $.ajax({
            url: "/upload/uploadAjax",
            type: "post",
            data: formData,
            processData: false,
            contentType: false,
            success: function(profile){
                showUploadFile(profile);
                console.log("업로드 성공 profile 리턴 : " + profile.toString());
            }
        });


        /*
        let profileData = {
            "profile.fileName" : $Image.data('name'),
            "profile.originalFileName" : $Image.data('original'),
            "profile.uploadDirectory" : $Image.data('directory')
        }

        $.ajax({
            url: "/user/modifyProfile",
            type: "post",
            data: profileData,
            contentType: "application/json",
            success: function(result){
                console.log(result);
            }
        });

         */


        let $form = $("form#profileForm");
        let str = "";

        str += "<input type='hidden' name='profile.fileName' value='" + $Image.data('name') +"'>"
        str += "<input type='hidden' name='profile.originalFileName' value='" + $Image.data('original') +"'>"
        str += "<input type='hidden' name='profile.uploadDirectory' value='" + $Image.data('directory') +"'>"

        $form.append(str).submit();


    }

});

/* 첨부된 파일 화면에 보여주기 */
function showUploadFile(profile){
    $Image.attr("data-name", profile.fileName);
    $Image.attr("data-original", profile.originalFileName);
    $Image.attr("data-directory", profile.uploadDirectory);
    $Image.css("background-image", "url('/upload/display?path=" + profile.uploadDirectory + "/t_" + profile.fileName + "'");
    console.log("화면에 첨부파일 뿌려주기");
}
/* 기본 이미지 적용 */
$basicBtn.on("click", function () {
    removeImageFile();
    showBasicImage();
    modalOff();

    $.ajax({
        url: "/user/removeProfile/" + userNumber,
        type: "delete",
        success: function(){
            console.log("DB 삭제 성공");
        },
        error: function () {
            console.log("DB 삭제 실패");
        }
    });

    /*
    const $deleteForm = $('<form></form>')
    $deleteForm.attr("name", "newForm");
    $deleteForm.attr("method", "post");
    $deleteForm.attr("action", "/user/removeProfile");

    $deleteForm.append($('<input/>', {type: 'hidden', name: 'userNumber', value: userNumber}));

    $deleteForm.appendTo('body');

    $deleteForm.submit();
     */

});
function showBasicImage() {
    $Image.css("background-image", "url('/img/user-profile-image.jpg'");
}
/* 첨부된 파일 삭제 */
function removeImageFile() {
    let path = $Image.data("directory") + "/t_" + $Image.data("name");
    pathData = {
        path: path
    }
    $.ajax({
        url: "/upload/delete",
        type: "delete",
        data: JSON.stringify(pathData),
        success: function(){
            console.log("삭제 성공");
        },
        error: function () {
            console.log("삭제 실패");
        }
    });
}

/*프로필*/
if(profileSession){
    const $image = $(".profile-image .user-profile-picture div");
    $image.css("background-image", "url('/upload/display?path=" + profileSession.uploadDirectory + "/t_" + profileSession.fileName + "'");
}
