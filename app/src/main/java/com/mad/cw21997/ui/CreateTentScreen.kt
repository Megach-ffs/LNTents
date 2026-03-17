package com.mad.cw21997.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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

@Composable
fun CreateTentForm(
    id: Int = 0,
    name: String = "",
    brand: String = "",
    capacity: Int = 0,
    waterProof: Int = 0,
    weight: Int = 0,
    type: String = "",
    stock: Int = 0,
    imageUrl: String = "",
    imageResourceId: Int = 0,
    onCreateButtonClick: () -> Unit = {},
    onCancelButtonClick: () -> Unit = {},
    modifier: Modifier = Modifier
){
    Column(modifier = modifier
//        .fillMaxWidth()
        .padding(16.dp)
        .verticalScroll(rememberScrollState())
//        .fillMaxHeight()
        .background(Color.White),
//        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Name:")
        TextField(modifier = Modifier.fillMaxWidth(),value = name, onValueChange = { /*TODO*/ })
        Spacer(Modifier.height(16.dp))
        Text("Brand:")
        TextField(modifier = Modifier.fillMaxWidth(),value = brand, onValueChange = { /*TODO*/ })
        Spacer(Modifier.height(16.dp))
        Row(modifier = Modifier
//            .padding(8.dp)
//            .background(color = Color.LightGray, shape = RoundedCornerShape(8.dp))
//            .height(64.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.width(150.dp),
//                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Capacity:")
                TextField(value = capacity.toString(), onValueChange = { /*TODO*/ })

            }

//            Spacer(Modifier.width(16.dp))
            Column(
                modifier = Modifier.width(150.dp),
            ) {
                Text("Weight:")
                TextField(value = weight.toString(), onValueChange = { /*TODO*/ })

            }
//            Spacer(Modifier.width(16.dp))


        }
        Spacer(Modifier.height(16.dp))

        Text("Water Proof: ")
        TextField(modifier=Modifier.fillMaxWidth(),value = waterProof.toString(), onValueChange = { /*TODO*/ })
        Spacer(Modifier.height(16.dp))

        Text("Tent Type: ")
        Row(
            modifier = Modifier.fillMaxWidth().padding(end=16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Row(verticalAlignment = Alignment.CenterVertically){
                RadioButton(selected = true, onClick = { /*TODO*/ })
                Text("Dome")
            }

            Row(verticalAlignment = Alignment.CenterVertically){
                RadioButton(selected = false, onClick = { /*TODO*/ })
                Text("Tunnel")
            }

            Row(verticalAlignment = Alignment.CenterVertically){
                RadioButton(selected = false, onClick = { /*TODO*/ })
                Text("Geodesic")
            }
        }
        Spacer(Modifier.height(16.dp))

        Text("Stock: ")
        TextField(modifier=Modifier.fillMaxWidth(),value = stock.toString(), onValueChange = { /*TODO*/ })
        Spacer(Modifier.height(16.dp))

        Text("Image:")
        TextField(modifier=Modifier.fillMaxWidth(),value = imageUrl, onValueChange = { /*TODO*/ })
        Spacer(Modifier.height(16.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = onCreateButtonClick) {
                Text(text = "Create")
            }

            Button(onClick = onCancelButtonClick) {
                Text(text = "Cancel")
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun CreateTentFormPreview(){
    CreateTentForm()
}