
<%@include file="/WEB-INF/views/backend/header.jsp" %>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="springForm"%> 

<body>
   <div class='container'>
   	<div class='row first_item'>
   	<div class='col-md-9 col-md-offset-1'>
   	<p class='head-title'>   Article  </p>	
   	
   	<springForm:form action="${pageContext.request.contextPath}/blog/new_article/add"  commandName="article" method="post"> 
   	 <div class='form-group'>
   	<label> Article Title </label>
   	<springForm:input path='title' cssClass='form-control'/> 
   	<springForm:errors path="title" cssClass='text-error'/>
   	</div>
   	
   	<div class='form-group'>
   	<label class='control-label'>Article </label>
   	<springForm:textarea path="content" name='content' cssClass="form-control textarea" cols='5' rows='10'/> 
   	<springForm:errors path='content' cssClass='text-error'/> 
   	</div>
   	
   	<div class='form-group'>
   	<label class='control-label'>Tags (For easy searching)</label>
   	<springForm:input  cssClass='form-control' path="tags" placeholder='word1, word2, word3'/> 
   	<springForm:errors path='tags' cssClass='text-error'/> 
   	</div> 
    
    <springForm:input path='id' type='hidden' name='id'/> 
   	<springForm:input path='authorId' type='hidden' name='authorId'/> 
   	
   	<div style='text-align:center'>
   	 <button style='display:block-inline' type='submit' class='btn btn-portfolio '> Submit Article <i class='fa fa-save'></i> </button>
 	</div>
   	 
   	</springForm:form>
   
   	
   	<%@include file='/WEB-INF/views/backend/shared/upload_in_page.jsp' %>
 	
 	</div>
  
 	
 	</div>  
		
		<div class='row'>  
		 
   	 </div>
   	 </div>
   </body>
  
    
 <%@include file="/WEB-INF/views/backend/footer.jsp" %>  
 <script type='text/javascript' src="${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js">
 	
 </script>
 
 <script>
 CKEDITOR.replace("content");
 </script>