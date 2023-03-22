/**
 * 
 */

 function applyFunction(){
	 
	 var reqparams = {};
	 reqparams.name = document.getElementById("name").value;
	 reqparams.guardian = document.getElementById("guardian").value;
	 reqparams.qualification = document.getElementById("qualification").value;
	 reqparams.exam = document.getElementById("exam").value;
	 reqparams.mobile = document.getElementById("mobile").value;
	 reqparams.email = document.getElementById("email").value;
	 reqparams.address = document.getElementById("address").value;
	 reqparams.dob = document.getElementById("dob").value;
	 reqparams.district = document.getElementById("district").value;
	 reqparams.state = document.getElementById("state").value;
	 reqparams.country = document.getElementById("country").value;
	 reqparams.aadhar = document.getElementById("aadhar").value;
	 
	 var xhr = new XMLHttpRequest();
	 xhr.onreadystatechange = () => {
		 if(xhr.status == 200 && xhr.readyState == 4){
			 var json = JSON.parse(xhr.responseText);
			 if(json.statusCode == 200 || json.statusCode == 402){
				 alert(json.message);
				document.getElementById("name").value = "";
				document.getElementById("guardian").value = "";
				document.getElementById("mobile").value = "";
				document.getElementById("email").value = "";
				document.getElementById("address").value = "";
				document.getElementById("dob").value = "";
				document.getElementById("state").value = "Tamil Nadu";
				document.getElementById("country").value = "India";
				document.getElementById("aadhar").value = "";
				
			 }
			 else{
				 alert(json.message);
			 }
		 }
	 }
	 xhr.open("POST", "http://localhost:8080/GroupExamApplications/Apply");
	 xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");	 
	 xhr.send(JSON.stringify(reqparams));
	 console.log(reqparams);
	 
 }