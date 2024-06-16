package kr.co.danal.spring_batch_danal.config;

import kr.co.danal.spring_batch_danal.model.Store;
import kr.co.danal.spring_batch_danal.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
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

@Configuration
@EnableBatchProcessing
@Slf4j
public class SpringBatchConfig {

//    @Bean
//    public Job job(JobBuilderFactory jobBuilderFactory,
//                   StepBuilderFactory stepBuilderFactory,
//                   ItemReader<User> itemReader,
//                   ItemProcessor<User, User> itemProcessor,
//                   ItemWriter<User> itemWriter
//    ) {
//
//        Step step = stepBuilderFactory.get("ETL-file-load")
//                .<User, User>chunk(100)
//                .reader(itemReader)
//                .processor(itemProcessor)
//                .writer(itemWriter)
//                .build();
//
//
//        return jobBuilderFactory.get("ETL-Load")
//                .incrementer(new RunIdIncrementer())
//                .start(step)
//                .build();
//    }
//
//    @Bean
//    public FlatFileItemReader<User> itemReader() {
//
//        FlatFileItemReader<User> flatFileItemReader = new FlatFileItemReader<>();
//        flatFileItemReader.setResource(new FileSystemResource("src/main/resources/users.csv"));
//        flatFileItemReader.setName("CSV-Reader");
//        flatFileItemReader.setLinesToSkip(1);
//        flatFileItemReader.setLineMapper(lineMapper());
//        return flatFileItemReader;
//    }
//
//    @Bean
//    public LineMapper<User> lineMapper() {
//
//        DefaultLineMapper<User> defaultLineMapper = new DefaultLineMapper<>();
//        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
//
//        lineTokenizer.setDelimiter(",");
//        lineTokenizer.setStrict(false);
//        lineTokenizer.setNames("id", "name", "dept", "salary");
//
//        BeanWrapperFieldSetMapper<User> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
//        fieldSetMapper.setTargetType(User.class);
//
//        defaultLineMapper.setLineTokenizer(lineTokenizer);
//        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
//
//        return defaultLineMapper;
//    }

    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory,
                   StepBuilderFactory stepBuilderFactory,
                   ItemReader<Store> itemReader,
                   ItemProcessor<Store, Store> itemProcessor,
                   ItemWriter<Store> itemWriter
    ) {

        Step step = stepBuilderFactory.get("ETL-file-load")
                .<Store, Store>chunk(10)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();


        return jobBuilderFactory.get("ETL-Load")
                .incrementer(new RunIdIncrementer())
                .start(step)
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
        // flatFileItemReader.setResource(new FileSystemResource("src/main/resources/소상공인시장진흥공단_상가(상권)정보_세종_202403.csv"));
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
