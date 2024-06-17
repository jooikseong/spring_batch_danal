package kr.co.danal.spring_batch_danal.batch;

import kr.co.danal.spring_batch_danal.model.Store;
import kr.co.danal.spring_batch_danal.repository.StoreRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DBWriter implements ItemWriter<Store> {

    private StoreRepository storeRepository;

    @Autowired
    public DBWriter (StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public void write(Chunk<? extends Store> stores) throws Exception {
        System.out.println("Data Saved for Store : " + stores);
        storeRepository.saveAll(stores);
    }
}
