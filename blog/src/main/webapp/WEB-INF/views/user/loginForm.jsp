<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">

<!-- /auth/loginProc 컨트롤러에서 메소드를 따로 만들지 않고 시큐리티에서 로그인기능을 가로채게 만든다 -->
	<form action="/auth/loginProc" method="post">
		<div class="form-group">
			<label for="username">Username :</label> 
			<input type="text" name="username" class="form-control" placeholder="Enter username" id="username">
		</div>
		<div class="form-group">
			<label for="password">Password :</label> 
			<input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
		</div>

	<button id="btn-login" class="btn btn-primary">로그인</button>	
	</form>
	
	

</div>
<!-- <script src="/js/user.js"></script>  -->
<%@ include file="../layout/footer.jsp"%>


