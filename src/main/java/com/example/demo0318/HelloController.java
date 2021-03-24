package com.example.demo0318;

import com.sun.istack.internal.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class HelloController {

    private static final Logger log = LoggerFactory.getLogger(HelloController.class);

    private static final String template = "Hello,%s";
    private final AtomicLong counter = new AtomicLong();


    //Domian Object 验证
    @GetMapping(value = "/valid")
    public String doValidate(
            @Valid @RequestParam User user
    ){

        return  "laiyuqan";

    }



    //正常的参数接受
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String Hello2(
          @Valid  @RequestParam (value = "name") @NotNull String name
    ){
        return "hello world22 " + name;
    }


    //返回json hackJson
    @RequestMapping(value = "/hellojson",method = RequestMethod.GET)
    public Hello helloJson(@RequestParam(value = "name",defaultValue = "laiyuquan") String name){

        return new Hello(counter.incrementAndGet(),String.format(template,name));

    }


    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception{
        return args -> {
            Quote quote =restTemplate.getForObject("https://quoters.apps.pcfone.io/api/random", Quote.class);

            log.info(quote.toString());
        };

    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }




}
