package cn.offer2020.pbj.book_reading.JVM.jvmdemo;

import java.util.Date;

/**
 * @ClassName: Account
 * @Author: pbj
 * @Date: 2020/5/13 18:33
 * @Description: TODO
 */
public class Account {
    private int AccountId;
    private String AccountName;
    private Date Time;
    private int Balance;

    public int getAccountId() {
        return AccountId;
    }

    public void setAccountId(int accountId) {
        AccountId = accountId;
    }

    public String getAccountName() {
        return AccountName;
    }

    public void setAccountName(String accountName) {
        AccountName = accountName;
    }

    public Date getTime() {
        return Time;
    }

    public void setTime(Date time) {
        Time = time;
    }

    public int getBalance() {
        return Balance;
    }

    public void setBalance(int balance) {
        Balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "AccountId=" + AccountId +
                ", AccountName='" + AccountName + '\'' +
                ", Time=" + Time +
                ", Balance=" + Balance +
                '}';
    }
}
