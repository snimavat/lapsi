
    <div class="form-group ${errors ? 'has-error' : ''}">
    <label for="${property}">
        ${label}
        <g:if test="${required}">
            <sup class="mandatory">*</sup>
        </g:if>
    </label>
    ${widget}
</div>
