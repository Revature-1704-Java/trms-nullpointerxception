$(function(){
//	$(document).on('submit','#newReimbursement',(e)=>{
//		e.preventDefault();
//		$.ajax({
//			url:'newreimbursement',
//			method: 'POST',
//			data: new FormData(document.getElementById('newReimbursment')),
//			/*data: {cost:123},*/
//			cache: false,
//			contentType: false,
//			//processData: false,
//			success: function(data){
//				console.log("success");
//			}
//		});
//	});
	
//	$('#newReimbursement').submit(function(e){
//		e.preventDefault();
//		$.ajax({
//			url:'newreimbursement',
//			method: 'POST',
//			//data: new FormData(document.getElementById('newReimbursment')),
//			data: '{cost:123}',
//			cache: false,
//			contentType: false,
//			//processData: false,
//			success: function(data){
//				console.log('Success');
//			}
//		});
//	});
	document.getElementById('newReimbursement').addEventListener('submit', function(e){
		e.preventDefault();
		var data = new FormData(this);
		var xhr = new XMLHttpRequest();
		xhr.open('POST','newreimbursement', true);
		xhr.onload = function(){
			console.log('Success');
		}
		xhr.send(data);
		
	});
});

