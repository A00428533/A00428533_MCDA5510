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
        String trnxId_1id=  request.getParameter("trnxId38");
        int trnxId_1idTemp  = Integer.parseInt(trnxId_1id);
        com.smu.dto.TransactionDTO readDataBase13mtemp = sampleTransactionDAOProxyid.readDataBase(trnxId_1idTemp);
if(readDataBase13mtemp == null){
%>
<%=readDataBase13mtemp %>
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
if(readDataBase13mtemp != null){
java.lang.String typecreatedBy16 = readDataBase13mtemp.getCreatedBy();
        String tempResultcreatedBy16 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typecreatedBy16));
        %>
        <%= tempResultcreatedBy16 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">creditCardType:</TD>
<TD>
<%
if(readDataBase13mtemp != null){
java.lang.String typecreditCardType18 = readDataBase13mtemp.getCreditCardType();
        String tempResultcreditCardType18 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typecreditCardType18));
        %>
        <%= tempResultcreditCardType18 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">iD:</TD>
<TD>
<%
if(readDataBase13mtemp != null){
%>
<%=readDataBase13mtemp.getID()
%><%}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">expDate:</TD>
<TD>
<%
if(readDataBase13mtemp != null){
java.lang.String typeexpDate22 = readDataBase13mtemp.getExpDate();
        String tempResultexpDate22 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typeexpDate22));
        %>
        <%= tempResultexpDate22 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">nameOnCard:</TD>
<TD>
<%
if(readDataBase13mtemp != null){
java.lang.String typenameOnCard24 = readDataBase13mtemp.getNameOnCard();
        String tempResultnameOnCard24 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typenameOnCard24));
        %>
        <%= tempResultnameOnCard24 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">unitPrice:</TD>
<TD>
<%
if(readDataBase13mtemp != null){
%>
<%=readDataBase13mtemp.getUnitPrice()
%><%}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">value:</TD>
<TD>
<%
if(readDataBase13mtemp != null){
%>
<%=readDataBase13mtemp.getValue()
%><%}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">totalPrice:</TD>
<TD>
<%
if(readDataBase13mtemp != null){
%>
<%=readDataBase13mtemp.getTotalPrice()
%><%}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">qty:</TD>
<TD>
<%
if(readDataBase13mtemp != null){
%>
<%=readDataBase13mtemp.getQty()
%><%}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">createdOn:</TD>
<TD>
<%
if(readDataBase13mtemp != null){
java.util.Calendar typecreatedOn34 = readDataBase13mtemp.getCreatedOn();
        java.text.DateFormat dateFormatcreatedOn34 = java.text.DateFormat.getDateInstance();
        java.util.Date datecreatedOn34 = typecreatedOn34.getTime();
        String tempResultcreatedOn34 = org.eclipse.jst.ws.util.JspUtils.markup(dateFormatcreatedOn34.format(datecreatedOn34));
        %>
        <%= tempResultcreatedOn34 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">cardNumber:</TD>
<TD>
<%
if(readDataBase13mtemp != null){
java.lang.String typecardNumber36 = readDataBase13mtemp.getCardNumber();
        String tempResultcardNumber36 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typecardNumber36));
        %>
        <%= tempResultcardNumber36 %>
        <%
}%>
</TD>
</TABLE>
<%
}
break;
case 40:
        gotMethod = true;
        String txnID_2id=  request.getParameter("txnID43");
        int txnID_2idTemp  = Integer.parseInt(txnID_2id);
        boolean deleteRecord40mtemp = sampleTransactionDAOProxyid.deleteRecord(txnID_2idTemp);
        String tempResultreturnp41 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(deleteRecord40mtemp));
        %>
        <%= tempResultreturnp41 %>
        <%
break;
case 45:
        gotMethod = true;
        String ID_3id=  request.getParameter("ID48");
        int ID_3idTemp  = Integer.parseInt(ID_3id);
        String nameOnCard_4id=  request.getParameter("nameOnCard50");
            java.lang.String nameOnCard_4idTemp = null;
        if(!nameOnCard_4id.equals("")){
         nameOnCard_4idTemp  = nameOnCard_4id;
        }
        java.lang.String updateRecord45mtemp = sampleTransactionDAOProxyid.updateRecord(ID_3idTemp,nameOnCard_4idTemp);
if(updateRecord45mtemp == null){
%>
<%=updateRecord45mtemp %>
<%
}else{
        String tempResultreturnp46 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(updateRecord45mtemp));
        %>
        <%= tempResultreturnp46 %>
        <%
}
break;
case 52:
        gotMethod = true;
        String ID_5id=  request.getParameter("ID55");
        int ID_5idTemp  = Integer.parseInt(ID_5id);
        String nameOnCard_6id=  request.getParameter("nameOnCard57");
            java.lang.String nameOnCard_6idTemp = null;
        if(!nameOnCard_6id.equals("")){
         nameOnCard_6idTemp  = nameOnCard_6id;
        }
        String cardNumber_7id=  request.getParameter("cardNumber59");
            java.lang.String cardNumber_7idTemp = null;
        if(!cardNumber_7id.equals("")){
         cardNumber_7idTemp  = cardNumber_7id;
        }
        String unitPrice_8id=  request.getParameter("unitPrice61");
            java.lang.String unitPrice_8idTemp = null;
        if(!unitPrice_8id.equals("")){
         unitPrice_8idTemp  = unitPrice_8id;
        }
        String quantity_9id=  request.getParameter("quantity63");
            java.lang.String quantity_9idTemp = null;
        if(!quantity_9id.equals("")){
         quantity_9idTemp  = quantity_9id;
        }
        String expDate_10id=  request.getParameter("expDate65");
            java.lang.String expDate_10idTemp = null;
        if(!expDate_10id.equals("")){
         expDate_10idTemp  = expDate_10id;
        }
        String creditCardType_11id=  request.getParameter("creditCardType67");
            java.lang.String creditCardType_11idTemp = null;
        if(!creditCardType_11id.equals("")){
         creditCardType_11idTemp  = creditCardType_11id;
        }
        java.lang.String createResultSetInTable52mtemp = sampleTransactionDAOProxyid.createResultSetInTable(ID_5idTemp,nameOnCard_6idTemp,cardNumber_7idTemp,unitPrice_8idTemp,quantity_9idTemp,expDate_10idTemp,creditCardType_11idTemp);
if(createResultSetInTable52mtemp == null){
%>
<%=createResultSetInTable52mtemp %>
<%
}else{
        String tempResultreturnp53 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(createResultSetInTable52mtemp));
        %>
        <%= tempResultreturnp53 %>
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