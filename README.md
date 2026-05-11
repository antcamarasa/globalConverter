# Global Converter

Application Java en ligne de commande qui convertit du texte entre différentes bases numériques (binaire, octal, décimal, hexadécimal, texte ASCII), avec une option de chiffrement César.

## Contexte

Projet pédagogique réalisé dans le cadre d'une entrée en Licence 2 au sein de l'école La Plateforme. 
L'objectif est de manipuler les conversions de bases sans utiliser les fonctions Java natives de conversion (`Integer.toBinaryString`, `Integer.toHexString`, etc.). Toute la logique de conversion est implémentée à la main.

## Prérequis

- Java 17 ou supérieur installé sur la machine
- Optionnel pour la compilation : Maven ou un IDE (IntelliJ, Eclipse)

Vérifier l'installation :

```bash
java -version
```

## Compilation

### Avec Maven

```bash
mvn clean package
```

Le `.jar` est généré dans `target/`.

### Avec IntelliJ

`Build → Build Artifacts → Build`

Le `.jar` est généré dans `out/artifacts/<nom_projet>_jar/`.

## Utilisation

Format général :

```bash
java -jar globalConverter.jar <base_origine> <base_destination> "<texte>" [option_chiffrement] [clé]
```

### Bases supportées

| Option courte | Option longue | Description |
|---|---|---|
| `-t` | `text` | Texte brut |
| `-d` | `decimal` | Représentation décimale |
| `-b` | `binary` | Représentation binaire |
| `-o` | `octal` | Représentation octale |
| `-h` | `hexadecimal` | Représentation hexadécimale |

### Chiffrement (optionnel)

| Option | Description |
|---|---|
| `-e <clé>` | Chiffrement César avec rotation de `<clé>` |
| `-d <clé>` | Déchiffrement César avec rotation de `<clé>` |

Note : le flag `-d` est réutilisé pour decimal et decrypt. Le contexte de position dans la commande détermine son interprétation.

## Exemples

### Conversion simple

Texte vers hexadécimal :

```bash
java -jar globalConverter.jar -t -h "hello world"
```

Sortie : `68 65 6C 6C 6F 20 77 6F 72 6C 64`

Texte vers binaire :

```bash
java -jar globalConverter.jar -t -b "hello world"
```

Sortie : `1101000 1100101 1101100 1101100 1101111 100000 1110111 1101111 1110010 1101100 1100100`

### Conversion inverse

Hexadécimal vers texte :

```bash
java -jar globalConverter.jar -h -t "68 65 6C 6C 6F 20 77 6F 72 6C 64"
```

Sortie : `hello world`

### Conversion entre deux bases non textuelles

Binaire vers octal (passage par le texte ASCII en interne) :

```bash
java -jar globalConverter.jar -b -o "1101000 1100101 1101100 1101100 1101111"
```

Sortie : `150 145 154 154 157`

### Chiffrement César + conversion

Texte vers hexadécimal avec chiffrement César +3 :

```bash
java -jar globalConverter.jar -t -h "hello world" -e 3
```

Sortie : `6B 68 6F 6F 72 20 7A 72 75 6F 67`

### Déchiffrement après conversion inverse

Hexadécimal vers texte avec déchiffrement César -3 :

```bash
java -jar globalConverter.jar -h -t "6B 68 6F 6F 72 20 7A 72 75 6F 67" -d 3
```

Sortie : `hello world`

## Architecture

Le projet suit une architecture en couches :

- **`Converter`** (interface) : contrat commun à tous les convertisseurs (binaire, octal, décimal, hexadécimal, texte)
- **`TextConverter`** : implémentation identité (Null Object Pattern) qui permet d'uniformiser le pipeline de conversion
- **`ConversionPipeline`** : orchestrateur qui applique séquentiellement la conversion d'entrée → chiffrement éventuel → conversion de sortie
- **`ModelObject`** + **`Builder`** : porteur immutable des paramètres de la commande
- **`Caesar`** + **`Encryption`** (interface) : chiffrement de César avec gestion des trois bornes ASCII (minuscules, majuscules, chiffres) + une exception pour les espaces " ".  
- **`MathUtils`** : utilitaires (puissances, décomposition en base)


## Tests

Lancer les tests :

```bash
mvn test
```

Ou dans IntelliJ : clic droit sur `src/test/java → Run All Tests`.
