var history="";

window.addEventListener('load', () => {

	// First, check if the id field is set.
	let getFirstDiv = document.getElementsByTagName('div')[0]	
	history = getFirstDiv.getAttribute('id')

	// Hide most items in the navbar, they're pointless
	document.getElementsByClassName('nav-item nav-link')[1].style.display = 'none'
	document.getElementsByClassName('nav-item nav-link')[2].style.display = 'none'
	
	// Reserve 1 item in the navbar for viewing grades on completed quizzes
	document.getElementsByClassName('nav-item nav-link')[0].textContent = 'View Quizzes To Be Taken'

	// Change the name of the navbar brand
	document.getElementsByClassName('navbar-brand')[0].textContent = `${history}`

})

