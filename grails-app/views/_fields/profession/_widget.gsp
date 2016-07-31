<%@ page import="org.ramanandi.profile.Profession" %>
<g:select
        name="${property}"
        from="${Profession.list()}"
        value="${value?.id}"
        optionKey = "id"
        optionValue="name"
        noSelection="['':'-- Select --']"
        class="form-control"/>