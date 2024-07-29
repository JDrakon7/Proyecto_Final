/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


export const numeros = (event , elemento) =>{
  console.log(event);
  let numeros = /^\d{0,10}$/; 
  let newValue = elemento.value + event.key;

  if (!numeros.test(newValue)){
    event.preventDefault()
  }
};