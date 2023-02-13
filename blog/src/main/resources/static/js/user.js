let index = {
	init: function(){
		$("#btn-save").on("click", ()=>{
			this.save();
		});
		
		/*$("#btn-login").on("click", ()=>{
			this.login();
		});*/
		
		$("#btn-update").on("click", ()=>{
			this.update();
		});
	},
	
	save: function(){
		//alert("user의 save함수 호출");
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		// console.log(data);
		
		// ajax 통신을 이용하여 3개의 데이터를 josn으로 변경하여 insert 요청
		$.ajax({
			type:"POST",
			url:"/auth/joinProc",
			data:JSON.stringify(data),
			contentType:"application/json;charset=utf-8",
			dataType:"json"
		}).done(function(res){
			if(res.status === 500){
				alert("회원가입에 실패하였습니다.");
			}else{
				alert("회원가입이 완료되었습니다.");
				//console.log(res);
				location.href="/";	
			}
			
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	},
	
		/*login: function(){
		//alert("user의 save함수 호출");
		let data = {
			username: $("#username").val(),
			password: $("#password").val()
		};
		// console.log(data);
		
		// ajax 통신을 이용하여 3개의 데이터를 josn으로 변경하여 insert 요청
		$.ajax({
			type:"POST",
			url:"/api/user/login",
			data:JSON.stringify(data),
			contentType:"application/json;charset=utf-8",
			dataType:"json"
		}).done(function(res){
			alert("로그인이 완료되었습니다.");
			//console.log(res);
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	}*/
	
	update: function(){
		//alert("user의 save함수 호출");
		let data = {
			id: $("#id").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		// console.log(data);
		
		// ajax 통신을 이용하여 3개의 데이터를 josn으로 변경하여 insert 요청
		$.ajax({
			type:"PUT",
			url:"/user",
			data:JSON.stringify(data),
			contentType:"application/json;charset=utf-8",
			dataType:"json"
		}).done(function(res){
			alert("회원수정이 완료되었습니다.");
			//console.log(res);
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	},
}

index.init();