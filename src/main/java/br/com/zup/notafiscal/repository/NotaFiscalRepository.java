package br.com.zup.notafiscal.repository;

import br.com.zup.notafiscal.model.NotaFiscal;
import org.hibernate.LockOptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;

public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, Long> {

    @QueryHints({
            @QueryHint(
                    name = "javax.persistence.lock.timeout",
                    value = (LockOptions.SKIP_LOCKED + "")
            )
    })
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<NotaFiscal> findTop5ByStatusOrderByCriadoEmAsc(String aprovada);
}
