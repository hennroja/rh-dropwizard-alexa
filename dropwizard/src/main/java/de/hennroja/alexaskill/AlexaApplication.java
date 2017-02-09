package de.hennroja.alexaskill;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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

	}

	@Override
	public void run(AlexaConfiguration configuration, Environment environment)
	{
		environment.jersey().setUrlPattern("/*");


		final AlexaResource resource = new AlexaResource();
		environment.jersey().register(resource);
	}

}