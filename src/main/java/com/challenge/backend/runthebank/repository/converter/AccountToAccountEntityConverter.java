package com.challenge.backend.runthebank.repository.converter;

import com.challenge.backend.runthebank.domain.Account;
import com.challenge.backend.runthebank.repository.entity.AccountEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AccountToAccountEntityConverter {

    private final ModelMapper modelMapper;

    public AccountToAccountEntityConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public AccountEntity convert(Account account){
        return modelMapper.map(account, AccountEntity.class);
    }
}
