package com.example.interesapp.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
//espacer que utilizaremos para separar horizontalmente nuestros componentes //el parametro size que es de tipo DP, tiene un valor por defecto de 5 dp fun
 fun SpaceH(size: Dp = 5.dp) {
    Spacer (modifier = Modifier.height(size))
}

@Composable
//espacer que utilizaremos para separar horizontalmente nuestros componentes
//el parametro size que es de tipo DP, tiene un valor por defecto de 5 dp
fun SpaceW(size: Dp = 5.dp) {

    Spacer(modifier = Modifier.width(size))
}




//con esta funcion crearemos un campo de texto de la libreria de material Design
// value: representa el valor al cual se asocia el contenido ingresado
//onValueChange, es de tipo Unit ya que a el se le asignara una accion o funcion
// label, etiqueta del campo de texto
@OptIn (ExperimentalMaterial3Api::class)
@Composable
fun MainTextField(value: String, onValueChange: (String) -> Unit, label: String) {
//Campo de texto de material Design el cual es transparente,
// tiene menor enfasis que un campo relleno
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {Text(text = label) },
//esta propiedad nos permite restringir el tipo de caracteres que se pueden ingresar
//al mismo tiempo se indica que tipo de teclado puede mostrar, en este caso un teclado numerico keyboardOptions = KeyboardOptions (keyboardType = KeyboardType.Number),
        modifier= Modifier
                .fillMaxWidth()
//el pading representa el espacio entre los bordes del componente,
// para que no ocupe toda la pantalla
            .padding(horizontal = 30.dp)
    )


}

@Composable
//funcion reutilizable para crear un Boton
//el parametro color tiene por defecto Material Theme para poder utilizar los colores del esquema
fun MainButton(text: String,
               color: Color = MaterialTheme.colorScheme.primary,
               onClick: () -> Unit)
{

//al igual que el OutlinedTextField es un boton de material design sin relleno,
// el cual resta enfasis al mismo
    OutlinedButton(
//la clase ButtonDefaults contiene valores por defecto para los 5 tipos de boton
    onClick= onClick, colors = ButtonDefaults.outlinedButtonColors (
            contentColor = color,
            containerColor = Color.Transparent
    ),
    modifier = Modifier
        .fillMaxWidth()
        .padding (horizontal = 30.dp)
    ) {
    Text(text = text)
    }
}



//funcion con la que crearemos alertas de dialogo reutilizable
@Composable
fun Alert(
    title: String, //titulo del mensaje de alerta
    message: String, //mensaje a mostrar en el dialogo
    confirmText: String, //texto para el boton de confirmacion
    onConfirmClick: () -> Unit, //accion al confirmar o aceptar el dialogo
    onDismissClick: () -> Unit //accion al ignorar el dialogo
) {

AlertDialog(
//funcion que se ejecuta cuando el usuario trata de ignorar la alerta //haciendo click fuera de ella
        onDismissRequest = onDismissClick,
        title = { Text(text = title) },
        text = { Text(text = message) },
//componente que representa la confirmacion sobre el dialogo mostrado
        confirmButton = {
            Button(onClick = { onConfirmClick()}) {
                Text(text = confirmText)
            }
        }
    )
}




