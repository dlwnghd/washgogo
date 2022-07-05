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


//프로필 업로드
// const inputUploader = document.querySelector('.profile-uploader');
// const uploader = document.querySelector('upload');
//
// uploader.addEventListener('click', () => inputUploader.click());

// //프로필사진 업로드
const fResult = $("div.file-result ul");
// let files = [[${user.profile}]];
let regex = new RegExp("(.*?)\.(jpg|png)$");
let maxSize = 5242880; // 5MB

function checkExtension(fileName, fileSize){
    if(!regex.test(fileName)){
        alert("(" + fileName + ")업로드 할 수 없는 파일의 형식입니다.")
        return false;
    }

    if(fileSize >= maxSize){
        alert("(" + fileName + ")파일 사이즈 초과")
        return false;
    }
    return true;
}

showUploadFile(files);

function showUploadFile(profile){
    let str = "";
    $.each(profile, function(i, file){
        str += "<li data-name='" + file.fileName + "' data-original='" + file.originalFileName + "' data-directory='" + file.uploadDirectory + "' data-image='" + file.image + "'>";
        str += "<span data-name='" + file.originalFileName + "' data-path='" + file.uploadDirectory + "/t_" + file.fileName + "' style='cursor: pointer'>x</span>";
        str += "<a href='/upload/download?path=" + file.uploadDirectory + "/" + file.fileName + "'>"
        str += file.image ? "<img src='/upload/display?path=" + file.uploadDirectory + "/t_" + file.fileName + "'>"
            : "<img src='/img/user-profile-image.jpg' width='100'>";
        str += "</a><p>" + file.originalFileName + "</p>";
        str += "</li>";
    });
    result.append(str);
}

//업로드 전
let arFile = Array.from($("input[type='file']")[0].files);

$("input[type='file']").on("change", function(e){
    let formData = new FormData();
    let input = $("input[name='files']"); // 업로드 후
    let files = input[0].files;
    console.log(files);
    for(let i=0; i<files.length; i++){
        if(checkExtension(files[i].name, files[i].size)){
            formData.append("files", files[i]);
        }
    }
    const dataTransfer = new DataTransfer();

    //사용자가 업로드한 파일들의 정보를 전역변수인 arFile에 담아놓기
    Array.from($(input[0].files)).forEach(file => arFile.push(file));
    //전역변수의 전체 파일들을 FileList타입으로 변경
    arFile.forEach(file => dataTransfer.items.add(file));
    //input태그에 그 동안 업로드했던 모든 파일의 정보로 덮어 씌우기
    input[0].files = dataTransfer.files;

    $.ajax({
        url: "/upload/uploadAjax",
        type: "post",
        data: formData,
        processData: false,
        contentType: false,
        success: function(profile){
            showUploadFile(profile);
        }
    });
});

let removedFiles = [];
$(".fResult").on("click", "span", function(e){
    let path = $(this).data("path");
    let li = $(this).closest("li");
    //x버튼을 눌렀을 때 삭제될 파일의 원본 이름
    let fileName = $(this).data("name");
    const dataTransfer = new DataTransfer();
    li.remove();
    removedFiles.push(path);
    //원래 있었던 파일들에서
    for(let i=0; i<arFile.length; i++){
        //삭제한 파일의 이름과 동일한 파일을 검사
        if(arFile[i].name == fileName){
            //삭제된 파일은 기존 파일들에서 삭제시켜야 한다.
            arFile.splice(i, 1);
        }
    }

    //삭제된 파일을 제외한 나머지 파일들을
    arFile.forEach(file => dataTransfer.items.add(file));
    //input태그에 다시 담아준다.
    $("input[type='file']")[0].files = dataTransfer.files;

});

$("input[type='button']").on("click", function(e){
    e.preventDefault();

    let $form = $("form#modifyForm");
    let str = "";

    for(let i=0; i<removedFiles.length; i++){
        $.ajax({
            url: "/upload/delete",
            type: "delete",
            data: {path: removedFiles[i]},
        });
    }

    $.each($(".result ul li"), function(i, li){
        str += "<input type='hidden' name='fileList[" + i + "].fileName' value='" + $(li).data('name') +"'>"
        str += "<input type='hidden' name='fileList[" + i + "].originalFileName' value='" + $(li).data('original') +"'>"
        str += "<input type='hidden' name='fileList[" + i + "].uploadDirectory' value='" + $(li).data('directory') +"'>"
        str += "<input type='hidden' name='fileList[" + i + "].image' value='" + $(li).data('image') +"'>"
    });

    $form.append(str).submit();
});