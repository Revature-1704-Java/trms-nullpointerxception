$(function(){
	document.getElementById('newReimbursement').addEventListener('submit', function(e){
		e.preventDefault();
		var data = new FormData(this);
		var xhr = new XMLHttpRequest();
		xhr.open('POST','newreimbursement', true);
		xhr.onload = function(){
			let json = JSON.parse(xhr.responseText);
			console.log(json);
			if(json.hasOwnProperty('ERROR')){
				
				$('#errCreate').prepend(json.ERROR);
				$('#errCreate').show();
				$('#createReimbursement').modal('hide');
				
			}else{
				$('#createReimbursement').modal('hide');
				$('#errCreate').hide();
				$('#accordion').prepend(`
						<div class="card">
							<div class="card-header collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapse${json.reimbursementId}" aria-expanded="false" aria-controls='collapse${json.reimbursementId}' role="tab" id="heading${json.reimbursmentId}">
								<div class="row">
									<div class="col-sm-12">
										<h5>ID: ${json.reimbursementId}</h5>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-6">
										<h5>Projected Reimbursement:  ${json.projectedCost}</h5>
									</div>
									<div class="col-sm-6">
										<h5>Status: ${json.status}</h5>
									</div>
								</div>
							</div>
							<div id="collapse${json.reimbursementId}" class="collapse" role="tabpanel" aria-labelledby="heading${json.reimbursementId}">
								<div class="card-body">
									<div class="row">
										<div class="col-sm-12">
											Submitted: ${json.employeeCreationTime}
										</div>
									</div>
									<br>
									<div class="row">
										<div class="col-sm-3">
											Cost: ${json.cost}
										</div>
										<div class="col-sm-3">
											Event Type: ${json.eventType}
										</div>
										<div class="col-sm-3">
											Grade Format: ${json.gradeFormat}
										</div>
										<div class="col-sm-3">
											Default Passing Grade: ${json.defaultPassingGrade}
										</div>
									</div>
									<br>
									<div class="row">
										<div class="col-sm-6">
											Supervisor Approval Date: ${json.supervisorApprovalDate}
										</div>
										<div class="col-sm-6">
											Department Head Approval Date: ${json.departmentHeadApprovalDate}
										</div>
									</div>
									<br>
									<div class="row">
										<div class="col-sm-6">	
											Adjusted Reimbursement: ${json.adjustedReimbursement}
										</div>
										<div class="col-sm-6">
										
										</div>
									</div>
									<br>
									<div class="row">
										<div class="col-sm-12">
											Description:<br>
											${json.description}
										</div>
									</div>
									<br>
									<div class="row">
										<div class="col-sm-12">
											Work Justification:<br>
											${json.workJustification}
										</div>
									</div>
									<br>
									<div class="row">
										<div class="col-sm-12">
											Time Missed: ${parseInt(json.timeMissed) / 24}d		${parseInt(json.timeMissed) % 24}h
										</div>
									</div>
									<br>
									<div class="row">
										<div class="col-sm-3">
											Address: ${json.address}
										</div>
										<div class="col-sm-3">
											City: ${json.city}
										</div>
										<div class="col-sm-3">
											Zip: ${json.zip}
										</div>
										<div class="col-sm-3">
											Country: ${json.country}
										</div>
									</div>
									<br>
									<div class="row">
										<div class="col-sm-6">
											Deny Reason: ${json.denyReason}
										</div>
										<div class="col-sm-6">
											Inflated Reimbursement Reason: ${json.inflatedReimbursementReason}
										</div>
									</div>
								</div>
							</div>
						</div>
				`);
			}
			
		}
		xhr.send(data);
		
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
	
	$('#cancelReimbursement').click(function(){
		console.log('test');
		$.ajax({
			url:'updatestatus',
			method:'POST',
			data: {reimbursementId: $(this).siblings('input').val(), role: 'employee'},
			success: function(){
				location.reload(true);
			}
				
		});
	});

});

