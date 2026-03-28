package com.mad.cw21997.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
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
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(tent.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = tent.name,
                placeholder = painterResource(R.drawable.tent1),
                error = painterResource(R.drawable.tent1),
                fallback = painterResource(R.drawable.tent1),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

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
                Text(
                    text = stringResource(R.string.type_format, tent.type),
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                Text(
                    text = stringResource(R.string.capacity_format, tent.capacity),
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
            Column(modifier = Modifier.padding(end = 16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.waterproof_format, tent.waterProof),
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                Text(
                    text = stringResource(R.string.weight_format, String.format("%.1f", tent.weight / 1000.0)),
                    modifier = Modifier.padding(vertical = 4.dp)
                )
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
                    Text(text = stringResource(R.string.edit_button))
                }
                Button(onClick = onDeleteButtonClick) {
                    Text(text = stringResource(R.string.delete_button))
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(onClick = increaseStock) {
                    Text(text = stringResource(R.string.increase_stock))
                }
                Text(text = tent.stock.toString(), modifier = Modifier.padding(8.dp))
                Button(onClick = decreaseStock) {
                    Text(text = stringResource(R.string.decrease_stock))
                }
            }
        }
    }
}
