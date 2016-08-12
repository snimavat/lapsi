package me.nimavat.lapsi

import groovy.transform.CompileStatic
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestAttributes
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletWebRequest

import javax.servlet.http.HttpServletRequest
import java.util.regex.Matcher
import java.util.regex.Pattern

@Component("siteNameResolver")
@CompileStatic
class SiteNameResolver {
	//matches (xx.)(xx).(com|me|net) etc
	private static final Pattern regex = ~/^(([^.]+)\.)?(([^.]+)\.(com|org|net|me))$/


	public Matcher getMatcher(HttpServletRequest request = null) {

		if(!request) {
			RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes()
			if(requestAttributes instanceof ServletWebRequest) {
				request = requestAttributes.getRequest()
			}
		}

		Matcher matcher = null

		if(request) {
			String server = request.serverName.toLowerCase()
			matcher = (server =~ regex)
		}

		if(matcher.matches()) return matcher

		return null
	}


	public String getSubDomain(HttpServletRequest request = null) {
		Matcher matcher = getMatcher(request)
		if(matcher != null) return matcher.group(2)
		else return null
	}

	public String getDomainName(HttpServletRequest request = null) {
		Matcher matcher = getMatcher(request)
		if(matcher != null) return matcher.group(3)
		else return null
	}

	public String getSiteName(HttpServletRequest request = null) {
		Matcher matcher = getMatcher(request)
		if(matcher != null) return matcher.group(4)
		else return null
	}

}
