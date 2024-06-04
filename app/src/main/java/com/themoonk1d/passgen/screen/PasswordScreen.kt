package com.themoonk1d.passgen.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.themoonk1d.passgen.R
import com.themoonk1d.passgen.model.PasswordViewModel
import com.themoonk1d.passgen.ui.theme.PassGenTheme

@Composable
fun LogoLayout(modifier: Modifier = Modifier){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Card(
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
            modifier = modifier
                .clip(RoundedCornerShape(20.dp))
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_privacy_tip_24),
                modifier = Modifier
                    .size(60.dp)
                    .padding(8.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.surface),
                contentDescription = null
            )

        }
        Text(
            text = stringResource(id = R.string.app_name),
            modifier = Modifier.padding(top = 4.dp),
            style = MaterialTheme.typography.displayMedium
        )
        Text(
            text = stringResource(id = R.string.version),
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Composable
fun InformationCard(
    title: String,
    information: String,
    action: () -> Unit,
    image: Painter
){
    Card(
        modifier = Modifier.padding(top = 16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            IconButton(
                onClick = { }
            ) {
                Icon(
                    image,
                    contentDescription = null,
                )
            }
            Column(
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.padding(top = 4.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = information,
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(top = 8.dp)
                )

            }

        }
    }
}

@Composable
fun PasswordApp(
    modifier: Modifier = Modifier,
    passwordViewModel: PasswordViewModel = viewModel()
){
    val passwordUiState by passwordViewModel.uiState.collectAsState()
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
        ){
        Spacer(modifier = modifier.weight(2f))
        LogoLayout()
        Spacer(modifier = modifier.weight(1f))
        InformationCard(
            information = passwordUiState.passwordText,
            action = {},
            image = painterResource(id = R.drawable.round_content_copy_24),
            title = stringResource(id = R.string.password)
        )
        InformationCard(
            information = when(passwordUiState.passwordText.length){
                in 4..6 -> stringResource(id = R.string.bad)
                in 7..16 -> stringResource(id = R.string.not_bad)
                in 17..32 -> stringResource(id = R.string.good)
                in 33..64 -> stringResource(id = R.string.very_good)
                else -> stringResource(id = R.string.unknown)
                },
            action = {},
            image = painterResource(id = R.drawable.baseline_info_24),
            title = stringResource(id = R.string.Strength)
        )
        Spacer(modifier = modifier.weight(1f))
        Button(
            onClick = {
                passwordViewModel.setPassword()
            }
            ,
            modifier = Modifier.padding(top = 16.dp, bottom = 24.dp)
        ) {
            Text(text = stringResource(id = R.string.Re_generate))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PasswordAppPreview(){
    PassGenTheme {
        PasswordApp()
    }
}

