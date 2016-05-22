
<%@include file="/WEB-INF/views/backend/header.jsp" %>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="springForm"%> 

<body>
   <div class='container'>
   	<div class='row first_item'>
   	<div class='col-md-9  col-md-offset-1'>
   	<p class='head-title'> Edit Profile </p>
   	<springForm:form action="${pageContext.request.contextPath}/user/add"  commandName="user" method="post">
   	
   	 	 <div  id='new_form'> 
 
<div class=' loading-asset'>
<div class='loading-asset-inner'>
<div class='loading-asset-loading'> 
  <a href='${pageContext.request.contextPath}/shared/add_image/${id}/${section}/${this_url}'>
 	<c:choose>
 	<c:when test="${user.getAssetId()>0}">
 	
 	<div style='text-align:center;'>
 	<img style='display:inline-block;' class='img img-responsive' src="${pageContext.request.contextPath}/shared/serve_image/${user.getAssetId()}" /> <br>
 	</div>
 	
 	</c:when>
 	<c:otherwise>
 		<i class='fa fa-user' style='font-size:400%; text-align:center;'></i>
 	</c:otherwise>
 	</c:choose>

 	<br>
	   Click to Change Profile Picture
	    	</a> 
  </div> 
 </div>
</div>
 
</div> 
   	 <div class='form-group'>
   	<label>Name </label>
   	<springForm:input path='name' cssClass='form-control'/> 
   	<springForm:errors path="name" cssClass='text-error'/>
   	</div>
   	    
   	
   	 <div class='form-group'>
   	<label>Surname</label>s
   	<springForm:input path='surname' cssClass='form-control' cols='5' rows='5'/>  
   	<springForm:errors path="surname" cssClass='text-error'/>
   	</div>
   	
   	<div class="form-group">
   	<label class='control-label'>Sex </label>
   	<springForm:select path='sex'  items="${sexes}" class='form-control'> 
   	</springForm:select> 
   	<springForm:errors path='sex' cssClass='text-error'/> 
    </div>
   	
   	
   	 <div class='form-group'>
   	<label>Email</label>
   	<springForm:input path='email' cssClass='form-control'/> 
   	<springForm:errors path="email" cssClass='text-error'/>
   	</div>
   	
    <springForm:input path='id' type='hidden' name='id'/>
     <springForm:input path='assetId' type='hidden' name='assetId'/>
      <springForm:input path='assetId' type='hidden' name='assetId'/>
      <springForm:input path='username' type='hidden' />   
    <div class='form-group'>
   	<label> Phone No:  </label>
   	<springForm:input path='phone' cssClass='form-control' placeholder="E.g. C# MVC, JAVA Spring Framework"/> 
   	<springForm:errors path="phone" cssClass='text-error'/>
   	</div>
   	 
   	 
   	 
   	 
   		<div class='form-group'>
   	<label class='control-label'>About Me (Visible to the world) </label>
   	<springForm:textarea path="about" cssClass="form-control textarea" cols='5' rows='5'/> 
   	<springForm:errors path='about' cssClass='text-error'/> 
   	</div>
   	
   	
   	<center> <button type='submit' class='btn btn-portfolio form-control'> Proceed >> </button> </center>
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
 CKEDITOR.replace("about");
 </script>
