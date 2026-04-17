package com.webcrafterszl.gatekeeper.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val GatekeeperColorScheme = lightColorScheme(
    primary = PrimaryGreen,
    secondary = PrimaryNeon,
    tertiary = DarkTeal,
    background = Background,
    surface = Surface,
    surfaceVariant = AccentSoftGreen.copy(alpha = 0.24f),
    outline = BorderLight,
    onPrimary = Color.White,
    onSecondary = TextPrimary,
    onTertiary = Color.White,
    onBackground = TextPrimary,
    onSurface = TextPrimary,
    onSurfaceVariant = TextSecondary,
    error = ErrorRed,
    onError = Color.White,
    errorContainer = Color(0xFFFDECEC),
    onErrorContainer = ErrorRed,
)

@Composable
fun GatekeeperTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = GatekeeperColorScheme,
        content = content,
    )
}

