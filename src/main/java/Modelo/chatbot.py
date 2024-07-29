from flask import Flask, request, jsonify
from flask_cors import CORS
import mysql.connector
from mysql.connector import Error
from difflib import get_close_matches
from datetime import datetime
import logging

app = Flask(__name__)
CORS(app)  # Permitir CORS para todas las rutas

# Configurar el registro de logs
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')

# Configurar la conexión a la base de datos
def get_db_connection():
    try:
        connection = mysql.connector.connect(
            host="localhost",
            user="root",
            password="",
            database="db_chatbotbm"
        )
        return connection
    except Error as e:
        logging.error(f"Error al conectar con la base de datos: {e}")
        return None

# Cargar la base de conocimientos desde la base de datos
def load_knowledge_base():
    knowledge_base = []
    connection = get_db_connection()
    if connection is None:
        return knowledge_base

    try:
        cursor = connection.cursor(dictionary=True)
        cursor.execute("SELECT pregunta AS question, respuesta AS answer FROM tb_entrenamiento")
        knowledge_base = cursor.fetchall()
    except Error as e:
        logging.error(f"Error al cargar la base de conocimientos: {e}")
    finally:
        cursor.close()
        connection.close()
    return knowledge_base

def find_best_match(user_input, questions):
    matches = get_close_matches(user_input, questions, n=1, cutoff=0.6)
    return matches[0] if matches else None

def get_answer_for_question(question, knowledge_base):
    for q in knowledge_base:
        if "question" in q and q["question"].lower() == question.lower():
            return q["answer"]

def save_interaction_to_db(fecha, hora, id_usuario, pregunta, respuesta):
    connection = get_db_connection()
    if connection is None:
        logging.error("No se pudo guardar la interacción porque no hay conexión a la base de datos.")
        return
    
    try:
        cursor = connection.cursor()
        query = """
        INSERT INTO tb_historial (fecha, hora, id_usuario, pregunta, respuesta)
        VALUES (%s, %s, %s, %s, %s)
        """
        cursor.execute(query, (fecha, hora, id_usuario, pregunta, respuesta))
        connection.commit()
        logging.info("Interacción guardada en el historial.")
    except Error as e:
        logging.error(f"Error al guardar la interacción en el historial: {e}")
    finally:
        cursor.close()
        connection.close()

@app.route('/chatbot', methods=['POST'])
def chatbot():
    data = request.json
    user_input = data.get('question', '')
    user_id = data.get('userId')  # Asegúrate de que se pase el ID del usuario en la solicitud

    if not user_input or not user_id:
        logging.error("La solicitud no contiene 'question' o 'userId'.")
        return jsonify({'error': "Solicitud inválida"}), 400

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

@app.errorhandler(500)
def internal_error(error):
    logging.error(f"Error interno del servidor: {error}")
    return jsonify({'error': 'Error interno del servidor'}), 500

@app.errorhandler(404)
def not_found_error(error):
    logging.error(f"Recurso no encontrado: {error}")
    return jsonify({'error': 'Recurso no encontrado'}), 404

if __name__ == '__main__':
    app.run(port=5000)
