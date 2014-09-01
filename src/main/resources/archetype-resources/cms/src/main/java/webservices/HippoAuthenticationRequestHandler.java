#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*
 * Copyright 2014 Hippo B.V. (http://www.onehippo.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * Modified by Ebrahim Aharpour
 * to integrate the authentication of the user with the CMS
 */

package ${package}.webservices;

import java.io.Serializable;

import javax.jcr.LoginException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.apache.cxf.jaxrs.ext.RequestHandler;
import org.apache.cxf.jaxrs.model.ClassResourceInfo;
import org.apache.cxf.message.Message;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.apache.wicket.Application;
import org.apache.wicket.protocol.http.servlet.ServletWebRequest;
import org.apache.wicket.session.ISessionStore;
import org.hippoecm.frontend.model.UserCredentials;
import org.hippoecm.frontend.session.PluginUserSession;
import org.onehippo.forge.webservices.AuthenticationConstants;
import org.onehippo.forge.webservices.jaxrs.exception.UnauthorizedException;
import org.onehippo.forge.webservices.jaxrs.jcr.util.RepositoryConnectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class HippoAuthenticationRequestHandler implements RequestHandler {

    private static final Logger LOG = LoggerFactory.getLogger(HippoAuthenticationRequestHandler.class);

    public Response handleRequest(Message m, ClassResourceInfo resourceClass) {
        ISessionStore sessionStore = Application.get().getSessionStore();
        Serializable attribute = sessionStore.getAttribute(
                new ServletWebRequest((HttpServletRequest) m.get(AbstractHTTPDestination.HTTP_REQUEST), ""), "session");
        if (attribute instanceof PluginUserSession) {
            UserCredentials userCredentials = ((PluginUserSession) attribute).getUserCredentials();
            if (userCredentials != null) {
                SimpleCredentials jcrCredentials = (SimpleCredentials) userCredentials.getJcrCredentials();
                String username = jcrCredentials.getUserID();
                String password = new String(jcrCredentials.getPassword());
                Session session = null;
                try {
                    session = RepositoryConnectionUtils.createSession(username, password);
                    if (isAuthenticated(session)) {
                        HttpServletRequest request = (HttpServletRequest) m.get(AbstractHTTPDestination.HTTP_REQUEST);
                        request.setAttribute(AuthenticationConstants.HIPPO_CREDENTIALS, new SimpleCredentials(username,
                                password.toCharArray()));
                        return null;
                    } else {
                        throw new UnauthorizedException();
                    }
                } catch (LoginException e) {
                    LOG.debug("Login failed: {}", e);
                    throw new UnauthorizedException(e.getMessage());
                } finally {
                    RepositoryConnectionUtils.cleanupSession(session);
                }
            }
        }
        throw new UnauthorizedException();
    }

    private boolean isAuthenticated(Session session) {
        if (session != null) {
            return true;
        }
        return false;
    }
}