package creditdirect.clientmicrocervice.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager());

        // Define URL patterns and their associated access control settings
        // Example: Securing "/dossiers/all" for users with the "admin" role
        // Adjust these settings according to your requirements
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/dossiers/all", "authc, roles[admin]");
        filterChainDefinitionMap.put("/**", "anon"); // Permit all other requests

        filterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return filterFactoryBean;
    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // Set your custom realm(s) here if needed
        // securityManager.setRealm(myRealm());

        return securityManager;
    }

    // Other beans and configurations related to Shiro can be added here
}
