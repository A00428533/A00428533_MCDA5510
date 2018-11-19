package com.smu.dao;

public class TransactionDAOProxy implements com.smu.dao.TransactionDAO {
  private String _endpoint = null;
  private com.smu.dao.TransactionDAO transactionDAO = null;
  
  public TransactionDAOProxy() {
    _initTransactionDAOProxy();
  }
  
  public TransactionDAOProxy(String endpoint) {
    _endpoint = endpoint;
    _initTransactionDAOProxy();
  }
  
  private void _initTransactionDAOProxy() {
    try {
      transactionDAO = (new com.smu.dao.TransactionDAOServiceLocator()).getTransactionDAO();
      if (transactionDAO != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)transactionDAO)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)transactionDAO)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (transactionDAO != null)
      ((javax.xml.rpc.Stub)transactionDAO)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.smu.dao.TransactionDAO getTransactionDAO() {
    if (transactionDAO == null)
      _initTransactionDAOProxy();
    return transactionDAO;
  }
  
  public com.smu.dto.TransactionDTO readDataBase(int trnxId) throws java.rmi.RemoteException{
    if (transactionDAO == null)
      _initTransactionDAOProxy();
    return transactionDAO.readDataBase(trnxId);
  }
  
  public boolean deleteRecord(int txnID) throws java.rmi.RemoteException{
    if (transactionDAO == null)
      _initTransactionDAOProxy();
    return transactionDAO.deleteRecord(txnID);
  }
  
  public java.lang.String updateRecord(int ID, java.lang.String nameOnCard) throws java.rmi.RemoteException{
    if (transactionDAO == null)
      _initTransactionDAOProxy();
    return transactionDAO.updateRecord(ID, nameOnCard);
  }
  
  public java.lang.String createResultSetInTable(int ID, java.lang.String nameOnCard, java.lang.String cardNumber, java.lang.String unitPrice, java.lang.String quantity, java.lang.String expDate, java.lang.String creditCardType) throws java.rmi.RemoteException{
    if (transactionDAO == null)
      _initTransactionDAOProxy();
    return transactionDAO.createResultSetInTable(ID, nameOnCard, cardNumber, unitPrice, quantity, expDate, creditCardType);
  }
  
  
}