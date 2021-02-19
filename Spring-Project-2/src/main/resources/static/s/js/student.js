let history = null

// Detect back arrows
window.addEventListener('DOMContentLoaded', () => {
    if(String(window.performance.getEntriesByType("navigation")[0].type) === "back_forward" && localStorage.getItem('closed') == 'normal'){
		alert('Retrieved via back arrows, but have to go back to the login page')
		//localStorage.removeItem('closed')
		location.href = '/'
	}
	else if(localStorage.getItem('closed') == 'forced')
		history = localStorage.getItem('user')

})

// The moment the Student window is loaded, perform this function.
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

	// Otherwise, create the Student View
	else{

		// Remember the retrieved id. 
		// Local storage basically prevents us from using the back arrow to access a User that was already logged out.
		localStorage.removeItem('user')

		// Change the name from Quiz Manager to "Student View"
		document.getElementsByClassName('navbar-brand')[0].textContent = 'Student View'

		// Also change the display name
		document.getElementById('main-menu-student-display').textContent = ''
		document.getElementById('main-menu-student-display').insertAdjacentHTML('afterbegin', `Welcome ${history}.<br>Please choose to either take a quiz, view a graded quiz, or log out.`)
	
		// Move the logout button on the nav bar to the right
		document.getElementsByClassName('navbar-nav')[0].style.float = 'right'

		// Hide the 3rd item in the navbar
		document.getElementsByClassName('nav-item nav-link')[2].style.display = 'none'

		// Change the name of the other 2 navbar items
		document.getElementsByClassName('nav-item nav-link')[0].textContent = 'View All Quizzes to Take'
		document.getElementsByClassName('nav-item nav-link')[1].textContent = 'View Grades of Completed Quizzes'

	}
})

// Do stuff before logging out via the log out button
document.getElementsByClassName('nav-item nav-link')[3].addEventListener('click', () => {
	localStorage.setItem('closed', 'normal')
	alert(`Logging out, see you next time ${history}`) 
})

// Do stuff before the page closes
window.addEventListener('beforeunload', () => {
	if(localStorage.getItem('closed') != null && localStorage.getItem('closed') != 'normal'){
		localStorage.setItem('closed', 'forced')
		localStorage.setItem('user', history)
	}
})

// View all quizzes that the User still needs to take
document.getElementsByClassName('nav-item nav-link')[0].addEventListener('click', () => {
	alert('Viewing all quizzes still required to be taken')
})

// View all grades of completed Quizzes that the User has taken
document.getElementsByClassName('nav-item nav-link')[1].addEventListener('click', () => {
	alert('Viewing past quiz results')
})