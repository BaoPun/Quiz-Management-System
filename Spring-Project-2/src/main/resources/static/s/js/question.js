
var numQuestions=0;
var questionNum=0;
var answerList=[]

$(function () {
	let answers=document.getElementsByClassName("answer");
	for (let i=0;i<answers.length;++i) {
		checkbox = document.createElement("input");
		checkbox.classList.add("form-check-input");
		checkbox.setAttribute("type","checkbox");
		checkbox.setAttribute("value","");
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
	if (questionNum < numQuestions-1) {
		hideQuestion(questionNum);
		showQuestion(questionNum+1);
		questionNum += 1;
		prevButton.disabled=false;
	}
	if (questionNum == numQuestions-1) {
		nextButton.disabled=true;
	}

}

function prev() {
	var nextButton=document.getElementById("next-button");
	var prevButton=document.getElementById("prev-button");
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
	
}