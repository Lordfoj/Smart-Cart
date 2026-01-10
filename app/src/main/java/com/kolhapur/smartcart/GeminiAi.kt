package com.kolhapur.smartcart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

data class ChatMessage(
    val text: String,
    val isUser: Boolean
)

@Composable
fun GeminiAiPage(navController: NavController) {

    val messages = remember {
        mutableStateListOf(
            ChatMessage(
                "Hi 👋 I'm Gemini AI.\nI can help you find the best store, lowest price, and smart deals for your shopping.",
                false
            ),
            ChatMessage(
                "Tell me what you want to buy today 🛒",
                false
            )
        )
    }

    var userInput by remember { mutableStateOf("") }

    AppScaffold(navController) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF5F7FA))
        ) {

            // Chat messages
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp),
                reverseLayout = false
            ) {
                items(messages) { message ->
                    ChatBubble(message)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            // Input area
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                TextField(
                    value = userInput,
                    onValueChange = { userInput = it },
                    placeholder = { Text("Ask Gemini about shopping...") },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.width(8.dp))

                IconButton(
                    onClick = {
                        if (userInput.isNotBlank()) {
                            // Add user message
                            messages.add(ChatMessage(userInput, true))

                            // Fake Gemini reply
                            messages.add(
                                ChatMessage(
                                    generateFakeGeminiReply(userInput),
                                    false
                                )
                            )

                            userInput = ""
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = "Send",
                        tint = Color(0xFF1976D2)
                    )
                }
            }
        }
    }
}

@Composable
fun ChatBubble(message: ChatMessage) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (message.isUser) Arrangement.End else Arrangement.Start
    ) {

        Box(
            modifier = Modifier
                .widthIn(max = 280.dp)
                .background(
                    color = if (message.isUser) Color(0xFF1976D2) else Color.White,
                    shape = RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp,
                        bottomStart = if (message.isUser) 16.dp else 0.dp,
                        bottomEnd = if (message.isUser) 0.dp else 16.dp
                    )
                )
                .padding(12.dp)
        ) {
            Text(
                text = message.text,
                color = if (message.isUser) Color.White else Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

fun generateFakeGeminiReply(userMessage: String): String {
    return when {

        userMessage.contains("analyze", true) ||
                userMessage.contains("cart", true) ->

            "🛒 **Cart Analysis Complete**\n\n" +
                    "Items in your cart:\n" +
                    "• Rice (5 kg)\n" +
                    "• Cooking Oil (1 L)\n" +
                    "• Onions\n" +
                    "• Tomatoes\n" +
                    "• Paneer (200 g)\n\n" +
                    "💰 Estimated total: ₹742\n\n" +
                    "Would you like recipe suggestions or cost optimization?"

        userMessage.contains("cook", true) ||
                userMessage.contains("recipe", true) ->

            "🍳 **Recipes You Can Make**\n\n" +
                    "1️⃣ Paneer Bhurji\n" +
                    "2️⃣ Veg Pulao\n" +
                    "3️⃣ Simple Dal Rice\n\n" +
                    "These recipes require no extra ingredients."

        userMessage.contains("save", true) ||
                userMessage.contains("cheap", true) ||
                userMessage.contains("money", true) ->

            "💡 **Smart Savings Tip**\n\n" +
                    "• Paneer is ₹38 cheaper at **Vishal Mart**\n" +
                    "• Rice has a ₹10 discount at **D-Mart**\n\n" +
                    "🎁 You are eligible for a **₹20 scratch card cashback** on checkout."

        userMessage.contains("best store", true) ||
                userMessage.contains("where", true) ->

            "🏬 **Best Store Recommendation**\n\n" +
                    "**D-Mart** offers the lowest total for your cart today.\n" +
                    "Estimated savings: ₹48 compared to nearby stores."

        else ->
            "🤖 I can analyze your cart, suggest recipes, and help you save money.\n\n" +
                    "Try asking:\n" +
                    "• Analyze my cart\n" +
                    "• What can I cook?\n" +
                    "• How can I save money?"
    }
}
