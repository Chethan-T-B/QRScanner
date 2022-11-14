package com.chethan.qrcodescanner

sealed class Screens(val route : String) {
    object HomeScreen : Screens("home_screen")
    object ScannerScreen : Screens("scanner_screen")
    object ValidateScreen : Screens("validate_screen")
}