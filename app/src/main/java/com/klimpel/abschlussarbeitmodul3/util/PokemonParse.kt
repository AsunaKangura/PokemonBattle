package com.klimpel.abschlussarbeitmodul3.util

import androidx.compose.ui.graphics.Color
import com.asunakangura.pokemonbattle.data.remote.responses.Stat
import com.asunakangura.pokemonbattle.data.remote.responses.Type
import com.google.android.play.integrity.internal.w
import com.klimpel.abschlussarbeitmodul3.ui.theme.AtkColor
import com.klimpel.abschlussarbeitmodul3.ui.theme.DefColor
import com.klimpel.abschlussarbeitmodul3.ui.theme.HPColor
import com.klimpel.abschlussarbeitmodul3.ui.theme.SpAtkColor
import com.klimpel.abschlussarbeitmodul3.ui.theme.SpDefColor
import com.klimpel.abschlussarbeitmodul3.ui.theme.SpdColor
import com.klimpel.abschlussarbeitmodul3.ui.theme.TypeBug
import com.klimpel.abschlussarbeitmodul3.ui.theme.TypeDark
import com.klimpel.abschlussarbeitmodul3.ui.theme.TypeDragon
import com.klimpel.abschlussarbeitmodul3.ui.theme.TypeElectric
import com.klimpel.abschlussarbeitmodul3.ui.theme.TypeFairy
import com.klimpel.abschlussarbeitmodul3.ui.theme.TypeFighting
import com.klimpel.abschlussarbeitmodul3.ui.theme.TypeFire
import com.klimpel.abschlussarbeitmodul3.ui.theme.TypeFlying
import com.klimpel.abschlussarbeitmodul3.ui.theme.TypeGhost
import com.klimpel.abschlussarbeitmodul3.ui.theme.TypeGrass
import com.klimpel.abschlussarbeitmodul3.ui.theme.TypeGround
import com.klimpel.abschlussarbeitmodul3.ui.theme.TypeIce
import com.klimpel.abschlussarbeitmodul3.ui.theme.TypeNormal
import com.klimpel.abschlussarbeitmodul3.ui.theme.TypePoison
import com.klimpel.abschlussarbeitmodul3.ui.theme.TypePsychic
import com.klimpel.abschlussarbeitmodul3.ui.theme.TypeRock
import com.klimpel.abschlussarbeitmodul3.ui.theme.TypeSteel
import com.klimpel.abschlussarbeitmodul3.ui.theme.TypeWater
import java.util.Locale

