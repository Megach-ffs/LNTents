package com.mad.cw21997.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
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


@Composable
fun AppScreen(
    tentListViewModel: TentListViewModel = viewModel()
){

    TentList(tentList = tentListViewModel.tentList)

}

@Composable
fun TentList(
    tentList: List<Tent>,
    modifier: Modifier = Modifier
){
    LazyColumn(modifier = modifier.background(Color.White)) {
        items(tentList.size) { tent ->
            TentCard(tent = tentList[tent])
        }
    }
}

@Composable
fun TentCard(tent: Tent, modifier: Modifier = Modifier){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp) // Give it room to breathe
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(8.dp),
                clip = false
            )
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(8.dp) // Inner padding
    ) {
        Image(
            painter = painterResource(tent.imageResourceId),
            contentDescription = null,
        )



        Text(
            modifier = modifier.padding(top = 16.dp, start = 8.dp),
            text = tent.brand,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray
        )
        Text(
            modifier = modifier.padding(start = 8.dp, bottom = 16.dp),
            text = tent.name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        // Details
        Row(modifier = modifier
            .padding(8.dp)
            .background(color = Color.LightGray, shape = RoundedCornerShape(8.dp))
            .height(64.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column (modifier = Modifier
//                .fillMaxWidth()
                .padding(start = 16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Type: ${tent.type}", modifier = Modifier.padding(vertical = 4.dp))
                Text(text = "Capacity: ${tent.capacity} Person", modifier = Modifier.padding(vertical = 4.dp))
            }
            Column(modifier = Modifier
//                .fillMaxWidth()
                .padding(end = 16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Waterproof: ${tent.waterProof} mm", modifier = Modifier.padding(vertical = 4.dp))
                Text(text = "Weight: ${String.format("%.1f", tent.weight / 1000.0)} kg", modifier = Modifier.padding(vertical = 4.dp))
            }
        }

        Row(modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Row() {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Edit")
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Delete")
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "+1")
                }
                Text(text = tent.stock.toString(), modifier = modifier.padding(8.dp))
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "-1")
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppScreenPreview(){
    AppScreen()
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
