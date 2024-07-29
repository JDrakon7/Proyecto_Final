from flask import Flask, request, jsonify
from flask_cors import CORS
import mysql.connector
from difflib import get_close_matches

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

@app.route('/chatbot', methods=['POST'])
def chatbot():
    data = request.json
    user_input = data.get('question', '')
    
    knowledge_base = load_knowledge_base()
    best_match = find_best_match(user_input.lower(), [q["question"].lower() for q in knowledge_base if "question" in q])
    
    if best_match:
        answer = get_answer_for_question(best_match, knowledge_base)
    else:
        answer = "Lo lamento en este momento no puedo responder a tu pregunta"
    
    return jsonify({'answer': answer})

if __name__ == '__main__':
    app.run(port=5000)
