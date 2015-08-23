package org.frege;

import frege.runtime.Applicable;
import frege.runtime.Delayed;
import frege.runtime.Lambda;
import javafx.event.Event;
import javafx.event.EventHandler;

public class FregeEventHandler implements EventHandler {
    protected Lambda lambda;

    public FregeEventHandler(Lambda lambda) {
        this.lambda = lambda;
    }

    @Override
    public void handle(Event event) {
        try {
            Applicable inter = lambda.apply(event);
            Delayed.forced(inter.apply(null).result().forced()); // the second argument is the IO context
        } catch(RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }
}
