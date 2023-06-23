$.ajax({
    // 클릭한 게시물의 데이터만 가져와야함
    url: "http://openapi.seoul.go.kr:8088/786e44785864656334354e61496a76/json/TbAdpWaitAnimalView/1/1/",
    type: "get",
    dataType: "json",
    success: function (result) {
        console.log(result);
        console.log(result.TbAdpWaitAnimalView.row);

        let list = result.TbAdpWaitAnimalView.row;
        list.forEach((obj) => makeList(obj));
    },
});

function makeList(obj) {
    let fullName = `${obj.NM}`;
    let centerName = fullName.split('(')[1].split('-')[0].split(')')[0];
    $('.title-section1').text(centerName);

    let petName = fullName.split('(')[0];
    $('.title-section2').text(petName);

    // 성별 'M', 'W' > '남아', '여아'로 변경
    let petGender = `${obj.SEXDSTN}`;
    if (petGender == 'M') {
        petGender = '남아';
    } else if (petGender == 'W') {
        petGender = '여아';
    }

    $(".name").text(petName);
    $(".gender").text(petGender);
    $(".register-date").text(`${obj.ENTRNC_DATE}`);
    $(".weight").text(`${obj.BDWGH}` + 'kg');

    // 입양상태 : N(입양대기), P(입양진행중), C(입양완료)
    let adoptStatus = `${obj.ADP_STTUS}`;
    switch (adoptStatus) {
        case 'N':
            adoptStatus = '입양대기';
            break;
        case 'P':
            adoptStatus = '입양진행중';
            break;
        case 'C':
            adoptStatus = '입양완료';
            break;
        default:
    }
    $(".adopt-status").text(adoptStatus);

    $(".introduce-video").prop("href", `${obj.INTRCN_MVP_URL}`);

    // 소개영상 URL이 없을 시 새창 띄우지 않기
    $(".introduce-video").on("click", function(event) {
        let videoUrl =  `${obj.INTRCN_MVP_URL}`;
        if (videoUrl == "") {
            event.preventDefault();
            alert("영상 URL이 존재하지 않습니다.")
        } else {

        }
    })

    $(".pet-image").attr("src", `https://animal.seoul.go.kr/comm/getImage?srvcId=MEDIA&upperNo=${obj.ANIMAL_NO}&fileTy=ADOPTIMG&fileNo=1&thumb`);

    let content = `<div>${obj.INTRCN_CN}</div>`;
    $(".content").append(content);
}
