<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="springForm"%> 

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<meta charset="utf-8">
		<title>Ife-Opipi</title>
		<meta name="generator" content="Bootply" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/resources/font-awesome/css/font-awesome.min.css" rel="stylesheet">
		<!--[if lt IE 9]>
			<script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->
		<link href="${pageContext.request.contextPath}/resources/css/styles.css" rel="stylesheet">
	</head>
	<body>
<header class="navbar navbar-bright navbar-fixed-top" role="banner">
  <div class="container">
    <div class="navbar-header">
      <button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".navbar-collapse">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
    
    </div>
    <nav class="collapse navbar-collapse" role="navigation">
      <ul class="nav navbar-nav">
        <li class='active'>
          <a href="${pageContext.request.contextPath}/">Latest</a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/blog/1">Articles</a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/app/1">Apps</a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/bug/1">Bug Solutions</a>
        </li>
      </ul>
      <ul class="nav navbar-right navbar-nav"> 
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-search"></i></a>
          <ul class="dropdown-menu" style="padding:12px;">
            <form class="form-inline" action='${pageContext.request.contextPath }/search_general' method='post'>
              <button type="submit" class="btn btn-default pull-right"><i class="glyphicon glyphicon-search"></i></button><input name='query' type="text" class="form-control pull-left" placeholder="Search">
            </form>
          </ul>
        </li>
        <c:choose>
          	<c:when test="${user!=null}">
          <li class="dropdown">
          	
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> ${user.getName()}  ${user.getSurname()}<b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="${pageContext.request.contextPath}/user/view_user/${user.getId()}"><i class="fa fa-fw fa-user"></i> Profile</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-fw fa-envelope"></i> Inbox</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/portfolio"><i class="fa fa-fw fa-gear"></i> Dashboard </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="${pageContext.request.contextPath}/shared/logout"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                        </li>
                    </ul>
                </li>
                </c:when>
                
                <c:otherwise>
                	<li>  <a href="${pageContext.request.contextPath}/shared/login" class="navbar-brand">Login</a> </li>
                </c:otherwise>
                </c:choose>
      </ul>
    </nav>
  </div>
</header>

<div id="masthead">  
  <div class="container">
    <div class="row">
      <div class="col-md-7">
      <h1>
        <a style='color:white;' href='${pageContext.request.contextPath}/'>${prefs.getAppTitle()} </a>
          <p class="lead"></p>
        </h1>
      </div>
      <div class="col-md-5">
        <div class="well well-lg"> 
          <div class="row">
            <div class="col-sm-12">
              
			
            </div>
          </div>
        </div>
      </div>
    </div> 
  </div><!-- /cont -->
  
  <div class="container">
    <div class="row">
      <div class="col-md-12">
        <div class="top-spacer"> </div>
      </div>
    </div> 
  </div><!-- /cont -->
  
</div>

