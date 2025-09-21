package com.oracle.pmsitis.controllers;
import com.oracle.pmsitis.ApplicationResourceConfiguration;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("auth")
public class AuthController {

    public static class Credentials {
        public String username;
        public String password;

        // Required for Jackson (JSON to POJO)
        public Credentials() {}

        public String getUsername() { return username; }
        public String getPassword() { return password; }
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(Credentials creds) {
        if (creds.getUsername() == null || creds.getPassword() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Missing username or password").build();
        }

        try {
            // Temporarily load settings with provided credentials
            String driver = ApplicationResourceConfiguration.DB_SETTINGS.getProperty("driver");
            String url = ApplicationResourceConfiguration.DB_SETTINGS.getProperty("url");

            // Attempt connection
            Class.forName(driver);
            try (java.sql.Connection connection = java.sql.DriverManager.getConnection(
                    url, creds.getUsername(), creds.getPassword())) {

                // If successful, update credentials for future use
                ApplicationResourceConfiguration.updateDbCredentials(creds.getUsername(), creds.getPassword());

                return Response.ok().entity("{\"message\":\"Login successful\"}").type(MediaType.APPLICATION_JSON).build();

            } catch (Exception e) {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity("{\"error\":\"Invalid credentials\"}")
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\":\"" + e.getMessage().replace("\"", "'") + "\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

}
