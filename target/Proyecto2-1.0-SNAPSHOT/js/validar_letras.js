/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


export const letras = (event, elemento) => {
  console.log(event);
  let letras = /^[a-zA-Zá-úÁ_U\s]+$/;
  if (letras.test(event.key)) {
      console.log("sí")      
  } else {
      event.preventDefault()
  }
};