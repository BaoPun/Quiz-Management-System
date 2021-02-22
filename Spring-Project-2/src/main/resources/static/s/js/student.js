var user="";

// The moment the Student window is loaded, perform this function.
window.addEventListener('load', () => {

	// First, check if the id field is set.
	let getFirstDiv = document.getElementsByTagName('div')[0]	
	user = getFirstDiv.getAttribute('id')
	
	// Change the name from Quiz Manager to "Student View"
	document.getElementsByClassName('navbar-brand')[0].textContent = `${user}`

	// Also change the display name
	document.getElementById('main-menu-student-display').textContent = ''
	document.getElementById('main-menu-student-display').insertAdjacentHTML('afterbegin', `Welcome ${user}.`)

	// Move the logout button on the nav bar to the right
	document.getElementsByClassName('navbar-nav')[0].style.float = 'right'

	// Hide most items in the navbar, they're pointless
	document.getElementsByClassName('nav-item nav-link')[1].style.display = 'none'
	document.getElementsByClassName('nav-item nav-link')[2].style.display = 'none'
	
	// Reserve 1 item in the navbar for viewing grades on completed quizzes
	document.getElementsByClassName('nav-item nav-link')[0].textContent = 'View Grades of Completed Quizzes'
})


// View all quiz results that were already taken
document.getElementsByClassName('nav-item nav-link')[0].addEventListener('click', () => {
	location.href = '/s/studentGrades'
})
