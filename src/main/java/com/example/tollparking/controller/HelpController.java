package com.example.tollparking.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelpController {

    @RequestMapping("/")
    public String help() {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "<h2>Url Mappings Help</h2>"+
                "<div>\n" +
                "    <span>park:</span>\n" +
                "    <span>http://-ip-:8080/tollparkingmanager/park</span>\n" +
                "</div>\n" + "\n" + "<div>\n" +
                "    <span>unpark:</span>\n" + " " +
                "   <span>http://-ip-:8080/tollparkingmanager/unpark</span>\n" +
                "</div>\n" + "\n" + "<div>\n" + " " +
                "   <span>statistic:</span>\n" +
                "    <span>http://-ip-:8080/tollparkingmanager/statistics</span>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>";
    }

}
