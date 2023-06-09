"use strict";

const textoCompleto = 
  "¡Hola! Soy Álvaro Quijorna Cenjor, un apasionado desarrollador de software con amplia experiencia en el campo. " +
  "Me dedico a diseñar y crear soluciones tecnológicas que hacen la vida más fácil y eficiente.\n\n" +
  "Con un profundo conocimiento en desarrollo web y móvil, estoy constantemente actualizándome con las últimas tecnologías y tendencias del sector. " +
  "Mi objetivo principal es utilizar mi experiencia para ayudar a emprendedores y empresas a mejorar su imagen y aumentar " +
  "sus ventas a través de sitios web optimizados.\n\n" +
  "Mi enfoque se centra en crear y potenciar la mejor arma de marketing que existe: una sólida presencia en línea. " +
  "Comprendo la importancia y el valor de una web bien diseñada y optimizada en la estrategia de negocio de mis clientes.\n\n" +
  
  "No desarrollo páginas web simplemente para presumir con mis amigos, ni para satisfacer mi ego como diseñador. " +
  "Mi objetivo es proporcionar resultados tangibles y medibles a mis clientes, generando tráfico de calidad y " +
  "convirtiendo visitantes en clientes satisfechos.Mi enfoque de trabajo se basa en la colaboración estrecha con mis clientes, " +
  "comprendiendo sus necesidades y metas para crear soluciones personalizadas y efectivas. Me enorgullece entregar " +
  "proyectos de alta calidad, cumpliendo con los plazos establecidos y superando las expectativas.\n\n" +
  "Si estás buscando un desarrollador de software apasionado y comprometido, estoy aquí para ayudarte. " +
  "¡Juntos podemos llevar tu negocio al siguiente nivel! No dudes en ponerse en contacto conmigo a través del enlace que dejo abajo.\n\n" +
  "¡Espero tener la oportunidad de trabajar contigo!\n Álvaro Quijorna Cenjor";
 
const velocidadEscritura = 50; // Velocidad en milisegundos entre cada letra

let indice = 0;


function escribirTexto() {
	const textoElemento = document.getElementById("texto");
	if (indice < textoCompleto.length) {
		textoElemento.innerHTML += textoCompleto.charAt(indice);
		indice++;
		setTimeout(escribirTexto, velocidadEscritura);
	} else {
		const a = document.getElementById("enlace");
		a.style.display = "block";
	}
	
}

function escribirEnlace() {
	const contenedor = document.getElementById("contenedor");
	const p = document.createElement("p");
	const a = document.createElement("a");
	const span = document.createElement("span");
	const spanAnidado = document.createElement("span");
	
	a.href = "contacto.xhtml";
	a.style.display = "none";
	a.id = "enlace";
	
	span.classList.add("fa");
	span.classList.add("fa-envelope");
	
	spanAnidado.textContent = "¿HABLAMOS?";
	span.appendChild(spanAnidado);
	
	a.appendChild(span);
	p.appendChild(a);
	contenedor.appendChild(p);

}