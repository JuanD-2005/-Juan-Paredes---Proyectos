# Paredes Gámez Juan Diego 
# 31357791 - Noviembre

import flet as ft

# Función para contar prefijos
def contar_prefijos(palabras, prefijos):
    # Crear un diccionario vacío
    contador = {}

    # Recorrer cada prefijo y establecer su conteo inicial en 0
    for prefijo in prefijos:
        contador[prefijo] = 0

    # Para cada palabra, verificar si coincide con cada prefijo
    for palabra in palabras:
        for prefijo in prefijos:
            if palabra.startswith(prefijo):
                contador[prefijo] += 1
    
    # Generar la lista de conteos en el mismo orden que los prefijos
    resultado = []
    for prefijo in prefijos:
        resultado.append(str(contador[prefijo]))

    return resultado

# Función para leer el archivo de entrada
def leer_archivo_entrada(nombre_archivo):
    with open(nombre_archivo, 'r') as archivo:
        return archivo.readlines()

# Función para procesar el archivo
def procesar_archivo(nombre_archivo):
    lineas = leer_archivo_entrada(nombre_archivo)
    
    # Procesar el archivo
    N = int(lineas[0].strip())  # Número de palabras en el diccionario
    palabras = [lineas[i].strip() for i in range(1, N + 1)]  # Lista de palabras

    M = int(lineas[N + 1].strip())  # Número de prefijos
    prefijos = [lineas[i].strip() for i in range(N + 2, N + 2 + M)]  # Lista de prefijos

    # Llamada a la función y obtención del resultado
    resultado = contar_prefijos(palabras, prefijos)

    return " ".join(resultado)

# Interfaz en Flet
def main(page: ft.Page):
    page.title = "Contar Prefijos"
    page.window_width = 300
    page.window_height = 900
    page.padding = 20

    # Elementos de la interfaz
    imagen = ft.Image(src="UNET0.png", width=300, height=200,fit = ft.ImageFit.CONTAIN)  
    campo_texto_entrada = ft.TextField(label="Contenido del archivo", multiline=True, width=300)
    campo_texto_resultado = ft.TextField(label="Resultado Procesado", multiline=True, width=300)
    boton_cargar = ft.ElevatedButton(text="Cargar y Procesar", on_click=lambda e: procesar_archivos())

    # Función para cargar y procesar archivos
    def procesar_archivos():
        # Leer y procesar archivo
        contenido_resultado = procesar_archivo("palabras.txt")

        # Mostrar el resultado en el campo de texto
        campo_texto_resultado.value = contenido_resultado
        
        # Mostrar contenido original en el campo de texto
        with open("palabras.txt", "r") as file:
            contenido_entrada = file.read()
        campo_texto_entrada.value = contenido_entrada
        
        # Actualizar la página
        page.update()

    # Agregar elementos a la página
    page.add(
        imagen,
        campo_texto_entrada,
        campo_texto_resultado,
        boton_cargar
    )

ft.app(target=main)
