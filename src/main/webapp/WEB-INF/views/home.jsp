<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page isELIgnored="false" %>   

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>To Do List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        /* Custom CSS for blurring the background */
        .modal-backdrop {
            backdrop-filter: blur(5px);
        }
    </style>
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <!-- Sidebar -->
         <div class="col-4">
            <nav class="nav flex-column text-center">
             <div class="col-8 p-5">
                <button class="btn btn-primary btn-lg mb-4" data-bs-toggle="modal" data-bs-target="#inputModal_1">
                    Create New To Do List
                </button>
              </div>
            </nav>
        </div>
        
        
        
        
        
        
        <!-- Error message for deleting the data  -->
        
        <c:if test="${error_message_of_deleting !=null }">
         
          <script>
          window.onload=function(){
              showAlert('${error_message_of_deleting }') ;
          }
          
          function showAlert(message)
          {
        	  alert(message);
          }
          
          </script>
          
        </c:if>
        
        
        <!-- CARD LOOP Main Content -->
        <div class="col-8">
            <h1>Your To Do List</h1>
            <div class="row">
            
          <c:forEach var="usertask" items="${AllToDoListOfUser}">
            <div class="col-md-4 mb-4">
                <div class="card h-100 shadow-sm">
                    <div class="card-body">
                        <h5 class="card-title font-weight-bold">${usertask.title}</h5>
                        <p class="card-text">${usertask.description}</p>

                        <div class="d-flex justify-content-between mt-3">
                            
                             <button class="btn btn-primary btn-sm"  data-bs-toggle="modal"  onclick=" populateEditModal('${usertask.id}')"  data-bs-target="#inputModal_2" >  
                                Edit
                            </button>
                            
                            <a href="delete_to_do_list_element?id=${usertask.id}"   class="btn btn-danger btn-sm">Delete</a>
                        </div>

                        <hr>

                        <div class="text-muted small">
                            <p class="mb-1">Created: ${usertask.created_at}</p>
                            <p class="mb-0">Updated: ${usertask.updated_at}</p>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
        
            </div>
        </div>
    </div>
</div>




<!-- Create New To Do List Modal -->
<div class="modal fade" id="inputModal_1" tabindex="-1" role="dialog" aria-labelledby="inputModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="inputModalLabel">Create Your To Do</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="create_to_do_list_data" method="post">
                    <div class="form-group">
                        <label for="title">Title</label>
                        <input type="text" class="form-control" id="title" name="title" placeholder="Enter your title">
                    </div>
                    <div class="form-group">
                        <label for="description">Description</label>
                        <textarea class="form-control" id="description" name="description" placeholder="Enter your description"></textarea>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>








<!-- Error Message for not sava data  -->

<c:if test="${edit_to_do_list_error != null}">
   <script>
        function showAlert(message) {
            alert(message);
        }

        window.onload = function() {
            showAlert('${edit_to_do_list_error}');
        }
   </script>
</c:if>



<!-- Edit To Do List Modal -->
<div class="modal fade" id="inputModal_2" tabindex="-1" role="dialog" aria-labelledby="inputModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="inputModalLabel">Edit Your To Do</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            
            
            <form action="edit_to_do_list"  method="post">
            <div class="modal-body">
                
                    <!--  <div class="form-group">
                        
                        <input type="hidden" class="form-control" id="edit-task-userid" name="user_id" >
                    </div>--> 
                    
                    <div class="form-group">
                        
                        <input type="hidden" class="form-control" id="edit-task-id" name="id" >
                    </div>
                    
                    <div class="form-group">
                        <label for="title">Title</label>
                        <input type="text" class="form-control" id="edit-task-title" name="title" placeholder="Enter your title">
                    </div>
                    <div class="form-group">
                        <label for="description">Description</label>
                        <textarea class="form-control" id="edit-task-description" name="description" placeholder="Enter your description"></textarea>
                    </div>
                
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>
            </form>
            
            
        </div>
    </div>
</div>

<!-- to set values in the edit page  -->
<script>

 function populateEditModal(taskid)
 {
	  document.getElementById("edit-task-id").value=taskid;
 }
 
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
