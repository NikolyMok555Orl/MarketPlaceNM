package com.example.marketplacenm.home.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.marketplacenm.ui.theme.MarketPlaceNMTheme

@Composable
fun ItemBrandUI(brand: Brand, modifier: Modifier=Modifier){
    AsyncImage(model = brand.imageUrl, contentDescription = brand.title, modifier = modifier.size(114.dp,149.dp).clip(
        RoundedCornerShape(9.dp)
    ))
}

@Preview(showBackground = true)
@Composable
private fun ItemLatestUIPreivew(){
    MarketPlaceNMTheme() {
        ItemBrandUI(  Brand("Adidas", "https://rikkir-sport.ru/wa-data/public/shop/brands/562/562.jpg"),)
    }
}


fun getBrands() = listOf<Brand>(
    Brand("Adidas", "https://rikkir-sport.ru/wa-data/public/shop/brands/562/562.jpg"),
    Brand("BMW", "https://static.tildacdn.com/tild3366-6533-4562-a461-663732646137/100023608402b0.jpg"),
    Brand("Xiaomi", "https://21vekrus.ru/wp-content/uploads/logotip-firmy-xiaomi.jpg"),
    Brand("Tesla", "https://kolesa-uploads.ru/-/6b808ab6-4b62-44d0-bf05-c0a1d12cc690/autowpru-tesla-logo-1-1.jpg"),
)

data class Brand(val title: String, val imageUrl: String)