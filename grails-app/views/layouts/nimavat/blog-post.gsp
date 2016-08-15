<%
    //= template_name blog_post
%>

<g:applyLayout name="nimavat/main" model="[page:page]">
    <h2 class="blog-post-title">${page.name}</h2>
    <p class="blog-post-meta"><g:formatDate date="${page.dateCreated}" format="MMM dd yyyy"/> </p>
    <lp:partial page="${page}" partial="body" class="blog-post">
        <p>Blog post content</p>
    </lp:partial>
</g:applyLayout>