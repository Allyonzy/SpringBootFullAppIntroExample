package ru.innopolis.shop.repository;

import com.google.common.collect.ImmutableList;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
//import io.zonky.test.db.flyway.FlywayWrapper;
//import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import ru.innopolis.shop.models.User;
import ru.innopolis.shop.repositories.AccountsRepository;

import javax.sql.DataSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.DatabaseType.POSTGRES;

import java.util.Optional;

@DisplayName(value = "User Repository is working when")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
@Disabled
@DataJpaTest
@AutoConfigureEmbeddedDatabase(type = POSTGRES) //создает базу данных
@TestPropertySource(properties = "zonky.test.database.postgres.server.properties.max_connections=15")
public class UserRepositoryTest {
    @Autowired
    private AccountsRepository userRepository; //класс 1

//    @Configuration
//    static class Config {
//
//        @Bean(initMethod = "migrate")
//        public Flyway flyway(DataSource dataSource) {
//            FlywayWrapper wrapper = FlywayWrapper.newInstance();
//            wrapper.setDataSource(dataSource);
//            wrapper.setSchemas(ImmutableList.of("test"));
//            return wrapper.getFlyway();
//        }
//
//        @Bean
//        public JdbcTemplate jdbcTemplate(DataSource dataSource) {
//            return new JdbcTemplate(dataSource);
//        }
//    }

    User newUser;

    @BeforeEach
    public void init() {
        newUser = new User();
        newUser.setId(59L);
        newUser.setFirstName("Peter");
        newUser.setLastName("Pen");
        newUser.setPassword("12345qwer");
        newUser.setEmail("test_peter@ya.ru");
        userRepository.save(newUser);
    }

    @Test
    @Disabled
    public void should_save_and_fetch_account() {
        Optional<User> maybePeter = userRepository.findById(newUser.getId());
        assertThat(maybePeter, is(Optional.of(newUser)));
    }

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
    }

}
