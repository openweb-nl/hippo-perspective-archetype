#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.webservices;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.xml.bind.annotation.XmlElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericExceptionMapper implements ExceptionMapper<Exception> {

    private static final Logger LOG = LoggerFactory.getLogger(GenericExceptionMapper.class);

    @Override
    public Response toResponse(Exception exception) {
        LOG.error(exception.getMessage(), exception);
        return Response.status(Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorMessage(exception.getMessage(), Status.INTERNAL_SERVER_ERROR.getStatusCode()))
                .type(MediaType.APPLICATION_JSON).build();

    } 

    public static class ErrorMessage {

        @XmlElement(name = "status-code")
        private final int statusCode;
        private final String message;

        public ErrorMessage(String message, int statusCode) {
            this.message = message;
            this.statusCode = statusCode;
        }

        public int getStatusCode() {
            return statusCode;
        }

        public String getMessage() {
            return message;
        }

    }

}
