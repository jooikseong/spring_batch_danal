package kr.co.danal.spring_batch_danal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@SpringBatchTest
class SpringBatchDanalApplicationTests {


	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Test
	public void testJobWithParameters() throws Exception {
		Job job = jobLauncherTestUtils.getJob();
		assertNotNull(job);

		// Define job parameters
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("paramKey", "paramValue")
				.addDate("currentTime", new Date())
				.toJobParameters();

		JobExecution jobExecution = jobLauncher.run(job, jobParameters);

		// Check job status
		assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
	}

	@Test
	public void testStepWithParameters() throws Exception {
		Job job = jobLauncherTestUtils.getJob();
		assertNotNull(job);

		// Define job parameters
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("paramKey", "paramValue")
				.addDate("currentTime", new Date())
				.toJobParameters();

		JobExecution jobExecution = jobLauncher.run(job, jobParameters);

		// Retrieve step execution
		StepExecution stepExecution = jobExecution.getStepExecutions().iterator().next();

		// Check step status
		assertEquals(BatchStatus.COMPLETED, stepExecution.getStatus());
	}

}
