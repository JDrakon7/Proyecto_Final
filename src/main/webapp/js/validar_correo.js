/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

export const correo = (event , elemento) =>{
  console.log(event);
  let email = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (email.test(elemento.value)) {
    console.log("correo valido")
  }else{
    correo.preventDefault()
  }
};