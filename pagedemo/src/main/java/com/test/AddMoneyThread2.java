package com.test;

public class AddMoneyThread2  implements Runnable {
    private Account2 account2;    // 存入账户
    private double money;       // 存入金额
 
    public AddMoneyThread2(Account2 account2, double money) {
        this.account2 = account2;
        this.money = money;
    }
 
    @Override
    public void run() {
    	synchronized (account2) {
    		account2.deposit(money);
		}
    }
 
}
