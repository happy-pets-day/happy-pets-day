let msg = $('.msg').val();
if(msg){
    alert(msg)
}

// 성별선택버튼 클릭시 버튼색상, 텍스트색상 변경
function changeColor(button) {
    var buttons = document.getElementsByClassName("gender-button");
    for (var i = 0; i < buttons.length; i++) {
        buttons[i].style.backgroundColor = "";  // 버튼 색상 초기화
        buttons[i].style.color = ""; // 텍스트 색상 초기화
        document.getElementById("pet-gender").value = button.value;
    }
    button.style.backgroundColor = "#68A5FE"; //선택된 버튼 색상 변경
    button.style.color = "#fff"; // 선택된 버튼의 텍스트 색상 변경
}

// 중성화선택버튼 클릭시 버튼색상, 텍스트색상 변경
function changeColor2(button) {
    var buttons = document.getElementsByClassName("surgery-button");
    for (var i = 0; i < buttons.length; i++) {
        buttons[i].style.backgroundColor = "";  // 버튼 색상 초기화
        buttons[i].style.color = "";  // 텍스트 색상 초기화
        document.getElementById("pet-surgery").value = button.value;
    }
    button.style.backgroundColor = "#68A5FE"; //선택된 버튼 색상 변경
    button.style.color = "#fff";  // 선택된 버튼의 텍스트 색상 변경
}

// 파일첨부 선택한 이미지 업로드
function updateImage(input) {
    var image = document.getElementById('uploaded-image');
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            image.src = e.target.result;
        };
        reader.readAsDataURL(input.files[0]);
    }
}

// 펫종류 직접입력칸 보여주기
$(function () {
    //직접입력 인풋박스 기존에는 숨어있다가
    $(".etc").hide();
    $("#select").change(function () {
        //직접입력을 누를 때 나타남
        if ($("#select").val() == "direct") {
            $(".etc").show();
        } else {
            $(".etc").hide();
        }
    })
});

// 달력으로 생년월일 집어넣기
$(function(){
    $('.datepicker').datepicker({

    });
});


$.datepicker.setDefaults({
    dateFormat: 'yy-mm-dd',
    prevText: '이전 달',
    nextText: '다음 달',
    monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
    monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
    dayNames: ['일', '월', '화', '수', '목', '금', '토'],
    dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
    dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
    showMonthAfterYear: true,
    yearSuffix: '년',
    changeMonth: true,
    changeYear: true,
    yearRange:'c-50:c'
});
