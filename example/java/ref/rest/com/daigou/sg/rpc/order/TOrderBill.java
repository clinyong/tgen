package com.daigou.sg.rpc.order;

import com.daigou.sg.rpc.BaseModule;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * This file is auto-generated by tgen
 * Don't change manually
 */

public class TOrderBill extends BaseModule<TOrderBill> implements Serializable {
    public int id;
    public String orderNumber;
    public String productName;
    public String productImage;
    public String unitPrice;
    public String localUnitPrice;
    public int qty;
    public boolean insured;
    public ArrayList<com.daigou.sg.rpc.payment.TPaymentBillCategory> orderBillDetails;
    public String total;
}
