package org.frege;

import frege.prelude.PreludeBase;
import frege.run8.Func;
import frege.run8.Lazy;
import frege.run8.Thunk;
import frege.runtime.Phantom.RealWorld;
import javafx.event.Event;
import javafx.event.EventHandler;

// type t -> IO ()   ==>  Func.U<T, Func.U<RealWorld, Short>> 

public class FregeEventHandler<T extends Event> implements EventHandler<T> {
    protected Func.U<T, Func.U<RealWorld, Short>> lambda;

    public FregeEventHandler(Func.U<T, Func.U<RealWorld, Short>> lambda) {
        this.lambda = lambda;
    }

    @Override
    public void handle(T event) {
    	Lazy<T> lazyEvent = Thunk.<T>lazy(event);
        try {
            PreludeBase.TST.performUnsafe( lambda.apply(lazyEvent).call() ).call();
        } catch(RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }
}
