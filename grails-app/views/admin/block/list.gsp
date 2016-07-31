<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="admin">
    <title></title>
</head>

<body>
<div class="panel panel-default">
    <div class="panel-heading">
        Block List
        <p class="pull-right">
            <g:link action="create" namespace="admin" class="btn btn-primary btn-xs">Create new</g:link>
        </p>
    </div>
    <div class="panel-body">
        <table class="table table-striped">
            <thead>
                <th>Id</th>
                <th>Name</th>
                <th></th>
            </thead>
            <tbody>
            <g:each in="${entityList}" var="block">
                <tr>
                    <td>${block.id}</td>
                    <td>${block.name}</td>
                    <td>
                        <g:link class="btn btn-primary btn-xs" controller="block" namespace="admin" action="edit" id="${block.id}">Edit</g:link>
                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>

    </div>
</div>
</body>
</html>