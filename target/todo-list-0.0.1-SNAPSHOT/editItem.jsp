<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ToDo - Home Page</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous" defer></script>
		
		<style>
		  
		  .task-container {
		  	border: 1px solid black;
		  	border-radius: 5px;
		    max-height: 600px; /* ou height: 80vh */
		    overflow: hidden; /* impede que a div principal cresça indefinidamente */
		    padding-top: 10px;
		  }
		
		  .task-column {
		    height: 100%;
		    overflow-y: auto;
		    border: 1px solid #ccc;
		    padding: 10px;
		    min-height: 200px;
		    max-height: 600px; /* mesma altura que a task-container */
		  }
		
		  .card-body {
		    margin-bottom: 10px;
		    border: 1px solid #dee2e6;
		    border-radius: 0.5rem;
		    background-color: #f8f9fa;
		  }
		</style>
				
	</head>
	<body>
		<h1>Página Inicial</h1>
		<div>
			<div class="container text-center" style="justify-content: space-between;">
			  <div class="row">
			    <div class="col" style="background-color: var(--bs-info); color: white;">
			      Pendente
			    </div>
			    <div class="col" style="background-color: var(--bs-warning); color: white;">
			      Em andamento
			    </div>
			    <div class="col" style="background-color: var(--bs-success); color: white;">
			      Concluído
			    </div>
			  </div>
			</div>
			<main>
				<div class="container text-center task-container" style="justify-content: space-between;">
				  <div class="row">
				  	<div class="col task-column" id="colToDo">
				    </div>
				  	<div class="col task-column" id="colDoing">
				    </div>
				  	<div class="col task-column" id="colDone">
				    </div>
				  </div>
				</div>
			</main>
		</div>
	</body>
	
	<script>
		
	</script>
</html>