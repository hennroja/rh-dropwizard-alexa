package de.hennroja.alexaskill;

import com.amazon.speech.speechlet.SpeechletV2;
import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by robinhenniges on 09.02.17.
 */
public class Handler extends SpeechletRequestStreamHandler {

    private static final HashSet<String> supportedApplicationIds;

    public Handler(SpeechletV2 speechlet, Set<String> supportedApplicationIds) {
        super(speechlet, supportedApplicationIds);
    }

    static {
        supportedApplicationIds = new HashSet<String>();
        // supportedApplicationIds.add("amzn1.echo-sdk-ams.app.[unique-value-here]");
    }

    public Handler() {
        super(new AlexaSpeechlet(), supportedApplicationIds);
    }
}
