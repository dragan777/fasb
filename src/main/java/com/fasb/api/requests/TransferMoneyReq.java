package com.fasb.api.requests;

public class TransferMoneyReq {


    private int accoundIDfrom;
    private int accoundIDTo;
    private int transferMoneyValue;

    public int getAccoundIDfrom() {
        return accoundIDfrom;
    }

    public void setAccoundIDfrom(int accoundIDfrom) {
        this.accoundIDfrom = accoundIDfrom;
    }

    public int getAccoundIDTo() {
        return accoundIDTo;
    }

    public void setAccoundIDTo(int accoundIDTo) {
        this.accoundIDTo = accoundIDTo;
    }

    public int getTransferMoneyValue() {
        return transferMoneyValue;
    }

    public void setTransferMoneyValue(int transferMoneyValue) {
        this.transferMoneyValue = transferMoneyValue;
    }
}
