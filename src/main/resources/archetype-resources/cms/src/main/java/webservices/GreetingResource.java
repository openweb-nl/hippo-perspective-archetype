#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.webservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.cxf.annotations.GZIP;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.hippoecm.frontend.session.PluginUserSession;

import io.swagger.annotations.Api;

@GZIP
@Api(value = "greeting", description = "Greeting API", position = 7)
@Path("greeting")
@CrossOriginResourceSharing(allowAllOrigins = true)
public class GreetingResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response sayHello() {
        Map<String, String> content = new LinkedHashMap<String, String>();
        content.put("username", getUserName());
        content.put("message", "Welcome to your perspective generated by Open Web IT Services perspective archetype.");
        return Response.ok(content).build();
    }

    private String getUserName(){
        return PluginUserSession.get().getUserCredentials().getUsername();
    }
}
