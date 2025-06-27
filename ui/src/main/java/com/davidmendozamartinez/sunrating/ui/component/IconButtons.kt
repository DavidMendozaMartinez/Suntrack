package com.davidmendozamartinez.sunrating.ui.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.davidmendozamartinez.sunrating.ui.R
import com.davidmendozamartinez.sunrating.ui.extension.asDisabled

@Composable
fun BackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
    enforceMinInteractiveSize: Boolean = true,
) {
    BaseIconButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource,
        enforceMinInteractiveSize = enforceMinInteractiveSize,
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
            contentDescription = stringResource(id = R.string.button_back_content_description),
        )
    }
}

@Composable
fun CloseButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
    enforceMinInteractiveSize: Boolean = true,
) {
    BaseIconButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource,
        enforceMinInteractiveSize = enforceMinInteractiveSize,
    ) {
        Icon(
            imageVector = Icons.Outlined.Close,
            contentDescription = stringResource(id = R.string.button_close_content_description),
        )
    }
}

@Composable
fun SettingsButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
    enforceMinInteractiveSize: Boolean = true,
) {
    BaseIconButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource,
        enforceMinInteractiveSize = enforceMinInteractiveSize,
    ) {
        Icon(
            imageVector = Icons.Outlined.Settings,
            contentDescription = stringResource(id = R.string.button_settings_content_description),
        )
    }
}

@Composable
private fun BaseIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
    enforceMinInteractiveSize: Boolean = true,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        value = LocalMinimumInteractiveComponentSize provides if (enforceMinInteractiveSize) {
            LocalMinimumInteractiveComponentSize.current
        } else {
            Dp.Unspecified
        },
    ) {
        IconButton(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = Color.Transparent,
                contentColor = LocalContentColor.current,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = LocalContentColor.current.asDisabled(),
            ),
            interactionSource = interactionSource,
            content = content,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun BaseIconButtonPreview(
) {
    BaseIconButton(onClick = {}) {
        Icon(
            imageVector = Icons.Default.Share,
            contentDescription = stringResource(id = R.string.button_close_content_description),
        )
    }
}
