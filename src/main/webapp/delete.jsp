<%@page import="com.desafio.rest.model.User"%>
<%@page import="com.desafio.rest.dao.*"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="TAG" tagdir="/WEB-INF/tags" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Apagar Usuário</title>
        <TAG:Bootstrap />
        <TAG:css />
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col">
                    <h1>Apagar Usuário</h1>
                    <% UserDao userService = new UserDao();  %>
                    <% ArrayList<User> temp = (ArrayList<User>) userService.findAll();  %>

                    <table class="table table-striped table-hover">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col">Nome</th>
                                <th scope="col">E-mail</th>
                                <th scope="col">Telefones (ddd/número/tipo)</th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (int index = 0; index < temp.size(); index++) {%>
                            <tr id=" <%=temp.get(index).getId()%> ">
                                <td>
                                    <%= temp.get(index).getName() %>
                                </td>
                                <td>
                                    <%= temp.get(index).getEmail()%>
                                </td>
                                <td>
                                    <% for (int telefoneIndex = 0; telefoneIndex < temp.get(index).getPhones().size(); telefoneIndex++) {%>
                                    (<%= temp.get(index).getPhones().get(telefoneIndex).getDdd()%>)                
                                    <%= temp.get(index).getPhones().get(telefoneIndex).getNumber() %> - 
                                    <%= temp.get(index).getPhones().get(telefoneIndex).getType() %>                    
                                    <% }%>
                                </td>
                                <td>
                                    <button class="delete-button btn btn-sm btn-secondary" id="<%= temp.get(index).getId()%>">Apagar</button>
                                </td>
                            </tr>   
                        </tbody>
                        <% }%>
                    </table>
                </div>                 
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
        $(".delete-button").bind('click', function (a) {
            $.ajax({
                method: "DELETE",
                url: `api/users/delete/${a.currentTarget.id}`,
                headers: {
                	"Authorization":"Bearer " + token,
                },
            }).done(function () {
                location.reload();
            }).fail(function (err) {
            	alert(err.statusText)
                console.log(err);
            });
        })
    </script>
</html>
