package com.fasb.api.requests;

public class PayoffCreditReq {


    private int accointID;
    private int creditID;
    private Long sumToPayoff;

    public int getAccointID() {
        return accointID;
    }

    public void setAccointID(int accointID) {
        this.accointID = accointID;
    }

    public int getCreditID() {
        return creditID;
    }

    public void setCreditID(int creditID) {
        this.creditID = creditID;
    }

    public Long getSumToPayoff() {
        return sumToPayoff;
    }

    public void setSumToPayoff(Long sumToPayoff) {
        this.sumToPayoff = sumToPayoff;
    }
}
