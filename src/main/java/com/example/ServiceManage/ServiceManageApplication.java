package com.example.ServiceManage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.time.LocalTime;

@SpringBootApplication(scanBasePackages = {
        "com.example.*"},exclude = { SecurityAutoConfiguration.class })
public class ServiceManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceManageApplication.class, args);
        System.out.println("hello mosio");
/*        int value = 253500;
        int hour = value / 10000; // برای محاسبه ساعت
        int minute = (value % 10000) / 100; // برای محاسبه دقیقه
        LocalTime localTime = LocalTime.of(hour, minute);
        System.out.println(localTime + " : aaaa");*/
    }

}
