$(function () {
	$('.answer').on('click',answerFunc);
	$('.previous').on('click',something);
	$('.next').on('click',something);
})

function answerFunc()
{
	$('.highlight').removeClass('highlight');
    $(this).addClass('highlight')
}