fun parsePokemonNameToGerman(name: String) : String{
    return when(name){
        "bulbasaur" -> "Bisasam"
        "ivysaur" -> "Bisaknosp"
        "venusaur" -> "Bisaflor"
        "charmander" -> "Glumanda"
        "charmeleon" -> "Glutexo"
        "charizard" -> "Glurak"
        "squirtle" -> "Schiggy"
        "wartortle" -> "Schillok"
        "blastoise" -> "Turtok"
        "caterpie" -> "Raupy"
        "metapod" -> "Safcon"
        "butterfree" -> "Smettbo"
        "weedle" -> "Hornliu"
        "kakuna" -> "Kokuna"
        "beedrill" -> "Bibor"
        "pidgey" -> "Taubsi"
        "pidgeotto" -> "Tauboga"
        "pidgeot" -> "Tauboss"
        "rattata" -> "Rattfratz"
        "raticate" -> "Rattikarl"
        "spearow" -> "Habitak"
        "fearow" -> "Ibitak"
        "ekans" -> "Rettan"
        "arbok" -> "Arbok"
        "pikachu" -> "Pikachu"
        "raichu" -> "Raichu"
        "sandshrew" -> "Sandan"
        "sandslash" -> "Sandamer"
        "nidoran-f" -> "Nidoran"
        "nidorina" -> "Nidorina"
        "nidoqueen" -> "Nidoqueen"
        "nidoran-m" -> "Nidoran"
        "nidorino" -> "Nidorino"
        "nidoking" -> "Nidoking"
        "clefairy" -> "Piepi"
        "clefable" -> "Pixi"
        "vulpix" -> "Vulpix"
        "ninetales" -> "Vulnona"
        "jigglypuff" -> "Pummeluff"
        "wigglytuff" -> "Knuddeluff"
        "zubat" -> "Zubat"
        "golbat" -> "Golbat"
        "oddish" -> "Myrapla"
        "gloom" -> "Duflor"
        "vileplume" -> "Giflor"
        "paras" -> "Paras"
        "parasect" -> "Parasect"
        "venonat" -> "Bluzuk"
        "venomoth" -> "Omot"
        "diglett" -> "Digda"
        "dugtrio" -> "Digdri"
        "meowth" -> "Mauzi"
        "persian" -> "Snobilikat"
        "psyduck" -> "Enton"
        "golduck" -> "Entoron"
        "mankey" -> "Menki"
        "primeape" -> "Rasaff"
        "growlithe" -> "Fukano"
        "arcanine" -> "Arkani"
        "poliwag" -> "Quapsel"
        "poliwhirl" -> "Quaputzi"
        "poliwrath" -> "Quappo"
        "abra" -> "Abra"
        "kadabra" -> "Kadabra"
        "alakazam" -> "Simsala"
        "machop" -> "Machollo"
        "machoke" -> "Maschock"
        "machamp" -> "Machomei"
        "bellsprout" -> "Knofensa"
        "weepinbell" -> "Ultrigaria"
        "victreebel" -> "Sarzenia"
        "tentacool" -> "Tentacha"
        "tentacruel" -> "Tentoxa"
        "geodude" -> "Kleinstein"
        "graveler" -> "Georok"
        "golem" -> "Geowaz"
        "ponyta" -> "Ponita"
        "rapidash" -> "Gallopa"
        "slowpoke" -> "Flegmon"
        "slowbro" -> "Lahmus"
        "magnemite" -> "Magnetilo"
        "magneton" -> "Magneton"
        "farfetchd" -> "Porenta"
        "doduo" -> "Dodu"
        "dodrio" -> "Dodri"
        "seel" -> "Jurob"
        "dewgong" -> "Jugong"
        "grimer" -> "Sleima"
        "muk" -> "Sleimok"
        "shellder" -> "Muschas"
        "cloyster" -> "Austos"
        "gastly" -> "Nebulak"
        "haunter" -> "Alpollo"
        "gengar" -> "Gengar"
        "onix" -> "Onix"
        "drowzee" -> "Traumato"
        "hypno" -> "Hypno"
        "krabby" -> "Krabby"
        "kingler" -> "Kingler"
        "voltorb" -> "Voltobal"
        "electrode" -> "Lektrobal"
        "exeggcute" -> "Owei"
        "exeggutor" -> "Kokowei"
        "cubone" -> "Tragosso"
        "marowak" -> "Knogga"
        "hitmonlee" -> "Kicklee"
        "hitmonchan" -> "Nockchan"
        "lickitung" -> "Schlurp"
        "koffing" -> "Smogon"
        "weezing" -> "Smogmog"
        "rhyhorn" -> "Rihorn"
        "rhydon" -> "Rizeros"
        "chansey" -> "Chaneira"
        "tangela" -> "Tangela"
        "kangaskhan" -> "Kangama"
        "horsea" -> "Seeper"
        "seadra" -> "Seemon"
        "goldeen" -> "Goldini"
        "seaking" -> "Golking"
        "staryu" -> "Sterndu"
        "starmie" -> "Starmie"
        "mr-mime" -> "Pantimos"
        "scyther" -> "Sichlor"
        "jynx" -> "Rossana"
        "electabuzz" -> "Elektek"
        "magmar" -> "Magmar"
        "pinsir" -> "Pinsir"
        "tauros" -> "Tauros"
        "magikarp" -> "Karpador"
        "gyarados" -> "Garados"
        "lapras" -> "Lapras"
        "ditto" -> "Ditto"
        "eevee" -> "Evoli"
        "vaporeon" -> "Aquana"
        "jolteon" -> "Blitza"
        "flareon" -> "Flamara"
        "porygon" -> "Porygon"
        "omanyte" -> "Amonitas"
        "omastar" -> "Amoroso"
        "kabuto" -> "Kabuto"
        "kabutops" -> "Kabutops"
        "aerodactyl" -> "Aerodactyl"
        "snorlax" -> "Relaxo"
        "articuno" -> "Arktos"
        "zapdos" -> "Zapdos"
        "moltres" -> "Lavados"
        "dratini" -> "Dratini"
        "dragonair" -> "Dragonair"
        "dragonite" -> "Dragoran"
        "mewtwo" -> "Mewtwo"
        "mew" -> "Mew"
        // 151 fertig
        else -> { name }
    }
}


