
<%@include file="/WEB-INF/views/backend/header.jsp" %>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="springForm"%> 

<body>
   <div class='container'>
   	<div class='row first_item'>
   	<div class='col-md-6  col-md-offset-2'>
   	<p class='head-title'> Platform Properties </p>
   	<springForm:form action="${pageContext.request.contextPath}/portfolio/new_platform/add"  commandName="platform" method="post">
   	 	
   	 <div class='form-group'>
   	<label> Platform Name </label>
   	<springForm:input path='title' cssClass='form-control'/> 
   	<springForm:errors path="title" cssClass='text-error'/>
   	</div>
   	    
   	
    <springForm:input path='id' type='hidden' name='id'/>
       
   	<center> <button type='submit' class='btn btn-portfolio form-control'> Proceed >> </button> </center>
   	</div>
   	
   	
   	</springForm:form>
   	
   	</div>
   	</div>
   </div>
   </body>
   
 <%@include file="/WEB-INF/views/backend/footer.jsp" %>