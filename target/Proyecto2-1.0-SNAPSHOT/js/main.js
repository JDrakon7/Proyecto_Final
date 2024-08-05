/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

// ======================================================
// INICIAR EL SERVIDOR FLASK
// ======================================================

fetch("startFlaskServer")
        .then(response => response.text())
        .then(data => console.log(data))
        .catch(error => console.error("Error al iniciar el servidor Flask:", error));

// ======================================================
// VARIABLES Y SELECCIÓN DE ELEMENTOS DEL DOM
// ======================================================

const themeButton = document.querySelector("#theme-btn");
const themeColor = localStorage.getItem("themeColor");
const chatInput = document.querySelector("#chat-input");
const sendButton = document.querySelector("#send-btn");
const chatContainer = document.querySelector(".chat-container");
const deleteButton = document.querySelector("#delete-btn");

let profileDropdownList = document.querySelector(".profile-dropdown-list");
let btn = document.querySelector(".profile-dropdown-btn");
let classList = profileDropdownList.classList;

// ======================================================
// FUNCIONES PARA EL MENU DESPLEGABLE DEL PERFIL
// ======================================================

const toggle = () => classList.toggle("active");

window.addEventListener("click", function (e) {
    if (!btn.contains(e.target))
        classList.remove("active");
});

// ======================================================
// FUNCIONES PARA EL MANEJO DEL TEMA
// ======================================================

document.body.classList.toggle("light-mode", themeColor === "light_mode");
themeButton.innerText = document.body.classList.contains("light-mode") ? "dark_mode" : "light_mode";

themeButton.addEventListener("click", () => {
    document.body.classList.toggle("light-mode");
    localStorage.setItem("themeColor", themeButton.innerText);
    themeButton.innerText = document.body.classList.contains("light-mode") ? "dark_mode" : "light_mode";
});



// ======================================================
// FUNCIONES PARA CARGAR Y GUARDAR CHATS
// ======================================================

const loadDataFromLocalstorage = () => {
    const themeColor = localStorage.getItem("themeColor");
    document.body.classList.toggle("light-mode", themeColor === "light_mode");
    themeButton.innerText = document.body.classList.contains("light-mode") ? "dark_mode" : "light_mode";

    const defaultText = `<div class="default-text">
                            <h1>Botmaster</h1>
                            <p>Bienvenido ingrese una pregunta para continuar...</p>
                        </div>`

    chatContainer.innerHTML = localStorage.getItem("all-chats") || defaultText;
    chatContainer.scrollTo(0, chatContainer.scrollHeight);
}

// ======================================================
// FUNCIONES PARA MANEJAR EL CHAT
// ======================================================

const createChatElement = (content, className) => {
    const chatDiv = document.createElement("div");
    chatDiv.classList.add("chat", className);
    chatDiv.innerHTML = content;
    return chatDiv;
}

const getChatResponse = async (incomingChatDiv) => {
    const API_URL = "http://localhost:5000/chatbot";
    const pElement = document.createElement("p");

    const requestOptions = {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            question: userText
        })
    }

    try {
        const response = await (await fetch(API_URL, requestOptions)).json();
        pElement.textContent = response.answer;
    } catch (error) {
        pElement.classList.add("error");
        pElement.textContent = "Ocurrio un error a la hora de generar una respuesta intentelo nuevamente.";
    }

    incomingChatDiv.querySelector(".typing-animation").remove();
    incomingChatDiv.querySelector(".chat-details").appendChild(pElement);
    localStorage.setItem("all-chats", chatContainer.innerHTML);
    chatContainer.scrollTo(0, chatContainer.scrollHeight);
}

const copyResponse = (copyBtn) => {
    const reponseTextElement = copyBtn.parentElement.querySelector("p");
    navigator.clipboard.writeText(reponseTextElement.textContent);
    copyBtn.textContent = "done";
    setTimeout(() => copyBtn.textContent = "content_copy", 1000);
}

