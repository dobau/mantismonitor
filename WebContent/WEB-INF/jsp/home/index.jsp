<!DOCTYPE html>
<html lang="pt">
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
	
		<link href="${context}/static/css/jquery-ui.css" rel="stylesheet" type="text/css" />
		<link href="${context}/static/css/bootstrap.min.css" rel="stylesheet" type="text/css" >
		<link href="${context}/static/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css" >
		<link href="${context}/static/css/jquery.tagit.css" rel="stylesheet" type="text/css" />
		<link href="${context}/static/css/mantismonitor.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
	
			<c:if test="${not empty message}">
				<div id="msgError" class="alert alert-danger alert-dismissable">${error}</div>
			</c:if>
			<c:if test="${not empty success}">
				<div id="msgSuccess" class="alert alert-success alert-dismissable">${success}</div>
			</c:if>
			
			<!--login modal-->
			<div id="loginModal" class="modal show" tabindex="-1" role="dialog" aria-hidden="true">
			  <div class="modal-dialog">
			  <div class="modal-content">
			      <div class="modal-header">
			          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			          <h1 class="text-center">Login</h1>
			      </div>
			      <div class="modal-body">
			          <form class="form col-md-12 center-block" action="${context}/login" method="POST">
			            <div class="form-group">
			              <input type="text" class="form-control input-lg" name="login" placeholder="Login">
			            </div>
			            <div class="form-group">
			              <input type="password" class="form-control input-lg" name="password" placeholder="Password">
			            </div>
			            <div class="form-group">
			              <button class="btn btn-primary btn-lg btn-block">Sign In</button>
			              <span class="pull-right"><a href="#">Register</a></span><span><a href="#">Need help?</a></span>
			            </div>
			          </form>
			      </div>
			      <div class="modal-footer">
			          <div class="col-md-12">
			          <button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
					  </div>	
			      </div>
			  </div>
			  </div>
			</div>

	</body>
</html>