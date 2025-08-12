package com.animals.safety.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.animals.safety.R
import com.animals.safety.data.Animal
import com.animals.safety.data.AnimalData
import com.animals.safety.data.Breed
import com.animals.safety.ui.theme.AimantsDanimauxTheme
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
  modifier: Modifier = Modifier,
  animals: List<Animal> = AnimalData.animals,
  onAnimalClick: (Animal) -> Unit = {},
  onFABClick: () -> Unit = {},
) {
  Scaffold(
    modifier = modifier,
    topBar = {
      TopAppBar(
        title = {
          Text(stringResource(id = R.string.home_fragment_label))
        }
      )
    },
    floatingActionButtonPosition = FabPosition.End,
    floatingActionButton = {
      FloatingActionButton(
        onClick = {
          onFABClick()
        }
      ) {
        Icon(
          imageVector = Icons.Filled.Add,
          contentDescription = stringResource(id = R.string.contentDescription_add_animal)
        )
      }
    }
  ) { contentPadding ->
    HomeList(
      modifier = modifier.padding(contentPadding),
      animals = animals,
      onAnimalClick = onAnimalClick
    )
  }
}

@Composable
private fun HomeList(
  modifier: Modifier = Modifier,
  animals: List<Animal>,
  onAnimalClick: (Animal) -> Unit,
) {
  LazyColumn(modifier) {
    items(animals) { animal ->
      HomeCell(
        animal = animal,
        onAnimalClick = onAnimalClick
      )
      HorizontalDivider()
    }
  }
}

@Composable
private fun HomeCell(
  animal: Animal,
  onAnimalClick: (Animal) -> Unit,
) {
  //TODO: A compléter
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .height(80.dp)
      .clickable {
        onAnimalClick(animal)
      }
  ){
    Image(
      painter = painterResource(animal.breed.cover),
      contentDescription = "Image of the breed",
      contentScale = ContentScale.Fit,
      modifier = Modifier.size(80.dp)
    )
    Column{
      Text(text = animal.name,
          fontWeight = FontWeight.Bold,
          style = MaterialTheme.typography.titleMedium)

      Text(
        text = "${animal.breed}, ${animal.age} years old! " +
                "Its weight is ${animal.weight} kilograms and " +
                "its size is ${animal.height} centimeters.",
        maxLines = 3,
        style = MaterialTheme.typography.bodySmall,
        overflow = TextOverflow.Ellipsis
      )
    }
  }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
private fun HomeCellPreview() {
  AimantsDanimauxTheme(dynamicColor = false) {
    HomeCell(
      animal = Animal(UUID.randomUUID(),"Milou", Breed.DOG, 6, 23.2f, 42.4f),
      onAnimalClick = {}
    )
  }
}
