<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>${page.name}</title>

    <asset:stylesheet href="lapsi/bootstrap/css/bootstrap.css"/>

    <sec:ifAllGranted roles="ROLE_ADMIN">
        <asset:stylesheet href="lapsi/contenttools/content-tools.min.css"/>
        <asset:javascript src="lapsi/admin/admin.js" />
    </sec:ifAllGranted>

</head>
<body>
<nav class="navbar navbar-default navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Lapsi CMS</a>
        </div>
    </div><!-- /.container-fluid -->
</nav>

<div class="container main-content">
    <div class="row">
        <div class="col-sm-12">
            <g:layoutBody/>
        </div>
    </div>
</div>

</body>
</html>
