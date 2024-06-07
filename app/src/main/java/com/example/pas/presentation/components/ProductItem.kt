package com.example.pas.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.pas.domain.model.Product

@Composable
fun ProductItem(product: Product, onClick: () -> Unit) {

    Surface(
        shape = RoundedCornerShape(15.dp),
        color = Color.White,
        modifier = Modifier
            .padding(4.dp)
            .clickable(onClick = onClick),
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Image(
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
                    .clip(RoundedCornerShape(10)),
                painter = rememberAsyncImagePainter(product.images[0]),
                contentDescription = "Product Image",
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(10.dp))

            Column {
                CardTitleAndDescription(title = product.title, description = product.description)

                Spacer(modifier = Modifier.height(4.dp))


            }
        }

    }
}

@Composable
fun CardTitleAndDescription(title: String, description: String) {
    Column(
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.W800),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.W100),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )


    }
}

@Preview
@Composable
fun PreviewProductItem() {
    ProductItem(
        product = Product(
            title = "Product title",
            category = "Category",
            description = "Description",
            discountPercentage = 10.00,
            id = 1,
            images = listOf("", ""),
            price = 20.00,
            rating = 4.5,
            thumbnail = ""
        )
    ) {

    }
}