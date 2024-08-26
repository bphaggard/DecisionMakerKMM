package com.example.decisionkmm.android

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.decisionkmm.android.ui.backgroundDark
import com.example.decisionkmm.android.ui.backgroundLight
import com.example.decisionkmm.android.ui.errorContainerDark
import com.example.decisionkmm.android.ui.errorContainerLight
import com.example.decisionkmm.android.ui.errorDark
import com.example.decisionkmm.android.ui.errorLight
import com.example.decisionkmm.android.ui.inverseOnSurfaceDark
import com.example.decisionkmm.android.ui.inverseOnSurfaceLight
import com.example.decisionkmm.android.ui.inversePrimaryDark
import com.example.decisionkmm.android.ui.inversePrimaryLight
import com.example.decisionkmm.android.ui.inverseSurfaceDark
import com.example.decisionkmm.android.ui.inverseSurfaceLight
import com.example.decisionkmm.android.ui.onBackgroundDark
import com.example.decisionkmm.android.ui.onBackgroundLight
import com.example.decisionkmm.android.ui.onErrorContainerDark
import com.example.decisionkmm.android.ui.onErrorContainerLight
import com.example.decisionkmm.android.ui.onErrorDark
import com.example.decisionkmm.android.ui.onErrorLight
import com.example.decisionkmm.android.ui.onPrimaryContainerDark
import com.example.decisionkmm.android.ui.onPrimaryContainerLight
import com.example.decisionkmm.android.ui.onPrimaryDark
import com.example.decisionkmm.android.ui.onPrimaryLight
import com.example.decisionkmm.android.ui.onSecondaryContainerDark
import com.example.decisionkmm.android.ui.onSecondaryContainerLight
import com.example.decisionkmm.android.ui.onSecondaryDark
import com.example.decisionkmm.android.ui.onSecondaryLight
import com.example.decisionkmm.android.ui.onSurfaceDark
import com.example.decisionkmm.android.ui.onSurfaceLight
import com.example.decisionkmm.android.ui.onSurfaceVariantDark
import com.example.decisionkmm.android.ui.onSurfaceVariantLight
import com.example.decisionkmm.android.ui.onTertiaryContainerDark
import com.example.decisionkmm.android.ui.onTertiaryContainerLight
import com.example.decisionkmm.android.ui.onTertiaryDark
import com.example.decisionkmm.android.ui.onTertiaryLight
import com.example.decisionkmm.android.ui.outlineDark
import com.example.decisionkmm.android.ui.outlineLight
import com.example.decisionkmm.android.ui.outlineVariantDark
import com.example.decisionkmm.android.ui.outlineVariantLight
import com.example.decisionkmm.android.ui.primaryContainerDark
import com.example.decisionkmm.android.ui.primaryContainerLight
import com.example.decisionkmm.android.ui.primaryDark
import com.example.decisionkmm.android.ui.primaryLight
import com.example.decisionkmm.android.ui.scrimDark
import com.example.decisionkmm.android.ui.scrimLight
import com.example.decisionkmm.android.ui.secondaryContainerDark
import com.example.decisionkmm.android.ui.secondaryContainerLight
import com.example.decisionkmm.android.ui.secondaryDark
import com.example.decisionkmm.android.ui.secondaryLight
import com.example.decisionkmm.android.ui.surfaceDark
import com.example.decisionkmm.android.ui.surfaceLight
import com.example.decisionkmm.android.ui.surfaceVariantDark
import com.example.decisionkmm.android.ui.surfaceVariantLight
import com.example.decisionkmm.android.ui.tertiaryContainerDark
import com.example.decisionkmm.android.ui.tertiaryContainerLight
import com.example.decisionkmm.android.ui.tertiaryDark
import com.example.decisionkmm.android.ui.tertiaryLight

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColorScheme(
            primary = primaryDark,
            onPrimary = onPrimaryDark,
            primaryContainer = primaryContainerDark,
            onPrimaryContainer = onPrimaryContainerDark,
            secondary = secondaryDark,
            onSecondary = onSecondaryDark,
            secondaryContainer = secondaryContainerDark,
            onSecondaryContainer = onSecondaryContainerDark,
            tertiary = tertiaryDark,
            onTertiary = onTertiaryDark,
            tertiaryContainer = tertiaryContainerDark,
            onTertiaryContainer = onTertiaryContainerDark,
            error = errorDark,
            onError = onErrorDark,
            errorContainer = errorContainerDark,
            onErrorContainer = onErrorContainerDark,
            background = backgroundDark,
            onBackground = onBackgroundDark,
            surface = surfaceDark,
            onSurface = onSurfaceDark,
            surfaceVariant = surfaceVariantDark,
            onSurfaceVariant = onSurfaceVariantDark,
            outline = outlineDark,
            outlineVariant = outlineVariantDark,
            scrim = scrimDark,
            inverseSurface = inverseSurfaceDark,
            inverseOnSurface = inverseOnSurfaceDark,
            inversePrimary = inversePrimaryDark
        )
    } else {
        lightColorScheme(
            primary = primaryLight,
            onPrimary = onPrimaryLight,
            primaryContainer = primaryContainerLight,
            onPrimaryContainer = onPrimaryContainerLight,
            secondary = secondaryLight,
            onSecondary = onSecondaryLight,
            secondaryContainer = secondaryContainerLight,
            onSecondaryContainer = onSecondaryContainerLight,
            tertiary = tertiaryLight,
            onTertiary = onTertiaryLight,
            tertiaryContainer = tertiaryContainerLight,
            onTertiaryContainer = onTertiaryContainerLight,
            error = errorLight,
            onError = onErrorLight,
            errorContainer = errorContainerLight,
            onErrorContainer = onErrorContainerLight,
            background = backgroundLight,
            onBackground = onBackgroundLight,
            surface = surfaceLight,
            onSurface = onSurfaceLight,
            surfaceVariant = surfaceVariantLight,
            onSurfaceVariant = onSurfaceVariantLight,
            outline = outlineLight,
            outlineVariant = outlineVariantLight,
            scrim = scrimLight,
            inverseSurface = inverseSurfaceLight,
            inverseOnSurface = inverseOnSurfaceLight,
            inversePrimary = inversePrimaryLight
        )
    }
    val typography = Typography(
        bodyMedium = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )
    )
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
