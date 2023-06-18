package com.mukesh.contactmachinetask.presentation.ui.commonComponent

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mukesh.contactmachinetask.common.DRAWABLE_ALIAS

@Composable
fun AppBarComponent(
    navHostController: NavHostController,
    title: String = "",
    showBackButton: Boolean = false
) {
    ElevatedCard {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 15.dp)
        ) {
            AnimatedVisibility (visible = showBackButton){
                Icon(painter = painterResource(id = DRAWABLE_ALIAS.ic_back), contentDescription = "Back", modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        navHostController.popBackStack()
                    })
            }
            Spacer(modifier = Modifier.padding(5.dp))
            Text(text = title, modifier = Modifier.fillMaxWidth(), fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}