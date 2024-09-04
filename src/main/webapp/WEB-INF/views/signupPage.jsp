<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page isELIgnored="false" %>   
    
    
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">
    <!-- <link rel="stylesheet" href="css/Online.css">
    <link rel="stylesheet" href="cs/all.css"> -->
    <style>
        body{
        display:flex;
        align-items: center;
        height:600px;
        background-color: #FFF;
        /* background-image: url(https://themewagon.github.io/musico/img/banner/contact.png); */
        background-size: cover;
        background-position: center;
        height: 100vh;
        display: flex;
        align-items: center;
        justify-content: center;
        margin: 0;
        backdrop-filter: blur(2px);
    }
    body:before {
        content: "";
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        /* background: linear-gradient(to bottom, rgba(0, 29, 56, 0.6) 0%, #001d38 100%); */
        z-index: -1; /* Places the gradient behind the body content */
    }
        .box{
            height:auto;
            width:100%;
            /* background-color: red; */
        }
        .hello{
            border:none;
            border-bottom: 2px solid black;
            /* outline:blueviolet; */
            box-shadow:no ;
            /* background-color: red; */
        }
        .Sign{
            font-size:25px;
            /* font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif; */
            /* font-weight: bold; */
            display: flex;
            align-items: center;
            justify-content: center;
            /* background-color: yellow; */


        }
        .sbutton{
            background-color:rgb(226, 226, 15);
            color: white;
            border: none;
            border-radius: 20px;
            width: 90%;
            height:40px;
        }
        .sbutton:hover{
            background-color: rgb(65, 55, 55);
        }
        .sbutton:active{
            background-color: black;
        }

        .image img{
            border-bottom-left-radius: 20px;
            border-top-left-radius: 20px;
            width: 100%;
            height:100%;

        }
        .mode{
            border-radius: 20px;
        }
        .carddiv{

            /* border: 1px solid black; */
            /border-right: 2px solid black;/
            background-color: #fff3f3;
            border-radius: 20px ;
            /* margin: 20px; */


            

        }
        .logo{

            /* background-color: red; */
            height: 120px;
        }
        .h{
            /* background-color: yellow; */
            /* width: 100%; */
            display: flex;
            justify-content: center;
            
        }
        .container{

            /* background-color: blue; */
            height: 500px;
        }
        .logo img{
            height:100%;
            /* width: 50%; */
        }
        
    </style>
</head>
<body>
    <div class="box">
        <div class="container d-flex justify-content-center">
            <!-- <div class="row d-flex justify-content-center  h"> -->
                <div class="col-6 d-flex align-items-center flex-column   h">
                    <div class="col-9 d-flex justify-content-center align-items-end logo " ><img src="logohack.jpeg" alt=""></div>
                    <div class="card carddiv col-9">
                    
                    
                    <c:if test="${messageaboutemail != null}">
                       <script>
                            function showAlert(message)
                            {
                            	alert(message);
                            }
                         
                           window.onload=function()
                           {
                        	 showAlert('${messageaboutemail}');
                           }
                         
                       </script>
                    
                    </c:if>
                    
                        <div class="card-body ">
                            <div class="row Sign">
                                Sign in or create account 
                            </div>
                            <!-- <div class="row Sign">
                                Sign in or create account 
                            </div> -->
                            <!-- <div class="row-12 text-center" id="result">

                            </div> -->
                            <form id="myForm" action="getUserDetail" method="post" >
                                <div class="row my-5">
                                 <div class="row my-4">
                                    <div class="form-floating mb-3">
                                        <input type="text" class="form-control hello" id="floatingInput"  name="username">
                                        <label for="floatingInput">Enter Name</label>
                                    </div>

                                </div>
                                 
                                 
                                
                                
                                    <div class="form-floating mb-3 wide">
                                        <input type="email" class="form-control hello " id="floatingInput" placeholder="name@example.com" name="email">
                                        <label for="floatingInput">Enter Email</label>
                                    </div>
                                    
                                    <div class="row my-4">
                                    <div class="form-floating mb-3">
                                        <input type="password" class="form-control hello" id="floatingInput"  name="password">
                                        <label for="floatingInput">Enter password</label>
                                    </div>

                                </div>

                                </div>
                               
                                
                               <button  type="submit" class="sbutton mx-auto my-4" >Continue</button>
                                 
                            </form>
                        </div>
                         <div class="text-center my-3">
                            <a href="loginPage" class="btn btn-primary btn-lg sbutton mx-auto">Sign In</a>
                         </div>
                    </div>
                </div>
            <!-- </div> -->
        </div>
    </div>
          <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
  crossorigin="anonymous"></script>

    </div>
</body>
</html>