package com.example.basicstatecodelab

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

private fun getWellNessTasks() = List(30) { i -> WellnessTask(i, "Task # $i") }

@Composable
fun WellnessTasksList(
    modifier: Modifier = Modifier,
    list: List<WellnessTask> = remember { getWellNessTasks() }
) {
    LazyColumn(modifier = modifier){
        items(list) { task ->
            WellnessTaskItem(taskName = task.label)
        }
    }
}