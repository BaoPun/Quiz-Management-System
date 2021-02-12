/**
 * 
 */

$(function () {
	$('.student-list-item').on('click',studentListItemClick);
})

function studentListItemClick() {
	let student=this.getAttribute("student");
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function () {
		if (this.readyState == 4) {
			if (this.status == 200) {
				alert(this.responseText);
			} else {
				alert("error");
			}
		}
	};
	let uri = "/s/getQuizzes?student="+student;
	xhttp.open("GET", uri);
	xhttp.send();
}
