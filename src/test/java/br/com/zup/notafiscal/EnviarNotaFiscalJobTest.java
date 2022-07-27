package br.com.zup.notafiscal;

import br.com.zup.notafiscal.job.EnviarNotaFiscalJob;
import br.com.zup.notafiscal.model.Item;
import br.com.zup.notafiscal.model.NotaFiscal;
import br.com.zup.notafiscal.repository.NotaFiscalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EnviarNotaFiscalJobTest {
    @Autowired
    private EnviarNotaFiscalJob job;
    @Autowired
    private NotaFiscalRepository repository;
    @MockBean
    private JavaMailSender javaMailSender;

    @BeforeEach
    public void setUp() {
        repository.deleteAll();
    }

    @Test
    public void deveriaMandarNotaFical() {
        NotaFiscal notaFiscal = new NotaFiscal(
                List.of(new Item("Pasta", 1, BigDecimal.TEN)),
                "Fulaninho da Silva",
                "09581234039",
                "Rua da Saudade, 12, Centro - Cidade Qualquer/Estado Qualquer",
                "fulaninho@silva.net",
                BigDecimal.TEN);

        when(javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));

        repository.save(notaFiscal);

        job.executa();

        NotaFiscal notaFiscalAposJob = repository.findById(notaFiscal.getId()).get();
        assertEquals("GERADA_ENVIADA", notaFiscalAposJob.getStatus());
    }


}
