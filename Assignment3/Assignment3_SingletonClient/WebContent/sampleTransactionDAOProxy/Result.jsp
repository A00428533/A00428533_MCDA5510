<%@page contentType="text/html;charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<HTML>
<HEAD>
<TITLE>Result</TITLE>
</HEAD>
<BODY>
<H1>Result</H1>

<jsp:useBean id="sampleTransactionDAOProxyid" scope="session" class="com.smu.dao.TransactionDAOProxy" />
<%
if (request.getParameter("endpoint") != null && request.getParameter("endpoint").length() > 0)
sampleTransactionDAOProxyid.setEndpoint(request.getParameter("endpoint"));
%>

<%
String method = request.getParameter("method");
int methodID = 0;
if (method == null) methodID = -1;

if(methodID != -1) methodID = Integer.parseInt(method);
boolean gotMethod = false;

try {
switch (methodID){ 
case 2:
        gotMethod = true;
        java.lang.String getEndpoint2mtemp = sampleTransactionDAOProxyid.getEndpoint();
if(getEndpoint2mtemp == null){
%>
<%=getEndpoint2mtemp %>
<%
}else{
        String tempResultreturnp3 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(getEndpoint2mtemp));
        %>
        <%= tempResultreturnp3 %>
        <%
}
break;
case 5:
        gotMethod = true;
        String endpoint_0id=  request.getParameter("endpoint8");
            java.lang.String endpoint_0idTemp = null;
        if(!endpoint_0id.equals("")){
         endpoint_0idTemp  = endpoint_0id;
        }
        sampleTransactionDAOProxyid.setEndpoint(endpoint_0idTemp);
break;
case 10:
        gotMethod = true;
        com.smu.dao.TransactionDAO getTransactionDAO10mtemp = sampleTransactionDAOProxyid.getTransactionDAO();
if(getTransactionDAO10mtemp == null){
%>
<%=getTransactionDAO10mtemp %>
<%
}else{
        if(getTransactionDAO10mtemp!= null){
        String tempreturnp11 = getTransactionDAO10mtemp.toString();
        %>
        <%=tempreturnp11%>
        <%
        }}
break;
case 13:
        gotMethod = true;
        String ID_1id=  request.getParameter("ID16");
        int ID_1idTemp  = Integer.parseInt(ID_1id);
        String nameOnCard_2id=  request.getParameter("nameOnCard18");
            java.lang.String nameOnCard_2idTemp = null;
        if(!nameOnCard_2id.equals("")){
         nameOnCard_2idTemp  = nameOnCard_2id;
        }
        String cardNumber_3id=  request.getParameter("cardNumber20");
            java.lang.String cardNumber_3idTemp = null;
        if(!cardNumber_3id.equals("")){
         cardNumber_3idTemp  = cardNumber_3id;
        }
        String unitPrice_4id=  request.getParameter("unitPrice22");
            java.lang.String unitPrice_4idTemp = null;
        if(!unitPrice_4id.equals("")){
         unitPrice_4idTemp  = unitPrice_4id;
        }
        String quantity_5id=  request.getParameter("quantity24");
            java.lang.String quantity_5idTemp = null;
        if(!quantity_5id.equals("")){
         quantity_5idTemp  = quantity_5id;
        }
        String expDate_6id=  request.getParameter("expDate26");
            java.lang.String expDate_6idTemp = null;
        if(!expDate_6id.equals("")){
         expDate_6idTemp  = expDate_6id;
        }
        String creditCardType_7id=  request.getParameter("creditCardType28");
            java.lang.String creditCardType_7idTemp = null;
        if(!creditCardType_7id.equals("")){
         creditCardType_7idTemp  = creditCardType_7id;
        }
        java.lang.String createResultSetInTable13mtemp = sampleTransactionDAOProxyid.createResultSetInTable(ID_1idTemp,nameOnCard_2idTemp,cardNumber_3idTemp,unitPrice_4idTemp,quantity_5idTemp,expDate_6idTemp,creditCardType_7idTemp);
if(createResultSetInTable13mtemp == null){
%>
<%=createResultSetInTable13mtemp %>
<%
}else{
        String tempResultreturnp14 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(createResultSetInTable13mtemp));
        %>
        <%= tempResultreturnp14 %>
        <%
}
break;
case 30:
        gotMethod = true;
        String ID_8id=  request.getParameter("ID33");
        int ID_8idTemp  = Integer.parseInt(ID_8id);
        String nameOnCard_9id=  request.getParameter("nameOnCard35");
            java.lang.String nameOnCard_9idTemp = null;
        if(!nameOnCard_9id.equals("")){
         nameOnCard_9idTemp  = nameOnCard_9id;
        }
        java.lang.String updateRecord30mtemp = sampleTransactionDAOProxyid.updateRecord(ID_8idTemp,nameOnCard_9idTemp);
