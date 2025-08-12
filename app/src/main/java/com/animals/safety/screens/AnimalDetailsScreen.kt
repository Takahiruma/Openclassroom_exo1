package com.animals.safety.screens

import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.animals.safety.R
import com.animals.safety.data.Animal
import com.animals.safety.data.Breed
import com.animals.safety.ui.theme.AimantsDanimauxTheme
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimalDetailsScreen(
  modifier: Modifier = Modifier,
  animal: Animal,
  onBackClick: () -> Unit,
) {
  Scaffold(
    modifier = modifier,
    topBar = {
      TopAppBar(
        title = {
          Text(stringResource(id = R.string.details_fragment_label))
        },
        navigationIcon = {
          IconButton(onClick = {
            onBackClick()
          }) {
            Icon(
              imageVector = Icons.AutoMirrored.Filled.ArrowBack,
              contentDescription = stringResource(id = R.string.contentDescription_go_back)
            )
          }
        }
      )
    },
  ) { contentPadding ->
    AnimalDetails(
      modifier = modifier.padding(contentPadding),
      animal = animal,
    )
  }
}

@Composable
private fun AnimalDetails(
  modifier: Modifier = Modifier,
  animal: Animal,
)
{
  //TODO: A compléter
  Column(
    modifier = Modifier.fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(screenHeight / 3)
    ) {
      Image(
        painter = painterResource(animal.breed.cover),
        contentDescription = "Image of the breed",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
      )
      Text(
        text = animal.name,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.titleMedium,
        color = Color.White,
        modifier = Modifier
          .align(Alignment.BottomStart)
          .padding(8.dp)
      )
    }

    Spacer(modifier = Modifier.height(16.dp))

    Row(
      horizontalArrangement = Arrangement.Center,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Column(modifier = Modifier.size(80.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
          painter = painterResource(R.drawable.ic_age),
          contentDescription = "Image of the Age",
          contentScale = ContentScale.Fit,
          modifier = Modifier.size(40.dp)
        )
        Text(text = "${animal.age} years old",
          style = MaterialTheme.typography.bodySmall,
          modifier = Modifier.padding(top = 4.dp))
      }
      Spacer(modifier = Modifier.width(16.dp))
      Column(modifier = Modifier.size(80.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
          painter = painterResource(R.drawable.ic_weight),
          contentDescription = "Image of the Weight",
          contentScale = ContentScale.Fit,
          modifier = Modifier.size(40.dp)
        )
        Text(text = "${animal.weight} kg",
          style = MaterialTheme.typography.bodySmall,
          modifier = Modifier.padding(top = 4.dp))
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    Column(modifier = Modifier.size(80.dp), horizontalAlignment = Alignment.CenterHorizontally) {
      Image(
        painter = painterResource(R.drawable.ic_height),
        contentDescription = "Image of the Height",
        contentScale = ContentScale.Fit,
        modifier = Modifier.size(40.dp)
      )
      Text(text = "${animal.height} cm",
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(top = 4.dp))
    }
  }



}

@Preview(showBackground = true)
@Composable
private fun AnimalDetailsPreview() {
  AimantsDanimauxTheme(dynamicColor = false) {
    AnimalDetails(
      animal = Animal(UUID.randomUUID(),"Milou", Breed.DOG, 6, 23.2f, 42.4f),
    )
  }
}