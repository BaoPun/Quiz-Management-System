

$(function () {
	$('.student-list-item').on('click',studentListItemClick);
})

function updateScores() {
	let quizid=this.getAttribute("quizid");
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function () {
		if (this.readyState == 4) {
			if (this.status == 200) {
				console.log(this.responseText);
			} else {
				console.log("error");
			}
		}
	}
	xhttp.open("GET","/s/getUserQuizResults?quiz="+quizid+"&user="+studentid);
	xhttp.send();
}

function updateQuizList() {
	let testsLabel = document.getElementById("tests-label");
	testsLabel.style.visibility="visible";

	let quizListGroup=document.getElementById("quizListGroup");
	quizListGroup.innerHTML="";
	for (let i=0;i<quizList.length;++i) {
		let div=document.createElement("div");
		div.classList.add("list-item");
		div.setAttribute("quizid",quizList[i]['id'])
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
var studentid="";

function studentListItemClick() {
	studentid=this.getAttribute("studentid");
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
	let uri = "/s/getQuizzesStartedByStudent?userid="+studentid;
	xhttp.open("GET", uri);
	xhttp.send();
}
