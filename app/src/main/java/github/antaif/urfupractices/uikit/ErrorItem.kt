package github.antaif.urfupractices.uikit

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import github.antaif.urfupractices.R

@Composable
fun ErrorItem(
    modifier: Modifier = Modifier,
    error: String? = null,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(Spacing.small),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = error ?: stringResource(R.string.error_occurred),
            style = MaterialTheme.typography.bodyMedium
        )
        Icon(
            imageVector = Icons.Default.Refresh,
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorItemPreview() {
    ErrorItem {}
}