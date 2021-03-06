<%
    //= template_name javaprimer_default
%>

<g:applyLayout name="javaprimer/main" model="[page:page]">
    <div class="row">
        <div class="col-sm-12">
            <lp:partial page="${page}" partial="body" editMode="${true}">
                <p>As soon as you've clicked the pencil icon the page will be editable and two new elements will appear; the toolbox and the inspector bar. The toolbox by default will appear in the upper left corner of the page, but it can be dragged to wherever you like. The inspector bar will appear fixed to the bottom of the page (it will be empty until an element on the page is selected).</p>
            </lp:partial>
        </div>
    </div>
</g:applyLayout>