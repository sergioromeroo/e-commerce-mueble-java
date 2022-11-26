package com.mindhub.ecommerce.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SendMailController {


    @Autowired
    private JavaMailSender mail;


    @PostMapping("/sendemailvalidation")
    public ResponseEntity<?> send_mail_validation(
            @RequestParam String contactTo
            ){

        SimpleMailMessage email = new SimpleMailMessage();


        email.setTo(contactTo);
        email.setFrom("estodounatema23@gmail.com");
        email.setSubject("Codigo de verificacion de email");
        email.setText("Gracias por registrarter la palabra es: Orange");


        mail.send(email);

        return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
    }

    @PostMapping("/sendemailcontact")
    public ResponseEntity<?> send_mail_contact(
            @RequestParam String name,
            @RequestParam String contactFrom,
            @RequestParam String subject,
            @RequestParam String description
    ){


        SimpleMailMessage email = new SimpleMailMessage();


        email.setTo("estodounatema23@gmail.com");
        email.setFrom(contactFrom);
        email.getReplyTo();
        email.setSubject(subject);
        email.setText(description + " reply to: " + contactFrom);


        mail.send(email);

        return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
    }

}
