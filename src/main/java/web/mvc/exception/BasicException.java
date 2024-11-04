package web.mvc.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BasicException extends RuntimeException {
//public class BasicException extends Exception {
	// Checked 예외에서는 롤백을 하지 않아서 Exception 인 경우 중간에 예외가 발생해도 송금이 된다->기본 값이
	// BankServiceImpl 에서 @Transactional(rollBackOn=BasicException.class) 하면 checked 예외도 처리 가능
	  private final ErrorCode errorCode;
}
