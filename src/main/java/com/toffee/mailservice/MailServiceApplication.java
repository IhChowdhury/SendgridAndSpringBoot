package com.toffee.mailservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MailServiceApplication implements CommandLineRunner {

    @Autowired
    SendGridEmailService sendGridEmailService;

    public static void main(String[] args) {
        SpringApplication.run(MailServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //this.sendGridEmailService.sendText("no-reply@toffeelive.com","ibrahim.chowdhury@nexdecade.com","Nexdecade Email", "Hi this is dummy email.");
        this.sendGridEmailService.sendText("From email","To email","subject", "body");

    }

}
