
<input type="number" id="${property}" name="${property}" value="${value}" class="form-control ${clazz}"/>
<g:if test="${errors}">
    <g:each in="${errors}">
        <span class="help-block">${it}</span><br/>
    </g:each>
</g:if>