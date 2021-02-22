
var numQuestions=0;
var questionNum=0;
var answerList=[]

function checkboxClick() {
	console.log(this.id);
}

$(function () {
	let answers=document.getElementsByClassName("answer");
	for (let i=0;i<answers.length;++i) {
		checkbox = document.createElement("input");
		checkbox.classList.add("form-check-input");
		checkbox.setAttribute("type","checkbox");
		checkbox.setAttribute("value","");
		checkbox.onclick=checkboxClick;
		checkboxName = "checkbox-"+answers[i].id;
		checkbox.setAttribute("id",checkboxName);
		answerList.push(checkbox);
		
		label=document.createElement("label");
		label.setAttribute("for",checkboxName);
		parentDiv=answers[i].parentElement;
		label.appendChild(answers[i]);
		parentDiv.appendChild(checkbox);
		parentDiv.appendChild(label);
		
	}
	numQuestions=document.getElementsByClassName("question").length;
	
	showQuestion(0);
})

function showQuestion(num) {
	let question = document.getElementById("question-"+num);
	let block=document.getElementById("question-block");
	block.appendChild(question);
}

function hideQuestion(num) {
	let question = document.getElementById("question-"+num);
	let block=document.getElementById("hidden-block");
	block.appendChild(question);
}

function next() {
	var nextButton=document.getElementById("next-button");
	var prevButton=document.getElementById("prev-button");
	var submitButton=document.getElementById("submit-button");
	if (questionNum < numQuestions-1) {
		hideQuestion(questionNum);
		showQuestion(questionNum+1);
		questionNum += 1;
		prevButton.disabled=false;
	}
	if (questionNum == numQuestions-1) {
		nextButton.disabled=true;
		submitButton.disabled=false;
	}

}

function prev() {
	var nextButton=document.getElementById("next-button");
	var prevButton=document.getElementById("prev-button");
	var submitButton=document.getElementById("submit-button");
	if (questionNum > 0) {
		hideQuestion(questionNum);
		showQuestion(questionNum-1);
		questionNum -= 1;
		nextButton.disabled=false;
	}
	
	if (questionNum == 0) {
		prevButton.disabled=true;
	}
}

function submit() {
	let answers=[];
	for (let i=0;i<answerList.length;++i) {
		let idSegments=answerList[i].id.split("-");
		let questionNum=idSegments[2];
		let answerNum=idSegments[3];
		if (answerList[i].checked) {
			answers.push(	{"questionNum": questionNum,
							"answerNum": answerNum});
		}
	}
	xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4) {
			if (this.status == 200) {
				const urlparams = new URLSearchParams(window.location.search);
				quizid=urlparams.get("quizid")
				window.location="/s/yourGrade?quizid="+quizid;
			}
		}
	}
	xhttp.open("POST","/s/submitQuiz");
	xhttp.setRequestHeader("Content-Type","application/json");
	xhttp.send(JSON.stringify(answers));
}