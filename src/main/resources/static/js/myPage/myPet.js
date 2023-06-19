// 메뉴버튼(수정,삭제) 누를 시 수정삭제창 나타나고 다른 곳 클릭하면 사라지기
$("body").on("click", function (event) {
  if ($(event.target).hasClass("menu-dot-img")) {
    $(".edit-box").css("display", "block");
  } else {
    $(".edit-box").css("display", "none");
  }
});

// 삭제버튼 누를 시 alert창
$(".delete1").on("click", function () {
  let petNumber = $(this).data("number");

  if (window.confirm("해당 반려동물을 삭제하시겠습니까?")) {
  } else {
  }
});

// 모달
let modal = $("#modal-container");

// '수정' 클릭 > 모달 띄워주기
$(".modify1").on("click", function () {
  modal.css('display', 'flex');
});

// 'X' 클릭 > 모달 끄기
$(".btn-close").on("click", function () {
  modal.css('display', 'none');
});

// '동물 종류' 드롭다운 메뉴 열기/닫기
$(".dropdown-btn").on("click", function () {
  if ($(".menu-list").css("display") == "none") {
    $(".menu-list").css("display", "block");
  } else {
    $(".menu-list").css("display", "none");
  }
});

$(".list button").on("click", function () {
  let selectValue = $(this).text();
  $(".dropdown-btn span").text(selectValue);
  $(".menu-list").css("display", "none");
  $("#etcDirect").val("");
});

$(".etc-direct").on("click", function () {
  $("#etcDirect").attr("type", "text");
  $("#etcDirect").focus();
});

$(document).ready(function () {
  $("#dog, #cat").on("click", function () {
    $("#etcDirect").attr("type", "hidden");
    $("#etcDirect").val($(this).val());
  });
});

// 선택한 이미지 원 안에 삽입
function updateImage(input) {
  let image = document.getElementById("uploaded-image");
  if (input.files && input.files[0]) {
    let reader = new FileReader();
    reader.onload = function (e) {
      image.src = e.target.result;
    };
    reader.readAsDataURL(input.files[0]);
  }
}

// '기본 이미지' 클릭 > 기본 프로필 이미지로 변경
function restoreDefaultImg() {
  let uploadedImage = document.getElementById("uploaded-image");
  uploadedImage.src = "https://lifet.co.kr/img/profile/default.png";
}
