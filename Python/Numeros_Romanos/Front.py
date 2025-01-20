import flet as ft

def Hoja (Num,cont,Key,mov):
    
    if	(Num==1): cont = 2
    elif(Num==0): cont = 1
    elif(Num==2): cont = 3
    elif(Num==3): cont = 4
    elif(Num==4): cont = 2 ; Key = 2
    elif(Num==5): cont = 2 ; mov = 1
    elif(Num==6): cont = 2 ; Key = 3 ; mov = 1
    elif(Num==7): cont = 3 ; Key = 3 ; mov = 1
    elif(Num==8): cont = 4 ; Key = 3 ; mov = 1
    elif(Num==9): cont = 2 ; Key = 1 ; mov = 2
    return cont, Key, mov

def Hoja2 (verificar,movimiento):
      if(verificar==4):movimiento = 6
      elif(verificar==3):movimiento = 4
      elif(verificar==2):movimiento = 2
      elif(verificar==1):movimiento = 0
      return movimiento

def convertir_a_romano(numero):    
    vuelta, Cont, Key, Roma, mov, cont, movimiento = 0, 0, 0, "", 0, 0, 0
    Romano = ["I", "V", "X", "L", "C", "D", "M"]

    if numero < 1:
        return "El número debe ser mayor a cero"

    if numero < 3500:
        str_Numero = str(numero)	
        contador = len(str_Numero)
        verificar = contador
        
        while verificar >= 1:
            Key = 0
            mov = 0
            movimiento = Hoja2(verificar, movimiento)
            
            Num = int(str_Numero[vuelta])
        
            cont, Key, mov = Hoja(Num, cont, Key, mov)
        
            for i in range(1, cont):
                if Key == 0:
                    Roma += Romano[movimiento + mov]
                elif Key == 1:
                    Roma += Romano[movimiento] + Romano[mov + movimiento]
                elif Key == 2:
                    Roma += Romano[mov + movimiento] + Romano[mov + movimiento + 1]
                elif Key == 3:
                    Roma += Romano[mov + movimiento] + Romano[movimiento]
                Key = 0
                mov = 0
                
            verificar -= 1
            vuelta += 1
            
        return Roma
        
    else:
        return "Número fuera de rango"
    
def procesar_archivo(event):
    if event.files:
        file_path = event.files[0].path  # Obtiene la ruta del archivo seleccionado
        try:
            with open(file_path, 'r') as file:
                contenido = file.readlines()
            
            # Lee y convierte los números
            original_text = ""
            converted_text = ""
            for linea in contenido:
                numeros = map(int, linea.split())
               
                for numero in numeros:
                    romano = convertir_a_romano(numero)
                   
                    original_text += f"{numero} "
                    converted_text += f"{numero} {romano} \n"
            
            # Muestra los contenidos en los campos de texto
            contenido_original.value = original_text.strip()
            contenido_convertido.value = converted_text.strip()
            
            # Actualiza la UI
            contenido_original.update()
            contenido_convertido.update()
        
        except Exception as e:
            print("Error al procesar el archivo:", e)

