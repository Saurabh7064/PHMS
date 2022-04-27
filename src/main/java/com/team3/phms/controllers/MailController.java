package com.team3.phms.controllers;

import com.team3.phms.advice.Response;
import com.team3.phms.models.User;
import com.team3.phms.payload.request.MailRequest;
import com.team3.phms.service.MailService;
import com.team3.phms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class MailController {
    @Autowired
    MailService mailService;
    @Autowired
    UserService userService;

    @PostMapping("/email")
    public Response<?> Board(@Valid @RequestBody MailRequest mailRequest) {
        List<User> users = userService.GetUser("");

        for (User user:users) {
            if (Objects.equals(user.getUsername(), "xb") || Objects.equals(user.getUsername(), "neenagpt1") ||
            Objects.equals(user.getUsername(), "sibangee") || Objects.equals(user.getUsername(), "Vishnu") ||
                    Objects.equals(user.getUsername(), "Manoj") || Objects.equals(user.getUsername(), "manojranga")) {
                try {
                    String subject = "Team 4 Message";
                    String content = mailRequest.getMessage();
                    String email = user.getEmail();
                    mailService.sendMail(email, subject, content);
                } catch (Exception e) {
                    return Response.fail(500, e.getMessage());
                }
            }
        }
        return Response.success("success");
    }
}
