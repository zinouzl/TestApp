package com.example.testapp.ui.auth

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.testapp.R
import com.example.testapp.ui.base.compose.DialogPosition
import com.example.testapp.ui.base.compose.compenent.LargeButtonPrimary
import com.example.testapp.ui.base.compose.compenent.LaunchedEffectFlowWithLifecycle
import com.example.testapp.ui.base.compose.compenent.LoaderDialog
import com.example.testapp.ui.base.compose.compenent.PositionalDialog
import com.example.testapp.ui.base.composenavigation.NavigationArgs
import com.example.testapp.ui.base.composenavigation.Screen

@Composable
fun AuthScreen(
    viewModel: AuthViewModel = viewModel(),
    navController: NavController
) {

    LaunchedEffectFlowWithLifecycle(flow = viewModel.event) { event ->
        when (event) {
            is AuthViewModel.Event.Navigation.UserValidated -> navController.navigate(
                route = Screen.PostsScreen.navigationPath(
                    NavigationArgs(
                        Screen.USER_ID,
                        event.userId.toString(),
                    ),
                    NavigationArgs(
                        Screen.USER_EMAIL,
                        event.userEmail
                    )
                )
            )
        }
    }

    val state by viewModel.state.collectAsState()

    when (val typedState = state) {
        is AuthViewModel.State.Content -> Content(
            content = typedState,
            onValueChanged = viewModel::onValueChanged,
            onClick = viewModel::onClick,
            onDialogDismiss = viewModel::dismissError
        )

        AuthViewModel.State.Empty -> Unit
    }
}

@Composable
private fun Content(
    content: AuthViewModel.State.Content,
    onValueChanged: (TextFieldValue) -> Unit,
    onClick: () -> Unit,
    onDialogDismiss: () -> Unit
) {
    if (content.isLoading) LoaderDialog()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = null)
        OutlinedTextField(
            value = content.userInput,
            onValueChange = onValueChanged,
            label = { Text("UserId") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
        )
        LargeButtonPrimary(
            modifier = Modifier.padding(16.dp),
            text = stringResource(id = R.string.connect),
            onClick = onClick
        )
    }
    if (content.isOnError) {
        RetryDialog(
            titleRes = R.string.dialog_title,
            descriptionRes = R.string.dialog_description,
            onRetryClick = onDialogDismiss
        )
    }
}

@Composable
fun RetryDialog(
    @StringRes titleRes: Int,
    @StringRes descriptionRes: Int,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier,
    @DrawableRes titleIconRes: Int = com.google.android.material.R.drawable.mtrl_ic_error,
    onDismissRequest: () -> Unit = {}
) {
    PositionalDialog {
        AlertDialog(
            modifier = modifier
                .position(DialogPosition.BOTTOM),
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            ),
            shape = RoundedCornerShape(topEnd = 4.dp, topStart = 4.dp),
            onDismissRequest = onDismissRequest,
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = titleIconRes),
                        contentDescription = null,
                    )

                    Text(
                        modifier = Modifier.weight(1f),
                        text = stringResource(id = titleRes),
                    )
                    Icon(
                        modifier = Modifier
                            .size(24.dp)
                            .clickable(onClick = onRetryClick),
                        painter = painterResource(id = com.google.android.material.R.drawable.ic_m3_chip_close),
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = null
                    )
                }
            },
            text = {
                Text(
                    text = stringResource(id = descriptionRes),
                    color = MaterialTheme.colorScheme.primary
                )
            },
            confirmButton = {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        LargeButtonPrimary(
                            modifier
                                .padding(bottom = 16.dp)
                                .padding(horizontal = 16.dp),
                            onClick = onRetryClick,
                            text = stringResource(id = R.string.retry)
                        )
                    }
                }
            }
        )
    }
}