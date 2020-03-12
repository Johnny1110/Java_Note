package Listener.processor;

import Listener.listener.ProcessListener;
import Listener.listener.ProcessorEndListener;
import Listener.listener.ProcessorExceptionListener;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTaskProcessor {

    List<ProcessorEndListener> endListeners = new ArrayList<>();

    List<ProcessorExceptionListener> exceptionListeners = new ArrayList<>();

    final public void addEndListener(ProcessorEndListener listener){
        this.endListeners.add(listener);
    }

    final public void addExceptionListener(ProcessorExceptionListener listener){
        this.exceptionListeners.add(listener);
    }

    final public void removeListener(ProcessListener listener){
        if (listener instanceof ProcessorEndListener){
            this.endListeners.remove(listener);
            return;
        }
        if (listener instanceof ProcessorExceptionListener){
            this.exceptionListeners.remove(listener);
        }
    }

    public abstract void run();

    final public void start(){
        try {
            run();
            endListeners.forEach(ProcessorEndListener::noticed);
        } catch (Exception ex){
            if (this.exceptionListeners.size() > 0) {
                exceptionListeners.forEach(listener -> listener.noticed(ex));
            } else{
                throw ex;
            }

        }
    }
}
