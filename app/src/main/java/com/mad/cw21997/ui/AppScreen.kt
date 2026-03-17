package com.mad.cw21997.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mad.cw21997.R
import com.mad.cw21997.data.Tent
import com.mad.cw21997.ui.TentListViewModel
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


enum class AppScreen {
    TentList,
    CreateTent,
    DeleteTent
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    modifier: Modifier = Modifier
){
    TopAppBar(
        title = {
            Text(text = "Tents")
        },
        modifier = modifier,
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    )
}

@Composable
fun AppScreen(
    tentListViewModel: TentListViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
){
    Scaffold(
        topBar = { AppBar() },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(AppScreen.CreateTent.name) }) {
                Text(text = "Add")
            }
        },

    ) {
        innerPadding ->

        NavHost(
            navController = navController,
            startDestination = AppScreen.TentList.name,
            modifier = Modifier
                .fillMaxSize()
//                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ){
            composable(route = AppScreen.TentList.name){
                TentList(
                    tentList = tentListViewModel.tentList,
                    onEditButtonClick = { navController.navigate(AppScreen.CreateTent.name) },
                    onDeleteButtonClick = { navController.navigate(AppScreen.DeleteTent.name) },
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

            composable(route = AppScreen.CreateTent.name){
                CreateTentForm(
                    onCreateButtonClick = { navController.navigate(AppScreen.TentList.name) },
                    onCancelButtonClick = { navController.navigate(AppScreen.TentList.name) },
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

            composable(route = AppScreen.DeleteTent.name){
                DeleteTentForm(
                    onYesButtonClick = { navController.navigate(AppScreen.TentList.name) },
                    onNoButtonClick = { navController.navigate(AppScreen.TentList.name) },
                    modifier = Modifier
                )
            }
        }
//        TentList(
//            tentList = tentListViewModel.tentList,
//            modifier = Modifier.padding(innerPadding)
//        )
    }

}





@Composable
fun DeleteTentForm(
    onYesButtonClick: () -> Unit = {},
    onNoButtonClick: () -> Unit = {},
    modifier: Modifier = Modifier
){
    Column(
        modifier = Modifier
//            .fillMaxWidth()
            .padding(16.dp)
            .height(120.dp)
//            .verticalScroll(rememberScrollState()),
            .width(280.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Text("Are you sure you want to delete this tent?")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),

            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Button(onClick = onYesButtonClick) {
                Text(text = "Yes")
            }

            Button(onClick = onNoButtonClick) {
                Text(text = "No")
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun AppScreenPreview(){
    DeleteTentForm()
//    AppScreen()
//    CreateTentForm()

//    TentCard(
//        tent = Tent(
//            id = 1,
//            name = "Test Tent",
//            brand = "Test Brand",
//            capacity = 4,
//            waterProof = 3000,
//            weight = 1400,
//            type = "Dome",
//            stock = 10,
//            imageUrl = "",
//            imageResourceId = R.drawable.tent1
//        )
//    )
}
