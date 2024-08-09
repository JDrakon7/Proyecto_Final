# Importar las bibliotecas necesarias
from datetime import datetime  # Importa datetime para manejar fechas y horas
from difflib import \
    get_close_matches  # Importa get_close_matches para encontrar coincidencias entre cadenas de texto

import pymysql  # Importa pymysql para interactuar con la base de datos MySQL
from flask import (  # Importa Flask para crear la aplicación web, request para manejar solicitudes y jsonify para enviar respuestas JSON
    Flask, jsonify, request)
from flask_cors import \
    CORS  # Importa CORS para manejar las solicitudes entre diferentes dominios

# Inicializar la aplicación Flask
app = Flask(__name__)  # Crea una instancia de la aplicación Flask
CORS(app)  # Habilita CORS para permitir solicitudes desde diferentes dominios


def get_db_connection():
    """
    Establece una conexión con la base de datos MySQL.
    """
    print(
        "Estableciendo conexión con la base de datos..."
    )  # Mensaje para indicar el inicio de la conexión
    return pymysql.connect(
        host="localhost",  # Dirección del servidor de base de datos
        user="root",  # Usuario de la base de datos
        password="",  # Contraseña de la base de datos
        database="db_chatbotbm",  # Nombre de la base de datos
        charset="utf8mb4",  # Codificación para manejar caracteres especiales
        cursorclass=pymysql.cursors.DictCursor,  # Configura el cursor para que devuelva diccionarios en lugar de tuplas
    )


def load_knowledge_base():
    """
    Carga la base de conocimientos desde la base de datos.
    """
    print(
        "Cargando base de conocimientos..."
    )  # Mensaje para indicar el inicio de la carga
    knowledge_base = []  # Inicializa una lista vacía para almacenar los datos
    try:
        connection = get_db_connection()  # Obtiene una conexión a la base de datos
        with connection.cursor() as cursor:  # Crea un cursor para ejecutar consultas
            cursor.execute(
                "SELECT pregunta AS question, respuesta AS answer FROM tb_entrenamiento"
            )  # Ejecuta una consulta para obtener preguntas y respuestas
            knowledge_base = (
                cursor.fetchall()
            )  # Obtiene todos los resultados de la consulta
            print(
                f"Base de conocimientos cargada: {knowledge_base}"
            )  # Mensaje para mostrar los datos cargados
        connection.close()  # Cierra la conexión a la base de datos
    except (
        pymysql.MySQLError
    ) as err:  # Captura errores relacionados con la base de datos
        print(f"Error al cargar la base de conocimientos: {err}")  # Mensaje de error
    return knowledge_base  # Devuelve la base de conocimientos cargada


def find_best_match(user_input, questions):
    """
    Encuentra la mejor coincidencia para la entrada del usuario en la lista de preguntas.
    """
    print(
        f"Buscando la mejor coincidencia para: {user_input}"
    )  # Mensaje para indicar la búsqueda
    matches = get_close_matches(
        user_input, questions, n=1, cutoff=0.6
    )  # Encuentra la coincidencia más cercana en la lista de preguntas
    return (
        matches[0] if matches else None
    )  # Devuelve la mejor coincidencia o None si no hay coincidencias


def get_answer_for_question(question, knowledge_base):
    """
    Obtiene la respuesta para una pregunta dada desde la base de conocimientos.
    """
    print(
        f"Buscando respuesta para la pregunta: {question}"
    )  # Mensaje para indicar la búsqueda de la respuesta
    for q in knowledge_base:  # Recorre la base de conocimientos
        if (
            "question" in q and q["question"].lower() == question.lower()
        ):  # Compara la pregunta en minúsculas
            return q["answer"]  # Devuelve la respuesta correspondiente


