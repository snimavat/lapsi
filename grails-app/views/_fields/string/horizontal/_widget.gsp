
<g:if test="${constraints.widget == 'textarea'}">
    <textarea id="${property}" name="${property}" class="form-control ${clazz}">${value}</textarea>
</g:if>
<g:else>
    <g:if test="${constraints.password}">
        <input type="password" id="${property}" name="${property}" value="${value}" class="form-control ${clazz}"/>
    </g:if>
    <g:else>
        <input type="text" id="${property}" name="${property}" value="${value}" class="form-control ${clazz}"/>
    </g:else>
</g:else>
<g:if test="${errors}">
    <g:each in="${errors}">
        <span class="help-block">${it}</span><br/>
    </g:each>
</g:if>