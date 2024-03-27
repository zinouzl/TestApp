package com.example.testapp.ui.posts

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.domain.model.Post
import com.example.testapp.R
import com.example.testapp.ui.base.compose.compenent.LaunchedEffectFlowWithLifecycle
import com.example.testapp.ui.theme.TestAppTheme

@Composable
fun PostsScreen(
    viewModel: PostViewModel = viewModel(),
    navController: NavController
) {

    LaunchedEffectFlowWithLifecycle(viewModel.event) { event ->
        when (event) {
            PostViewModel.Event.Navigation.Back -> navController.popBackStack()
        }
    }

    val state by viewModel.state.collectAsState()

    when (val typedState = state) {
        is PostViewModel.State.Content -> Content(
            content = typedState,
            onBackClicked = viewModel::onBackClicked
        )

        PostViewModel.State.Empty -> Unit
    }

}

@Composable
private fun Content(
    content: PostViewModel.State.Content,
    onBackClicked: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Surface(
                modifier = Modifier
                    .height(64.dp)
                    .fillMaxWidth(),
                shadowElevation = 4.dp
            ) {
                Row(
                    modifier = Modifier.padding(start = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Icon(
                        modifier = Modifier
                            .clickable(onClick = onBackClicked),
                        painter = painterResource(id = com.google.android.material.R.drawable.ic_arrow_back_black_24),
                        contentDescription = null
                    )
                    Text(text = stringResource(id = R.string.post_title))
                }
            }
        }
    ) {
        MainScreen(
            content = content,
            modifier = Modifier.padding(it)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun MainScreen(
    content: PostViewModel.State.Content,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        stickyHeader {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .height(45.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(

                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Text(text = stringResource(id = R.string.user_name))
                    Text(text = content.userEmail)
                }
            }

        }
        items(content.postList) {
            postItem(post = it)
        }
    }
}

@Composable
fun postItem(post: Post) {
    Card(
        modifier = Modifier
            .padding(16.dp),
        shape = RoundedCornerShape(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(4.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        )
        {
            Text(
                modifier = Modifier.align(Alignment.Start),
                text = post.title,
                fontSize = 20.sp
            )
            Text(
                modifier = Modifier.align(Alignment.Start),
                text = post.body,
                fontSize = 12.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PostsScreenPreview() {
    TestAppTheme {
        Content(
            content = PostViewModel.State.Content(
                isLoading = false,
                userEmail = "bernie.hammond@example.com",
                postList = listOf(
                    Post(
                        userId = 9359,
                        id = 8588,
                        title = "noluisse",
                        body = "assueveritq not not not not not not"

                    ),
                    Post(
                        userId = 7529,
                        id = 8896,
                        title = "consul",
                        body = "doming donw donw dnow dnow dnow"
                    )
                )

            ), onBackClicked = {})
    }
}