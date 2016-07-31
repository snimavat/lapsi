<%@ page import="org.ramanandi.profile.EducationField" %>
<g:select
        name="${property}"
        from="${EducationField.list(sort:'id')}"
        value="${value?.id}"
        optionKey = "id"
        optionValue="name"
        noSelection="['':'-- Select --']"
        class="form-control"/>