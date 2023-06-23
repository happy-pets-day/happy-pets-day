let $input = $("#post-image");
let $imgList = $(".img-list");
console.log($input);

// file change이벤트로 미리보기 갱신하기
$input.on("change", function () {
  let dt = new DataTransfer();
  let files = this.files;

  $('.file-wrap .img-list').detach();
  $('.file-wrap .input-hidden').detach();

  for (let i = 0; i < files.length; i++) {
    $(".file-wrap").prepend(`<li class="img-list"></li>
                    <div class="input-hidden">
                      <div class="license-name">
                        <p>자격증 이름 :</p>
                      </div>
                      <input type="text" name="applyFileTitle"/>
                    </div>`);

    let src = URL.createObjectURL(files[i]);
    let $img = $("<img>").attr("src", src);
    $img.css({
      width: "100%",
      height: "100%",
    });
    $(".img-list").eq(0).append($img);



    // let reader = new FileReader();
    // reader.onload = function (e) {
    //   let src = e.target.result;
    //   let $img = $("<img>").attr("src", src);
    //   $img.css({
    //     width: "100%",
    //     height: "100%",
    //   });
    //   $(".img-list").eq(0).append($img);
    // };
    // reader.readAsDataURL(files[i]);
    console.log(files);
  }
});

// $(document).ready(function () {
//   // 체크박스 클릭 이벤트 핸들러
//   $("input[name='specialty']").on("click", function () {
//     // 모든 체크박스의 체크를 해제
//     $("input[name='specialty']").prop("checked", true);
//     // 현재 클릭한 체크박스만 체크
//     $(this).prop("checked", true);
//   });
// });

// 클릭 이벤트로 이미지 지우고 미리보기 갱신하기
// $imgList.on("click", function (e) {
//   let name = $(e.target).data("name");
//   console.log(name);
//   // removeImg(name);
//   appendImg($input[0].files);
// });

// //미리보기 삭제 함수
// function removeImg(name) {
//   let dt = new DataTransfer();

//   let files = $input[0].files;

//   for (let i = 0; i < files.length; i++) {
//     if (files[i].name !== name) {
//       dt.items.add(files[i]);
//     }
//   }
//   $input[0].files = dt.files;
// }

// //파일 길이 체크 함수(체크할 files객체, 제한할 길이)
// function checkLength(files, num) {
//   if (files.length > num) {
//     alert(`파일은 최대 ${num}개까지만 첨부 가능합니다.`);
//     // 최대 수를 넘으면 비어있는 files객체 반환
//     return new DataTransfer().files;
//   }
//   return files;
// }

// // 이미지 미리보기 처리 함수
// // 이미지 수가 4개보다 적으면 기본이미지로 대체함
// function appendImg(files) {
//   for (let i = 0; i < 4; i++) {
//     if (i < files.length) {
//       let src = URL.createObjectURL(files[i]);

//       $imgList
//         .eq(i)
//         .css("background-image", `url(${src})`)
//         .css("background-size", "cover")
//         .data("name", `${files[i].name}`);

//       $imgList.eq(i).addClass("x-box");
//     } else {
//       $imgList
//         .eq(i)
//         .css(
//           "background",
//           "url(data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIzNiIgaGVpZ2h0PSIzNiI+PGcgZmlsbD0ibm9uZSIgZmlsbC1ydWxlPSJldmVub2RkIj48ZyBzdHJva2U9IiNCNUI1QjUiIHN0cm9rZS13aWR0aD0iMS41IiB0cmFuc2Zvcm09InRyYW5zbGF0ZSg0IDQpIj48cmVjdCB3aWR0aD0iMjgiIGhlaWdodD0iMjgiIHJ4PSIzLjUiLz48Y2lyY2xlIGN4PSI4LjU1NiIgY3k9IjguNTU2IiByPSIyLjMzMyIvPjxwYXRoIGQ9Ik0yOCAxOC42NjdsLTcuNzc3LTcuNzc4TDMuMTExIDI4Ii8+PC9nPjxwYXRoIGQ9Ik0wIDBoMzZ2MzZIMHoiLz48L2c+PC9zdmc+) no-repeat 50% #f2f2f2"
//         )
//         .data("name", null);
//       $imgList.eq(i).removeClass("x-box");
//     }
//   }
// }
