
package com.kolhapur.smartcart

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    navController: NavController,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        containerColor = Color.White,   // 👈 THIS LINE
        topBar = { TopBar() },
        bottomBar = { BottomNav(navController) }
    ) { paddingValues ->
        content(paddingValues)
    }

}

