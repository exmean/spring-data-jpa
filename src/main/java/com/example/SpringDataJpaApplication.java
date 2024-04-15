package com.example;

import com.example.member.entity.Member;
import com.example.member.entity.Member.Role;
import com.example.member.repository.MemberRepository;
import java.util.NoSuchElementException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringDataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(MemberRepository repository) {
        return args -> {
            Member John = Member.builder()
                .email("John@gmail.com")
                .name("John")
                .role(Role.ADMIN)
                .build();

            repository.save(John);
            repository.findById(John.getId())
                .orElseThrow(NoSuchElementException::new);

            Member find = repository.findMemberByEmail("John@gmail.com")
                .orElseThrow(NoSuchElementException::new);

            System.out.println(find);
        };
    }
}