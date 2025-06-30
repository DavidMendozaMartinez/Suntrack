package com.davidmendozamartinez.sunrating.ui.designsystem

import androidx.compose.ui.graphics.Color
import com.davidmendozamartinez.sunrating.ui.designsystem.custom.CustomColorScheme

val Primary25 = Color(color = 0xFFFFF2F4)
val Primary50 = Color(color = 0xFFFFE4E9)
val Primary100 = Color(color = 0xFFFFD7DE)
val Primary200 = Color(color = 0xFFFFC1CC)
val Primary300 = Color(color = 0xFFFFA6B6)
val Primary400 = Color(color = 0xFFFF859B)
val Primary500 = Color(color = 0xFFFD6D87)
val Primary600 = Color(color = 0xFFF85774)
val Primary700 = Color(color = 0xFFEF3B5B)
val Primary800 = Color(color = 0xFFDD2849)
val Primary900 = Color(color = 0xFFCC1738)
val Primary950 = Color(color = 0xFFAC001F)

val Secondary25 = Color(color = 0xFFFAF7FF)
val Secondary50 = Color(color = 0xFFF2EDFC)
val Secondary100 = Color(color = 0xFFE4DAF8)
val Secondary200 = Color(color = 0xFFC7B5F0)
val Secondary300 = Color(color = 0xFFAB8CEE)
val Secondary400 = Color(color = 0xFF936BE9)
val Secondary500 = Color(color = 0xFF7A49E4)
val Secondary600 = Color(color = 0xFF663DBE)
val Secondary700 = Color(color = 0xFF503095)
val Secondary800 = Color(color = 0xFF3A236D)
val Secondary900 = Color(color = 0xFF251644)
val Secondary950 = Color(color = 0xFF140A28)

val Tertiary25 = Color(color = 0xFFFFF8F6)
val Tertiary50 = Color(color = 0xFFFFEEE7)
val Tertiary100 = Color(color = 0xFFFFD1BD)
val Tertiary200 = Color(color = 0xFFFFB393)
val Tertiary300 = Color(color = 0xFFFF956A)
val Tertiary400 = Color(color = 0xFFFF7840)
val Tertiary500 = Color(color = 0xFFEC6026)
val Tertiary600 = Color(color = 0xFFD54910)
val Tertiary700 = Color(color = 0xFFA73A0C)
val Tertiary800 = Color(color = 0xFF7A2A09)
val Tertiary900 = Color(color = 0xFF4D1A06)
val Tertiary950 = Color(color = 0xFF351103)

val Neutral25 = Color(color = 0xFFFFFFFF)
val Neutral50 = Color(color = 0xFFFAFAFA)
val Neutral100 = Color(color = 0xFFF3F3F3)
val Neutral200 = Color(color = 0xFFDFDFDF)
val Neutral300 = Color(color = 0xFFD1D1D1)
val Neutral400 = Color(color = 0xFFB3B3B3)
val Neutral500 = Color(color = 0xFF909090)
val Neutral600 = Color(color = 0xFF6C6C6C)
val Neutral700 = Color(color = 0xFF505050)
val Neutral800 = Color(color = 0xFF303030)
val Neutral900 = Color(color = 0xFF222222)
val Neutral950 = Color(color = 0xFF141414)

val Success25 = Color(color = 0xFFF7FBF4)
val Success50 = Color(color = 0xFFEDF6E6)
val Success100 = Color(color = 0xFFCDE5BB)
val Success200 = Color(color = 0xFFADD58F)
val Success300 = Color(color = 0xFF8EC563)
val Success400 = Color(color = 0xFF6EB437)
val Success500 = Color(color = 0xFF4CA309)
val Success600 = Color(color = 0xFF3F860A)
val Success700 = Color(color = 0xFF326B06)
val Success800 = Color(color = 0xFF244E04)
val Success900 = Color(color = 0xFF173103)
val Success950 = Color(color = 0xFF102401)

val Warning25 = Color(color = 0xFFFFFBF3)
val Warning50 = Color(color = 0xFFFFF2D9)
val Warning100 = Color(color = 0xFFFFE9BE)
val Warning200 = Color(color = 0xFFFFDFA1)
val Warning300 = Color(color = 0xFFFFD079)
val Warning400 = Color(color = 0xFFFEBC41)
val Warning500 = Color(color = 0xFFFDB022)
val Warning600 = Color(color = 0xFFFAA706)
val Warning700 = Color(color = 0xFFE39908)
val Warning800 = Color(color = 0xFFBC8107)
val Warning900 = Color(color = 0xFF996A08)
val Warning950 = Color(color = 0xFF6F4F07)

val Danger25 = Color(color = 0xFFFFF5F5)
val Danger50 = Color(color = 0xFFFCEAE9)
val Danger100 = Color(color = 0xFFF9D2D1)
val Danger200 = Color(color = 0xFFF3B4B1)
val Danger300 = Color(color = 0xFFED7974)
val Danger400 = Color(color = 0xFFE7544D)
val Danger500 = Color(color = 0xFFE22C23)
val Danger600 = Color(color = 0xFFBC251D)
val Danger700 = Color(color = 0xFF941D17)
val Danger800 = Color(color = 0xFF6C1511)
val Danger900 = Color(color = 0xFF440D0B)
val Danger950 = Color(color = 0xFF2C0706)

internal val customColorScheme = CustomColorScheme(
    primary = Primary800,
    onPrimary = Primary25,
    primaryContainer = Primary100,
    onPrimaryContainer = Primary900,
    secondary = Secondary700,
    onSecondary = Secondary25,
    secondaryContainer = Secondary200,
    onSecondaryContainer = Secondary900,
    tertiary = Tertiary500,
    onTertiary = Tertiary25,
    tertiaryContainer = Tertiary100,
    onTertiaryContainer = Tertiary700,
    background = Neutral25,
    onBackground = Neutral900,
    surface = Neutral25,
    onSurface = Neutral800,
    surfaceLow = Neutral100,
    onSurfaceLow = Neutral900,
//    surfaceVariant = Secondary950,
//    onSurfaceVariant = Secondary200,
//    surfaceVariantLow = Secondary900,
//    onSurfaceVariantLow = Secondary50,
    outline = Neutral200,
    outlineVariant = Primary500,
    success = Success300,
    onSuccess = Success950,
    successContainer = Success100,
    onSuccessContainer = Neutral950,
    warning = Warning300,
    onWarning = Warning950,
    warningContainer = Warning100,
    onWarningContainer = Neutral950,
    danger = Danger300,
    onDanger = Danger950,
    dangerContainer = Danger100,
    onDangerContainer = Neutral950,
)
