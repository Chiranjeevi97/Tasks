<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login </title>
</head>
<body>
<div >
 
 <h2> Login Page </h2>
 <p id="error"></p>
 <form name="loginfrom" onsubmit="checkvalidate()" action="Login" method="get">
 
 <b> User name: <input type="text" name="uname"></b><br><br>
 <b> Password: <input type="password" name="password"></b><br><br>
  <input type="submit" value="Submit">
  </form>
  <br>
  <a href="SignUp.jsp">SIGN UP </a>
   </div>
   
  
   <script> 
function checkvalidate()                                    
{ 
    var name = document.forms["loginform"]["uname"];               
    var password = document.forms["loginform"]["password"];    
    
   
    if (name.value == "")                                  
    { 
        window.alert("Please enter your name."); 
        name.focus(); 
        return false; 
    } 
    if (password.value == "")                                  
    { 
        window.alert("Please enter your name."); 
        password.focus(); 
        return false; 
    } 
}
</script>
   
 
</body>
</html>