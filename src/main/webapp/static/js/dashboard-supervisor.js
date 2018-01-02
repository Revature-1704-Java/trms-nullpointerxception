$(function(){
	$('#deny').click(function(){
		let id = $(this).siblings('input').val();
		$('#reimbursementId').val(id);
	});
});