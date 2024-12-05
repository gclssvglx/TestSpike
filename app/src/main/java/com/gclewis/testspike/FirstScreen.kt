package com.gclewis.testspike

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gclewis.testspike.ui.theme.TestSpikeTheme

@Composable
fun FirstScreen(
    onNavigateToNextScreen: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.first_screen_description),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Button(
            onClick = {
                onNavigateToNextScreen()
            }
        ) {
            Text(
                text = stringResource(R.string.navigate_to_second_screen),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FirstScreenPreview() {
    TestSpikeTheme {
        FirstScreen(
            onNavigateToNextScreen = {}
        )
    }
}
