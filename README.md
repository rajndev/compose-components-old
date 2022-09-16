# Compose Compoents Library
An Android Jetpack Compose Components library featuring customized UI Composables. The library uses Material2 components and theming.

## Importing the library into your project

### settings.gradle file

```gradle
repositories {
  // When using build.gradle
  maven { url 'https://jitpack.io' } 
        
  // When using build.gradle.kts
  maven { setUrl("https://jitpack.io") }
}
```

### build.gradle file

```gradle
dependencies {
  implementation 'com.github.rajndev:compose-components:<latest-version>'
}
```

## Included Components

### CustomAppBar: A customizeable appbar

```kotlin
@Composable
fun CustomAppBar(
    title: String,
    titleModifier: Modifier = Modifier,
    titleColor: Color = Color.Black,
    titleFontSize: TextUnit = TextUnit.Unspecified,
    titleTextStyle: TextStyle = MaterialTheme.typography.h6,
    backgroundColor: Color = Color.White,
    appBarElevation: Dp = Dp.Unspecified,
    actions: @Composable () -> Unit = { },
    navController: NavController? = null,
    scaffoldState: ScaffoldState? = null,
)
```

### AppBarAction: A customizeable icon component for the actions section of `TopAppBar`

```kotlin
@Composable
fun AppBarAction(
    modifier: Modifier = Modifier,
    imageVector: ImageVector? = null,
    painter: Painter? = null,
    iconColor: Color = Color.Black,
    contentDescription: String = "",
    onClick: () -> Unit = { }
)
```

### CustomOutlinedTextField: Customizeable `OutlinedTextField` composable

```kotlin
@Composable
fun CustomOutlinedTextField(
    modifier: Modifier = Modifier,
    label: String = "",
    inputVal: String,
    textStyle: TextStyle = TextStyle(Color.Black),
    isSingleLine: Boolean = false,
    maxLines: Int = 0,
    isError: Boolean = false,
    errorTextMessage: String = "",
    isReadOnly: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onValChange: (String) -> Unit
)
```

### DropDownMenuOutlinedTextField: A customizeable dropdownmenu integrated into an `outlinedTextField`

```kotlin
@Composable
fun DropdownMenuOutlinedTextField(
    modifier: Modifier = Modifier,
    fieldLabel: String,
    inputVal: String,
    isSingleLine: Boolean = false,
    maxLines: Int = 1,
    isError: Boolean = false,
    errorTextMessage: String = "",
    dropDownList: List<String>? = null,
    dropDownListMap: Map<Any, String>? = null,
    onValueChanged: (Any?, String) -> Unit
)
```

### AppImage: A customizeable image container

```kotlin
@Composable
fun AppImage(
    cardModifier: Modifier,
    cardElevationAmount: Dp,
    imageModifier: Modifier = Modifier,
    imageContainerModifier: Modifier = Modifier,
    imageUri: Uri = Uri.EMPTY,
    imageContentScale: ContentScale = ContentScale.None,
    imageContentDescription: String? = null,
    onImageClick: (() -> Unit)? = null,
    onDeleteIconClick: () -> Unit,
    onZoomIconClick: () -> Unit,
    loading: @Composable (SubcomposeAsyncImageScope.(AsyncImagePainter.State.Loading) -> Unit)? = null,
    success: @Composable (SubcomposeAsyncImageScope.(AsyncImagePainter.State.Success) -> Unit)? = null,
    error: @Composable (SubcomposeAsyncImageScope.(AsyncImagePainter.State.Error) -> Unit)? = null
)
```

### ImageDialog: A customizeable composable for expanding or zooming an image

```kotlin
@Composable
fun ImageDialog(
    showDialog: MutableState<Boolean>,
    imageUri: Uri,
    imageContentDescription: String? = null,
    loading: @Composable (SubcomposeAsyncImageScope.(AsyncImagePainter.State.Loading) -> Unit)? = null,
    success: @Composable (SubcomposeAsyncImageScope.(AsyncImagePainter.State.Success) -> Unit)? = null,
    error: @Composable (SubcomposeAsyncImageScope.(AsyncImagePainter.State.Error) -> Unit)? = null
)
```

### ConditionalCircularProgressBar

```kotlin
@Composable
fun ConditionalCircularProgressBar(
    modifier: Modifier = Modifier,
    isDisplayed: Boolean
)
```

### Image Utilities: Utility functions to handle image processing

```kotlin
object ImageUtils {
  fun Context.getImageDimensionsFromUri(uri: Uri): Pair<Int, Int>

  fun File.writeBitmap(bitmap: Bitmap, format: Bitmap.CompressFormat, quality: Int)

  suspend fun Context.compressImage(imageUri: Uri, destFolder: String, filePrefix: String): Uri

  fun Context.createImageThumbnail(uri: Uri, destFolder: String, filePrefix: String): Uri

  fun extractItemThumbnail(uri: Uri): Bitmap?

  fun Context.getCamMediaUri(camImageDirectory: String): Uri

  fun Uri.getImageDimensions(context: Context): Pair<Int, Int>
}
```

### Metrics Utilities: Utility functions to handle compenent measurements

```kotlin
object MetricsUtils {
  fun convertDpToPixel(dp: Float, context: Context?): Float

  fun convertPixelsToDp(px: Float, context: Context?): Float
}
```

## Contributions

Any and all contributions are welcome and appreciated. Please open a pull request when you are ready. Thank you.

## License

```
MIT License

Copyright (c) 2022 Raj Narayanan

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
