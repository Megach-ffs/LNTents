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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mad.cw21997.R
import com.mad.cw21997.data.Tent

enum class AppScreen {
    TentList,
    CreateTent,
//    DeleteTent
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title: String,
    modifier: Modifier = Modifier
){
    TopAppBar(
        title = {
            Text(text = title)
        },
        modifier = modifier,
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    )
}

@Composable
fun AppScreen(
    tentListViewModel: TentListViewModel = viewModel(factory = TentListViewModel.Factory),
    createTentModel: CreateTentModel = viewModel(factory = CreateTentModel.Factory),
    navController: NavHostController = rememberNavController()
) {

    val tentListUiState by tentListViewModel.uiState.collectAsState()
    val createTentUiState by createTentModel.uiState.collectAsState()
    

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // State to hold the tent being deleted
    var tentToDelete by remember { mutableStateOf<Tent?>(null) }

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        tentListViewModel.userMessage.collect { message ->
            snackbarHostState.showSnackbar(message)
        }
    }

    LaunchedEffect(Unit) {
        createTentModel.userMessage.collect { message ->
            snackbarHostState.showSnackbar(message)
        }
    }

    Scaffold(
        topBar = {
            val title = when (currentRoute) {


                AppScreen.CreateTent.name -> if (createTentUiState.editMode) stringResource(R.string.app_bar_title_edit_tent) else stringResource(R.string.app_bar_title_create_tent)
//              AppScreen.DeleteTent.name -> "Delete Tent"
                else -> stringResource(R.string.app_bar_title_tents)
            }
            AppBar(title = title)
        },
        floatingActionButton = {

                FloatingActionButton(onClick = { 
                    createTentModel.clearForm()
                    navController.navigate(AppScreen.CreateTent.name) 
                }) {
                    Text(text = stringResource(R.string.fab_add))
                }

//                FloatingActionButton(onClick = {
//                    tentListViewModel.fetchData()
//                    navController.navigate(AppScreen.TentList.name)
//                }){
//                    Text(text="Refresh")
//                }

        },

        snackbarHost =  { SnackbarHost(hostState = snackbarHostState) }

        ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = AppScreen.TentList.name,
            modifier = Modifier
                .fillMaxSize()
//                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            composable(route = AppScreen.TentList.name) {

                when (val state = tentListUiState) {
                    is TentListUIState.Loading -> {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text(text = stringResource(R.string.loading_text))
                        }
                    }

                    is TentListUIState.Error -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = stringResource(R.string.error_loading_text))
                        }
                    }

                    is TentListUIState.Success -> {
                        TentList(
                            tentList = state.tentList,
                            onEditButtonClick = { tent ->
                                createTentModel.initiateEdit(tent)
                                navController.navigate(AppScreen.CreateTent.name)
                            },
                            onDeleteButtonClick = { tent -> 
                                tentToDelete = tent
//                                navController.navigate(AppScreen.DeleteTent.name)
                            },
                            increaseStock = { tent ->
                                tentListViewModel.increaseStock(tent)
//                                navController.navigate(AppScreen.TentList.name)
                            },
                            decreaseStock = { tent ->
                                tentListViewModel.decreaseStock(tent)
//                                navController.navigate(AppScreen.TentList.name)
                            },
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }

            composable(route = AppScreen.CreateTent.name) {
                CreateTentForm(
                    onCreateButtonClick = {
                        navController.popBackStack()
                        tentListViewModel.fetchData()
                    },
                    onCancelButtonClick = {
                        createTentModel.clearForm()
                        navController.popBackStack() 
                    },
                    createTentModel = createTentModel,
                    modifier = Modifier.fillMaxSize()
                )
            }

//            composable(route = AppScreen.DeleteTent.name) {
//                DeleteTentForm(
//                    onYesButtonClick = {
//                        tentToDelete?.let { tent ->
//                            tentListViewModel.deleteTent(tent)
//                        }
//                        navController.navigate(AppScreen.TentList.name)
//                    },
//                    onNoButtonClick = {
//                        tentToDelete = null
//                        navController.navigate(AppScreen.TentList.name)
//                    },
//                    modifier = Modifier.fillMaxSize()
//                )
//            }
        }

        if (tentToDelete != null) {
            AlertDialog(
                onDismissRequest = {
                    tentToDelete = null
                },
                title = {
                    Text(text = stringResource(R.string.delete_dialog_title))
                },
                text = {
                    Text(text = stringResource(R.string.delete_dialog_text))
                },
                confirmButton = {
                    Button(onClick = {
                        tentToDelete?.let { tent ->
                            tentListViewModel.deleteTent(tent)
                        }
                        tentToDelete = null
                    }) { 
                        Text(text = stringResource(R.string.yes_button))
                    }
                },
                dismissButton = {
                    Button(onClick = { 
                        tentToDelete = null
                    }) {
                        Text(text = stringResource(R.string.no_button))
                    }

                }
            )
        }
    }
}

@Composable
fun DeleteTentForm(
    onYesButtonClick: () -> Unit = {},
    onNoButtonClick: () -> Unit = {},
    modifier: Modifier = Modifier
){
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .width(280.dp)
                .background(Color.White, shape = MaterialTheme.shapes.medium)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Are you sure you want to delete this tent?")
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = onYesButtonClick) {
                    Text(text = "Yes")
                }
                Button(onClick = onNoButtonClick) {
                    Text(text = "No")
                }
            }
        }
    }
}
