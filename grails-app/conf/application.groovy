import grails.plugin.springsecurity.SecurityConfigType

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'me.nimavat.lapsi.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'me.nimavat.lapsi.UserRole'
grails.plugin.springsecurity.authority.className = 'me.nimavat.lapsi.Role'


grails {
	plugin {
		springsecurity {
			securityConfigType = SecurityConfigType.InterceptUrlMap
			useSecurityEventListener = true

			apf {
				storeLastUsername = true
			}
			logout {
				postOnly = false
			}


		}
	}
}

grails.plugin.springsecurity.interceptUrlMap = [
		//anonymous
		[pattern: '/',               	access: ['permitAll']],
		[pattern: '/error',          	access: ['permitAll']],
		[pattern: '/assets/**',      	access: ['permitAll']],
		[pattern: '/**/js/**',       	access: ['permitAll']],
		[pattern: '/**/css/**',      	access: ['permitAll']],
		[pattern: '/**/images/**',   	access: ['permitAll']],
		[pattern: '/**/favicon.ico', 	access: ['permitAll']],
		[pattern: '/login',          	access: ['permitAll']],
		[pattern: '/login/**',       	access: ['permitAll']],
		[pattern: '/logoff',       	 	access: ['permitAll']],

		//admin mappings
		[pattern: '/console/**', 	 		access: ['ROLE_ADMIN']],
		[pattern: '/plugins/console/**', 	access: ['ROLE_ADMIN']],
		[pattern: '/admin/**', 	 			access: ['ROLE_ADMIN']],

		//all user
		[pattern: '/**', 	 				access: ['permitAll']],
]


grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]

grails.plugin.springsecurity.active = true

grails.plugin.databasemigration.updateOnStart = true
grails.plugin.databasemigration.updateOnStartFileNames = ["changelog.groovy"]

grails.config.locations = ["classpath:lapsi-config.groovy"]

environments {
	test {
		//for test env, delete and recreate db.
		grails.plugin.databasemigration.dropOnStart = true
	}
}

lapsi {
	//The page which should be used as home page
	homePage = "home-page"
}