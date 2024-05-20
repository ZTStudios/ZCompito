package com.example.ztasks.views

import CheckList
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ztasks.R
import com.example.ztasks.components.StatusWidget

@Preview(showBackground = true)
@Composable
fun Home() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(10.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Image(
                    painter = painterResource(id = R.drawable.logo_zt),
                    contentDescription = "zt logo",
                    modifier = Modifier.size(width = 70.dp, height = 70.dp)
                )
            }
            Column {
                Text(text = "Fernando Gomez", fontSize = 16.sp)
                //Aqui dentro deberia de ir una imagen
            }

        }
        Row {
            Text(
                text = "Current Task",
                fontSize = 20.sp,
                style = TextStyle(color = colorResource(id = R.color.gray_600)),
                fontWeight = FontWeight.Medium
            )
        }
        Divider(modifier = Modifier.height(10.dp), color = Color.Transparent)
        Row {
            StatusWidget()
        }
        Divider(modifier = Modifier.height(10.dp), color = Color.Transparent)
        Row {
            StatusWidget()
        }
        Divider(modifier = Modifier.height(10.dp), color = Color.Transparent)
        Row {
            Text(
                text = "My day",
                fontSize = 20.sp,
                style = TextStyle(color = colorResource(id = R.color.gray_600)),
                fontWeight = FontWeight.Medium
            )
        }
        Row{
            CheckList()
        }

    }
}