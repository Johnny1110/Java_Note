package Listener.listener;

import Listener.processor.AbstractTaskProcessor;

public interface ProcessorExceptionListener extends ProcessListener<AbstractTaskProcessor> {
    void noticed(Exception ex);
}
