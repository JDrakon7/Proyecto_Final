import mysql.connector
from mysql.connector import Error
import json
from difflib import get_close_matches
from datetime import datetime

def save_knowledge_base(file_path: str, data: list[dict]):
    with open(file_path, "w") as file:
        json.dump(data, file, indent=2)

def load_knowledge_base(file_path: str) -> list[dict]:
    with open(file_path, "r") as file:
        return json.load(file)

def find_best_match(user_input: str, questions: list[str]) -> str | None:
    matches: list = get_close_matches(user_input, questions, n=1, cutoff=0.6)
    return matches[0] if matches else None

def get_answer_for_question(question: str, knowledge_base: list[dict]) -> str | None:
    for q in knowledge_base:
        if "question" in q and q["question"].lower() == question.lower():
            return q["answer"]

def save_interaction_to_db(fecha, hora, id_usuario, pregunta, respuesta, tipo_interaccion):
    try:
        connection = mysql.connector.connect(
           host='localhost',
           port=3306,
           user='root',  # usuario de la base de datos
           password="",  # contraseña de la base de datos
           database='db_chatbotbm'  # nombre de la base de datos
        )
        
         
        
        if connection.is_connected():
            cursor = connection.cursor()
            query = """
            INSERT INTO tb_historial (fecha, hora, id_usuario, pregunta, respuesta, tipo_interaccion)
            VALUES (%s, %s, %s, %s, %s, %s)
            """
            cursor.execute(query, (fecha, hora, id_usuario, pregunta, respuesta, tipo_interaccion))
            connection.commit()
            cursor.close()
            connection.close()
            print("Interacción guardada en el historial.")
        else:
            print("No se pudo conectar a la base de datos.")
    except Error as e:
        print(f"Error al conectar con la base de datos: {e}")

def chat_bot():
    knowledge_base: list[dict] = load_knowledge_base('entrenamiento.json')

    while True:
        user_input: str = input('Tu: ')

        if user_input.lower() == 'salir':
            print("Botmaster: Hasta la próxima")
            break

        best_match: str | None = find_best_match(user_input.lower(), [q["question"].lower() for q in knowledge_base if "question" in q])

        if best_match:
            answer: str = get_answer_for_question(best_match, knowledge_base)
            print(f"Botmaster: {answer}")
        else:
            print("Botmaster: No conozco esa pregunta. ¿Podrías enseñarme cómo debería responder a ella por favor?")
            new_answer: str = input("Escribe la respuesta o 'salir' para terminar la conversación: ")

            if new_answer.lower() != "salir":
                knowledge_base.append({"question": user_input.lower(), "answer": new_answer})
                save_knowledge_base('entrenamiento.json', knowledge_base)
                print('Botmaster: ¡Gracias por tu tiempo! Aprendí una nueva pregunta que me ayudará a mejorar.')

        # Guardar la interacción en la base de datos
        fecha = datetime.now().date().isoformat()
        hora = datetime.now().time().isoformat()
        id_usuario = 1  # Suponiendo un id de usuario de ejemplo
        pregunta = user_input
        respuesta = answer if best_match else new_answer
        tipo_interaccion = 'Automática' if best_match else 'Manual'
        
        save_interaction_to_db(fecha, hora, id_usuario, pregunta, respuesta, tipo_interaccion)

if __name__ == '__main__':
    chat_bot()
