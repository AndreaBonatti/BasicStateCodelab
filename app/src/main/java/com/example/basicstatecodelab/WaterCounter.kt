package com.example.basicstatecodelab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * WaterCounter contains a Text composable that displays the number of glasses
 */
//@Composable
//fun WaterCounter(modifier: Modifier = Modifier) {
//    Column(modifier = modifier.padding(16.dp)) {
////1)        var count = 0 // No Re-Composition call so no UI update
//        // Changes to count are now tracked by Compose
////2)        val count: MutableState<Int> = mutableStateOf(0) //  when a recomposition happens, the variable count is re-initialized back to 0, so we need a way to preserve this value across recompositions
////3)        val count: MutableState<Int> = remember { mutableStateOf(0) }
//        var count by remember { mutableStateOf(0) } // simplify the usage of count by using Kotlin's delegated properties (more Readable code)
////1)        Text(text = "You've had $count glasses.")
////2)        Text(text = "You've had ${count.value} glasses.")
//        if (count > 0) {
//            var showTask by remember { mutableStateOf(true) }
//            if (showTask) {
//                WellnessTaskItem(taskName = "Have you taken your 15 minute walk today?"
//                    , onClose = { showTask = false })
//            }
//            // This text is present if the button has been clicked
//            // at least once; absent otherwise
//            Text("You've had $count glasses.")
//        }
//        Row(Modifier.padding(top = 8.dp)) {
//            Button(
//                onClick = {
////1)            count++
////2,3)            count.value++
//                    count++
//                },
//                // This button is enabled until we add the 10th glass
//                enabled = count < 10
//            ) {
//                Text(text = "Add one")
//            }
//            Button(onClick = { count = 0 }, modifier = Modifier.padding(start = 8.dp)) {
//                Text(text = "Clear water count")
//            }
//        }
//    }
//}

// Reset at this version at end of the 7 point of 13
@Composable
fun WaterCounter(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        // While remember helps you retain state across recompositions,
        // it's not retained across configuration changes.
        // For this, you must use rememberSaveable instead of remember.
//        var count by remember { mutableStateOf(0) }
        var count by rememberSaveable { mutableStateOf(0) }
        if (count > 0) {
            Text("You've had $count glasses.")
        }
        Button(onClick = { count++ }, Modifier.padding(top = 8.dp), enabled = count < 10) {
            Text("Add one")
        }
    }
}

// State hoisting --> WaterCounter is divided in 2 parts: StatelessCounter and StatefulCounter

// Stateless composable can now be reused
@Composable
fun StatelessCounter(count:Int, onIncrement: () -> Unit, modifier: Modifier = Modifier){
    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {
            Text("You've had $count glasses.")
        }
        Button(onClick = onIncrement, Modifier.padding(top = 8.dp), enabled = count < 10) {
            Text("Add one")
        }
    }
}

// Stateful composable function can provide the same state to multiple composable functions
@Composable
fun StatefulCounter(modifier: Modifier = Modifier){
    var count by rememberSaveable { mutableStateOf(0) }
    StatelessCounter(count = count, onIncrement = { count++ }, modifier = modifier)
}
