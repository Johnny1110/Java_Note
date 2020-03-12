package Listener.listener;

import Listener.processor.AbstractTaskProcessor;

public abstract class AbstractProcessorExceptionListener implements ProcessorExceptionListener {
    AbstractTaskProcessor processor;

    public AbstractProcessorExceptionListener(AbstractTaskProcessor processor){
        this.processor = processor;
    }

    @Override
    public AbstractTaskProcessor getProcessor(){
        return this.processor;
    }
}
