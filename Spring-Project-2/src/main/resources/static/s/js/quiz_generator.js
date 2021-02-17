/**
 * 
 */

var questionNum=0;
var questionNumAnswers={}


function textBoxChange() {
	if (this.classList && this.classList.contains("text-box")) {
		let info=this.getAttribute("id").split("-");
		let thisQuestionNum=parseInt(info[2]);
		let answerNum=parseInt(info[3]);
		if (answerNum+1 == questionNumAnswers[thisQuestionNum]) {
			let questionDiv=document.getElementById("question-number-"+thisQuestionNum);
			let textBox=document.createElement("input");
			textBox.classList.add("form-control");
			textBox.classList.add("text-box");
			textBox.setAttribute("placeholder","answer");
			textBox.setAttribute("id","text-box-"+thisQuestionNum+"-"+(1+answerNum));
			textBox.setAttribute("type","text");
			textBox.onchange=textBoxChange;
			questionDiv.appendChild(textBox);
			questionNumAnswers[thisQuestionNum]++;
		}
	}
}

function submit() {
	let submission={
		"name":document.getElementById("quiz-name-input").value,
		"answers":[],
		"questions":[]};
	for (i=0;i<questionNum;++i) {
		let answerList=[];
		for (j=0;j<questionNumAnswers[i];++j) {
			let textbox=document.getElementById("text-box-"+i+"-"+j);
			if (textbox.value != "") {
				answerList.push(textbox.value)
			}
		}
		submission['answers'].push(answerList);
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
	
	let questionBox=document.createElement("input");
	questionBox.classList.add("form-control");
	questionBox.classList.add("question-box");
	questionBox.setAttribute("placeholder","Question");
	questionBox.setAttribute("id","question-box-"+questionNum);
	questionBox.setAttribute("type","text");
	thisQuestion.appendChild(questionBox);
	
	let textBox=document.createElement("input");
	textBox.classList.add("form-control");
	textBox.classList.add("text-box");
	textBox.setAttribute("id","text-box-"+questionNum+"-0");
	textBox.setAttribute("type","text");
	textBox.setAttribute("placeholder","Answer");
	textBox.onchange=textBoxChange;
	thisQuestion.appendChild(textBox);
	questionDiv.appendChild(thisQuestion);
	questionNumAnswers[questionNum]=1;
	++questionNum;
}
