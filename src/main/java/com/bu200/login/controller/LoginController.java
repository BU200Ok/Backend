package com.bu200.login.controller;

import com.bu200.common.response.ResponseDTO;
import com.bu200.login.service.LoginService;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final LoginService loginService;
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/send-email-for-find-accout")
    public ResponseEntity<ResponseDTO> sendMailForFindAccount(@RequestParam String email){
        System.out.println(email);
        if(loginService.sendMailForFindAccount(email)){
            System.out.println("성공");
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"성공",true));
        }else{
            System.out.println("실패");
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.BAD_GATEWAY,"실패",null));
        }

    }
}
