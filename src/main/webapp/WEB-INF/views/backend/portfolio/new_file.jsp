
<%@include file="/WEB-INF/views/backend/header.jsp" %>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="springForm"%> 

<body>
   <div class='container'>
   	<div class='row first_item'>
   	<div class='col-md-9  col-md-offset-1'>
   	<p class='head-title'> App Properties </p>
   	<springForm:form action="${pageContext.request.contextPath}/portfolio/new_file/add"  commandName="app" method="post">
   	 	
   	 <div class='form-group'>
   	<label> App Name </label>
   	<springForm:input path='title' cssClass='form-control'/> 
   	<springForm:errors path="title" cssClass='text-error'/>
   	</div>
   	
   	<div class='form-group'>
   	<label class='control-label'>App Description</label>
   	<springForm:textarea path="description" name='description' cssClass="form-control" cols='5' rows='5'/> 
   	<springForm:errors path='description' cssClass='text-error'/> 
   	</div>
   	
   	<div class='form-group'>
   	<label class='control-label'>Tags (For easy searching)</label>
   	<springForm:input  cssClass='form-control' path="tags" placeholder='word1, word2, word3'/> 
   	<springForm:errors path='tags' cssClass='text-error'/> 
   	</div>
   	
   	<div class='form-group'>
   	<label class='control-label'>App link</label>
   	<springForm:input  cssClass='form-control' path="app_url" placeholder='http:googledrive.com/youraccount/download_link'/> 
   	<springForm:errors path='app_url' cssClass='text-error'/> 
   	</div>
   	
   	<div class='form-group'>
   	<label class='control-label'>Source Code Link (If Available)</label>
   	<springForm:input  cssClass='form-control' path="source_url" placeholder='http:github.com/youraccount/yourapp'/> 
   	<springForm:errors path='source_url' cssClass='text-error'/> 
   	</div>
   	
   	
   	
   	<div class="form-group">
   	<label class='control-label'>Platform </label>
   	<springForm:select path='platformId'  items="${platforms}" class='form-control'> 
   	</springForm:select> 
   	<springForm:errors path='platformId' cssClass='text-error'/> 
    </div>
    
    <div class='form-group'>
    <label class='control-label'>Category</label>
    <springForm:select path='categoryId' items="${categories}"  cssClass='form-control'> 
    </springForm:select>
    <springForm:errors path="categoryId" cssClass="text-error"/>
    </div>
    
    <springForm:input path='id' type='hidden' name='id'/>
    <springForm:input path='authorId' type='hidden' name='authorId'/>
    <springForm:input path='date' type='hidden' name='date'/>
      
   	<div style='text-align:center'>
   	 <button style='display:block-inline;' type='submit' class='btn btn-portfolio  '> Submit <i class='fa fa-save'></i> </button> </center>
   	</div>
   	</div>
   	
   	
   	</springForm:form>
   	
   	</div>
   	 
   	<%@include file="/WEB-INF/views/backend/shared/upload_in_page.jsp"%>
   	
   	
   	<div class='row'> 
		 
   	 </div>
   	</div>
   </div>
   </body>
   
 <%@include file="/WEB-INF/views/backend/footer.jsp" %>
 <script type='text/javascript' src="${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js"> 
 </script>
 
 <script> 
 CKEDITOR.replace("description");
 </script>
  