package org.acme;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @GET
    @Transactional
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        GreetingEntity greetingEntity = new GreetingEntity();
        greetingEntity.setMessage("hello !");
        greetingEntity.persistAndFlush();
        return greetingEntity.getMessage();
    }
}