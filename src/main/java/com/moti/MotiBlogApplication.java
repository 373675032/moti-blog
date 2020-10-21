package com.moti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MotiBlogApplication {


    public static void main(String[] args) {
        SpringApplication.run(MotiBlogApplication.class, args);
        System.out.println("                  _   _   _     _             \n" +
                "                 | | (_) | |   | |            \n" +
                "  _ __ ___   ___ | |_ _  | |__ | | ___   __ _ \n" +
                " | '_ ` _ \\ / _ \\| __| | | '_ \\| |/ _ \\ / _` |\n" +
                " | | | | | | (_) | |_| | | |_) | | (_) | (_| |\n" +
                " |_| |_| |_|\\___/ \\__|_| |_.__/|_|\\___/ \\__, |\n" +
                "                                         __/ |\n" +
                "                                        |___/ ");
    }
}
