
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.klimpel.abschlussarbeitmodul3.ui.components.TopAppBarTitelBackArrow
import com.klimpel.abschlussarbeitmodul3.ui.theme.DeepRed
import com.klimpel.abschlussarbeitmodul3.ui.theme.LightBlueBackground
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.pokedexscreen.PokemonList
import com.klimpel.abschlussarbeitmodul3.ui.theme.layouts.pokedexscreen.Searchbar
import com.klimpel.abschlussarbeitmodul3.viewmodels.PokemonListViewModel
import com.klimpel.pokemonbattlefinal.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Pokedex(
    navController: NavController,
    viewModel: PokemonListViewModel = hiltViewModel(),
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBarTitelBackArrow(pageTitle = R.string.pokedex, navController) },
        containerColor = LightBlueBackground,
    ) { innerpadding ->
        Divider(thickness = 4.dp, color = DeepRed, modifier = Modifier.padding(innerpadding))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding)
        ) {

            Spacer(modifier = Modifier.height(30.dp))
            Searchbar(hint = stringResource(R.string.searchbarHint)) {
                viewModel.searchPokemonList(it)
            }
            Spacer(modifier = Modifier.height(30.dp))
            PokemonList(navController = navController)
        }
    }
}


