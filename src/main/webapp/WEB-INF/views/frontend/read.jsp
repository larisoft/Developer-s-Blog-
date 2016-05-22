
 <%@include file="/WEB-INF/views/frontend/header.jsp" %>
<div class="container">
  <div class="row"> 
    <div class="col-md-12"> 
      <div class="panel">
        <div class="panel-body"> 
   <div class='row'>
   <div class='section-head'> <p class='section-title col-md-offset-2'> ${content.getTitle()}</p></div>     
   </div>
  	
  	<div class='article'> 
  	<p>
  	${content.getContent()}
  	</p>
                                                
   	</div> 
   	
   	

<%@ include file="/WEB-INF/views/frontend/comment.jsp" %>
  </div>
</div>
          </div>
      </div>                                         
 <%@include file="/WEB-INF/views/frontend/footer.jsp" %>