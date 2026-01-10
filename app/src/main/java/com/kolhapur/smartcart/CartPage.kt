package com.kolhapur.smartcart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun CartPage(navController: NavController) {

    AppScaffold(navController) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {

            // 🔹 Header
            Text(
                text = "Your Cart",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Shopping from HyperMart",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 🔹 Cart Items
            CartItem("Basmati Rice (5kg)", 420)
            CartItem("Sunflower Oil (1L)", 180)
            CartItem("Toor Dal (1kg)", 160)

            Spacer(modifier = Modifier.height(16.dp))

            // 🔹 Scan Product Card
            ScanProductCard()

            Spacer(modifier = Modifier.height(12.dp))

            // 🔹 Ask Gemini Card
            AskGeminiCard(navController)

            Spacer(modifier = Modifier.weight(1f))

            // 🔹 Checkout Button
            ProceedToCheckoutButton()
        }
    }
}

@Composable
private fun CartItem(
    name: String,
    price: Int
) {
    Card(
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column {
                Text(name, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                Text("₹$price", color = Color.Gray, fontSize = 13.sp)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Remove, contentDescription = null)
                Text("1", modifier = Modifier.padding(horizontal = 8.dp))
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
    }
}

@Composable
fun ScanProductCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column {
                Text(
                    text = "Scan a Product",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = "Add items quickly by scanning barcode",
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            }

            Button(
                onClick = { /* Fake scan action */ },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .height(60.dp)
                    .wrapContentWidth()      // 👈 KEY FIX
                    .padding(start = 0.dp)   // 👈 spacing from text column
            ) {
                Text(
                    text = "Scan",
                    maxLines = 1,
                    softWrap = false         // 👈 CRITICAL
                )
            }

        }
    }
}

@Composable
fun AskGeminiCard(navController: NavController) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = Icons.Default.AutoAwesome,
                contentDescription = "Gemini",
                tint = Color(0xFF1976D2),
                modifier = Modifier.size(28.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Ask Gemini",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = "Get smart suggestions & save money",
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            }

            TextButton(
                onClick = { navController.navigate(Routes.GEMINI) }
            ) {
                Text("Ask")
            }
        }
    }
}

@Composable
fun ProceedToCheckoutButton() {
    Button(
        onClick = { /* Fake checkout */ },
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp),
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF2E7D32)
        )
    ) {
        Text(
            text = "Proceed to Checkout",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}
