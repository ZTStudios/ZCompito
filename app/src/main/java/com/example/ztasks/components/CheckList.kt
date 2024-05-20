import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ztasks.R

@Preview(showBackground = true)
@Composable
fun CheckList() {
    // Lista de elementos
    val items = remember { mutableStateOf(listOf("Hacer de desayunar", "Item 2", "Item 3")) }

    LazyColumn {
        items(items.value) { item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Checkbox
                val checkedState = remember { mutableStateOf(false) }
                Row(verticalAlignment = Alignment.CenterVertically){
                    Checkbox(
                        checked = checkedState.value,
                        onCheckedChange = { checkedState.value = it }
                    )

                    Text(text = item, modifier = Modifier.padding(start = 8.dp))
                }

                // Botón de eliminar
                IconButton(onClick = { items.value = items.value - item }) {
                    Icon(Icons.Filled.Delete, contentDescription = "Eliminar", tint = colorResource(
                        id = R.color.red_400)
                    )
                }
            }
        }
    }
}