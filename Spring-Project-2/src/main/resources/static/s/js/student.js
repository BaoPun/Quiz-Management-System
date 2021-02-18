// The moment the Student window is loaded, perform this function.
window.onload = function(){

	// First, check if the id field is set.
	let getFirstDiv = document.getElementsByTagName('div')[0]
	console.log(getFirstDiv)

	// If not, redirect back to the login page.
	if(!getFirstDiv.hasAttribute('id')){
		alert('Error, you are not logged in.  You will now be redirected to the login page.')
		location.href = '/'
	}
	else{
		// Change the name from Quiz Manager to "Student View"
		document.getElementsByClassName('navbar-brand')[0].textContent = 'Student View'

		// Also change the display name
		document.getElementById('main-menu-student-display').textContent = ''
		document.getElementById('main-menu-student-display').insertAdjacentHTML('afterbegin', `Welcome ${getFirstDiv.id}.<br>Please choose to either take a quiz, view a graded quiz, or log out.`)
	}
}