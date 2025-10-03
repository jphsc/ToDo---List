<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ToDo - Home Page</title>
		<%@ include file="index-header.jsp" %>
		<link rel="stylesheet" href="style-index.css">
		<script src="main-index.js"></script>				
	</head>
	<body onload="fetchTasks()">
		<div class="div-header">
			<a class="btn btn-outline-primary" href=pageCreateTask>Create Task</a>
		</div>
		<div>
			<div class="container text-center" style="justify-content: space-between;">
			  <div class="row">
			    <div class="col" style="background-color: var(--bs-info); color: white;">Pendente</div>
			    <div class="col" style="background-color: var(--bs-warning); color: white;">Em andamento</div>
			    <div class="col" style="background-color: var(--bs-success); color: white;">Concluído</div>
			  </div>
			</div>
			<main>
				<div class="container text-center task-container" style="justify-content: space-between;">
				  <div class="row">
				  	<div class="col task-column" id="colToDo"></div>
				  	<div class="col task-column" id="colDoing"></div>
				  	<div class="col task-column" id="colDone"></div>
				  </div>
				</div>
			</main>
		</div>
		
		<div class="modal fade" id="modal" tabindex="-1" aria-labelledby="titleModal" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h1 class="modal-title fs-5" id="titleModal"></h1>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body" id="modal-body"></div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
		        <!-- <button type="button" class="btn btn-primary">Save changes</button> -->
		      </div>
		    </div>
		  </div>
		</div>
	</body>
</html>