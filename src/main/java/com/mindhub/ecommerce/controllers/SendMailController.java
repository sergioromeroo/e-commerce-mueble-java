package com.mindhub.ecommerce.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;

@RestController
@RequestMapping("/api")
public class SendMailController {


    @Autowired
    private JavaMailSender mail;


    @PostMapping("/sendemailvalidation")
    public ResponseEntity<?> send_mail_validation(
            @RequestParam String contactTo
    ) throws MessagingException {

        MimeMessage message = mail.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message,true);



        String contenido = "<p>Thanks for register to Nogal. Please write this code in your account:</p>";
        contenido += "<img src=\" https://i.postimg.cc/50780xj7/Historia-de-Instagram-Feliz-lunes-caf-Minimalista-Blanco.jpg \">";

        helper.setFrom("estodounatema23@gmail.com");
        helper.setTo(contactTo);
        helper.setSubject("Code to email verification");
        helper.setText(contenido,true);


        mail.send(message);
        return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
    }
//    public ResponseEntity<?> send_mail_validation(
//            @RequestParam String contactTo
//            ) throws MessagingException {
//
//        SimpleMailMessage email = new SimpleMailMessage();
//
//
//        email.setTo(contactTo);
//        email.setFrom("nogalfurniture00@gmail.com");
//        email.setSubject("Codigo de verificacion de email");
//        email.setText("Gracias por registrarter la palabra es: chair");
//
//
//
//
//        mail.send(email);
//
//        return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
//    }

    @PostMapping("/sendemailcontact")
    public ResponseEntity<?> send_mail_contact(
            @RequestParam String name,
            @RequestParam String contactFrom,
            @RequestParam String subject,
            @RequestParam String description
    ){


        SimpleMailMessage email = new SimpleMailMessage();


        email.setTo("nogalfurniture00@gmail.com");
        email.setFrom(contactFrom);
        email.getReplyTo();
        email.setSubject(subject);
        email.setText(description + " reply to: " + contactFrom);


        mail.send(email);

        return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
    }

}
