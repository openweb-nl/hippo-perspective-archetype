#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.utils;

import javax.jcr.Session;
import javax.servlet.http.HttpServletRequest;

import org.hippoecm.frontend.session.PluginUserSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JcrSessionUtil {
    private static final Logger LOG = LoggerFactory.getLogger(JcrSessionUtil.class);
    public static final String HIPPO_SESSION = "HIPPO.SESSION";

    private JcrSessionUtil() {}

    public static Session setSessionOnRequest(HttpServletRequest request){
        Session session = null;
        PluginUserSession pluginUserSession = PluginUserSession.get();
        if(pluginUserSession != null){
            session = pluginUserSession.getJcrSession();
            request.setAttribute(HIPPO_SESSION, session);
        }
        return session;
    }

    public static Session getSessionFromRequest(HttpServletRequest request) {
        Session session = (Session) request.getAttribute(HIPPO_SESSION);
        if(session == null){
            session = setSessionOnRequest(request);
        }
        return session;
    }
}
