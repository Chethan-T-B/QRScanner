package com.chethan.qrcodescanner.screens


import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.chethan.qrcodescanner.Screens

@Composable
fun HomeScreen(navController: NavController){
    val mcontext = LocalContext.current
    val btn1 by remember{
        mutableStateOf("BTC")
    }
    val btn2 by remember{
        mutableStateOf("ETH")
    }
    Column(
        modifier = Modifier.fillMaxSize()
            .background(Color(0xFF080E2C)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Button(
            modifier = Modifier.fillMaxWidth(0.5f),
            onClick = {
                navigate(navController , btn1)
                toast(mcontext , "$btn1 is selected")
            }
        ) {
            Text(
                text = btn1,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 60.sp
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier.fillMaxWidth(0.5f),
            onClick = {
                navigate(navController , btn2)
                toast(mcontext , "$btn2 is selected")
            }
        ) {
            Text(
                text = btn2,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 60.sp
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            color = Color.White,
            text = "Click above buttons to start scanning",
            fontSize = 20.sp
        )
    }
}

fun navigate(navController:NavController ,curName :String) =
    navController.navigate(Screens.ScannerScreen.route+"/$curName")

fun toast(context: Context, str : String){
    Toast.makeText(context,str,Toast.LENGTH_SHORT).show()
}