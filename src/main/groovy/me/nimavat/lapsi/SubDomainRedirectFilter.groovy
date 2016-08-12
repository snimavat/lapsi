package me.nimavat.lapsi

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j

import javax.servlet.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Slf4j
@CompileStatic
class SubDomainRedirectFilter implements Filter {
	private static final String LOCALHOST = "localhost"
	private static final String WWW = "www"

	SiteNameResolver siteNameResolver

	@Override
	void init(FilterConfig filterConfig) throws ServletException { }

	@Override
	void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req
		HttpServletResponse response = (HttpServletResponse) res

		String server = request.serverName.toLowerCase()

		if(server != LOCALHOST && !server.startsWith(WWW) && siteNameResolver.getSubDomain(request) == null) {
			StringBuilder sb = new StringBuilder()
			sb.append(request.scheme)
			sb.append("://www.")
			sb.append(siteNameResolver.getDomainName(request)) //root domain name - eg domain.com
			if(request.serverPort != 80) {
				sb.append(":")
				sb.append(request.serverPort)
			}
			sb.append(request.requestURI)

			String redirectUrl = sb.toString()

			log.debug "Redirecting non www request to $redirectUrl"
			response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY)
			response.setHeader("Location", redirectUrl)

		} else {
			chain.doFilter(req, res)
		}

	}

	@Override
	void destroy() { }
}
