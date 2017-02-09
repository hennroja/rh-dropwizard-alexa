package de.hennroja.alexaskill.handling;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.SimpleCard;
import de.hennroja.alexaskill.AlexaSpeechlet;

/**
 * Created by robinhenniges on 01.02.17.
 */
public abstract class IntentHandler {

    public IntentHandler() {
    }

    static public SpeechletResponse buildCardSpeechletResponse(final String output, final boolean shouldEndSession) {
        SimpleCard card = new SimpleCard();
        card.setTitle(AlexaSpeechlet.SKILLNAME);
        card.setContent(String.format("%s", output));

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(output);

        // Create the speechlet response.
        SpeechletResponse response = new SpeechletResponse();
        response.setShouldEndSession(shouldEndSession);
        response.setOutputSpeech(speech);
        response.setCard(card);
        return response;
    }

    static public SpeechletResponse buildSpeechletResponse(final String output, final boolean shouldEndSession) {


        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(output);

        // Create the speechlet response.
        SpeechletResponse response = new SpeechletResponse();
        response.setShouldEndSession(shouldEndSession);
        response.setOutputSpeech(speech);
        return response;
    }

    public abstract SpeechletResponse handleIntent(Intent intent);

    public abstract String name();
}
