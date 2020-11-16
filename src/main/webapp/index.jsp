<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="TAG" tagdir="/WEB-INF/tags" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <TAG:Bootstrap />
        <TAG:css />
    </head>
  <body>
  <div class="container">
    <div class="row">
      <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
        <div class="card card-signin my-5">
          <div class="card-body">
            <h5 class="card-title text-center">Log In</h5>
            <form novalidate="novalidate" action="api/authentication/login" method="POST">
              <div class="form-label-group" style="height: auto;">
              	<label for="inputEmail">Email</label>
                <input type="email" id="email" name="email" class="form-control" placeholder="exemplo@email.com" required autofocus>
              </div>
              <div class="form-label-group" style="height: auto;" >
              	<label for="inputPassword">Senha</label>
                <input type="password" id="password" name="password" class="form-control" placeholder="Digite sua senha" required>
              </div>
              <div class="custom-control custom-checkbox mb-3">
              </div>
              <button id="button-login" class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">ENTRAR</button>
            </form>
            <br>
            <div class="alert alert-warning alert-dismissible fade show" id="error-message" hidden="true">
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
<TAG:jquery />
<script type="text/javascript">

$(".close").click(function ( e ){
	console.log("ue")
	let error = document.querySelector("#error-message");
	error.hidden = true
})
/*
$("#button-login").click(function ( e ) {
	var email = document.querySelector("#email").value;
	var password = document.querySelector("#password").value
	$.ajax({
        url: 'api/authentication/login',
        type: 'POST',
        contentType: "application/json",
        data:JSON.stringify({email,password}),
        success: function(data) {
        	const formatted = JSON.parse(data)
        	let error = document.querySelector("#error-message");
        	error.hidden = false;
        	error.innerHTML = error.innerHTML + "<strong>Erro! <strong>"
        	error.innerHTML = error.innerHTML + formatted.message
        },
        error: function(jqxhr, status, errorMsg) {
            alert('Failed! ' + errorMsg);
        }
        }).done((data) => {console.log(data)})
})
*/
</script>
</html>