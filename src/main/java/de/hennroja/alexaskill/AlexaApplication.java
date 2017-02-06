package de.hennroja.alexaskill;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import de.hennroja.alexaskill.resources.AlexaResource;

/**
 * Created by hennroja on 20/03/16.
 */
public class AlexaApplication extends Application<AlexaConfiguration>
{
	public static void main(String[] args) throws Exception
	{
		// when in production set to "false"
	    System.setProperty("com.amazon.speech.speechlet.servlet.disableRequestSignatureCheck", "true");
		new AlexaApplication().run(args);
	}

	@Override
	public void initialize(Bootstrap<AlexaConfiguration> bootstrap)
	{
        bootstrap.addBundle(new MigrationsBundle<AlexaConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(AlexaConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
	}

	@Override
	public void run(AlexaConfiguration configuration, Environment environment)
	{
		environment.jersey().setUrlPattern("/*");

        AlexaSpeechlet alexaSpeechlet = new AlexaSpeechlet(configuration);

		final AlexaResource resource = new AlexaResource(alexaSpeechlet);
		environment.jersey().register(resource);
	}

}