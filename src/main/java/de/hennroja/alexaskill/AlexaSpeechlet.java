package de.hennroja.alexaskill;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.*;
import de.hennroja.alexaskill.handling.DefaultIntent;
import de.hennroja.alexaskill.handling.IntentHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by robinhenniges on 01.02.17.
 */
public class AlexaSpeechlet implements SpeechletV2 {

    private final static Logger logger = LoggerFactory.getLogger(AlexaSpeechlet.class);
    private final AlexaConfiguration configuration;

    private Set<IntentHandler> intentHandlers = new HashSet<>();

    public AlexaSpeechlet(AlexaConfiguration configuration) {
        this.configuration = configuration;
    }


    @Override
    public void onSessionStarted(SpeechletRequestEnvelope<SessionStartedRequest> requestEnvelope) {
        logger.debug("onSessionStarted");
        intentHandlers.add(new DefaultIntent());
    }

    @Override
    public SpeechletResponse onLaunch(SpeechletRequestEnvelope<LaunchRequest> requestEnvelope) {
        logger.debug("onLaunch");
        return IntentHandler.buildSpeechletResponse("Hello!", false);
    }

    @Override
    public SpeechletResponse onIntent(SpeechletRequestEnvelope<IntentRequest> request) {
        logger.debug("onIntent");

        final Intent intent = request.getRequest().getIntent();
        final String intentName = intent.getName();

        for (IntentHandler handler : intentHandlers) {
            if (handler.name().equals(intentName)) {
                return handler.handleIntent(intent);
            }
        }

        return IntentHandler.buildSpeechletResponse("Error", true);
    }

    @Override
    public void onSessionEnded(SpeechletRequestEnvelope<SessionEndedRequest> requestEnvelope) {
        logger.debug("onSessionEnded");
    }
}
