<%
    //= template_name Template with side bar
%>

<g:applyLayout name="javaprimer/main" model="[page:page]">
    <div class="row">
        <div class="col-sm-2">
            <lp:block name="ga"/>
        </div>
        <div class="col-sm-10">
            <div class="panel panel-default">
                <div class="panel-heading">Dashboard</div>
                <div class="panel-body">
                    <lp:partial page="${page}" partial="body" editMode="${true}">
                        <p>As soon as you've clicked the pencil icon the page will be editable and two new elements will appear; the toolbox and the inspector bar. The toolbox by default will appear in the upper left corner of the page, but it can be dragged to wherever you like. The inspector bar will appear fixed to the bottom of the page (it will be empty until an element on the page is selected).</p>
                    </lp:partial>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    New
                </div>
                <div class="panel-body">
                    <lp:partial page="${page}" partial="panel" editMode="${true}">
                        <table class="table">
                            <thead>
                                <th>Name</th>
                                <th>value</th>
                            </thead>
                            <tbody>
                                <td>Sample 1</td>
                                <td>Value 1</td>
                            </tbody>
                        </table>
                    </lp:partial>
                </div>
            </div>
        </div>
    </div>
</g:applyLayout>