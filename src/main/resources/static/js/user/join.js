// 성별 리스트 설정
function toggleGenderList() {
  var genderList = document.getElementById("genderList");
  if (genderList.style.display === "none") {
    genderList.style.display = "block";
  } else {
    genderList.style.display = "none";
  }
}

function selectGender(gender) {
  var selectBox = document.querySelector(".select-box");
  var selectedGender = document.getElementById("selectedGender");

  selectBox.innerHTML = gender;
  selectedGender.value = gender;

  var genderList = document.getElementById("genderList");
  genderList.style.display = "none";
}

// 회원 가입 유효성 검사
document.addEventListener("DOMContentLoaded", function () {
  //이름 유효성 검사 변수값
  var nameInput = document.getElementById("name");
  var nameConfirmMsg = document.querySelector(".confirmMsg");

  //생년월일 유효성 검사 변수값

  var birthdayConfirmMsg = document.querySelectorAll(".confirmMsg")[1];

  //핸드폰번호 유효성 검사 변수값
  var phoneInput = document.getElementById("phone");
  var phoneConfirmMsg = document.querySelectorAll(".confirmMsg")[3];

  //아이디 유효성 검사 변수값
  var idInput = document.getElementById("id");
  var idConfirmMsg = document.querySelectorAll(".confirmMsg")[5];

  //비밀번호 유효성 검사 변수값
  var pwInput = document.getElementById("pw");
  var pwConfirmMsg = document.querySelectorAll(".confirmMsg")[6];

  //비밀번호 확인 유효성 검사 변수값
  var RepwInput = document.getElementById("Repw");
  var RepwConfirmMsg = document.querySelectorAll(".confirmMsg")[7];

  //이메일 유효성 검사 변수값

  var emailInput = document.getElementById("email");
  var emailConfirmMsg = document.querySelectorAll(".confirmMsg")[8];

  //보안 질문 답변 유효성 검사 변수값
  var answerInput = document.getElementById("answer");
  var answerConfirmMsg = document.querySelectorAll(".confirmMsg")[10];

  // 이름 유효성 검사
  nameInput.addEventListener("input", function () {
    var name = nameInput.value;
    if (
      name.length < 2 ||
      name.length >= 10 ||
      !/^[A-Za-z가-힣]+$/.test(name)
    ) {
      nameConfirmMsg.innerHTML = "2~10자의 한글이나 영문으로 입력해주세요.";
      nameConfirmMsg.classList.add("error");
    } else {
      nameConfirmMsg.innerHTML = "";
      nameConfirmMsg.classList.remove("error");
    }
  });

  // 생년월일
  $.datepicker.setDefaults({
    dateFormat: "yy-mm-dd",
    prevText: "이전 달",
    nextText: "다음 달",
    monthNames: [
      "1월",
      "2월",
      "3월",
      "4월",
      "5월",
      "6월",
      "7월",
      "8월",
      "9월",
      "10월",
      "11월",
      "12월",
    ],
    monthNamesShort: [
      "1월",
      "2월",
      "3월",
      "4월",
      "5월",
      "6월",
      "7월",
      "8월",
      "9월",
      "10월",
      "11월",
      "12월",
    ],
    dayNames: ["일", "월", "화", "수", "목", "금", "토"],
    dayNamesShort: ["일", "월", "화", "수", "목", "금", "토"],
    dayNamesMin: ["일", "월", "화", "수", "목", "금", "토"],
    showMonthAfterYear: true,
    yearSuffix: "년",
    changeMonth: true,
    changeYear: true,
    yearRange: "1930:2023",
  });

  $(".datepicker").datepicker();
  $(".datepicker").on("click", function () {
  });

  // 핸드폰번호 유효성 검사
  phoneInput.addEventListener("input", function () {
    if (phoneInput.validity.valid) {
      phoneConfirmMsg.innerHTML = "";
      phoneConfirmMsg.classList.remove("error");
    } else {
      phoneConfirmMsg.innerHTML = "올바른 핸드폰 번호를 입력해주세요.";
      phoneConfirmMsg.classList.add("error");
    }
  });

  // // 아이디 유효성 검사
  // idInput.addEventListener("input", function () {
  //   var id = idInput.value;
  //   if (!/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,12}$/.test(id)) {
  //     idConfirmMsg.innerHTML =
  //       "영어와 숫자를 조합한 6~12글자의 아이디를 입력해주세요.";
  //     idConfirmMsg.classList.add("error");
  //   } else {
  //     idConfirmMsg.innerHTML = "";
  //     idConfirmMsg.classList.remove("error");
  //   }
  // });
  //
  // // 비밀번호 유효성 검사
  // pwInput.addEventListener("input", function () {
  //   var password = pwInput.value;
  //   if (
  //     !/^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*])[a-zA-Z\d!@#$%^&*]{8,15}$/.test(
  //       password
  //     )
  //   ) {
  //     pwConfirmMsg.innerHTML =
  //       "8~15 글자의 문자/숫자/특수문자 필수 조합이어야 합니다.";
  //     pwConfirmMsg.classList.add("error");
  //   } else {
  //     pwConfirmMsg.innerHTML = "";
  //     pwConfirmMsg.classList.remove("error");
  //   }
  // });

  function checkUserIdAvailability(userId) {
    // AJAX 호출을 통해 서버에 아이디 중복 검사 요청
    $.ajax({
      url: "/checkUserIdAvailability", // 중복 검사를 수행하는 서버 API 경로
      method: "GET",
      data: { userId: userId },
      success: function(response) {
        if (response.available) {
          // 아이디 사용 가능
          $(".confirmMsg").text("사용 가능한 아이디입니다.").removeClass("error").addClass("success");
        } else {
          // 아이디 중복
          $(".confirmMsg").text("이미 사용 중인 아이디입니다.").removeClass("success").addClass("error");
        }
      },
      error: function() {
        // 오류 처리
        $(".confirmMsg").text("중복 검사 중 오류가 발생했습니다.").removeClass("success").addClass("error");
      }
    });
  }

  // 비밀번호 확인 유효성 검사
  RepwInput.addEventListener("input", function () {
    var pw = pwInput.value;
    var Repw = RepwInput.value;

    if (pw !== Repw) {
      RepwConfirmMsg.innerHTML = "비밀번호가 일치하지 않습니다.";
      RepwConfirmMsg.classList.add("error");
    } else {
      RepwConfirmMsg.innerHTML = "";
      RepwConfirmMsg.classList.remove("error");
    }
  });

  //이메일 유효성 검사

  emailInput.addEventListener("input", function () {
    var email = emailInput.value;
    if (
      email.length === 0 || // 이메일이 입력되지 않은 경우
      !/^[\w.-]+@[a-zA-Z_-]+?\.[a-zA-Z]{2,3}$/.test(email)
    ) {
      emailConfirmMsg.innerHTML = "올바른 이메일 주소를 입력해주세요.";
      emailConfirmMsg.classList.add("error");
    } else {
      emailConfirmMsg.innerHTML = "";
      emailConfirmMsg.classList.remove("error");
    }
  });

  //보안 질문 답변 유효성 검사
  answerInput.addEventListener("input", function () {
    var answer = answerInput.value;

    if (
      answer.length < 2 ||
      answer.length >= 10 ||
      !/^[A-Za-z가-힣]+$/.test(answer)
    ) {
      answerConfirmMsg.innerHTML = "2~10자의 한글이나 영문으로 입력해주세요.";
      answerConfirmMsg.classList.add("error");
    } else {
      answerConfirmMsg.innerHTML = "";
      answerConfirmMsg.classList.remove("error");
    }
  });
});