fun parsePokemonNameToEnglish(name: String) : String{
    return when(name){
        "feuer" -> "fire"
        "wasser" -> "water"
        "normal" -> "normal"
        "elektro" -> "electric"
        "pflanze" -> "grass"
        "eis" -> "ice"
        "kampf" -> "fighting"
        "gift" -> "poison"
        "boden" -> "ground"
        "flug" -> "flying"
        "psycho" -> "psychic"
        "käfer" -> "bug"
        "gestein" -> "rock"
        "geist" -> "ghost"
        "drachen" -> "dragon"
        "unlicht" -> "dark"
        "stahl" -> "steel"
        "fee" -> "fairy"
        "bisasam" -> "bulbasaur"
        "bisaknosp" -> "ivysaur"
        "bisaflor" -> "venusaur"
        "glumanda" -> "charmander"
        "glutexo" -> "charmeleon"
        "glurak" -> "charizard"
        "schiggy" -> "squirtle"
        "schillok" -> "wartortle"
        "turtok" -> "blastoise"
        "raupy" -> "caterpie"
        "safcon" -> "metapod"
        "smettbo" -> "butterfree"
        "hornliu" -> "weedle"
        "kokuna" -> "kakuna"
        "bibor" -> "beedrill"
        "taubsi" -> "pidgey"
        "tauboga" -> "pidgeotto"
        "tauboss" -> "pidgeot"
        "rattfratz" -> "rattata"
        "rattikarl" -> "raticate"
        "habitak" -> "spearow"
        "ibitak" -> "fearow"
        "rettan" -> "ekans"
        "arbok" -> "arbok"
        "pikachu" -> "pikachu"
        "raichu" -> "raichu"
        "sandan" -> "sandshrew"
        "sandamer" -> "sandslash"
        "nidoran" -> "nidoran-f"
        "nidorina" -> "nidorina"
        "nidoqueen" -> "nidoqueen"
        "nidoran" -> "nidoran-m"
        "nidorino" -> "nidorino"
        "nidoking" -> "nidoking"
        "piepi" -> "clefairy"
        "pixi" -> "clefable"
        "vulpix" -> "vulpix"
        "vulnona" -> "ninetales"
        "pummeluff" -> "jigglypuff"
        "knuddeluff" -> "wigglytuff"
        "zubat" -> "zubat"
        "golbat" -> "golbat"
        "myrapla" -> "oddish"
        "duflor" -> "gloom"
        "giflor" -> "vileplume"
        "paras" -> "paras"
        "parasect" -> "parasect"
        "bluzuk" -> "venonat"
        "omot" -> "venomoth"
        "digda" -> "diglett"
        "digdri" -> "dugtrio"
        "mauzi" -> "meowth"
        "snobilikat" -> "persian"
        "enton" -> "psyduck"
        "entoron" -> "golduck"
        "menki" -> "mankey"
        "rasaff" -> "primeape"
        "fukano" -> "growlithe"
        "arkani" -> "arcanine"
        "quapsel" -> "poliwag"
        "quaputzi" -> "poliwhirl"
        "quappo" -> "poliwrath"
        "abra" -> "abra"
        "kadabra" -> "kadabra"
        "simsala" -> "alakazam"
        "machollo" -> "machop"
        "maschock" -> "machoke"
        "machomei" -> "machamp"
        "knofensa" -> "bellsprout"
        "ultrigaria" -> "weepinbell"
        "sarzenia" -> "victreebel"
        "tentacha" -> "tentacool"
        "tentoxa" -> "tentacruel"
        "kleinstein" -> "geodude"
        "georok" -> "graveler"
        "geowaz" -> "golem"
        "ponita" -> "ponyta"
        "gallopa" -> "rapidash"
        "flegmon" -> "slowpoke"
        "lahmus" -> "slowbro"
        "magnetilo" -> "magnemite"
        "magneton" -> "magneton"
        "porenta" -> "farfetchd"
        "dodu" -> "doduo"
        "dodri" -> "dodrio"
        "jurob" -> "seel"
        "jugong" -> "dewgong"
        "sleima" -> "grimer"
        "sleimok" -> "muk"
        "muschas" -> "shellder"
        "austos" -> "cloyster"
        "nebulak" -> "gastly"
        "alpollo" -> "haunter"
        "gengar" -> "gengar"
        "onix" -> "onix"
        "traumato" -> "drowzee"
        "hypno" -> "hypno"
        "krabby" -> "krabby"
        "kingler" -> "kingler"
        "voltobal" -> "voltorb"
        "lektrobal" -> "electrode"
        "owei" -> "exeggcute"
        "kokowei" -> "exeggutor"
        "tragosso" -> "cubone"
        "knogga" -> "marowak"
        "kicklee" -> "hitmonlee"
        "nockchan" -> "hitmonchan"
        "schlurp" -> "lickitung"
        "smogon" -> "koffing"
        "smogmog" -> "weezing"
        "rihorn" -> "rhyhorn"
        "rizeros" -> "rhydon"
        "chaneira" -> "chansey"
        "tangela" -> "tangela"
        "kangama" -> "kangaskhan"
        "seeper" -> "horsea"
        "seemon" -> "seadra"
        "goldini" -> "goldeen"
        "golking" -> "seaking"
        "sterndu" -> "staryu"
        "starmie" -> "starmie"
        "pantimos" -> "mr-mime"
        "sichlor" -> "scyther"
        "rossana" -> "jynx"
        "elektek" -> "electabuzz"
        "magmar" -> "magmar"
        "pinsir" -> "pinsir"
        "tauros" -> "tauros"
        "larpador" -> "magikarp"
        "garados" -> "gyarados"
        "lapras" -> "lapras"
        "ditto" -> "ditto"
        "evoli" -> "eevee"
        "aquana" -> "vaporeon"
        "blitza" -> "jolteon"
        "flamara" -> "flareon"
        "porygon" -> "porygon"
        "amonitas" -> "omanyte"
        "amoroso" -> "omastar"
        "kabuto" -> "kabuto"
        "kabutops" -> "kabutops"
        "aerodactyl" -> "aerodactyl"
        "relaxo" -> "snorlax"
        "arktos" -> "articuno"
        "zapdos" -> "zapdos"
        "lavados" -> "moltres"
        "dratini" -> "dratini"
        "dragonair" -> "dragonair"
        "dragoran" -> "dragonite"
        "mewtwo" -> "mewtwo"
        "mew" -> "mew"
        else -> { name }
    }
}

