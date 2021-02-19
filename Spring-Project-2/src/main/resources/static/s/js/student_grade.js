var quizList=[]

$(function () {
	$('.student-list-item').on('click',studentListItemClick);
})

function getListOfGrades(){
    let quizid=this.getAttribute("quizid");
    let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function () {
		if (this.readyState == 4) {
			if (this.status == 200) {
				progressData=JSON.parse(this.responseText)
				updateProgress();
			} else {
				console.log("error");
			}
		}
	}
	xhttp.open("GET","/s/getUserQuizResults?quiz="+quizid+"&user="+id);
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


// Below here is to keep track of user accounts!

let history = null

function studentListItemClick() {
	studentid=this.getAttribute("id");
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

window.addEventListener('load', () => {

	// First, check if the id field is set.
	let getFirstDiv = document.getElementsByTagName('div')[0]	
	history = getFirstDiv.getAttribute('id')
	console.log(history)

	// If not, redirect back to the login page.
	if(!getFirstDiv.hasAttribute('id')){
		alert('Error, you are not logged in.  You will now be redirected to the login page.')
		location.href = '/'
	}
	else{

		// Hide most items in the navbar, they're pointless
		document.getElementsByClassName('nav-item nav-link')[1].style.display = 'none'
		document.getElementsByClassName('nav-item nav-link')[2].style.display = 'none'
		
		// Reserve 1 item in the navbar for viewing grades on completed quizzes
		document.getElementsByClassName('nav-item nav-link')[0].textContent = 'View Quizzes To Be Taken'
	}
})

// Detect navigation arrows if we're already logged out but trying to get in via outside means.
window.addEventListener('DOMContentLoaded', () => {
    if(String(window.performance.getEntriesByType("navigation")[0].type) === "back_forward" && localStorage.getItem('closed') == 'normal'){
		alert('Error, you are not logged in.  You will now be redirected to the login page.')
		location.href = '/'
	}
	else if(localStorage.getItem('type') == 'TEACHER'){
		alert('Error, you are a teacher.  You will now be redirected to the Teacher page.')
		location.href = '/s/teacher'
	} 
	else if(localStorage.getItem('closed') == 'forced')
		history = localStorage.getItem('user')
})

// Do stuff before logging out via the log out button
document.getElementsByClassName('nav-item nav-link')[3].addEventListener('click', () => {
	localStorage.setItem('closed', 'normal')
	localStorage.removeItem('type')
	alert(`Logging out, see you next time ${history}`)
})

// Do stuff before the page closes
window.addEventListener('beforeunload', () => {
	if(localStorage.getItem('closed') != null && localStorage.getItem('closed') != 'normal'){
		localStorage.setItem('closed', 'forced')
		localStorage.setItem('user', history)
	}
})

// Go back to the home page, don't affect the localStorage stuff
document.getElementsByClassName('nav-item nav-link')[0].addEventListener('click', () => {
	alert('Going back to the home page to view quizzes!')
	location.href = '/s/student'
})
