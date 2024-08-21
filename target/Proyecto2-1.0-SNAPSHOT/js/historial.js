/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

import { URL } from './config.js';


document.addEventListener("DOMContentLoaded", function() {
    const chatContainer = document.getElementById("chat-container");
    const userId = sessionStorage.getItem('userId');

    const createChatElement = (content, className) => {
        const chatDiv = document.createElement("div");
        chatDiv.classList.add("chat", className);
        chatDiv.innerHTML = content;
        return chatDiv;
    }

    const loadChatHistory = () => {
        fetch('historial', {  
            method: "GET",
            headers: {
                "Content-Type": "application/json ; charset=UTF-8"
            }
        })
        .then(response => {
            if (!response.ok) {
                throw new Error("Ha ocurrido un error, inténtelo nuevamente");
            }
            return response.json();
        })
        .then(data => {
            data.forEach(chat => {
                const outgoingHtml = `<div class="chat-content">
                                        <div class="chat-details">
                                            <p>${chat.pregunta}</p>
                                        </div>
                                      </div>`;
                const outgoingChatDiv = createChatElement(outgoingHtml, "outgoing");
                chatContainer.appendChild(outgoingChatDiv);

                const incomingHtml = `<div class="chat-content">
                                        <div class="chat-details">
                                            <p>${chat.respuesta}</p>
                                        </div>
                                      </div>`;
                const incomingChatDiv = createChatElement(incomingHtml, "incoming");
                chatContainer.appendChild(incomingChatDiv);
            });
            chatContainer.scrollTo(0, chatContainer.scrollHeight);
        })
        .catch(error => {
            console.error("Error loading chat history:", error);
        });
    }

    loadChatHistory();
});





