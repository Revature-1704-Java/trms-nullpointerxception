$(function(){
	$('#denyForm').submit(function(e){
		$.ajax({
			url: 'updatestatus',
			method: 'POST',
			data: new FormData(document.getElementById('denyForm')),
			success: function(){
				console.log('Success');
			}
		});
	});
	
	$('#approve').click(function(e){
		$.ajax({
			url: 'dashboard',
			method: 'POST',
			data: {role: 'supervisor'}
		});
	});
});