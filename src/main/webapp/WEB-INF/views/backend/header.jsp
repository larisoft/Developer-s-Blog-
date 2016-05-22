
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="springForm"%> 

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Nigerian Software Giant">
    <meta name="author" content="">

    <title>${prefs.getAppTitle()}::Dashboard</title> 
    
    
      <link href="${pageContext.request.contextPath}/resources/ckeditor/plugins/codesnippet/lib/highlight/styles/default.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/resources/ckeditor/plugins/codesnippet/lib/highlight/highlight.pack.js"></script>
   
   
    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/resources/css/sb-admin.css" rel="stylesheet">
  

    <!-- Morris Charts CSS -->
    <link href="${pageContext.request.contextPath}/resources/css/plugins/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->	
    <link href="${pageContext.request.contextPath}/resources/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
 
	 <script src="${pageContext.request.contextPath}/resources/tinymce/tinymce.min.js"></script>
</head>

<body>

</body>


<body>

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a href='${pageContext.request.contextPath}/'><span class="navbar-brand">${prefs.getAppTitle()}</span></a>
            </div>
            <!-- Top Menu Items -->
            <ul class="nav navbar-right top-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-envelope"></i> <b class="caret"></b></a>
                    <ul class="dropdown-menu message-dropdown">
                      <li class="message-preview">
                      <c:forEach items="${prefs.getMessagesFor(user.getId(), messageService)}" var="message">
                            <a href="#">
                                <div class="media">
                                    <span class="pull-left">
                                        <img class="media-object" src="http://placehold.it/50x50" alt="">
                                    </span>
                                    <div class="media-body">
                                        <h5 class="media-heading"><strong>${message.getEmail()}</strong>
                                        </h5>
                                        <p class="small text-muted"><i class="fa fa-clock-o"></i> ${message.getAge() } Ago</p>
                                        <p>${message.getMessage() } </p>
                                    </div>
                                </div>
                            </a>
                            </c:forEach>
                        </li>
                        <li class="message-footer">
                            <a href="#"> </a>
                        </li>
                    </ul>
                </li>
                
                <c:choose>
                <c:when test='${prefs.getUnreadMessages(user.getId(), messageService)  >0}'>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-bell" style='color:red; '></i> <b class="caret"></b></a>
                    <ul class="dropdown-menu alert-dropdown">
                        <li> 
                            <a href="#">${prefs.getUnreadMessages(user.getId(), messageService)} New Message(s) <span class="label label-default"></span></a>
                        </li> 
                    </ul>
                </li>
                </c:when>
                </c:choose>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> ${user.getName()}  ${user.getSurname()}<b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="${pageContext.request.contextPath}/user/view_user/${user.getId()}"><i class="fa fa-fw fa-user"></i> Profile</a>
                        </li>
                  
                        <li class="divider"></li>
                        <li>
                            <a href="${pageContext.request.contextPath}/shared/logout"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                        </li>
                    </ul>
                </li>
            </ul> 
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav side-nav"> 
                 		
                 		<c:choose>
                 		<c:when test="${module.toString() eq 'app'}">
                 			<li class='active'>
                 		</c:when>
                 		<c:otherwise>
                 			<li>
                 		</c:otherwise>
                 		</c:choose>
                 		
                         <a href="${pageContext.request.contextPath }/portfolio/"><i class="fa fa-fw fa-table"></i> My Apps <i class="fa fa-fw fa-caret-down"></i> </a>
                         </li>   
                            <c:choose>
                 		<c:when test="${sub_section eq 'manage_app'}">
                 			<li class='sub-menu active'>
                 		</c:when>
                 		<c:otherwise>
                 			<li class='sub-menu'>
                 		</c:otherwise>
                 		</c:choose>
                 		     <a  href="${pageContext.request.contextPath }/portfolio/view_apps">Apps</a>
                            </li>
                            
                            <c:choose>
                 		<c:when test="${sub_section eq 'platform'}">
                 			<li class='sub-menu active'>
                 		</c:when>
                 		<c:otherwise>
                 			<li class='sub-menu'>
                 		</c:otherwise>
                 		</c:choose>
                 		
                                <a href="${pageContext.request.contextPath }/portfolio/view_platforms">Platforms</a>
                            </li>
                            
                            <c:choose>
                 		<c:when test="${sub_section eq 'category'}">
                 			<li class='sub-menu active'>
                 		</c:when>
                 		<c:otherwise>
                 			<li class='sub-menu'>
                 		</c:otherwise>
                 		</c:choose>   <a href="${pageContext.request.contextPath }/portfolio/view_categories">Categories</a>
                            </li> 
                             
                            
                            <c:choose>
                 		<c:when test="${module.toString() eq 'blog'}">
                 			<li class='active'>
                 		</c:when>
                 		<c:otherwise>
                 			<li>
                 		</c:otherwise> 
                 		</c:choose> 
                         <a href="${pageContext.request.contextPath }/blog/"><i class="fa fa-fw fa-table"></i> Blog <i class="fa fa-fw fa-caret-down"></i> </a>
                         </li>    
                            <c:choose>
                 		<c:when test="${sub_section eq 'article'}">
                 			<li class='sub-menu active'>
                 		</c:when>
                 		<c:otherwise>
                 			<li class='sub-menu'>
                 		</c:otherwise>
                 		</c:choose>
                 		     <a  href="${pageContext.request.contextPath }/blog/view_articles">Articles</a>
                            </li> 
                            
                            
                            
                             <c:choose>
                 		<c:when test="${module.toString() eq 'bug_section'}">
                 			<li class='active'>
                 		</c:when>
                 		<c:otherwise>
                 			<li>
                 		</c:otherwise> 
                 		</c:choose> 
                         <a href="${pageContext.request.contextPath }/bug"><i class="fa fa-fw fa-table"></i>My Bugs<i class="fa fa-fw fa-caret-down"></i> </a>
                         </li>    
                          <c:choose>
                 		<c:when test="${sub_section eq 'bug'}">
                 			<li class='sub-menu active'>
                 		</c:when>
                 		<c:otherwise>
                 			<li class='sub-menu'>
                 		</c:otherwise>
                 		 </c:choose> 
                                <a href="${pageContext.request.contextPath }/bug/view_bugs"> Manage Bugs </a>
                            </li> 
                           <c:choose>
                 		<c:when test="${module.toString() eq 'settings_section'}">
                 			<li class='active'>
                 		</c:when>
                 		<c:otherwise>
                 			<li>
                 		</c:otherwise> 
                 		</c:choose> 
                         <a href="${pageContext.request.contextPath }/bug"><i class="fa fa-fw fa-table"></i>Settings<i class="fa fa-fw fa-caret-down"></i> </a>
                         </li>    
                              <c:choose>
                 		<c:when test="${sub_section eq 'bug'}">
                 			<li class='sub-menu active'>
                 		</c:when>
                 		<c:otherwise>
                 			<li class='sub-menu'>
                 		</c:otherwise>
                 		 </c:choose> 
                                <a href="${pageContext.request.contextPath }/bug/view_bugs"> Reinstall Portfolio </a>
                            </li> 
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </nav>
        <body>
         
        </body>
        <div id="placeholder">
</html>