function toggleSelectList() {
  var selectList = document.getElementById("selectList");
  selectList.style.display = selectList.style.display === "none" ? "block" : "none";
}

function selectQuestion(questionIndex) {
  var questionText = document.getElementsByClassName("select-item")[questionIndex].innerText;
  var selectedQuestion = document.getElementById("selectedQuestion");
  var questionNumberInput = document.querySelector('.questionNumber');

  selectedQuestion.innerText = questionText;
  selectedQuestion.style.color = "black";
  questionNumberInput.value = questionIndex + 1; // questionIndex에 1을 더하여 대입

  toggleSelectList();
}


function selectList(select) {
  var selectBox = document.querySelector(".question-select-box");
  var answer = document.getElementById("answer");

  selectBox.innerHTML = select;
  answer.value = select;

  toggleSelectList();
}


// 카카오 주소 api
function selectAddress() {
  new daum.Postcode({
    oncomplete: function (data) {
      // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

      // 각 주소의 노출 규칙에 따라 주소를 조합한다.
      // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
      var addr = ""; // 주소 변수
      var extraAddr = ""; // 참고항목 변수

      // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
      if (data.userSelectedType === "R") {
        // 사용자가 도로명 주소를 선택했을 경우
        addr = data.roadAddress;
      } else {
        // 사용자가 지번 주소를 선택했을 경우(J)
        addr = data.jibunAddress;
      }

      // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
      if (data.userSelectedType === "R") {
        // 법정동명이 있을 경우 추가한다. (법정리는 제외)
        // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
        if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
          extraAddr += data.bname;
        }
        // 건물명이 있고, 공동주택일 경우 추가한다.
        if (data.buildingName !== "" && data.apartment === "Y") {
          extraAddr +=
            extraAddr !== "" ? ", " + data.buildingName : data.buildingName;
        }
        // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
        if (extraAddr !== "") {
          extraAddr = " (" + extraAddr + ")";
        }
        // 조합된 참고항목을 해당 필드에 넣는다.
        document.getElementById("userAddressTip").value = extraAddr;
      } else {
        document.getElementById("userAddressTip").value = "";
      }

      // 우편번호와 주소 정보를 해당 필드에 넣는다.
      document.getElementById("userZoneCode").value = data.zonecode;
      document.getElementById("userAddress").value = addr;
      // 커서를 상세주소 필드로 이동한다.
      document.getElementById("userAddressDetail").focus();
    },
  }).open();
}

