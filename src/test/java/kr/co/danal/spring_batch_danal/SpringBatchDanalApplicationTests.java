package kr.co.danal.spring_batch_danal;

import kr.co.danal.spring_batch_danal.controller.LoadController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class SpringBatchDanalApplicationTests {

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
		// Mock JobExecution
		JobExecution jobExecution = new JobExecution(1L);
		jobExecution.setStatus(BatchStatus.STARTED);

		// Mock jobLauncher.run() method
		when(jobLauncher.run(any(Job.class), any(JobParameters.class))).thenReturn(jobExecution);

		// Call the load() method
		ResponseEntity<BatchStatus> responseEntity = loadController.load();

		// Verify that jobLauncher.run() is called with the correct parameters
		verify(jobLauncher, times(1)).run(eq(job), any(JobParameters.class));

		// Assert that the response status is HttpStatus.OK (200)
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		// Assert that the response body contains the same BatchStatus as jobExecution.getStatus()
		assertEquals(jobExecution.getStatus(), responseEntity.getBody());

		// Optionally, you can verify other aspects of the job execution or response
		// For example, verify that appropriate log messages are printed
	}

}
