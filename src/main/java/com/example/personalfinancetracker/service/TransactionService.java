package com.example.personalfinancetracker.service;

import com.example.personalfinancetracker.dto.TransactionRequest;
import com.example.personalfinancetracker.dto.TransactionResponse;
import com.example.personalfinancetracker.exeptions.EmptyCategory;
import com.example.personalfinancetracker.exeptions.EmptyUser;
import com.example.personalfinancetracker.exeptions.InvalidToken;
import com.example.personalfinancetracker.model.Category;
import com.example.personalfinancetracker.model.FinanceTransaction;
import com.example.personalfinancetracker.model.User;
import com.example.personalfinancetracker.repository.CategoryRepository;
import com.example.personalfinancetracker.repository.FinanceTransactionRepository;
import com.example.personalfinancetracker.repository.UserRepository;
import com.example.personalfinancetracker.security.JWToken;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class TransactionService {
    private final UserRepository userRepository;
    private final JWToken jwToken;
    private final CategoryRepository categoryRepository;
    private final FinanceTransactionRepository transactionRepository;
    TransactionService(UserRepository userRepository, JWToken JWT,FinanceTransactionRepository financeTransactionRepository,CategoryRepository categoryRepository){
        this.transactionRepository =financeTransactionRepository;
        this.jwToken =JWT;
        this.categoryRepository=categoryRepository;
        this.userRepository = userRepository;
    }
    private User extractUserFromToken(String token){
        if (!jwToken.isValid(token)){
            throw new InvalidToken("Token is invalid");
        }
        String login = jwToken.extractLogin(token);
        Optional<User> user = userRepository.findUserByLogin(login);
        if (user.isEmpty()){
            throw new EmptyUser("User Not Found");
       }
        return user.get();

    }
    @Transactional
    public FinanceTransaction create(TransactionRequest transactionRequest,String token){


        FinanceTransaction financeTransaction = new FinanceTransaction();
        financeTransaction.setCreatedAt(LocalDateTime.now());
        Optional<Category> categoryEntity = categoryRepository
                .findById(transactionRequest
                        .getCategoryId());
        if (categoryEntity.isEmpty()){
            throw new EmptyCategory("Bad Request,cause category is Empty");
        }

        Category category=categoryEntity.get();
        User user = extractUserFromToken(token);
        financeTransaction.setCategory(category);
        financeTransaction.setAmount(transactionRequest.getAmount());
        financeTransaction.setDescription(transactionRequest.getDescription());


        financeTransaction.setUser(user);
        transactionRepository.save(financeTransaction);
        return financeTransaction;
    }
    public List<TransactionResponse> list(String token){
        User user = extractUserFromToken(token);
        Long userId = user.getId();
        List<FinanceTransaction>transactionList=transactionRepository.findAllByUserId(userId);
        List<TransactionResponse> financeTransactionList = new ArrayList<>();
        for (FinanceTransaction financeTransaction:transactionList){
            TransactionResponse transactionResponse= new TransactionResponse();
            transactionResponse.setAmount(financeTransaction.getAmount());
            transactionResponse.setDescription(financeTransaction.getDescription());
            transactionResponse.setCreatedAt(financeTransaction.getCreatedAt());
            transactionResponse.setCategoryName(financeTransaction.getCategory().getCategory());
            financeTransactionList.add(transactionResponse);

        }
        return financeTransactionList;
    }
}
