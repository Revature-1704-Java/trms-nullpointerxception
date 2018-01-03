$(function(){
	$('#deny').click(function(){
		console.log('Test');
		let id = $(this).siblings('input').val();
		$('#reimbursementId').val(id);
		console.log(id);
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