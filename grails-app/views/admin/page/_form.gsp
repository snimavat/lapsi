
<div class="form-group">
	<label>Select space</label>
	<g:select name="space.id" from="${me.nimavat.lapsi.LapsiSpace.list()}" class="form-control" optionKey="id" optionValue="name" value="${entityInstance.space?.id}" required="true" />
</div>


<div class="form-group">
	<label>Select template</label>
	<g:select name="template" from="${templates}" class="form-control" optionKey="name" optionValue="name" required="true" value="${entityInstance.template}"/>
</div>

<f:with bean="entityInstance">
	<f:field property="name"/>
	<f:field property="url"/>
	<f:field property="metaKeywords"/>
	<f:field property="metaDescription"/>
</f:with>
