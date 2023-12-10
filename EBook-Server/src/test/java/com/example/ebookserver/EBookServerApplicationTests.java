package com.example.ebookserver;

import com.example.ebookserver.utils.RandomPasswordGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EBookServerApplicationTests {

    @Test
    void contextLoads() {
        System.out.printf(RandomPasswordGenerator.generatePassword(6));
    }

}
