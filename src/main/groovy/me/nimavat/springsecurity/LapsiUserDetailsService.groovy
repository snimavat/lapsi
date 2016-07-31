package me.nimavat.springsecurity

import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.userdetails.GrailsUser
import grails.plugin.springsecurity.userdetails.GrailsUserDetailsService
import grails.plugin.springsecurity.userdetails.NoStackUsernameNotFoundException
import grails.transaction.Transactional
import me.nimavat.lapsi.Role
import me.nimavat.lapsi.User
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException

class LapsiUserDetailsService implements GrailsUserDetailsService {

    protected Logger log = LoggerFactory.getLogger("grails.app.services.me.nimavat.LapsiUserDetailsService")

    /**
     * Some Spring Security classes (e.g. RoleHierarchyVoter) expect at least one role, so
     * we give a user with no granted roles this one which gets past that restriction but
     * doesn't grant anything.
     */
    static final GrantedAuthority NO_ROLE = new SimpleGrantedAuthority(SpringSecurityUtils.NO_ROLE)

    @Transactional(readOnly = true, noRollbackFor = [IllegalArgumentException, UsernameNotFoundException])
    UserDetails loadUserByUsername(String username, boolean loadRoles) throws UsernameNotFoundException {

        username = username?.toLowerCase()
        User user = User.findByUsernameOrEmail(username, username)
        if (!user) {
            log.warn 'User not found: {}', username
            throw new NoStackUsernameNotFoundException()
        }

        Collection<GrantedAuthority> authorities = loadAuthorities(user, username, loadRoles)
        createUserDetails user, authorities
    }

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        loadUserByUsername username, true
    }

    protected Collection<GrantedAuthority> loadAuthorities(User user, String username, boolean loadRoles) {
        if (!loadRoles) {
            return []
        }

        Collection<Role> userAuthorities = user.authorities

        def authorities
        authorities = userAuthorities.collect { new SimpleGrantedAuthority(it.springSecRoleName) }
        authorities ?: [NO_ROLE]
    }

    protected UserDetails createUserDetails(User user, Collection<GrantedAuthority> authorities) {
        String username = user.username
        String password = user.password
        boolean enabled = user.enabled
        boolean accountExpired = user.accountExpired
        boolean accountLocked = user.accountLocked
        boolean passwordExpired = user.passwordExpired

        new GrailsUser(username, password, enabled, !accountExpired, !passwordExpired, !accountLocked, authorities, user.id)
    }
}
