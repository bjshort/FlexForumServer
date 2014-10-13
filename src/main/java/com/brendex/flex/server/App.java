package com.brendex.flex.server;

import com.brendex.flex.server.dao.MemberDAO;
import com.brendex.flex.server.dao.PostDAO;
import com.brendex.flex.server.domains.Member;
import com.brendex.flex.server.domains.Post;
import com.brendex.flex.server.resources.MembersResource;
import com.brendex.flex.server.resources.PostsResource;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.migrations.MigrationsBundle;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class App extends Application<ServerConfiguration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    private final HibernateBundle<ServerConfiguration> hibernate = new HibernateBundle<ServerConfiguration>(Member.class, Post.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(ServerConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    private final MigrationsBundle<ServerConfiguration> migrations = new MigrationsBundle<ServerConfiguration>() {
        @Override
        public DataSourceFactory getDataSourceFactory(ServerConfiguration serverConfiguration) {
            return serverConfiguration.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(Bootstrap<ServerConfiguration> b) {
        b.addBundle(hibernate);
        b.addBundle(migrations);
    }

    @Override
    public void run(ServerConfiguration c, Environment e) throws Exception {

        //Initialize DAOs
        final MemberDAO memberDao = new MemberDAO(hibernate.getSessionFactory());
        final PostDAO postsDao = new PostDAO(hibernate.getSessionFactory());

        //Register resources
        try {
            e.jersey().register(new MembersResource(memberDao));
            e.jersey().register(new PostsResource(postsDao));
        } catch (Exception ex){
            LOGGER.info("Could not register environment variables: %s", ex.getMessage());
        }
    }

    public static void main( String[] args ) throws Exception {
        new App().run(args);
    }
}
