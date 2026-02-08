package com.example.clothesshop.controller;

import com.example.clothesshop.dto.TransactionRequest;
import com.example.clothesshop.dto.TransactionResponse;
import com.example.clothesshop.model.Clothes;
import com.example.clothesshop.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;
    public TransactionController(TransactionService transactionService){
        this.transactionService=transactionService;

    }
    @PostMapping("")
    public ResponseEntity<TransactionResponse> createTransaction(@RequestBody TransactionRequest transactionRequest,@RequestParam String token){
        Clothes transactionResponse = transactionService.create(transactionRequest,token);
        TransactionResponse response = new TransactionResponse();
        response.setCreatedAt(transactionResponse.getCreatedAt());
        response.setAmount(transactionResponse.getAmount());
        response.setDescription(transactionResponse.getDescription());
        response.setCategoryName(transactionResponse.getCategory().getCategory());
        return ResponseEntity.status(201).body(response);


    }
    @GetMapping("/api/transactions")
    public ResponseEntity<List<TransactionResponse>> listTransactions(@RequestParam String token){
        List<TransactionResponse> transactionResponse = transactionService.list(token);
        return ResponseEntity.status(200).body(transactionResponse);

    }
}
