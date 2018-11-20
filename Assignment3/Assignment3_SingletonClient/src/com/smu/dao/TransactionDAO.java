/**
 * TransactionDAO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.smu.dao;

public interface TransactionDAO extends java.rmi.Remote {
    public java.lang.String createResultSetInTable(int ID, java.lang.String nameOnCard, java.lang.String cardNumber, java.lang.String unitPrice, java.lang.String quantity, java.lang.String expDate, java.lang.String creditCardType) throws java.rmi.RemoteException;
    public java.lang.String updateRecord(int ID, java.lang.String nameOnCard) throws java.rmi.RemoteException;
    public boolean deleteRecord(int txnID) throws java.rmi.RemoteException;
    public com.smu.dto.TransactionDTO readDataBase(int trnxId) throws java.rmi.RemoteException;
}
