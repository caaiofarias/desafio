<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="TAG" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
    <head>        
        <meta http-equiv="Content-Type" content="text/html;">
        <meta charset = "UTF-8" />
        <title>Criar Usuário</title>
        <TAG:Bootstrap />
        <TAG:css />
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-2"></div>
                <div class="col">
                    <h1>Criar Usuário</h1>
                    <div id="form-div">
                        <div class="form-row" id="main">
                        	<div class="form-group col-md-6">
                        		<label for="email">Email</label>
                        		<input type="email" class="form-control" id="email" placeholder="Email" required>
                        	</div>
                        	<div class="form-group col-md-6">
                        		<label for="password">Senha</label>
                        		<input type="password" class="form-control" id="password" placeholder="Senha" required>
                        	</div>
                        </div>
                       <div class="form-group">
                       		<label for="name">Nome Completo</label>
                       		<input type="text" class="form-control" id="name" placeholder="Digite seu nome">
                       </div>
                       <label>Telefone</label>
                       		<div class="form-row" id="phone-div">
                       			<div class="col-md-2">
	                        		<label for="ddd">DDD</label>
	                        		<input type="text" class="form-control" id="ddd" placeholder="(99)">
	                        	</div>
	                        	<div class="col-md-6">
	                        		<label for="number">Número</label>
	                        		<input type="number" class="form-control" id="number" placeholder="999999999">
	                        	</div>
	                        	<div class="col-md-4">
	                        		<label>Tipo</label>
	                        		<input class="form-control" id="ultimocampo" placeholder="tipo"/>
	                        	</div>
                       		</div>
                       		<div class="form-row" id="more-phones"></div>
                       <input class="btn btn-primary btn-sm" type="submit" value="Cadastrar" id="button" style="margin-top: 20px">
                    </div>
                    <button id="more-button" style="display: inline;"+></button>

                    <button id="less-button" style="display:none">-</button>
                </div>
                <div class="col-3"></div>
            </div>
        </div>
        <TAG:jquery />
    </body>
    <script>
    
    function getCookie(name) {
    	  const value = `; ${document.cookie}`;
    	  const parts = value.split(`; ${name}=`);
    	  if (parts.length === 2) return parts.pop().split(';').shift();
    	}
    
    	const token = getCookie('token')
        let fieldsCount = 1;
        let i = 0;
        const fieldExample = document.querySelector('#phone-div').innerHTML;
        let morePhonesDiv = document.querySelector('#more-phones');
        $("#more-button").click(function (e) {
            e.preventDefault();
            morePhonesDiv.innerHTML = morePhonesDiv.innerHTML + fieldExample;
            fieldsCount++;
            checkLessButton();
        });
        function checkLessButton() {
            if (fieldsCount > 1) {
                document.getElementById('less-button').setAttribute('style', 'display:inline');
            } else {
                document.getElementById('less-button').setAttribute('style', 'display:none');
            }
        }
        $("#less-button").click(function (e) {
            fieldsCount--;
            checkLessButton();
            $('#more-phones').empty();
            console.log(morePhonesDiv);
        });
        
        $("#button").click(function ( e ) {
        	let phones = [] 
        	const name = document.querySelector('#name').value;
        	const email = document.querySelector('#email').value;
        	const password = document.querySelector('#password').value;
        	while (i < fieldsCount ) {
        		let ddd = document.querySelectorAll('#ddd')[i].value
        		let number = document.querySelectorAll('#number')[i].value
        		let type = document.querySelectorAll('#ultimocampo')[i].value
        		phones.push({
        			ddd,
        			number,
        			type
        		})
        		i++
        	}
        	if(!name || !email || !password) {
        		alert("campos vazios");
        		return
        	}
        	const token = getCookie('token')
        	console.log(token)
        	$.ajax({
                url: 'api/users/add',
                type: 'POST',
                async:false,
                contentType: "application/json",
                data:JSON.stringify({name,email,password,phones}),
                headers: {
                	"Authorization":"Bearer " + token,
                },
                success: function(data) {
                    alert(JSON.stringify(data));
                },
                error: function(jqxhr, status, errorMsg) {
                    alert('Failed! ' + errorMsg);
                }
                }).done((data) => {console.log(data)})
        })
    </script>
</html>