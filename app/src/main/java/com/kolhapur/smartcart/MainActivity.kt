package com.kolhapur.smartcart

import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kolhapur.smartcart.ui.theme.Smart_cartTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Smart_cartTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Routes.WELCOME
                ) {

                    composable(Routes.WELCOME) {
                        SmartCartHome(navController)
                    }

                    composable(Routes.HOME) {
                        HomePage(navController)
                    }

                    composable(Routes.EXPLORE) {
                        ExplorePage(navController)
                    }

                    composable(Routes.CART) {
                        CartPage(navController)
                    }


                    composable(Routes.GEMINI) {
                        GeminiAiPage(navController)
                    }
                }

            }
        }
    }
}

@Composable
fun SmartCartHome(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(60.dp))

                // 🔹 LOGO IMAGE
                Image(
                    painter = painterResource(id = R.drawable.mainlogo2),
                    contentDescription = "Smart Cart Logo",
                    modifier = Modifier
                        .size(250.dp)
                        .padding(bottom = 20.dp)
                )

                // 🔹 RUNNING / SCRIPT FONT TITLE
                Text(
                    text = "Welcome to\nSmart Cart",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold,   // 👈 BOLD

                    color = Color(0xFF1976D2),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Add, remove, buy now\nin a faster way",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.DarkGray,
                    textAlign = TextAlign.Center
                )
            }

            Button(
                onClick = {
                    navController.navigate(Routes.HOME)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1976D2)
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "Let’s Get Buying →",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}