def main(page: ft.Page):
    
    #VENTANA PRINCIPAL
    page.vertical_alignment = 'center'
    page.horizontal_alignment = 'center'
    page.window_width = 400
    page.window_height = 700
    page.scroll = 'always'
    page.window_resizable = False
    page.window_maximizable = False
    
    #IMAGENES DE HOME
    logo_image = ft.Image(src=f"Logo.png", width=390, height=300,fit = ft.ImageFit.CONTAIN) 
    logo_image2 = ft.Image(src="Titulo.png", width=400, height=100,fit = ft.ImageFit.CONTAIN)
    logo_image3 = ft.Image(src="Linea.png", width=400, height=220,fit = ft.ImageFit.CONTAIN)
    logo_image4 = ft.Image(src="Linea.png", width=400, height=30,fit = ft.ImageFit.CONTAIN)
    logo_image5 = ft.Image(src="Linea.png", width=400, height=30,fit = ft.ImageFit.CONTAIN)
    
    #IMAGENES DE TEXTO
    text_image = ft.Image(src="Sabias.png", width=400, height=100,fit = ft.ImageFit.CONTAIN)
    text_image2 = ft.Image(src="que.png", width=400, height=400,fit = ft.ImageFit.CONTAIN)
    
    #IMAGENES DE CREDITOS
    creditos_image = ft.Image(src="UNET.png", width=400, height=130,fit = ft.ImageFit.CONTAIN)
    creditos_image2 = ft.Image(src="UNET0.png", width=400, height=230,fit = ft.ImageFit.CONTAIN)
    creditos_image3 = ft.Image(src="UNET2.png", width=400, height=140,fit = ft.ImageFit.CONTAIN)
    def youchange(e):
        home_content.visible = False
        text_content.visible = False
        upload_content.visible = False
        edit_content.visible = False
        account_content.visible = False

        if e.control.selected_index == 0:
            home_content.visible = True
        elif e.control.selected_index == 1:
            text_content.visible = True
        elif e.control.selected_index == 2:
            upload_content.visible = True
        elif e.control.selected_index == 3:
            edit_content.visible = True
        elif e.control.selected_index == 4:
            account_content.visible = True
        page.update()
    
    
    #BOTONES DE NAVIGACION
    myicons = ['home', 'book', 'upload', 'edit', 'account_balance_outlined']

    t = ft.Tabs(
        selected_index=0,
        animation_duration=100,
        indicator_color='white',
        divider_color='green',
        scrollable=False,
        on_change=youchange,
        tabs=[ft.Tab(tab_content=ft.Icon(name=icon, size=25, color='white')) for icon in myicons]
    )
   
    # BOTON HOME    
    home_content = ft.Container(
        ft.Column([
            logo_image2,logo_image,logo_image3,  
        ], alignment='center'),
        visible=True
    )

    #BOTON TEXTO
    text_content = ft.Container(
        ft.Column([
            text_image,text_image2,
        ], alignment='center'),
        visible=False
    )

    #BOTON SUBIR
   # Imagen de cabecera
    logo_image = ft.Image(src="Logo.png", width=400, height=150,fit = ft.ImageFit.CONTAIN)

    # Selector de archivos
    file_picker = ft.FilePicker(on_result=procesar_archivo)

    # Campos para mostrar los contenidos
    global contenido_original, contenido_convertido
    contenido_original = ft.TextField(label="Contenido Original", multiline=True, read_only=True)
    contenido_convertido = ft.TextField(label="Contenido Convertido a Romano", multiline=True, read_only=True,)

    # Contenedor principal
    upload_content = ft.Container(
        ft.Column([
            logo_image,
            ft.ElevatedButton("Seleccionar archivo", on_click=lambda e: file_picker.pick_files(),width=400,height=50),
            contenido_original,
            contenido_convertido
        ], alignment='center'),
        visible=False
    )

    #BOTON ESCRIBIR
    
    entrada_numero = ft.TextField(
        hint_text="               Escribe un número aquí",
        width=400,
        on_change=lambda e: actualizar_romano(e)  # Llama a la función cada vez que cambie el texto
    )

    # Campo de texto para mostrar el número en formato romano
    salida_romano = ft.Text(
        value="                 Número romano",
        size=20
    )

    # Función para actualizar el campo de salida
    def actualizar_romano(e):
        try:
            numero = int(e.control.value)
            romano = convertir_a_romano(numero)  # Convierte el número a romano
            salida_romano.value = romano  # Actualiza el valor en el campo de texto de salida
            salida_romano.update()  # Actualiza la interfaz
        except ValueError:
            salida_romano.value = "               Entrada no válida"
            salida_romano.update()

    # Contenedor principal
    edit_content = ft.Container(
        ft.Column([
            logo_image4,
            logo_image,
            ft.Text("        Introduce un número natural\n                  (menor a 3500)", size=20 ,italic=True),	
            logo_image5,
            entrada_numero,
            salida_romano  # Muestra el número romano aquí
        ], alignment='center'),
        visible=False
    )

    #BOTON ACCOUNT
    account_content = ft.Container(
        ft.Column([
            creditos_image,creditos_image2,creditos_image3,
        ], alignment='center'),
        visible=False
    )


    #Barra de Navegación
    listnavicon = ft.Container(
        shadow=ft.BoxShadow(blur_radius=15, spread_radius=1),
        border_radius=30,
        width=page.window_width,
        bgcolor='green',
        padding=10,
        content=t,
        margin=ft.margin.only(
            top=page.window_height / 2 + 200,  # Ajuste de margen superior para empujar hacia abajo
            left=10,
            right=10
        )
    )

    # ANADIR TODO
    page.add(
        ft.Stack([
            ft.Column(
                [home_content, text_content, upload_content, edit_content, account_content],
                alignment='center'
            ),
            listnavicon
        ])
    )

    page.overlay.append(file_picker)

ft.app(target=main)
