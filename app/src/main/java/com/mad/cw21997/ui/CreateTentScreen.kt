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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
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
            onValueChange = { createTentModel.updateName(it) },
            isError = uiState.nameError != null,
            supportingText = {uiState.nameError?.let { Text(it) }}
        )
        Spacer(Modifier.height(16.dp))

        Text("Brand:")
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = uiState.brand,
            onValueChange = { createTentModel.updateBrand(it) },
            isError = uiState.brandError != null,
            supportingText = {uiState.brandError?.let { Text(it) }}
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
                    value = uiState.capacity,
                    onValueChange = { createTentModel.updateCapacity(it) },
                    isError = uiState.capacityError != null,
                    supportingText = { uiState.capacityError?.let { Text(it) } },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
            Spacer(Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text("Weight (g):")
                TextField(
                    value = uiState.weight,
                    onValueChange = { createTentModel.updateWeight(it) },
                    isError = uiState.weightError != null,
                    supportingText = { uiState.weightError?.let { Text(it) } },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        }
        Spacer(Modifier.height(16.dp))

        Text("Water Proof (mm):")
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = uiState.waterProof,
            onValueChange = { createTentModel.updateWaterProof(it) },
            isError = uiState.waterProofError != null,
            supportingText = { uiState.waterProofError?.let { Text(it) } },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
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
            value = uiState.stock,
            onValueChange = { createTentModel.updateStock(it) },
            isError = uiState.stockError != null,
            supportingText = { uiState.stockError?.let { Text(it) } },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(Modifier.height(16.dp))

        Text("Image URL:")
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = uiState.imageUrl,
            onValueChange = { createTentModel.updateImageUrl(it) },
            isError = uiState.imageUrlError != null,
            supportingText = {uiState.imageUrlError?.let { Text(it) }}
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
                enabled = uiState.isFormValid
            ) {
                Text(text = if (uiState.editMode) "Update" else "Create")
            }

            Button(onClick = onCancelButtonClick) {
                Text(text = "Cancel")
            }
        }
    }
}
