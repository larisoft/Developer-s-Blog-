 
 <div class="row modal-body" id="file_modal">  
 <p>
 <p style='text-align:center'>Images and their urls are listed below. To insert an image in your publication, copy the image url to your clipboard and click the picture icon in the text editor above </p>
 
 </p>
 
 <div class='col-md-3 ' id='new_form'>  
<div class='  loading-asset'>
<div class='loading-asset-inner'>
<div class='loading-asset-loading'> 
<div class='transparent-file' >   
<form method='post' target='ajax_frame' action='${pageContext.request.contextPath}/shared/upload' name='asset_form' enctype='multipart/form-data'> 
 <input type='file' class='transparent-input' name='asset' onchange='prepare_upload(this)'> 
 <input type='hidden' name='moduleId' value='${moduleId}'/>
 <input type='hidden' name='contentId' value='${contentId}'/> 
	</form>
	</div>
		<i class='fa fa-plus' style='font-size:400%; text-align:center;'></i> <br>
   Upload New Image
  </div> 
 </div> 
</div>
</div>  
 
 <c:forEach items="${assets}" var="asset">
 <div class='col-md-3 '> 
	<div class='loading-asset'> 
	<div class='loading-asset-inner' onclick=' '> 
	<div class='loading-asset-loading'> 
	 <img  class='img-preview img-responsive img-thumbnail' id='${asset.getId()}' src='${pageContext.request.contextPath}/shared/serve_image/${asset.getId()}'/>
	  	 </div> 
	</div> 
	</div>
	<input class='form-control' value='${pageContext.request.contextPath}/shared/serve_image/${asset.getId()}'/>
	
	 </div>
</c:forEach> 
 
<iframe id='ajax_frame' name='ajax_frame' src='#' style='width:0px; height:0px; position:absolute;' hidden> </iframe>
</div>
 
  

<script type='text/javascript'>
 
var server_address = "${pageContext.request.contextPath}";

if(window.location.href.indexOf("#uploadModal")!=-1){
	
	$("#uploadModal").modal("show");
}

var files_index = 1;
var selected_items = [];
flag_failed = false;

loading = false;
$(document).ready(function(){  
 
});


function prepare_upload(obj){
	if(loading) return; 
	if(validate(obj)){
	start_upload(); 
	document.asset_form.submit();   
	}
}


function validate(obj){
	
	if(obj.files.length < 1) return false;
	
	var ext = (obj.files[0].name.toLowerCase().split(".")[obj.files[0].name.toLowerCase().split(".").length-1]);
	
	console.log("extension is " + ext);
	
	if(ext!=='jpg' && ext!=='png' && ext!=='gif'){
		return false;
	}
	
	console.log("passed all");
	return true;
	
	
}


function fill_selected_assets(obj){
	   
	var target = $("#assets");
	
	var assets = "";
	for(i =0; i < selected_items.length; i++){
		
		assets+= selected_items[i];
		if(i+1 < selected_items.length){
			assets+=",";
		}
	}
	
	console.log("appending " + assets);
	
	target.val(assets);
	return false;
}

function select_file(){
	alert("selecting..");
}


function start_upload(){ 
	
	if(!flag_failed){
	 new_image = "<div class='col-md-3 '> "+
	"<div class=' loading-asset'> "+
	"<div class='loading-asset-inner'> "+
	" <div class='loading-asset-loading'> " +
	" <img  class='img-preview img-responsive img-thumbnail' id='file_image_"+files_index+"' src='"+server_address+"/resources/img/loader.gif'>" +
	"  <p id='file_text_"+files_index+"'>Uploading... </p>"+
	"  <input type='text' id='asset_id_"+files_index+"' onclick='select_asset(this)' class='transparent-file'> "+
	" </div> " +
	" </div> " +
	" </div>  "+
	" </div> ";
	 
	$(new_image).insertAfter("#new_form"); 
	$("#new_form").hide();
	$("#new_form").show(1000);
	}
	else{
		
		alert("loading again");
	}
	
	loading= true;
}

function stopUpload(status, assetId){
	console.log("status " + status + " assetId " + assetId);
	if(status==1){  
		$("#file_image_"+files_index).attr('src', server_address+'/shared/serve_image/'+assetId);
		$("#asset_id_"+files_index).val(""+assetId);
		console.log("Value now " + $("#asset_id_"+files_index).val());
		$("#file_text_"+files_index).html(""); 
		files_index++;
		flag_failed= false;
	}
	else{
		flag_failed = true;
		$("#file_text_"+files_index).html("<div style='color:red;'> Error Uploading </div>");
	}
	 loading = false;
}



function select_asset(obj){
	 
	var id = obj.value;
	
	for(i =0; i < selected_items.length; i++){ 
		if(selected_items[i] == id){
			selected_items.splice(i, 1);
			deselect(obj);
			
			//if all items have been removed, hide the 'done' button
			if(selected_items.length==0){
				$("#submit_mapping").hide(1000);
			}
			
			console.log("removed "+id);
			
			return;
		}  
	}
	console.log("added " + id);
	
	//if this is the first item; show the 'done' button
	if(selected_items.length==0){
		$("#submit_mapping").show(1000);
	}
	selected_items[selected_items.length] = id;
	
	highlight(obj);
}


function highlight(obj){ 
	$(obj).css('background-color', 'rgba(152,212,144,0.5)'); 
}

function deselect(obj){
	$(obj).css('background-color', 'rgba(152,212,144,0.0)'); 
}


function hide_me(nos){
	$("#file_"+nos).hide(500);
}
</script>