package com.brendex.flex.server;

import com.brendex.flex.server.resources.MembersResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class App extends Application<ServerConfiguration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    @Override
    public void initialize(Bootstrap<ServerConfiguration> b) {}

    @Override
    public void run(ServerConfiguration c, Environment e) throws Exception {
        LOGGER.info("Method App#run() called");
        System.out.println( "Hello world, by Dropwizard!" );

        // Add the resource to the environment
        e.jersey().register(new MembersResource());
    }

    public static void main( String[] args ) throws Exception {
        new App().run(args);
    }
}
