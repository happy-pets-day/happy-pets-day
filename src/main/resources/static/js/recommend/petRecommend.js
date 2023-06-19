$('.select-btn').on('click', function (){
    $(this).siblings().removeClass('click');
    $(this).addClass('click');
    $(this).parent().parent().removeClass('show');

    if($(this).parent().parent().hasClass('section1')){
        $('.input1').val($(this).text());
    }
    else if($(this).parent().parent().hasClass('section2')){
        $('.input2').val($(this).text());
    }
    else if($(this).parent().parent().hasClass('section3')){
        $('.input3').val($(this).text());
    }
    else if($(this).parent().parent().hasClass('section4')){
        $('.input4').val($(this).text());
    }
    else if($(this).parent().parent().hasClass('section5')){
        $('.input5').val($(this).text());
    }
    else if($(this).parent().parent().hasClass('section6')){
        $('.input6').val($(this).text());
    }

    if(!$(this).parent().parent().hasClass('section6')){
        $('html, body').animate({scrollTop: $(this).parent().parent().next().offset().top-500}, 400);
        $(this).parent().parent().next().addClass('show');
    }else {
        $('html, body').animate({scrollTop: $('.next').offset().top-500},400);
    }
});

$('.dog').on('click', function(){
    $('.change-select2-btn').text('독립');
    $('.change-select3-btn').text('노랑');
});

$('.cat').on('click', function(){
    $('.change-select2-btn').text('영리');
    $('.change-select3-btn').text('크림');
});