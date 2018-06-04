package com.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
 
public class TestAccount {
 
    public static void main(String[] args) {
//        testSynchronized();
    	testLock();
        		
    }

	private static void testSynchronized() {
		Account account = new Account();
        ExecutorService service = Executors.newFixedThreadPool(100);
 
        for(int i = 1; i <= 100; i++) {
            service.execute(new AddMoneyThread(account, 1));
        }
 
        service.shutdown();
 
        while(!service.isTerminated()) {}
 
        System.out.println("账户余额: " + account.getBalance());
	}
	
	private static void testLock() {
		Account2 account2 = new Account2();
        ExecutorService service = Executors.newFixedThreadPool(100);
 
        for(int i = 1; i <= 100; i++) {
            service.execute(new AddMoneyThread2(account2, 1));
        }
 
        service.shutdown();
 
        while(!service.isTerminated()) {}
 
        System.out.println("账户余额: " + account2.getBalance());
	}
}
