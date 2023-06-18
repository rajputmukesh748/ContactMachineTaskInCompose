package com.mukesh.contactmachinetask.presentation.navController

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.mukesh.contactmachinetask.common.DRAWABLE_ALIAS
import com.mukesh.contactmachinetask.presentation.theme.Purple80


@Composable
fun BottomNavController(
    navHostController: NavHostController
) {
    val navBackStackEntry = navHostController.currentBackStackEntryAsState()
    val route = navBackStackEntry.value?.destination?.route
    val visibility = (route == Screens.ContactsScreen.route) || (route == Screens.MessagesScreen.route)
    AnimatedVisibility(visible = visibility) {
        BottomMenu(
            navHostController = navHostController,
            items = listOf(
                BottomMenuContent(title = "Contacts", iconId = DRAWABLE_ALIAS.ic_group),
                BottomMenuContent(title = "Messages", iconId = DRAWABLE_ALIAS.ic_message)
            ),
        )
    }
}


@Composable
fun BottomMenu(
    navHostController: NavHostController,
    items: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeHighlightColor: Color = Purple80,
    activeTextColor: Color = Purple80,
    inActiveTextColor: Color = Color.Black,
    initialSelectedItemIndex: Int = 0
) {
    var selectedItemIndex by remember { mutableStateOf(initialSelectedItemIndex) }
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color.White)
    ) {
        items.forEachIndexed { index, item ->
            BottomMenuItem(
                item = item,
                isSelected = index == selectedItemIndex,
                activeHighlightColor = activeHighlightColor,
                activeTextColor = activeTextColor,
                inActiveTextColor = inActiveTextColor
            ) {
                selectedItemIndex = index
                navHostController.navigate(if (index == 1) Screens.MessagesScreen.route else Screens.ContactsScreen.route)
            }
        }
    }
}


@Composable
fun BottomMenuItem(
    item: BottomMenuContent,
    isSelected: Boolean = false,
    activeHighlightColor: Color = Purple80,
    activeTextColor: Color = Color.White,
    inActiveTextColor: Color = Color.Black,
    onItemClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable {
            onItemClick()
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(if (isSelected) activeHighlightColor else Color.Transparent)
                .padding(5.dp)
        ) {
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = item.title,
                tint = if (isSelected) Color.White else inActiveTextColor,
                modifier = Modifier.size(18.dp)
            )
        }
        Text(
            text = item.title,
            color = if (isSelected) activeTextColor else inActiveTextColor,
            fontSize = 12.sp
        )
    }
}

