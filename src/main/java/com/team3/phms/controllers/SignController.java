package com.team3.phms.controllers;


import com.team3.phms.advice.Response;
import com.team3.phms.models.Sign;
import com.team3.phms.models.User;
import com.team3.phms.payload.request.SignRequest;
import com.team3.phms.service.SignService;
import com.team3.phms.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class SignController {
    final UserService userService;
    final SignService signService;

    public SignController(UserService userService, SignService signService) {
        this.userService = userService;
        this.signService = signService;
    }

    @GetMapping("/sign/feed")
    @PreAuthorize("hasRole('USER')")
    public Response<?> GetSignFeed() {
        User user = userService.GetCurrentUser();
        List<Sign> signs = signService.GetAllSignByUser(user);
        return Response.success(signs);
    }

    @GetMapping("/sign")
    public Response<?> GetAllSign() {
        List<Sign> sign = signService.GetAll();
        return Response.success(sign);
    }

    @GetMapping("/sign/{id}")
    @PreAuthorize("hasRole('USER')")
    public Response<?> GetSign(@PathVariable("id") Long id) {
        User user = userService.GetCurrentUser();
        Optional<Sign> sign = signService.GetSignByIdAuth(id, user);
        if (!sign.isPresent()) {
            return Response.fail(400, "wrong id or not authed");
        }
        return Response.success(sign);
    }

    @PostMapping("/sign")
    @PreAuthorize("hasRole('USER')")
    public Response<?> CreateSign(@Valid @RequestBody SignRequest signRequest) {
        User user = userService.GetCurrentUser();
        Sign sign = signService.CreateSign(signRequest.getBloodPressure(), signRequest.getGlucoseLevel(), signRequest.getCholesterol(), user);
        if (sign == null) {
            return Response.fail(400, "failed to create sign");
        }

        return Response.success(sign);
    }

    @PutMapping("/sign/{id}")
    @PreAuthorize("hasRole('USER')")
    public Response<?> UpdateSign(@PathVariable("id") Long id, @Valid @RequestBody SignRequest signRequest) {
        User user = userService.GetCurrentUser();
        Optional<Sign> sign = signService.GetSignByIdAuth(id, user);
        if (!sign.isPresent()) {
            return Response.fail(400, "wrong id or not authed");
        }
        Sign signed = signService.UpdateSign(sign.get(), signRequest.getBloodPressure(), signRequest.getGlucoseLevel(), signRequest.getCholesterol());
        return Response.success(signed);
    }

    @DeleteMapping("/sign/{id}")
    @PreAuthorize("hasRole('USER')")
    public Response<?> DeleteSign(@PathVariable("id") Long id) {
        User user = userService.GetCurrentUser();
        Optional<Sign> sign = signService.GetSignByIdAuth(id, user);
        if (!sign.isPresent()) {
            return Response.fail(400, "wrong id or not authed");
        }
        Boolean flag = signService.DeleteSign(sign.get());
        return Response.success(flag);
    }
}
