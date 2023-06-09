"use strict";

$(document).ready(function() {
	const seleccionArchivo = document.querySelector(".fileUploadWidget");
	const imagenPrevisualizacion = document.querySelector(".image-view");
	const campoOcultoNombreImagen = document.querySelector(".oculto");
	
	seleccionArchivo.addEventListener("change", () => {
		const archivos = seleccionArchivo.files;
		if (!archivos || !archivos.length) {
			imagenPrevisualizacion.src = "";
		    campoOcultoNombreImagen.value = ""; 
		    return;
		}
		const primerArchivo = archivos[0];
		const objectURL = URL.createObjectURL(primerArchivo);
		imagenPrevisualizacion.src = objectURL;
		campoOcultoNombreImagen.value = primerArchivo.name;
	});
	
});



