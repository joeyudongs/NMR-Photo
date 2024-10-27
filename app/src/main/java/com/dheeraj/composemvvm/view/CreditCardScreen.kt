
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.dheeraj.composemvvm.model.Demo
import com.dheeraj.composemvvm.viewmodel.CreditCardViewModel

@Composable
fun CreditCardScreen(viewModel: CreditCardViewModel) {
    val creditCards by viewModel.creditCards.observeAsState(null)

    LaunchedEffect(Unit) {
        viewModel.fetchCreditCards()
    }

    Column {
        if (creditCards == null) {
            // Show loading indicator or placeholder
            Column(
                modifier = Modifier
                    .background(Color.Black, RectangleShape)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                LoadingAnimation()
            }
        } else {
            // Display the list of credit cards
            CreditCardItem(creditCards!!)
        }
    }
}



@Composable
fun MarsSection(item: Demo) {
    Box(

        modifier = Modifier.padding(start = 32.dp,top = 16.dp, end = 32.dp)

    ) {
        SubcomposeAsyncImage(
            model = item.img_src ,
            /*loading = {
                CircularProgressIndicator()
            },*/
            contentDescription = "",
            modifier = Modifier
                .requiredHeight(height = 220.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )
        /*Image(
            painter = painterResource(id = R.mipmap.video_play),
            "play icon",
            modifier = Modifier.align(Alignment.Center)
        )*/


        Text(
            text = ("The Martian Solar Day：" + item.sol) ?: "",
            fontSize = 14.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 12.dp, top = 8.dp, end = 12.dp)
        )
    }
    Text(
        text = "Rover: " + item.rover?.name.toString() + ", LandingDate: " + item.rover?.landing_date + ", Status: " + item.rover?.status,
        color = Color.White,
        modifier = Modifier

            .padding(start = 32.dp, top = 8.dp, end = 32.dp)
    )

    Text(
        text = "Camera: " + item.camera?.name.toString() + ", Earth Date: " + item.earth_date,
        color = Color.White,
        modifier = Modifier
            .padding(start = 32.dp, top = 8.dp, end = 32.dp)
    )

    Text(
        text = "Photo Id: " + item.photoId,
        color = Color.White,
        modifier = Modifier
            .padding(start = 32.dp, top = 8.dp, end = 32.dp)
    )
}

@Composable
fun CreditCardItem(creditCard: CreditCardResponse) {
    Card(
        backgroundColor = Color.Black,
        modifier = Modifier
            .fillMaxWidth(),
        elevation = 4.dp
    ) {

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(creditCard.photos) {
                when(it){
                    is Demo -> MarsSection(item = it)
                }
            }
        }


        /*
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "creditCard.photos",
                        style = MaterialTheme.typography.h6
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Expiry Date: ",
                        style = MaterialTheme.typography.body1
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "creditCard.credit_card_type",
                        style = MaterialTheme.typography.body1
                    )
                }*/
    }
}
