<%@page defaultCodec="none" %>
<g:each in="${blogs}" var="page">
	<div class="blog-post">
		<h2 class="blog-post-title">
			<lp:pageLink page="${page}"/>
		</h2>
		<p class="blog-post-meta"><g:formatDate date="${page.dateCreated}" format="MMM dd yyyy"/> </p>

		${page.partial("body")}

	</div>
</g:each>