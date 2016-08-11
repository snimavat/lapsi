<!doctype html>
<html>
<head>
	<title>Login</title>
	<asset:stylesheet href="lapsi/admin/login.css"/>
	<asset:link rel="shortcut icon" href="lapsi/favicon.ico" type="image/x-icon"/>
	<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>


<div class="container">
	<div class="row">
		<form method="post" action="${g.createLink(uri:'/login/authenticate')}">
		<div class="col-md-offset-5 col-md-3">
			<g:if test="${flash.message}">
				<p class="text-danger">${flash.message}</p>
			</g:if>

			<div class="form-login">
				<h4>Welcome back.</h4>
				<input type="text" id="userName" name="username" class="form-control input-sm chat-input" placeholder="username" />
			</br>
				<input type="password" id="userPassword" name="password" class="form-control input-sm chat-input" placeholder="password" />
			</br>
				<div class="wrapper">
					<span class="group-btn">
						<button type="submit" class="btn btn-primary btn-md">login <i class="fa fa-sign-in"></i></button>
					</span>
				</div>
			</div>
		</div>
		</form>
	</div>
</div>

</body>
</html>