
package kr.co.danal.spring_batch_danal;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing // 배치 기능 활성화
@SpringBootApplication
public class SpringBatchDanalApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchDanalApplication.class, args);
	}

}
