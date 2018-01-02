$(function(){
	$('#deny').click(function(){
		let id = $(this).siblings('input').val();
		$('#reimbursementId').val(id);
	});
	
	$('#logout').click(function(){
		$.ajax({
			url:'logout',
			method:'GET',
			success: function(){
				location.reload(true);
			}
		});
	});
});