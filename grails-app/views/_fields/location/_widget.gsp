<%@ page import="org.ramanandi.profile.Education" %>

<select id="${property}" name="${property}" class="form-control">
   <g:if test="${value}">
           <option value="${value.name}">${value.name}</option>
   </g:if>
   <g:else>
        <option value="">-- Select --</option>
   </g:else>
</select>
<p class="help-line">Select or enter your location</p>