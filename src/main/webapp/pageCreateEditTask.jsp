<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<%@ include file="index-header.jsp" %>
		<title>ToDo - Page ${acao == 'Create' ? 'Create' : 'Edit'} Task</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous" defer></script>
		
		<style>
			.div-main{
				width: 80%;
				margin: 0 auto;
			}
		</style>
	</head>
	<body onload="initPage()">
		<div class="div-header">
			<h3>${acao == 'Create' ? 'Create' : 'Edit'} Task</h3>
		</div>
		<form class="div-main" action="${acao == 'Create' ? 'createTask' : 'editTask'}">
			<input type="hidden" name="task_id" value="${acao == 'Edit' ? task.getId() : ''}">
			<input type="hidden" name="createdAt" value="${acao == 'Edit' ? task.getCreatedAt() : ''}">
			<input type="hidden" name="finishedAt" value="${acao == 'Edit' ? task.getFinishedAt() : ''}">
			<input type="hidden" name="status" value="${acao == 'Edit' ? task.getStatus() : ''}">
			<input type="hidden" name="version" value="${acao == 'Edit' ? task.getVersion() : ''}">
			
			<div class="input-group mb-3">
			  <span class="input-group-text">Title</span>
			  <input type="text" id="title" value="${acao == 'Edit' ? task.title : 'Titulo'}" name="title" class="form-control" placeholder="Task to make something" aria-label="title" aria-describedby="title" required>
			</div>
			
			<div class="input-group mb-3">
			  <span class="input-group-text" id="date-finish">Finish Limit</span>
			  <input type="date" value="${acao == 'Edit' ? task.finishLimit : '2025-01-01'}" name="date-finish" class="form-control" placeholder="2025-01-01" aria-label="date-finish" aria-describedby="date-finish" required>
			</div>
			
			
			<div class="input-group mb-3">
			  <span class="input-group-text" id="title">Category</span>
				<select class="form-select" name="category" aria-label="Default select example">
					<c:forEach var="cat" items="${categories}">
						<option value="${cat.getId()}">${cat.getName()}</option>
					</c:forEach>
				</select>
			</div>
			
			<div class="input-group">
			  <span class="input-group-text">Description</span>
			  <textarea name="description" class="form-control" aria-label="description" required>${acao == 'Edit' ? task.description : 'Valor teste'}</textarea>
			</div>
			
			<div class="div-actions">
				<button type="submit" class="btn btn-success" value="">${acao == 'Create' ? 'CRIAR' : 'EDITAR'}</button>
				<a href="index.jsp" class="btn btn-danger" value="">CANCELAR</a>
			</div>
		</form>
	</body>
	
	<script>
		function initPage(){
			document.getElementById("title").focus();
		}
	</script>
</html>