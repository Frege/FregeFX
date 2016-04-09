package org.frege;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import frege.runtime.Phantom.RealWorld;
import frege.prelude.PreludeBase;
import frege.run7.Func;
import frege.run7.Lazy;
import frege.run7.Thunk;

//type t -> IO ()   ==>  Func.U<T, Func.U<T, Func.U<RealWorld, Short>>> 

public class FregeChangeListener<T> implements ChangeListener<T> {
    protected Func.U<T, Func.U<T, Func.U<RealWorld, Short>>> lambda;

    public FregeChangeListener(Func.U<T, Func.U<T, Func.U<RealWorld, Short>>> lambda) {
        this.lambda = lambda;
    }

    /**
     * @param observable It is by design that we do not make use of the of this object. It would introduce more coupling.
     */
    @Override
    public void changed(ObservableValue<? extends T> observable, T oldValue, T newValue) {
        try {
        	Lazy<T> alt = Thunk.<T>lazy(oldValue);
        	Lazy<T> neu = Thunk.<T>lazy(newValue);
        	PreludeBase.TST.performUnsafe(lambda.apply(alt).call().apply(neu).call()).call();
            // Applicable inter = lambda.apply(oldValue).apply(newValue);
            // Delayed.forced(inter.apply(null).result().forced()); // the second argument is the IO context
        } catch(RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }
}
