<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>${page.name}</title>

    <meta name="description" content="${page.metaDescription}">
    <meta name="keywords" content="${page.metaKeywords}">

    <asset:link rel="shortcut icon" href="lapsi/favicon.ico" type="image/x-icon"/>

    <link href='https://fonts.googleapis.com/css?family=Open+Sans+Condensed:700|Lora' rel='stylesheet' type='text/css'>

    <asset:stylesheet href="lapsi/bootstrap/css/bootstrap.css"/>
    <asset:stylesheet href="javaprimer/style.css"/>

    <sec:ifAllGranted roles="ROLE_ADMIN">
        <asset:stylesheet href="lapsi/contenttools/content-tools.min.css"/>
        <asset:javascript src="lapsi/admin/admin.js" />
    </sec:ifAllGranted>

</head>
<body>
<div class="container header">
<div class="col-md-6 col-md-offset-3">
    <div class="row">
        <div class="col-xs-10 col-xs-offset-1" style="text-align: center">
            <h1>Java Primer</h1>
        </div>
    </div>

</div>
</div>
<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <div class="main-content">
                <g:layoutBody/>
            </div>
        </div>
    </div>
</div>

</body>
</html>
