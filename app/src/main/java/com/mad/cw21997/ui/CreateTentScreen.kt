package com.mad.cw21997.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue


@Composable
fun CreateTentForm(
    onCreateButtonClick: () -> Unit = {},
    onCancelButtonClick: () -> Unit = {},
    createTentModel: CreateTentModel,
    modifier: Modifier = Modifier
){
    val uiState by createTentModel.uiState.collectAsState()
    
    Column(modifier = modifier
        .padding(16.dp)
        .verticalScroll(rememberScrollState())
        .background(Color.White)
    ) {
        Text("Name:")
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = uiState.name,
            onValueChange = { createTentModel.updateName(it) }
        )
        Spacer(Modifier.height(16.dp))

        Text("Brand:")
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = uiState.brand,
            onValueChange = { createTentModel.updateBrand(it) }
        )
        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text("Capacity:")
                TextField(
                    value = if (uiState.capacity == 0) "" else uiState.capacity.toString(),
                    onValueChange = { createTentModel.updateCapacity(it) }
                )
            }
            Spacer(Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text("Weight (g):")
                TextField(
                    value = if (uiState.weight == 0) "" else uiState.weight.toString(),
                    onValueChange = { createTentModel.updateWeight(it) }
                )
            }
        }
        Spacer(Modifier.height(16.dp))

        Text("Water Proof (mm):")
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = if (uiState.waterProof == 0) "" else uiState.waterProof.toString(),
            onValueChange = { createTentModel.updateWaterProof(it) }
        )
        Spacer(Modifier.height(16.dp))

        Text("Tent Type:")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            listOf("Dome", "Tunnel", "Geodesic").forEach { type ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = uiState.type == type,
                        onClick = { createTentModel.updateType(type) }
                    )
                    Text(type)
                }
            }
        }
        Spacer(Modifier.height(16.dp))

        Text("Stock:")
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = if (uiState.stock == 0) "" else uiState.stock.toString(),
            onValueChange = { createTentModel.updateStock(it) }
        )
        Spacer(Modifier.height(16.dp))

        Text("Image URL:")
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = uiState.imageUrl,
            onValueChange = { createTentModel.updateImageUrl(it) }
        )
        Spacer(Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { 
                    createTentModel.saveTent(onSuccess = {
                        onCreateButtonClick()
                    })
//                    onCreateButtonClick()
                },
                enabled = uiState.name.isNotBlank() && uiState.brand.isNotBlank()
            ) {
                Text(text = if (uiState.editMode) "Update" else "Create")
            }

            Button(onClick = onCancelButtonClick) {
                Text(text = "Cancel")
            }
        }
    }
}
