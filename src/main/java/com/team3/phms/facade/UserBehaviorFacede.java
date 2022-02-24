package com.team3.phms.facade;

import com.team3.phms.models.Sign;
import com.team3.phms.models.User;
import com.team3.phms.service.SignService;
import com.team3.phms.service.UserService;

import java.util.List;

public class UserBehaviorFacede {
    UserService userService;
    SignService signService;

    public UserBehaviorFacede(UserService userService, SignService signService) {
        this.userService = userService;
        this.signService = signService;
    }

    public List<Sign> getSignByUser() {
        User user = userService.GetCurrentUser();
        return signService.GetAllSignByUser(user);
    }
}
