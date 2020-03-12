package Listener.listener;

import Listener.processor.AbstractTaskProcessor;

public abstract class AbstractProcessorEndListener implements ProcessorEndListener {

    AbstractTaskProcessor processor;

    public AbstractProcessorEndListener(AbstractTaskProcessor processor){
        this.processor = processor;
    }

    @Override
    public AbstractTaskProcessor getProcessor(){
        return this.processor;
    }

}
