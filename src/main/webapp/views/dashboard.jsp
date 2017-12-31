<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.revature.beans.*,java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>TRMS | Dashboard</title>
		<meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	    <!-- Bootstrap CSS -->
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
		<link rel="stylesheet" href="css/dashboard.css">
	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="col-sm-12">
					<h1>Success!</h1>
					<h3>
						<%! Employee e; %>
						<% e = (Employee) session.getAttribute("employee"); %>
						<%= e.getEmployeeId() %>
					</h3>
					<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#createReimbursement">Submit New Reimbursement Form</button>
					
					
				</div>
			</div>
		</div>
		<div class="modal fade" id="createReimbursement" tabindex="-1" role="dialog" aria-labelledby="ReimbursementForm" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="modalLabel">New Reimbursement</h5>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="container">
							<div class="row">
								<div class="col-sm-12">
									<form id="newReimbursement" onsubmit="submitNewForm">
										<div class="form-group">
											<label for="formCost">Reimbursement Request Amount</label>
											$<input name="cost" class="form-control" type="number" min="0" id="formCost" aria-describedby="formCostSmall" placeholder="Ex. 249.99" required>
											<small id="formCostSmall">Required</small>
										</div>
										<div class="form-group">
											<label for="formDate">Start Date</label>
											<input name="startDate" class="form-control" type="date" id="formDate" aria-describedby="formDateSmall" required>
											<small id="formDateSmall">Required</small>
										</div>
										<div class="form-group">
											<label for="formTimeMissedHours">Time Missed (hrs)</label>
											<input name="timeMissed" class="form-control" type="number" min="0" id="formTimeMissedDays" min="0" aria-describedby="formTimeMissedHoursSmall">
										</div>
										<div class="form-group">
											<label for="formEventType">Event Type</label>
											<select name="eventType" class="form-control" id="formEventTypes" required>
											<%! ArrayList<EventType> eventTypes; %>
											<% eventTypes = (ArrayList<EventType>) request.getAttribute("eventTypes"); %>
												<option value=""></option>
												<% for(int i = 0; i < eventTypes.size(); i++){ %>
													<option value="<%= eventTypes.get(i).getEventType() %>"><%= eventTypes.get(i).getEventType() %></option>
												<% } %>
											</select>
											<small for="formEventTypes">Required</small>
										</div>
										<div class="form-group">
											<label for="formGradeFormat">GradeFormat</label>
											<%! ArrayList<GradeFormat> gradeFormats; %>
											<% gradeFormats = (ArrayList<GradeFormat>) request.getAttribute("gradeFormats"); %>
											<select name="gradeFormat" class="form-control" id="formGradeFormats" required>
												<option value=""></option>
												<% for(int i = 0; i < gradeFormats.size(); i++){ %>
													<option value="<%=gradeFormats.get(i).getFormat() %>"><%= gradeFormats.get(i).getFormat() %></option>
												<% } %>
											</select>
											<small for="formGradeFormats">Required</small>
										</div>
										<h6 id="eventLocation">Event Location</h6>
										<small for="eventLocation">required</small>
										<div class="form-group">
											<label for="formAddress">Address</label>
											<input name="address" class="form-control" type="text" id="formAddress" aria-describedby="formAddress" required>
										</div>
										<div class="form-row">
											<div class="col">
												<label for="formCity">City</label>
												<input name="city" class="form-control" type="text" id="formCity" aria-describedby="formCity" required>
											</div>
											<div class="col">
												<label for="formZip">Zip</label>
												<input name="zip" class="form-control" type="text" id="formZip" aria-describedby="formZip" required>
											</div>
											<div class="col">
												<label for="formCountry">Country</label>
												<input name="country" class="form-control" type="text" id="forCountry" aria-describedby="formCountry" required>
											</div>
										</div>
										<br/>
										<div class="form-group">
											<label for="formDescription">Description</label>
											<textarea name="description" class="form-control" id="formDescription" max-length="500" required></textarea>
											<small for=formDescription>Required. 500 max characters.</small>
										</div>
										<div class="form-group">
											<label for="formWorkJustification">Work Justification</label>
											<textarea name="workJustification" class="form-control" id="formJustification" max-length="500" required></textarea>
											<small for=formWorkJustification>Required. 500 max characters.</small>
										</div>
										<div class="form-group">
											<label for="formAttachement">Event-related Document</label>
											<input name="attachment" class="form-control" id="formAttachment" type="file" accept=".pdf, .png, .jpeg, .txt, .doc">
										</div>
										<div class="form-group">
											<label for="formApprovalDocument">Approval Document</label>
											<input name="approvalDocument" class="form-control" id="formApprovalDocument" type="file" accept=".msg">
										</div>
										<br>
										<div class="from-group">
											<label for="formProjectedReimbursement">Projected Reimbursement</label>
											<input type="number" class="form-control" id="formProjectedReimbursement" step="0.01" readonly>
										</div>
										<button type="submit" class="btn btn-primary">Submit</button>
										<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
	    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
		<script>
		$(function(){
			
			function submitNewForm(){
				$.ajax({
					url:'newreimbursement',
					method: 'POST',
					data: new FormData(document.getElementById('newReimbursment')),
					cache: false,
					contentType: false,
					processData: false,
					success: function(data, textStatus, jqXHR){
						console.log('Success');
					}
				});
			}
			
			
			function updateProjectedReimbursement(){
				let cost = $("#formCost");
				let eventType = $("#formEventTypes");
				let projectedReimbursement = $("#formProjectedReimbursement");
				let eventTypes= {};
				<% for(int i = 0; i < eventTypes.size(); i++){ %>
					eventTypes["<%= eventTypes.get(i).getEventType() %>"] = <%= eventTypes.get(i).getCoverage() %>;
				<% } %>
				console.log("test");
				projectedReimbursement.val((cost.val() * eventTypes[eventType.val()]).toFixed(2));
				
				
			}
			
			$("#formCost").change(function(){
				if($("#formCost").val() && $("#formEventTypes").val()){
					updateProjectedReimbursement();
				}
			});
			
			$("#formEventTypes").change(function(){
				if($("#formCost").val() && $("#formEventTypes").val()){
					updateProjectedReimbursement();
				}
			});
			
			
		});
		</script>
	</body>
</html>