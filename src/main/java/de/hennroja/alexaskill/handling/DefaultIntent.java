package de.hennroja.alexaskill.handling;


import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.SpeechletResponse;

/**
 * Created by robinhenniges on 01.02.17.
 */
public class DefaultIntent extends IntentHandler {

    private static final String SLOT_NAME = "SLOT_NAME";
    private static final String INTENT_NAME = "INTENT_NAME";


    @Override
    public SpeechletResponse handleIntent(Intent intent) {
        final Slot slotName = intent.getSlot(SLOT_NAME);
        return buildCardSpeechletResponse("Demo: "+slotName, false);
    }

    @Override
    public String name() {
        return INTENT_NAME;
    }

}
