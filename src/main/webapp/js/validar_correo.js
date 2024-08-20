/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

//regex para validar el correo 
export const correo = (event, elemento) => {
  console.log(event);
  let emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (!emailPattern.test(elemento.value)) {
    alert("Correo electrónico no válido, por favor verifique.");
    event.preventDefault(); // no envia si no es valido el correo
  }
};


