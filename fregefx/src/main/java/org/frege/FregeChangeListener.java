package org.frege;

import frege.runtime.Applicable;
import frege.runtime.Delayed;
import frege.runtime.Lambda;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;

public class FregeChangeListener implements ChangeListener {
    protected Lambda lambda;

    public FregeChangeListener(Lambda lambda) {
        this.lambda = lambda;
    }

    /**
     * @param observable It is by design that we do not make use of the of this object. It would introduce more coupling.
     */
    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        try {
            Applicable inter = lambda.apply(oldValue).apply(newValue);
            Delayed.forced(inter.apply(null).result().forced()); // the second argument is the IO context
        } catch(RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }
}
