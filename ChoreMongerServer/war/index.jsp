<!doctype html>
<%@ page import="com.google.appengine.api.users.User" %><%@ page import="com.google.appengine.api.users.UserService" %><%@ page import="com.google.appengine.api.users.UserServiceFactory" %><%
	UserService userService = UserServiceFactory.getUserService(); 
	User user = userService.getCurrentUser();
%>
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link type="text/css" rel="stylesheet" href="ChoreMonger.css">
    <title>Chore Monger</title>
    <script type="text/javascript" language="javascript" src="choremonger/choremonger.nocache.js"></script>
  </head>

  <body>

    <!-- OPTIONAL: include this if you want history support -->
    <iframe src="javascript:''" id="__gwt_historyFrame" tabIndex='-1' style="position:absolute;width:0;height:0;border:0"></iframe>
    
    <noscript>
      <div style="width: 22em; position: absolute; left: 50%; margin-left: -11em; color: red; background-color: white; border: 1px solid red; padding: 4px; font-family: sans-serif">
        Your web browser must have JavaScript enabled
        in order for this application to display correctly.
      </div>
    </noscript>

	<% if (user != null) {%>
		<div style="text-align: right;">Welcome, <%= user.getNickname() %>!
        | <a href="<%=userService.createLogoutURL("/")%>">Log Out</a>
		<% if (userService.isUserAdmin()) {%>
		| <a href="/admin/">Admin</a>
		<% }%></div>
	<% } %>
    <div><h1>Chore Monger</h1></div>

    <table align="center">
      <tr>
        <td style="font-weight:bold;">ID:</td>
        <td colspan="3" id="idFieldContainer"></td>        
      </tr>
      <tr>
        <td style="font-weight:bold;">Name:</td>
        <td colspan="3" id="nameFieldContainer"></td>        
      </tr>
      <tr>
        <td style="font-weight:bold;">Street:</td>
        <td colspan="3" id="streetFieldContainer"></td>        
      </tr>
      <tr>
        <td style="font-weight:bold;">City:</td>
        <td colspan="3" id="cityFieldContainer"></td>        
      </tr>
      <tr>
        <td style="font-weight:bold;">State:</td>
        <td colspan="3" id="stateFieldContainer"></td>        
      </tr>
      <tr>
        <td style="font-weight:bold;">Zip:</td>
        <td colspan="3" id="zipFieldContainer"></td>        
      </tr>
      <tr>
      	<td id="getButtonContainer"></td>
      	<td id="postButtonContainer"></td>
      	<td id="putButtonContainer"></td>
      	<td id="deleteButtonContainer"></td>
      </tr>
      <tr>
        <td colspan="4" style="color:red;" id="errorLabelContainer"></td>
      </tr>
    </table>
  </body>
</html>
