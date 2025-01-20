# Paredes Gámez Juan Diego 
# 31357791 - Noviembre

import flet as ft

#funcion para las combinaciones posibles
def generar_combinaciones(n):
    combinaciones = []
    def generar(secuencia):
        if len(secuencia) == n:
            combinaciones.append(secuencia[:])
            return
        generar(secuencia + [0])
        generar(secuencia + [1])
    generar([])
    return combinaciones

#Funcion lectura Archivo
def leer_archivo_entrada(nombre_archivo):
    with open(nombre_archivo, 'r') as archivo:
        return archivo.read().strip()

#Funcion escritura Archivo
def escribir_archivo_salida(nombre_archivo, movimientos, secuencia):
    with open(nombre_archivo, 'w') as archivo:
        archivo.write(f"{movimientos}\n{' '.join(map(str, secuencia))}\n")

#Algoritmo Principal
def encontrar_minima_secuencia(Cifra):
    n = len(Cifra)
    combinaciones = generar_combinaciones(n)
    min_movimientos = n + 1
    mejor_secuencia = None
    for secuencia in combinaciones:
        posicion = 0
        movimientos = 0
        for paso in secuencia:
            if Cifra[posicion] == '0':
                if movimientos < min_movimientos:
                    min_movimientos = movimientos
                    mejor_secuencia = secuencia[:movimientos]
                break
            pasos = int(Cifra[posicion])
            if paso == 0:
                posicion = (posicion + pasos) % n
            else:
                posicion = (posicion - pasos) % n
            movimientos += 1
    return min_movimientos, mejor_secuencia

#Llamado de funciones
def resolver_caja_fuerte(nombre_entrada, nombre_salida):
    Cifra = leer_archivo_entrada(nombre_entrada)
    if not Cifra:  # Verifica si el archivo está vacío o no tiene contenido
        return "", "Archivo de entrada vacío o no válido."
    
    min_movimientos, mejor_secuencia = encontrar_minima_secuencia(Cifra)
    escribir_archivo_salida(nombre_salida, min_movimientos, mejor_secuencia)
    
    # Devolver contenido original y contenido procesado
    contenido_procesado = f"{min_movimientos}\n{' '.join(map(str, mejor_secuencia))}"
    return Cifra, contenido_procesado

# Interfaz en Flet
def main(page: ft.Page):
    page.title = "Caja Fuerte"
    page.padding = 20
    page.window_width = 300
    page.window_height = 500
    
    # Elementos de la interfaz
    imagen = ft.Image(src="UNET0.png", width=300, height=200,fit = ft.ImageFit.CONTAIN)  
    campo_texto_original = ft.TextField(label="Contenido Original", multiline=True, width=300)
    campo_texto_procesado = ft.TextField(label="Contenido Procesado", multiline=True, width=300)
    boton_cargar = ft.ElevatedButton(text="Cargar y Procesar", on_click=lambda e: procesar_archivos())

    # Función para cargar y procesar archivos
    def procesar_archivos():
        # Leer y procesar archivos
        contenido_original, contenido_procesado = resolver_caja_fuerte("fuerte.txt", "salidafuerte.txt")
        
        # Mostrar en los campos de texto
        campo_texto_original.value = contenido_original
        campo_texto_procesado.value = contenido_procesado
        
        # Actualizar los cambios en la página
        page.update()

    # Agregar elementos a la página
    page.add(
        imagen,
        campo_texto_original,
        campo_texto_procesado,
        boton_cargar
    )

ft.app(target=main)
