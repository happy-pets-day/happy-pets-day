$('.material-symbols-outlined').on('click', function (){
    window.location.href='/sitter/addList';
})

$('.profile-box').on('click', function (){
    let sitterNumber = $('').val();
    window.location.href='/sitter/list?siiterNumber=' + sitterNumber;
})