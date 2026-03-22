package com.mad.cw21997.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mad.cw21997.R
import com.mad.cw21997.data.Tent

@Composable
fun TentList(
    tentList: List<Tent>,
    onEditButtonClick: (Tent) -> Unit = {},
    onDeleteButtonClick: (Tent) -> Unit = {},
    increaseStock: (Tent) -> Unit = {},
    decreaseStock: (Tent) -> Unit = {},
    modifier: Modifier = Modifier
){
    LazyColumn(modifier = modifier) {
        items(tentList.size) { index ->
            val tent = tentList[index]
            TentCard(
                tent = tent,
                onEditButtonClick = { onEditButtonClick(tent) },
                onDeleteButtonClick = { onDeleteButtonClick(tent) },
                increaseStock = { increaseStock(tent) },
                decreaseStock = { decreaseStock(tent) }
            )
        }
    }
}

@Composable
fun TentCard(
    tent: Tent,
    modifier: Modifier = Modifier,
    onEditButtonClick: () -> Unit = {},
    onDeleteButtonClick: () -> Unit = {},
    increaseStock: () -> Unit = {},
    decreaseStock: () -> Unit = {}
    ){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(8.dp),
                clip = false
            )
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.tent1),
            contentDescription = null,
        )

        Text(
            modifier = Modifier.padding(top = 16.dp, start = 8.dp),
            text = tent.brand,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray
        )
        Text(
            modifier = Modifier.padding(start = 8.dp, bottom = 16.dp),
            text = tent.name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Row(modifier = Modifier
            .padding(8.dp)
            .background(color = Color.LightGray, shape = RoundedCornerShape(8.dp))
            .height(64.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column (modifier = Modifier.padding(start = 16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Type: ${tent.type}", modifier = Modifier.padding(vertical = 4.dp))
                Text(text = "Capacity: ${tent.capacity} Person", modifier = Modifier.padding(vertical = 4.dp))
            }
            Column(modifier = Modifier.padding(end = 16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Waterproof: ${tent.waterProof} mm", modifier = Modifier.padding(vertical = 4.dp))
                Text(text = "Weight: ${String.format("%.1f", tent.weight / 1000.0)} kg", modifier = Modifier.padding(vertical = 4.dp))
            }
        }

        Row(modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Row {
                Button(onClick = onEditButtonClick) {
                    Text(text = "Edit")
                }
                Button(onClick = onDeleteButtonClick) {
                    Text(text = "Delete")
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(onClick = increaseStock) {
                    Text(text = "+1")
                }
                Text(text = tent.stock.toString(), modifier = Modifier.padding(8.dp))
                Button(onClick = decreaseStock) {
                    Text(text = "-1")
                }
            }
        }
    }
}
