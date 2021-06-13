package com.toffee.mailservice;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SendGridEmailService {
    @Value("${sendgrid.api.key}")
    String sendGridAPIKey;


    public void sendText(String from, String to, String subject, String body) throws IOException {
        Response response = sendEmail(from, to, subject, new Content("text/plain", body));
        System.out.println("Status Code: " + response.getStatusCode() + ", Body: " + response.getBody() + ", Headers: "
                + response.getHeaders());

    }


    public void sendHTML(String from, String to, String subject, String body) throws IOException {

        Response response = sendEmail(from, to, subject, new Content("text/html", body));
        System.out.println("Status Code: " + response.getStatusCode() + ", Body: " + response.getBody() + ", Headers: "
                + response.getHeaders());

    }

    private Response sendEmail(String from, String to, String subject, Content content) throws IOException {
        Mail mail = new Mail(new Email(from), subject, new Email(to), content);


        SendGrid sg = new SendGrid(sendGridAPIKey);
        mail.setReplyTo(new Email(to));
        Request request = new Request();
        Response response = null;
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
        response = sg.api(request);

        return response;
    }
}
