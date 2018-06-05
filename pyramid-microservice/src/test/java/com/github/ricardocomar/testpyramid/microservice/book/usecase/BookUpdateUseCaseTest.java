package com.github.ricardocomar.testpyramid.microservice.book.usecase;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.ricardocomar.testpyramid.microservice.PyramidMicroserviceApplication;
import com.github.ricardocomar.testpyramid.microservice.book.dataprovider.DataProviderConfiguration;
import com.github.ricardocomar.testpyramid.microservice.book.model.Book;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { DataProviderConfiguration.class })
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(classes = PyramidMicroserviceApplication.class)
@ActiveProfiles("dataprovider")
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:updateBook.sql")
public class BookUpdateUseCaseTest {
	
	@Autowired
	private BookUpdateGateway bookUpdateGateway;
	
	final Book expected = Book.builder().id(1003l).name("test update 2").writter("test writter update 2").price(50.00).build();
	
	public void name() {
		
	}
}
