<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>${page.name}</title>

    <meta name="description" content="${page.metaDescription}">
    <meta name="keywords" content="${page.metaKeywords}">

    <asset:stylesheet href="lapsi/bootstrap/css/bootstrap.css"/>
    <asset:stylesheet href="nimavat_me/blog.css"/>

    <sec:ifAllGranted roles="ROLE_ADMIN">
        <asset:stylesheet href="lapsi/contenttools/content-tools.min.css"/>
        <asset:javascript src="lapsi/admin/admin.js" />
    </sec:ifAllGranted>

</head>

<body>

<!--
<div class="blog-masthead">
    <div class="container">
        <nav class="blog-nav">
            <a class="blog-nav-item active" href="#">Home</a>
            <a class="blog-nav-item" href="#">New features</a>
            <a class="blog-nav-item" href="#">Press</a>
            <a class="blog-nav-item" href="#">New hires</a>
            <a class="blog-nav-item" href="#">About</a>
        </nav>
    </div>
</div>
-->

<div class="container" style="margin-top: 40px;">

    <!--
    <div class="blog-header">
        <h1 class="blog-title">The Bootstrap Blog</h1>
        <p class="lead blog-description">The official example template of creating a blog with Bootstrap.</p>
    </div>
    -->
    <div class="row">

        <div class="col-sm-8 blog-main">

            <g:layoutBody/>

        </div><!-- /.blog-main -->

        <div class="col-sm-3 col-sm-offset-1 blog-sidebar">
            <div class="sidebar-module sidebar-module-inset">
                <h4>About</h4>
                <p>I am a Java/Groovy/AngularJs developer, and here i write about the stuff i have learned recently.</p>
            </div>

            <div class="sidebar-module">
                <h4>Elsewhere</h4>
                <ol class="list-unstyled">
                    <li><a href="http://github.com/snimavat">GitHub</a></li>
                </ol>
            </div>
        </div><!-- /.blog-sidebar -->

    </div><!-- /.row -->

</div><!-- /.container -->

</body>
</html>
