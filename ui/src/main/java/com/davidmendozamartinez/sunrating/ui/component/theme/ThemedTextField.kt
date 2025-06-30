@file:OptIn(ExperimentalMaterial3Api::class)

package com.davidmendozamartinez.sunrating.ui.component.theme

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.davidmendozamartinez.sunrating.ui.designsystem.SunRatingTheme
import com.davidmendozamartinez.sunrating.ui.extension.asDisabled

@Composable
fun ThemedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    placeholder: String? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: String? = null,
    suffix: String? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: TextFieldColors = ThemedTextFieldDefaults.surfaceLowColors(),
    contentPadding: PaddingValues = ThemedTextFieldDefaults.ContentPadding,
) {
    val textColor: Color = with(colors) {
        val focused = interactionSource.collectIsFocusedAsState().value
        when {
            !enabled -> disabledTextColor
            isError -> errorTextColor
            focused -> focusedTextColor
            else -> unfocusedTextColor
        }
    }
    val mergedTextStyle: TextStyle = SunRatingTheme.typography.bodyMedium.merge(other = TextStyle(color = textColor))

    CompositionLocalProvider(value = LocalTextSelectionColors provides colors.textSelectionColors) {
        BasicTextField(
            value = value,
            modifier = modifier.defaultMinSize(
                minWidth = ThemedTextFieldDefaults.MinWidth,
                minHeight = ThemedTextFieldDefaults.MinHeight
            ),
            onValueChange = onValueChange,
            enabled = enabled,
            readOnly = readOnly,
            textStyle = mergedTextStyle,
            cursorBrush = SolidColor(value = with(colors) { if (isError) errorCursorColor else cursorColor }),
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            interactionSource = interactionSource,
            singleLine = singleLine,
            maxLines = maxLines,
            minLines = minLines,
            decorationBox =
                @Composable { innerTextField ->
                    TextFieldDefaults.DecorationBox(
                        value = value,
                        visualTransformation = visualTransformation,
                        innerTextField = innerTextField,
                        placeholder = textOrNull(value = placeholder, style = SunRatingTheme.typography.bodyMedium),
                        label = null,
                        leadingIcon = leadingIcon,
                        trailingIcon = trailingIcon,
                        prefix = textOrNull(value = prefix, style = SunRatingTheme.typography.bodyMedium),
                        suffix = textOrNull(value = suffix, style = SunRatingTheme.typography.bodyMedium),
                        supportingText = null,
                        shape = SunRatingTheme.shape.small,
                        singleLine = singleLine,
                        enabled = enabled,
                        isError = isError,
                        interactionSource = interactionSource,
                        colors = colors,
                        contentPadding = contentPadding,
                    )
                }
        )
    }
}

@Composable
private fun textOrNull(
    value: String?,
    style: TextStyle,
): @Composable (() -> Unit)? =
    if (value != null) {
        { Text(text = value, style = style) }
    } else {
        null
    }

@Preview
@Composable
private fun ThemedTextFieldPreview() {
    SunRatingTheme {
        var value: String by remember { mutableStateOf(value = "") }

        ThemedTextField(
            value = value,
            onValueChange = { value = it },
            placeholder = LoremIpsum(words = 3).values.first(),
        )
    }
}

object ThemedTextFieldDefaults {
    val MinWidth = 280.dp
    val MinHeight = 40.dp

    @Composable
    fun surfaceLowColors(): TextFieldColors = TextFieldDefaults.colors(
        focusedTextColor = SunRatingTheme.colorScheme.onSurfaceLow,
        unfocusedTextColor = SunRatingTheme.colorScheme.onSurfaceLow,
        disabledTextColor = SunRatingTheme.colorScheme.onSurfaceLow.asDisabled(),
        errorTextColor = SunRatingTheme.colorScheme.onSurfaceLow,
        focusedContainerColor = SunRatingTheme.colorScheme.surfaceLow,
        unfocusedContainerColor = SunRatingTheme.colorScheme.surfaceLow,
        disabledContainerColor = SunRatingTheme.colorScheme.surfaceLow,
        errorContainerColor = SunRatingTheme.colorScheme.dangerContainer,
        cursorColor = SunRatingTheme.colorScheme.primary,
        errorCursorColor = SunRatingTheme.colorScheme.primary,
        selectionColors = TextSelectionColors(
            handleColor = SunRatingTheme.colorScheme.primary,
            backgroundColor = SunRatingTheme.colorScheme.primaryContainer,
        ),
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        errorIndicatorColor = Color.Transparent,
        focusedLeadingIconColor = SunRatingTheme.colorScheme.onSurfaceLow,
        unfocusedLeadingIconColor = SunRatingTheme.colorScheme.onSurfaceLow,
        disabledLeadingIconColor = SunRatingTheme.colorScheme.onSurfaceLow.asDisabled(),
        errorLeadingIconColor = SunRatingTheme.colorScheme.onSurfaceLow,
        focusedTrailingIconColor = SunRatingTheme.colorScheme.onSurfaceLow,
        unfocusedTrailingIconColor = SunRatingTheme.colorScheme.onSurfaceLow,
        disabledTrailingIconColor = SunRatingTheme.colorScheme.onSurfaceLow.asDisabled(),
        errorTrailingIconColor = SunRatingTheme.colorScheme.onSurfaceLow,
        focusedLabelColor = Color.Unspecified,
        unfocusedLabelColor = Color.Unspecified,
        disabledLabelColor = Color.Unspecified,
        errorLabelColor = Color.Unspecified,
        focusedPlaceholderColor = SunRatingTheme.colorScheme.onSurfaceLow.copy(alpha = ALPHA_PLACEHOLDER),
        unfocusedPlaceholderColor = SunRatingTheme.colorScheme.onSurfaceLow.copy(alpha = ALPHA_PLACEHOLDER),
        disabledPlaceholderColor = SunRatingTheme.colorScheme.onSurfaceLow.copy(alpha = ALPHA_PLACEHOLDER),
        errorPlaceholderColor = SunRatingTheme.colorScheme.onSurfaceLow.copy(alpha = ALPHA_PLACEHOLDER),
        focusedSupportingTextColor = Color.Unspecified,
        unfocusedSupportingTextColor = Color.Unspecified,
        disabledSupportingTextColor = Color.Unspecified,
        errorSupportingTextColor = Color.Unspecified,
        focusedPrefixColor = SunRatingTheme.colorScheme.onSurfaceLow,
        unfocusedPrefixColor = SunRatingTheme.colorScheme.onSurfaceLow,
        disabledPrefixColor = SunRatingTheme.colorScheme.onSurfaceLow.asDisabled(),
        errorPrefixColor = SunRatingTheme.colorScheme.onSurfaceLow,
        focusedSuffixColor = SunRatingTheme.colorScheme.onSurfaceLow,
        unfocusedSuffixColor = SunRatingTheme.colorScheme.onSurfaceLow,
        disabledSuffixColor = SunRatingTheme.colorScheme.onSurfaceLow.asDisabled(),
        errorSuffixColor = SunRatingTheme.colorScheme.onSurfaceLow,
    )

    val ContentPadding: PaddingValues
        @Composable get() = PaddingValues(
            horizontal = SunRatingTheme.spacing.space4,
            vertical = SunRatingTheme.spacing.space2,
        )
}

private const val ALPHA_PLACEHOLDER = 0.6f
