package Listener;

import Listener.listener.AbstractProcessorEndListener;
import Listener.listener.AbstractProcessorExceptionListener;
import Listener.listener.ProcessorEndListener;
import Listener.listener.ProcessorExceptionListener;
import Listener.processor.AbstractTaskProcessor;

public class Main {

    private static class MyTaskProcessorEndListener extends AbstractProcessorEndListener {

        public MyTaskProcessorEndListener(AbstractTaskProcessor processor) {
            super(processor);
        }

        @Override
        public void noticed() {
            System.out.println("ProcessorEndListener : 處理結束..");
        }
    }

    private static class MyTaskProcessorErrorListener extends AbstractProcessorExceptionListener {

        public MyTaskProcessorErrorListener(AbstractTaskProcessor processor) {
            super(processor);
        }

        @Override
        public void noticed(Exception ex) {
            System.out.println("ProcessorErrorListener : oop ! 遇到錯誤了..");
        }
    }

    public static void main(String[] args) {
        MyTaskProcessor processor = new MyTaskProcessor();
        ProcessorEndListener myEndListener = new MyTaskProcessorEndListener(processor);
        ProcessorExceptionListener myErrListener = new MyTaskProcessorErrorListener(processor);

        processor.addEndListener(myEndListener);
        processor.addExceptionListener(myErrListener);
        processor.addExceptionListener(myErrListener);

        processor.removeListener(myErrListener);
        processor.removeListener(myErrListener);
        processor.removeListener(myEndListener);

        processor.start();
    }
}