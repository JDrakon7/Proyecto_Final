from flask import Flask, request, jsonify
from flask_cors import CORS
import mysql.connector
from mysql.connector import Error
from difflib import get_close_matches
from datetime import datetime

app = Flask(__name__)
CORS(app)  # Permitir CORS para todas las rutas

# Configurar la conexión a la base de datos
def get_db_connection():
    return mysql.connector.connect(
        host="localhost",
        user="root",
        password="",
        database="db_chatbotbm"
    )

# Cargar la base de conocimientos desde la base de datos
def load_knowledge_base():
    knowledge_base = []
    try:
        connection = get_db_connection()
        cursor = connection.cursor(dictionary=True)
        cursor.execute("SELECT pregunta AS question, respuesta AS answer FROM tb_entrenamiento")
        knowledge_base = cursor.fetchall()
        cursor.close()
        connection.close()
    except mysql.connector.Error as err:
        print(f"Error: {err}")
    return knowledge_base

def find_best_match(user_input, questions):
    matches = get_close_matches(user_input, questions, n=1, cutoff=0.6)
    return matches[0] if matches else None

def get_answer_for_question(question, knowledge_base):
    for q in knowledge_base:
        if "question" in q and q["question"].lower() == question.lower():
            return q["answer"]

def save_interaction_to_db(fecha, hora, id_usuario, pregunta, respuesta):
    try:
        connection = get_db_connection()
        cursor = connection.cursor()
        query = """
        INSERT INTO tb_historial (fecha, hora, id_usuario, pregunta, respuesta)
        VALUES (%s, %s, %s, %s, %s)
        """
        cursor.execute(query, (fecha, hora, id_usuario, pregunta, respuesta))
        connection.commit()
        cursor.close()
        connection.close()
        print("Interacción guardada en el historial.")
    except Error as e:
        print(f"Error al conectar con la base de datos: {e}")

@app.route('/chatbot', methods=['POST'])
def chatbot():
    data = request.json
    user_input = data.get('question', '')
    user_id = data.get('userId')  # Asegúrate de que se pase el ID del usuario en la solicitud

    knowledge_base = load_knowledge_base()
    best_match = find_best_match(user_input.lower(), [q["question"].lower() for q in knowledge_base if "question" in q])
    
    if best_match:
        answer = get_answer_for_question(best_match, knowledge_base)
    else:
        answer = "Lo lamento en este momento no puedo responder a tu pregunta"

    # Guardar la interacción en la base de datos
    fecha = datetime.now().date().isoformat()
    hora = datetime.now().time().isoformat()
    pregunta = user_input
    respuesta = answer
    
    save_interaction_to_db(fecha, hora, user_id, pregunta, respuesta)

    return jsonify({'answer': answer})

if __name__ == '__main__':
    app.run(port=5000)
