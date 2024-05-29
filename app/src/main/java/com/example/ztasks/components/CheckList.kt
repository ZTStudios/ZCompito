import android.annotation.SuppressLint
import android.text.Layout
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
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.*
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ztasks.R
import com.example.ztasks.data.models.Task

@SuppressLint("RememberReturnType")
@Composable
fun CheckList(tasks: List<Task>, onDelete: (Task) -> Unit, onTaskClick: (Task) -> Unit) {
    LazyColumn {
        items(tasks) { task ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(8.dp)
                    .fillParentMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                val checkedState = remember { mutableStateOf(false) }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = task.completed ?: false,
                        onCheckedChange = { checked ->
                            onTaskClick(task.copy(completed = checked))
                            checkedState.value = checked
                        }
                    )

                    Column {
                    Text(text = task.title, modifier = Modifier.padding(start = 8.dp), style = TextStyle( fontWeight = FontWeight.Bold))
                    Text(text = task.description, modifier = Modifier.padding(start = 8.dp))
                    }
                }

                IconButton(onClick = { onDelete(task) }) {
                    Icon(
                        Icons.Filled.Delete,
                        contentDescription = "Eliminar",
                        tint = colorResource(id = R.color.red_400)
                    )
                }
            }
        }
    }
}

