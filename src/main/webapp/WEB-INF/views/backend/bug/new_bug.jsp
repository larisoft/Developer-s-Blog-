
<%@include file="/WEB-INF/views/backend/header.jsp" %>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="springForm"%> 

<body>
   <div class='container'>
   	<div class='row first_item'>
   	<div class='col-md-9  col-md-offset-1'>
   	<p class='head-title'> New Bug </p>
   	<springForm:form action="${pageContext.request.contextPath}/bug/new_bug/add"  commandName="bug" method="post">
   	 	
   	 <div class='form-group'>
   	<label> Bug Title </label>
   	<springForm:input path='title' cssClass='form-control'/> 
   	<springForm:errors path="title" cssClass='text-error'/>
   	</div>
   	    
   	
   	 <div class='form-group'>
   	<label> Bug Error Message</label>
   	<springForm:textarea path='error_message' cssClass='form-control' cols='5' rows='5'/>  
   	<springForm:errors path="error_message" cssClass='text-error'/>
   	</div>
   	
   	
   	 <div class='form-group'>
   	<label> Bug Solution </label>
   	<springForm:textarea path='solution' cssClass='form-control' cols='5' rows='5'/> 
   	<springForm:errors path="solution" cssClass='text-error'/>
   	</div>
   	
    <springForm:input path='id' type='hidden' name='id'/>
       
       
    <div class='form-group'>
   	<label> Technologies Involved  </label>
   	<springForm:input path='technologies_involved' cssClass='form-control' placeholder="E.g. C# MVC, JAVA Spring Framework"/> 
   	<springForm:errors path="technologies_involved" cssClass='text-error'/>
   	</div>
   	
   	
   	<center> <button type='submit' class='btn btn-portfolio form-control'> Save and Publish >> </button> </center>
   	</div>
   	
   	
   	</springForm:form>
   	
   	</div>
   	</div>
   </div>
   </body>
   
 
 <%@include file="/WEB-INF/views/backend/footer.jsp" %>
 <script type='text/javascript' src="${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js"> 
 </script>
 
 <script> 
 CKEDITOR.replace("error_message");
 CKEDITOR.replace("solution");
 </script>