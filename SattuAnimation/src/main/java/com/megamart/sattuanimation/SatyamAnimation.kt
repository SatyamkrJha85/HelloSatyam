import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun HelloSatyam(img: Int) {
    var imageUrl by remember { mutableStateOf("") }
    var borderColor by remember { mutableStateOf(Color.Blue) }
    var borderWidth by remember { mutableStateOf(2.dp) }
    var animationDuration by remember { mutableStateOf(500) }
    var isClicked by remember { mutableStateOf(false) }
    var rotationAngle by remember { mutableStateOf(0f) }
    var opacity by remember { mutableStateOf(1f) }

    // Animations
    val scale by animateFloatAsState(
        targetValue = if (isClicked) 1.3f else 1f,
        animationSpec = tween(durationMillis = animationDuration, easing = FastOutSlowInEasing)
    )
    val animatedBorderColor by animateColorAsState(
        targetValue = borderColor,
        animationSpec = tween(durationMillis = animationDuration)
    )
    val animatedBorderWidth by animateDpAsState(
        targetValue = borderWidth,
        animationSpec = tween(durationMillis = animationDuration)
    )
    val animatedRotationAngle by animateFloatAsState(
        targetValue = if (isClicked) 360f else 0f,
        animationSpec = tween(durationMillis = animationDuration, easing = FastOutSlowInEasing)
    )
    val animatedOpacity by animateFloatAsState(
        targetValue = opacity,
        animationSpec = tween(durationMillis = animationDuration)
    )

    // Coroutine scope for simulating image picker
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Image Box for Profile Picture with More Animations
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .scale(scale)
                    .rotate(animatedRotationAngle)
                    .clip(CircleShape)
                    .background(Color.Gray)
                    .border(animatedBorderWidth, animatedBorderColor, CircleShape)
                    .clickable {
                        coroutineScope.launch {
                            imageUrl = "https://via.placeholder.com/150"
                            isClicked = !isClicked
                        }
                    }
                    .alpha(animatedOpacity)
            ) {
                if (imageUrl.isNotEmpty()) {
                    // Load Image if available
                    Image(
                        painter = painterResource(id = img),
                        contentDescription = "Profile Picture",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    // Placeholder Text
                    Text(
                        text = "Add Image",
                        color = Color.White,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Border Color Picker
            ColorPicker(onColorSelected = { selectedColor ->
                borderColor = selectedColor
            })

            // Border Width Slider
            Text("Border Width: ${borderWidth.value.toInt()} dp")
            Slider(
                value = borderWidth.value,
                onValueChange = { borderWidth = it.dp },
                valueRange = 0f..10f,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            // Animation Duration Slider
            Text("Animation Duration: ${animationDuration} ms")
            Slider(
                value = animationDuration.toFloat(),
                onValueChange = { animationDuration = it.toInt() },
                valueRange = 300f..1500f,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            // Rotation Angle Control
            Text("Rotation Angle: ${rotationAngle.toInt()}°")
            Slider(
                value = rotationAngle,
                onValueChange = { rotationAngle = it },
                valueRange = 0f..360f,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            // Opacity Control
            Text("Opacity: ${opacity * 100}%")
            Slider(
                value = opacity,
                onValueChange = { opacity = it },
                valueRange = 0f..1f,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun ColorPicker(onColorSelected: (Color) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        val colors = listOf(Color.Red, Color.Green, Color.Blue, Color.Yellow, Color.Cyan, Color.Magenta)
        colors.forEach { color ->
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(color)
                    .clickable { onColorSelected(color) }
            )
        }
    }
}
