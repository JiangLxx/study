package com.test;

/**
 * 银行账户
 * @author 骆昊
 *
 */
public class Account {
    private double balance;     // 账户余额
 
    /**
     * 存款
     * @param money 存入金额
     */
    public synchronized void deposit(double money) {
        double newBalance = balance + money;
        try {
        	// 模拟此业务需要一段处理时间
            Thread.sleep(10);
        }
        catch(InterruptedException ex) {
            ex.printStackTrace();
        }
        balance = newBalance;
    }
 
    /**
     * 获得账户余额
     */
    public double getBalance() {
        return balance;
    }
}
