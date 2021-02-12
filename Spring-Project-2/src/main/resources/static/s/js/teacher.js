/**
 * 
 */

$(function () {
	$('.student-list-item').on('click',studentListItemClick);
})

function updateScores() {
	alert(this.id);
}

function updateQuizList() {
	let quizListGroup=document.getElementById("quizListGroup");
	quizListGroup.innerHTML="";
	for (let i=0;i<quizList.length;++i) {
		let div=document.createElement("div");
		div.classList.add("list-item");
		div.setAttribute("id",quizList[i]['id'])
		div.onclick=updateScores;
		let link=document.createElement("a");
		link.setAttribute("href","#");
		link.classList.add("list-group-item");
		link.classList.add("list-group-item-action");
		link.innerText=quizList[i]['name'];
		div.appendChild(link);
		quizListGroup.appendChild(div);
	}
}

var quizList=[];

function studentListItemClick() {
	let student=this.getAttribute("student");
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function () {
		if (this.readyState == 4) {
			if (this.status == 200) {
				quizList = JSON.parse(this.responseText);
				updateQuizList();
			} else {
				alert("error");
			}
		}
	};
	let uri = "/s/getQuizzes?student="+student;
	xhttp.open("GET", uri);
	xhttp.send();
}
