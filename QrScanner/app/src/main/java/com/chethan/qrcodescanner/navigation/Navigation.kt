package com.chethan.qrcodescanner.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.chethan.qrcodescanner.Screens
import com.chethan.qrcodescanner.screens.HomeScreen
import com.chethan.qrcodescanner.screens.ScannerScreen
import com.chethan.qrcodescanner.screens.ValidationScreen


@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.HomeScreen.route){
        composable(route = Screens.HomeScreen.route){
            HomeScreen(navController = navController)
        }
        composable(
            route = Screens.ScannerScreen.route+"/{curName}",
            arguments = listOf(
                navArgument("curName"){
                    type = NavType.StringType
                    defaultValue = "CURNAME NOT SELECTED"
                }
            )
        ){
            it.arguments!!.getString("curName")
                ?.let { it1 -> ScannerScreen(navController = navController, curName = it1) }
        }
        composable(
            route = Screens.ValidateScreen.route + "/{curName}/{data}",
            arguments = listOf(
                navArgument("curName"){
                    type = NavType.StringType
                    defaultValue = "CURNAME NOT SELECTED"
                },
                navArgument("data"){
                    type = NavType.StringType
                    defaultValue = "NO CODE SCANNED"
                    nullable = true
                }
            )
        ){
            ValidationScreen(curName = it.arguments!!.getString("curName"),data = it.arguments!!.getString("data"))
        }
    }
}