package deadLock;

import java.util.concurrent.TimeUnit;

/**
 * Java并发编程实战 10.1.2
 **/
public class DeadLockDemo2 {

    public static void transferMoney(Account fromAccount, Account toAccount, Integer amount) {
        synchronized (fromAccount) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (toAccount) {
                fromAccount.debit(amount);
                toAccount.credit(amount);
            }
        }
    }


    public static void transferMoney2(Account fromAccount, Account toAccount, Integer amount) {
        int fromHash = System.identityHashCode(fromAccount);
        int toHash = System.identityHashCode(toAccount);

        Object tieLock = new Object();
        if (fromHash < toHash) {
            synchronized (fromAccount) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (toAccount) {
                    fromAccount.debit(amount);
                    toAccount.credit(amount);
                }
            }
        } else if (fromHash > toHash) {
            synchronized (toAccount) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (fromAccount) {


                        fromAccount.debit(amount);
                        toAccount.credit(amount);

                }
            }
        }else{
            synchronized (tieLock) {
                synchronized (fromAccount) {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (toAccount) {
                        fromAccount.debit(amount);
                        toAccount.credit(amount);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Account accountA = new Account();
        Account accountB = new Account();

        /**
         * 看起都是按照同一个顺序获取两个锁
         * 但是其实这里并不能确定多个线程传递进来的参数顺序
         */
//        new Thread(()-> transferMoney(accountA, accountB, 2)).start();
//        new Thread(()-> transferMoney(accountB, accountA, 3)).start();
        new Thread(()-> transferMoney2(accountA, accountB, 2)).start();
        new Thread(()-> transferMoney2(accountB, accountA, 3)).start();
        //正常的计算结果应该是 accountA = 11 accountB = 9
        TimeUnit.SECONDS.sleep(5);
        System.out.println(accountA.getAccount());
        System.out.println(accountB.getAccount());
    }
}

class Account {
    private Integer account = 10;

    public void debit(Integer amount) {
        account = account - amount;
    }

    public void credit(Integer amount) {
        account = account + amount;
    }

    public Integer getAccount() {
        return account;
    }
}
