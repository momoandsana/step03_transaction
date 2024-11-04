package web.mvc.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import web.mvc.domain.Bank;
import web.mvc.dto.RequestTransferDTO;
import web.mvc.exception.BasicException;
import web.mvc.exception.ErrorCode;
import web.mvc.repository.BankRepository;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = BasicException.class)// 원래 checked 예외는 롤백 안 하는데 checked 예외도 롤백하도록 수정
public class BankServiceImpl implements  BankService{

    private final BankRepository bankRepository;
    @Override
    public int transfer(RequestTransferDTO requestTransferDTO) throws BasicException {
        //출금 계좌에서 금액 만큼 인출하기
       Bank outBank = bankRepository.findById(requestTransferDTO.getOutAccount())
                .orElseThrow(()->new BasicException(ErrorCode.FAILED_WITHDRAWAL_ACCOUNT));

        outBank.setBalance(outBank.getBalance()-requestTransferDTO.getAmount());

        //입금계좌에 금액 만큼 입금하기
        Bank inBank = bankRepository.findById(requestTransferDTO.getInAccount())
                .orElseThrow(()->new BasicException(ErrorCode.FAILED_DEPOSIT_ACCOUNT));


        inBank.setBalance(inBank.getBalance()+ requestTransferDTO.getAmount());

        //잔액확인하기 - 최대금액이 1000원을초과하면 예외발생)
        if( inBank.getBalance() > 1000)
            throw new  BasicException(ErrorCode.FAILED_MAXIMUM);

        return 1;
    }
}
