package service;

import model.Bank;
import repository.BankRepository;

public class BankService {
    private BankRepository bankRepository;
    public BankService(){
        this.bankRepository = new BankRepository();
    }

    public Bank build(String bankDetails) {
        String[] attributes = bankDetails.split("/");
        String name = attributes[0];
        int age = Integer.valueOf(attributes[1]);
        return new Bank(name, age);
    }

    public void addBank(Bank bank){
        bankRepository.addBank(bank);
    }
}
