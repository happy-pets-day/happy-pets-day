// 주소 API
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
        document.getElementById("select_extraAddress").value = extraAddr;
      } else {
        document.getElementById("select_extraAddress").value = "";
      }

      // 우편번호와 주소 정보를 해당 필드에 넣는다.
      document.getElementById("select_postcode").value = data.zonecode;
      document.getElementById("select_address").value = addr;
      // 커서를 상세주소 필드로 이동한다.
      document.getElementById("select_detailAddress").focus();
    },
  }).open();
}

// 생년월일 입력 datepicker
$(".birth").on("click", function () {
  $(".datepicker").datepicker();
});

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

// 비밀번호 확인
const regex =
  /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*()_+])[a-zA-Z\d!@#$%^&*()_+]{8,15}$/;

let pwInput = $(".password");
let pwCheckInput = $(".check-password");
let pwCheckMsg = $(".check-pw-msg");
let pwCheckMsg2 = $(".check-pw-msg2");

pwInput.on("blur", function () {
  if (regex.test($(this).val())) {
    pwCheckMsg.text("사용 가능한 비밀번호입니다.");
    pwCheckMsg.css("color", "#00a000");
    // 비밀번호 일치 검사(1차 정규식 통과한 후에만 일치 검사)
    pwCheckInput.on("blur", function () {
      if ($(this).val() != pwInput.val()) {
        pwCheckMsg2.text("비밀번호가 일치하지 않습니다.");
        pwCheckMsg2.css("color", "#dc143c");
      } else {
        pwCheckMsg2.text("비밀번호가 일치합니다.");
        pwCheckMsg2.css("color", "#00a000");
      }
    });
  } else {
    pwCheckMsg.html(
      "사용 불가능한 비밀번호입니다. <br>영어, 숫자, 특수문자를 포함하여 8글자 이상 작성하세요!"
    );
    pwCheckMsg.css("color", "#dc143c");
  }
});
