<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../common/meta.jsp"%>
<%@include file="../common/css.jsp"%>
</head>
<body class="hold-transition login-page">
	<div class="login-box">
		<div class="login-logo">
			<a href="../../index2.html"><b>E-PUB</b> Learning</a>
		</div>
		<!-- /.login-logo -->
		<div class="card">
			<div class="card-body login-card-body">
				<p class="login-box-msg">Sign in to start your session</p>

				<form action="../perform_login" method="post">
					<div class="input-group mb-3">
						<input type="text" class="form-control" placeholder="Username"
							name="username">
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-user"></span>
							</div>
						</div>
					</div>
					<div class="input-group mb-3">
						<input type="password" class="form-control"
							placeholder="*************" name="password">
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-lock"></span>
							</div>
						</div>
					</div>
					<div class="row">
<!-- 						<div class="col-8"> -->
<!-- 							<div class="icheck-primary"> -->
<!-- 								<input type="checkbox" id="remember"> <label -->
<!-- 									for="remember"> Remember Me </label> -->
<!-- 							</div> -->
<!-- 						</div> -->
						<!-- /.col -->
						<div class="col-4">
							<button type="submit" class="btn btn-primary btn-block">Sign
								In</button>
						</div>
						<!-- /.col -->
					</div>
				</form>

				<!-- 				<div class="social-auth-links text-center mb-3"> -->
				<!-- 					<p>- OR -</p> -->
				<!-- 					<a href="#" class="btn btn-block btn-primary"> <i -->
				<!-- 						class="fab fa-facebook mr-2"></i> Sign in using Facebook -->
				<!-- 					</a> <a href="#" class="btn btn-block btn-danger"> <i -->
				<!-- 						class="fab fa-google-plus mr-2"></i> Sign in using Google+ -->
				<!-- 					</a> -->
				<!-- 				</div> -->
				<!-- 				/.social-auth-links -->

				<p class="mb-1">
					<a href="forgot-password.html">I forgot my password</a>
				</p>
				<p class="mb-0">
					<a href="register.html" class="text-center">Register a new
						membership</a>
				</p>
			</div>
			<!-- /.login-card-body -->
		</div>
	</div>
	<!-- /.login-box -->

	<!-- jQuery -->
	<script src="plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap 4 -->
	<script src="plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- AdminLTE App -->
	<script src="dist/js/adminlte.min.js"></script>

</body>
</html>