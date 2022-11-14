package com.chethan.qrcodescanner.screens

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ValidationScreen(curName:String?, data : String?){
    var visible by remember { mutableStateOf(false) }
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {

        Text(
            modifier = Modifier.padding(16.dp),
            text = data!!,
            style = TextStyle(
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                fontSize = 60.sp
            )
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            modifier = Modifier.fillMaxWidth(0.5f),
            onClick = {
                visible = validate(curName!!, data)
                if(visible){
                    toast(context, "Valid $curName")
                }
                else{
                    toast(context, "Invalid $curName")
                }

            }
        ) {
            Text(text = "Validate")
        }

        Button(

            modifier = Modifier.fillMaxWidth(0.5f),
            onClick = {
                visible = validate(curName!!, data)
                if(!visible){
                    toast(context, "Valid $curName Cant Share")
                }else{
                    val shareIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, "$data")
                        type = "text/plain"
                    }
                    context.startActivity(shareIntent)
                }

            }
        ) {
            Text(text = "Share")
        }


    }


}

fun validate(curName: String , data : String): Boolean {

    val length = data.length
    when (curName) {
        "BTC" -> {
            if (length in 25..34){
                if (data.startsWith("1")){
                    if(data.all{it in 'A'..'Z' || it in 'a'..'z' || it in '0'..'9'}){
                        if(data.none {it in "O" || it in "0" || it in "l" || it in "I"}){
                            return true
                        }
                    }
                }
            }
            return false
        }
        "ETH" -> {
            if(data.startsWith("0x")){
                if(data.substring(2 until length).all{ it in 'a'..'f' || it in '0'..'9'}){
                    return true
                }
            }
            return false
        }
        else -> {
            return false
        }


    }
}