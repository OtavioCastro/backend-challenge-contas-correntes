package com.challenge.backend.runthebank.repository.converter;

import com.challenge.backend.runthebank.domain.Account;
import com.challenge.backend.runthebank.repository.entity.AccountEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AccountEntityToAccountConverter {

    private final ModelMapper modelMapper;

    public AccountEntityToAccountConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Account convert(AccountEntity accountEntity){
        return modelMapper.map(accountEntity, Account.class);
    }
}
