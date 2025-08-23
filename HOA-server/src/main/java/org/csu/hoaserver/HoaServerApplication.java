package org.csu.hoaserver;

import enable.EnableJwt;
import enable.EnableResponse;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

//@EnableResponse
@EnableJwt
@EnableCaching
@SpringBootApplication
@ComponentScan(basePackages = {
        "org.csu.hoaserver",
        "properties"         // 扫描 HOA-pojo，如果有 Bean
})
public class HoaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HoaServerApplication.class, args);
    }

}
