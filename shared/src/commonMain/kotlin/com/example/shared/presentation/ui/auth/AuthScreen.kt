package com.example.shared.presentation.ui.auth

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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.example.shared.presentation.ui.base.compose.DialogPosition
import com.example.shared.presentation.ui.base.compose.compenent.LargeButtonPrimary
import com.example.shared.presentation.ui.base.compose.compenent.LaunchedEffectFlowWithLifecycle
import com.example.shared.presentation.ui.base.compose.compenent.LoaderDialog
import com.example.shared.presentation.ui.base.compose.compenent.PositionalDialog
import com.example.shared.presentation.ui.base.composenavigation.Profile
import com.example.shared.presentation.ui.base.composenavigation.Screen
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import testapp.shared.generated.resources.Res
import testapp.shared.generated.resources.connect
import testapp.shared.generated.resources.dialog_description
import testapp.shared.generated.resources.dialog_title
import testapp.shared.generated.resources.ic_close
import testapp.shared.generated.resources.ic_foreground
import testapp.shared.generated.resources.mtrl_ic_error
import testapp.shared.generated.resources.retry

@Composable
fun AuthScreen(
    viewModel: AuthViewModel = koinViewModel<AuthViewModel>(),
    navController: NavController
) {

    LaunchedEffectFlowWithLifecycle(flow = viewModel.event) { event ->
        when (event) {
            is AuthViewModel.Event.Navigation.UserValidated -> navController.navigate(
                route = Screen.PostsScreen(
                    Profile(
                        userId = event.userId,
                        userEmail = event.userEmail
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
        Image(
            painter = painterResource(resource = Res.drawable.ic_foreground),
            contentDescription = null
        )
        OutlinedTextField(
            value = content.userInput,
            onValueChange = onValueChanged,
            label = { Text("UserId") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
        )
        LargeButtonPrimary(
            modifier = Modifier.padding(16.dp),
            text = stringResource(resource = Res.string.connect),
            onClick = onClick
        )
    }
    if (content.isOnError) {
        RetryDialog(
            titleRes = Res.string.dialog_title,
            descriptionRes = Res.string.dialog_description,
            onRetryClick = onDialogDismiss
        )
    }
}

@Composable
fun RetryDialog(
    titleRes: StringResource,
    descriptionRes: StringResource,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier,
    titleIconRes: DrawableResource = Res.drawable.mtrl_ic_error,
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
                        painter = painterResource(resource = titleIconRes),
                        contentDescription = null,
                    )

                    Text(
                        modifier = Modifier.weight(1f),
                        text = stringResource(resource = titleRes),
                    )
                    Icon(
                        modifier = Modifier
                            .size(24.dp)
                            .clickable(onClick = onRetryClick),
                        painter = painterResource(resource = Res.drawable.ic_close),
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = null
                    )
                }
            },
            text = {
                Text(
                    text = stringResource(resource = descriptionRes),
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
                            text = stringResource(resource = Res.string.retry)
                        )
                    }
                }
            }
        )
    }
}