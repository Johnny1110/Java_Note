package Listener;

import Listener.processor.AbstractTaskProcessor;

import java.util.stream.IntStream;

public class MyTaskProcessor extends AbstractTaskProcessor {
    @Override
    public void run() {
        IntStream.range(1, 10).forEach(i -> {
            if (i == 4){
                //throw new RuntimeException("opopopop error happened..");
            }else{
                System.out.println(i);
            }
        });
    }
}
