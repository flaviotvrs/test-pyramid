package com.github.ricardocomar.testpyramid.microservice.book.usecase;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
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
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:findBook.sql")
public class BookFindUseCaseTest {

	@Autowired
	private BookFindGateway findGateway;

	final Book expected = Book.builder().id(1001l).name("test find").writter("test writter find").price(100.00).build();

	@Test
	public void testFindById() {
		Book result = this.findGateway.find(1001l);
		Assert.assertThat(result, Matchers.samePropertyValuesAs(expected));
	}

	@Test
	public void testFindAll() {
		List<Book> books = this.findGateway.find(0, 10);
		Assert.assertThat(books, Matchers.hasSize(3));
		
		Assert.assertThat(books, Matchers.containsInAnyOrder(
				Book.builder().id(1001l).name("test find").writter("test writter find").price(100.00).build(),
				Book.builder().id(1002l).name("test find 2").writter("test writter find 2").price(100.00).build(),
				Book.builder().id(1003l).name("test find 3").writter("test writter find 3").price(100.00).build()));
	}

}
