"use strict";

document.addEventListener("DOMContentLoaded", () => {
	const toggleButton = document.querySelector(".navbar-toggle");
	const mobileMenu = document.querySelector(".navbar-menu-mobile");
	const toggleBody = document.querySelector(".container-all");

	let pulsado = 0;
	
	toggleButton.addEventListener("click", () => {
		toggleButton.classList.toggle("active");
		
		if(pulsado == 1) {
			pulsado = 0;
			mobileMenu.style.height  = "0";
			mobileMenu.style.display = "none";
		} else {
			pulsado = 1;
			mobileMenu.style.height  = "auto";
			mobileMenu.style.display = "block";
		}
	});
	
	toggleBody.addEventListener("click", () => {
		toggleBody.classList.toggle("active");
		if(pulsado == 1) {
			pulsado = 0;
			mobileMenu.style.height  = "0";
		  	mobileMenu.style.display = "none";
		} 
	});
});


