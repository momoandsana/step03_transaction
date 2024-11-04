package web.mvc.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
//public class BasicException extends RuntimeException {
public class BasicException extends Exception {
	  private final ErrorCode errorCode;
}
