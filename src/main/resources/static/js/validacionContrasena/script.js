function validadContrasena(){
	var validacion=true;
	var pass1=document.getElementById("comprobacion1").value;
	var pass2=document.getElementById("comprobacion2").value;
	
	if(pass1!==pass2){
		document.getElementById("validacion").innerHTML="Las contraseñas no coiciden";
		validacion=false;
	}
	return validacion;
	
}