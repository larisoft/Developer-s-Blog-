
<%@ include  file="/WEB-INF/views/backend/header.jsp" %> 
<div class='container'>
<div class='row first_item'> 
 <h1 class='app-title'> App Assets </h1> 
 
 <c:forEach items="${errors}" var="error">
 	<p style='text-align:center; color:red;'> <c:out value="${error}"/> </p>
 </c:forEach>
 


<form method='post' enctype="multipart/form-data" action="${pageContext.request.contextPath}/portfolio/receive_file">

<div class='col-md-2 col-md-offset-2'> 
<div class='file-container'> 
<p class='asset-title' style='text-align:center'> App Screenshot 1</p>   
<div class='preview-file'  "> 
 <input type='file' name='app_image' class='transparent-file' onchange="prepareUpload(this, '{name: \'app_image1\', max_size:\'1048576\', ext :\'jpg,png\'}')">
 <div class='file-text' id='app_image1_error' >Must be less than 1MB <br/> <small>click to change </small></div> 
</div>  
</div> 
 
 </div>
 
 
 
 
 
 <div class='col-md-2 '> 
<div class='file-container'> 
<p class='asset-title' style='text-align:center;'> App Screenshot 2 <p> </p>  

<div class='preview-file'  > 
 <input type='file' name='app_image' class='transparent-file' onchange="prepareUpload(this, '{name: \'app_image2\', max_size:\'1048576\', ext :\'jpg,png\'}')">
 <div class='file-text' id='app_image2_error' >Must be less than 1MB <br/> <small> click to change </small></div> 
</div>  
</div> 
 
 </div>
 
  <div class='col-md-2 '> 
<div class='file-container'> 
<p class='asset-title' style='text-align:center;'> App Screenshot 3 <p> </p>  

<div class='preview-file'  > 
 <input type='file' name='app_image' class='transparent-file' onchange="prepareUpload(this, '{name: \'app_image3\', max_size:\'1048576\', ext :\'jpg,png\'}')">
 <div class='file-text' id='app_image3_error' >Must be less than 1MB <br/> <small> click to change </small></div> 
</div>  
</div> 
 
 </div>
 
 
   <div class='col-md-2'> 
<div class='file-container'> 
<p class='asset-title' style='text-align:center;'> App Screenshot 4 <p> </p>  

<div class='preview-file'  "> 
 <input type='file' name='app_image' class='transparent-file' onchange="prepareUpload(this, '{name: \'app_image4\', max_size:\'1048576\', ext :\'jpg,png\'}')">
 <div class='file-text' id='app_image4_error' >Must be less than 1MB <br/> <small> click to change </small></div> 
</div>  
</div>  	
</div>
 
 
 <div class='col-md-2'>
 </div>
 <div class='col-md-12'>
 <input type='hidden' name='id' value='${id}'/>
 
 <center> <button type='submit' class='btn btn-success  '> Finish>> </button> <a href='${pageContext.request.contextPath}/portfolio/skip_upload/${id}' 	> <button type='button' class='btn btn-info'> Skip >> </button> </a> </center>
  
 
</form>
</div> 
 
</div>
</div>

<%@include file="/WEB-INF/views/backend/footer.jsp" %>
<script type='text/javascript'> 
 
function prepareUpload(obj, file_properties){  
	
	//if nothing selected, return now.
	if(obj.files.length<1) return; 
	var properties = eval("("+file_properties+")");
	file = obj.files[0]; 
	
	var validation = validate(file, properties);
	log(validation);
	
	if(validation===true){
		
	 	$("#"+properties.name+"_error").html(file.name + ", " + file.size); 
	
	}
	else{
		
		$("#"+properties.name+"_error").html(validation);
		
	}
	 
}


//validate before upload
function validate(file, properties){   
	log("validating");
	
	if(file.size > properties.max_size){
		return "File is too Large!"; 
	}
	
	log("testing size " + file.size);
	if( file.size == NaN || file.size < 100){
		return "File is empty!";
	}
	
	//break into array to get extension
	ext = file.name.toLowerCase().split(".")[file.name.toLowerCase().split(".").length-1];
	var accepted = properties.ext.split(",");
	
	for(i = 0; i < accepted.length; i++){ 
	if(ext == accepted[i]){
		return true;
	} 	
	}
	
	return "Invalid file Type"; 
	 
}

//perform upload
function upload(data, urls){
	
	$.ajax({
		 url: urls,
		 data: JSON.stringify(data),
		 type: 'POST',
		 async: true,
		 beforeSend: function(xhr){
			 xhr.setRequestHeader("Accept","application/json");
			 xhr.setRequestHeader("Content-Type", "application/json");
			 log("set headers");
		 },
		 success: function(result){
			 log("successful "+ result);
		 },
		 error: function(xhr, status, error){
			 log("Errors " + xhr.responseText);
			 var err  = eval("("+xhr.responseText+")");
			 log(err.Message);
		 }
		 
	});
	  
	 
	log("posted " );
}


function style_error(message){
	
	return "<p style='color:red;'>"+message+"</p>";
}

function log(message){
	
	console.log(message);
}

function updateProgress(control, progress){ 
	var elemBar = document.getElementById(control+"_progressBar");
	var elemText = document.getElementById(control+"_progressText"); 
	
	elemBar.style.width = progress+"%";
	elemText.innerHTML= progress+"%"; 
}


function getTimeEstimate(size){ 
	 size = size/100;  
	 log("Size is " + size)
	 return size;
}

</script>