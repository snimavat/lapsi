
<g:if test="${pageProperty(name:'page.multipart') == 'true'}">
	<g:set var="multipart" value="true" />
</g:if>

<form ${multipart ? 'enctype=multipart/form-data' : ''} method="post" action="${createLink(action:'save', namespace:'admin')}">

	<g:if test="${actionName == 'edit'}">
		<g:hiddenField name="id" value="${entityInstance?.id}" />
	</g:if>
	<g:layoutBody/>

	<div class="form-actions">
		<button type="submit" class="btn btn-primary" value="save">
			<i class="icon-ok icon-white"></i>
			<g:if test="${actionName == 'edit'}">Update</g:if>
			<g:else>Save</g:else>
		</button>

		<g:link action="list" namespace="admin" class="btn btn-warning">
			<i class="icon-arrow-left icon-white"></i> Cancel
		</g:link>
	</div>
</form>