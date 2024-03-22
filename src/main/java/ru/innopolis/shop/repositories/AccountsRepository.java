package ru.innopolis.shop.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.innopolis.shop.models.User;

import java.util.List;
import java.util.Optional;

public interface AccountsRepository extends JpaRepository<User, Long> {

    Optional<User> findByFirstNameAndLastName(String firstName, String lastName);

    Optional<User> findByEmail(String email);

    Optional<User> findByConfirmCode(String confirmCode);

    @Query("select user from User user where user.age > :age")
    List<User> findUsersWithMoreThanAge(@Param("age") Integer age, Sort sort);

    @Modifying
    @Query("update User user set user.firstName = ?1 where user.id = ?2")
    int setFirstNameFor(String firstName, Long userId);

    Page<User> findAll(Pageable pageable);
}
