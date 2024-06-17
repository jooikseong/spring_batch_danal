package kr.co.danal.spring_batch_danal;

import kr.co.danal.spring_batch_danal.controller.LoadController;
import kr.co.danal.spring_batch_danal.model.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@SpringBatchTest
class SpringBatchDanalApplicationTests {

	@Autowired
	private ItemReader<Store> itemReader;
	@Autowired
	private ItemProcessor<Store, Store> itemProcessor;

	@Test
	void contextLoads() {
	}

	@Mock
	private JobLauncher jobLauncher;

	@Mock
	private Job job;

	@InjectMocks
	private LoadController loadController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testLoad() throws Exception {
		JobExecution jobExecution = new JobExecution(1L);
		jobExecution.setStatus(BatchStatus.STARTED);

		when(jobLauncher.run(any(Job.class), any(JobParameters.class))).thenReturn(jobExecution);

		ResponseEntity<BatchStatus> responseEntity = loadController.load();

		verify(jobLauncher, times(1)).run(eq(job), any(JobParameters.class));

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(jobExecution.getStatus(), responseEntity.getBody());

	}

	@Test
	public void testItemReader() throws Exception {
		// ClassPathResource를 사용하여 파일 경로 설정
		Resource resource = new ClassPathResource("소상공인시장진흥공단_상가(상권)정보_세종_202403.csv");
		FlatFileItemReader<Store> reader = (FlatFileItemReader<Store>) itemReader;
		reader.setResource(resource);
		reader.open(MetaDataInstanceFactory.createStepExecution().getExecutionContext());

		Store store;
		while ((store = reader.read()) != null) {
			assertThat(store).isNotNull();
		}

		reader.close();
	}

	@Test
	public void testItemProcessor() throws Exception {
		Store input = new Store();
		Store output = itemProcessor.process(input);
		assertThat(output).isNotNull();
	}

}
