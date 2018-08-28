package com.srn.api.controller;

import com.srn.api.domain.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("restApiController")
@RequestMapping("/srn-api")
public class RestApiController {

    @RequestMapping("/")
    public String welcome() {//Welcome page, non-rest
        return "Welcome to RestTemplate Example.";
    }

    @RequestMapping(value = "/hello/{player}", method = RequestMethod.GET)
    public Message message(@PathVariable String player) {//REST Endpoint.

        Message msg = new Message(player, "Hello there");
        return msg;
    }

    @RequestMapping(value = "/test/message", method = RequestMethod.GET)
    public ResponseEntity<?> message1() {//REST Endpoint.

        Message msg = new Message("message name", "message contents");
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @RequestMapping(value = "/device/provisioning", method = RequestMethod.POST)
    public ResponseEntity<?> deviceProvisioning(@RequestBody String input) {
        return new ResponseEntity<>(input, HttpStatus.OK);
    }
}