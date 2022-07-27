package br.com.zup.notafiscal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableKafka
@EnableScheduling
@SpringBootApplication
public class NotaFiscalApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotaFiscalApplication.class, args);
    }

}
