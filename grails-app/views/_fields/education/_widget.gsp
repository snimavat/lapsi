<%@ page import="org.ramanandi.profile.Education" %>
<g:select
        name="${property}"
        from="${Education.list(sort:'id')}"
        value="${value?.id}"
        optionKey = "id"
        optionValue="name"
        noSelection="['':'-- Select --']"
        class="form-control"/>