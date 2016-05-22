 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="springForm"%> 
 <div class='row'>
 <div class='col-md-8'>
<div class="detailBox">
    <div class="titleBox">
      <label>${comments.size() } Comments</label>
        <button type="button" class="close" aria-hidden="true">&times;</button>
    </div>
 	
 	<div class='action-box'>
 	<ul class="commentList">
 	<div class="commentText">
	<c:forEach items="${comments}" var="comment">
	<li class='comment-text'> 
 
		${comment.getComment()}
		
	  </p> <span class="date sub-text">${comment.getAge()} Ago</span>
	</li>
	</c:forEach>
 	</div>
 	</ul>
	</div>
	
	<div class='comment-form'>
	 <springForm:form class="form-horizontal" action="${pageContext.request.contextPath}/shared/comment/add" commandName="comment">
	  		<div class='form-group comment'>
   			<label> Your Email: </label>
   			<springForm:input path='email' cssClass='form-control'/>  
   			</div>
   			
            <div class="form-group comment">
            <label> Your Comment</label>
         	<springForm:textarea path="comment" name='content' cssClass="form-control textarea" cols='5' rows='4'/> 
            </div>
            
            <springForm:input type='hidden' path='content_id'/>
            <springForm:input type='hidden' path='module_id'/>
            <springForm:input type='hidden' path='url'/>
            <springForm:input type="hidden" path="date"/>
            
            <div class="form-group comment">
                <button class="btn btn-default">Submit comment</button>
            </div>
        </springForm:form> 
        </div>
</div>
 
</div>
</div>

  
   
   	
      
 	 
 <script type='text/javascript' src="${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js">
 	
 </script>
 
 <script>
 CKEDITOR.replace("content");
 </script>