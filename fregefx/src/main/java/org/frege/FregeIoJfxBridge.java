package org.frege;

import frege.runtime.Applicable;
import frege.runtime.Delayed;
import frege.runtime.Lambda;
import javafx.event.Event;
import javafx.event.EventHandler;

public class FregeIoJfxBridge {
    protected Lambda ioLambda;
    protected Lambda jfxLambda;

    public FregeIoJfxBridge(Lambda ioLambda, Lambda jfxLambda) {
        this.ioLambda = ioLambda;
        this.jfxLambda = jfxLambda;
    }

    public void bridge() {
        try {
            Applicable inter = ioLambda.apply(null); // no first argument
            final Object ioResult = Delayed.forced(inter.apply(null).result().forced());// the second argument is the IO context
            Applicable inter2 = jfxLambda.apply(ioResult); // no first argument
            Delayed.forced(inter2.apply(null).result().forced());// the second argument is the JFX context
        } catch(RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }
}
