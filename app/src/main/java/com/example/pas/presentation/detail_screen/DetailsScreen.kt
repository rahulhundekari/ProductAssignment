package com.example.pas.presentation.detail_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.pas.R
import com.example.pas.domain.model.Product

@Composable
fun DetailsScreen(navController: NavHostController, product: Product) {


    Scaffold { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            // Image Section
            ImageSection(product, navController)

            Column(
                modifier = Modifier.padding(horizontal = 15.dp)
            ) {
                // title
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = product.title,
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(20.dp))

                Surface(
                    color = Color.White,
                    shape = RoundedCornerShape(10.dp),
                    shadowElevation = 4.dp
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Category:",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Text(
                            text = product.category,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
//                Row(
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
////                    Text(
////                        text = "Category",
////                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
////                    )
//
//                    Spacer(modifier = Modifier.width(10.dp))
//
//                    SingleEntityHighlight(
//                        icon = painterResource(id = R.drawable.ic_category),
//                        data = product.category
//                    )
//                }
//                Row(
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.ic_category),
//                        contentDescription = "Category"
//                    )
//
//                    Text(
//                        text = "Category: ",
//                        style = MaterialTheme.typography.bodyLarge
//                    )
//
//                    Spacer(modifier = Modifier.width(10.dp))
//                    Text(
//                        text = product.category,
//                        style = MaterialTheme.typography.bodyLarge
//                    )
//
//                }

                Spacer(modifier = Modifier.height(10.dp))

                Row {
                    SingleEntityHighlight(
                        icon = painterResource(id = R.drawable.ic_rupee),
                        data = "${product.price}"
                    )

                    Spacer(modifier = Modifier.width(20.dp))
                    SingleEntityHighlight(
                        icon = painterResource(id = R.drawable.ic_half_star),
                        data = "${product.rating}"
                    )
                }
                // Description
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Description",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                )

                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = product.description,
                    style = MaterialTheme.typography.bodyLarge,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }


}

@Composable
private fun ImageSection(
    product: Product,
    navController: NavController
) {
    var currentImage by remember {
        mutableIntStateOf(0)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp),
            painter = rememberAsyncImagePainter(product.images[currentImage]),
            contentDescription = "Product Image",
            contentScale = ContentScale.Crop
        )


        Box(
            modifier = Modifier
                .padding(start = 10.dp, top = 40.dp)
                .align(Alignment.TopStart)
                .clickable {
                    navController.popBackStack()
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.arrow_back_24),
                contentDescription = "Back Icon",
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White)
                    .padding(8.dp),

                )
        }

        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 20.dp)
                .align(Alignment.BottomCenter)
        ) {
            for ((index, images) in product.images.withIndex()) {
                Image(
                    modifier = Modifier
                        .size(80.dp)
                        .padding(4.dp)
                        .border(
                            width = 2.dp,
                            color = if (currentImage == index) MaterialTheme.colorScheme.primary else Color.White,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clip(RoundedCornerShape(10.dp))
                        .clickable { currentImage = index },
                    painter = rememberAsyncImagePainter(images),
                    contentDescription = "Image",
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Composable
fun SingleEntityHighlight(
    icon: Painter,
    data: String
) {
    Surface(
        color = Color.White,
        shape = RoundedCornerShape(10.dp),
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(30.dp),
                painter = icon,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(6.dp))

            Text(
                text = data,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Preview
@Composable
fun PreviewDetailsScreen() {
    DetailsScreen(
        navController = rememberNavController(), product = Product(
            title = "Product title",
            category = "Category",
            description = "Description",
            discountPercentage = 10.00,
            id = 1,
            images = listOf("",""),
            price = 20.00,
            rating = 4.5,
            thumbnail = ""
        )
    )
}