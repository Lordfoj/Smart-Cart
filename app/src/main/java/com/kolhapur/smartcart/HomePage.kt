package com.kolhapur.smartcart

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.CardGiftcard
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(navController: NavController) {
    val isLoggedIn by remember { mutableStateOf(false) }

    AppScaffold(navController) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            if (!isLoggedIn) {
                LoginOfferCard()
            }
            StartShoppingSection(navController)
            RewardsSection()
            RecentStoresSection()
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}


@Composable
private fun LoginOfferCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)),
        elevation = CardDefaults.cardElevation(4.dp),
        border = BorderStroke(1.dp, Color(0xFF1976D2).copy(alpha = 0.5f))
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = "Get ₹200 OFF on Your First Order 🎉",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Flat 50% discount up to ₹200 for new users",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { /* Handle Sign In */ },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Unlock Offer & Sign In", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun StartShoppingSection(navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text("Start Your Shopping", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Text("Scan products or explore nearby stores", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
            }
            Button(
                onClick = {

                },
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier.width(95.dp)   // 👈 FIX
            ) {
                Text(
                    text = "Go!",
                    maxLines = 1                    // 👈 SAFETY
                )
            }
        }
    }
}

@Composable
private fun RewardsSection() {
    val rewards = listOf(
        "₹5–₹30 Cashback" to "On ₹500+ Shopping",
        "Shopping Voucher" to "On ₹1000+ Order",
        "Buy 1 Get 1" to "Frequent Buyer Reward"
    )

    Column(modifier = Modifier.padding(top = 24.dp)) {
        Text(
            text = "🎁 Rewards for You",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            rewards.forEach { (reward, condition) ->
                ScratchCard(reward, condition)
            }
        }
    }
}

@Composable
private fun ScratchCard(reward: String, condition: String) {
    Card(
        modifier = Modifier.size(width = 160.dp, height = 180.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F7FA)),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(Icons.Outlined.CardGiftcard, contentDescription = null, tint = Color(0xFF00796B), modifier = Modifier.size(40.dp))
            Spacer(modifier = Modifier.height(12.dp))
            Text("Scratch & Win", fontWeight = FontWeight.Bold, fontSize = 16.sp, textAlign = TextAlign.Center)
            Text(reward, fontSize = 14.sp, color = Color.Gray, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.weight(1f))
            Text(condition, fontSize = 12.sp, color = Color.Gray, textAlign = TextAlign.Center)
        }
    }
}

@Composable
private fun RecentStoresSection() {
    val recentStores = listOf(
        StoreHistory("Star Bazaar", "₹1,240 spent · 2 days ago", Icons.Outlined.Storefront),
        StoreHistory("D-Mart", "₹560 spent · Yesterday", Icons.Outlined.Storefront),
        StoreHistory("Local Mart", "₹810 spent · 4 days ago", Icons.Outlined.Storefront),
        StoreHistory("Reliance Fresh", "₹2,150 spent · Last week", Icons.Outlined.Storefront)
    )

    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp)) {
        Text(
            text = "🕘 Recent Stores Visited",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(12.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Column(modifier = Modifier.padding(vertical = 8.dp)) {
                recentStores.forEach { store ->
                    StoreHistoryItem(store)
                    Divider(modifier = Modifier.padding(horizontal = 16.dp))
                }
            }
        }
    }
}

data class StoreHistory(val name: String, val info: String, val icon: ImageVector)

@Composable
private fun StoreHistoryItem(store: StoreHistory) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = store.icon,
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color(0xFFE3F2FD))
                .padding(8.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(store.name, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyLarge)
            Text(store.info, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
        }
    }
}


// --- Fixed Components (Not Modified as per instructions) ---

@OptIn(ExperimentalMaterial3Api::class)
@Composable
 fun TopBar() {
    TopAppBar(
        title = {
            Text(
                text = "Smart Cart",
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF1976D2)
        ),
        actions = {
            IconButton(onClick = { /* Profile */ }) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Profile",
                    tint = Color.White
                )
            }
        }
    )
}

@Composable
fun BottomNav(navController: NavController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val navItems = listOf(
        Routes.HOME to Icons.Default.Home,
        Routes.EXPLORE to Icons.Default.Explore,
        Routes.GEMINI to Icons.Default.AutoAwesome,
        Routes.CART to Icons.Default.ShoppingCart,
        Routes.SETTINGS to Icons.Default.Settings
    )


    NavigationBar(
        containerColor = Color(0xFF1976D2)
    ) {
        navItems.forEach { (route, icon) ->

            val isSelected = currentRoute == route

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    if (currentRoute != route) {
                        navController.navigate(route) {
                            launchSingleTop = true
                            restoreState = true
                            popUpTo(Routes.HOME) {
                                saveState = true
                            }
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = route,
                        tint = if (isSelected) Color.White else Color(0xFFBBDEFB)
                    )
                },
                label = {
                    Text(
                        text = route.replaceFirstChar { it.uppercase() },
                        fontSize = 11.sp,
                        color = Color.White
                    )
                },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}



@Composable
private fun GeminiFab() {
    Box(
        modifier = Modifier
            .size(64.dp)
            .offset(y = 36.dp) // Overlap nav bar
            .clip(CircleShape)
            .background(Color.White)
            .border(2.dp, Color(0xFF1976D2), CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.gimini_icon),
            contentDescription = "Gemini",
            modifier = Modifier.size(48.dp),
            tint = Color.Unspecified
        )
    }
}
