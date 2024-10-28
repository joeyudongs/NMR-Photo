
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.dheeraj.composemvvm.DetailActivity
import com.dheeraj.composemvvm.model.Demo
import com.dheeraj.composemvvm.viewmodel.MarsPhotoViewModel

@Composable
fun MarsPhotoScreen(viewModel: MarsPhotoViewModel) {
    val marsPhoto by viewModel.marsPhotoResponseLiveData.observeAsState(null)
    val errorMsg by viewModel.errorMessage.observeAsState(null)

    // Get the current context
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.fetchMarsPhoto()
    }

    LaunchedEffect(errorMsg) {
        errorMsg?.let {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
    }

    Column {
        if (marsPhoto == null) {
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
            MarsPhotoItem(marsPhoto!!)
        }
    }
}

@Composable
fun MarsSection(item: Demo) {
    val context = LocalContext.current

    Box(

        modifier = Modifier.padding(start = 32.dp,top = 16.dp, end = 32.dp)
            .clickable {

                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("detail_image_url",item.img_src)
                intent.putExtra("detail_photo_id",item.photoId)
                intent.putExtra("detail_sol",item.sol)
                intent.putExtra("detail_earth_date",item.earth_date)
                context.startActivity(intent)
            }

    ) {
        SubcomposeAsyncImage(
            model = item.img_src ,
            contentDescription = "",
            modifier = Modifier
                .requiredHeight(height = 220.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )

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
fun MarsPhotoItem(marsPhoto: MarsPhotoResponse) {
    Card(
        backgroundColor = Color.Black,
        modifier = Modifier
            .fillMaxWidth(),
        elevation = 4.dp,
        shape = RectangleShape
    ) {

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(marsPhoto.photos) {
                when(it){
                    is Demo -> MarsSection(item = it)
                }
            }
        }
    }
}
