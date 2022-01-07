package com.example.freelancer.ui.screens.list

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.example.freelancer.data.model.IItem
import com.example.freelancer.ui.screens.TopBar
import com.example.freelancer.ui.theme.PrimaryColor
import com.example.freelancer.ui.theme.PrimaryVariant
import com.example.freelancer.ui.viewmodel.BaseViewModel
import kotlinx.coroutines.launch


@ExperimentalFoundationApi
@Composable
fun MainList(
    navController: NavController,
    viewModel: BaseViewModel,
    owner: LifecycleOwner,
    title: String
) {
    LaunchedEffect(Unit) {
        viewModel.fetch()
    }



    MainHeader(title = title)

    if (viewModel.list.value==null) {
        LoadingComponent()
        return
    }

    List(
        navController = navController, list = viewModel.list,
        onItemClicked = (viewModel::itemClicked),
        owner = owner
    )
}


@ExperimentalFoundationApi
@Composable
fun List(
    navController: NavController,
    list: LiveData<List<IItem>>,
    onItemClicked: (IItem) -> Unit,
    owner: LifecycleOwner
) {
    val listState = rememberLazyListState()

    Spacer(Modifier.size(200.dp))
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyRow(
            state = listState,
            verticalAlignment = Alignment.Bottom
        )
        {
            list.observe(owner, Observer<List<IItem>> { list ->
                itemsIndexed(list) { _, item ->
                    item.ListViewItem(navController = navController, item = item, onItemClicked)
                    Log.d("list", "Success ${list.size}")

                }
            })
        }
    }
}


@Composable
fun MainHeader(title: String) {
    Surface(
        Modifier
            .fillMaxWidth()
            .background(PrimaryVariant)

    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h4,
            textAlign = TextAlign.Center,
            fontSize = 40.sp,
            color = Color.White,
            modifier = Modifier
                .background(PrimaryVariant)
                .padding(10.dp)

        )
    }
}

@Composable
fun LoadingComponent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(modifier = Modifier.wrapContentWidth(CenterHorizontally))
    }
}
