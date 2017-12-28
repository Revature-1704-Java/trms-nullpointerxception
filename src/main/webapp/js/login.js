$(document).ready(function(){
	
	
});

function login(){
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(this.readyState == 4 && this.status == 200){
			console.log("Change to dashboard");
		}else if(this.readyState == 3){
			console.log("Getting data");
		}else if(this.readyState == 2){
			console.log("Sent");
		}else if(this.readyState == 1){
			console.log("Opened");
		}
	};
	xhr.open("POST", "login");
	xhr.send();
}