const showTypingAnimation = () => {
    const html = `<div class="chat-content">
                    <div class="chat-details">
                        <div class="typing-animation">
                            <div class="typing-dot" style="--delay: 0.2s"></div>
                            <div class="typing-dot" style="--delay: 0.3s"></div>
                            <div class="typing-dot" style="--delay: 0.4s"></div>
                        </div>
                    </div>
                    <span onclick="copyResponse(this)" class="material-symbols-rounded">content_copy</span>
                </div>`;
    const incomingChatDiv = createChatElement(html, "incoming");
    chatContainer.appendChild(incomingChatDiv);
    chatContainer.scrollTo(0, chatContainer.scrollHeight);
    getChatResponse(incomingChatDiv);
}

const handleOutgoingChat = () => {
    userText = chatInput.value.trim();
    if (!userText)
        return;

    chatInput.value = "";
    chatInput.style.height = `${initialInputHeight}px`;

    const html = `<div class="chat-content">
                    <div class="chat-details">
                        <p>${userText}</p>
                    </div>
                </div>`;

    const outgoingChatDiv = createChatElement(html, "outgoing");
    chatContainer.querySelector(".default-text")?.remove();
    chatContainer.appendChild(outgoingChatDiv);
    chatContainer.scrollTo(0, chatContainer.scrollHeight);
    setTimeout(showTypingAnimation, 500);
}

// ======================================================
// EVENTOS DE BOTONES Y ENTRADA DE TEXTO
// ======================================================

deleteButton.addEventListener("click", () => {
    //Eliminar todos los chats de la pagina principal
    if (confirm("¿Está seguro que desea eliminar el chat?")) {
        localStorage.removeItem("all-chats");
        loadDataFromLocalstorage();
    }
});

document.addEventListener('DOMContentLoaded', (event) => {
    const logoutLink = document.getElementById('logout-link');

    if (logoutLink) {
        logoutLink.addEventListener('click', (e) => {
            e.preventDefault(); // Evita la redirección inmediata

            // Eliminar el chat del localStorage
            localStorage.removeItem("all-chats");

            // Borra la sesión
            fetch('login?action=logout', {
                method: 'POST'
            }).then(() => {
                // Redirige a la página de inicio de sesión
                window.location.href = 'index.jsp';

                // Deshabilitar la función de retroceso del navegador
                window.history.pushState(null, null, window.location.href);
                window.onpopstate = function () {
                    window.history.go(1);
                };
            });
        });
    }
});




// ======================================================
//  Apartado para recibir respuestas del chatbot
// ======================================================
const initialInputHeight = chatInput.scrollHeight;

chatInput.addEventListener("input", () => {
    chatInput.style.height = `${initialInputHeight}px`;
    chatInput.style.height = `${chatInput.scrollHeight}px`;
});

chatInput.addEventListener("keydown", (e) => {
    if (e.key === "Enter" && !e.shiftKey && window.innerWidth > 800) {
        e.preventDefault();
        handleOutgoingChat();
    }
});

document.addEventListener("DOMContentLoaded", function () {
    const sendButton = document.getElementById("send-btn");
    const chatInput = document.getElementById("chat-input");
    const chatContainer = document.getElementById("chat-container");

    sendButton.addEventListener("click", function () {
        const userMessage = chatInput.value;
        if (userMessage.trim() !== "") {
            appendMessage("Tu", userMessage);
            sendMessageToBot(userMessage);
            chatInput.value = "";
        }
    });

    chatInput.addEventListener("keypress", function (event) {
        if (event.key === "Enter") {
            event.preventDefault();
            sendButton.click();
        }
    });

    function appendMessage(sender, message) {
        const messageElement = document.createElement("div");
        messageElement.classList.add("message");
        messageElement.innerHTML = `<strong>${sender}:</strong> ${message}`;
        chatContainer.appendChild(messageElement);
        chatContainer.scrollTop = chatContainer.scrollHeight;
    }

    function sendMessageToBot(message) {
        fetch("http://localhost:5000/chatbot", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({question: message})
        })
                .then(response => response.json())
                .then(data => {
                    const botMessage = data.answer;
                    appendMessage("Botmaster", botMessage);
                })
                .catch(error => {
                    console.error("Error:", error);
                    appendMessage("Botmaster", "Lo lamento en este momento no puedo responder a tu pregunta.");
                });
    }

    loadDataFromLocalstorage();
});



