import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HelloSatyam(modifier: Modifier = Modifier, initialText: String = "Hello, User!") {
    // Create a mutable state for the user's text input
    var userText by remember { mutableStateOf(initialText) }
    var isExpanded by remember { mutableStateOf(false) }
    var textSize by remember { mutableStateOf(32f) }
    var animationDuration by remember { mutableStateOf(600) }
    var shadowElevation by remember { mutableStateOf(10.dp) }

    // Text style state
    var fontWeight by remember { mutableStateOf(FontWeight.Normal) }
    var fontStyle by remember { mutableStateOf(FontStyle.Normal) }

    // Animation states
    val animatedScale by animateFloatAsState(
        targetValue = if (isExpanded) 1.5f else 1f,
        animationSpec = tween(durationMillis = animationDuration, easing = FastOutSlowInEasing)
    )
    val animatedRotation by animateFloatAsState(
        targetValue = if (isExpanded) 360f else 0f,
        animationSpec = tween(durationMillis = animationDuration, easing = FastOutSlowInEasing)
    )
    val animatedAlpha by animateFloatAsState(
        targetValue = if (isExpanded) 1f else 0.5f,
        animationSpec = tween(durationMillis = animationDuration)
    )

    var backgroundColor by remember { mutableStateOf(Color(0xFF8E24AA)) }
    val animatedBackgroundColor by animateColorAsState(
        targetValue = backgroundColor,
        animationSpec = tween(durationMillis = animationDuration)
    )

    var textColor by remember { mutableStateOf(Color.White) }
    val animatedTextColor by animateColorAsState(
        targetValue = textColor,
        animationSpec = tween(durationMillis = animationDuration)
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.White)
            .clickable { isExpanded = !isExpanded },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            // TextField for user input
            TextField(
                value = userText,
                onValueChange = { userText = it },
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth(),
                label = { Text("Enter your text") }
            )

            // Slider for adjusting text size
            Text("Adjust Text Size: ${textSize.toInt()}", modifier = Modifier.padding(8.dp))
            Slider(
                value = textSize,
                onValueChange = { textSize = it },
                valueRange = 16f..64f,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            // Slider for adjusting animation duration
            Text("Adjust Animation Duration: ${animationDuration} ms", modifier = Modifier.padding(8.dp))
            Slider(
                value = animationDuration.toFloat(),
                onValueChange = { animationDuration = it.toInt() },
                valueRange = 300f..1500f,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            // Slider for adjusting shadow elevation
            Text("Adjust Shadow Elevation: ${shadowElevation.value.toInt()} dp", modifier = Modifier.padding(8.dp))
            Slider(
                value = shadowElevation.value,
                onValueChange = { shadowElevation = it.dp },
                valueRange = 0f..30f,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            // Font style selection
            Text("Select Font Style:", modifier = Modifier.padding(top = 16.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Button(onClick = { fontWeight = FontWeight.Normal; fontStyle = FontStyle.Normal }) {
                    Text("Normal")
                }
                Button(onClick = { fontWeight = FontWeight.Bold }) {
                    Text("Bold")
                }
                Button(onClick = { fontStyle = FontStyle.Italic }) {
                    Text("Italic")
                }
            }

            // Color pickers for background and text
            Text("Select Background Color:", modifier = Modifier.padding(top = 16.dp))
            ColorPicker { color -> backgroundColor = color }

            Text("Select Text Color:", modifier = Modifier.padding(top = 16.dp))
            ColorPicker { color -> textColor = color }

            // Preview Button
            Button(onClick = { isExpanded = true }, modifier = Modifier.padding(top = 16.dp)) {
                Text("Preview")
            }

            // Reset Button
            Button(onClick = {
                userText = initialText
                textSize = 32f
                animationDuration = 600
                shadowElevation = 10.dp
                fontWeight = FontWeight.Normal
                fontStyle = FontStyle.Normal
            }, modifier = Modifier.padding(top = 8.dp)) {
                Text("Reset Settings")
            }

            Spacer(modifier = Modifier.height(36.dp))

            // Animate the text size, scale, rotation, alpha, and background color
            Box(
                modifier = Modifier
                    .scale(animatedScale)
                    .background(animatedBackgroundColor, RoundedCornerShape(8.dp))
                    .padding(22.dp)
                    .shadow(shadowElevation)
            ) {
                Text(
                    text = userText,
                    fontSize = textSize.sp,
                    color = animatedTextColor,
                    fontWeight = fontWeight,
                    fontStyle = fontStyle,
                    modifier = Modifier
                        .alpha(animatedAlpha)
                        .padding(16.dp)
                        .rotate(animatedRotation)
                )
            }
        }
    }
}

@Composable
fun ColorPicker(onColorSelected: (Color) -> Unit) {
    var alpha by remember { mutableStateOf(1f) } // Separate alpha state
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        // Define a list of colors to choose from
        val colors = listOf(Color.Red, Color.Green, Color.Blue, Color.Yellow, Color.Cyan)
        colors.forEach { color ->
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(color, RoundedCornerShape(20.dp))
                    .clickable {
                        // Combine the selected color with the alpha value
                        onColorSelected(Color(color.red, color.green, color.blue, alpha))
                    }
            )
        }

        // Slider for adjusting color alpha
        Text("Alpha: ${alpha * 100}%", modifier = Modifier.padding(start = 8.dp, end = 8.dp))
        Slider(
            value = alpha,
            onValueChange = { alpha = it },
            valueRange = 0f..1f,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}