def save_interaction_to_db(fecha, hora, id_usuario, pregunta, respuesta):
    """
    Guarda la interacción del usuario en la base de datos.
    """
    print(
        "Guardando interacción en la base de datos..."
    )  # Mensaje para indicar el inicio de la guardado
    try:
        connection = get_db_connection()  # Obtiene una conexión a la base de datos
        with connection.cursor() as cursor:  # Crea un cursor para ejecutar consultas
            query = """
            INSERT INTO tb_historial (fecha, hora, id_usuario, pregunta, respuesta)
            VALUES (%s, %s, %s, %s, %s)
            """  # Consulta SQL para insertar una nueva interacción
            cursor.execute(
                query, (fecha, hora, id_usuario, pregunta, respuesta)
            )  # Ejecuta la consulta con los datos proporcionados
            connection.commit()  # Guarda los cambios en la base de datos
            print("Interacción guardada en el historial.")  # Mensaje de confirmación
        connection.close()  # Cierra la conexión a la base de datos
    except pymysql.MySQLError as e:  # Captura errores relacionados con la base de datos
        print(f"Error al conectar con la base de datos: {e}")  # Mensaje de error
    except Exception as e:  # Captura otros errores
        print(f"Error al guardar la interacción: {e}")  # Mensaje de error


@app.route("/chatbot", methods=["GET", "POST"])
def chatbot():
    """
    Maneja las solicitudes al endpoint /chatbot.
    """
    if request.method == "POST":  # Verifica si la solicitud es de tipo POST
        print(
            "Solicitud POST recibida en /chatbot."
        )  # Mensaje para indicar la recepción de la solicitud
        data = request.json  # Obtiene los datos JSON de la solicitud
        user_input = data.get("question", "")  # Extrae la pregunta del JSON
        user_id = data.get("userId")  # Extrae el ID del usuario del JSON

        print(
            f"Pregunta recibida: {user_input}, Usuario ID: {user_id}"
        )  # Muestra la pregunta y el ID del usuario

        knowledge_base = load_knowledge_base()  # Carga la base de conocimientos
        best_match = find_best_match(
            user_input.lower(),
            [q["question"].lower() for q in knowledge_base if "question" in q],
        )  # Encuentra la mejor coincidencia

        if best_match:  # Si se encuentra una coincidencia
            answer = get_answer_for_question(
                best_match, knowledge_base
            )  # Obtiene la respuesta correspondiente
            print(
                f"Mejor coincidencia encontrada: {best_match}, Respuesta: {answer}"
            )  # Muestra la coincidencia y la respuesta
        else:  # Si no se encuentra ninguna coincidencia
            answer = "Lo lamento en este momento no puedo responder a tu pregunta"  # Mensaje de respuesta predeterminado
            print(
                "No se encontró una coincidencia adecuada."
            )  # Mensaje indicando que no se encontró coincidencia

        # Guardar la interacción en la base de datos
        fecha = (
            datetime.now().date().isoformat()
        )  # Obtiene la fecha actual en formato ISO
        hora = (
            datetime.now().time().isoformat()
        )  # Obtiene la hora actual en formato ISO
        pregunta = user_input  # Almacena la pregunta del usuario
        respuesta = answer  # Almacena la respuesta obtenida

        print(
            f"Guardando interacción: Fecha: {fecha}, Hora: {hora}, Pregunta: {pregunta}, Respuesta: {respuesta}"
        )  # Muestra los detalles de la interacción a guardar
        save_interaction_to_db(
            fecha, hora, user_id, pregunta, respuesta
        )  # Guarda la interacción en la base de datos

        return jsonify({"answer": answer})  # Devuelve la respuesta en formato JSON

    elif request.method == "GET":  # Si la solicitud es de tipo GET
        return "Endpoint /chatbot está disponible para recibir solicitudes POST."  # Mensaje de confirmación para solicitudes GET


if __name__ == "__main__":
    app.run(
        port=5000, debug=True
    )  # Ejecuta la aplicación Flask en el puerto 5000 con modo de depuración activado
