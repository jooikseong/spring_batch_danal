package kr.co.danal.spring_batch_danal.config;

import kr.co.danal.spring_batch_danal.model.Store;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@Slf4j
public class SpringBatchConfig {


    @Bean
    public Job simpleJob1(JobRepository jobRepository, Step simpleStep1) {
        return new JobBuilder("job", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(simpleStep1)
                .build();
    }
    @Bean
    public Step simpleStep1(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                   ItemReader<Store> itemReader,
                   ItemProcessor<Store, Store> itemProcessor,
                   ItemWriter<Store> itemWriter
    ){
        return new StepBuilder("step", jobRepository)
                .<Store, Store>chunk(10)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .transactionManager(transactionManager)
                .build();
    }

    @Bean
    @Primary
    public MultiResourceItemReader<Store> multiResourceItemReader() {
        MultiResourceItemReader<Store> multiResourceItemReader = new MultiResourceItemReader<>();
        multiResourceItemReader.setResources(getResources());
        multiResourceItemReader.setDelegate(flatFileItemReader());
        return multiResourceItemReader;
    }

    private Resource[] getResources() {
        return new Resource[]{
                new FileSystemResource("src/main/resources/소상공인시장진흥공단_상가(상권)정보_세종_202403.csv"),
                new FileSystemResource("src/main/resources/소상공인시장진흥공단_상가(상권)정보_울산_202403.csv"),
                new FileSystemResource("src/main/resources/소상공인시장진흥공단_상가(상권)정보_제주_202403.csv"),
                new FileSystemResource("src/main/resources/소상공인시장진흥공단_상가(상권)정보_경남_202403.csv"),
                new FileSystemResource("src/main/resources/소상공인시장진흥공단_상가(상권)정보_경북_202403.csv"),
                new FileSystemResource("src/main/resources/소상공인시장진흥공단_상가(상권)정보_광주_202403.csv"),
                new FileSystemResource("src/main/resources/소상공인시장진흥공단_상가(상권)정보_대전_202403.csv"),
                new FileSystemResource("src/main/resources/소상공인시장진흥공단_상가(상권)정보_부산_202403.csv"),
                new FileSystemResource("src/main/resources/소상공인시장진흥공단_상가(상권)정보_인천_202403.csv"),
                new FileSystemResource("src/main/resources/소상공인시장진흥공단_상가(상권)정보_전남_202403.csv"),
                new FileSystemResource("src/main/resources/소상공인시장진흥공단_상가(상권)정보_전북_202403.csv"),
                new FileSystemResource("src/main/resources/소상공인시장진흥공단_상가(상권)정보_충남_202403.csv"),
                new FileSystemResource("src/main/resources/소상공인시장진흥공단_상가(상권)정보_충북_202403.csv"),
                //new FileSystemResource("src/main/resources/소상공인시장진흥공단_상가(상권)정보_서울_202403.csv"),
                //new FileSystemResource("src/main/resources/소상공인시장진흥공단_상가(상권)정보_경기_202403.csv"),
                new FileSystemResource("src/main/resources/소상공인시장진흥공단_상가(상권)정보_대구_202403.csv")
        };
    }

    @Bean
    public FlatFileItemReader<Store> flatFileItemReader() {

        FlatFileItemReader<Store> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setName("CSV-Reader");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(lineMapper());
        return flatFileItemReader;

    }

    @Bean
    public LineMapper<Store> lineMapper() {

        DefaultLineMapper<Store> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(
                "id", "tradeName", "branchName", "businessLargeCode", "businessLargeName",
                "businessMiddleCode", "businessMiddleName", "businessSmallCode", "businessSmallName",
                "standardCode", "standardName", "cityCode", "cityName", "addressCode", "addressName",
                "administrativeDongCode", "administrativeDongName", "legalOperationCode", "legalOperationName",
                "numberCode", "landCode", "landName", "localMainNumber", "localSubNumber", "localAddress",
                "roadNameCode", "roadName", "buildMainNumber", "buildSubNumber", "buildMgmtNumber", "buildMame",
                "roadNameAddress", "oldPostalCode", "newPostalCode", "dong", "floor", "ho", "longitude", "latitude"
        );


        BeanWrapperFieldSetMapper<Store> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Store.class);

        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;
    }

}
