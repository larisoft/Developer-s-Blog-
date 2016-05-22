<%@include file='/WEB-INF/views/frontend/header.jsp'%>

 <div class='container' >
 <div class='row' >
 <div class='col-md-10 col-md-offset-1 ' style='padding:20px;'>
 
 <div style='text-align:center'>
 <img class='img img-thumbnail' style='display:inline-block;' src='${pageContext.request.contextPath}/shared/serve_image/${user_profile.getAssetId()}'/>
 </div>
 <h1 style='text-align:center; ' class='app-title'>${user_profile.getName()}  ${user_profile.getSurname()}</h1>
 <p style='text-align:justify;'>
 ${user_profile.getAbout()}  
 </p>
 
 <div class='msg-box' style='margin-top:50px;'>
 <p style="margin:10px; text-align:center;"><i class='fa fa-contact'></i> Contact ${user_profile.getName()}</p>

 <p style='text-align:center' class='text-success' id='return_message'> </p> 
 
 <div class='form-group'>
 <label >Your Email:</label>
 <input type='text' name='email' id='email' class='form-control'/> 
 </div>
 
<div class='form-group' >
<label class='control-label'>Your Message</label>
<textarea  id='message' class='form-control' rows="5" cols="5" name='message'></textarea>

<input type='hidden' id ='receiver' value="${user_profile.getId() }"/>

 <div style='text-align:center'>
<button onclick='send_message()' class='btn btn-primary' style='display:inline-block; margin:10px;' >Send Message</button>
</div>
</div> 
</div>
</div>


</div>
</div>


<%@include file='/WEB-INF/views/frontend/footer.jsp'%>

<script type='text/javascript'>

var message_url = '${pageContext.request.contextPath}/send_message';

function send_message(){
	
	var receiver = $("#receiver").val();
	var message = $("#message").val();
	var email = $("#email").val();
	
	if(email.trim().length < 1 || email.indexOf('@')==-1){
		$("#return_message").html("Invalid Email");
		$("#return_message").attr('class', 'text-danger');
		return;
	}
	
	if(message.trim().length < 5){
		
		$("#return_message").html("Invalid Message");
		$("#return_message").attr('class', 'text-danger');
		return;
	}
	var data  = {'receiver': receiver, 'message': message, 'email': email};
	
	console.log("reading " + data.message);
	
	$.post(message_url, data, function(status, response){ 
		$("#return_message").html(status); 
		$("#message").val("");
		$("#email").val("");
		$("#return_message").attr('class', 'text-success');
	});
	
	console.log('sent');
}

</script>