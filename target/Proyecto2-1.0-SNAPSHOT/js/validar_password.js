/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


export const password = (event , elemento) => {
  console.log(event);
  let password = /^[a-zA-Zá-úÁ_U\s]{0,10}+$/;
  if (password.test(event.key)){
    console.log("valido")
    
  }else {
    event.preventDefault()
  }

}