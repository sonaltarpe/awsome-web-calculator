
var joinedUser = false;

$('.user-input-container').hide();
$('.user-input-show').hide();
$('.result-container').hide();

$('#join').on("click", ()=>{
	$('.user-input-container').show();
	$('.user-input-show').show();
	joinedUser=true;
	$('#joined-user').text($('#user-name').val());
	$('.user-join-container').hide();
})

$('#calculate').on("click", ()=>{
	$('.result-container').show();
})
