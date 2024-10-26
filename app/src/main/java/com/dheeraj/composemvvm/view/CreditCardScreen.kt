import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
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
            Text(text = "Loading...")
        } else {
            // Display the list of credit cards
            CreditCardItem(creditCards!!)
        }
    }
}



@Composable
fun MarsSection(item: Demo) {
    Box(

        modifier = Modifier.padding(top = 5.dp)) {
        AsyncImage(
            model = item.img_src ,
            contentDescription = "",
            modifier = Modifier
                .requiredHeight(height = 220.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.FillHeight
        )
        /*Image(
            painter = painterResource(id = R.mipmap.video_play),
            "play icon",
            modifier = Modifier.align(Alignment.Center)
        )*/


        Text(
            text = item.earth_date ?: "",
            color = Color.White,
            modifier = Modifier.align(Alignment.BottomStart)
        )
        /*Text(
            text = item., color = Color.White, modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 8.dp)
        )*/
    }
}

@Composable
fun CreditCardItem(creditCard: CreditCardResponse) {
    Card(
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
