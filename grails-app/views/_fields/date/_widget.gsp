<div class="input-group date" id="datepicker-${property}">
    <input type="text" id="${property}" name="${property}" value="${formatDate(date:value, format:'dd/mm/yyyy')}" class="form-control ${clazz}"/>
    <div class="input-group-addon">
        <span class="glyphicon glyphicon-th"></span>
    </div>
</div>

<g:if test="${errors}">
    <g:each in="${errors}">
        <span class="help-block">${it}</span><br/>
    </g:each>
</g:if>

<script>
    $(function () {
        $('#datepicker-${property}').datepicker({autoclose: true, format:'dd/mm/yyyy'});
    });
</script>