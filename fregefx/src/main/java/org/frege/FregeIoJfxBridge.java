package org.frege;

import frege.runtime.Applicable;
import frege.runtime.Delayed;
import frege.runtime.Lambda;
import javafx.application.Platform;

import java.util.concurrent.*;

public class FregeIoJfxBridge {
    protected Lambda ioLambda;  // to be executed async
    protected Lambda jfxLambda; // to be executed in JavaFx UI Application thread

    public FregeIoJfxBridge(Lambda ioLambda, Lambda jfxLambda) {
        this.ioLambda = ioLambda;
        this.jfxLambda = jfxLambda;
    }

    public void bridge() {
        try {

            final FutureTask<Object> ioFuture =
                new FutureTask<Object>(new Callable<Object>() {
                    public Object call() {
                        Applicable inter = ioLambda.apply(null); // no first argument
                        final Object ioResult = Delayed.forced(inter.apply(null).result().forced());// the second argument is the IO context

                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Applicable inter2 = jfxLambda.apply(ioResult);
                                Delayed.forced(inter2.apply(null).result().forced());// the second argument is the JFX context
                            }
                        });
                        return null;
                    }
                });

            ForkJoinPool.commonPool().execute(ioFuture);


        } catch(RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }
}
