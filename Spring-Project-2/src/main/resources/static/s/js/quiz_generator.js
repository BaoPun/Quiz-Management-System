/**
 * 
 */

var questionNum=0;
var questionNumAnswers={}

function addAnswerBox(thisQuestionNum,answerNum) {
	let questionDiv=document.getElementById("question-number-"+thisQuestionNum);
	let answerDiv=document.createElement("div");
	answerDiv.classList.add("answerDiv");
	questionDiv.appendChild(answerDiv);

	let checkBoxDiv = document.createElement("div");
	checkBoxDiv.classList.add("form-check");
	checkBoxDiv.classList.add("form-switch");
	let checkBox=document.createElement("input");
	checkBox.setAttribute("type","checkbox");
	checkBox.setAttribute("id","checkbox-"+thisQuestionNum+"-"+answerNum);
	checkBox.classList.add("form-check-input");
	checkBoxDiv.appendChild(checkBox);
	answerDiv.appendChild(checkBoxDiv);

	let textBox=document.createElement("input");
	textBox.classList.add("form-control");
	textBox.classList.add("text-box");
	textBox.setAttribute("placeholder","answer");
	textBox.setAttribute("id","text-box-"+thisQuestionNum+"-"+answerNum);
	textBox.setAttribute("type","text");
	textBox.onchange=textBoxChange;
	answerDiv.appendChild(textBox);

	questionNumAnswers[thisQuestionNum]++;
}	

function textBoxChange() {
	if (this.classList && this.classList.contains("text-box")) {
		let info=this.getAttribute("id").split("-");
		let thisQuestionNum=parseInt(info[2]);
		let answerNum=parseInt(info[3]);
		if (answerNum+1 == questionNumAnswers[thisQuestionNum]) {
			addAnswerBox(thisQuestionNum,answerNum+1);
		}
	}
}

function submit() {
	let submission={
		"name":document.getElementById("quiz-name-input").value,
		"answerCorrectness":[],
		"answers":[],
		"questions":[]};
	for (i=0;i<questionNum;++i) {
		let answerList=[];
		var isCorrectList=[]
		for (j=0;j<questionNumAnswers[i];++j) {
			let textbox=document.getElementById("text-box-"+i+"-"+j);
			let checkbox=document.getElementById("checkbox-"+i+"-"+j);
			if (textbox.value != "") {
				answerList.push(textbox.value);
				isCorrectList.push(checkbox.checked);
			}
		}
		submission['answers'].push(answerList);
		submission['answerCorrectness'].push(isCorrectList);
		let questionBox=document.getElementById('question-box-'+i);
		submission['questions'].push(questionBox.value);
	}
	
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function () {
		if (this.readyState == 4) {
			if (this.status == 200) {
				alert("submitted");
			} else {
				alert("failure");
			}
		}
	}
	xhttp.open("POST","/s/submitNewQuiz");
	xhttp.setRequestHeader("Content-Type","application/json");
	xhttp.send(JSON.stringify(submission));
}

function addQuestion() {
	let questionDiv=document.getElementById("questions-block");
	let thisQuestion=document.createElement("div");
	thisQuestion.setAttribute("id","question-number-"+questionNum);
	thisQuestion.classList.add("question-block");
	questionDiv.appendChild(thisQuestion);
	
	let questionBox=document.createElement("input");
	questionBox.classList.add("form-control");
	questionBox.classList.add("question-box");
	questionBox.setAttribute("placeholder","Question");
	questionBox.setAttribute("id","question-box-"+questionNum);
	questionBox.setAttribute("type","text");
	thisQuestion.appendChild(questionBox);
	
	questionNumAnswers[questionNum]=0;
	addAnswerBox(questionNum,0);
	
	++questionNum;
}