fun parseTypeToColor(type: Type): Color {
    return when(type.type.name.lowercase(Locale.ROOT)) {
        "normal" -> TypeNormal
        "fire" -> TypeFire
        "water" -> TypeWater
        "electric" -> TypeElectric
        "grass" -> TypeGrass
        "ice" -> TypeIce
        "fighting" -> TypeFighting
        "poison" -> TypePoison
        "ground" -> TypeGround
        "flying" -> TypeFlying
        "psychic" -> TypePsychic
        "bug" -> TypeBug
        "rock" -> TypeRock
        "ghost" -> TypeGhost
        "dragon" -> TypeDragon
        "dark" -> TypeDark
        "steel" -> TypeSteel
        "fairy" -> TypeFairy
        else -> Color.Black
    }
}
fun parseTypeToColor2(type: String): Color {
    return when(type) {
        "normal" -> TypeNormal
        "fire" -> TypeFire
        "water" -> TypeWater
        "electric" -> TypeElectric
        "grass" -> TypeGrass
        "ice" -> TypeIce
        "fighting" -> TypeFighting
        "poison" -> TypePoison
        "ground" -> TypeGround
        "flying" -> TypeFlying
        "psychic" -> TypePsychic
        "bug" -> TypeBug
        "rock" -> TypeRock
        "ghost" -> TypeGhost
        "dragon" -> TypeDragon
        "dark" -> TypeDark
        "steel" -> TypeSteel
        "fairy" -> TypeFairy
        else -> Color.Black
    }
}

fun parseStatToColor(stat: Stat): Color {
    return when(stat.stat.name.lowercase(Locale.ROOT)) {
        "hp" -> HPColor
        "attack" -> AtkColor
        "defense" -> DefColor
        "special-attack" -> SpAtkColor
        "special-defense" -> SpDefColor
        "speed" -> SpdColor
        else -> Color.White
    }
}

fun parseStatToColor2(stat: String): Color {
    return when(stat.lowercase(Locale.ROOT)) {
        "hp" -> HPColor
        "attack" -> AtkColor
        "defense" -> DefColor
        "special-attack" -> SpAtkColor
        "special-defense" -> SpDefColor
        "speed" -> SpdColor
        else -> Color.White
    }
}

fun parseStatToAbbr2(stat: String): String {
    return when(stat.lowercase(Locale.ROOT)) {
        "hp" -> "HP"
        "attack" -> "Atk"
        "defense" -> "Def"
        "special-attack" -> "SpAtk"
        "special-defense" -> "SpDef"
        "speed" -> "Spd"
        else -> ""
    }
}

fun parseStatToAbbr(stat: Stat): String {
    return when(stat.stat.name.lowercase(Locale.ROOT)) {
        "hp" -> "HP"
        "attack" -> "Atk"
        "defense" -> "Def"
        "special-attack" -> "SpAtk"
        "special-defense" -> "SpDef"
        "speed" -> "Spd"
        else -> ""
    }
}