// 제출 유효성 검사
$(document).ready(function () {
  $("#submit").click(function (event) {
    var requiredFields = $(".required");
    var incompleteFields = [];

    requiredFields.each(function () {
      if ($(this).val() === "") {
        incompleteFields.push($(this));
      }
    });

    var genderField = $("#selectedGender");
    if (genderField.val() === "") {
      incompleteFields.push(genderField);
    }

    var questionField = $("#selectedQuestion");
    if (questionField.text() === "질문 선택") {
      incompleteFields.push(questionField);
    }

    var agreeCheckbox = $(".all");
    if (!agreeCheckbox.prop("checked")) {
      incompleteFields.push(agreeCheckbox);
    }

    if (incompleteFields.length > 0) {
      event.preventDefault();

      var firstIncompleteField = incompleteFields[0];
      var confirmMsg = firstIncompleteField.next(".confirmMsg");

      // 입력되지 않은 필드로 스크롤 이동
      var fieldTopOffset = firstIncompleteField.offset().top - 100;
      $("html, body").animate({ scrollTop: fieldTopOffset }, "slow");

      // 입력되지 않은 필드를 강조하기 위해 confirmMsg에 메시지 표시
      confirmMsg.text("필수 입력 부분입니다.");
      confirmMsg.css("display", "block");

      if (firstIncompleteField.attr("id") === "selectedQuestion") {
        // 보안질문을 선택하지 않은 경우 경고 메시지 표시
        var questionConfirmMsg = firstIncompleteField
          .parent()
          .next(".confirmMsg");
        questionConfirmMsg.text("질문을 선택해주세요.");
        questionConfirmMsg.css("display", "block");
      }
    }
  });
});
