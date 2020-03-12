package Listener.listener;

import Listener.processor.AbstractTaskProcessor;

public interface ProcessorEndListener extends  ProcessListener<AbstractTaskProcessor> {
    void noticed();
}
