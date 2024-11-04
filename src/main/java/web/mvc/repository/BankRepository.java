package web.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.mvc.domain.Bank;

public interface BankRepository extends JpaRepository<Bank, String> {
// 여기서 id(pk) 는 Long 이 아니라 String 타입
}
