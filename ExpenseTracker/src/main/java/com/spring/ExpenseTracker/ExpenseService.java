package com.spring.ExpenseTracker;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class ExpenseService {

    private static List<Expense> expenses = new ArrayList<>();

    private static int todosCount = 0;

    static {
        expenses.add(new Expense(++todosCount,"singht","Books", 100));
        expenses.add(new Expense(++todosCount, "singht", "Food", 200));
        expenses.add(new Expense(++todosCount, "singht", "Rent", 600));
    }


    public List<Expense> findByUsername(String username){
        Predicate<? super Expense> predicate = expense -> expense.getUsername().equalsIgnoreCase(username);
        return expenses.stream().filter(predicate).toList();
    }


    public void addExpense(String username,String description, int amount){
        Expense expense = new Expense(++todosCount, username, description,amount);
        expenses.add(expense);
    }

    public void deleteById(int id){
        Predicate<? super Expense> predicate = expense -> expense.getId() == id;
        expenses.removeIf(predicate);
    }

    public Expense findById(int id){
        Predicate<? super Expense> predicate = expense -> expense.getId() == id;
        Expense expense =  expenses.stream().filter(predicate).findFirst().get();
        return expense;
    }

    public void updateExpense(@Valid Expense expense){
        deleteById(expense.getId());
        expenses.add(expense);
    }

}
