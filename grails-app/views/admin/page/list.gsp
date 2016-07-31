<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="admin">
    <title></title>
</head>

<body>
<div class="panel panel-default">
    <div class="panel-heading">
        Page List
        <p class="pull-right">
            <g:link action="create" namespace="admin" class="btn btn-primary btn-xs">Create new page</g:link>
        </p>
    </div>
    <div class="panel-body">
        <table class="table table-striped">
            <thead>
                <th>Id</th>
                <th>Title</th>
                <th>Url</th>
                <th>Published</th>
                <th></th>
            </thead>
            <tbody>
            <g:each in="${entityList}" var="page">
                <tr>
                    <td>${page.id}</td>
                    <td>${page.name}</td>
                    <td><lp:pageLink page="${page}"/></td>
                    <td>${page.published}</td>
                    <td>
                        <g:link class="btn btn-primary btn-xs" controller="page" namespace="admin" action="edit" id="${page.id}">Edit</g:link>
                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>

    </div>
</div>
</body>
</html>