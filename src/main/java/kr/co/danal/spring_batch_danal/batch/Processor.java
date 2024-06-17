package kr.co.danal.spring_batch_danal.batch;

import kr.co.danal.spring_batch_danal.model.Store;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class Processor implements ItemProcessor<Store, Store> {

    @Override
    public Store process(Store store) {
        return store;
    }
}
