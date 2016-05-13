#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.webservices.jaxrs.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status
        ;
public class UnauthorizedException extends WebApplicationException {

    private static final long serialVersionUID = 1L;

    public UnauthorizedException() {
        this("Please authenticate.");
    }

    public UnauthorizedException(String message) {
        super(Response.status(Status.UNAUTHORIZED).header(HttpHeaders.WWW_AUTHENTICATE,
                "Basic realm=${symbol_escape}"Default realm${symbol_escape}"")
                .entity(message).build());
    }
}