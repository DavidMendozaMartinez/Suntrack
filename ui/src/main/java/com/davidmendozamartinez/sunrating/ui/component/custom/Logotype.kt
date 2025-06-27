package com.davidmendozamartinez.sunrating.ui.component.custom

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import com.davidmendozamartinez.sunrating.ui.R
import com.davidmendozamartinez.sunrating.ui.designsystem.SunRatingTheme

@Composable
fun Logotype(
    modifier: Modifier = Modifier,
    color: Color = SunRatingTheme.colorScheme.onBackground,
    emphasizedColor: Color = SunRatingTheme.colorScheme.primary,
    style: TextStyle = SunRatingTheme.typography.titleLarge,
    emphasizedStyle: TextStyle = SunRatingTheme.typography.titleLargeEmphasized
) {
    val base: String = stringResource(id = R.string.logotype_base)
    val emphasized: String = stringResource(id = R.string.logotype_emphasized)

    Text(
        text = buildAnnotatedString {
            append(text = base)

            val start: Int = base.indexOf(string = emphasized).takeIf { it != -1 } ?: return@buildAnnotatedString
            val end: Int = start + emphasized.length

            addStyle(
                style = emphasizedStyle.toSpanStyle().copy(color = emphasizedColor),
                start = start,
                end = end,
            )
        },
        modifier = modifier,
        color = color,
        style = style,
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun LogotypePreview() {
    SunRatingTheme {
        Logotype(
            style = SunRatingTheme.typography.display,
            emphasizedStyle = SunRatingTheme.typography.displayEmphasized,
        )
    }
}
