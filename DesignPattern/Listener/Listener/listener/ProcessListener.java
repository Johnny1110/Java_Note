package Listener.listener;

import Listener.processor.AbstractTaskProcessor;

public interface ProcessListener<T extends AbstractTaskProcessor> {
    T getProcessor();
}
