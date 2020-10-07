package com.backend.digitalbank.view.service;

import com.backend.digitalbank.model.Transaction;

public interface ITransactionService{
    public String excute(Transaction transaction, String token);
}