#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.webservices;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.hippoecm.frontend.session.PluginUserSession;

import ${package}.webservices.jaxrs.exception.UnauthorizedException;

public class HippoAuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        boolean validSession = false;
        PluginUserSession pluginUserSession = PluginUserSession.get();
        if(pluginUserSession != null &&
                pluginUserSession.getJcrSession().isLive()){
            validSession = true;
        }

        if(!validSession){
            HttpServletResponse resp = (HttpServletResponse) response;
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Please Login to the CMS");
            throw new UnauthorizedException();
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}