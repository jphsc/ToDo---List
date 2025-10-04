async function fetchTasks(){
	let itensPendentes = null;
	let itensFazendo = null;
	let itensFeitos = null;
			
	try {
		const response = await fetch("http://localhost:8081/todo-list/api/task/getTasks");
		const data = await response.json();
		
		itensPendentes = data.filter(t => t.status == 1).sort((a, b) => new Date(b.createdAt) - new Date(a.createdAt));
		itensFazendo = data.filter(t => t.status == 2).sort((a, b) => new Date(b.createdAt) - new Date(a.createdAt));
		itensFeitos = data.filter(t => t.status == 3 || t.status == 4).sort((a, b) => new Date(b.finishedAt) - new Date(a.finishedAt));
				
		preencherTarefas(itensPendentes, itensFazendo, itensFeitos);
	
	} catch (error) {
		console.error('Erro ao buscar tarefas:'+ error);
	}
}

function preencherTarefas(itensPendentes, itensFazendo, itensFeitos){
	
	if(itensPendentes != null){
		const elColToDo = document.getElementById("colToDo");
		itensPendentes.forEach(task => createCard(task, elColToDo));
	}  
	
	if(itensFazendo != null){
		const elColDoing = document.getElementById("colDoing");
		itensFazendo.forEach(task => createCard(task, elColDoing));
	}   
		    
	if(itensFeitos != null){
		const elColDone = document.getElementById("colDone");
		itensFeitos.forEach(task => createCard(task, elColDone));
	}
}

function createCard(task, el){
			
	const action = task.status == 1 ? "doing" : "doned";
	const divSeeTask = document.createElement("div");
	const cardBody = document.createElement("div");
	const cardTitle = document.createElement("h6");
	const cardSubtitle = document.createElement("h7");
	const cardText = document.createElement("p");
	const btnEdit = document.createElement("a");
	const btnDelete = document.createElement("a");
	const btnAlterSituation = document.createElement("a");
	const btnCancelTask = document.createElement("a");
	const divActionsTask = document.createElement("div");
	const iconBtnAlterSituation = document.createElement("i");
	const iconBtnCancelTask = document.createElement("i");
	const iconBtnEditTask = document.createElement("i");
	const iconBtnDeleteTask = document.createElement("i");
			
	divSeeTask.setAttribute("class", "card-detail");
	divSeeTask.addEventListener("click", function() { seeCardDetail(task); });
			
	cardBody.setAttribute("class", "card-body");
            
	cardTitle.setAttribute("class", "card-title");
	cardTitle.innerHTML = task.title.length > 20 ? task.title.substring(0,19).concat("...") : task.title;
            
	cardSubtitle.setAttribute("class", "card-subtitle mb-2 text-body-secondary");
	cardSubtitle.innerHTML = task.status == 3 || task.status == 4 
		? task.status == 3 
			? "Doned at: "	 +getDate('finishedAt', task)
			: "Canceled at: "+getDate('finishedAt', task)
        : "Limit date: "	 +getDate('finishLimit', task);
		
	cardText.setAttribute("class", "card-text");
	cardText.innerHTML = task.description.length > 30 ? task.description.substring(0, 29).concat(" ...") : task.description;
            
	divActionsTask.setAttribute("class", "div-action-task");

	iconBtnEditTask.setAttribute("class", "bi bi-pencil-square");
	btnEdit.setAttribute("class", "btn btn-success");
	btnEdit.setAttribute("href", "pageEditTask?task_id="+task.id);
	btnEdit.appendChild(iconBtnEditTask);

	iconBtnDeleteTask.setAttribute("class", "bi bi-trash3");
	btnDelete.setAttribute("class", "btn btn-danger");
	btnDelete.setAttribute("href", "deleteTask?task_id="+task.id);
	btnDelete.appendChild(iconBtnDeleteTask);

	iconBtnAlterSituation.setAttribute("class", "bi bi-arrow-right-circle-fill");
	btnAlterSituation.setAttribute("class", "btn btn-primary");
	btnAlterSituation.setAttribute("href", "alterTask?task_id="+task.id+"&action="+action);
	btnAlterSituation.appendChild(iconBtnAlterSituation);

	iconBtnCancelTask.setAttribute("class", "bi bi-x-circle");
	btnCancelTask.setAttribute("class", "btn btn-danger btn-sm");
	btnCancelTask.setAttribute("href", "alterTask?task_id="+task.id+"&action=cancel");
	btnCancelTask.appendChild(iconBtnCancelTask);
            
	divSeeTask.appendChild(cardTitle);
	divSeeTask.appendChild(cardSubtitle);
	divSeeTask.appendChild(cardText);
	cardBody.appendChild(divSeeTask);
            
	if(!(task.status == 3 || task.status == 4)){ 	
		divActionsTask.appendChild(btnEdit);
		if (task.status == 1) divActionsTask.appendChild(btnDelete);
		divActionsTask.appendChild(btnAlterSituation);
		divActionsTask.appendChild(btnCancelTask);
		cardBody.appendChild(divActionsTask);
	} else {
		let badge = document.createElement("span");
		badge.setAttribute("class", "badge text-bg-danger");
		badge.innerHTML = task.status == 3 ? "Finalizada" : "Cancelada";
		cardBody.appendChild(badge);
	}
            
	el.appendChild(cardBody);
	el.appendChild(document.createElement("hr"));
}
		
function seeCardDetail(task){
	const modal = new bootstrap.Modal(document.getElementById('modal'));
	let status;
	let color = "danger";
			
	switch(task.status) {
		case 1:
			status = "Pendente";
			color = "primary";
			break;
		case 2:
			status = "Em andamento";
			color = "warning";
			break;
		case 3:
			status = "Finalizada";
			break;
		case 4:
			status = "Cancelada";
			break;
	}
	
	const content =	
		(task.status == 1 || task.status == 2
			?  '<strong>Limite de encerramento</strong>: '	+getDate('finishLimit', task)
			:  (task.status == 3 
				? '<strong>Encerrado em</strong>: '			+getDate('finishedAt', task) 
				: '<strong>Cancelado em</strong>: '    		+getDate('finishedAt', task)
				)
		)
		+'<br>'+'<strong>Situação</strong>: '+'<span class="badge text-bg-'+color+'">'+status+'</span>'
		+'<br>'+'<strong>Descrição</strong>: '+task.description
		  	
		document.getElementById('titleModal').innerHTML = task.title;
		document.getElementById('modal-body').innerHTML = content;
			
		modal.show();
}

function getDate(originDate, task){
	if(originDate == "finishLimit"){
		return task.finishLimit.substring(8,10)+"/"+task.finishLimit.substring(5,7)+"/"+task.finishLimit.substring(0,4);
	} else if (originDate == "finishedAt"){
		return task.finishedAt.substring(8,10) +'/'+task.finishedAt.substring(5,7) +'/'+task.finishedAt.substring(0,4);
	}
}