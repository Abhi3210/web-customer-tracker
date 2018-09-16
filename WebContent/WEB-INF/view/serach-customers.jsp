<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<title>Customer list</title>
<link type="text/css" rel="stylesheet"
      href="${pageContext.request.contextPath}/resources/css/style.css"/>

</head>
<body>
 <div id="wrapper">
   <div id="header">
     <h2>CRM - Customer Relationship Manager</h2>
   </div>
 </div>
 
 <div id="container">
    <div id="content">
    <!-- put new button: Add Customer -->
			<input type="button" value="Add Customer"
				   onclick="window.location.href='showFormForAdd'; return false;"
				   class="add-button"
			/>
			
    <!--  add a search box -->
			<form:form action="search" method="POST">
				Search customer: <input type="text" name="theSearchName" />
             <input type="submit" value="Search" class="add-button" />
			</form:form>				
							
    <!-- Add out html table here -->
    <table style="border:1px solid black;">
     <tbody>
       <tr>
         <th>Customer Id</th>
         <th>First Name</th>
         <th>Last Name</th>
         <th>Email</th>
         <th>Action</th>
        </tr>
        <!-- loop over and print our customers --> 
        <c:forEach var="tempCustomers" items="${customers}">
        
        <!-- construct an "update" link with customer id -->
        <c:url var="updateLink" value="/customer/showFormForUpdate">
          <c:param name="customerId" value="${tempCustomers.id}" />
        </c:url>
        
        <!-- construct an "delete" link with customer id -->
        <c:url var="deleteLink" value="/customer/delete">
          <c:param name="customerId" value="${tempCustomers.id}" />
        </c:url>
        
        <tr>
          <td>${tempCustomers.id}</td>
          <td>${tempCustomers.first_name}</td>
          <td>${tempCustomers.last_name}</td>
          <td>${tempCustomers.email}</td>
          <!-- Display the Update link -->
          <td>
           <a href="${updateLink}"><input type="submit" value="Update" class="add-button" /></a>
           |
           <a href="${deleteLink}"
             onclick="if(!(confirm('Are you sure want to delete this Customer?'))) return false">
             <input type="submit" value="Delete" class="delete-button" /></a>
             
          </td>
        </c:forEach>
        </tbody>
    </table>
    
    </div>
    
    <div style="clear; both;"></div>
		
		<p>
			<a href="${pageContext.request.contextPath}/customer/list">Back to List</a>
		</p>
    
 
 </div>


</body>

</html>