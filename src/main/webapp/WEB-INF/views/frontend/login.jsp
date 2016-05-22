
 <%@include file="/WEB-INF/views/frontend/header.jsp" %>
<div class="container">
  <div class="row">
    
    <div class="col-md-5"> 
      
      <div class="panel">
        <div class="panel-body"> 
   
   <div class='row'>
    <div class='section-head' style='text-align:center;'> <p style='display:inline-block;' class='section-title'> Login </p></div>     
  </div>
    
    <form action="${pageContext.request.contextPath}/shared/login" method="POST">
    
     <div style='text-align:center; margin-top:4px;'>
     <p style='display:inline-block;' class='text text-danger'>${login_error} </p>
     </div>
     
    <div class="form-group">
    <label class='form-label'>Email:</label>
    <input type='email' class='form-control' name='email'/>
    </div>
    
    <div class='form-group'>
    <label class='form-label'>Password:</label> 
    <input type='password' class='form-control' name='password'/>
    </div>
    <button type='submit' class='btn btn-primary'>Submit </button>
    </div>
    </form>
   
   
   <div style='margin:5px;	'>
    <a href="${pageContext.request.contextPath}/shared/forgot_password"> <p class='text text-info'>Forgot Password ? </p> </a>
   </div>
              
       
        
    <div class='form-group' style='margin:14px;'>
    <label class='form-label'>Email:</label> 
    <input type='email' class='form-control' name='password' placeholder='Email to reset password with'/>
    </div>
    <button type='submit' style='margin:14px;' class='btn btn-primary'>Reset My Password </button>       
              
                                                
   	</div> 
  </div>
  
  
    <div class="col-md-5 col-md-offset-1"> 
      
      <div class="panel">
        <div class="panel-body"> 
   
   <div class='row'>
   <div class='section-head' style='text-align:center;'> <p style='display:inline-block;' class='section-title'> Sign Up </p></div>     
   </div>
    
    <springForm:form action="${pageContext.request.contextPath}/shared/sign_up" method="POST" commandName="user">
    
    <div class="form-group">
    <label class='form-label'>Username:</label>
    <springForm:input type='text' class='form-control' path='username'/>
    <springForm:errors path='username' cssClass='text text-danger'/>
    </div>
    
    <div class='form-group'>
    <label class='form-label'>Email</label> 
    <springForm:input type='email' class='form-control' path='email'/>
     <springForm:errors path='email' cssClass='text text-danger'/>
    </div>
    
    <div class='form-group'>
    <label class='form-label'>Password:</label> 
    <springForm:input type='password' class='form-control' path='password'/>
     <springForm:errors path='password' cssClass='text text-danger'/>
    </div>
    
     <div class='form-group'>
    <label class='form-label'>Confirm Password:</label> 
    <springForm:input type='password' class='form-control' path='confirm_password'/>
     <springForm:errors path='password' cssClass='text text-danger'/>
    </div>
    
    <button type='submit' class='btn btn-primary'>Submit </button>
   
    
    <springForm:input type='hidden' path="validated" value="0"/>
    <springForm:input type='hidden' path="date"/> 
    
      </div>
    </springForm:form>
   
                                                
   	</div> 
  </div>
</div>
          </div>
      </div>                                         
 <%@include file="/WEB-INF/views/frontend/footer.jsp" %>