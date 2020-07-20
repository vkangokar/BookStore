
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- <%@page import="com.captcha.botdetect.web.servlet.Captcha"%> --%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> Book store</title>
</head>
<body>

	<center><h1>BookStore!!!</h1>

	<h5>Welcome and place your order!! </h5><br></center>
	
	<td>Register!</td>
	
	<a href="user/register.htm"><input value="Sign Up:" type="submit" ></a><br/><br><br>

	<h2>Login</h2>
	<form action="user/login" 
		method="post">
	
		<table>
		<tr><td>Login!</td>	
		<tr>
		    <td>User Name:</td>
		    <td>
		    <input name="username" required="required"   size="30" placeholder= "Enter your Username" />
		    </td>
		</tr>
		
		<tr>
		    <td>Password:</td>
		    <td><input type="password" name="password" size="30" required="required" placeholder= "Enter your password"/></td>
		</tr>
	<%-- 	<tr>
	 		<td colspan="2">
	 		<label for="captchaCode" class="prompt">Retype the characters from the picture:</label> 
	 		 <%
                    // Adding BotDetect Captcha to the page
                    Captcha captcha = Captcha.load(request, "CaptchaObject");
                    captcha.setUserInputID("captchaCode");

                    String captchaHtml = captcha.getHtml();
                    out.write(captchaHtml);
                %> 
                <input id="captchaCode" type="text" name="captchaCode" required="required"/>
	 		</td>
	 	</tr> --%>
		
		<tr>
		    <td colspan="2">
		    
		    <input type="submit" value="Login" /></td>
		</tr>
				
		</table>

	</form>


</body>
</html>


