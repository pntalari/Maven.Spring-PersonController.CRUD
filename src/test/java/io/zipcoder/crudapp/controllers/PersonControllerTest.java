package io.zipcoder.crudapp.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.zipcoder.crudapp.models.Person;
import io.zipcoder.crudapp.repositories.PersonRepository;
import io.zipcoder.crudapp.services.PersonService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.persistence.Entity;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class PersonControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private PersonRepository repository;

  @Test
  public void testFindOne() throws Exception {
    //Given
    Integer givenId = 1;
    //When
    BDDMockito
      .given(repository.findOne(givenId))
      .willReturn(new Person("First1", "Last1"));
    //Then
    String expectedContent = "{\"id\":null,\"firstName\":\"First1\",\"lastName\":\"Last1\"}";
    this.mvc.perform(MockMvcRequestBuilders
      .get("/people/" + givenId))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().string(expectedContent));
  }

  @Test
  public void testCreate() throws Exception{
    //Given
    Person person = new Person("First2","Last2");
    //When
    BDDMockito
      .given(repository.save(person))
      .willReturn(person);

    //Then
    String expectedContent = "{\"id\":1,\"firstName\":\"First2\",\"lastName\":\"Last2\"}";
    this.mvc.perform(MockMvcRequestBuilders
    .post("/people/")
      .content(expectedContent)
      .accept(MediaType.APPLICATION_JSON)
      .contentType(MediaType.APPLICATION_JSON)
    )
      .andExpect(MockMvcResultMatchers.status().isCreated())
      .andExpect(MockMvcResultMatchers.content().string(expectedContent));
  }

  @Test
  public void testCreateMock(){
    Person person = new Person();
    Integer id = 1;
    PersonRepository mockRepo = mock(PersonRepository.class);
    when(mockRepo.save(person)).thenReturn(new Person(1,"test1","test2"));

    //creating service with the mock
    PersonService service = new PersonService(mockRepo);

    Person actualPerson = service.create(person);

    Assert.assertEquals(id,actualPerson.getId());
  }

  @Test
  public void testDelete(){
    Person person = new Person();

    PersonRepository mockRepo = mock(PersonRepository.class);
  //  when(mockRepo.save(person)).thenReturn(new Person(1,"test1","test2"));

    //creating service with the mock
    PersonService service = new PersonService(mockRepo);

   service.delete(person);

   verify(mockRepo).delete(person);
  }

}
