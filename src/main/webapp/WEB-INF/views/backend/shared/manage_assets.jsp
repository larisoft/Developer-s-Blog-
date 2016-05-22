 	<div class='row'> 
   
 	<div class='col-md-9 col-md-offset-1'> 

 		<h4 style='text-align:center; margin-top:50px;'> Images ${id}  </h4> <br/>
 	<p>To Add Images </p>
 	<ol>
 	<li> Save your write up </li>
 	<li> Click 'Add Images' below  and in the next page, mark the pictures you want to insert and click 'Done' </li>
 	<li> When you are back to this page, each image is listed below with its url. Click the insert image icon, copy and paste the url of the desired image into the dialog that pops up</li>
 	<li> When you paste each url in the dialogue, you should see a preview of the image load. If not, the url is incorrect. Copy and paste, do not try to type it!" </li>
 	<li> The image will appear at the point in your write up where you did the "Insertion". </li>
 	<li> Remove the pictures  below (the ones outside the write up). They are useless now you've inserted them in your write up. </li>
 	</ol> 
 	<div style= ' height:150px; overflow:auto;	'>
 	
 	<div class='col-md-3 no-decoration'>
   <c:choose>
  <c:when test="${id>0}">
    <div  id='new_form'> 
  <a href='${pageContext.request.contextPath}/shared/add_image/${id}/${module.toString()}/${this_url}'>
<div class=' loading-asset'>
<div class='loading-asset-inner'>
<div class='loading-asset-loading'> 
<div class='transparent-file' >   
 
 </div>
	<i class='fa fa-plus' style='font-size:400%; text-align:center;'></i> <br>
   Click to Add Images 
  </div> 
 </div>
</div>
</a>  
</div> 

</c:when>
<c:otherwise>
<div  id='new_form' onclick="alert('Please save first')"> 
  
<div class=' loading-asset'>
<div class='loading-asset-inner'>
<div class='loading-asset-loading'> 
<div class='transparent-file' >   
 
 </div>
	<i class='fa fa-plus' style='font-size:400%; text-align:center;'></i> <br>
  <p id='message_box'> Click to Add Images </p>
  </div> 
 </div>
</div>
</div> 
</c:otherwise>
</c:choose>
</div>

 <c:forEach items="${assets}" var="asset">
 <div class='col-md-3'> 
	<div class='loading-asset'> 
	<div class='loading-asset-inner' onclick='select_asset(${asset.getId()})' id='obj_${asset.getId()}'> 
	<div class='loading-asset-loading'> 
	<input type='text' value='${pageContext.request.contextPath}/shared/serve_image/${asset.getAssetId()}' class='form-control'/>
	 <img  class='img-preview img-responsive img-thumbnail' id='${asset.getId()}' src='${pageContext.request.contextPath}/shared/serve_image/${asset.getAssetId()}'/>
	   	 </div> 
	</div> 
	</div>  
	 </div>
</c:forEach>
<div class='col-md-1'></div>
   	</div>
   		<div style='text-align:center'>
   		<form method='post' action='${pageContext.request.contextPath}/shared/remove_mapping'>
   	 	<input type='hidden' name='doomed_assets' id='results'/>
   	 	<input type='hidden' name='request_url' value='blog-edit_article-${id}'/> 
   	 	<button style='display:inline-block; margin-top:20px; display:none;' class='btn btn-primary btn-danger' id='remove_mapping' hidden='true'> Remove Selected </button>  
   	 	</form>
   	 </div>
   	 
   	 
   	 </div>
   	 </div>
   	 
   	 
   	 <script type='text/javascript'>
 
 function show_message(message){ 
 $("#message_box").html(message)
 }
 
  
 var selected_items = [];
 function select_asset(obj){
	 
		var id = obj
		
		for(i =0; i < selected_items.length; i++){ 
			if(selected_items[i] == id){
				selected_items.splice(i, 1);
				deselect(obj);
				
				//if all items have been removed, hide the 'remove' button
				if(selected_items.length==0){
					$("#remove_mapping").hide(1000);
				}
				
				console.log("removed "+id);
				
				return;
			}  
		}
		console.log("added " + id);
		
		//if this is the first item; show the 'done' button
		if(selected_items.length==0){
			$("#remove_mapping").show(1000);
		}
		selected_items[selected_items.length] = id;
		
		
		//change the value of the input that will be posted for update
		var results = "";
		for(i = 0; i < selected_items.length; i++){
			
			results+= selected_items[i];
			results+=",";
		} 
		$("#results").val(results); 
		highlight(obj);
	}


	function highlight(id){ 
		var obj = $("#obj_"+id);
		$(obj).css('background-color', 'rgba(152,212,144,0.5)'); 
	}

	function deselect(id){
		var obj = $("#obj_"+id);
		$(obj).css('background-color', 'rgba(152,212,144,0.0)'); 
	}

 </script>