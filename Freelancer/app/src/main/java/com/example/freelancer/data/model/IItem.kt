package com.example.freelancer.data.model

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

interface IItem {
    @Composable
    fun ListViewItem(
        navController: NavController,
        item: IItem,
        onItemClicked: (item: IItem) -> Unit
    )
    @Composable
    fun Details(
        item: IItem,
        navController: NavController?
    )
}