if(updateRecord30mtemp == null){
%>
<%=updateRecord30mtemp %>
<%
}else{
        String tempResultreturnp31 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(updateRecord30mtemp));
        %>
        <%= tempResultreturnp31 %>
        <%
}
break;
case 37:
        gotMethod = true;
        String txnID_10id=  request.getParameter("txnID40");
        int txnID_10idTemp  = Integer.parseInt(txnID_10id);
        boolean deleteRecord37mtemp = sampleTransactionDAOProxyid.deleteRecord(txnID_10idTemp);
        String tempResultreturnp38 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(deleteRecord37mtemp));
        %>
        <%= tempResultreturnp38 %>
        <%
break;
case 42:
        gotMethod = true;
        String trnxId_11id=  request.getParameter("trnxId67");
        int trnxId_11idTemp  = Integer.parseInt(trnxId_11id);
        com.smu.dto.TransactionDTO readDataBase42mtemp = sampleTransactionDAOProxyid.readDataBase(trnxId_11idTemp);
if(readDataBase42mtemp == null){
%>
<%=readDataBase42mtemp %>
<%
}else{
%>
<TABLE>
<TR>
<TD COLSPAN="3" ALIGN="LEFT">returnp:</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">createdBy:</TD>
<TD>
<%
if(readDataBase42mtemp != null){
java.lang.String typecreatedBy45 = readDataBase42mtemp.getCreatedBy();
        String tempResultcreatedBy45 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typecreatedBy45));
        %>
        <%= tempResultcreatedBy45 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">creditCardType:</TD>
<TD>
<%
if(readDataBase42mtemp != null){
java.lang.String typecreditCardType47 = readDataBase42mtemp.getCreditCardType();
        String tempResultcreditCardType47 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typecreditCardType47));
        %>
        <%= tempResultcreditCardType47 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">iD:</TD>
<TD>
<%
if(readDataBase42mtemp != null){
%>
<%=readDataBase42mtemp.getID()
%><%}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">expDate:</TD>
<TD>
<%
if(readDataBase42mtemp != null){
java.lang.String typeexpDate51 = readDataBase42mtemp.getExpDate();
        String tempResultexpDate51 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typeexpDate51));
        %>
        <%= tempResultexpDate51 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">nameOnCard:</TD>
<TD>
<%
if(readDataBase42mtemp != null){
java.lang.String typenameOnCard53 = readDataBase42mtemp.getNameOnCard();
        String tempResultnameOnCard53 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typenameOnCard53));
        %>
        <%= tempResultnameOnCard53 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">unitPrice:</TD>
<TD>
<%
if(readDataBase42mtemp != null){
%>
<%=readDataBase42mtemp.getUnitPrice()
%><%}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">value:</TD>
<TD>
<%
if(readDataBase42mtemp != null){
%>
<%=readDataBase42mtemp.getValue()
%><%}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">totalPrice:</TD>
<TD>
<%
if(readDataBase42mtemp != null){
%>
<%=readDataBase42mtemp.getTotalPrice()
%><%}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">qty:</TD>
<TD>
<%
if(readDataBase42mtemp != null){
%>
<%=readDataBase42mtemp.getQty()
%><%}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">createdOn:</TD>
<TD>
<%
if(readDataBase42mtemp != null){
java.util.Calendar typecreatedOn63 = readDataBase42mtemp.getCreatedOn();
        java.text.DateFormat dateFormatcreatedOn63 = java.text.DateFormat.getDateInstance();
        java.util.Date datecreatedOn63 = typecreatedOn63.getTime();
        String tempResultcreatedOn63 = org.eclipse.jst.ws.util.JspUtils.markup(dateFormatcreatedOn63.format(datecreatedOn63));
        %>
        <%= tempResultcreatedOn63 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">cardNumber:</TD>
<TD>
<%
if(readDataBase42mtemp != null){
java.lang.String typecardNumber65 = readDataBase42mtemp.getCardNumber();
        String tempResultcardNumber65 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typecardNumber65));
        %>
        <%= tempResultcardNumber65 %>
        <%
}%>
</TD>
</TABLE>
<%
}
break;
}
} catch (Exception e) { 
%>
Exception: <%= org.eclipse.jst.ws.util.JspUtils.markup(e.toString()) %>
Message: <%= org.eclipse.jst.ws.util.JspUtils.markup(e.getMessage()) %>
<%
return;
}
if(!gotMethod){
%>
result: N/A
<%
}
%>
</BODY>
</HTML>