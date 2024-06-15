package kr.co.danal.spring_batch_danal.batch;

import kr.co.danal.spring_batch_danal.model.Store;
import kr.co.danal.spring_batch_danal.model.User;
import kr.co.danal.spring_batch_danal.repository.StoreRepository;
import kr.co.danal.spring_batch_danal.repository.UserRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
//public class DBWriter implements ItemWriter<User> {
//
//    private UserRepository userRepository;
//
//    @Autowired
//    public DBWriter (UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public void write(List<? extends User> users) throws Exception{
//        System.out.println("Data Saved for Users: " + users);
//        userRepository.saveAll(users);
//    }
//}

@Component
public class DBWriter implements ItemWriter<Store> {

    private StoreRepository storeRepository;

    @Autowired
    public DBWriter (StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public void write(List<? extends Store> stores) throws Exception{
        System.out.println("Data Saved for Users: " + stores);
        storeRepository.saveAll(stores);
    }
}
