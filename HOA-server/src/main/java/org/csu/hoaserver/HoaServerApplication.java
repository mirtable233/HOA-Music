package org.csu.hoaserver;

import enable.EnableJwt;
import enable.EnableResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableResponse
@EnableJwt
@SpringBootApplication
public class HoaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HoaServerApplication.class, args);
    }

}
